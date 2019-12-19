package com.Ukasz09.ValentineGame.graphicModule.texturesPath;

public class FrameStatePositions {
    private double minX;
    private double maxX;
    private double minY;
    private double maxY;
    private int startIndex;
    private int endIndex;

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public FrameStatePositions(double minX, double maxX, double minY, double maxY) {
        this.minX = minX;
        this.maxX = maxX;
        this.minY = minY;
        this.maxY = maxY;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
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

    protected void setIndexes(int startIndex, int endIndex) {
        this.startIndex = startIndex;
        this.endIndex = endIndex;
    }

    public int getRandomIndex() {
        int range = (endIndex - startIndex) + 1;
        return (int) (Math.random() * range) + startIndex;
    }

}