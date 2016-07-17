/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.scify.talkandplay.gui.grid.entertainment;

import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.MouseAdapter;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import org.scify.talkandplay.gui.grid.BaseMediaPanel;
import org.scify.talkandplay.gui.grid.timers.TimerManager;
import org.scify.talkandplay.gui.helpers.UIConstants;
import org.scify.talkandplay.models.User;
import org.scify.talkandplay.models.sensors.KeyboardSensor;
import org.scify.talkandplay.models.sensors.MouseSensor;
import org.scify.talkandplay.models.sensors.Sensor;
import org.scify.talkandplay.services.SensorService;

public class FilesPanel extends javax.swing.JPanel {

    private User user;
    private List<File> files;
    private int start, end, step, empties;
    private List<JPanel> panelList;
    private List<String> currentFiles;
    private BaseMediaPanel parent;
    private JPanel prevSongs, nextSongs, controls, back;
    private String currentFile;
    private TimerManager timer;
    private SensorService sensorService;

    public FilesPanel(User user, List<File> files, BaseMediaPanel parent) {
        this.user = user;
        this.files = new ArrayList();
        this.parent = parent;
        this.timer = parent.getTimer();
        this.sensorService = new SensorService(user);
        this.files = files;

        initComponents();
        initCustomComponents();
    }

    private void initCustomComponents() {
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        setBackground(Color.white);

        timer.setDefaultBackgroundColor(UIConstants.grey);

        step = user.getEntertainmentModule().getMusicModule().getPlaylistSize() - 3;
        start = 0;
        if (files.size() < step) {
            end = files.size();
        } else {
            end = step;
        }
        drawFiles();

    }

