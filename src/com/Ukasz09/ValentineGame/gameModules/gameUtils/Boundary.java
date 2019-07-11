package com.Ukasz09.ValentineGame.gameModules.gameUtils;

import javafx.geometry.Bounds;
import javafx.scene.canvas.Canvas;

public class Boundary {
    private static double atRightBorder;
    private static double atLeftBorder;
    private static double atBottomBorder;
    private static double atTopBorder;

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public Boundary(Canvas canvas) {
        checkWindowBoundary(canvas);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private void checkWindowBoundary(Canvas canvas) {
        Bounds bounds = canvas.getBoundsInLocal();
        atRightBorder = bounds.getMaxX();
        atLeftBorder = bounds.getMinX();
        atBottomBorder = bounds.getMaxY();
        atTopBorder = bounds.getMinY();
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public double getAtRightBorder() {
        return atRightBorder;
    }

    public double getAtLeftBorder() {
        return atLeftBorder;
    }

    public double getAtBottomBorder() {
        return atBottomBorder;

    }

    public double getAtTopBorder() {
        return atTopBorder;
    }

}
