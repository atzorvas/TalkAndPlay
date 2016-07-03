package org.scify.talkandplay.utils;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;
import org.scify.talkandplay.models.Category;
import org.scify.talkandplay.models.modules.CommunicationModule;
import org.scify.talkandplay.models.Configuration;
import org.scify.talkandplay.models.modules.EntertainmentModule;
import org.scify.talkandplay.models.modules.GameModule;
import org.scify.talkandplay.models.Tile;
import org.scify.talkandplay.models.User;
import org.scify.talkandplay.models.games.GameImage;
import org.scify.talkandplay.models.games.GameType;
import org.scify.talkandplay.models.games.SequenceGame;
import org.scify.talkandplay.models.games.SimilarityGame;
import org.scify.talkandplay.models.games.StimulusReactionGame;
import org.scify.talkandplay.models.modules.MusicModule;
import org.scify.talkandplay.models.modules.VideoModule;
import org.scify.talkandplay.models.sensors.KeyboardSensor;
import org.scify.talkandplay.models.sensors.MouseSensor;
import org.scify.talkandplay.models.sensors.Sensor;

/**
 * ConfigurationHandler is responsible for parsing the xml and other xml-related
 * functions.
 *
 * @author christina
 */
public class ConfigurationHandler {

    private Document configurationFile;
    private List<User> profiles;
    private File file;
    private String projectPath;
    private Map<String, List<String>> userFiles;

    private static String DEFAULT_SOUND;

    public ConfigurationHandler() {
        DEFAULT_SOUND = getClass().getResource("/org/scify/talkandplay/resources/sounds/cat.mp3").getPath();

        try {
            projectPath = System.getProperty("user.dir") + File.separator + "conf.xml";
            file = new File(projectPath);
            if (!file.exists() || file.isDirectory()) {
                PrintWriter writer = new PrintWriter(projectPath, "UTF-8");
                writer.println("<?xml version=\"1.0\"?>\n"
                        + "<profiles></profiles>");
                writer.close();
            }

            SAXBuilder builder = new SAXBuilder();
            configurationFile = (Document) builder.build(file);

            profiles = parseXML();
        } catch (Exception e) {
            e.printStackTrace(System.err);
        }
    }

    public Document getConfigurationFile() {
        return configurationFile;
    }

    public List<User> getProfiles() {
        try {
            profiles = refreshXMLFile();
            return profiles;
        } catch (Exception ex) {
            return null;
        }
    }

    public User getUser(String name) {
        for (User user : getProfiles()) {
            if (user.getName().equals(name)) {
                return user;
            }
        }
        return null;
    }

    public Element getProfileElement(String name) throws Exception {
        Element profile = null;
        SAXBuilder builder = new SAXBuilder();
        configurationFile = (Document) builder.build(file);
        List profiles = configurationFile.getRootElement().getChildren();

        for (int i = 0; i < profiles.size(); i++) {

            profile = (Element) profiles.get(i);

            if (name.equals(profile.getChildText("name"))) {
                break;
            }
        }
        return profile;
    }

    /**
     * Parse the XML file that holds all users' configuration
     *
     * @return
     * @throws Exception
     */
    private List<User> parseXML() throws Exception {
        List<User> list = new ArrayList<>();
        List profiles = configurationFile.getRootElement().getChildren();

        for (int i = 0; i < profiles.size(); i++) {

            Element profile = (Element) profiles.get(i);
            User user = new User(profile.getChildText("name"), profile.getChildText("image"));

            if (profile.getAttributeValue("preselected") != null) {
                user.setPreselected(Boolean.parseBoolean(profile.getAttributeValue("preselected")));
            } else {
                user.setPreselected(false);
            }

            Element configuration = (Element) profile.getChild("configuration");
            user.setConfiguration(getConfiguration(configuration));

            Element communication = (Element) profile.getChild("communication");
            user.setCommunicationModule(getCommunicationModule(communication));

            Element entertainment = (Element) profile.getChild("entertainment");
            user.setEntertainmentModule(getEntertainmentModule(entertainment));

            Element games = (Element) profile.getChild("games");
            user.setGameModule(getGameModule(games));

            list.add(user);
        }

        return list;
    }

