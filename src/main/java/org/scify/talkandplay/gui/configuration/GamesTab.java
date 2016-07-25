package org.scify.talkandplay.gui.configuration;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.border.LineBorder;
import org.scify.talkandplay.gui.helpers.GuiHelper;
import org.scify.talkandplay.gui.helpers.UIConstants;
import org.scify.talkandplay.models.User;
import org.scify.talkandplay.models.games.Game;
import org.scify.talkandplay.models.games.GameType;
import org.scify.talkandplay.services.GameService;

public class GamesTab extends javax.swing.JPanel {

    private User user;
    private GuiHelper guiHelper;
    private GameService gameService;
    private ConfigurationPanel parent;
    private List<GamePanel> gamePanels;
    private String currentGameType;

    public GamesTab(User user, ConfigurationPanel parent) {
        this.user = user;
        this.guiHelper = new GuiHelper();
        this.gameService = new GameService();
        this.parent = parent;

        initComponents();
        initCustomComponents();
    }

    private void initCustomComponents() {
        gamePanels = new ArrayList();
        guiHelper.drawButton(saveButton);
        Font font = new Font(UIConstants.mainFont, Font.BOLD, 16);
        step1Label.setFont(font);
        step2Label.setFont(font);
        step2Label.setVisible(false);
        saveButton.setVisible(false);

        gamesPanel2.setLayout(new BoxLayout(gamesPanel2, BoxLayout.Y_AXIS));
        gamesComboBox.setBorder(new LineBorder(Color.decode(UIConstants.green), 1));
        gamesComboBox.setFont(new Font(UIConstants.green, Font.PLAIN, 12));

        gamesComboBox.addItem("[-- Επίλεξε παιχνίδι --]");
        gamesComboBox.addItem("Ερέθισμα - Αντίδραση");
        gamesComboBox.addItem("Χρονική αλληλουχία");
        gamesComboBox.addItem("Βρες το όμοιο");

        gamesComboBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent ie) {
                if ("Ερέθισμα - Αντίδραση".equals(ie.getItem())) {
                    showGamesPerType("stimulusReactionGame");
                    currentGameType = "stimulusReactionGames";
                } else if ("Χρονική αλληλουχία".equals(ie.getItem())) {
                    showGamesPerType("sequenceGame");
                    currentGameType = "sequenceGames";
                } else if ("Βρες το όμοιο".equals(ie.getItem())) {
                    showGamesPerType("similarityGame");
                    currentGameType = "similarityGames";
                }
            }
        });
    }

    private void showGamesPerType(String type) {
        gamesPanel2.removeAll();
        gamePanels.clear();
        GameType gameType = null;

        for (GameType gt : user.getGameModule().getGameTypes()) {
            if (type.equals(gt.getType())) {
                gameType = gt;
            }
        }

        if (gameType != null) {
            if (gameType.getGames().size() > 0) {
                for (Game game : gameType.getGames()) {
                    GamePanel gamePanel = new GamePanel(game);
                    gamesPanel2.add(gamePanel);
                    gamePanels.add(gamePanel);

                }
                step2Label.setVisible(true);
                saveButton.setVisible(true);
            }
        } else {
            step2Label.setVisible(true);
            saveButton.setVisible(false);
            gamesPanel2.add(new JLabel("Δεν υπάρχουν παιχνίδια σε αυτή την κατηγορία"));
        }

        gamesPanel2.revalidate();
        gamesPanel2.repaint();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        wrapperPanel = new javax.swing.JPanel();
        step1Label = new javax.swing.JLabel();
        gamesComboBox = new javax.swing.JComboBox();
        step2Label = new javax.swing.JLabel();
        gamesPanel2 = new javax.swing.JPanel();
        saveButton = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));

        jScrollPane1.setBorder(null);

        wrapperPanel.setBackground(new java.awt.Color(255, 255, 255));

        step1Label.setText("1. Επίλεξε κατηγορία παιχνιδιού");

        gamesComboBox.setBackground(new java.awt.Color(255, 255, 255));

        step2Label.setText("2. Διαχειρίσου τις διαθέσιμες ομάδες εικόνων");

        gamesPanel2.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout gamesPanel2Layout = new javax.swing.GroupLayout(gamesPanel2);
        gamesPanel2.setLayout(gamesPanel2Layout);
        gamesPanel2Layout.setHorizontalGroup(
            gamesPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        gamesPanel2Layout.setVerticalGroup(
            gamesPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 172, Short.MAX_VALUE)
        );

        saveButton.setBackground(new java.awt.Color(75, 161, 69));
        saveButton.setFont(saveButton.getFont());
        saveButton.setForeground(new java.awt.Color(255, 255, 255));
        saveButton.setText("Αποθήκευση");
        saveButton.setBorder(null);
        saveButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                saveButtonMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout wrapperPanelLayout = new javax.swing.GroupLayout(wrapperPanel);
        wrapperPanel.setLayout(wrapperPanelLayout);
        wrapperPanelLayout.setHorizontalGroup(
            wrapperPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(wrapperPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(wrapperPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(step1Label)
                    .addComponent(step2Label)
                    .addComponent(gamesComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, wrapperPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(gamesPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(wrapperPanelLayout.createSequentialGroup()
                .addGap(271, 271, 271)
                .addComponent(saveButton)
                .addContainerGap(324, Short.MAX_VALUE))
        );
        wrapperPanelLayout.setVerticalGroup(
            wrapperPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(wrapperPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(step1Label)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(gamesComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(step2Label)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(gamesPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(saveButton)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jScrollPane1.setViewportView(wrapperPanel);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void saveButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_saveButtonMouseClicked
        for (GamePanel panel : gamePanels) {
            try {
                gameService.updateGame(user.getName(), panel.getGame(), currentGameType);
            } catch (Exception ex) {
                Logger.getLogger(GamesTab.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        parent.displayMessage("Οι αλλαγές αποθηκεύτηκαν!");
    }//GEN-LAST:event_saveButtonMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox gamesComboBox;
    private javax.swing.JPanel gamesPanel;
    private javax.swing.JPanel gamesPanel1;
    private javax.swing.JPanel gamesPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton saveButton;
    private javax.swing.JLabel step1Label;
    private javax.swing.JLabel step2Label;
    private javax.swing.JPanel wrapperPanel;
    // End of variables declaration//GEN-END:variables
}
