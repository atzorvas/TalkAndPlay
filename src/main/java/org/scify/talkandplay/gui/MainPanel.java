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
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.scify.talkandplay.gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.scify.talkandplay.gui.grid.GridFrame;
import org.scify.talkandplay.gui.helpers.UIConstants;
import org.scify.talkandplay.gui.users.UserFormPanel;
import org.scify.talkandplay.gui.users.UserPanel;
import org.scify.talkandplay.models.User;
import org.scify.talkandplay.services.UserService;
import org.scify.talkandplay.utils.ConfigurationFile;
import uk.co.caprica.vlcj.discovery.NativeDiscovery;

/**
 *
 * @author christina
 */
public class MainPanel extends javax.swing.JPanel {

    private ConfigurationFile configurationFile;
    private List<UserPanel> userPanelList;
    private MainFrame parent;

    public MainPanel(MainFrame parent) {
        this.configurationFile = ConfigurationFile.getInstance();
        this.parent = parent;
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

        usersPanel = new javax.swing.JPanel();

        usersPanel.setBackground(new java.awt.Color(255, 255, 255));
        usersPanel.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(usersPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 674, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(usersPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void initCustomComponents() {

        List<User> users = configurationFile.getUsers();

        if (users.size() > 4) {
            usersPanel.setLayout(new GridLayout(0, 6));
        } else {
            usersPanel.setLayout(new GridLayout(0, users.size() + 2));
        }

        userPanelList = new ArrayList<>();
        for (final User user : users) {

            UserPanel userPanel = new UserPanel(parent, user);

            userPanel.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    if (evt.getClickCount() == 2) {

                        boolean found = new NativeDiscovery().discover();
                        if (found) {

                            GridFrame imagesFrame;
                            try {
                                imagesFrame = new GridFrame(user.getName());
                                imagesFrame.setLocationRelativeTo(null);
                                imagesFrame.setTitle("Talk&Play");
                                imagesFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
                                imagesFrame.setVisible(true);
                            } catch (IOException ex) {
                                Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        } else {
                            JOptionPane.showMessageDialog(parent,
                                    "Εγκατέστησε το VLC και δοκίμασε ξανά");
                        }
                    }
                }
            });
            userPanelList.add(userPanel);
        }
        repaintUsers();
    }

    public void removeUser(User selectedUser) {
        for (UserPanel p : userPanelList) {
            if (p.getUser().equals(selectedUser)) {
                usersPanel.remove(p);
                break;
            }
        }
        repaintUsers();
    }

    public void repaintUsers() {
        usersPanel.removeAll();
        if (!userPanelList.isEmpty()) {
            for (JPanel panel : userPanelList) {
                usersPanel.add(panel);
            }
        }
        addUserPanel();
        uploadUserPanel();
        usersPanel.repaint();
    }

    private void addUserPanel() {
        JPanel addUserPanel = new JPanel();
        addUserPanel.setLayout(new BoxLayout(addUserPanel, BoxLayout.Y_AXIS));
        addUserPanel.setBackground(Color.white);

        JLabel nameLabel = new JLabel("Πρόσθεσε νέο χρήστη");
        final JLabel imageLabel = new JLabel(new ImageIcon(getClass().getResource("/org/scify/talkandplay/resources/add-icon.png")));

        imageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        nameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        nameLabel.setBorder(new EmptyBorder(5, 0, 20, 0));
        nameLabel.setFont(new Font(UIConstants.mainFont, Font.PLAIN, 16));

        addUserPanel.add(imageLabel);
        addUserPanel.add(nameLabel);
        usersPanel.add(addUserPanel);

        imageLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent arg0) {
                imageLabel.setIcon(new ImageIcon(getClass().getResource("/org/scify/talkandplay/resources/add-icon.png")));
            }

            @Override
            public void mouseEntered(MouseEvent arg0) {
                imageLabel.setIcon(new ImageIcon(getClass().getResource("/org/scify/talkandplay/resources/add-icon-hover.png")));
            }

            @Override
            public void mouseClicked(MouseEvent arg0) {
                UserService us = new UserService();
                try {
                    boolean result = us.createUserAsCopyOfDefaultUser();
                    /**
                     * if user is successfully created from old user's 
                     * configuration just refresh the page, else open the form 
                     * that creates a new user
                     */
                    if(result){
                        /**
                         * redirect to the main page [it is the same page 
                         * but used to refresh content and display the newly 
                         * created user]
                         */
                        parent.changePanel(new MainPanel(parent));
                    } else {
                        // open form that creates new user
                        parent.changePanel(new UserFormPanel(parent));
                    }
                } catch(Exception ex) {
                    Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
                }                
            }
        });
    }

    private void uploadUserPanel() {
        JPanel uploadUserPanel = new JPanel();
        uploadUserPanel.setLayout(new BoxLayout(uploadUserPanel, BoxLayout.Y_AXIS));
        uploadUserPanel.setBackground(Color.white);

        JLabel nameLabel = new JLabel("Φόρτωσε χρήστη");
        final JLabel imageLabel = new JLabel(new ImageIcon(getClass().getResource("/org/scify/talkandplay/resources/upload-icon.png")));

        imageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        nameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        nameLabel.setBorder(new EmptyBorder(5, 0, 20, 0));
        nameLabel.setFont(new Font(UIConstants.mainFont, Font.PLAIN, 16));

        uploadUserPanel.add(imageLabel);
        uploadUserPanel.add(nameLabel);
        usersPanel.add(uploadUserPanel);

        imageLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent arg0) {
                imageLabel.setIcon(new ImageIcon(getClass().getResource("/org/scify/talkandplay/resources/upload-icon.png")));
            }

            @Override
            public void mouseEntered(MouseEvent arg0) {
                imageLabel.setIcon(new ImageIcon(getClass().getResource("/org/scify/talkandplay/resources/upload-icon-hover.png")));
            }

            @Override
            public void mouseClicked(MouseEvent arg0) {
                JFileChooser chooser = new JFileChooser();
                chooser.setDialogTitle("Επίλεξε αρχείο");
                chooser.setAcceptAllFileFilterUsed(false);
                chooser.setFileFilter(new FileNameExtensionFilter("XML files", "xml"));
                chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
                if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                    File file = chooser.getSelectedFile();
                    UserService us = new UserService();
                    boolean result = us.uploadUserFromFile(file);
                    //on success
                    if (result) {
                        /**
                         * redirect to the main page [it is the same page 
                         * but used to refresh content and display the newly 
                         * uploaded user]
                         */
                        parent.changePanel(new MainPanel(parent));   
                    }
                }
            }
        });
    }
    
    public void updateUsersPanel(User user, String oldName) {
        for (UserPanel p : userPanelList) {
            if (p.getUser().getName().equals(oldName)) {
                p.repaintPanel(user);
                break;
            }
        }
        repaintUsers();
    }

    public void removeFromUsersPanel(String removeUser) {
        for (UserPanel p : userPanelList) {
            if (p.getUser().getName().equals(removeUser)) {
                usersPanel.remove(p);
                break;
            }
        }
        repaintUsers();
    }

    public List<UserPanel> getUsersPanel() {
        return userPanelList;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel usersPanel;
    // End of variables declaration//GEN-END:variables
}
