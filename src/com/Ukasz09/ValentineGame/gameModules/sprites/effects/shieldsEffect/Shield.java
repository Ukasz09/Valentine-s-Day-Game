package com.Ukasz09.ValentineGame.gameModules.sprites.effects.shieldsEffect;

import com.Ukasz09.ValentineGame.gameModules.sprites.creatures.Sprite;

import javafx.scene.image.Image;

public abstract class Shield {
    public static final int SHIELD_REDUCE_TIME = 50;

    private int actualShieldCooldown;
    private int defaultShieldCooldown;
    private int defaultShieldDuration;
    private Image shieldImage;
    Sprite sprite;

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public Shield(int defaultShieldCooldown, int defaultShieldDuration, Image shieldImage, Sprite sprite) {

        this.defaultShieldCooldown = defaultShieldCooldown;
        this.defaultShieldDuration = defaultShieldDuration;
        this.shieldImage = shieldImage;
        this.sprite = sprite;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public abstract void updateShield();

    public abstract void activateShield();

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public void activeProtectDuration(Sprite sprite) {
        sprite.setProtectionTime(defaultShieldDuration);
    }

    public void reduceShieldDuration(Sprite sprite) {
        sprite.removeProtectionTime(SHIELD_REDUCE_TIME);

        if (sprite.getProtectionTime() < 0)
            sprite.setProtectionTime(0);
    }

    public void reduceShieldCooldown(int value) {
        actualShieldCooldown -= value;

        if (actualShieldCooldown < 0)
            actualShieldCooldown = 0;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public int getActualShieldCooldown() {
        return actualShieldCooldown;
    }

    public int getDefaultShieldCooldown() {
        return defaultShieldCooldown;
    }

    public void setActualShieldCooldown(int actualShieldCooldown) {
        this.actualShieldCooldown = actualShieldCooldown;
    }

    public Image getShieldImage() {
        return shieldImage;
    }
}
