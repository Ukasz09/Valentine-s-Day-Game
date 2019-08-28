package com.Ukasz09.ValentineGame.gameModules.sprites.creatures;

import com.Ukasz09.ValentineGame.gameModules.utilitis.ViewManager;
import com.Ukasz09.ValentineGame.soundsModule.soundsPath.SoundsPlayer;
import javafx.geometry.Rectangle2D;
import javafx.scene.SnapshotParameters;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public abstract class Sprite {
    private Image actualImage;
    private double positionX;
    private double positionY;
    private double velocityX;
    private double velocityY;
    private double width;
    private double height;
    protected double lives;
    protected double maxLives;
    private int protectionTime;

    private SoundsPlayer hitSound;
    private SoundsPlayer deathSound;
    private SoundsPlayer missSound;

    private ViewManager manager;
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public Sprite(Image actualImage, ViewManager manager) {
        this.manager = manager;
        this.actualImage = actualImage;
        width = actualImage.getWidth();
        height = actualImage.getHeight();
        lives = 3;
        maxLives = lives;
        missSound = null;
        protectionTime = 0;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public boolean shieldIsActive() {
        if (getProtectionTime() <= 0) return false;

        return true;
    }

    public void update(double time) {
        positionX += velocityX * time;
        positionY += velocityY * time;
    }

    public void render() {
        //manager.getGraphicContext().drawImage(actualImage, positionX, positionY);
        //drawActualImage(0);

        drawNormallImage();
        drawBoundaryForTests();

    }

    public void drawActualImage(double rotate){
        if(rotate!=0) {
            ImageView iv = new ImageView(actualImage);
            iv.setRotate(rotate);
            SnapshotParameters params = new SnapshotParameters();
            params.setFill(Color.TRANSPARENT);
            Image rotatedImage = iv.snapshot(params, null);
            manager.getGraphicContext().drawImage(rotatedImage, positionX, positionY);
        } else manager.getGraphicContext().drawImage(actualImage, positionX, positionY);
    }

    //todo: na czas testow
    public void drawNormallImage(){
        manager.getGraphicContext().drawImage(actualImage, positionX, positionY);
    }


    public void drawBoundaryForTests(){
        double tmpPosX = getBoundaryForCollision().getMinX();
        double tmpPosY = getBoundaryForCollision().getMinY();
        double tmpWidth = getBoundaryForCollision().getWidth();
        double tmpHeight = getBoundaryForCollision().getHeight();
        Paint p=new Color(0.6,0.6,0.6,0.3);
        manager.getGraphicContext().setFill(p);
        manager.getGraphicContext().fillRect(tmpPosX, tmpPosY, tmpWidth, tmpHeight);
    }
    //
    public abstract Rectangle2D getBoundaryForCollision();

    public Rectangle2D getBoundary() {
        return new Rectangle2D(positionX, positionY, width, height);
    }

    public boolean intersects(Sprite s) {
        return (s.getBoundary().intersects(this.getBoundary()));
    }

    public boolean intersectsForCollision(Sprite s) {
        return (s.getBoundaryForCollision().intersects(this.getBoundaryForCollision()));
    }

    public void addPositionX(double offset) {
        positionX += offset;
    }

    public void addPositionY(double offset) {
        positionY += offset;
    }

    public void removeLives(double howMany) {
        lives -= howMany;
    }

    public void removeProtectionTime(int value) {
        protectionTime -= value;
    }

    public boolean boundaryCollisionFromLeftSide(double atLeftBorder) {
        if ((this.getBoundary().getMinX()) <= atLeftBorder) return true;
        else return false;
    }

    public boolean boundaryCollisionFromRightSide(double atRightBorder) {
        if ((this.getBoundary().getMaxX()) >= atRightBorder) return true;
        else return false;
    }

    public boolean boundaryCollisionFromBottom(double atBottomBorder) {
        if ((this.getBoundary().getMaxY()) >= atBottomBorder) return true;
        else return false;

    }

    public boolean boundaryCollisionFromTop(double atTopBorder) {
        if ((this.getBoundary().getMinY()) <= atTopBorder) return true;
        else return false;
    }


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public void setActualImage(Image actualImage) {
        this.actualImage = actualImage;
    }

    public void setPosition(double x, double y) {

        positionX = x;
        positionY = y;
    }

    public void setVelocity(double x, double y) {

        velocityX = x;
        velocityY = y;
    }

    public void addVelocity(double x, double y) {
        velocityX += x;
        velocityY += y;
    }

    public void setLives(double lives) {
        this.lives = lives;
    }

    public void setHitSound(SoundsPlayer hitSound) {
        this.hitSound = hitSound;
    }

    public void setDeathSound(SoundsPlayer deathSound) {
        this.deathSound = deathSound;
    }

    public void setMissSound(SoundsPlayer missSound) {
        this.missSound = missSound;
    }

    public void setProtectionTime(int protectionTime) {
        this.protectionTime = protectionTime;
    }

    public ViewManager getManager() {
        return manager;
    }

    public Image getActualImage() {
        return actualImage;
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

    public SoundsPlayer getHitSound() {
        return hitSound;
    }

    public SoundsPlayer getDeathSound() {
        return deathSound;
    }

    public SoundsPlayer getMissSound() {
        return missSound;
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

    public int getProtectionTime() {
        return protectionTime;
    }
}
