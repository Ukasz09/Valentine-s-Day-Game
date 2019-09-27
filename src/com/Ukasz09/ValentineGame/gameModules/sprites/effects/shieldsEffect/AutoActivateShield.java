package com.Ukasz09.ValentineGame.gameModules.sprites.effects.shieldsEffect;

import javafx.scene.image.Image;

public class AutoActivateShield extends Shield {

    public AutoActivateShield(int shieldCooldown, int shieldDuration, Image shieldImage) {
        super(shieldCooldown, shieldDuration, shieldImage);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @Override
    public void updateShield() {
        if (isActive())
            reduceProtectDurationTimer();
        else {
            if (canBeActivated())
                activateShield();
            else reduceShieldCooldown();
        }
    }
}
