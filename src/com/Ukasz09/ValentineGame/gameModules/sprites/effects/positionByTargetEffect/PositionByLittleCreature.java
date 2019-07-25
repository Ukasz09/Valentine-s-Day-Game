package com.Ukasz09.ValentineGame.gameModules.sprites.effects.positionByTargetEffect;

import com.Ukasz09.ValentineGame.gameModules.sprites.creatures.Sprite;

public class PositionByLittleCreature implements IPositionByTarget {

    @Override
    public boolean isRightSideToTarget(Sprite creature, Sprite target) {
        double creatureMinX = creature.getBoundary().getMinX();
        if (creatureMinX + 0.1 * creatureMinX > target.getBoundary().getMaxX())
            return true;

        return false;
    }

    @Override
    public boolean isLeftSideToTarget(Sprite creature, Sprite target) {
        double creatureMaxX = creature.getBoundary().getMaxX();
        if (creatureMaxX - 0.1 * creatureMaxX < target.getBoundary().getMinX())
            return true;

        return false;
    }

    @Override
    public boolean isUpSideToTarget(Sprite creature, Sprite target) {
        double creatureMaxY = creature.getBoundary().getMaxY();
        if (creatureMaxY - 0.1 * creatureMaxY < target.getBoundary().getMinY())
            return true;

        return false;
    }

    @Override
    public boolean isDownSideToTarget(Sprite creature, Sprite target) {
        double creatureMinY = creature.getBoundary().getMinY();
        if (creatureMinY + 0.1 * creatureMinY > target.getBoundary().getMaxX())
            return true;

        return false;
    }

    @Override
    public boolean isExactlyUnderOrAboveTarget(Sprite creature, Sprite target) {
        double creatureMinX = creature.getBoundary().getMinX();
        double creatureMaxX = creature.getBoundary().getMaxX();

        if ((creatureMinX > target.getBoundary().getMinX()) && (creatureMaxX < target.getBoundary().getMaxX()))
            return true;

        return false;
    }

    @Override
    public boolean isExactlyAboveTarget(Sprite creature, Sprite target) {
        double creatureMaxY = creature.getBoundary().getMaxY();

        if (isExactlyUnderOrAboveTarget(creature, target))
            if (creatureMaxY - 0.15 * creatureMaxY < target.getBoundary().getMinY())
                return true;

        return false;
    }
}
