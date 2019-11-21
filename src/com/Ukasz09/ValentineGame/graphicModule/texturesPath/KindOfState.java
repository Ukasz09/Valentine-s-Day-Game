package com.Ukasz09.ValentineGame.graphicModule.texturesPath;

public class KindOfState {
    private double minX;
    private double maxX;
    private double minY;
    private double maxY;

    KindOfState(double minX, double maxX, double minY, double maxY) {
        this.minX = minX;
        this.maxX = maxX;
        this.minY = minY;
        this.maxY = maxY;
    }

    public double getMinX() {
        return minX;
    }

    public double getMaxX() {
        return maxX;
    }

    public double getMinY() {
        return minY;
    }

    public double getMaxY() {
        return maxY;
    }
}