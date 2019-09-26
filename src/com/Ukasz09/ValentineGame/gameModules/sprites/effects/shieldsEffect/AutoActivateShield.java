package com.Ukasz09.ValentineGame.gameModules.sprites.effects.shieldsEffect;

import javafx.scene.image.Image;

public class AutoActivateShield extends Shield {

    public AutoActivateShield(int shieldCooldown, int shieldDuration, Image shieldImage) {
        super(shieldCooldown, shieldDuration, shieldImage);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @Override
    public void activateShield() {
        activeProtectDuration();
        setActualShieldCooldown(getDefaultShieldCooldown());
    }

    //auto activate shield by timer
    @Override
    public void updateShield() {
        //if shield is not active
        if (!isActive()) { //==0
            if (getActualShieldCooldown() == 0)
                activateShield();
            else reduceShieldCooldown(SHIELD_REDUCE_TIME);
        } else reduceShieldDuration();
    }
}
