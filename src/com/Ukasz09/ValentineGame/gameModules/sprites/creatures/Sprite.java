package com.Ukasz09.ValentineGame.gameModules.sprites.creatures;

import com.Ukasz09.ValentineGame.soundsModule.SoundsPlayer;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

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

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /* Konstruktory */

    public Sprite(Image actualImage) {

        this.actualImage = actualImage;
        width = actualImage.getWidth();
        height = actualImage.getHeight();
        lives = 3;
        maxLives = lives;
        missSound = null;
        protectionTime = 0;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /* Settery */

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

    public void setMaxLives(double maxLives) {
        this.maxLives = maxLives;
    }

    public void setProtectionTime(int protectionTime) {
        this.protectionTime = protectionTime;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /* Gettery */

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

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /* Metody */

    public void update(double time) {

        positionX += velocityX * time;
        positionY += velocityY * time;
    }

    public void render(GraphicsContext gc) {

        gc.drawImage(actualImage, positionX, positionY);
        //gc.fillRect(positionX,positionY,getWidth(),getHeight());
    }

    //pobiera prostokat do ustalania kolizji
    public Rectangle2D getBoundary() {

        return new Rectangle2D(positionX, positionY, width, height);
    }

    //pobiera pomniejszony prostokat (korekta do nie nachodzenia na siebie postaci, lepszego lapania hita przez potwora)
    public Rectangle2D getBoundaryForMonster() {

        return new Rectangle2D(positionX + 40, positionY + 40, width - 40, height - 40);
    }

    public boolean intersects(Sprite s) {

        return (s.getBoundary().intersects(this.getBoundary()));
    }

    //zwraca kolizyjnosc ze zmniejszonymi granicami obrazka
    public boolean intersectsWithMonster(Sprite s) {

        return (s.getBoundaryForMonster().intersects(this.getBoundary()));
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

}
