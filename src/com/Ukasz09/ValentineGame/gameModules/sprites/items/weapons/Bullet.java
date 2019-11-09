package com.Ukasz09.ValentineGame.gameModules.sprites.items.weapons;

import com.Ukasz09.ValentineGame.gameModules.sprites.creatures.Monster;
import com.Ukasz09.ValentineGame.gameModules.sprites.creatures.Player;
import com.Ukasz09.ValentineGame.gameModules.utilitis.DirectionEnum;
import com.Ukasz09.ValentineGame.gameModules.utilitis.ViewManager;
import com.Ukasz09.ValentineGame.graphicModule.texturesPath.SpritesImages;
import com.Ukasz09.ValentineGame.soundsModule.soundsPath.SoundsPath;
import com.Ukasz09.ValentineGame.soundsModule.soundsPath.SoundsPlayer;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;

public class Bullet extends Weapon {
    public static final double DEFAULT_LIVES_TAKES = 1;
    public static final double DEFAULT_MAX_OVERHEATING = 1000;
    public static final double DEFAULT_SHOT_VELOCITY = 600;
    private static final Image DEFAULT_SHOT_IMAGE = SpritesImages.playerShotImage;
    private static final String SHOT_SOUND_PATH = SoundsPath.BULLET_SHOT_SOUND_PATH;
    private static final double SHOT_SOUND_VOLUME = 0.2;
    private static final SoundsPlayer SHOT_SOUND = new SoundsPlayer(SHOT_SOUND_PATH, SHOT_SOUND_VOLUME, false);
    private static double maxOverheating = DEFAULT_MAX_OVERHEATING;

    private DirectionEnum shotDirection;

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public Bullet(DirectionEnum shotDirection, double positionX, double positionY, ViewManager manager) {
        this(DEFAULT_SHOT_IMAGE, DEFAULT_SHOT_VELOCITY, shotDirection, positionX, positionY, DEFAULT_LIVES_TAKES, DEFAULT_MAX_OVERHEATING, manager);

    }

    public Bullet(Image image, double shotVelocity, DirectionEnum shotDirection, double positionX, double positionY, double howManyLiveTakes, double maxOverheating, ViewManager manager) {
        super(image, shotVelocity, shotVelocity, positionX, positionY, howManyLiveTakes, manager);
        this.maxOverheating = maxOverheating;
        this.shotDirection = shotDirection;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    public void update(double elapsedTime) {
        if (shotDirection.equals(DirectionEnum.RIGHT))
            update(elapsedTime,1,0);
        if (shotDirection.equals(DirectionEnum.LEFT))
            update(elapsedTime,-1,0);
    }

    @Override
    public boolean isOutOfBoundary() {
        if ((getPositionX() > getManager().getRightFrameBorder()) || (getPositionX() < getManager().getLeftFrameBorder()))
            return true;

        return false;
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
        Bullet.SHOT_SOUND.playSound();
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public static double getMaxOverheating() {
        return maxOverheating;
    }

}
