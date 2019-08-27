package com.Ukasz09.ValentineGame.gameModules.sprites.effects.positionByTargetEffect;

import com.Ukasz09.ValentineGame.gameModules.sprites.creatures.Sprite;

public class PositionByTarget {

    public boolean isRightSideToTarget(Sprite creature, Sprite target) {
        double creatureMinX = creature.getBoundary().getMinX();
        double creatureWidth = creature.getWidth();
        if (creatureMinX + creatureWidth / 10 > target.getBoundary().getMaxX())
            return true;

        return false;
    }

    public boolean isLeftSideToTarget(Sprite creature, Sprite target) {
        double creatureMaxX = creature.getBoundary().getMaxX();
        double creatureWidth = creature.getWidth();
        if (creatureMaxX - creatureWidth / 10 < target.getBoundary().getMinX())
            return true;

        return false;
    }

    public boolean isUpSideToTarget(Sprite creature, Sprite target) {
        double creatureMaxY = creature.getBoundary().getMaxY();
        double creatureHeight = creature.getHeight();
        if (creatureMaxY - creatureHeight / 10 < target.getBoundary().getMinY())
            return true;

        return false;
    }

    public boolean isDownSideToTarget(Sprite creature, Sprite target) {
        double creatureMinY = creature.getBoundary().getMinY();
        double creatureHeight = creature.getHeight();
        if (creatureMinY + creatureHeight / 10 > target.getBoundary().getMaxX())
            return true;

        return false;
    }

    public boolean isExactlyUnderOrAboveTarget(Sprite creature, Sprite target) {
        double creatureMinX = creature.getBoundary().getMinX();
        double creatureMaxX = creature.getBoundary().getMaxX();

        if ((creatureMinX > target.getBoundary().getMinX()) && (creatureMaxX < target.getBoundary().getMaxX()))
            return true;

        return false;
    }

    public boolean isExactlyAboveTarget(Sprite creature, Sprite target) {
        double creatureMaxY = creature.getBoundary().getMaxY();
        double creatureHeight = creature.getHeight();

        if (isExactlyUnderOrAboveTarget(creature, target))
            if (creatureMaxY - creatureHeight / 10 < target.getBoundary().getMinY())
                return true;

        return false;
    }
}
