package com.Ukasz09.ValentineGame.soundsModule.soundsPath;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;

public class SoundsPlayer {
    private String soundPath;
    private MediaPlayer mediaPlayer;
    double volume;
    boolean inLoop;

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public SoundsPlayer(String soundPath, double volume, boolean inLoop) {
        this.soundPath = soundPath;
        this.inLoop = inLoop;
        this.volume = volume;
        makeSound();
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private void makeSound() {
        String resource = new File(soundPath).toURI().toString();
        Media media = new Media(resource);
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setVolume(volume);

        if (inLoop)
            mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
    }

    public void playSound() {
        mediaPlayer.play();
    }

    public void stopSound() {
        mediaPlayer.stop();
    }
}
