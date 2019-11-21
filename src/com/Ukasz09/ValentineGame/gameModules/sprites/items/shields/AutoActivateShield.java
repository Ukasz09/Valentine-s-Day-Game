package com.Ukasz09.ValentineGame.gameModules.sprites.items.shields;

import com.Ukasz09.ValentineGame.gameModules.utilitis.ViewManager;
import com.Ukasz09.ValentineGame.graphicModule.texturesPath.ImageSheetProperty;

public class AutoActivateShield extends Shield {

    public AutoActivateShield(ImageSheetProperty spriteSheetProperty, double spriteWidth, double spriteHeight, int shieldCooldown, int shieldDuration, ViewManager manager) {
        super(spriteSheetProperty, spriteWidth, spriteHeight, shieldCooldown, shieldDuration, manager);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @Override
    public void updateShield() {
        if (isActive()){
            reduceProtectDurationTimer();
            updateSpriteSheetFrame();
        }
        else {
            if (canBeActivated())
                activateShield();
            else reduceShieldCooldown();
        }
    }
}
