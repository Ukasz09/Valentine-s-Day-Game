package com.Ukasz09.ValentineGame.gameModules.sprites.items.weapons;

import com.Ukasz09.ValentineGame.gameModules.sprites.creatures.Monster;
import com.Ukasz09.ValentineGame.gameModules.utilitis.ViewManager;
import com.Ukasz09.ValentineGame.graphicModule.texturesPath.ImageSheetProperty;
import com.Ukasz09.ValentineGame.graphicModule.texturesPath.FrameStatePositions;
import com.Ukasz09.ValentineGame.graphicModule.texturesPath.KindOfState;
import com.Ukasz09.ValentineGame.soundsModule.soundsPath.SoundsPath;
import com.Ukasz09.ValentineGame.soundsModule.soundsPath.SoundsPlayer;

public class Bomb extends Weapon {
    private static final double DEFAULT_LIVES_TAKES = 2;
    private static final double DEFAULT_MAX_OVERHEATING = 10000;
    private static final double DEFAULT_SHOT_VELOCITY = 600;
    private static final double DEFAULT_SHOT_VOLUME = 0.5;
    private static final int DEFAULT_AMOUNT_OF_BOMB_BOOM_SOUNDS = 4;
    private static final String BOMB_BOOM_SOUND_PREFIX = SoundsPath.BOMB_BOOM_SOUND_PATH_PREFIX;
    private static final String BOMB_SHOT_SOUND_PATH = SoundsPath.BOMB_SHOT_SOUND_PATH;
    private static double maxOverheating = DEFAULT_MAX_OVERHEATING;
    private static SoundsPlayer[] bombBoomSound;
    private final SoundsPlayer shotSound;

    private static double DEFAULT_SPRITE_WIDTH = 50;
    private static double DEFAULT_SPRITE_HEIGHT = 50;

    private FrameStatePositions actualState;

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public Bomb(ImageSheetProperty spriteSheetProperty, double positionX, double positionY, ViewManager manager) {
        this(spriteSheetProperty, DEFAULT_SHOT_VELOCITY, positionX, positionY, DEFAULT_LIVES_TAKES, DEFAULT_MAX_OVERHEATING, manager);
    }

    private Bomb(ImageSheetProperty spriteSheetProperty, double shotVelocity, double positionX, double positionY, double howManyLiveTakes, double maxOverheating, ViewManager manager) {
        super(spriteSheetProperty, DEFAULT_SPRITE_WIDTH, DEFAULT_SPRITE_HEIGHT, shotVelocity, shotVelocity, positionX, positionY, howManyLiveTakes, manager);
        Bomb.maxOverheating = maxOverheating;
        bombBoomSound = getBombBoomSound(DEFAULT_AMOUNT_OF_BOMB_BOOM_SOUNDS);
        actualState = spriteSheetProperty.getAction(KindOfState.MOVE);
        shotSound = new SoundsPlayer(BOMB_SHOT_SOUND_PATH, DEFAULT_SHOT_VOLUME, false);
    }


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
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
        shotSound.playSound();
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

    @Override
    protected void setPositionOfNextFrame() {
        setPositionOfNextFrame(actualState.getMinX(), actualState.getMaxX(), actualState.getMinY(), actualState.getMaxY(), spriteImage.getWidth());
    }

    private SoundsPlayer getRandomBoomSound() {
        int random = (int) (Math.random() * bombBoomSound.length);
        return bombBoomSound[random];

    }

    public static double getMaxOverheating() {
        return maxOverheating;
    }
}
