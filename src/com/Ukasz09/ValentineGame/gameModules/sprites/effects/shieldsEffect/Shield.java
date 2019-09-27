package com.Ukasz09.ValentineGame.gameModules.sprites.effects.shieldsEffect;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public abstract class Shield {
    public static final int SHIELD_REDUCE_OFFSET = 50;

    private int protectDurationTimer;
    private int shieldCooldownTimer;

    private int defaultShieldCooldown;
    private int defaultShieldDuration;
    private Image shieldImage;

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public Shield(int shieldCooldown, int shieldDuration, Image shieldImage) {
        this.defaultShieldCooldown = shieldCooldown;
        this.defaultShieldDuration = shieldDuration;
        this.shieldImage = shieldImage;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public abstract void updateShield();

    public void activateShield() {
        resetProtectDurationTimer();
        resetShieldCooldownTimer();
    }

    public void render(double positionX, double positionY, GraphicsContext gc) {
        gc.drawImage(shieldImage, positionX, positionY);
    }

    public boolean isActive() {
        if (protectDurationTimer > 0)
            return true;
        return false;
    }

    protected boolean canBeActivated() {
        return shieldCooldownTimer == 0;
    }

    private void resetProtectDurationTimer() {
        protectDurationTimer = defaultShieldDuration;
    }

    protected void reduceProtectDurationTimer() {
        protectDurationTimer -= SHIELD_REDUCE_OFFSET;
        if (protectDurationTimer < 0)
            protectDurationTimer = 0;
    }

    private void resetShieldCooldownTimer() {
        shieldCooldownTimer = defaultShieldCooldown;
    }

    protected void reduceShieldCooldown() {
        shieldCooldownTimer -= SHIELD_REDUCE_OFFSET;
        if (shieldCooldownTimer < 0)
            shieldCooldownTimer = 0;
    }
}
