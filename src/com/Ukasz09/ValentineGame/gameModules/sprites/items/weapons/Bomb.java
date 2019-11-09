package com.Ukasz09.ValentineGame.gameModules.sprites.items.weapons;

import com.Ukasz09.ValentineGame.gameModules.sprites.creatures.Monster;
import com.Ukasz09.ValentineGame.gameModules.utilitis.ViewManager;
import com.Ukasz09.ValentineGame.graphicModule.texturesPath.SpritesImages;
import com.Ukasz09.ValentineGame.soundsModule.soundsPath.SoundsPath;
import com.Ukasz09.ValentineGame.soundsModule.soundsPath.SoundsPlayer;

import javafx.scene.image.Image;

public class Bomb extends Weapon {
    private static final double DEFAULT_LIVES_TAKES = 2;
    private static final double DEFAULT_MAX_OVERHEATING = 10000;
    private static final double DEFAULT_SHOT_VELOCITY = 600;
    private static final double DEFAULT_SHOT_VOLUME = 0.5;
    private static final int DEFAULT_AMOUNT_OF_BOMB_BOOM_SOUNDS = 4;
    private static final String BOMB_BOOM_SOUND_PREFIX = SoundsPath.BOMB_BOOM_SOUND_PATH_PREFIX;
    private static final String BOMB_SHOT_SOUND_PATH = SoundsPath.BOMB_SHOT_SOUND_PATH;
    private static final SoundsPlayer SHOT_SOUND = new SoundsPlayer(BOMB_SHOT_SOUND_PATH, DEFAULT_SHOT_VOLUME, false);
    private static final Image[] DEFAULT_BOMB_IMAGES = SpritesImages.getUkaszBombShotImages();
    private static double maxOverheating = DEFAULT_MAX_OVERHEATING;
    private static SoundsPlayer[] bombBoomSound;

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public Bomb(double positionX, double positionY, ViewManager manager) {
        this(getRandomDefaultImage(), DEFAULT_SHOT_VELOCITY, positionX, positionY, DEFAULT_LIVES_TAKES, DEFAULT_MAX_OVERHEATING, manager);
    }

    public Bomb(Image image, double shotVelocity, double positionX, double positionY, double howManyLiveTakes, double maxOverheating, ViewManager manager) {
        super(image, shotVelocity, shotVelocity, positionX, positionY, howManyLiveTakes, manager);
        this.maxOverheating = maxOverheating;
        bombBoomSound = getBombBoomSound(DEFAULT_AMOUNT_OF_BOMB_BOOM_SOUNDS);
    }


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private static Image getRandomDefaultImage() {
        return (DEFAULT_BOMB_IMAGES[(int) (Math.random() * 2)]);
    }

    @Override
    public void update(double elapsedTime) {
        update(elapsedTime, 0, 1);
    }

    private void playBoomSound() {
        SoundsPlayer boomShot = getRandomBoomSound();
        if (boomShot != null)
            boomShot.playSound();
        else System.out.println("Error: getRandomBoomSound return null");

    }

    public void playShotSound() {
        Bomb.SHOT_SOUND.playSound();
    }

    @Override
    public boolean isOutOfBoundary() {
        if ((getBoundary().getMaxY() > getManager().getBottomFrameBorder()))
            return true;

        return false;
    }

    @Override
    public void doOutOfBoundaryAction() {
        playBoomSound();
    }

    @Override
    public void hitMonster(Monster monster) {
        playBoomSound();
        monster.removeLives(getHowManyLivesTake());
    }

    private SoundsPlayer[] getBombBoomSound(int amountOfSounds) {
        SoundsPlayer[] boomSound = new SoundsPlayer[amountOfSounds];
        for (int i = 0; i < amountOfSounds; i++)
            boomSound[i] = new SoundsPlayer(BOMB_BOOM_SOUND_PREFIX + i + ".mp3", DEFAULT_SHOT_VOLUME, false);

        return boomSound;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private SoundsPlayer getRandomBoomSound() {
        int random = (int) (Math.random() * bombBoomSound.length);
        return bombBoomSound[random];

    }

    public static double getMaxOverheating() {
        return maxOverheating;
    }
}