    /**
     * Get the configuration list for a certain user
     *
     * @param configurationNode
     * @param profile
     * @return
     * @throws Exception
     */
    private Configuration getConfiguration(Element configurationNode) {
        Configuration configuration = new Configuration();
        Sensor selectionSensor = null;
        Sensor navigationSensor = null;

        configuration.setRotationSpeed(Integer.parseInt(configurationNode.getChildText("rotationSpeed")));
        configuration.setDefaultGridRow(Integer.parseInt(configurationNode.getChildText("defaultGridRow")));
        configuration.setDefaultGridColumn(Integer.parseInt(configurationNode.getChildText("defaultGridColumn")));
        configuration.setSound("true".equals(configurationNode.getChildText("sound")));
        configuration.setImage("true".equals(configurationNode.getChildText("image")));
        configuration.setText("true".equals(configurationNode.getChildText("text")));

        Element selectionSensorEl = configurationNode.getChild("selectionSensor");
        if ("mouse".equals(selectionSensorEl.getChildText("type"))) {
            selectionSensor = new MouseSensor(Integer.parseInt(selectionSensorEl.getChildText("button")),
                    Integer.parseInt(selectionSensorEl.getChildText("clickCount")), selectionSensorEl.getChildText("type"));
        } else if ("keyboard".equals(selectionSensorEl.getChildText("type"))) {
            selectionSensor = new KeyboardSensor(Integer.parseInt(selectionSensorEl.getChildText("keyCode")),
                    selectionSensorEl.getChildText("keyChar"), selectionSensorEl.getChildText("type"));
        }

        Element navigationSensorEl = configurationNode.getChild("navigationSensor");
        if (navigationSensorEl != null) {
            if ("mouse".equals(navigationSensorEl.getChildText("type"))) {
                navigationSensor = new MouseSensor(Integer.parseInt(navigationSensorEl.getChildText("button")),
                        Integer.parseInt(navigationSensorEl.getChildText("clickCount")), navigationSensorEl.getChildText("type"));
            } else if ("keyboard".equals(navigationSensorEl.getChildText("type"))) {
                navigationSensor = new KeyboardSensor(Integer.parseInt(navigationSensorEl.getChildText("keyCode")),
                        navigationSensorEl.getChildText("keyChar"), navigationSensorEl.getChildText("type"));
            }
            configuration.setNavigationSensor(navigationSensor);
        }

        configuration.setSelectionSensor(selectionSensor);

        return configuration;
    }

    private CommunicationModule getCommunicationModule(Element communicationNode) {
        //set the communication module settings
        List<Category> categoriesArray = new ArrayList<>();

        Element categories = (Element) communicationNode.getChild("categories");
        categoriesArray = getCategories(categories, categoriesArray, null);

        CommunicationModule communicationModule = new CommunicationModule();
        communicationModule.setName(communicationNode.getChildText("name"));
        communicationModule.setSound(getSound(communicationNode.getChildText("sound")));
        communicationModule.setRows(Integer.parseInt(communicationNode.getChildText("rows")));
        communicationModule.setColumns(Integer.parseInt(communicationNode.getChildText("columns")));

        if (communicationNode.getChildText("image").isEmpty()) {
            communicationModule.setImageURL(getClass().getResource("/org/scify/talkandplay/resources/defaultImgs/communication_module.png"));
        } else {
            communicationModule.setImage(communicationNode.getChildText("image"));
        }

        communicationModule.setEnabled("true".equals(communicationNode.getChildText("enabled")));
        communicationModule.setCategories(categoriesArray);

        return communicationModule;
    }

    private EntertainmentModule getEntertainmentModule(Element entertainmentNode) {
        //set the entertainment module settings
        EntertainmentModule entertainmentModule = new EntertainmentModule();
        entertainmentModule.setName(entertainmentNode.getChildText("name"));
        entertainmentModule.setSound(getSound(entertainmentNode.getChildText("sound")));
        entertainmentModule.setEnabled("true".equals(entertainmentNode.getChildText("enabled")));

        if (entertainmentNode.getChildText("image").isEmpty()) {
            entertainmentModule.setImageURL(getClass().getResource("/org/scify/talkandplay/resources/defaultImgs/entertainment_module.png"));
        } else {
            entertainmentModule.setImage(entertainmentNode.getChildText("image"));
        }

        //set the music module
        Element musicNode = (Element) entertainmentNode.getChild("music");
        MusicModule musicModule = new MusicModule();
        musicModule.setName(musicNode.getChildText("name"));
        musicModule.setImage(musicNode.getChildText("image"));
        musicModule.setSound(getSound(musicNode.getChildText("sound")));
        musicModule.setFolderPath(musicNode.getChildText("folderPath"));
        musicModule.setEnabled("true".equals(musicNode.getChildText("name")));

        //set the video module
        Element videoNode = (Element) entertainmentNode.getChild("video");
        VideoModule videoModule = new VideoModule();
        videoModule.setName(videoNode.getChildText("name"));
        videoModule.setImage(videoNode.getChildText("image"));
        videoModule.setSound(getSound(videoNode.getChildText("sound")));
        videoModule.setFolderPath(videoNode.getChildText("folderPath"));
        videoModule.setEnabled("true".equals(videoNode.getChildText("name")));

        entertainmentModule.setMusicModule(musicModule);
        entertainmentModule.setVideoModule(videoModule);

        return entertainmentModule;
    }

