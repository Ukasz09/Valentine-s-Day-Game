package com.Ukasz09.ValentineGame.gameModules.sprites.effects.positionByTargetEffect;

import com.Ukasz09.ValentineGame.gameModules.sprites.creatures.Sprite;

public class PositionByTarget {

    public boolean isLeftSideToTarget(Sprite creature, Sprite target) {
        double creatureMinX = creature.getBoundaryForCollision().getMinX();
        if (creatureMinX <= target.getBoundaryForCollision().getMinX())
            return true;

        return false;
    }

    public boolean isRightSideToTarget(Sprite creature, Sprite target) {
        return !isLeftSideToTarget(creature, target);
    }

    public boolean isUpSideToTarget(Sprite creature, Sprite target) {
        double creatureMinY = creature.getBoundaryForCollision().getMinY();
        if (creatureMinY <= target.getBoundaryForCollision().getMinY())
            return true;

        return false;
    }

    public boolean isDownSideToTarget(Sprite creature, Sprite target) {
        return !isUpSideToTarget(creature, target);
    }

    public boolean isExactlyUnderOrAboveTarget(Sprite creature, Sprite target) {
        double creatureMinX = creature.getBoundaryForCollision().getMinX();
        double creatureMaxX = creature.getBoundaryForCollision().getMaxX();

        if ((creatureMinX >= target.getBoundaryForCollision().getMinX()) && (creatureMaxX <= target.getBoundaryForCollision().getMaxX()))
            return true;

        return false;
    }

    public boolean isExactlyAboveTarget(Sprite creature, Sprite target) {
        if (isExactlyUnderOrAboveTarget(creature, target) && isUpSideToTarget(creature, target))
            return true;

        return false;
    }
}
