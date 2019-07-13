package com.Ukasz09.ValentineGame.gameModules.sprites.weapons;

import com.Ukasz09.ValentineGame.gameModules.utilitis.ViewManager;
import com.Ukasz09.ValentineGame.gameModules.sprites.creatures.Sprite;
import com.Ukasz09.ValentineGame.graphicModule.texturesPath.SpritesImages;
import com.Ukasz09.ValentineGame.soundsModule.soundsPath.Sounds;
import com.Ukasz09.ValentineGame.soundsModule.soundsPath.SoundsPlayer;
import javafx.scene.image.Image;

public class BulletSprite extends ShotSprite {
    public static final double DEFAULT_LIVES_TAKES = 1;
    public static final double DEFAULT_MAX_OVERHEATING = 1000;
    public static final double DEFAULT_SHOT_VELOCITY = 600;

    private static final Image DEFAULT_SHOT_IMAGE = SpritesImages.playerShotImage;
    private static final SoundsPlayer SHOT_SOUND = Sounds.bulletShotSound;
    private static final double DEFAULT_SHOT_VOLUME = 0.2;
    private static double maxOverheating = DEFAULT_MAX_OVERHEATING;

    private String shotDirection;

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public BulletSprite(String shotDirection, ViewManager manager) {
        this(DEFAULT_SHOT_IMAGE, shotDirection, manager);
    }

    public BulletSprite(Image image, String shotDirection, ViewManager manager) {
        this(image, shotDirection, DEFAULT_SHOT_VELOCITY, DEFAULT_LIVES_TAKES, DEFAULT_MAX_OVERHEATING, manager);
    }

    public BulletSprite(Image image, String shotDirection, double shotVelocity, double howManyLiveTakes, double maxOverheating, ViewManager manager) {
        super(image, shotVelocity, howManyLiveTakes, manager);
        this.shotDirection = shotDirection;
        this.maxOverheating = maxOverheating;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //todo: przerobic by byla uniwersalna
    @Override
    public void setPosition(Sprite sprite) {
        double shotPositionY = sprite.getBoundary().getMinY() + 160;
        double shotPositionRightX = sprite.getBoundary().getMaxX();
        double shotPositionLeftX = sprite.getBoundary().getMinX();

        if (shotDirection.equals("D"))
            setPosition(shotPositionRightX, shotPositionY);
        else setPosition(shotPositionLeftX, shotPositionY);
    }

    @Override
    public void update(double time) {
        if (shotDirection.equals("D"))
            addPositionX(getVelocityX() * time);
        if (shotDirection.equals("A"))
            addPositionX((-1) * getVelocityX() * time);
    }

    @Override
    public void playShotSound() {
        playSound(SHOT_SOUND, DEFAULT_SHOT_VOLUME);
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

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public static double getMaxOverheating() {
        return maxOverheating;
    }

}
