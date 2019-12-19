package com.Ukasz09.ValentineGame.gameModules.sprites.items.shields;

import com.Ukasz09.ValentineGame.gameModules.sprites.Sprite;
import com.Ukasz09.ValentineGame.gameModules.utilitis.ViewManager;
import com.Ukasz09.ValentineGame.graphicModule.texturesPath.ImageSheetProperty;
import com.Ukasz09.ValentineGame.graphicModule.texturesPath.FrameStatePositions;
import com.Ukasz09.ValentineGame.graphicModule.texturesPath.KindOfState;

public abstract class Shield extends Sprite {
    public static final int SHIELD_REDUCE_OFFSET = 50;

    private int protectDurationTimer;
    private int shieldCooldownTimer;
    private int defaultShieldCooldown;
    private int defaultShieldDuration;
    private FrameStatePositions actualState;

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public Shield(ImageSheetProperty spriteSheetProperty, double spriteWidth, double spriteHeight, int shieldCooldown, int shieldDuration, ViewManager manager) {
        super(spriteSheetProperty, spriteWidth, spriteHeight, manager);
        this.defaultShieldCooldown = shieldCooldown;
        this.defaultShieldDuration = shieldDuration;
        actualState=spriteSheetProperty.getAction(KindOfState.MOVE);
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
        return  (protectDurationTimer > 0);
    }

    protected boolean canBeActivated() {
        return shieldCooldownTimer == 0;
    }

    private void resetProtectDurationTimer() {
        protectDurationTimer = defaultShieldDuration;
    }

    protected void reduceProtectDurationTimer() {
        protectDurationTimer -= SHIELD_REDUCE_OFFSET/((Math.random()*2)+1);
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
