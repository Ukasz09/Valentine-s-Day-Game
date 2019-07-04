package com.Ukasz09.ValentineGame.gameModules.sprites.creatures;

import com.Ukasz09.ValentineGame.soundsModule.SoundsPath;
import com.Ukasz09.ValentineGame.soundsModule.Sounds;
import javafx.scene.image.Image;

public class LittleMonster extends Monster{

    private final String hitSoundPath= SoundsPath.littleMonsterHitSoundPath;
    private final String deathSoundPath=SoundsPath.littleMonsterDeathSoundPath;

    private final double howManyLivesTake=0.5;
    private final int howBigKickSize=0;
    private final double velocityX=1;
    private final double velocityY=1;

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /* Konstruktor */

    public LittleMonster(Image image){

        super(image);
        setLives(3);

        setHitSound(new Sounds(hitSoundPath));
        setDeathSound(new Sounds(deathSoundPath));
        setHowBigKickSize(howBigKickSize);
        setHowManyLivesTake(howManyLivesTake);
        setVelocity(velocityX,velocityY);
    }

}
