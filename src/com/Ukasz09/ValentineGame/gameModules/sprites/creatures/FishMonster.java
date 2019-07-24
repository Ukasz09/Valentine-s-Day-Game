package com.Ukasz09.ValentineGame.gameModules.sprites.creatures;

import com.Ukasz09.ValentineGame.gameModules.sprites.effects.collisionAvoidEffect.ICollisionAvoidWay;
import com.Ukasz09.ValentineGame.gameModules.utilitis.ViewManager;
import com.Ukasz09.ValentineGame.gameModules.sprites.effects.kickEffect.KickPlayer;
import com.Ukasz09.ValentineGame.soundsModule.soundsPath.SoundsPath;
import com.Ukasz09.ValentineGame.soundsModule.soundsPath.SoundsPlayer;
import javafx.scene.image.Image;

import java.util.ArrayList;

public class FishMonster extends Monster {

    private final String hitSoundPath= SoundsPath.FISH_MONSTER_HIT_SOUND_PATH;
    private final String deathSoundPath=SoundsPath.FISH_MONSTER_DEATH_SOUND_PATH;
    private final double defaultDeathVolume=1;
    private final double defaultHitVolume=1;

    private final double howManyLivesTake=0.5;
    private final int howBigKickSize=0;
    private final double velocityX=2;
    private final double velocityY=2;

    private Image imageLeft;
    private Image imageRight;
    private Image imageBottom;
    private Image imageTop;

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public FishMonster(Image imageLeft, Image imageRight, Image imageBottom, Image imageTop, KickPlayer kickMethod, ViewManager manager, ICollisionAvoidWay collisionAvoidWay){
        super(imageLeft, kickMethod, manager, collisionAvoidWay);
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

    @Override
    public void isDeadAction() {
        defaultIsDeadAction(defaultDeathVolume);
    }

    @Override
    public void isHitAction() {
        defaultIsHitAction(defaultHitVolume);
    }

    @Override
    public void missHitAction() {
        //nothing
    }
}
