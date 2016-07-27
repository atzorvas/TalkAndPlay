package org.scify.talkandplay.gui.grid.games;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.swing.JPanel;
import org.scify.talkandplay.gui.grid.BaseGridPanel;
import org.scify.talkandplay.gui.grid.GridFrame;
import org.scify.talkandplay.gui.grid.tiles.TileAction;
import org.scify.talkandplay.gui.helpers.UIConstants;
import org.scify.talkandplay.models.User;
import org.scify.talkandplay.models.games.GameImage;
import org.scify.talkandplay.models.games.GameType;
import org.scify.talkandplay.models.games.SequenceGame;

public class SequenceGamePanel extends BaseGridPanel {

    private JPanel imagesPanel, correctImagesPanel, panel;
    private SequenceGame game;

    private int correctImages;

    public SequenceGamePanel(User user, GridFrame parent) {
        super(user, parent);
        this.correctImages = 1;

        initComponents();
        initCustomComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 518, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 235, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void initCustomComponents() {
        /*setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

         imagesPanel = new JPanel();
         correctImagesPanel = new JPanel();
         imagesPanel.setLayout(new BoxLayout(imagesPanel, BoxLayout.LINE_AXIS));
         imagesPanel.setBorder(new MatteBorder(0, 0, 1, 0, Color.decode(UIConstants.grey)));
         correctImagesPanel.setLayout(new BoxLayout(correctImagesPanel, BoxLayout.LINE_AXIS));
         */

        UIConstants.getInstance().setRows(2);
        UIConstants.getInstance().setColumns(4);
        initLayout();
        panelList = new ArrayList<>();

        //select a random game
        Random randomGenerator = new Random();
        for (GameType gameType : user.getGameModule().getGameTypes()) {
            if ("sequenceGame".equals(gameType.getType())) {
                for (int j = 0; j < gameType.getGames().size(); j++) {
                    int i = randomGenerator.nextInt(gameType.getGames().size());
                    if (gameType.getGames().get(i).isEnabled()) {
                        game = (SequenceGame) gameType.getGames().get(i);
                        break;
                    }
                }
            }
        }

        //draw the images in a random order
        List<GameImage> tmpImages = new ArrayList(game.getImages());
        int i;
        while (!tmpImages.isEmpty()) {
            i = randomGenerator.nextInt(tmpImages.size());
            JPanel panel = createGameItem(tmpImages.get(i));
            add(panel, c);
            c.gridx++;
            panelList.add(panel);
            tmpImages.remove(i);
        }

        c.gridx = 0;
        c.gridy++;
        for (int j = 0; j < game.getImages().size(); j++) {
            add(tileCreator.createEmpty(), c);
            c.gridx++;
        }

        /* add(imagesPanel);
         add(correctImagesPanel);*/
        revalidate();
        repaint();
        parent.clearGrid();
        parent.addGrid(this);
        parent.revalidate();
        parent.repaint();

        timer.setList(panelList);
        timer.start();
    }

    private JPanel createGameItem(final GameImage image) {
        final JPanel panel = tileCreator.create("",
                image.getImage(),
                null,
                new TileAction() {
                    @Override
                    public void act() {
                        System.out.println("sequence clicky");
                        if (correctImages == image.getOrder()) {
                            correctImages++;
                            // imagesPanel.remove(panel);
                            //  panel.setBorder(null);
                            //  panelList.remove(panel);
                            //  correctImagesPanel.add(panel);

                        } else {
                            timer.cancel();
                            tileCreator.playAudio(game.getErrorSound());
                        }
                    }

                    @Override
                    public void audioFinished() {
                    }
                });

        return panel;
    }

    private void congratulate(GameImage image) {
        tileCreator.playAudio(game.getWinSound());

        JPanel finalImage = tileCreator.create("",
                image.getImage(),
                null,
                new TileAction() {
                    @Override
                    public void act() {
                    }

                    @Override
                    public void audioFinished() {
                    }
                });

        JPanel nextGame = tileCreator.create("Επόμενο παιχνίδι",
                getClass().getResource("/org/scify/talkandplay/resources/more-icon.png").getFile(),
                null,
                new TileAction() {
                    @Override
                    public void act() {
                        SequenceGamePanel gamePanel = new SequenceGamePanel(user, parent);
                        parent.clearGrid();
                        parent.addGrid(gamePanel);
                    }

                    @Override
                    public void audioFinished() {
                    }
                });

        JPanel back = tileCreator.create("Πίσω",
                getClass().getResource("/org/scify/talkandplay/resources/back-icon.png").getFile(),
                null,
                new TileAction() {
                    @Override
                    public void act() {
                        GamesPanel gamesPanel = new GamesPanel(user, parent);
                        parent.clearGrid();
                        parent.addGrid(gamesPanel);
                    }

                    @Override
                    public void audioFinished() {
                    }
                });

        panelList.add(nextGame);
        panelList.add(back);

        removeAll();
        add(finalImage);
        add(nextGame);
        add(back);
        revalidate();
        repaint();

        timer.setList(panelList);
        timer.start();
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
