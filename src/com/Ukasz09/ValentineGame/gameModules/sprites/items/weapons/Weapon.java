package com.Ukasz09.ValentineGame.gameModules.sprites.items.weapons;

import com.Ukasz09.ValentineGame.gameModules.sprites.Sprite;
import com.Ukasz09.ValentineGame.gameModules.sprites.creatures.Monster;
import com.Ukasz09.ValentineGame.gameModules.utilitis.ViewManager;
import javafx.scene.image.Image;

public abstract class Weapon extends Sprite {

    private double howManyLivesTake;
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public Weapon(Image image, double shotVelocityX, double shotVelocityY, double positionX, double positionY, double howManyLivesTake, ViewManager manager) {
        super(image, manager);
        setPosition(positionX,positionY);
        setActualVelocity(shotVelocityX,shotVelocityY);

        this.howManyLivesTake = howManyLivesTake;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public double getHowManyLivesTake() {
        return howManyLivesTake;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public abstract void update(double elapsedTime);

    public abstract boolean isOutOfBoundary();

    public abstract void doOutOfBoundaryAction();

    public abstract void hitMonster(Monster monster);
}
