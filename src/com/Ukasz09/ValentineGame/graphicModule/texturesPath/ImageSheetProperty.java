package com.Ukasz09.ValentineGame.graphicModule.texturesPath;

import javafx.geometry.Point2D;
import javafx.scene.image.Image;

import java.security.InvalidParameterException;
import java.util.EnumMap;
import java.util.Map;

public class ImageSheetProperty {
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //                                               BUILDER
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public static final class Builder {
        private Image imageSheet;
        private EnumMap<KindOfState, FrameStatePositions> actionStates;
        private double widthOfOneFrame;
        private double heightOfOneFrame;
        private int maxAmountOfFramesInRow;
        private double durationPerOneFrame;

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        private Builder(int amountOfFramesInRow, double widthOfOneFrame, double heightOfOneFrame) {
            maxAmountOfFramesInRow = amountOfFramesInRow;
            this.widthOfOneFrame = widthOfOneFrame;
            this.heightOfOneFrame = heightOfOneFrame;
            durationPerOneFrame = DEFAULT_DURATION_PER_FRAME;
            actionStates = new EnumMap<>(KindOfState.class);
        }

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        public Builder withImagePath(String imagePath) {
            this.imageSheet = new Image(imagePath);
            return this;
        }

        public Builder withSizeOfOneFrame(double widthOfOneFrame, double heightOfOneFrame) {
            this.widthOfOneFrame = widthOfOneFrame;
            this.heightOfOneFrame = heightOfOneFrame;
            return this;
        }

        public Builder withDefaultDurationPerOneFrame(double defaultDurationPerOneFrame) {
            this.durationPerOneFrame = defaultDurationPerOneFrame;
            return this;
        }

        public Builder withAddActionState(KindOfState state, int startedIndex, int amountOfFrames) {
            this.actionStates.put(state, getFrameState(startedIndex, amountOfFrames));
            return this;
        }

        private FrameStatePositions getFrameState(int startedIndex, int amountOfFrames) {
            Point2D startPosOfFirstFrame = getPositionOfIndex(startedIndex);
            Point2D startPositionOfLastFrame = getPositionOfIndex(startedIndex + amountOfFrames - 1);
            FrameStatePositions frameState = new FrameStatePositions(startPosOfFirstFrame.getX(), startPositionOfLastFrame.getX() + widthOfOneFrame, startPosOfFirstFrame.getY(), startPositionOfLastFrame.getY());
            frameState.setIndexes(startedIndex, startedIndex + amountOfFrames - 1);
            return frameState;
        }

        private Point2D getPositionOfIndex(int index) {
            return ImageSheetProperty.getPositionOfIndex(index, maxAmountOfFramesInRow, widthOfOneFrame, heightOfOneFrame);
        }

        public ImageSheetProperty build() {
            ImageSheetProperty imageSheetProperty = new ImageSheetProperty();
            imageSheetProperty.imageSheet = this.imageSheet;
            imageSheetProperty.widthOfOneFrame = this.widthOfOneFrame;
            imageSheetProperty.heightOfOneFrame = this.heightOfOneFrame;
            imageSheetProperty.durationPerFrame = this.durationPerOneFrame;
            imageSheetProperty.actionStates = this.actionStates;
            imageSheetProperty.maxAmountOfFramesInRow = this.maxAmountOfFramesInRow;
            return imageSheetProperty;
        }
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //                                                FIELDS
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private static final int DEFAULT_FRAMES_IN_ROW = 10;
    private static final double DEFAULT_DURATION_PER_FRAME = 3;

    private Image imageSheet;
    private EnumMap<KindOfState, FrameStatePositions> actionStates;
    private double widthOfOneFrame;
    private double heightOfOneFrame;
    private double durationPerFrame;
    private int maxAmountOfFramesInRow;

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //                                             CONSTRUCTORS
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private ImageSheetProperty() {
        //Nothing to do...
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //                                                METHODS
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public static Builder bulider(int amountOfFramesInRow, double widthOfOneFrame, double heightOfOneFrame) {
        return new Builder(amountOfFramesInRow, widthOfOneFrame, heightOfOneFrame);
    }

    public static Builder bulider(double widthOfOneFrame, double heightOfOneFrame) {
        return new Builder(DEFAULT_FRAMES_IN_ROW, widthOfOneFrame, heightOfOneFrame);
    }

    public Point2D getPositionOfIndex(int index) {
        return getPositionOfIndex(index, maxAmountOfFramesInRow, widthOfOneFrame, heightOfOneFrame);
    }

    private static Point2D getPositionOfIndex(int index, int maxAmountOfFramesInRow, double widthOfOneFrame, double heightOfOneFrame) {
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

        return new Point2D(maxXOffset, maxYOffset);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public Image getImage() {
        return imageSheet;
    }

    public double getDurationPerFrame() {
        return durationPerFrame;
    }

    public FrameStatePositions getAction(KindOfState kindOfAction) {
        return actionStates.get(kindOfAction);
    }

    public double getWidthOfOneFrame() {
        return widthOfOneFrame;
    }

    public double getHeightOfOneFrame() {
        return heightOfOneFrame;
    }
}
