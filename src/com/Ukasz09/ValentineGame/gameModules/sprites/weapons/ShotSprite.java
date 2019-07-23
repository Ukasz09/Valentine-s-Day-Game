package com.Ukasz09.ValentineGame.gameModules.sprites.weapons;

import com.Ukasz09.ValentineGame.gameModules.sprites.creatures.Monster;
import com.Ukasz09.ValentineGame.gameModules.utilitis.ViewManager;
import com.Ukasz09.ValentineGame.gameModules.sprites.creatures.Sprite;
import com.Ukasz09.ValentineGame.soundsModule.soundsPath.SoundsPlayer;
import javafx.scene.image.Image;

public abstract class ShotSprite extends Sprite {

    private double howManyLivesTake;
    private double shotVelocity;

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public ShotSprite(Image image, double shotVelocity, double howManyLivesTake, ViewManager manager) {
        super(image, manager);
        this.howManyLivesTake = howManyLivesTake;
        this.shotVelocity = shotVelocity;
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

    public abstract void setPosition(Sprite sprite);

    public abstract void update(double time);

    protected void playSound(SoundsPlayer sound, double volume) {
        sound.playSound(volume, false);
    }

    public abstract void playShotSound();

    public abstract boolean isOutOfBoundary();

    public abstract void doOutOfBoundaryAction();

    public abstract void hitMonster(Monster monster);
}
