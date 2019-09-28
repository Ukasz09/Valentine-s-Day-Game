package com.Ukasz09.ValentineGame.gameModules.sprites.items.weapons;

import com.Ukasz09.ValentineGame.gameModules.sprites.Sprite;
import com.Ukasz09.ValentineGame.gameModules.sprites.creatures.Monster;
import com.Ukasz09.ValentineGame.gameModules.sprites.creatures.Player;
import com.Ukasz09.ValentineGame.gameModules.utilitis.ViewManager;
import com.Ukasz09.ValentineGame.gameModules.sprites.creatures.Creature;
import javafx.scene.image.Image;

public abstract class Weapon extends Sprite {

    private double howManyLivesTake;
    private double shotVelocity;
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public Weapon(Image image, double shotVelocity, double howManyLivesTake, ViewManager manager) {
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

    public abstract void prepareToShot(Player player);

    public abstract void update(double time);

    public abstract boolean isOutOfBoundary();

    public abstract void doOutOfBoundaryAction();

    public abstract void hitMonster(Monster monster);
}
