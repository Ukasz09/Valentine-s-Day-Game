package com.Ukasz09.ValentineGame.gameModules.sprites.effects.shieldsEffect;

import com.Ukasz09.ValentineGame.gameModules.sprites.creatures.Sprite;

import javafx.scene.image.Image;

public class AutoActivateShield extends Shield {

    public AutoActivateShield(int shieldCooldown, int shieldDuration, Image shieldImage, Sprite sprite) {
        super(shieldCooldown, shieldDuration, shieldImage, sprite);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @Override
    public void activateShield() {
        activeProtectDuration(sprite);
        setActualShieldCooldown(getDefaultShieldCooldown());
    }

    //auto activate shield by timer
    @Override
    public void updateShield() {
        //if shield is not active
        if (sprite.getProtectionTime() == 0) {
            if (getActualShieldCooldown() == 0)
                activateShield();
            else reduceShieldCooldown(SHIELD_REDUCE_TIME);
        } else reduceShieldDuration(sprite);
    }
}
