package com.Ukasz09.ValentineGame.gameModules.sprites.others;

import com.Ukasz09.ValentineGame.gameModules.sprites.creatures.Sprite;
import javafx.scene.image.Image;

public class MoneyBag extends Sprite {

    private int value;

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /* Konstruktor */

    public MoneyBag(Image image, int value){

        super(image);
        this.value=value;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /* Gettery */

    public int getValue() {
        return value;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /* Settery */

    public void setValue(int value) {
        this.value = value;
    }
}
