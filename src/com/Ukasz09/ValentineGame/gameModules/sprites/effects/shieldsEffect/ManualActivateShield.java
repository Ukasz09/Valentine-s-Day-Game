package com.Ukasz09.ValentineGame.gameModules.sprites.effects.shieldsEffect;

import javafx.scene.image.Image;

public class ManualActivateShield extends Shield {

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public ManualActivateShield(int shieldDuration, Image shieldImage) {
        super(Integer.MAX_VALUE, shieldDuration, shieldImage);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @Override
    public void updateShield() {
        if (isActive())
            reduceProtectDurationTimer();
    }
}
