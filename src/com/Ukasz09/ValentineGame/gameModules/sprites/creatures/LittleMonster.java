package com.Ukasz09.ValentineGame.gameModules.sprites.creatures;

import com.Ukasz09.ValentineGame.gameModules.effects.collisionAvoidEffect.ICollisionAvoidWay;
import com.Ukasz09.ValentineGame.gameModules.effects.rotateEffect.RotateEffect;
import com.Ukasz09.ValentineGame.gameModules.utilitis.ViewManager;
import com.Ukasz09.ValentineGame.gameModules.effects.kickEffect.KickPlayer;
import com.Ukasz09.ValentineGame.graphicModule.texturesPath.ImageSheetProperty;
import com.Ukasz09.ValentineGame.graphicModule.texturesPath.FrameStatePositions;
import com.Ukasz09.ValentineGame.graphicModule.texturesPath.KindOfState;
import com.Ukasz09.ValentineGame.soundsModule.soundsPath.SoundsPath;
import com.Ukasz09.ValentineGame.soundsModule.soundsPath.SoundsPlayer;

public class LittleMonster extends Monster {

    private static final String HIT_SOUND_PATH = SoundsPath.LITTLE_MONSTER_HIT_SOUND_PATH;
    private static final String DEATH_SOUND_PATH = SoundsPath.LITTLE_MONSTER_DEATH_SOUND_PATH;

    private static final double DEATH_SOUND_VOLUME = 1;
    private static final double HIT_SOUND_VOLUME = 1;

    private static final SoundsPlayer HIT_SOUND = new SoundsPlayer(HIT_SOUND_PATH, HIT_SOUND_VOLUME, false);
    private static final SoundsPlayer DEATH_SOUND = new SoundsPlayer(DEATH_SOUND_PATH, DEATH_SOUND_VOLUME, false);

    private static final double DEFAULT_SPRITE_WIDTH = 98;
    private static final double DEFAULT_SPRITE_HEIGHT = 90;

    private static final double DEFAULT_LIVES = 3;
    private static final double DEFAULT_LIVES_TAKE = 0.5;
    private static final double DEFAULT_KICK_SIZE = 0;
    private static final double DEFAULT_VELOCITY_X = 1;
    private static final double DEFAULT_VELOCITY_Y = 1;
    private final double rotateOffset = (Math.random() * (360));

    private FrameStatePositions actualState;

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public LittleMonster(ImageSheetProperty spriteSheetProperty, KickPlayer kickMethod, ViewManager manager, ICollisionAvoidWay collisionAvoidWay) {
        super(spriteSheetProperty, DEFAULT_SPRITE_WIDTH, DEFAULT_SPRITE_HEIGHT, manager);
        hueImage(-1, 1);
        setCreatureProperties(DEFAULT_LIVES, DEFAULT_VELOCITY_X, DEFAULT_VELOCITY_Y);
        setMonsterProperties(kickMethod, DEFAULT_KICK_SIZE, DEFAULT_LIVES_TAKE, collisionAvoidWay);

        actualState = spriteSheetProperty.getAction(KindOfState.MOVE);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


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
    public void updateMonsterRotate(Creature target) {
        double rotate = RotateEffect.setRotateByAngle(this, target);
        rotate += rotateOffset;
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

//    @Override
//    public void render() {
//        drawBoundaryForTests();
//        getManager().getGraphicContext().drawImage(spriteSheet, actualFramePositionX, actualFramePositionY, widthOfOneFrame, heightOfOneFrame, getPositionX(), getPositionY(), width, height);
//    }

    @Override
    protected void setPositionOfNextFrame() {
        setPositionOfNextFrame(actualState.getMinX(), actualState.getMaxX(), actualState.getMinY(), actualState.getMaxY(), spriteImage.getWidth());
    }
}
