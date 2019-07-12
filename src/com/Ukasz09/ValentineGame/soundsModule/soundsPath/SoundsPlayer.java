package com.Ukasz09.ValentineGame.soundsModule.soundsPath;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;
import java.net.URL;

public class SoundsPlayer {
    private String soundPath;
    private MediaPlayer mediaPlayer;

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public SoundsPlayer(String soundPath) {
        this.soundPath = soundPath;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public void playSound(double volume, boolean inLoop) {
        //URL resource = getClass().getResource(soundPath);
        String resource=new File(soundPath).toURI().toString();
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
