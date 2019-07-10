package com.Ukasz09.ValentineGame.gameModules.levels;

import com.Ukasz09.ValentineGame.gameModules.Boundary;
import com.Ukasz09.ValentineGame.gameModules.sprites.others.MoneyBag;
import com.Ukasz09.ValentineGame.gameModules.sprites.creatures.Monster;
import com.Ukasz09.ValentineGame.gameModules.sprites.creatures.Sprite;

import com.Ukasz09.ValentineGame.gameModules.sprites.weapons.BombSprite;
import com.Ukasz09.ValentineGame.graphicModule.texturesPath.SpritesImages;
import com.Ukasz09.ValentineGame.soundsModule.Sounds;
import com.Ukasz09.ValentineGame.soundsModule.SoundsPlayer;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.util.ArrayList;

public abstract class Levels {

    private static final double WINGS_SOUND_VOLUME = 1;
    private static SoundsPlayer backgroundSound;

    private int howManyMoneybags;
    private Image moneyBagImage1;
    private Image moneyBagImage2;

    private int smallCoinValue;
    private int normalCoinValue;
    private int howManySmallCoins;

    private int howManyLittleMonsters;   //potworki
    private int howManyAllMonsters;      //potworki + (bossy / minibossy)

    private Image heartFull = SpritesImages.heartFullImage;
    private Image heartHalf = SpritesImages.heartHalfImage;
    private Image heartEmpty = SpritesImages.heartEmptyImage;
    private Image[] batteryImages=SpritesImages.getBatteryImages();

    Canvas canvas;

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /* Settery */

    public void setHowManyMoneybags(int howManyMoneybags) {
        this.howManyMoneybags = howManyMoneybags;
    }

    public void setSmallCoinValue(int smallCoinValue) {
        this.smallCoinValue = smallCoinValue;
    }

    public void setNormalCoinValue(int normalCoinValue) {
        this.normalCoinValue = normalCoinValue;
    }

    public void setHowManyLittleMonsters(int howManyLittleMonsters) {
        this.howManyLittleMonsters = howManyLittleMonsters;
    }

    public void setHowManyAllMonsters(int howManyAllMonsters) {
        this.howManyAllMonsters = howManyAllMonsters;
    }

    public void setMoneyBagImage1(Image moneyBagImage1) {
        this.moneyBagImage1 = moneyBagImage1;
    }

    public void setMoneyBagImage2(Image moneyBagImage2) {
        this.moneyBagImage2 = moneyBagImage2;
    }

    public void setCanvas(Canvas canvas) {
        this.canvas = canvas;
    }

    public void setHowManySmallCoins(int howManySmallCoins) {
        this.howManySmallCoins = howManySmallCoins;
    }

    public static void setBackgroundSound(SoundsPlayer backgroundSound) {
        Levels.backgroundSound = backgroundSound;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /* Gettery */

    public int getHowManyMoneybags() {
        return howManyMoneybags;
    }

    public int getHowManyLittleMonsters() {
        return howManyLittleMonsters;
    }

    public int getHowManyAllMonsters() {
        return howManyAllMonsters;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /* Metody */

    public void makeMoneyBags(ArrayList<MoneyBag> moneybagList) {

        for (int i = 0; i < howManyMoneybags; i++) {

            MoneyBag moneybag;

            if (i < howManySmallCoins)
                moneybag = new MoneyBag(moneyBagImage1, smallCoinValue);
            else moneybag = new MoneyBag(moneyBagImage2, normalCoinValue);

            double px = Boundary.getAtRightBorder(canvas) * 9 / 10 * Math.random();
            double py = Boundary.getAtBottomBorder(canvas) * 8 / 10 * Math.random();

            moneybag.setPosition(px, py);
            moneybagList.add(moneybag);
        }
    }

    public void renderMonsters(ArrayList<Monster> monsters, GraphicsContext gc) {

        for (Sprite m : monsters)
            m.render(gc);
    }

    public void updateMonsters(Sprite target, ArrayList<Monster> monsters) {

        for (Monster m : monsters)
            m.update(target, monsters);
    }

    public abstract void makeLevel(ArrayList<MoneyBag> moneybagList, ArrayList<Monster> monsters);

    public abstract void endLevel();

    public abstract void renderLevel(GraphicsContext gc, ArrayList<Monster> monsters);

    public void drawHearts(GraphicsContext gc, Canvas canvas, Sprite ukasz) {

        double tmpLives = ukasz.getLives();
        double positionX = Boundary.getAtRightBorder(canvas) - ukasz.getMaxLives() * heartFull.getWidth();    //serca maja te sama dlugosc / wysokosc
        double positionY = Boundary.getAtBottomBorder(canvas) - heartFull.getHeight();

        for (int i = 0; i < ukasz.getMaxLives(); i++) {

            //rysuj polowke
            if (tmpLives == 0.5) {

                gc.drawImage(heartHalf, positionX, positionY);
                tmpLives = 0;
            }

            //rysuj cale
            else if (tmpLives > 0) {

                gc.drawImage(heartFull, positionX, positionY);
                tmpLives--;
            }

            //rysuj puste
            else gc.drawImage(heartEmpty, positionX, positionY);

            positionX += heartFull.getWidth();

        }

    }

    public void drawBattery(GraphicsContext gc, Canvas canvas, double overheating) {

        double overheatingPercents = overheating / BombSprite.getMaxOverheating() * 100;
        double batteryPositionX = Boundary.getAtLeftBorder(canvas);
        double batteryPositionY = Boundary.getAtBottomBorder(canvas) - batteryImages[0].getHeight();

        //cala bateria
        if (overheatingPercents == 0) {
            gc.drawImage(batteryImages[4], batteryPositionX, batteryPositionY);
        }

        //4 kreski
        else if (overheatingPercents < 40) {
            gc.drawImage(batteryImages[3], batteryPositionX, batteryPositionY);
        }

        //3 kreski
        else if (overheatingPercents < 60) {
            gc.drawImage(batteryImages[2], batteryPositionX, batteryPositionY);
        }

        //2 kreski
        else if (overheatingPercents < 80) {
            gc.drawImage(batteryImages[1], batteryPositionX, batteryPositionY);
        }

        //1 kreska
        else gc.drawImage(batteryImages[0], batteryPositionX, batteryPositionY);

    }

    protected void drawBackground(GraphicsContext gc, Image backgroundImage) {
        gc.drawImage(backgroundImage, 0, 0);
    }

    public static void playWingsSound(){
        Sounds.ukaszWingsSound.playSound(WINGS_SOUND_VOLUME,true);
    }

    public static void stopWingsSound(){
        Sounds.ukaszWingsSound.stopSound();
    }

    public static void playBackgroundSound(double soundVolume, boolean inLoop) {
        backgroundSound.playSound(soundVolume, inLoop);
    }

    public static void stopBackgroundSound() {
        backgroundSound.stopSound();
    }

}
