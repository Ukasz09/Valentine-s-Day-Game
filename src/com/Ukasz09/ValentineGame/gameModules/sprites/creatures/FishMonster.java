package com.Ukasz09.ValentineGame.gameModules.sprites.creatures;

import com.Ukasz09.ValentineGame.gameModules.effects.collisionAvoidEffect.ICollisionAvoidWay;
import com.Ukasz09.ValentineGame.gameModules.effects.rotateEffect.RotateEffect;
import com.Ukasz09.ValentineGame.gameModules.utilitis.ViewManager;
import com.Ukasz09.ValentineGame.gameModules.effects.kickEffect.KickPlayer;
import com.Ukasz09.ValentineGame.graphicModule.texturesPath.CreatureSheetProperty;
import com.Ukasz09.ValentineGame.graphicModule.texturesPath.KindOfState;
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

    private static final double DEFAULT_SPRITE_WIDTH = 100;
    private static final double DEFAULT_SPRITE_HEIGHT = 88;

    private static final double DEFAULT_LIVES = 2;
    private static final double DEFAULT_LIVES_TAKE = 0.5;
    private static final int DEFAULT_KICK_SIZE = 0;
    private static final double DEFAULT_VELOCITY_X = 4.2;
    private static final double DEFAULT_VELOCITY_Y = 4.2;

    //todo:
    private KindOfState actualState;

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public FishMonster(CreatureSheetProperty spriteSheetProperty, KickPlayer kickMethod, ViewManager manager, ICollisionAvoidWay collisionAvoidWay) {
        super(spriteSheetProperty, DEFAULT_SPRITE_WIDTH, DEFAULT_SPRITE_HEIGHT, manager);
        hueImage(-1, 1);
//        setSpriteSheetProperties(WIDTH_OF_ONE_FRAME, HEIGHT_OF_ONE_FRAME);
        setCreatureProperties(DEFAULT_LIVES, DEFAULT_VELOCITY_X, DEFAULT_VELOCITY_Y);
        setMonsterProperties(kickMethod, DEFAULT_KICK_SIZE, DEFAULT_LIVES_TAKE, collisionAvoidWay);

        actualState = spriteSheetProperty.getMove();
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

    @Override
    protected void setPositionOfNextFrame() {
        setPositionOfNextFrame(actualState.getMinX(), actualState.getMaxX(), actualState.getMinY(), actualState.getMaxY(), spriteImage.getWidth());
    }
}