    private void drawFiles() {
        removeAll();
        panelList = new ArrayList();
        currentFiles = new ArrayList();

        empties = 0;

        if (parent instanceof MusicPanel) {
            prevSongs = drawFile("Προηγούμενα τραγούδια", new ImageIcon(new ImageIcon(getClass().getResource("/org/scify/talkandplay/resources/left-icon.png")).getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH)));
            nextSongs = drawFile("Επόμενα τραγούδια", new ImageIcon(new ImageIcon(getClass().getResource("/org/scify/talkandplay/resources/right-icon.png")).getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH)));
            controls = drawFile("Χειριστήριο", new ImageIcon(new ImageIcon(getClass().getResource("/org/scify/talkandplay/resources/down-icon.png")).getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH)));
        } else {
            prevSongs = drawFile("Προηγούμενα video", new ImageIcon(new ImageIcon(getClass().getResource("/org/scify/talkandplay/resources/left-icon.png")).getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH)));
            nextSongs = drawFile("Επόμενα video", new ImageIcon(new ImageIcon(getClass().getResource("/org/scify/talkandplay/resources/right-icon.png")).getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH)));
            back = drawFile("Πίσω", new ImageIcon(new ImageIcon(getClass().getResource("/org/scify/talkandplay/resources/back-icon.png")).getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH)));
        }

        add(prevSongs);
        panelList.add(prevSongs);

        for (int i = start; i < end; i++) {
            if (i >= files.size()) {
                empties++;
            } else {
                JPanel panel = drawFile(files.get(i).getName(), null);
                add(panel);
                panelList.add(panel);
                currentFiles.add(files.get(i).getName());
                addFileListener(panel, files.get(i));
            }
        }

        add(nextSongs);
        if (parent instanceof MusicPanel) {
            add(controls);
        } else if (parent instanceof VideoPanel) {
            add(back);
        }

        for (int i = 0; i < empties; i++) {
            add(drawEmpty());
        }

        panelList.add(nextSongs);

        if (parent instanceof MusicPanel) {
            panelList.add(controls);
        } else if (parent instanceof VideoPanel) {
            panelList.add(back);
        }

        timer.setList(panelList);
        timer.start();

        addListeners();
        revalidate();
        repaint();
    }

    private JPanel drawFile(String text, ImageIcon icon) {
        JLabel fileLabel = new JLabel(text);
        if (icon != null) {
            fileLabel.setIcon(icon);
        }

        fileLabel.setFont(new Font(UIConstants.mainFont, Font.PLAIN, 18));
        fileLabel.setHorizontalAlignment(JLabel.LEFT);
        fileLabel.setBorder(new EmptyBorder(3, 3, 3, 3));

        JPanel songPanel = new JPanel();
        songPanel.setLayout(new FlowLayout());
        songPanel.setBackground(Color.decode(UIConstants.grey));
        songPanel.setBorder((new LineBorder(Color.white, 5)));
        songPanel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);

        songPanel.add(fileLabel);

        return songPanel;
    }

    private JPanel drawEmpty() {

        JLabel emptyLabel = new JLabel(" ");
        emptyLabel.setFont(new Font(UIConstants.mainFont, Font.PLAIN, 18));
        emptyLabel.setBorder(new EmptyBorder(3, 3, 3, 3));

        JPanel empty = new JPanel();
        empty.setLayout(new FlowLayout());
        empty.setBackground(Color.white);
        empty.setBorder((new LineBorder(Color.white, 5)));

        empty.add(emptyLabel);

        return empty;
    }

    private void addListeners() {

        prevSongs.addMouseListener(new MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Sensor sensor = new MouseSensor(evt.getButton(), evt.getClickCount(), "mouse");
                if (sensorService.shouldSelect(sensor)) {
                    if (files.size() > step && start > 0) {
                        timer.cancel();
                        configurePrevSongs();
                        drawFiles();
                    }

                }
            }
        });
        prevSongs.addKeyListener(new KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Sensor sensor = new KeyboardSensor(evt.getKeyCode(), String.valueOf(evt.getKeyChar()), "keyboard");
                if (sensorService.shouldSelect(sensor)) {
                    if (files.size() > step && start > 0) {
                        timer.cancel();
                        configurePrevSongs();
                        drawFiles();
                    }
                }
            }
        });

        nextSongs.addMouseListener(new MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Sensor sensor = new MouseSensor(evt.getButton(), evt.getClickCount(), "mouse");
                if (sensorService.shouldSelect(sensor)) {
                    if (files.size() > step && end < files.size()) {
                        timer.cancel();
                        configureNextSongs();
                        drawFiles();
                    }
                }
            }
        });
        nextSongs.addKeyListener(new KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Sensor sensor = new KeyboardSensor(evt.getKeyCode(), String.valueOf(evt.getKeyChar()), "keyboard");
                if (sensorService.shouldSelect(sensor)) {
                    if (files.size() > step && end < files.size()) {
                        timer.cancel();
                        configureNextSongs();
                        drawFiles();
                    }
                }
            }
        });

        if (parent instanceof MusicPanel) {
            controls.addMouseListener(new MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    Sensor sensor = new MouseSensor(evt.getButton(), evt.getClickCount(), "mouse");
                    if (sensorService.shouldSelect(sensor)) {
                        timer.cancel();
                        timer.unselect();
                        timer.setList(parent.getControlsList());
                        timer.start();
                    }
                }
            });
            controls.addKeyListener(new KeyAdapter() {
                public void keyPressed(java.awt.event.KeyEvent evt) {
                    Sensor sensor = new KeyboardSensor(evt.getKeyCode(), String.valueOf(evt.getKeyChar()), "keyboard");
                    if (sensorService.shouldSelect(sensor)) {
                        timer.cancel();
                        timer.unselect();
                        timer.setList(parent.getControlsList());
                        timer.start();
                    }
                }
            });
        } else if (parent instanceof VideoPanel) {
            back.addMouseListener(new MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    Sensor sensor = new MouseSensor(evt.getButton(), evt.getClickCount(), "mouse");
                    if (sensorService.shouldSelect(sensor)) {
                        timer.cancel();
                        parent.goBack();
                    }
                }
            });
            back.addKeyListener(new KeyAdapter() {
                public void keyPressed(java.awt.event.KeyEvent evt) {
                    Sensor sensor = new KeyboardSensor(evt.getKeyCode(), String.valueOf(evt.getKeyChar()), "keyboard");
                    if (sensorService.shouldSelect(sensor)) {
                        timer.cancel();
                        parent.goBack();
                    }
                }
            });
        }
    }

    private void addFileListener(JPanel panel, final File file) {
        panel.addMouseListener(new MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Sensor sensor = new MouseSensor(evt.getButton(), evt.getClickCount(), "mouse");
                if (sensorService.shouldSelect(sensor)) {
                    currentFile = file.getName();
                    //  setSelected();
                    parent.playFile(currentFile);
                }
            }
        });
        panel.addKeyListener(new KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Sensor sensor = new KeyboardSensor(evt.getKeyCode(), String.valueOf(evt.getKeyChar()), "keyboard");
                if (sensorService.shouldSelect(sensor)) {
                    currentFile = file.getName();
                    //  setSelected();
                    parent.playFile(currentFile);
                }
            }
        });
    }

    private void configureNextSongs() {
        if (end <= files.size()) {
            start = end;
            end += step;
        }
    }

    private void configurePrevSongs() {
        if (start >= step) {
            end = start;
            start -= step;
        }
    }

    public List<JPanel> getPanelList() {
        return panelList;
    }

    public List<String> getFileList() {
        return currentFiles;
    }

    public int getSelected() {
        for (int i = 0; i < currentFiles.size(); i++) {
            if (currentFile.equals(currentFiles.get(i))) {
                return i;
            }
        }
        return -1;
    }

    public void setSelected(int selected) {
        currentFile = currentFiles.get(selected);//((JLabel) panelList.get(selected).getComponent(0)).getText();

        for (int i = 0; i < panelList.size(); i++) {

            if (currentFile.equals(((JLabel) panelList.get(i).getComponent(0)).getText())) {
                panelList.get(i).setBorder(BorderFactory.createLineBorder(Color.decode(UIConstants.blue), 5));
                panelList.get(i).setBackground(Color.decode(UIConstants.lightBlue));
            } else {
                panelList.get(i).setBorder(BorderFactory.createLineBorder(Color.white, 5));
                panelList.get(i).setBackground(Color.decode(UIConstants.grey));
            }
        }
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