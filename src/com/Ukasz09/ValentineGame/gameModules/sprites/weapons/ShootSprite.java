package com.Ukasz09.ValentineGame.gameModules.sprites.weapons;

import com.Ukasz09.ValentineGame.gameModules.sprites.creatures.Sprite;
import javafx.scene.image.Image;

public abstract class ShootSprite extends Sprite {

    private double howManyLivesTake;
    private double shotVelocity;

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /* Konstruktor */

    public ShootSprite(Image image, double shotVelocity){

        super(image);
        howManyLivesTake=1;
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

    public void setHowManyLivesTake(double howManyLivesTake) {
        this.howManyLivesTake = howManyLivesTake;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /* Metody */

    public abstract void update(double time);
}
