package com.Ukasz09.ValentineGame.gameModules.sprites.items.weapons;

import com.Ukasz09.ValentineGame.gameModules.sprites.creatures.Monster;
import com.Ukasz09.ValentineGame.gameModules.utilitis.DirectionEnum;
import com.Ukasz09.ValentineGame.gameModules.utilitis.ViewManager;
import com.Ukasz09.ValentineGame.graphicModule.texturesPath.ImageSheetProperty;
import com.Ukasz09.ValentineGame.graphicModule.texturesPath.FrameStatePositions;
import com.Ukasz09.ValentineGame.graphicModule.texturesPath.KindOfState;
import com.Ukasz09.ValentineGame.soundsModule.soundsPath.SoundsPath;
import com.Ukasz09.ValentineGame.soundsModule.soundsPath.SoundsPlayer;

public class Bullet extends Weapon {
    private static final double DEFAULT_LIVES_TAKES = 1;
    private static final double DEFAULT_MAX_OVERHEATING = 1000;
    private static final double DEFAULT_SHOT_VELOCITY = 600;
    private static final String SHOT_SOUND_PATH = SoundsPath.BULLET_SHOT_SOUND_PATH;
    private static final double SHOT_SOUND_VOLUME = 0.2;
    private static double maxOverheating = DEFAULT_MAX_OVERHEATING;
    private static final double DEFAULT_SPRITE_WIDTH = 71;
    private static final double DEFAULT_SPRITE_HEIGHT = 34;

    private  SoundsPlayer shotSound;
    private DirectionEnum shotDirection;
    private FrameStatePositions actualState;


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public Bullet(ImageSheetProperty spriteSheetProperty, DirectionEnum shotDirection, double positionX, double positionY, ViewManager manager) {
        this(spriteSheetProperty, DEFAULT_SHOT_VELOCITY, shotDirection, positionX, positionY, DEFAULT_LIVES_TAKES, DEFAULT_MAX_OVERHEATING, manager);

    }

    public Bullet(ImageSheetProperty spriteSheetProperty, double shotVelocity, DirectionEnum shotDirection, double positionX, double positionY, double howManyLiveTakes, double maxOverheating, ViewManager manager) {
        super(spriteSheetProperty, DEFAULT_SPRITE_WIDTH, DEFAULT_SPRITE_HEIGHT, shotVelocity, shotVelocity, positionX, positionY, howManyLiveTakes, manager);
        Bullet.maxOverheating = maxOverheating;
        this.shotDirection = shotDirection;
        actualState = spriteSheetProperty.getAction(KindOfState.MOVE);
        shotSound = new SoundsPlayer(SHOT_SOUND_PATH, SHOT_SOUND_VOLUME, false);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @Override
    protected void setPositionOfNextFrame() {
        setPositionOfNextFrame(actualState.getMinX(), actualState.getMaxX(), actualState.getMinY(), actualState.getMaxY(), spriteImage.getWidth());
    }

    @Override
    public void update(double elapsedTime) {
        if (shotDirection.equals(DirectionEnum.RIGHT))
            update(elapsedTime, 1, 0);
        if (shotDirection.equals(DirectionEnum.LEFT))
            update(elapsedTime, -1, 0);
    }

    @Override
    public boolean isOutOfBoundary() {
        return (getPositionX() > getManager().getRightFrameBorder()) || (getPositionX() < 0);
    }

    @Override
    public void doOutOfBoundaryAction() {
        //nothing to do
    }

    @Override
    public void hitMonster(Monster monster) {
        monster.removeLives(getHowManyLivesTake());
    }

    public void playShotSound() {
        shotSound.playSound();
    }

    @Override
    public void render() {
        if (shotDirection == DirectionEnum.LEFT)
            drawMirrorReflectedImage();
        else super.render();
    }

    public static double getMaxOverheating() {
        return maxOverheating;
    }

}
