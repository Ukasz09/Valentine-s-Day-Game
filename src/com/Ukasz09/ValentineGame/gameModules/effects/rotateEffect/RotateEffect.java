package com.Ukasz09.ValentineGame.gameModules.effects.rotateEffect;

import com.Ukasz09.ValentineGame.gameModules.sprites.creatures.Creature;
import com.Ukasz09.ValentineGame.gameModules.utilitis.DirectionEnum;

public class RotateEffect {

    public static double rotateByPressedKey(boolean pressedKey_A, boolean pressedKey_D, boolean pressedKey_W, boolean pressedKey_S, double amountOfRotate) {
        double rotation = 0;

        if ((pressedKey_A && pressedKey_W) || (pressedKey_D && pressedKey_S))
            rotation = amountOfRotate;
        else if ((pressedKey_A && pressedKey_S) || (pressedKey_D && pressedKey_W))
            rotation = -amountOfRotate;
        return rotation;
    }

    public static double setRotateByAngle(Creature creatureToRotate, Creature target) {
        double rotation = Math.toDegrees(creatureToRotate.getAngleToTarget(target));
        if (rotation > 90 || rotation < -90) {
            rotation += 180;
            creatureToRotate.setImageDirection(DirectionEnum.LEFT);
        } else creatureToRotate.setImageDirection(DirectionEnum.RIGHT);

        return rotation;
    }
}