    private GameModule getGameModule(Element gameNode) {
        //set the game module settings
        GameModule gameModule = new GameModule();
        gameModule.setName(gameNode.getChildText("name"));
        gameModule.setSound(getSound(gameNode.getChildText("sound")));
        gameModule.setEnabled("true".equals(gameNode.getChildText("enabled")));

        if (gameNode.getChildText("image").isEmpty()) {
            gameModule.setImageURL(getClass().getResource("/org/scify/talkandplay/resources/defaultImgs/games_module.png"));
        } else {
            gameModule.setImage(gameNode.getChildText("image"));
        }

        //set the stimulus reaction games
        Element stimulusReactionGamesNode = gameNode.getChild("stimulusReactionGames");
        if (stimulusReactionGamesNode != null) {
            GameType stimulusReactionType = new GameType(stimulusReactionGamesNode.getChildText("name"),
                    stimulusReactionGamesNode.getChildText("image"),
                    "true".equals(stimulusReactionGamesNode.getChildText("enabled")),
                    "stimulusReactionGame");

            List gamesList = stimulusReactionGamesNode.getChild("games").getChildren();

            for (int i = 0; i < gamesList.size(); i++) {
                StimulusReactionGame game = new StimulusReactionGame(((Element) gamesList.get(i)).getChildText("name"),
                        ((Element) gamesList.get(i)).getChildText("image"),
                        "true".equals(((Element) gamesList.get(i)).getChildText("enabled")),
                        Integer.parseInt(((Element) gamesList.get(i)).getChildText("difficulty")));
                game.setWinSound(((Element) gamesList.get(i)).getChildText("winSound"));
                game.setErrorSound(((Element) gamesList.get(i)).getChildText("errorSound"));

                List imagesList = ((Element) gamesList.get(i)).getChild("gameImages").getChildren();

                for (int j = 0; j < imagesList.size(); j++) {
                    GameImage image = new GameImage(((Element) imagesList.get(j)).getChildText("name"),
                            ((Element) imagesList.get(j)).getChildText("path"),
                            Integer.parseInt(((Element) imagesList.get(j)).getChildText("order")));
                    game.getImages().add(image);
                }
                stimulusReactionType.getGames().add(game);
            }
            gameModule.getGameTypes().add(stimulusReactionType);
        }

        //set the sequence games
        Element sequenceGamesNode = gameNode.getChild("sequenceGames");
        if (sequenceGamesNode != null) {
            GameType sequenceGameType = new GameType(sequenceGamesNode.getChildText("name"),
                    sequenceGamesNode.getChildText("image"),
                    "true".equals(sequenceGamesNode.getChildText("enabled")),
                    "sequenceGame");

            List gamesList = sequenceGamesNode.getChild("games").getChildren();

            for (int i = 0; i < gamesList.size(); i++) {
                SequenceGame game = new SequenceGame(((Element) gamesList.get(i)).getChildText("name"),
                        ((Element) gamesList.get(i)).getChildText("image"),
                        "true".equals(((Element) gamesList.get(i)).getChildText("enabled")),
                        Integer.parseInt(((Element) gamesList.get(i)).getChildText("difficulty")));
                game.setWinSound(((Element) gamesList.get(i)).getChildText("winSound"));
                game.setErrorSound(((Element) gamesList.get(i)).getChildText("errorSound"));

                List imagesList = ((Element) gamesList.get(i)).getChild("gameImages").getChildren();

                for (int j = 0; j < imagesList.size(); j++) {
                    GameImage image = new GameImage(((Element) imagesList.get(j)).getChildText("name"),
                            ((Element) imagesList.get(j)).getChildText("path"),
                            Integer.parseInt(((Element) imagesList.get(j)).getChildText("order")));
                    game.setEnabled("true".equals(((Element) imagesList.get(j)).getChildText("enabled")));
                    game.getImages().add(image);
                }
                sequenceGameType.getGames().add(game);
            }
            gameModule.getGameTypes().add(sequenceGameType);
        }

        //set the similar games
        Element similarGamesNode = gameNode.getChild("similarityGames");
        if (similarGamesNode != null) {
            GameType similarityGameType = new GameType(similarGamesNode.getChildText("name"),
                    similarGamesNode.getChildText("image"),
                    "true".equals(similarGamesNode.getChildText("enabled")),
                    "similarityGame");

            List gamesList = similarGamesNode.getChild("games").getChildren();

            for (int i = 0; i < gamesList.size(); i++) {
                SimilarityGame game = new SimilarityGame(((Element) gamesList.get(i)).getChildText("name"),
                        ((Element) gamesList.get(i)).getChildText("image"),
                        "true".equals(((Element) gamesList.get(i)).getChildText("enabled")),
                        Integer.parseInt(((Element) gamesList.get(i)).getChildText("difficulty")));
                game.setWinSound(((Element) gamesList.get(i)).getChildText("winSound"));
                game.setErrorSound(((Element) gamesList.get(i)).getChildText("errorSound"));

                List imagesList = ((Element) gamesList.get(i)).getChild("gameImages").getChildren();

                for (int j = 0; j < imagesList.size(); j++) {
                    GameImage image = new GameImage(((Element) imagesList.get(j)).getChildText("name"),
                            ((Element) imagesList.get(j)).getChildText("path"), 0);
                    game.setEnabled("true".equals(((Element) imagesList.get(j)).getChildText("enabled")));
                    game.getImages().add(image);
                }
                similarityGameType.getGames().add(game);
            }
            gameModule.getGameTypes().add(similarityGameType);
        }

        return gameModule;
    }

