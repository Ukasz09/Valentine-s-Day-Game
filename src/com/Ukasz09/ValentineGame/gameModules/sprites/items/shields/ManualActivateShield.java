package com.Ukasz09.ValentineGame.gameModules.sprites.items.shields;

import com.Ukasz09.ValentineGame.gameModules.utilitis.ViewManager;
import com.Ukasz09.ValentineGame.graphicModule.texturesPath.ImageSheetProperty;

public class ManualActivateShield extends Shield {

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public ManualActivateShield(ImageSheetProperty spriteSheetProperty, double spriteWidth, double spriteHeight, int shieldDuration, ViewManager manager) {
        super(spriteSheetProperty, spriteWidth, spriteHeight, Integer.MAX_VALUE, shieldDuration, manager);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @Override
    public void updateShield() {
        if (isActive()){
            reduceProtectDurationTimer();
            updateSpriteSheetFrame();
        }
    }
}
