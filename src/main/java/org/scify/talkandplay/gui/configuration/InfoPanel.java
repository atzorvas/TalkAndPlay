package org.scify.talkandplay.gui.configuration;

import java.awt.GridLayout;
import java.util.List;
import org.scify.talkandplay.gui.helpers.GuiHelper;
import org.scify.talkandplay.models.User;

public class InfoPanel extends javax.swing.JPanel {

    private GuiHelper guiHelper;
    private ConfigurationPanel parent;
    private User user;

    public InfoPanel(ConfigurationPanel parent, User user) {
        this.guiHelper = new GuiHelper();
        this.parent = parent;
        this.user = user;

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

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        addWordButton = new javax.swing.JButton();

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setText("Κάνε κλικ πάνω σε κάποια λέξη για να την επεξεργαστείς.");

        jLabel2.setText("Πάτησε “Πρόσθεσε νέα λέξη” για να προσθέσεις κάποια επιπλέον.");

        addWordButton.setBackground(new java.awt.Color(75, 161, 69));
        addWordButton.setFont(addWordButton.getFont());
        addWordButton.setForeground(new java.awt.Color(255, 255, 255));
        addWordButton.setText("Πρόσθεσε νέα λέξη");
        addWordButton.setBorder(null);
        addWordButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                addWordButtonMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(addWordButton))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2)
                .addGap(60, 60, 60)
                .addComponent(addWordButton)
                .addContainerGap(478, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void addWordButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addWordButtonMouseClicked
        parent.addPanel(new WordFormPanel(user, parent));
    }//GEN-LAST:event_addWordButtonMouseClicked

    private void initCustomComponents() {
        setLayout(new GridLayout(0, 1, 0, 0));
        guiHelper.drawButton(addWordButton);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addWordButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
