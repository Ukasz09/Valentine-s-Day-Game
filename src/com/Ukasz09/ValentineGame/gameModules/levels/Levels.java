package com.Ukasz09.ValentineGame.gameModules.levels;

import com.Ukasz09.ValentineGame.gameModules.Boundary;
import com.Ukasz09.ValentineGame.gameModules.Game;
import com.Ukasz09.ValentineGame.gameModules.sprites.others.MoneyBag;
import com.Ukasz09.ValentineGame.gameModules.sprites.creatures.Monster;
import com.Ukasz09.ValentineGame.gameModules.sprites.creatures.Sprite;

import com.Ukasz09.ValentineGame.gameModules.sprites.weapons.BombSprite;
import com.Ukasz09.ValentineGame.gameModules.sprites.weapons.BulletSprite;
import com.Ukasz09.ValentineGame.gameModules.sprites.weapons.ShotSprite;
import com.Ukasz09.ValentineGame.graphicModule.texturesPath.SpritesImages;
import com.Ukasz09.ValentineGame.soundsModule.Sounds;
import com.Ukasz09.ValentineGame.soundsModule.SoundsPlayer;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.Iterator;

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

            double px = Game.boundary.getAtRightBorder() * 9 / 10 * Math.random();
            double py = Game.boundary.getAtBottomBorder() * 8 / 10 * Math.random();

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
        double positionX = Game.boundary.getAtRightBorder() - ukasz.getMaxLives() * heartFull.getWidth();    //serca maja te sama dlugosc / wysokosc
        double positionY = Game.boundary.getAtBottomBorder() - heartFull.getHeight();

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

    protected void drawBackground(GraphicsContext gc, Image backgroundImage) {
        gc.drawImage(backgroundImage, 0, 0);
    }

    public static void playWingsSound() {
        Sounds.ukaszWingsSound.playSound(WINGS_SOUND_VOLUME, true);
    }

    public static void stopWingsSound() {
        Sounds.ukaszWingsSound.stopSound();
    }

    public static void playBackgroundSound(double soundVolume, boolean inLoop) {
        backgroundSound.playSound(soundVolume, inLoop);
    }

    public static void stopBackgroundSound() {
        backgroundSound.stopSound();
    }

    public void renderShots(ArrayList<ShotSprite> shotSprites, GraphicsContext gc) {
        Iterator<ShotSprite> shotIter = shotSprites.iterator();
        while (shotIter.hasNext())
            shotIter.next().render(gc);
    }

    public void updateShots(ArrayList<ShotSprite> shotSprites, double elapsedTime) {
        Iterator<ShotSprite> shotIter = shotSprites.iterator();
        while (shotIter.hasNext()) {
            ShotSprite shot = shotIter.next();
            shot.update(elapsedTime);

            if (shot instanceof BulletSprite)
                if ((shot.getPositionX() > Game.boundary.getAtRightBorder()) || (shot.getPositionX() < Game.boundary.getAtLeftBorder()))
                    shotIter.remove();

            if (shot instanceof BombSprite)
                if ((shot.getBoundary().getMaxY() > Game.boundary.getAtBottomBorder())) {
                    shot.playBoomSound();
                    shotIter.remove();
                }
        }
    }
}