package com.Ukasz09.ValentineGame.gameModules.utilitis;

public enum DirectionEnum {
    NORTH, SOUTH, EAST, WEST;

    public static DirectionEnum getDirection() {
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
}
