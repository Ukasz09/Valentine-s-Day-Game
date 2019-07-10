package com.Ukasz09.ValentineGame.gameModules.sprites.weapons;

import com.Ukasz09.ValentineGame.gameModules.sprites.creatures.Sprite;
import com.Ukasz09.ValentineGame.soundsModule.Sounds;
import com.Ukasz09.ValentineGame.soundsModule.SoundsPlayer;
import javafx.scene.image.Image;

public class BulletSprite extends ShotSprite {
    public static final double DEFAULT_LIVES_TAKES = 1;
    public static final double DEFAULT_MAX_OVERHEATING = 1000;
    public static final double DEFAULT_SHOT_VELOCITY = 600;

    private static final SoundsPlayer SHOT_SOUND = Sounds.bulletShotSound;
    private static final double DEFAULT_SHOT_VOLUME = 0.2;
    private static double maxOverheating;
    private String shotDirection;

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public BulletSprite(Image image, String shotDirection) {
        this(image, shotDirection, DEFAULT_SHOT_VELOCITY, DEFAULT_LIVES_TAKES, DEFAULT_MAX_OVERHEATING);
    }

    public BulletSprite(Image image, String shotDirection, double shotVelocity, double howManyLiveTakes, double maxOverheating) {
        super(image, shotVelocity, howManyLiveTakes);
        this.shotDirection = shotDirection;
        this.maxOverheating = maxOverheating;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //todo: przerobic by byla uniwersalna
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
    public void playBoomSound() {
        playShotSound();
    }

    @Override
    public void playShotSound() {
        playSound(SHOT_SOUND, DEFAULT_SHOT_VOLUME);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public double getMaxOverheating() {
        return maxOverheating;
    }

}