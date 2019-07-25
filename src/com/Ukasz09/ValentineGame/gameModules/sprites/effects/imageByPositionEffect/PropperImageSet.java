package com.Ukasz09.ValentineGame.gameModules.sprites.effects.imageByPositionEffect;

import com.Ukasz09.ValentineGame.gameModules.sprites.creatures.Monster;
import com.Ukasz09.ValentineGame.gameModules.sprites.creatures.Sprite;
import javafx.scene.image.Image;

public class PropperImageSet {

    public void byTargetPosition(Image left, Image right, Image bottom, Image top, Monster monsterToImageSet, Sprite target) {
        if (monsterToImageSet.isRightSideToTarget(target))
            monsterToImageSet.setActualImage(left);
        else monsterToImageSet.setActualImage(right);

        if (monsterToImageSet.isExactlyUnderOrAboveTarget(target)) {
            if (monsterToImageSet.isExactlyAboveTarget(target))
                monsterToImageSet.setActualImage(bottom);
            else monsterToImageSet.setActualImage(top);
        }
    }

    public void byLastDirection(Image left, Image right, Sprite sprite, String lastDirectionX) {
        if (lastDirectionX.equals("A"))
            sprite.setActualImage(left);
        else sprite.setActualImage(right);
    }

}
