package com.Ukasz09.ValentineGame.gameModules.sprites.creatures;

import com.Ukasz09.ValentineGame.gameModules.effects.collisionAvoidEffect.ICollisionAvoidWay;
import com.Ukasz09.ValentineGame.gameModules.effects.rotateEffect.RotateEffect;
import com.Ukasz09.ValentineGame.gameModules.utilitis.ViewManager;
import com.Ukasz09.ValentineGame.gameModules.effects.kickEffect.KickPlayer;
import com.Ukasz09.ValentineGame.soundsModule.soundsPath.SoundsPath;
import com.Ukasz09.ValentineGame.soundsModule.soundsPath.SoundsPlayer;
import javafx.scene.image.Image;


public class FishMonster extends Monster {
    private static final String HIT_SOUND_PATH = SoundsPath.FISH_MONSTER_HIT_SOUND_PATH;
    private static final String DEATH_SOUND_PATH = SoundsPath.FISH_MONSTER_DEATH_SOUND_PATH;
    private static final double DEATH_SOUND_VOLUME = 1;
    private static final double HIT_SOUND_VOLUME = 1;
    private static final SoundsPlayer HIT_SOUND = new SoundsPlayer(HIT_SOUND_PATH, HIT_SOUND_VOLUME, false);
    private static final SoundsPlayer DEATH_SOUND = new SoundsPlayer(DEATH_SOUND_PATH, DEATH_SOUND_VOLUME, false);

    private final double defaultLives = 2;
    private final double defaultLivesTake = 0.5;
    private final int defaultKickSize = 0;
    private final double defaultVelocityX = 2.2;
    private final double defaultVelocityY = 2.2;

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public FishMonster(Image imageRight, KickPlayer kickMethod, ViewManager manager, ICollisionAvoidWay collisionAvoidWay) {
        super(imageRight, kickMethod, manager, collisionAvoidWay);
        setLives(defaultLives);
        setKickSize(defaultKickSize);
        setLivesTake(defaultLivesTake);
        setVelocity(defaultVelocityX, defaultVelocityY);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @Override
    public void setStartedPosition() {
        setPositionByDirection(true, true, true, true, 60);
    }

    @Override
    public void actionWhenMissHit() {
        //nothing
    }

    @Override
    public void updateMonsterRotate(Creature target) {
        double properRotate = RotateEffect.setRotateByAngle(this, target);
        setActualRotation(properRotate);
    }

    @Override
    public SoundsPlayer getHitSoundOrNull() {
        return FishMonster.HIT_SOUND;
    }

    @Override
    public SoundsPlayer getMissSoundOrNull() {
        return null;
    }

    @Override
    public SoundsPlayer getDeathSoundOrNull() {
        return FishMonster.DEATH_SOUND;
    }

    @Override
    public boolean hasActiveShield() {
        return false;
    }
}
