package com.Ukasz09.ValentineGame.gameModules.sprites.items.others;

import com.Ukasz09.ValentineGame.gameModules.sprites.Sprite;
import com.Ukasz09.ValentineGame.gameModules.utilitis.ViewManager;
import com.Ukasz09.ValentineGame.gameModules.sprites.creatures.Creature;
import com.Ukasz09.ValentineGame.soundsModule.soundsPath.SoundsPath;
import com.Ukasz09.ValentineGame.soundsModule.soundsPath.SoundsPlayer;
import javafx.scene.image.Image;

public class Coin extends Sprite {
    public static final String COLLECT_SOUND_PATH = SoundsPath.COLLECT_COIN_SOUND_PATH;
    public static final double SOUND_VOLUME = 0.1;
    private static final SoundsPlayer COLLECT_SOUND = new SoundsPlayer(COLLECT_SOUND_PATH, SOUND_VOLUME, false);
    private int value;

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public Coin(Image image, int value, ViewManager manager) {
        super(image, manager);
        this.value = value;
//        collectSound = new SoundsPlayer(COLLECT_SOUND_PATH, SOUND_VOLUME, false);
    }

//    public Coin(Image image, int value, SoundsPlayer collectSound, ViewManager manager) {
//        this(image, value, manager);
//        this.collectSound = collectSound;
//    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public void setPosition() {
        double positionX = getManager().getRightBorder() * 0.9 * Math.random();
        double positionY = getManager().getBottomBorder() * 0.8 * Math.random();
        this.setPosition(positionX, positionY);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public int getValue() {
        return value;
    }

    public static void playCollectSound() {
        Coin.COLLECT_SOUND.playSound();
    }

}
