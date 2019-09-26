package com.Ukasz09.ValentineGame.gameModules.sprites.creatures;

import com.Ukasz09.ValentineGame.gameModules.sprites.effects.collisionAvoidEffect.ICollisionAvoidWay;
import com.Ukasz09.ValentineGame.gameModules.sprites.effects.rotateEffect.RotateEffect;
import com.Ukasz09.ValentineGame.gameModules.utilitis.ViewManager;
import com.Ukasz09.ValentineGame.gameModules.sprites.effects.kickEffect.KickPlayer;
import com.Ukasz09.ValentineGame.soundsModule.soundsPath.SoundsPath;
import com.Ukasz09.ValentineGame.soundsModule.soundsPath.SoundsPlayer;
import javafx.scene.image.Image;

public class LittleMonster extends Monster {
    private static final String HIT_SOUND_PATH = SoundsPath.LITTLE_MONSTER_HIT_SOUND_PATH;
    private static final String DEATH_SOUND_PATH = SoundsPath.LITTLE_MONSTER_DEATH_SOUND_PATH;

    private static final double DEATH_SOUND_VOLUME = 1;
    private static final double HIT_SOUND_VOLUME = 1;

    private static final SoundsPlayer HIT_SOUND = new SoundsPlayer(HIT_SOUND_PATH, HIT_SOUND_VOLUME, false);
    private static final SoundsPlayer DEATH_SOUND = new SoundsPlayer(DEATH_SOUND_PATH, DEATH_SOUND_VOLUME, false);

    private final double defaultLives =3;
    private final double defaultLivesTake =0.5;
    private final double defaultKickSize =0;
    private final double defaultVelocityX =1;
    private final double defaultVelocityY =1;
    private final double rotateOffset=Math.random()*360;

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public LittleMonster(Image image, KickPlayer kickMethod, ViewManager manager, ICollisionAvoidWay collisionAvoidWay) {
        super(image, kickMethod, manager, collisionAvoidWay);
        setDefaultProperties();
    }

    public LittleMonster(Image image, KickPlayer kickMethod, ViewManager manager, ICollisionAvoidWay collisionAvoidWay, double livesTake, double lives, double kickSize, double velocityX, double velocityY){
        super(image,kickMethod,manager,collisionAvoidWay);
        setProperties(kickSize, livesTake,lives, velocityX, velocityY);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public void setDefaultProperties() {
        setProperties(defaultKickSize, defaultLivesTake, defaultLives, defaultVelocityX, defaultVelocityY);
    }

    @Override
    public void setStartedPosition() {
        setPositionByDirection(true, false, true, true, getWidth());
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @Override
    public void actionWhenMissHit() {
        //nothing
    }

    @Override
    public void updateMonsterRotate(Sprite target) {
        double rotate= RotateEffect.setRotateByAngle(this,target);
        rotate+=rotateOffset;
        setActualRotation(rotate);
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

    @Override
    public boolean hasActiveShield() {
        return false;
    }
}
