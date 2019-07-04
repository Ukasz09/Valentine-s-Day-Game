package com.Ukasz09.ValentineGame.gameModules.sprites.others;

import com.Ukasz09.ValentineGame.gameModules.sprites.creatures.Sprite;
import com.Ukasz09.ValentineGame.gameModules.sprites.creatures.Ukasz;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class UkaszShield extends Shield {

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /* Konstruktor */

    public UkaszShield(int defaultShieldTimer, int defaultShieldDuration, Image shieldImage, Sprite sprite){

        super(defaultShieldTimer,defaultShieldDuration,shieldImage,sprite);

    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /* Metody */

    //Shield timer: 1- nie ustawiac nowego czasu trwania tarczy, 0- ustawic nowy czas trwania tarczy
    @Override
    public void updateAndDrawShield(GraphicsContext gc){

        if(getActualShieldTimer()==0){

            makeShield(sprite);
            setActualShieldTimer(1);
        }

        if(sprite.getProtectionTime()>0){

            //opoznienie by bylo widac ze tarcza znika przed kolejnym uderzeniem
            if(sprite.getProtectionTime()>750){

                Ukasz ukasz=(Ukasz) sprite;

                if(ukasz.getLastDirectionX().equals("D"))
                    gc.drawImage(getShieldImage(), ukasz.getPositionX(), ukasz.getPositionY());
                else gc.drawImage(getShieldImage(),ukasz.getPositionX()-50, ukasz.getPositionY());
            }

            reduceShieldDuration(sprite);
        }

    }
}
