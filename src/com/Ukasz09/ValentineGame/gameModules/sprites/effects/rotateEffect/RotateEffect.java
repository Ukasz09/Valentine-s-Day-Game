package com.Ukasz09.ValentineGame.gameModules.sprites.effects.rotateEffect;

public class RotateEffect {

    public double rotateByPressedKey(boolean pressedKey_A, boolean pressedKey_D, boolean pressedKey_W, boolean pressedKey_S, double amountOfRotate) {
        double rotation = 0;

        if ((pressedKey_A && pressedKey_W) || (pressedKey_D && pressedKey_S))
            rotation = amountOfRotate;
        else if ((pressedKey_A && pressedKey_S) || (pressedKey_D && pressedKey_W))
            rotation = -amountOfRotate;

        if (pressedKey_A && pressedKey_D)
            return 0;

        return rotation;
    }

}
