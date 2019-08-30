package com.Ukasz09.ValentineGame.soundsModule.soundsPath;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;
import java.net.URL;

public class SoundsPlayer {
    private String soundPath;
    private MediaPlayer mediaPlayer;
    double volume;
    boolean inLoop;

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public SoundsPlayer(String soundPath, double volume, boolean inLoop) {
        this.soundPath = soundPath;
        this.volume = volume;
        this.inLoop=inLoop;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public void playSound(double volume, boolean inLoop) {
        this.volume = volume;
        this.inLoop=inLoop;
        playSound();
    }

    public void playSound() {
        String resource = new File(soundPath).toURI().toString();
        Media media = new Media(resource);
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setVolume(volume);

        if (inLoop)
            mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);

        mediaPlayer.play();
    }

    public void stopSound() {
        mediaPlayer.stop();
    }
}
