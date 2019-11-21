package com.Ukasz09.ValentineGame.gameModules.sprites.items.shields;

import com.Ukasz09.ValentineGame.gameModules.sprites.Sprite;
import com.Ukasz09.ValentineGame.gameModules.utilitis.ViewManager;
import com.Ukasz09.ValentineGame.graphicModule.texturesPath.ImageSheetProperty;
import com.Ukasz09.ValentineGame.graphicModule.texturesPath.KindOfState;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public abstract class Shield extends Sprite {
    public static final int SHIELD_REDUCE_OFFSET = 50;

    private int protectDurationTimer;
    private int shieldCooldownTimer;

    private int defaultShieldCooldown;
    private int defaultShieldDuration;

    private KindOfState actualState;

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public Shield(ImageSheetProperty spriteSheetProperty, double spriteWidth, double spriteHeight, int shieldCooldown, int shieldDuration, ViewManager manager) {
        super(spriteSheetProperty, spriteWidth, spriteHeight, manager);
        this.defaultShieldCooldown = shieldCooldown;
        this.defaultShieldDuration = shieldDuration;
        actualState=spriteSheetProperty.getMove();
        setDurationPerOneFrame(0); //todo: TMP
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public abstract void updateShield();

    public void activateShield() {
        resetProtectDurationTimer();
        resetShieldCooldownTimer();
    }

    public void render(double positionX, double positionY) {
        setPosition(positionX,positionY);
        render();
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

    @Override
    protected void setPositionOfNextFrame() {
        setPositionOfNextFrame(actualState.getMinX(), actualState.getMaxX(), actualState.getMinY(), actualState.getMaxY(), spriteImage.getWidth());
    }
}
