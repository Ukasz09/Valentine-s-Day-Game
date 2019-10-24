/*
    Dla wielokratkowych spritow
    Tymczasowo nieuzywana
 */

package com.Ukasz09.ValentineGame.tmp;

public class AnimatedSprite_NOTUSING {

//    private Image[] frames;
//    private int lastFrameId;
//
//    private double positionX;
//    private double positionY;
//    private double velocityX;
//    private double width;
//    private double height;
//    private String updateDirection;
//
//    private double lives;
//    private double maxLives;
//    private SoundsPlayer hitSound;
//    private SoundsPlayer deathSound;
//
//    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//    /* Konstruktor */
//
//    public AnimatedSprite_NOTUSING(Image[] frames, String updateDirection){
//
//        this.frames=frames;
//        lastFrameId=-1;
//
//        width=frames[0].getWidth();
//        height=frames[0].getHeight();
//
//        this.updateDirection=updateDirection;
//
//        lives=3;
//        maxLives=lives;
//    }
//
//
//    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//    /* Settery */
//
//    public void setPosition(double x, double y){
//
//        positionX=x;
//        positionY=y;
//    }
//
//    public void setVelocity(double x){
//
//        velocityX=x;
//    }
//
//    public void addVelocity(double x){
//        velocityX+=x;
//    }
//
//
//    public void setLives(double lives) {
//        this.lives = lives;
//    }
//
//    public void setHitSound(SoundsPlayer hitSound) {
//        this.hitSound = hitSound;
//    }
//
//    public void setDeathSound(SoundsPlayer deathSound) {
//        this.deathSound = deathSound;
//    }
//
//    public void setMaxLives(double maxLives) {
//        this.maxLives = maxLives;
//    }
//
//    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//    /* Gettery */
//
//    public Image getNextFrame()
//    {
//        lastFrameId++;
//
//        if(lastFrameId>=frames.length)
//            lastFrameId=0;
//
//        return frames[lastFrameId];
//    }
//
//    public double getPositionX() {
//        return positionX;
//    }
//
//    public double getPositionY() {
//        return positionY;
//    }
//
//
//    public double getLives() {
//        return lives;
//    }
//
//    public SoundsPlayer getHitSound() {
//        return hitSound;
//    }
//
//    public SoundsPlayer getDeathSound() {
//        return deathSound;
//    }
//
//    public double getWidth() {
//        return width;
//    }
//
//    public double getHeight() {
//        return height;
//    }
//
//    public double getMaxLives() {
//        return maxLives;
//    }
//
//
//    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//    /* Metody */
//
//    public void update(double time){
//
//        if(updateDirection.equals("D"))
//            positionX+=velocityX*time;
//        if(updateDirection.equals("A"))
//            positionX-=velocityX*time;
//    }
//
//    public void render(GraphicsContext gc){
//
//        gc.drawImage(getNextFrame(),getPositionX(),getPositionY());
//    }
//
//    public Rectangle2D getBoundary(){
//
//        return new Rectangle2D(positionX, positionY,width,height);
//    }
//    //pobiera pomniejszony prostokat (korekta do nie nachodzenia na siebie postaci)
//    public Rectangle2D getBoundaryForMonster(){
//
//        return new Rectangle2D(positionX+30,positionY+30,width-30,height-30);
//    }
//
//    public boolean intersects(Sprite s) {
//
//        return (s.getBoundary().intersects(this.getBoundary()));
//    }
//
////    //zwraca kolizyjnosc ze zmniejszonymi granicami obrazka
////    public boolean intersectsWithMonster(Sprite s){
////
////        return (s.getBoundaryForMonster().intersects(this.getBoundary()));
////    }
//
//    public void addPositionX(double offset){
//        positionX+=offset;
//    }
//
//    public void addPositionY(double offset){
//        positionY+=offset;
//    }
//
//    public void removeLives(double howMany){
//
//        lives-=howMany;
//
//    }
}
