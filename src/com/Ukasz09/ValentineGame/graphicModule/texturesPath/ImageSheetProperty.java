package com.Ukasz09.ValentineGame.graphicModule.texturesPath;

import javafx.scene.image.Image;

public class ImageSheetProperty {
    private Image imageSheet;
    private KindOfState move;
    private double widthOfOneFrame;
    private double heightOfOneFrame;

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    protected ImageSheetProperty(String imagePath, double widthOfOneFrame, double heightOfOneFrame) {
        this.imageSheet = new Image(imagePath);
        this.widthOfOneFrame = widthOfOneFrame;
        this.heightOfOneFrame = heightOfOneFrame;
        move = null;
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public Image getImage() {
        return imageSheet;
    }

    public void setMove(double minX, double maxX, double minY, double maxY) {
        move = new KindOfState(minX, maxX, minY, maxY);
    }

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
