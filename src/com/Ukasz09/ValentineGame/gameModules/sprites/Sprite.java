package com.Ukasz09.ValentineGame.gameModules.sprites;

import com.Ukasz09.ValentineGame.gameModules.utilitis.DirectionEnum;
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
    protected Image spriteSheet;
    private double positionX;
    private double positionY;
    private double actualVelocityX;
    private double actualVelocityY;

    protected double width;
    protected double height;
    private double actualRotation;
    private DirectionEnum imageDirection;

    //todo:
    private double durationPerOneFrame;
    private double actualDuration;

    protected double actualFramePositionX;
    protected double actualFramePositionY;
    private boolean needToChangeFrame;
    protected double widthOfOneFrame;
    protected double heightOfOneFrame;

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public Sprite(Image spriteSheet, ViewManager manager) {
        this.spriteSheet = spriteSheet;
        width = spriteSheet.getWidth();
        height = spriteSheet.getHeight();
        this.manager = manager;
        actualRotation = 0;
        imageDirection = DirectionEnum.RIGHT;
        durationPerOneFrame = 0;
        needToChangeFrame = false;
    }

    public Sprite(Image spriteSheet, double spriteWidth, double spriteHeight, double widthOfOneFrame, double heightOfOneFrame, double durationPerOneFrame, ViewManager manager) {
        this.spriteSheet = spriteSheet;
        this.manager = manager;
        this.width = spriteWidth;
        this.height = spriteHeight;

        this.widthOfOneFrame = widthOfOneFrame;
        this.heightOfOneFrame = heightOfOneFrame;

        actualRotation = 0;
        imageDirection = DirectionEnum.RIGHT;
        actualFramePositionX = 0;
        actualFramePositionY = 0;
        this.durationPerOneFrame = durationPerOneFrame;
        needToChangeFrame = false;

        actualDuration = durationPerOneFrame;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //todo
    public void setPositionOfNextFrame(double minXPosition, double maxXPosition, double minYPosition, double maxPositionY) {
        if (!needToChangeFrame)
            return;

        actualFramePositionX += widthOfOneFrame;
        if (actualFramePositionX >= maxXPosition) {
            actualFramePositionX = minXPosition;

            if (actualFramePositionY < maxPositionY)
                actualFramePositionY += heightOfOneFrame;
            else actualFramePositionY = minYPosition;
        } else actualFramePositionX += widthOfOneFrame;
    }

    protected void updateNeedToChangeFrame(double elapsedTime) {
//        int index = (int) ((elapsedTime % (actualAmountOfFrames * durationPerOneFrame)) / durationPerOneFrame);
//        System.out.println(index);
//        if (index != actualIndex){
//            actualIndex=index;
//            needToChangeFrame=true;
//        } else needToChangeFrame=false;
        actualDuration -= 1;
        if (actualDuration <= 0) {
            needToChangeFrame = true;
            actualDuration = durationPerOneFrame;
        } else needToChangeFrame = false;
    }

//    public Image getFrameIndex(double time)
//    {
//        int index = (int)((time % (frames.length * duration)) / duration);
//        return frames[index];
//    }


    /////////

    public void update(double elapsedTime) {
        updatePosition(elapsedTime);
        updateNeedToChangeFrame(elapsedTime);
    }

    private void updatePosition(double time) {
        positionX += actualVelocityX * time;
        positionY += actualVelocityY * time;
    }

    public void update(double time, double multiplierX, double multiplierY) {
        updatePosition(time, multiplierX, multiplierY);
    }

    private void updatePosition(double time, double multiplierX, double multiplierY) {
        positionX += actualVelocityX * time * multiplierX;
        positionY += actualVelocityY * time * multiplierY;
    }

    public void render() {
        renderSpriteWithoutRotation();
//        drawBoundaryForTests();
    }

    private void renderSpriteWithoutRotation() {
        drawImageWithoutMirrorReflection();
        drawBoundaryForTests();
    }

    private void drawImageWithoutMirrorReflection() {
        manager.getGraphicContext().drawImage(spriteSheet, positionX, positionY);
    }

    //TEMP
    //todo: tmp public
    public void drawBoundaryForTests() {
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

    private void drawMirrorReflectedImage() {
        manager.getGraphicContext().drawImage(spriteSheet, positionX + width, positionY, -width, height);
    }

    private boolean needToChangeImageDirection() {
        return imageDirection.equals(DirectionEnum.LEFT);
    }

    private boolean needToRotate() {
        return actualRotation != 0;
    }

    public Rectangle2D getBoundary() {
        return new Rectangle2D(positionX, positionY, width, height);
    }

    ////////////////////////////////
    public boolean intersects(Sprite s) {
        return (s.getBoundary().intersects(this.getBoundary()));
    }

    public boolean frameCollision(DirectionEnum side) {
        switch (side) {
            case UP:
                return (topSideFrameCollision());
            case DOWN:
                return (downSideFrameCollision());
            case LEFT:
                return (leftSideFrameCollision());
            case RIGHT:
                return (rightSideFrameCollision());
        }
        return false;
    }

    public boolean topSideFrameCollision() {
        double topFrameBorder = manager.getTopFrameBorder();
        return (this.getBoundary().getMinY() <= topFrameBorder);
    }

    public boolean downSideFrameCollision() {
        double bottomFrameBorder = manager.getBottomFrameBorder();
        return (this.getBoundary().getMaxY() >= bottomFrameBorder);
    }

    public boolean leftSideFrameCollision() {
        double leftFrameBorder = manager.getLeftFrameBorder();
        return (this.getBoundary().getMinX() <= leftFrameBorder);
    }

    public boolean rightSideFrameCollision() {
        double rightFrameBorder = manager.getRightFrameBorder();
        return (this.getBoundary().getMaxX() >= rightFrameBorder);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public void setPosition(double x, double y) {
        positionX = x;
        positionY = y;
    }

    public void setActualVelocity(double x, double y) {
        actualVelocityX = x;
        actualVelocityY = y;
    }

    public void addActualVelocity(DirectionEnum axis, double value) {
        switch (axis) {
            case LEFT:
                actualVelocityX -= value;
                break;
            case RIGHT:
                actualVelocityX += value;
                break;
            case UP:
                actualVelocityY -= value;
                break;
            case DOWN:
                actualVelocityY += value;
                break;
        }
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

    public double getActualVelocityX() {
        return actualVelocityX;
    }

    public double getActualVelocityY() {
        return actualVelocityY;
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

    public boolean setImageDirection(DirectionEnum imageDirection) {
        if (imageDirection.equals(DirectionEnum.LEFT) || imageDirection.equals(DirectionEnum.RIGHT)) {
            this.imageDirection = imageDirection;
            return true;
        }

        return false;
    }


    public DirectionEnum getImageDirection() {
        return imageDirection;
    }

    public void setSpriteSheet(Image spriteSheet) {
        this.spriteSheet = spriteSheet;
    }


}
