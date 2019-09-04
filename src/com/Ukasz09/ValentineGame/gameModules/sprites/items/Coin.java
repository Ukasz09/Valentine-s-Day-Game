package com.Ukasz09.ValentineGame.gameModules.sprites.items;

import com.Ukasz09.ValentineGame.gameModules.utilitis.ViewManager;
import com.Ukasz09.ValentineGame.gameModules.sprites.creatures.Sprite;
import com.Ukasz09.ValentineGame.soundsModule.soundsPath.SoundsPath;
import com.Ukasz09.ValentineGame.soundsModule.soundsPath.SoundsPlayer;
import javafx.scene.image.Image;

public class Coin extends Sprite {

    private static final String COLLECT_SOUND_PATH = SoundsPath.COLLECT_COIN_SOUND_PATH;
    private static final double SOUND_VOLUME = 0.1;
    private SoundsPlayer collectSound;
    private int value;

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public Coin(Image image, int value, ViewManager manager) {
        super(image, manager);
        this.value = value;
        collectSound = new SoundsPlayer(COLLECT_SOUND_PATH, SOUND_VOLUME, false);
    }

    public Coin(Image image, int value, SoundsPlayer collectSound, ViewManager manager) {

        this(image, value, manager);
        this.collectSound = collectSound;
    }

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

    public void playCollectSound() {
        collectSound.playSound(SOUND_VOLUME, false);
    }

}
