package com.Ukasz09.ValentineGame.gameModules.sprites.effects.positionByTargetEffect;

import com.Ukasz09.ValentineGame.gameModules.sprites.creatures.Sprite;

public class PositionByTarget {

    public boolean isLeftSideToTarget(Sprite creature, Sprite target) {
        double creatureMinX = creature.getBoundary().getMinX();
        if (creatureMinX <= target.getBoundary().getMinX())
            return true;

        return false;
    }

    public boolean isRightSideToTarget(Sprite creature, Sprite target) {
        return !isLeftSideToTarget(creature, target);
    }

    public boolean isUpSideToTarget(Sprite creature, Sprite target) {
        double creatureMinY = creature.getBoundary().getMinY();
        if (creatureMinY <= target.getBoundary().getMinY())
            return true;

        return false;
    }

    public boolean isDownSideToTarget(Sprite creature, Sprite target) {
        return !isUpSideToTarget(creature, target);
    }

    public boolean isExactlyUnderOrAboveTarget(Sprite creature, Sprite target) {
        double creatureMinX = creature.getBoundary().getMinX();
        double creatureMaxX = creature.getBoundary().getMaxX();

        if ((creatureMinX >= target.getBoundary().getMinX()) && (creatureMaxX <= target.getBoundary().getMaxX()))
            return true;

        return false;
    }

    public boolean isExactlyAboveTarget(Sprite creature, Sprite target) {
        if (isExactlyUnderOrAboveTarget(creature, target) && isUpSideToTarget(creature, target))
            return true;

        return false;
    }
}
