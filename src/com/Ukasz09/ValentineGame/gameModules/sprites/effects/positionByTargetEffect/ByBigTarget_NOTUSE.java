package com.Ukasz09.ValentineGame.gameModules.sprites.effects.positionByTargetEffect;

import com.Ukasz09.ValentineGame.gameModules.sprites.creatures.Sprite;

public class ByBigTarget_NOTUSE extends PositionByTarget {
    public boolean isRightSideToTarget(Sprite creature, Sprite target) {
        double creatureMinX = creature.getBoundary().getMinX();
        if (creatureMinX + creature.getWidth()/3 > target.getBoundary().getMaxX())
            return true;

        return false;
    }

    public boolean isLeftSideToTarget(Sprite creature, Sprite target) {
        double creatureMaxX = creature.getBoundary().getMaxX();
        if (creatureMaxX - creature.getWidth()/3 < target.getBoundary().getMinX())
            return true;

        return false;
    }

    public boolean isUpSideToTarget(Sprite creature, Sprite target) {
        double creatureMaxY = creature.getBoundary().getMaxY();
        if (creatureMaxY - creature.getHeight()/3 < target.getBoundary().getMinY())
            return true;

        return false;
    }

    public boolean isDownSideToTarget(Sprite creature, Sprite target) {
        double creatureMinY = creature.getBoundary().getMinY();
        if (creatureMinY + creature.getHeight()/3 > target.getBoundary().getMaxX())
            return true;

        return false;
    }

//    public boolean isExactlyUnderOrAboveTarget(Sprite creature, Sprite target) {
//        double creatureMinX = creature.getBoundary().getMinX();
//        double creatureMaxX = creature.getBoundary().getMaxX();
//
//        if ((creatureMinX > target.getBoundary().getMinX()) && (creatureMaxX < target.getBoundary().getMaxX()))
//            return true;
//
//        return false;
//    }
//
//    public boolean isExactlyAboveTarget(Sprite creature, Sprite target) {
//        double creatureMaxY = creature.getBoundary().getMaxY();
//
//        if (isExactlyUnderOrAboveTarget(creature, target))
//            if (creatureMaxY - 0.15 * creatureMaxY < target.getBoundary().getMinY())
//                return true;
//
//        return false;
//    }
}
