package org.scify.talkandplay.gui.grid;

import org.scify.talkandplay.gui.grid.entertainment.MusicPanel;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import org.scify.talkandplay.gui.helpers.Time;
import uk.co.caprica.vlcj.component.AudioMediaPlayerComponent;
import uk.co.caprica.vlcj.player.MediaPlayer;
import uk.co.caprica.vlcj.player.MediaPlayerEventAdapter;

/**
 *
 * @author christina
 */
public class MediaPlayerPanel extends javax.swing.JPanel {

    private AudioMediaPlayerComponent audioPlayer;
    private JPanel parent;
    private GridFrame gridFrame;

    /**
     * Creates new form MediaPlayerPanel
     */
    public MediaPlayerPanel(JPanel parent, GridFrame gridFrame) {
        this.parent = parent;
        this.gridFrame = gridFrame;
        this.audioPlayer = new AudioMediaPlayerComponent();
        initComponents();
        initAudioPlayer();
        initCustomComponents();
    }

    private void initAudioPlayer() {

        audioPlayer.getMediaPlayer().mute(false);

        audioPlayer.getMediaPlayer().addMediaPlayerEventListener(new MediaPlayerEventAdapter() {

            @Override
            public void opening(MediaPlayer mediaPlayer) {
                System.out.println("dsdas");
            }

            @Override
            public void playing(MediaPlayer mediaPlayer) {
                audioPlayer.getMediaPlayer().mute(false);
                // audioPlayer.getMediaPlayer().setVolume(100);
            }

            @Override
            public void finished(MediaPlayer mediaPlayer) {
                if (parent instanceof MusicPanel) {
                    String nextFile = ((MusicPanel) parent).getNextFile();
                    ((MusicPanel) parent).setSelected();
                    playMedia(((MusicPanel) parent).getFilePath(nextFile));
                }
            }

            @Override
            public void positionChanged(MediaPlayer mp, float f) {
                int iPos = (int) (f * 100.0);
                mediaSlider.setValue(iPos);
            }

            @Override
            public void timeChanged(MediaPlayer mediaPlayer, final long newTime) {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        startLabel.setText(String.format("%s", Time.formatTime(newTime)));
                    }
                });
            }
        });

        gridFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                audioPlayer.getMediaPlayer().stop();
                audioPlayer.getMediaPlayer().stop();
                e.getWindow().dispose();
            }
        });
    }

    private void initCustomComponents() {

    }

    public void playMedia(String path) {
        System.out.println("Now playing " + path);
        mediaSlider.setValue(0);
        audioPlayer.getMediaPlayer().prepareMedia(path);
        audioPlayer.getMediaPlayer().parseMedia();

        int secs = (int) (audioPlayer.getMediaPlayer().getMediaMeta().getLength() / 1000) % 60;
        int mins = (int) ((audioPlayer.getMediaPlayer().getMediaMeta().getLength() / (1000 * 60)) % 60);
        int hrs = (int) ((audioPlayer.getMediaPlayer().getMediaMeta().getLength() / (1000 * 60 * 60)) % 24);

        endLabel.setText(Time.getTime(hrs, mins, secs));
        audioPlayer.getMediaPlayer().playMedia(path);
        playButton.setText("Pause");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mediaPlayerPanel = new javax.swing.JPanel();
        mediaSlider = new javax.swing.JSlider();
        startLabel = new javax.swing.JLabel();
        endLabel = new javax.swing.JLabel();
        nextButton = new javax.swing.JButton();
        playButton = new javax.swing.JButton();
        prevButton = new javax.swing.JButton();

        mediaSlider.setToolTipText("");
        mediaSlider.setValue(0);

        startLabel.setText("00:00:00");

        endLabel.setText("00:00:00");

        nextButton.setText(">>");
        nextButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nextButtonActionPerformed(evt);
            }
        });

        playButton.setText("Play");
        playButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                playButtonActionPerformed(evt);
            }
        });

        prevButton.setText("<<");
        prevButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                prevButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout mediaPlayerPanelLayout = new javax.swing.GroupLayout(mediaPlayerPanel);
        mediaPlayerPanel.setLayout(mediaPlayerPanelLayout);
        mediaPlayerPanelLayout.setHorizontalGroup(
            mediaPlayerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mediaPlayerPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(mediaPlayerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mediaPlayerPanelLayout.createSequentialGroup()
                        .addComponent(prevButton)
                        .addGap(128, 128, 128)
                        .addComponent(playButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(nextButton))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mediaPlayerPanelLayout.createSequentialGroup()
                        .addComponent(startLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(mediaSlider, javax.swing.GroupLayout.PREFERRED_SIZE, 308, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(endLabel)))
                .addContainerGap())
        );
        mediaPlayerPanelLayout.setVerticalGroup(
            mediaPlayerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mediaPlayerPanelLayout.createSequentialGroup()
                .addContainerGap(25, Short.MAX_VALUE)
                .addGroup(mediaPlayerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(mediaSlider, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(startLabel)
                    .addComponent(endLabel))
                .addGap(12, 12, 12)
                .addGroup(mediaPlayerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(prevButton)
                    .addComponent(nextButton)
                    .addComponent(playButton))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mediaPlayerPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mediaPlayerPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void playButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_playButtonActionPerformed
        playButton.setText("Play");
        audioPlayer.getMediaPlayer().pause();
    }//GEN-LAST:event_playButtonActionPerformed

    private void prevButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_prevButtonActionPerformed
        if (parent instanceof MusicPanel) {
            String prevFile = ((MusicPanel) parent).getPreviousFile();
            playMedia(((MusicPanel) parent).getFilePath(prevFile));
        }
    }//GEN-LAST:event_prevButtonActionPerformed

    private void nextButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nextButtonActionPerformed
        if (parent instanceof MusicPanel) {
            String nextFile = ((MusicPanel) parent).getNextFile();
            playMedia(((MusicPanel) parent).getFilePath(nextFile));
        }
    }//GEN-LAST:event_nextButtonActionPerformed

    public AudioMediaPlayerComponent getAudioPlayer() {
        return this.audioPlayer;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel endLabel;
    private javax.swing.JPanel mediaPlayerPanel;
    private javax.swing.JSlider mediaSlider;
    private javax.swing.JButton nextButton;
    private javax.swing.JButton playButton;
    private javax.swing.JButton prevButton;
    private javax.swing.JLabel startLabel;
    // End of variables declaration//GEN-END:variables
}
