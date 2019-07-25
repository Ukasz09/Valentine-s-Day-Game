package com.Ukasz09.ValentineGame.gameModules.sprites.effects.positionByTargetEffect;

import com.Ukasz09.ValentineGame.gameModules.sprites.creatures.Sprite;

//todo: add position by Big creature
public interface IPositionByTarget {

    boolean isRightSideToTarget(Sprite creature, Sprite target);

    boolean isLeftSideToTarget(Sprite creature, Sprite target);

    boolean isUpSideToTarget(Sprite creature, Sprite target);

    boolean isDownSideToTarget(Sprite creature, Sprite target);

    boolean isExactlyUnderOrAboveTarget(Sprite creature, Sprite target);

    boolean isExactlyAboveTarget(Sprite creature, Sprite target);
}