    /**
     * Recursive function to get the user categories
     *
     * @param categoriesNode
     * @param categories
     * @return
     */
    private List<Category> getCategories(Element categoriesNode, List<Category> categories, Category parent) {

        if (categoriesNode == null) {
            return categories;
        } else {
            //get the user categories

            for (int i = 0; i < categoriesNode.getChildren().size(); i++) {

                Element categoryEl = (Element) categoriesNode.getChildren().get(i);

                Category category = new Category(
                        categoryEl.getAttributeValue("name"),
                        Integer.parseInt(categoryEl.getChildText("rows")),
                        Integer.parseInt(categoryEl.getChildText("columns")),
                        categoryEl.getChildText("image"));

                category.setSound(getSound(categoryEl.getChildText("sound")));

                if (parent != null) {
                    category.setParentCategory(new Category(parent.getName()));
                }

                if (categoryEl.getChildText("editable") != null) {
                    category.setEditable(Boolean.parseBoolean(categoryEl.getChildText("editable")));
                } else {
                    category.setEditable(true);
                }

                if (categoryEl.getChildText("order") != null) {
                    category.setOrder(Integer.parseInt(categoryEl.getChildText("order")));
                } else {
                    category.setOrder(0);
                }

                if (categoryEl.getChildText("hasSound") != null) {
                    category.setHasSound("true".equals(categoryEl.getChildText("hasSound")));
                } else {
                    category.setHasSound(true);
                }

                if (categoryEl.getChildText("hasImage") != null) {
                    category.setHasImage("true".equals(categoryEl.getChildText("hasImage")));
                } else {
                    category.setHasImage(true);
                }

                if (categoryEl.getChildText("hasText") != null) {
                    category.setHasText("true".equals(categoryEl.getChildText("hasText")));
                } else {
                    category.setHasText(true);
                }

                //set the tiles
                if (categoryEl.getChild("tiles") != null) {
                    Element tileEl;
                    for (int j = 0; j < categoryEl.getChild("tiles").getChildren().size(); j++) {
                        tileEl = (Element) categoryEl.getChild("tiles").getChildren().get(j);

                        int order = 0;
                        if (tileEl.getAttributeValue("order") != null) {
                            order = Integer.parseInt(categoryEl.getAttributeValue("order"));
                        }

                        category.getTiles().add(new Tile(tileEl.getChildText("name"), tileEl.getChildText("image"), tileEl.getChildText("sound"), order));
                    }
                }

                if (parent != null) {
                    category.setParentCategory(parent);
                }

                List<Category> categoriesArray = new ArrayList<>();

                Element subCategories = (Element) categoryEl.getChild("categories");
                categoriesArray = getCategories(subCategories, categoriesArray, category);

                category.setSubCategories((ArrayList<Category>) categoriesArray);
                categories.add(category);

            }
            return categories;
        }
    }

    /**
     * For a certain user, check that all the files exist (in case the files
     * must be configured again)
     *
     * @param username
     * @return
     */
    public boolean hasBrokenFiles(String username) {
        for (Map.Entry<String, List<String>> entry : userFiles.entrySet()) {
            if (entry.getKey().equals(username)) {
                for (String path : entry.getValue()) {
                    if (!(new File(path).isFile())) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    /**
     * Set the sound, either the path given or the default one
     *
     * @param path
     * @return
     */
    private String getSound(String path) {
        if (path != null) {
            return path;
        } else {
            return DEFAULT_SOUND;
        }
    }

    public List refreshXMLFile() throws Exception {
        SAXBuilder builder = new SAXBuilder();
        configurationFile = (Document) builder.build(file);
        profiles = parseXML();
        return profiles;
    }

    /**
     * Write the new data to the xml file
     */
    public void writeToXmlFile() throws Exception {
        XMLOutputter xmlOutput = new XMLOutputter();
        xmlOutput.setFormat(Format.getPrettyFormat());
        xmlOutput.output(configurationFile, new FileWriter(projectPath));
    }

}
