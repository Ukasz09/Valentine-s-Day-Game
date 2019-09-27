package com.Ukasz09.ValentineGame.gameModules.sprites.creatures;

import com.Ukasz09.ValentineGame.gameModules.utilitis.ViewManager;
import javafx.geometry.Rectangle2D;
import javafx.scene.SnapshotParameters;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public abstract class Sprite {
    public enum YAxisDirection {
        LEFT, RIGHT
    }

    private Image actualImage;
    private double positionX;
    private double positionY;
    private double velocityX;
    private double velocityY;
    private double width;
    private double height;
    private double lives;
    protected double maxLives;
    private double actualRotation;

    private ViewManager manager;
    private YAxisDirection imageDirection;

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public Sprite(Image actualImage, ViewManager manager) {
        this.manager = manager;
        this.actualImage = actualImage;
        width = actualImage.getWidth();
        height = actualImage.getHeight();
        lives = 3;
        maxLives = lives;
//        protectionTime = 0;
        actualRotation = 0;
        imageDirection = YAxisDirection.RIGHT;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //todo: do nieożywionych
    public void update(double time) {
        updatePosition(time);
    }

    //todo: do nieożywionych
    private void updatePosition(double time) {
        positionX += velocityX * time;
        positionY += velocityY * time;
    }

    //todo: do nieożywionych
    public void update(double time, double multiplierX, double multiplierY) {
        updatePosition(time, multiplierX, multiplierY);
    }

    //todo: do nieożywionych
    private void updatePosition(double time, double multiplierX, double multiplierY) {
        positionX += velocityX * time * multiplierX;
        positionY += velocityY * time * multiplierY;
    }

    //todo: do nieożywionych
    public void render() {
        renderSprite();
    }

    //todo: do nieożywionych
    private void renderSprite() {
        drawNormalImage();
        drawBoundaryForTests();
    }

    //todo: do nieożywionych
    private void drawNormalImage() {
        manager.getGraphicContext().drawImage(actualImage, positionX, positionY);
    }

    //TEMP
    //todo: do nieożywionych
    private void drawBoundaryForTests() {
        double tmpPosX = getBoundary().getMinX();
        double tmpPosY = getBoundary().getMinY();
        double tmpWidth = getBoundary().getWidth();
        double tmpHeight = getBoundary().getHeight();
        Paint p = new Color(0.6, 0.6, 0.6, 0.3);
        manager.getGraphicContext().setFill(p);
        manager.getGraphicContext().fillRect(tmpPosX, tmpPosY, tmpWidth, tmpHeight);
    }

    //todo: do nieożywionych
    public void renderRotatedSprite() {
        drawRotatedImage();
        drawBoundaryForTests();
    }

    //todo: do nieożywionych
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

    //todo: do nieożywionych
    private boolean needToChangeImageDirection() {
        return imageDirection.equals(YAxisDirection.LEFT);
    }

    //todo: do nieożywionych
    private boolean needToRotate() {
        return actualRotation != 0;
    }

    public float getAngleToTarget(Sprite target) {
        double dx = this.getPositionX();
        double dy = this.getPositionY();
        double diffX = target.getPositionX() - dx;
        double diffY = target.getPositionY() - dy;
        float angle = (float) Math.atan2(diffY, diffX);

        return angle;
    }

    //todo: do nieozywioncyh
    public Rectangle2D getBoundary() {
        return new Rectangle2D(positionX, positionY, width, height);
    }

    //todo: do nieozywioncyh
    public boolean intersects(Sprite s) {
        return (s.getBoundary().intersects(this.getBoundary()));
    }

    public void removeLives(double howMany) {
        lives -= howMany;
    }

    //todo: do nieozywioncyh (boundary)
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
    //todo: do nieozywioncyh
    public void setPosition(double x, double y) {
        positionX = x;
        positionY = y;
    }

    //todo: do nieozywioncyh
    public void setVelocity(double x, double y) {
        velocityX = x;
        velocityY = y;
    }

    //todo: do nieozywioncyh
    //temp
    public void addVelocity(double x, double y) {
        velocityX += x;
        velocityY += y;
    }

    //todo: do nieozywioncyh:...
    public void setLives(double lives) {
        this.lives = lives;
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

    public double getLives() {
        return lives;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    public double getMaxLives() {
        return maxLives;
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
