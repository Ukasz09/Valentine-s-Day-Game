package com.Ukasz09.ValentineGame.soundsModule.soundsPath;

import com.Ukasz09.ValentineGame.SoundResource;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;

public class SoundsPlayer {
    private String soundPath;
    private MediaPlayer mediaPlayer;
    private double volume;
    private boolean inLoop;

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public SoundsPlayer(String soundPath, double volume, boolean inLoop) {
        this.soundPath = soundPath;
        this.inLoop = inLoop;
        this.volume = volume;
        makeSound();
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private void makeSound() {
        java.net.URL soundURL = SoundResource.class.getResource(soundPath);
        Media media = new Media(soundURL.toString());
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
