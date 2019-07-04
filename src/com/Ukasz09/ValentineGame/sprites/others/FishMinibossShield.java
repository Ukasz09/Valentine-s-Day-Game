package com.Ukasz09.ValentineGame.sprites.others;

import com.Ukasz09.ValentineGame.sprites.creatures.FishMonsterMiniboss;
import com.Ukasz09.ValentineGame.sprites.creatures.Sprite;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class FishMinibossShield extends Shield {

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /* Konstruktor */

    public FishMinibossShield(int defaultShieldTimer, int defaultShieldDuration, Image shieldImage, Sprite sprite){

        super(defaultShieldTimer,defaultShieldDuration,shieldImage, sprite);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /* Metody */

    @Override
    public void updateAndDrawShield(GraphicsContext gc){

       FishMonsterMiniboss fishMonsterMiniboss=(FishMonsterMiniboss) sprite;

        //jesli nie ma tarczy
        if(fishMonsterMiniboss.getProtectionTime()==0){

            //sprawdz czy mozna ja uruchomic juz
            if(getActualShieldTimer()==0){

                makeShield(fishMonsterMiniboss);
                setActualShieldTimer(getDefaultShieldTimer());
            } else reduceShieldTimer(50);
        }
        else {

            double centerPositionX;

            if(fishMonsterMiniboss.getImage()==fishMonsterMiniboss.getImageLeft())
                centerPositionX=fishMonsterMiniboss.getPositionX()+40;
            else centerPositionX=fishMonsterMiniboss.getPositionX();

            double centerPositionY=fishMonsterMiniboss.getPositionY();

            reduceShieldDuration(fishMonsterMiniboss);
            gc.drawImage(getShieldImage(),centerPositionX,centerPositionY);
        }

    }
}
