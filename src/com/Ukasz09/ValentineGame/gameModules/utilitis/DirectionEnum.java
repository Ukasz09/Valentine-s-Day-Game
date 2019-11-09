package com.Ukasz09.ValentineGame.gameModules.utilitis;

import java.lang.reflect.Array;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

//todo: usunac geographic
public enum DirectionEnum {
    LEFT("A"), RIGHT("D"), UP("W"), DOWN("S");

    private static final int AMOUNT_OF_DEFINED_DIRECTION = 4;
    private static final Random RANDOM = new Random();
    String keypadEquivalent;

    DirectionEnum(String keypadEquivalent) {
        this.keypadEquivalent = keypadEquivalent;
    }

    public static DirectionEnum getRandomDirection() {
        return getRandomDirection(true, true, true, true);
    }

    public static DirectionEnum getRandomDirection(boolean north, boolean south, boolean east, boolean west) throws InvalidParameterException {
        int directionNumber = RANDOM.nextInt(AMOUNT_OF_DEFINED_DIRECTION);
        switch (directionNumber) {
            case 0:
                if (north)
                    return UP;
            case 1:
                if (south)
                    return DOWN;
            case 2:
                if (east)
                    return LEFT;
            case 3:
                if (west)
                    return RIGHT;
        }

        //if we do not random allowed direction
        if (east) return LEFT;
        if (west) return RIGHT;
        if (south) return DOWN;
        return UP;
    }
}

