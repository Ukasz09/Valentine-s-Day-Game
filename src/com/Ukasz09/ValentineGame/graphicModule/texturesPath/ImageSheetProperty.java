package com.Ukasz09.ValentineGame.graphicModule.texturesPath;

import javafx.geometry.Point2D;
import javafx.scene.image.Image;

import java.security.InvalidParameterException;

public class ImageSheetProperty {
    private final Image imageSheet;
    private  KindOfState move;
    private final double widthOfOneFrame;
    private final double heightOfOneFrame;
    private final int maxAmountOfFramesInRow;

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    protected ImageSheetProperty(String imagePath, double widthOfOneFrame, double heightOfOneFrame, int maxAmountOfFramesInRow) {
        this.imageSheet = new Image(imagePath);
        this.widthOfOneFrame = widthOfOneFrame;
        this.heightOfOneFrame = heightOfOneFrame;
        move = null;
        this.maxAmountOfFramesInRow = maxAmountOfFramesInRow;
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public Image getImage() {
        return imageSheet;
    }

    // calculate offset to move from started index pixels to get maxX and maxY
    // frame which is 'amountOfFrames' counting, started from startIndex
    protected void setMove(int startedIndex, int amountOfFrames) {
        Point2D startPosOfFirstFrame = getPositionOfIndex(startedIndex);
        Point2D startPositionOfLastFrame = getPositionOfIndex(startedIndex + amountOfFrames - 1);
        move = new KindOfState(startPosOfFirstFrame.getX(), startPositionOfLastFrame.getX() + widthOfOneFrame, startPosOfFirstFrame.getY(), startPositionOfLastFrame.getY());
    }

    protected Point2D getPositionOfIndex(int index) {
        if (index < 0)
            throw new InvalidParameterException();
        if (index == 0) return new Point2D(0, 0);

        double maxXOffset, maxYOffset;
        int rowsOffset;
        int columnsOffset;

        if (index >= maxAmountOfFramesInRow)
            rowsOffset = index / maxAmountOfFramesInRow;
        else rowsOffset = 0;

        int restOfFrames = index % maxAmountOfFramesInRow;
        int freeFramesOnStartedRow = maxAmountOfFramesInRow;
        if (restOfFrames > freeFramesOnStartedRow) {
            rowsOffset++;
            columnsOffset = -(restOfFrames - freeFramesOnStartedRow);
        } else columnsOffset = restOfFrames;

        maxXOffset = widthOfOneFrame * columnsOffset;
        maxYOffset = heightOfOneFrame * rowsOffset;

        System.out.println("OFFSET: " + columnsOffset + ", " + rowsOffset);

        return new Point2D(maxXOffset, maxYOffset);
        /////
//        Point2D minOffset = calculateOffsetPosition(0, index);
//        double minX = 0 + minOffset.getX();
//        double minY = 0 + minOffset.getY();
//        return new Point2D(minX, minY);
    }

//    private Point2D calculateOffsetPosition(int startedIndex, int amountOfFrames) {
//
//
//        return new Point2D(maxXOffset, maxYOffset);
//    }

    public KindOfState getMove() {
        if (move == null)
            throw new UnsupportedOperationException();
        return move;
    }

    public double getWidthOfOneFrame() {
        return widthOfOneFrame;
    }

    public double getHeightOfOneFrame() {
        return heightOfOneFrame;
    }
}
