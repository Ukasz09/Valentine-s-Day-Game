package com.Ukasz09.ValentineGame.gameModules.sprites.creatures;

import com.Ukasz09.ValentineGame.gameModules.sprites.effects.collisionAvoidEffect.ICollisionAvoidWay;
import com.Ukasz09.ValentineGame.gameModules.utilitis.ViewManager;
import com.Ukasz09.ValentineGame.gameModules.sprites.effects.kickEffect.KickPlayer;
import com.Ukasz09.ValentineGame.soundsModule.soundsPath.SoundsPath;
import com.Ukasz09.ValentineGame.soundsModule.soundsPath.SoundsPlayer;
import javafx.scene.image.Image;

public class LittleMonster extends Monster {
    public static final String HIT_SOUND_PATH = SoundsPath.LITTLE_MONSTER_HIT_SOUND_PATH;
    public static final String DEATH_SOUND_PATH = SoundsPath.LITTLE_MONSTER_DEATH_SOUND_PATH;
    public static final double DEATH_SOUND_VOLUME = 1;
    public static final double HIT_SOUND_VOLUME = 1;
    private static final SoundsPlayer HIT_SOUND = new SoundsPlayer(HIT_SOUND_PATH, HIT_SOUND_VOLUME, false);
    private static final SoundsPlayer DEATH_SOUND = new SoundsPlayer(DEATH_SOUND_PATH, DEATH_SOUND_VOLUME, false);


    private final double howManyLivesTake = 0.5;
    private final int howBigKickSize = 0;
    private final double velocityX = 1;
    private final double velocityY = 1;

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public LittleMonster(Image image, KickPlayer kickMethod, ViewManager manager, ICollisionAvoidWay collisionAvoidWay) {
        super(image, kickMethod, manager, collisionAvoidWay);
        setLives(3);

//        setHitSound(HIT_SOUND_PATH, HIT_SOUND_VOLUME);
//        setDeathSound(DEATH_SOUND_PATH, DEATH_SOUND_VOLUME);
        setHowBigKickSize(howBigKickSize);
        setHowManyLivesTake(howManyLivesTake);
        setVelocity(velocityX, velocityY);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @Override
    public void setStartedPosition() {
        setPositionByDirection(true, false, true, true, getWidth());
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @Override
    public void missHitAction() {
        //nothing
    }

    @Override
    public SoundsPlayer getHitSoundOrNull() {
        return LittleMonster.HIT_SOUND;
    }

    @Override
    public SoundsPlayer getMissSoundOrNull() {
        return null;
    }

    @Override
    public SoundsPlayer getDeathSoundOrNull() {
        return LittleMonster.DEATH_SOUND;
    }
}
