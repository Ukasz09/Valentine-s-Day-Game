package com.Ukasz09.ValentineGame.gameModules.sprites.creatures;

import com.Ukasz09.ValentineGame.gameModules.sprites.Sprite;
import com.Ukasz09.ValentineGame.gameModules.utilitis.ViewManager;
import com.Ukasz09.ValentineGame.graphicModule.texturesPath.CreatureSheetProperty;
import com.Ukasz09.ValentineGame.graphicModule.texturesPath.ImageSheetProperty;
import javafx.scene.image.Image;

public abstract class Creature extends Sprite {
    private final static double DEFAULT_MAX_LIVES = 0;
    private final static double DEFAULT_LIVES = 0;

    private double lives;
    private double maxLives;


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//    public Creature(Image image, ViewManager manager) {
//        super(image, manager);
//    }

    public Creature(CreatureSheetProperty spriteSheetProperty, double creatureWidth, double creatureHeight, ViewManager manager) {
        super(spriteSheetProperty, creatureWidth, creatureHeight, manager);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    protected void setCreatureProperties(double maxLives, double velocityX, double velocityY) {
        setLives(maxLives);
        setMaxLives(maxLives);
        setActualVelocity(velocityX,velocityY);
    }

    protected void setMaxLives(double maxLives) {
        if (maxLives > 0)
            this.maxLives = maxLives;
        else this.maxLives = DEFAULT_MAX_LIVES;
    }

    protected void setLives(double lives) {
        if (lives > 0)
            this.lives = lives;
        else this.lives = DEFAULT_LIVES;
    }

    public double getLives() {
        return lives;
    }

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

    public double getMaxLives() {
        return maxLives;
    }
}
