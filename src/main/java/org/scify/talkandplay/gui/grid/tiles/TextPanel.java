/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.scify.talkandplay.gui.grid.tiles;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JLabel;
import org.scify.talkandplay.gui.helpers.UIConstants;

/**
 *
 * @author christina
 */
public class TextPanel extends javax.swing.JPanel {

    private String text;

    public TextPanel(String text) {
        this.text = text;
        initComponents();
        initCustomComponents();
    }

    private void initCustomComponents() {
        setBackground(Color.white);

    }

    @Override
    protected void paintComponent(Graphics g) {
        JLabel textLabel = new JLabel(text);
        textLabel.setForeground(Color.black);
        textLabel.setFont(new Font(UIConstants.mainFont, Font.PLAIN, 35));
        textLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(textLabel);

        Font labelFont = new Font(UIConstants.mainFont, Font.PLAIN, 35);
        String labelText = textLabel.getText();

        int stringWidth = textLabel.getFontMetrics(labelFont).stringWidth(labelText);
        int componentWidth = textLabel.getWidth();

// Find out how much the font can grow in width.
        double widthRatio = (double) componentWidth / (double) stringWidth;

        int newFontSize = (int) (labelFont.getSize() * widthRatio);
        int componentHeight = textLabel.getHeight();

// Pick a new font size so it will not be larger than the height of label.
        int fontSizeToUse = Math.min(newFontSize, componentHeight);

// Set the label's font size to the newly determined size.
        textLabel.setFont(new Font(labelFont.getName(), Font.PLAIN, fontSizeToUse));
        add(textLabel);
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
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}