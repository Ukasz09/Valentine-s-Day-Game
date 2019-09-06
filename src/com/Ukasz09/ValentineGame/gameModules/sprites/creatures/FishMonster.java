package com.Ukasz09.ValentineGame.gameModules.sprites.creatures;

import com.Ukasz09.ValentineGame.gameModules.sprites.effects.collisionAvoidEffect.ICollisionAvoidWay;
import com.Ukasz09.ValentineGame.gameModules.utilitis.ViewManager;
import com.Ukasz09.ValentineGame.gameModules.sprites.effects.kickEffect.KickPlayer;
import com.Ukasz09.ValentineGame.soundsModule.soundsPath.SoundsPath;
import javafx.scene.image.Image;

import java.util.ArrayList;

public class FishMonster extends Monster {
    public static final String HIT_SOUND_PATH = SoundsPath.FISH_MONSTER_HIT_SOUND_PATH;
    public static final String DEATH_SOUND_PATH = SoundsPath.FISH_MONSTER_DEATH_SOUND_PATH;
    public static final double DEATH_SOUND_VOLUME = 1;
    public static final double HIT_SOUND_VOLUME = 1;

    private final double howManyLivesTake = 0.5;
    private final int howBigKickSize = 0;
    private final double velocityX = 2.2;
    private final double velocityY = 2.2;

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public FishMonster(Image imageRight, KickPlayer kickMethod, ViewManager manager, ICollisionAvoidWay collisionAvoidWay) {
        super(imageRight, kickMethod, manager, collisionAvoidWay);
        setLives(2);
        setHitSound(HIT_SOUND_PATH, HIT_SOUND_VOLUME);
        setDeathSound(DEATH_SOUND_PATH, DEATH_SOUND_VOLUME);
        setHowBigKickSize(howBigKickSize);
        setHowManyLivesTake(howManyLivesTake);
        setVelocity(velocityX, velocityY);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // TODO: zrobione:
    @Override
    public void setStartedPosition() {
        setPositionByDirection(true, true, true, true, 0);
    }

    @Override
    public void isDeadAction() {
        defaultIsDeadAction(DEATH_SOUND_VOLUME);
    }

    @Override
    public void isHitAction() {
        defaultIsHitAction(HIT_SOUND_VOLUME);
    }

    @Override
    public void missHitAction() {
        //nothing
    }

}
