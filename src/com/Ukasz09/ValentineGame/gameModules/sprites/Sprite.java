package com.Ukasz09.ValentineGame.gameModules.sprites;

import com.Ukasz09.ValentineGame.gameModules.utilitis.ViewManager;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.transform.Affine;
import javafx.scene.transform.Rotate;

public abstract class Sprite {
    private ViewManager manager;
    private Image actualImage;
    private double positionX;
    private double positionY;
    private double velocityX;
    private double velocityY;
    private double width;
    private double height;
    private double actualRotation;
    private YAxisDirection imageDirection;

    public enum YAxisDirection {
        LEFT, RIGHT
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public Sprite(Image actualImage, ViewManager manager) {
        this.actualImage = actualImage;
        this.manager = manager;
        width = actualImage.getWidth();
        height = actualImage.getHeight();
        actualRotation = 0;
        imageDirection = YAxisDirection.RIGHT;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public void update(double time) {
        updatePosition(time);
    }

    private void updatePosition(double time) {
        positionX += velocityX * time;
        positionY += velocityY * time;
    }

    public void update(double time, double multiplierX, double multiplierY) {
        updatePosition(time, multiplierX, multiplierY);
    }

    private void updatePosition(double time, double multiplierX, double multiplierY) {
        positionX += velocityX * time * multiplierX;
        positionY += velocityY * time * multiplierY;
    }

    public void render() {
        renderSpriteWithoutRotation();
    }

    private void renderSpriteWithoutRotation() {
        drawImageWithoutMirrorReflection();
        drawBoundaryForTests();
    }

    private void drawImageWithoutMirrorReflection() {
        manager.getGraphicContext().drawImage(actualImage, positionX, positionY);
    }

    //TEMP
    private void drawBoundaryForTests() {
        double tmpPosX = getBoundary().getMinX();
        double tmpPosY = getBoundary().getMinY();
        double tmpWidth = getBoundary().getWidth();
        double tmpHeight = getBoundary().getHeight();
        Paint p = new Color(0.6, 0.6, 0.6, 0.3);
        manager.getGraphicContext().setFill(p);
        manager.getGraphicContext().fillRect(tmpPosX, tmpPosY, tmpWidth, tmpHeight);
    }

    public void renderSpriteWithRotation() {
        drawImageWithRotation();
        drawBoundaryForTests();
    }

    private void drawImageWithRotation() {
        GraphicsContext gc = manager.getGraphicContext();
        boolean needToRestoreGc = false;

        if (needToRotate()) {
            double rotationCenterX = positionX + width / 2;
            double rotationCenterY = positionY + height / 2;

            gc.save();
            gc.transform(new Affine(new Rotate(actualRotation, rotationCenterX, rotationCenterY)));
            needToRestoreGc = true;
        }

        if (needToChangeImageDirection())
            drawMirrorReflectedImage();
        else drawImageWithoutMirrorReflection();

        if (needToRestoreGc)
            gc.restore();
    }

    private void drawMirrorReflectedImage(){
        manager.getGraphicContext().drawImage(actualImage, positionX + width, positionY, -width, height);
    }
    
    private boolean needToChangeImageDirection() {
        return imageDirection.equals(Sprite.YAxisDirection.LEFT);
    }

    private boolean needToRotate() {
        return actualRotation != 0;
    }

    public Rectangle2D getBoundary() {
        return new Rectangle2D(positionX+0.1*width, positionY+0.1*height, width*0.8, height*0.8);
    }

    ////////////////////////////////
    public boolean intersects(Sprite s) {
        return (s.getBoundary().intersects(this.getBoundary()));
    }

    public boolean leftSideFrameCollision() {
        double atLeftBorder = manager.getLeftBorder();
        return (this.getBoundary().getMinX() <= atLeftBorder);
    }

    public boolean boundaryCollisionFromRightSide() {
        double atRightBorder = manager.getRightBorder();
        return (this.getBoundary().getMaxX() >= atRightBorder);
    }

    public boolean boundaryCollisionFromBottom() {
        double atBottomBorder = manager.getBottomBorder();
        return (this.getBoundary().getMaxY() >= atBottomBorder);
    }

    public boolean boundaryCollisionFromTop() {
        double atTopBorder = manager.getTopBorder();
        return (this.getBoundary().getMinY() <= atTopBorder);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public void setPosition(double x, double y) {
        positionX = x;
        positionY = y;
    }

    public void setVelocity(double x, double y) {
        velocityX = x;
        velocityY = y;
    }

    //temp
    public void addVelocity(double x, double y) {
        velocityX += x;
        velocityY += y;
    }

    public ViewManager getManager() {
        return manager;
    }

    public double getPositionX() {
        return positionX;
    }

    public double getPositionY() {
        return positionY;
    }

    public double getVelocityX() {
        return velocityX;
    }

    public double getVelocityY() {
        return velocityY;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    public double getActualRotation() {
        return actualRotation;
    }

    public void setActualRotation(double actualRotation) {
        this.actualRotation = actualRotation;
    }

    public void setImageDirection(YAxisDirection imageDirection) {
        this.imageDirection = imageDirection;
    }

    public YAxisDirection getImageDirection() {
        return imageDirection;
    }
}
