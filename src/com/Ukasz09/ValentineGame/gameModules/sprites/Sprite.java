package com.Ukasz09.ValentineGame.gameModules.sprites;

import com.Ukasz09.ValentineGame.gameModules.utilitis.DirectionEnum;
import com.Ukasz09.ValentineGame.gameModules.utilitis.ViewManager;
import com.Ukasz09.ValentineGame.graphicModule.texturesPath.ImageSheetProperty;
import com.Ukasz09.ValentineGame.graphicModule.texturesPath.KindOfState;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.transform.Affine;
import javafx.scene.transform.Rotate;

public abstract class Sprite {
    private ViewManager manager;
    protected Image spriteImage;
    private double positionX;
    private double positionY;
    private double actualVelocityX;
    private double actualVelocityY;
    private double width;
    private double height;
    private double actualRotation;
    private DirectionEnum imageDirection;
    private double durationPerOneFrame;
    private double remainingTimeOnActualFrame;
    private double actualFramePositionY;
    private double actualFramePositionX;
    private double widthOfOneFrame;
    private double heightOfOneFrame;

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
        widthOfOneFrame = spriteSheetProperty.getWidthOfOneFrame();
        heightOfOneFrame = spriteSheetProperty.getHeightOfOneFrame();
        actualFramePositionX = spriteSheetProperty.getPositionOfIndex((spriteSheetProperty.getAction(KindOfState.MOVE).getRandomIndex())).getX();
        actualFramePositionY = spriteSheetProperty.getPositionOfIndex((spriteSheetProperty.getAction(KindOfState.MOVE).getRandomIndex())).getY();
        durationPerOneFrame = spriteSheetProperty.getDurationPerFrame();
        remainingTimeOnActualFrame = 0;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    protected void setPositionOfNextFrame(double minXPosition, double maxXPosition, double minYPosition, double maxYPosition, double sheetWidth) {
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

    protected abstract void setPositionOfNextFrame();

    private void updatePosition(double time, double multiplierX, double multiplierY) {
        positionX += actualVelocityX * time * multiplierX;
        positionY += actualVelocityY * time * multiplierY;
    }

    public void render() {
        renderSpriteWithoutRotation();
    }

    private void renderSpriteWithoutRotation() {
        drawImageWithoutMirrorReflection();
    }

    private void drawImageWithoutMirrorReflection() {
        manager.getGraphicContext().drawImage(spriteImage, actualFramePositionX, actualFramePositionY, widthOfOneFrame, heightOfOneFrame, positionX, positionY, width, height);
    }

    protected void renderSpriteWithRotation() {
        drawImageWithRotation();
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

    protected void drawMirrorReflectedImage() {
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
        return (this.getBoundary().getMinY() <= 0);
    }

    public boolean downSideFrameCollision() {
        double bottomFrameBorder = manager.getBottomFrameBorder();
        return (this.getBoundary().getMaxY() >= bottomFrameBorder);
    }

    public boolean leftSideFrameCollision() {
        return (this.getBoundary().getMinX() <= 0);
    }

    public boolean rightSideFrameCollision() {
        double rightFrameBorder = manager.getRightFrameBorder();
        return (this.getBoundary().getMaxX() >= rightFrameBorder);
    }

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

    protected double getHeight() {
        return height;
    }

    protected void setActualRotation(double actualRotation) {
        this.actualRotation = actualRotation;
    }

    public boolean setImageDirection(DirectionEnum imageDirection) {
        if (imageDirection.equals(DirectionEnum.LEFT) || imageDirection.equals(DirectionEnum.RIGHT)) {
            this.imageDirection = imageDirection;
            return true;
        }
        return false;
    }

    protected DirectionEnum getImageDirection() {
        return imageDirection;
    }

    protected void setActualFramePositionX(double actualFramePositionX) {
        this.actualFramePositionX = actualFramePositionX;
    }

    protected void setActualFramePositionY(double actualFramePositionY) {
        this.actualFramePositionY = actualFramePositionY;
    }
}
