package com.Ukasz09.ValentineGame.gameModules.sprites.others;

import com.Ukasz09.ValentineGame.gameModules.sprites.creatures.Sprite;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public abstract class Shield {

    private int actualShieldTimer;  //licznik wyznaczajacy czy uruchomic tarcze (0- tak)
    private int defaultShieldTimer;
    private int defaultShieldDuration;
    private Image shieldImage;
    Sprite sprite;

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /* Konstruktor */

    public Shield(int defaultShieldTimer, int defaultShieldDuration, Image shieldImage, Sprite sprite){

        this.defaultShieldTimer=defaultShieldTimer;
        this.defaultShieldDuration=defaultShieldDuration;
        this.shieldImage=shieldImage;
        this.sprite=sprite;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /* Gettery/Settery */

    public int getActualShieldTimer() {
        return actualShieldTimer;
    }

    public int getDefaultShieldTimer() {
        return defaultShieldTimer;
    }

    public void setActualShieldTimer(int actualShieldTimer) {
        this.actualShieldTimer = actualShieldTimer;
    }

    public Image getShieldImage() {
        return shieldImage;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /* Metody */

    //ustawia timer tarczy
    public void makeShield(Sprite sprite){
        sprite.setProtectionTime(defaultShieldDuration);
    }

    public void reduceShieldDuration(Sprite sprite){

        sprite.removeProtectionTime(50);

        if (sprite.getProtectionTime()<0)
            sprite.setProtectionTime(0);
    }

    public void reduceShieldTimer(int value){

       actualShieldTimer-=value;

       if(actualShieldTimer<0)
           actualShieldTimer=0;
    }

    //tutaj dawac algorymt uruchamianaia tarczy (ustawienie timera/czasu ochrony, zmienjszanie timera/czasu ochrony)
    public abstract void updateAndDrawShield(GraphicsContext gc);

}
