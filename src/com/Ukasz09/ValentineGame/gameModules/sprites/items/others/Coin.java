package com.Ukasz09.ValentineGame.gameModules.sprites.items.others;

import com.Ukasz09.ValentineGame.gameModules.sprites.Sprite;
import com.Ukasz09.ValentineGame.gameModules.utilitis.ViewManager;
import com.Ukasz09.ValentineGame.graphicModule.texturesPath.ImageSheetProperty;
import com.Ukasz09.ValentineGame.graphicModule.texturesPath.KindOfState;
import com.Ukasz09.ValentineGame.soundsModule.soundsPath.SoundsPath;
import com.Ukasz09.ValentineGame.soundsModule.soundsPath.SoundsPlayer;
import javafx.scene.image.Image;

public class Coin extends Sprite {
    public static final String COLLECT_SOUND_PATH = SoundsPath.COLLECT_COIN_SOUND_PATH;
    public static final double SOUND_VOLUME = 0.1;
    private static final SoundsPlayer COLLECT_SOUND = new SoundsPlayer(COLLECT_SOUND_PATH, SOUND_VOLUME, false);
    private int value;
    private KindOfState actualState;

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public Coin(ImageSheetProperty spriteSheetProperty, double spriteWidth, double spriteHeight, int value, ViewManager manager) {
        super(spriteSheetProperty, spriteWidth, spriteHeight, manager);
        this.value = value;
        setPosition();
        actualState=spriteSheetProperty.getMove();
//        collectSound = new SoundsPlayer(COLLECT_SOUND_PATH, SOUND_VOLUME, false);
    }

//    public Coin(Image image, int value, SoundsPlayer collectSound, ViewManager manager) {
//        this(image, value, manager);
//        this.collectSound = collectSound;
//    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private void setPosition() {
        double positionX = getManager().getRightFrameBorder() * 0.9 * Math.random();
        double positionY = getManager().getBottomFrameBorder() * 0.8 * Math.random();
        this.setPosition(positionX, positionY);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public int getValue() {
        return value;
    }

    public static void playCollectSound() {
        Coin.COLLECT_SOUND.playSound();
    }

    @Override
    protected void setPositionOfNextFrame() {
        setPositionOfNextFrame(actualState.getMinX(), actualState.getMaxX(), actualState.getMinY(), actualState.getMaxY(), spriteImage.getWidth());
    }
}
