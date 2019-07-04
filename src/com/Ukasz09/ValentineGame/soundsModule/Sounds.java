package com.Ukasz09.ValentineGame.soundsModule;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.net.URL;

public class Sounds {

    String backgroundSoundPath;

    private  MediaPlayer mediaPlayer;
    private  Media media;

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /* Konstruktor */

    public Sounds(String backgroundSoundPath){

        this.backgroundSoundPath = backgroundSoundPath;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /* Metody */

    public void playSound(double volume, boolean inLoop){

        URL resource=getClass().getResource(backgroundSoundPath);
        media = new Media(resource.toString());

        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setVolume(volume);

        if(inLoop)
            mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);

        mediaPlayer.play();
    }

    public void stopSound(){
        mediaPlayer.stop();
    }
}
