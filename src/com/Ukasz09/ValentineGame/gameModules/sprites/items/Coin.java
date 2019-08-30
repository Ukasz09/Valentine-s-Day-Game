package com.Ukasz09.ValentineGame.gameModules.sprites.items;

import com.Ukasz09.ValentineGame.gameModules.utilitis.ViewManager;
import com.Ukasz09.ValentineGame.gameModules.sprites.creatures.Sprite;
import com.Ukasz09.ValentineGame.soundsModule.soundsPath.Sounds;
import com.Ukasz09.ValentineGame.soundsModule.soundsPath.SoundsPlayer;
import javafx.scene.image.Image;

public class Coin extends Sprite {

    private static final SoundsPlayer DEFAULT_COLLECT_SOUND = Sounds.collectMoneySound;
    private SoundsPlayer collectSound;
    private double soundVolume;

    private int value;

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public Coin(Image image, int value, ViewManager manager) {
        super(image, manager);
        this.value = value;
        collectSound = DEFAULT_COLLECT_SOUND;
        soundVolume = 0.1;
    }

    public Coin(Image image, int value, SoundsPlayer collectSound, double soundVolume, ViewManager manager) {

        this(image, value, manager);
        this.collectSound = collectSound;
        this.soundVolume = soundVolume;
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

    public void setValue(int value) {
        this.value = value;
    }

    public void playCollectSound() {
        collectSound.playSound(soundVolume, false);
    }

}
