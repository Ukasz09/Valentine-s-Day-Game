package com.Ukasz09.ValentineGame.gameModules.sprites.effects.shieldsEffect;

import com.Ukasz09.ValentineGame.gameModules.sprites.creatures.Sprite;

import javafx.scene.image.Image;

public class ManualActivateShield extends Shield {

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public ManualActivateShield(int shieldCooldown, int shieldDuration, Image shieldImage) {
        super(shieldCooldown, shieldDuration, shieldImage);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @Override
    public void activateShield() {
        activeProtectDuration();
        setActualShieldCooldown(Integer.MAX_VALUE);
    }

    //shieldCooldown: 0 - active, Integer.MAX_VALUE - not active (overheating)
    @Override
    public void updateShield() {
        if (getActualShieldCooldown() == 0)
            activateShield();

        if (isActive())
            reduceShieldDuration();
    }
}
