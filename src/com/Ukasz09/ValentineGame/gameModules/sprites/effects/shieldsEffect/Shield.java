package com.Ukasz09.ValentineGame.gameModules.sprites.effects.shieldsEffect;

import com.Ukasz09.ValentineGame.gameModules.sprites.creatures.Sprite;

import javafx.scene.image.Image;

public abstract class Shield {
    public static final int SHIELD_REDUCE_TIME = 50;

    private int protectionTime;

    private int actualShieldCooldown;
    private int defaultShieldCooldown;
    private int defaultShieldDuration;
    private Image shieldImage;
//    Sprite sprite;

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public Shield(int defaultShieldCooldown, int defaultShieldDuration, Image shieldImage) {

        this.defaultShieldCooldown = defaultShieldCooldown;
        this.defaultShieldDuration = defaultShieldDuration;
        this.shieldImage = shieldImage;
//        this.sprite = sprite;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public abstract void updateShield();

    public abstract void activateShield();

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public boolean isActive() {
        if (protectionTime > 0)
            return true;

        return false;
    }

    public void activeProtectDuration() {
//        sprite.setProtectionTime(defaultShieldDuration);
        protectionTime = defaultShieldDuration;
    }

    public void reduceShieldDuration() {
        removeProtectionTime(SHIELD_REDUCE_TIME);

        if (protectionTime < 0)
            protectionTime = 0;
    }

    private void removeProtectionTime(int value) {
        protectionTime -= value;
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
//
//    public int getProtectionTime() {
//        return protectionTime;
//    }
}
