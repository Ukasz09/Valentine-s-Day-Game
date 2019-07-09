package com.Ukasz09.ValentineGame.gameModules.sprites.creatures;

import com.Ukasz09.ValentineGame.gameModules.sprites.others.kickEffect.KickPlayer;
import com.Ukasz09.ValentineGame.soundsModule.SoundsPath;
import com.Ukasz09.ValentineGame.soundsModule.SoundsPlayer;
import javafx.scene.image.Image;

import java.util.ArrayList;

public class FishMonster extends Monster {

    private final String hitSoundPath= SoundsPath.fishMonsterHitSoundPath;
    private final String deathSoundPath=SoundsPath.fishMonsterDeathSoundPath;

    private final double howManyLivesTake=0.5;
    private final int howBigKickSize=0;
    private final double velocityX=3;
    private final double velocityY=3;

    private Image imageLeft;
    private Image imageRight;
    private Image imageBottom;
    private Image imageTop;

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /* Konstruktory */

    public FishMonster(Image imageLeft, Image imageRight, Image imageBottom, Image imageTop, KickPlayer kickMethod){

        super(imageLeft, kickMethod);
        setLives(2);

        setHitSound(new SoundsPlayer(hitSoundPath));
        setDeathSound(new SoundsPlayer(deathSoundPath));
        setHowBigKickSize(howBigKickSize);
        setHowManyLivesTake(howManyLivesTake);
        setVelocity(velocityX,velocityY);

        this.imageLeft=imageLeft;
        this.imageRight=imageRight;
        this.imageBottom=imageBottom;
        this.imageTop=imageTop;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /* Metody */

    @Override
    public void update(Sprite ukasz, ArrayList<Monster> monsters) {

        super.update(ukasz, monsters);
        setImageByPosition(imageLeft,imageRight,imageBottom,imageTop,ukasz);
    }
}
