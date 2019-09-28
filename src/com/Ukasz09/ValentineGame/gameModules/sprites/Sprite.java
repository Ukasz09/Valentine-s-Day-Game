package com.Ukasz09.ValentineGame.gameModules.sprites;

import com.Ukasz09.ValentineGame.gameModules.utilitis.ViewManager;
import javafx.geometry.Rectangle2D;
import javafx.scene.SnapshotParameters;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

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

    public Sprite(Image actualImage, ViewManager manager) {
        this.actualImage = actualImage;
        this.manager = manager;
        width = actualImage.getWidth();
        height = actualImage.getHeight();
        actualRotation = 0;
        imageDirection = YAxisDirection.RIGHT;
    }

    public enum YAxisDirection {
        LEFT, RIGHT
    }

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
        renderSprite();
    }

    private void renderSprite() {
        drawNormalImage();
        drawBoundaryForTests();
    }

    private void drawNormalImage() {
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

    public void renderRotatedSprite() {
        drawRotatedImage();
        drawBoundaryForTests();
    }

    private void drawRotatedImage() {
        if (needToRotate() || needToChangeImageDirection()) {
            ImageView iv = new ImageView(actualImage);
            if (needToChangeImageDirection())
                iv.setScaleX(-1);
            if (needToRotate())
                iv.setRotate(actualRotation);

            SnapshotParameters params = new SnapshotParameters();
            params.setFill(Color.TRANSPARENT);
            Image rotatedImage = iv.snapshot(params, null);
            manager.getGraphicContext().drawImage(rotatedImage, positionX, positionY);
        } else drawNormalImage();
    }

    private boolean needToChangeImageDirection() {
        return imageDirection.equals(Sprite.YAxisDirection.LEFT);
    }

    private boolean needToRotate() {
        return actualRotation != 0;
    }

    public Rectangle2D getBoundary() {
        return new Rectangle2D(positionX, positionY, width, height);
    }

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
