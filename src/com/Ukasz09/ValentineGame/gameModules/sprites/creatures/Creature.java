package com.Ukasz09.ValentineGame.gameModules.sprites.creatures;

import com.Ukasz09.ValentineGame.gameModules.sprites.Sprite;
import com.Ukasz09.ValentineGame.gameModules.utilitis.ViewManager;
import javafx.scene.image.Image;

public abstract class Creature extends Sprite {
    private double lives;
    protected double maxLives;

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public Creature(Image actualImage, ViewManager manager) {
        super(actualImage, manager);
        lives = 3;
        maxLives = lives;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public float getAngleToTarget(Creature target) {
        double dx = this.getPositionX();
        double dy = this.getPositionY();
        double diffX = target.getPositionX() - dx;
        double diffY = target.getPositionY() - dy;
        float angle = (float) Math.atan2(diffY, diffX);

        return angle;
    }

    public void removeLives(double howMany) {
        lives -= howMany;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public void setLives(double lives) {
        this.lives = lives;
    }

    public double getLives() {
        return lives;
    }

    public double getMaxLives() {
        return maxLives;
    }
}
