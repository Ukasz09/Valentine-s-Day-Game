package com.Ukasz09.ValentineGame.gameModules.sprites;

import com.Ukasz09.ValentineGame.gameModules.utilitis.DirectionEnum;
import com.Ukasz09.ValentineGame.gameModules.utilitis.ViewManager;
import com.Ukasz09.ValentineGame.graphicModule.texturesPath.ImageSheetProperty;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.transform.Affine;
import javafx.scene.transform.Rotate;

public abstract class Sprite {
    private ViewManager manager;

    //todo: tmp
    protected Image spriteImage;

    private double positionX;
    private double positionY;
    private double actualVelocityX;
    private double actualVelocityY;
    protected double width;
    protected double height;
    private double actualRotation;
    private DirectionEnum imageDirection;

    private double durationPerOneFrame;
    private double remainingTimeOnActualFrame;

    protected double actualFramePositionY;
    protected double actualFramePositionX;

    //todo:tmp
    protected double widthOfOneFrame;
    protected double heightOfOneFrame;

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public Sprite(ImageSheetProperty spriteSheetProperty, double spriteWidth, double spriteHeight, ViewManager manager) {
        this.spriteImage = spriteSheetProperty.getImage();
        this.width = spriteWidth;
        this.height = spriteHeight;
        this.manager = manager;
        imageDirection = DirectionEnum.RIGHT;
        actualRotation = 0;
        positionX = 0;
        positionY = 0;

        //todo: tmp
        widthOfOneFrame = spriteSheetProperty.getWidthOfOneFrame();
        heightOfOneFrame = spriteSheetProperty.getHeightOfOneFrame();
        actualFramePositionX = 0;
        actualFramePositionY = 0;

        durationPerOneFrame = 4; //todo: tmp
        remainingTimeOnActualFrame = 0;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public void setPositionOfNextFrame(double minXPosition, double maxXPosition, double minYPosition, double maxYPosition, double sheetWidth) {
        //Finished one cycle
        actualFramePositionX += widthOfOneFrame;
        if (actualFramePositionX >= maxXPosition && actualFramePositionY >= maxYPosition) {
            actualFramePositionX = minXPosition;
            actualFramePositionY = minYPosition;
        }
        //Steped out of sheet
        else if (actualFramePositionX >= sheetWidth) {
            actualFramePositionX = 0;
            actualFramePositionY += heightOfOneFrame;
        }
    }

    protected void updateSpriteSheetFrame() {
        updateRemainingTimeOnFrame();
        if (needToChangeFrame()) {
            setPositionOfNextFrame();
            restoreRemainingTimeOnFrame();
        }
    }

    private void updateRemainingTimeOnFrame() {
        remainingTimeOnActualFrame -= 1;
    }

    private void restoreRemainingTimeOnFrame() {
        remainingTimeOnActualFrame = durationPerOneFrame;
    }

    private boolean needToChangeFrame() {
        return (remainingTimeOnActualFrame <= 0);
    }

    public void update(double elapsedTime, double multiplierX, double multiplierY) {
        updatePosition(elapsedTime, multiplierX, multiplierY);
        updateSpriteSheetFrame();
    }

    //todo: dac actualState w Sprite
    protected abstract void setPositionOfNextFrame();

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
        manager.getGraphicContext().drawImage(spriteImage, actualFramePositionX, actualFramePositionY, widthOfOneFrame, heightOfOneFrame, positionX, positionY, width, height);
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
        manager.getGraphicContext().drawImage(spriteImage, actualFramePositionX, actualFramePositionY, widthOfOneFrame, heightOfOneFrame, positionX + width, positionY, -width, height);
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

    public void setDurationPerOneFrame(double durationPerOneFrame) {
        this.durationPerOneFrame = durationPerOneFrame;
    }

    //todo: tmp
    public void setActualFramePositionX(double actualFramePositionX) {
        this.actualFramePositionX = actualFramePositionX;
    }

    public void setActualFramePositionY(double actualFramePositionY) {
        this.actualFramePositionY = actualFramePositionY;
    }
}
