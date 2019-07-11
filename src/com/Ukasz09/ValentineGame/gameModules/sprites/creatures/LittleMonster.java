package com.Ukasz09.ValentineGame.gameModules.sprites.creatures;

import com.Ukasz09.ValentineGame.gameModules.utilitis.ViewManager;
import com.Ukasz09.ValentineGame.gameModules.sprites.effects.kickEffect.KickPlayer;
import com.Ukasz09.ValentineGame.soundsModule.soundsPath.SoundsPath;
import com.Ukasz09.ValentineGame.soundsModule.soundsPath.SoundsPlayer;
import javafx.scene.image.Image;

public class LittleMonster extends Monster{

    private final String hitSoundPath= SoundsPath.LITTLE_MONSTER_HIT_SOUND_PATH;
    private final String deathSoundPath=SoundsPath.LITTLE_MONSTER_DEATH_SOUND_PATH;

    private final double howManyLivesTake=0.5;
    private final int howBigKickSize=200;
    private final double velocityX=1;
    private final double velocityY=1;

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public LittleMonster(Image image, KickPlayer kickMethod, ViewManager manager){
        super(image, kickMethod, manager);
        setLives(3);

        setHitSound(new SoundsPlayer(hitSoundPath));
        setDeathSound(new SoundsPlayer(deathSoundPath));
        setHowBigKickSize(howBigKickSize);
        setHowManyLivesTake(howManyLivesTake);
        setVelocity(velocityX,velocityY);
    }

}
