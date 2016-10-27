/**
* Copyright 2016 SciFY
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
*     http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/
package org.scify.talkandplay.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import org.scify.talkandplay.gui.configuration.ConfigurationPanel;
import org.scify.talkandplay.gui.helpers.UIConstants;
import org.scify.talkandplay.models.User;
import org.scify.talkandplay.utils.Properties;

public class MainFrame extends javax.swing.JFrame {

    private final Properties prop;
    
    public MainFrame() {
        prop = new Properties();
        initComponents();
        initCustomComponents();
        openLinkToBrowser();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPopupMenu1 = new javax.swing.JPopupMenu();
        contentPane = new javax.swing.JPanel();
        logoLabel = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        titlePanel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        contentPanel = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        scifyLogoLabel = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        niarchosLogoLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Talk&Play");

        contentPane.setBackground(new java.awt.Color(255, 255, 255));
        contentPane.setPreferredSize(new java.awt.Dimension(800, 720));

        logoLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/scify/talkandplay/resources/tp_logo_small.png"))); // NOI18N

        jLabel1.setBackground(new java.awt.Color(51, 51, 255));
        jLabel1.setFont(jLabel1.getFont());
        jLabel1.setForeground(new java.awt.Color(153, 153, 153));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("SciFY 2016 - version: " + prop.getVersion());
        jLabel1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        titlePanel.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout titlePanelLayout = new javax.swing.GroupLayout(titlePanel);
        titlePanel.setLayout(titlePanelLayout);
        titlePanelLayout.setHorizontalGroup(
            titlePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        titlePanelLayout.setVerticalGroup(
            titlePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jScrollPane1.setBorder(null);

        contentPanel.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane1.setViewportView(contentPanel);

        jLabel2.setFont(new java.awt.Font("Dialog", 0, 10)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(153, 153, 153));
        jLabel2.setText("Δημιουργήθηκε από τη");
        jLabel2.setAlignmentY(0.0F);
        jLabel2.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jLabel2.setDoubleBuffered(true);
        jLabel2.setMaximumSize(new java.awt.Dimension(234, 50));
        jLabel2.setPreferredSize(new java.awt.Dimension(234, 50));
        jLabel2.setRequestFocusEnabled(false);

        scifyLogoLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        scifyLogoLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/scify/talkandplay/resources/logos/scify_logo_108.png"))); // NOI18N
        scifyLogoLabel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jLabel4.setFont(new java.awt.Font("Dialog", 0, 10)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(153, 153, 153));
        jLabel4.setText("Αποκλειστική δωρεά");

        niarchosLogoLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/scify/talkandplay/resources/logos/snf_lg.png"))); // NOI18N
        niarchosLogoLabel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        javax.swing.GroupLayout contentPaneLayout = new javax.swing.GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(contentPaneLayout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(logoLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(titlePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(contentPaneLayout.createSequentialGroup()
                .addGroup(contentPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(contentPaneLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, contentPaneLayout.createSequentialGroup()
                        .addGroup(contentPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(contentPaneLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(contentPaneLayout.createSequentialGroup()
                                .addGap(47, 47, 47)
                                .addComponent(scifyLogoLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 594, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                        .addGroup(contentPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(contentPaneLayout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(niarchosLogoLabel))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, contentPaneLayout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addGap(8, 8, 8)))))
                .addContainerGap())
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(contentPaneLayout.createSequentialGroup()
                .addGroup(contentPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(contentPaneLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(logoLabel))
                    .addComponent(titlePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(41, 41, 41)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 352, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(contentPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(contentPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(scifyLogoLabel)
                    .addGroup(contentPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(niarchosLogoLabel)
                        .addComponent(jLabel1)))
                .addContainerGap())
        );

        jLabel2.getAccessibleContext().setAccessibleName("createdByLabel");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(contentPane, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 830, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(contentPane, javax.swing.GroupLayout.DEFAULT_SIZE, 540, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void initCustomComponents() {
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setIconImage((new ImageIcon(getClass().getResource("/org/scify/talkandplay/resources/tp_logo_mini.png"))).getImage());

        contentPanel.add(new MainPanel(this), BorderLayout.CENTER);

        contentPanel.revalidate();
        contentPanel.repaint();
        revalidate();
        repaint();
        pack();
    }

    public void changePanel(JPanel newPanel) {
        if (newPanel instanceof MainPanel) {
            titlePanel.removeAll();
            titlePanel.setBackground(Color.white);
        }
        contentPanel.removeAll();
        contentPanel.add(newPanel);
        revalidate();
        repaint();
    }

    public void setPanelTitle(String title) {
        titlePanel.removeAll();
        titlePanel.setBackground(Color.decode(UIConstants.green));
        //titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.LINE_AXIS));
        titlePanel.setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(new Font(UIConstants.mainFont, Font.PLAIN, 20));
        titleLabel.setForeground(Color.white);
        titleLabel.setHorizontalAlignment(JLabel.CENTER);

        titlePanel.add(titleLabel, BorderLayout.CENTER);
    }
    /*
     public void setPanelTitleWithBackNext(final User user, String title) {
     titlePanel.removeAll();
     titlePanel.setBackground(Color.decode(UIConstants.green));
     //titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.LINE_AXIS));
     titlePanel.setLayout(new BorderLayout());

     JLabel titleLabel = new JLabel(title);
     titleLabel.setFont(new Font(UIConstants.mainFont, Font.PLAIN, 20));
     titleLabel.setForeground(Color.white);
     titleLabel.setHorizontalAlignment(JLabel.CENTER);

     /*  JPanel backPanel = new JPanel();
     backPanel.setBackground(Color.decode(UIConstants.green));
     backPanel.setBorder(new LineBorder(Color.white, 1));*/
    /*   JLabel backLabel = new JLabel("ΠΙΣΩ");
     backLabel.setFont(new Font(UIConstants.mainFont, Font.PLAIN, 20));
     backLabel.setIcon(new ImageIcon(new ImageIcon(getClass().getResource("/org/scify/talkandplay/resources/left-icon.png")).getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT)));
     backLabel.setBorder(new LineBorder(Color.white, 1));
     /*  JLabel backIcon = new JLabel(new ImageIcon(new ImageIcon(getClass().getResource("/org/scify/talkandplay/resources/left-icon.png")).getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT)));
     backPanel.add(backIcon);
     backPanel.add(backLabel);
     */
    /*
     JLabel nextLabel = new JLabel("ΕΠΟΜΕΝΟ");
     nextLabel.setFont(new Font(UIConstants.mainFont, Font.PLAIN, 20));
     nextLabel.setIcon(new ImageIcon(new ImageIcon(getClass().getResource("/org/scify/talkandplay/resources/right-icon.png")).getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT)));
     nextLabel.setBorder(new LineBorder(Color.white, 1));

     titlePanel.add(backLabel, BorderLayout.LINE_START);
     titlePanel.add(titleLabel, BorderLayout.CENTER);
     titlePanel.add(nextLabel, BorderLayout.LINE_END);
     // titlePanel.add(Box.createHorizontalGlue());

     final MainFrame mainFrame = this;
     nextLabel.addMouseListener(new MouseAdapter() {
     public void mouseClicked(MouseEvent me) {
     changePanel(new ConfigurationPanel(user.getName(), mainFrame));

     }
     });
     }*/
    
    private void openLinkToBrowser() {
        scifyLogoLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent arg0) {
                if (Desktop.isDesktopSupported()) {
                    try {
                        Desktop.getDesktop().browse(new URI("http://scify.org"));
                    } catch (Exception ex) {
                        Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });
        
        niarchosLogoLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent arg0) {
                if (Desktop.isDesktopSupported()) {
                    try {
                        Desktop.getDesktop().browse(new URI("http://www.snf.org/"));
                    } catch (Exception ex) {
                        Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel contentPane;
    private javax.swing.JPanel contentPanel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel logoLabel;
    private javax.swing.JLabel niarchosLogoLabel;
    private javax.swing.JLabel scifyLogoLabel;
    private javax.swing.JPanel titlePanel;
    // End of variables declaration//GEN-END:variables
}
