package com.Ukasz09.ValentineGame.gameModules.utilitis;

import java.security.InvalidParameterException;

public enum DirectionEnum {
    NORTH, SOUTH, EAST, WEST;

    public static DirectionEnum getRandomDirection() {
        int directionNumber = (int) (Math.random() * 4) + 1;

        switch (directionNumber) {
            case 1:
                return NORTH;
            case 2:
                return SOUTH;
            case 3:
                return EAST;
            case 4:
                return WEST;
            default:
                return WEST;
        }
    }

    public static DirectionEnum getRandomDirection(boolean north, boolean south, boolean east, boolean west) throws InvalidParameterException {
        if (north || south || east || west) {
            int directionNumber = (int) (Math.random() * 4) + 1;
            switch (directionNumber) {
                case 1:
                    if (north)
                        return NORTH;
                    break;
                case 2:
                    if (south)
                        return SOUTH;
                    break;
                case 3:
                    if (east)
                        return EAST;
                    break;
                case 4:
                    if (west)
                        return WEST;
                    break;
            }
            //if we do not random allowed direction
            if (north) return NORTH;
            if (south) return SOUTH;
            if (east) return EAST;
            if (west) return WEST;
        }

        throw new InvalidParameterException("Dont allow to get any direction");
    }
}
