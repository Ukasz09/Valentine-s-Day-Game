package com.Ukasz09.ValentineGame.gameModules.sprites.effects.imageByPositionEffect;

import com.Ukasz09.ValentineGame.gameModules.sprites.creatures.Monster;
import com.Ukasz09.ValentineGame.gameModules.sprites.creatures.Sprite;
import javafx.scene.image.Image;

public class PropperImageSet {

    public void byTargetPosition(Image left, Image right, Image down, Image up, Monster monsterToImageSet, Sprite target) {
        if (monsterToImageSet.isRightSideToTarget(target))
            monsterToImageSet.setActualImage(left);
        else monsterToImageSet.setActualImage(right);

        if (monsterToImageSet.isExactlyUnderOrAboveTarget(target)) {
            if (monsterToImageSet.isExactlyAboveTarget(target))
                monsterToImageSet.setActualImage(down);
            else monsterToImageSet.setActualImage(up);
        }
    }

    public void byTargetPosition(Image left, Image leftUp, Image leftBottom, Image right, Image rightUp, Image rightBottom, Image bottom, Image up, Monster monsterToImageSet, Sprite target) {
        if (monsterToImageSet.isRightSideToTarget(target)) {
            setImageBySide(leftBottom, leftUp, left, monsterToImageSet, target);
        } else if (monsterToImageSet.isLeftSideToTarget(target)) {
            setImageBySide(rightBottom, rightUp, right, monsterToImageSet, target);
        } else if (monsterToImageSet.isUpSideToTarget(target)) monsterToImageSet.setActualImage(bottom);
        else monsterToImageSet.setActualImage(up);
    }

    private void setImageBySide(Image upCorner, Image bottomCorner, Image normal, Monster monsterToImageSet, Sprite target) {
        if (monsterToImageSet.isUpSideToTarget(target))
            monsterToImageSet.setActualImage(upCorner);
        else if (monsterToImageSet.isDownSideToTarget(target))
            monsterToImageSet.setActualImage(bottomCorner);
        else monsterToImageSet.setActualImage(normal);
    }

    public void byLastDirection(Image left, Image right, Sprite sprite, String lastDirectionX) {
        if (lastDirectionX.equals("A"))
            sprite.setActualImage(left);
        else sprite.setActualImage(right);
    }

}
