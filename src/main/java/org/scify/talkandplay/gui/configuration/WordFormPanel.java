package org.scify.talkandplay.gui.configuration;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.scify.talkandplay.gui.helpers.GuiHelper;
import org.scify.talkandplay.gui.helpers.UIConstants;
import org.scify.talkandplay.models.Category;
import org.scify.talkandplay.models.User;
import org.scify.talkandplay.services.CategoryService;

public class WordFormPanel extends javax.swing.JPanel {

    private GuiHelper guiHelper;
    private User user;
    private Category category;
    private List<String> categories;
    private String imagePath;
    private String soundPath;
    private CategoryService categoryService;
    private ConfigurationPanel parent;

    public WordFormPanel(User user, Category category, ConfigurationPanel parent) {
        this.guiHelper = new GuiHelper();
        this.user = user;
        this.imagePath = null;
        this.soundPath = null;
        this.categoryService = new CategoryService();
        this.category = category;
        this.parent = parent;

        initComponents();
        initCustomComponents();
    }

    private void initCustomComponents() {
        categories = categoryService.getLinearCategories(user);
        setBorder(BorderFactory.createCompoundBorder(new EmptyBorder(10, 10, 10, 10), new LineBorder(Color.decode(UIConstants.getMainColor()), 1)));
        setUI();
        setListeners();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        backButton = new javax.swing.JButton();
        titleLabel = new javax.swing.JLabel();
        step1Label = new javax.swing.JLabel();
        wordTextField = new javax.swing.JTextField();
        step2Label = new javax.swing.JLabel();
        step3Label = new javax.swing.JLabel();
        step3ExplLabel = new javax.swing.JLabel();
        uploadSoundLabel = new javax.swing.JLabel();
        step4Label = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        step4ExplTextArea = new javax.swing.JTextArea();
        categoriesComboBox = new javax.swing.JComboBox();
        uploadImageLabel = new javax.swing.JLabel();
        saveButton = new javax.swing.JButton();
        errorLabel = new javax.swing.JLabel();
        editStepsPanel = new javax.swing.JPanel();
        step5Label = new javax.swing.JLabel();
        rowsTextField = new javax.swing.JTextField();
        xLabel = new javax.swing.JLabel();
        columnsTextField = new javax.swing.JTextField();
        step6Label = new javax.swing.JLabel();
        imageCheckBox = new javax.swing.JCheckBox();
        textCheckBox = new javax.swing.JCheckBox();
        soundCheckBox = new javax.swing.JCheckBox();

        backButton.setBackground(new java.awt.Color(75, 161, 69));
        backButton.setFont(backButton.getFont());
        backButton.setForeground(new java.awt.Color(255, 255, 255));
        backButton.setText("Πίσω");
        backButton.setBorder(null);

        setBackground(new java.awt.Color(255, 255, 255));

        titleLabel.setText("ΠΡΟΣΘΗΚΗ ΝΕΑΣ ΛΕΞΗΣ");

        step1Label.setText("1. Γράψε λέξη");

        wordTextField.setBackground(new java.awt.Color(255, 255, 255));
        wordTextField.setForeground(new java.awt.Color(51, 51, 51));
        wordTextField.setText("Λέξη");

        step2Label.setText("2. Ανέβασε φωτογραφία");

        step3Label.setText("3. Ανέβασε ηχητικό");

        step3ExplLabel.setText("Εάν δεν ανεβάσεις ηχητικό, θα παίζει προκαθορισμένος ήχος.");

        uploadSoundLabel.setText("uploadSound");
        uploadSoundLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                uploadSoundLabelMouseClicked(evt);
            }
        });

        step4Label.setText("4. Επίλεξε πού ανήκει η λέξη");

        step4ExplTextArea.setBackground(new java.awt.Color(255, 255, 255));
        step4ExplTextArea.setColumns(20);
        step4ExplTextArea.setRows(5);
        step4ExplTextArea.setText("Επίλεξε την λέξη στην οποία υπάγεται η λέξη που προσθέτεις εδώ, ή επίλεξε “ΕΠΙΚΟΙΝΩΝΙΑ” εάν πρόκειται για εντελώς νέα κατηγορία λέξεων.\n");
        step4ExplTextArea.setBorder(null);
        jScrollPane1.setViewportView(step4ExplTextArea);

        categoriesComboBox.setBackground(new java.awt.Color(255, 255, 255));
        categoriesComboBox.setBorder(null);

        uploadImageLabel.setText("uploadImg");

        saveButton.setBackground(new java.awt.Color(75, 161, 69));
        saveButton.setFont(saveButton.getFont());
        saveButton.setForeground(new java.awt.Color(255, 255, 255));
        saveButton.setText("Αποθήκευση λέξης");
        saveButton.setBorder(null);
        saveButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                saveButtonMouseClicked(evt);
            }
        });

        errorLabel.setBackground(new java.awt.Color(255, 255, 255));
        errorLabel.setForeground(new java.awt.Color(153, 0, 0));
        errorLabel.setText("error");

        editStepsPanel.setBackground(new java.awt.Color(255, 255, 255));

        step5Label.setText("5. Καθόρισε μέγεθος πίνακα");

        rowsTextField.setText("3");

        xLabel.setText("x");

        columnsTextField.setText("3");

        step6Label.setText("6. Εμφάνιση πίνακα");

        imageCheckBox.setBackground(new java.awt.Color(255, 255, 255));
        imageCheckBox.setText("Εικόνα");

        textCheckBox.setBackground(new java.awt.Color(255, 255, 255));
        textCheckBox.setText("Λεκτικό");

        soundCheckBox.setBackground(new java.awt.Color(255, 255, 255));
        soundCheckBox.setText("Ήχος");

        javax.swing.GroupLayout editStepsPanelLayout = new javax.swing.GroupLayout(editStepsPanel);
        editStepsPanel.setLayout(editStepsPanelLayout);
        editStepsPanelLayout.setHorizontalGroup(
            editStepsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(editStepsPanelLayout.createSequentialGroup()
                .addGroup(editStepsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(editStepsPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(step5Label))
                    .addGroup(editStepsPanelLayout.createSequentialGroup()
                        .addGap(155, 155, 155)
                        .addComponent(rowsTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(xLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(columnsTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(editStepsPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(step6Label))
                    .addGroup(editStepsPanelLayout.createSequentialGroup()
                        .addGap(46, 46, 46)
                        .addComponent(soundCheckBox)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(textCheckBox)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(imageCheckBox)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        editStepsPanelLayout.setVerticalGroup(
            editStepsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(editStepsPanelLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(step5Label)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(editStepsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(xLabel)
                    .addComponent(columnsTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rowsTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(step6Label)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(editStepsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(soundCheckBox)
                    .addComponent(textCheckBox)
                    .addComponent(imageCheckBox))
                .addContainerGap(19, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(titleLabel)
                .addGap(157, 157, 157))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(step1Label))
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(step2Label))
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(step3Label)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(20, 20, 20)
                                        .addComponent(step3ExplLabel))))
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(step4Label)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(14, 14, 14)
                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 434, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(22, 22, 22)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(uploadImageLabel)
                                    .addComponent(wordTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(errorLabel)
                            .addComponent(categoriesComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 434, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(166, 166, 166)
                        .addComponent(saveButton))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(169, 169, 169)
                        .addComponent(uploadSoundLabel)))
                .addContainerGap(26, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(editStepsPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(titleLabel)
                .addGap(18, 18, 18)
                .addComponent(step1Label)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(wordTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addComponent(step2Label)
                .addGap(18, 18, 18)
                .addComponent(uploadImageLabel)
                .addGap(20, 20, 20)
                .addComponent(step3Label)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(step3ExplLabel)
                .addGap(18, 18, 18)
                .addComponent(uploadSoundLabel)
                .addGap(18, 18, 18)
                .addComponent(step4Label)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(categoriesComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(editStepsPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(errorLabel)
                .addGap(2, 2, 2)
                .addComponent(saveButton)
                .addGap(36, 36, 36))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void saveButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_saveButtonMouseClicked

        if (validateCategory()) {

            Category category = new Category();
            category.setName(wordTextField.getText());
            category.setRows(user.getConfiguration().getDefaultGridRow());
            category.setColumns(user.getConfiguration().getDefaultGridColumn());
            category.setImage(imagePath);
            category.setSound(soundPath);
            category.setParentCategory(new Category(categoriesComboBox.getSelectedItem().toString()));

            try {
                categoryService.save(category, user);
                parent.redrawCategoriesList();
            } catch (Exception ex) {
                Logger.getLogger(WordFormPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_saveButtonMouseClicked

    private void uploadSoundLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_uploadSoundLabelMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_uploadSoundLabelMouseClicked

    private boolean validateCategory() {

        String name = wordTextField.getText();

        //word should not be null
        if (name == null || name.isEmpty()) {
            errorLabel.setText("Η λέξη δεν πρέπει να είναι κενή");
            errorLabel.setVisible(true);
            return false;
        }

        //word should be unique
        for (String category : categories) {
            if (category.equals(name)) {
                errorLabel.setText("Η λέξη πρέπει να είναι μοναδική");
                errorLabel.setVisible(true);
                return false;
            }
        }

        //image should be uploaded
        if (imagePath == null) {
            //TODO this is not correct, image might not be visible due to settings
            errorLabel.setText("Η λέξη πρέπει να έχει εικόνα");
            errorLabel.setVisible(true);
            return false;
        }

        if (categoriesComboBox.getSelectedIndex() == 0) {
            errorLabel.setText("Η λέξη πρέπει να ανήκει σε κάποια λέξη");
            errorLabel.setVisible(true);
            return false;
        }

        return true;
    }

    private void setUI() {
        errorLabel.setVisible(false);

        titleLabel.setFont(new Font(UIConstants.getMainFont(), Font.BOLD, 16));
        titleLabel.setForeground(Color.decode(UIConstants.getMainColor()));
        titleLabel.setHorizontalAlignment(JLabel.CENTER);

        guiHelper.drawButton(saveButton);
        guiHelper.setStepLabelFont(step1Label);
        guiHelper.setStepLabelFont(step2Label);
        guiHelper.setStepLabelFont(step3Label);
        guiHelper.setStepLabelFont(step4Label);
        guiHelper.setStepLabelFont(step5Label);
        guiHelper.setStepLabelFont(step6Label);
        guiHelper.setStepExplLabelFont(step3ExplLabel);

        wordTextField.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.GRAY));
        wordTextField.setFont(new Font(UIConstants.getMainFont(), Font.ITALIC, 14));
        wordTextField.setHorizontalAlignment(JTextField.CENTER);

        uploadImageLabel.setIcon(new ImageIcon(new ImageIcon(getClass().getResource("/org/scify/talkandplay/resources/add-icon.png")).getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH)));
        uploadSoundLabel.setIcon(new ImageIcon(new ImageIcon(getClass().getResource("/org/scify/talkandplay/resources/add-icon.png")).getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH)));
        uploadImageLabel.setText("");
        uploadSoundLabel.setText("");
        uploadImageLabel.setHorizontalAlignment(JLabel.CENTER);
        uploadSoundLabel.setHorizontalAlignment(JLabel.CENTER);

        //set up the textarea to look like a text field
        step4ExplTextArea.setEditable(false);
        step4ExplTextArea.setLineWrap(true);
        step4ExplTextArea.setWrapStyleWord(true);
        step4ExplTextArea.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        jScrollPane1.setBorder(null);

        categoriesComboBox.addItem("[-- Επίλεξε λέξη --]");
        for (String category : categories) {
            categoriesComboBox.addItem(category);
        }

        categoriesComboBox.setBorder(new LineBorder(Color.decode(UIConstants.getMainColor()), 1));
        categoriesComboBox.setFont(new Font(UIConstants.getMainColor(), Font.PLAIN, 14));

        if (category == null) {
            editStepsPanel.setVisible(false);
        } else {
            editStepsPanel.setVisible(true);
            System.out.println(category.getName());
            wordTextField.setText(category.getName());
            uploadImageLabel.setIcon(new ImageIcon(new ImageIcon(category.getImage()).getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH)));
            // uploadSoundLabel.setIcon(new ImageIcon(new ImageIcon(getClass().getResource("/org/scify/talkandplay/resources/add-icon.png")).getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH)));
//            categoriesComboBox.setSelectedItem(category.getParentCategory().getName());
            rowsTextField.setText(String.valueOf(category.getRows()));
            columnsTextField.setText(String.valueOf(category.getColumns()));
            // soundCheckBox.setSelected(category.);
        }
    }

    private void setListeners() {
        uploadImageLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent me) {
                if (imagePath == null) {
                    uploadImageLabel.setIcon(new ImageIcon(new ImageIcon(getClass().getResource("/org/scify/talkandplay/resources/add-icon.png")).getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH)));
                }
            }

            @Override
            public void mouseEntered(MouseEvent me) {
                if (imagePath == null) {
                    uploadImageLabel.setIcon(new ImageIcon(new ImageIcon(getClass().getResource("/org/scify/talkandplay/resources/add-icon-hover.png")).getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH)));
                }
            }

            @Override
            public void mouseClicked(MouseEvent me) {
                JFileChooser chooser = new JFileChooser();

                chooser.setDialogTitle("Διαλέξτε εικόνα");
                chooser.setAcceptAllFileFilterUsed(false);
                chooser.setFileFilter(new FileNameExtensionFilter("Image Files", "png", "jpg", "jpeg", "JPG", "JPEG", "gif"));
                chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
                if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                    imagePath = chooser.getSelectedFile().getAbsolutePath();
                    uploadImageLabel.setIcon(new ImageIcon(new ImageIcon(imagePath).getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH)));

                }
            }
        });

        uploadSoundLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent me) {
                uploadSoundLabel.setIcon(new ImageIcon(new ImageIcon(getClass().getResource("/org/scify/talkandplay/resources/add-icon.png")).getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH)));
            }

            @Override
            public void mouseEntered(MouseEvent me) {
                uploadSoundLabel.setIcon(new ImageIcon(new ImageIcon(getClass().getResource("/org/scify/talkandplay/resources/add-icon-hover.png")).getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH)));
            }

            @Override
            public void mouseClicked(MouseEvent me) {
                JFileChooser chooser = new JFileChooser();

                chooser.setDialogTitle("Διαλέξτε εικόνα");
                chooser.setAcceptAllFileFilterUsed(false);
                chooser.setFileFilter(new FileNameExtensionFilter("Sound Files", "mp3", "wav", "wma", "mid"));
                chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
                if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                    soundPath = chooser.getSelectedFile().getAbsolutePath();
                    uploadImageLabel.setIcon(new ImageIcon(new ImageIcon(getClass().getResource("/org/scify/talkandplay/resources/sound-icon.png")).getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH)));
                }
            }

        });

        wordTextField.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent fe) {
                if ("Λέξη".equals(wordTextField.getText())) {
                    wordTextField.setText("");
                }
            }

            public void focusLost(FocusEvent fe) {
                if (wordTextField.getText().isEmpty()) {
                    wordTextField.setText("Λέξη");
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton backButton;
    private javax.swing.JComboBox categoriesComboBox;
    private javax.swing.JTextField columnsTextField;
    private javax.swing.JPanel editStepsPanel;
    private javax.swing.JLabel errorLabel;
    private javax.swing.JCheckBox imageCheckBox;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField rowsTextField;
    private javax.swing.JButton saveButton;
    private javax.swing.JCheckBox soundCheckBox;
    private javax.swing.JLabel step1Label;
    private javax.swing.JLabel step2Label;
    private javax.swing.JLabel step3ExplLabel;
    private javax.swing.JLabel step3Label;
    private javax.swing.JTextArea step4ExplTextArea;
    private javax.swing.JLabel step4Label;
    private javax.swing.JLabel step5Label;
    private javax.swing.JLabel step6Label;
    private javax.swing.JCheckBox textCheckBox;
    private javax.swing.JLabel titleLabel;
    private javax.swing.JLabel uploadImageLabel;
    private javax.swing.JLabel uploadSoundLabel;
    private javax.swing.JTextField wordTextField;
    private javax.swing.JLabel xLabel;
    // End of variables declaration//GEN-END:variables
}
