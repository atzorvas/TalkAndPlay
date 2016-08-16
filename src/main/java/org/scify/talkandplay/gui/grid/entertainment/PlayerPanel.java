/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.scify.talkandplay.gui.grid.entertainment;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import org.scify.talkandplay.gui.grid.selectors.Selector;
import org.scify.talkandplay.gui.helpers.UIConstants;
import org.scify.talkandplay.services.SensorService;

/**
 *
 * @author christina
 */
public class PlayerPanel extends javax.swing.JPanel {

    private JPanel prevPanel, playPanel, nextPanel, fullScreenPanel, exitPanel;
    private SensorService sensorService;
    private Selector selector;
    private List<JPanel> controlsList;

    public PlayerPanel(SensorService sensorService, Selector selector) {
        this.sensorService = sensorService;
        this.controlsList = new ArrayList();
        this.selector = selector;

        initComponents();
        initCustomComponents();
        initPlayerButtons();
    }

    private void initCustomComponents() {
        setBackground(Color.white);
        startLabel.setFont(new Font(UIConstants.mainFont, Font.PLAIN, 18));
        endLabel.setFont(new Font(UIConstants.mainFont, Font.PLAIN, 18));
    }

    private void initPlayerButtons() {
        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.NONE;
        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 1;
        c.weighty = 1;

        prevPanel = drawButton("Προηγούμενο", getClass().getResource("/org/scify/talkandplay/resources/prev-button.png"));
        playPanel = drawButton("Αναπαραγωγή", getClass().getResource("/org/scify/talkandplay/resources/play-button.png"));
        nextPanel = drawButton("Επόμενο", getClass().getResource("/org/scify/talkandplay/resources/next-button.png"));
        fullScreenPanel = drawButton("Πλήρης Οθόνη", getClass().getResource("/org/scify/talkandplay/resources/fullscreen-icon.png"));
        exitPanel = drawButton("Έξοδος", getClass().getResource("/org/scify/talkandplay/resources/exit-icon.png"));

        c.gridwidth = 5;
        add(durationPanel, c);
        c.gridy++;
        c.gridwidth = 1;
        add(prevPanel, c);
        c.gridx++;
        add(playPanel, c);
        c.gridx++;
        add(nextPanel, c);
        c.gridx++;
        add(fullScreenPanel, c);
        c.gridx++;
        add(exitPanel, c);

        controlsList.add(prevPanel);
        controlsList.add(playPanel);
        controlsList.add(nextPanel);
        controlsList.add(fullScreenPanel);
        controlsList.add(exitPanel);

        selector.setDefaultBackgroundColor(UIConstants.grey);
        selector.setList(controlsList);
        selector.start();
    }

    private JPanel drawButton(String text, URL imageIcon) {
        JLabel label = new JLabel(text);
        label.setBorder(new EmptyBorder(5, 5, 5, 5));
        label.setFont(new Font(UIConstants.mainFont, Font.PLAIN, 18));
        label.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel icon = new JLabel(new ImageIcon(new ImageIcon(imageIcon).getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH)));
        icon.setBorder(new EmptyBorder(5, 5, 5, 5));
        icon.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
        panel.setBackground(Color.decode(UIConstants.grey));
        panel.setPreferredSize(new Dimension(180, 100));
        panel.setMaximumSize(new Dimension(180, 100));
        panel.setMinimumSize(new Dimension(180, 100));
        panel.setBorder((new LineBorder(Color.white, 5)));

        panel.add(label);
        panel.add(icon);
        return panel;
    }

    public void setPlayButton() {
        ((JLabel) playPanel.getComponent(0)).setText("Αναπαραγωγή");
        ((JLabel) playPanel.getComponent(1)).setIcon(new ImageIcon(new ImageIcon(getClass().getResource("/org/scify/talkandplay/resources/play-button.png")).getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH)));
    }

    public void setPauseButton() {
        ((JLabel) playPanel.getComponent(0)).setText("Παύση");
        ((JLabel) playPanel.getComponent(1)).setIcon(new ImageIcon(new ImageIcon(getClass().getResource("/org/scify/talkandplay/resources/pause-button.png")).getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH)));
    }

    public void setDurationSlider(int iPos) {
        durationSlider.setValue(iPos);
    }

    public void setStartLabelText(String text) {
        startLabel.setText(text);
    }

    public void setEndLabel(String text) {
        endLabel.setText(text);
    }

    public JPanel getPrevPanel() {
        return prevPanel;
    }

    public JPanel getNextPanel() {
        return nextPanel;
    }

    public JPanel getPlayPanel() {
        return playPanel;
    }

    public JPanel getFullscreenPanel() {
        return fullScreenPanel;
    }

    public JPanel getExitPanel() {
        return exitPanel;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        durationPanel = new javax.swing.JPanel();
        startLabel = new javax.swing.JLabel();
        durationSlider = new javax.swing.JSlider();
        endLabel = new javax.swing.JLabel();

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        durationPanel.setBackground(new java.awt.Color(255, 255, 255));

        startLabel.setText("00:00:00");

        durationSlider.setValue(0);

        endLabel.setText("00:00:00");

        javax.swing.GroupLayout durationPanelLayout = new javax.swing.GroupLayout(durationPanel);
        durationPanel.setLayout(durationPanelLayout);
        durationPanelLayout.setHorizontalGroup(
            durationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(durationPanelLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(startLabel)
                .addGap(0, 0, 0)
                .addComponent(durationSlider, javax.swing.GroupLayout.PREFERRED_SIZE, 276, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(endLabel)
                .addGap(0, 0, 0))
        );
        durationPanelLayout.setVerticalGroup(
            durationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(durationPanelLayout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addGroup(durationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(endLabel)
                    .addGroup(durationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(durationSlider, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(startLabel)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(durationPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(durationPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel durationPanel;
    private javax.swing.JSlider durationSlider;
    private javax.swing.JLabel endLabel;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel startLabel;
    // End of variables declaration//GEN-END:variables
}