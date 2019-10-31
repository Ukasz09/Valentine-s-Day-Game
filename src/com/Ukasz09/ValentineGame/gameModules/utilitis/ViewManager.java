package com.Ukasz09.ValentineGame.gameModules.utilitis;

import javafx.event.EventHandler;
import javafx.geometry.Bounds;
import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.util.ArrayList;

public class ViewManager {
    public static final String DEFAULT_FONT_FAMILY = "Helvetica";
    public static final FontWeight DEFAULT_FONT_WEIGHT = FontWeight.BOLD;
    public static final int DEFAULT_FONT_SIZE = 34;
    public static final Color DEFAULT_FONT_COLOR = Color.TAN;
    public static final int DEFAULT_GAME_FRAME_WIDTH = 1600;
    public static final int HEIGHT = 900;

    private Stage mainStage;
    private Scene mainScene;
    private Canvas canvas;
    private GraphicsContext gc;

    private double rightBorder;
    private double leftBorder;
    private double bottomBorder;
    private double topBorder;

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public ViewManager() {
        //nothing to do
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * VERY IMPORTANT: initialize at first in javaFx start method, othervise it casue errors
     */
    public void initialize(String title, boolean fullScreen) {
        mainStage = new Stage();
        mainStage.setTitle(title);
        mainStage.setWidth(DEFAULT_GAME_FRAME_WIDTH);
        mainStage.setHeight(HEIGHT);
        mainStage.setFullScreen(fullScreen);
        Group root = new Group();
        mainScene = new Scene(root);
        mainStage.setScene(mainScene);
        canvas = new Canvas(mainStage.getWidth(), mainStage.getHeight());
        root.getChildren().add(canvas);
        gc = canvas.getGraphicsContext2D();

        setFillColor(DEFAULT_FONT_COLOR);
        setDefaultFont();
        checkWindowBoundary(canvas);

        //todo
//        canvas.setScaleX(1.187);
//        scale();
//        for(Node n: root.getChildren()){
//            Rectangle2D userResulution= Screen.getPrimary().getBounds();
////            n.setScaleX(1.05);
//             no
////            n.setScaleY(1.05);
//        }
        scaleToProperResolution();
    }

    public void setDefaultFont() {
        setFont(DEFAULT_FONT_FAMILY, DEFAULT_FONT_WEIGHT, DEFAULT_FONT_SIZE, DEFAULT_FONT_COLOR);
    }

    public void setFont(String family, FontWeight weight, int size, Color color) {
        try {
            Font font = Font.font(family, weight, size);
            setFont(font, color);
        } catch (Exception e) {
            setDefaultFont();
        }
    }

    private void setFont(Font font, Color color) {
        gc.setFont(font);
        gc.setFill(color);
    }

    public void setFillColor(Color color) {
        gc.setFill(color);
    }

    public void readKeyboardAction(ArrayList<String> input) {
        mainScene.setOnKeyPressed(
                new EventHandler<KeyEvent>() {
                    public void handle(KeyEvent e) {
                        String code = e.getCode().toString();
                        if (!input.contains(code))
                            input.add(code);
                    }
                });
        mainScene.setOnKeyReleased(
                new EventHandler<KeyEvent>() {
                    public void handle(KeyEvent e) {
                        String code = e.getCode().toString();
                        input.remove(code);
                    }
                });
    }

    private void checkWindowBoundary(Canvas canvas) {
        Bounds bounds = canvas.getBoundsInLocal();
        rightBorder = bounds.getMaxX();
        leftBorder = bounds.getMinX();
        bottomBorder = bounds.getMaxY();
        topBorder = bounds.getMinY();
    }

    private void scaleToProperResolution() {
        Point2D userResolution = getUserResolution();
        canvas.setScaleX(userResolution.getX() / DEFAULT_GAME_FRAME_WIDTH);
        canvas.setScaleY(userResolution.getY() / HEIGHT);
        double translateOffsetX = (userResolution.getX() - DEFAULT_GAME_FRAME_WIDTH) / 2; //todo: nie mnozyc tylko dac userResolution.x
        double translateOffsetY = (userResolution.getY() - HEIGHT) / 2;

        translateCanvas(translateOffsetX, translateOffsetY);
    }

    private Point2D getUserResolution() {
        Rectangle2D resolutionBounds = Screen.getPrimary().getBounds();
        return new Point2D(resolutionBounds.getWidth(), resolutionBounds.getHeight());
    }

    private void translateCanvas(double offsetX, double offsetY) {
        canvas.setTranslateX(offsetX);
        canvas.setTranslateY(offsetY);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public Stage getMainStage() {
        return mainStage;
    }

    public GraphicsContext getGraphicContext() {
        return gc;
    }

    public double getRightBorder() {
        return rightBorder;
    }

    public double getLeftBorder() {
        return leftBorder;
    }

    public double getBottomBorder() {
        return bottomBorder;
    }

    public double getTopBorder() {
        return topBorder;
    }

}
