package com.Ukasz09.ValentineGame.gameModules.sprites.weapons;

import com.Ukasz09.ValentineGame.gameModules.sprites.creatures.Monster;
import com.Ukasz09.ValentineGame.gameModules.sprites.creatures.Player;
import com.Ukasz09.ValentineGame.gameModules.utilitis.Game;
import com.Ukasz09.ValentineGame.gameModules.utilitis.ViewManager;
import com.Ukasz09.ValentineGame.graphicModule.texturesPath.SpritesImages;
import com.Ukasz09.ValentineGame.soundsModule.soundsPath.SoundsPath;
import com.Ukasz09.ValentineGame.soundsModule.soundsPath.SoundsPlayer;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;

public class BulletSprite extends ShotSprite {
    public static final double DEFAULT_LIVES_TAKES = 1;
    public static final double DEFAULT_MAX_OVERHEATING = 1000;
    public static final double DEFAULT_SHOT_VELOCITY = 600;
    private static final Image DEFAULT_SHOT_IMAGE = SpritesImages.playerShotImage;
    private static final String SHOT_SOUND_PATH = SoundsPath.BULLET_SHOT_SOUND_PATH;
    private static final double SHOT_SOUND_VOLUME = 0.2;
    private static final SoundsPlayer SHOT_SOUND = new SoundsPlayer(SHOT_SOUND_PATH, SHOT_SOUND_VOLUME, false);
    private static double maxOverheating = DEFAULT_MAX_OVERHEATING;

    private YAxisDirection shotDirection;

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public BulletSprite(YAxisDirection shotDirection, ViewManager manager) {
        this(DEFAULT_SHOT_IMAGE, shotDirection, manager);
    }

    public BulletSprite(Image image, YAxisDirection shotDirection, ViewManager manager) {
        this(image, DEFAULT_SHOT_VELOCITY, shotDirection, DEFAULT_LIVES_TAKES, DEFAULT_MAX_OVERHEATING, manager);
    }

    public BulletSprite(Image image, double shotVelocity, YAxisDirection shotDirection, double howManyLiveTakes, double maxOverheating, ViewManager manager) {
        super(image, shotVelocity, howManyLiveTakes, manager);
        this.maxOverheating = maxOverheating;
        this.shotDirection = shotDirection;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    public void update(double time) {
        if (shotDirection.equals(YAxisDirection.RIGHT))
            addPositionX(getVelocityX() * time);
        if (shotDirection.equals(YAxisDirection.LEFT))
            addPositionX((-1) * getVelocityX() * time);
    }

    @Override
    public boolean isOutOfBoundary() {
        if ((getPositionX() > getManager().getRightBorder()) || (getPositionX() < getManager().getLeftBorder()))
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

    private void playShotSound() {
        BulletSprite.SHOT_SOUND.playSound();
    }

    @Override
    public void prepareToShot(Player player) {
        Point2D bulletPosition = player.getBulletPosition();
        setPosition(bulletPosition.getX(), bulletPosition.getY());
        setVelocity(getShotVelocity(), 0);
        playShotSound();
    }


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public static double getMaxOverheating() {
        return maxOverheating;
    }

}
