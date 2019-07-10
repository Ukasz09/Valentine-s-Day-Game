package com.Ukasz09.ValentineGame.gameModules.sprites.weapons;

import com.Ukasz09.ValentineGame.gameModules.sprites.creatures.Sprite;
import com.Ukasz09.ValentineGame.soundsModule.SoundsPlayer;
import javafx.scene.image.Image;

public abstract class ShotSprite extends Sprite {

    private double howManyLivesTake;
    private double shotVelocity;

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /* Konstruktor */

    public ShotSprite(Image image, double shotVelocity, double howManyLivesTake){
        super(image);
        this.howManyLivesTake=howManyLivesTake;
        this.shotVelocity=shotVelocity;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /* Gettery/Settery */

    public double getHowManyLivesTake() {
        return howManyLivesTake;
    }

    public double getShotVelocity() {
        return shotVelocity;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /* Metody */

    public abstract void update(double time);

    protected void playSound(SoundsPlayer sound, double volume){
        sound.playSound(volume,false);
    }

    public abstract void playShotSound();

    public abstract void playBoomSound();
}
