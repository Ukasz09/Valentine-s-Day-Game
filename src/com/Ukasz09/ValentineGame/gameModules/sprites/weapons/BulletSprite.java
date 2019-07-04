package com.Ukasz09.ValentineGame.gameModules.sprites.weapons;

import com.Ukasz09.ValentineGame.gameModules.sprites.creatures.Sprite;
import javafx.scene.image.Image;

public class BulletSprite extends ShootSprite {

    private final double howManyLiveTakes=1;
    private final double maxOverheating=1000;
    private static final double defaultShotVelocity=600;

    private String shotDirection;

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /* Konstruktor */

    public BulletSprite(Image image, String shotDirection, double shotVelocity){

        super(image, shotVelocity);
        this.shotDirection =shotDirection;
        setHowManyLivesTake(howManyLiveTakes);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /* Gettery */

    public double getMaxOverheating() {
        return maxOverheating;
    }

    public static double getDefaultShotVelocity() {
        return defaultShotVelocity;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /* Metody */

    @Override
    public void update(double time){

        if(shotDirection.equals("D"))
            addPositionX(getVelocityX()*time);
        if(shotDirection.equals("A"))
            addPositionX((-1)*getVelocityX()*time);
    }

    public void setPosition(Sprite ukasz){

        double shotPositionY = ukasz.getBoundary().getMinY()+160;
        double shotPositionRightX = ukasz.getBoundary().getMaxX();
        double shotPositionLeftX = ukasz.getBoundary().getMinX();

        if(shotDirection.equals("D"))
            setPosition(shotPositionRightX,shotPositionY);
        else  setPosition(shotPositionLeftX,shotPositionY);
    }

}
