package com.Ukasz09.ValentineGame.gameModules.levels;

import com.Ukasz09.ValentineGame.gameModules.sprites.creatures.Player;
import com.Ukasz09.ValentineGame.gameModules.utilitis.ViewManager;
import com.Ukasz09.ValentineGame.gameModules.sprites.items.MoneyBag;
import com.Ukasz09.ValentineGame.gameModules.sprites.creatures.Monster;
import com.Ukasz09.ValentineGame.gameModules.sprites.creatures.Sprite;

import com.Ukasz09.ValentineGame.gameModules.sprites.weapons.BombSprite;
import com.Ukasz09.ValentineGame.gameModules.sprites.weapons.BulletSprite;
import com.Ukasz09.ValentineGame.gameModules.sprites.weapons.ShotSprite;
import com.Ukasz09.ValentineGame.graphicModule.texturesPath.SpritesImages;
import com.Ukasz09.ValentineGame.soundsModule.soundsPath.Sounds;
import com.Ukasz09.ValentineGame.soundsModule.soundsPath.SoundsPlayer;
import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.Iterator;

public abstract class Levels {
    private static final double POINTS_TEXT_OFFSET_X = 450;
    private static final double POINTS_TEXT_OFFSET_Y = 70;
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

    private ViewManager manager;

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public Levels(ViewManager manager) {
        this.manager = manager;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

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

    public ViewManager getManager() {
        return manager;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /* Metody */

    public boolean defaultLevelIsEnd(Player player) {
        if ((player.getCollectedMoneyBagsOnLevel() < getHowManyMoneybags()) || (player.getKilledMonstersOnLevel() < getHowManyAllMonsters()))
            return false;
        else return true;
    }

    public void makeMoneyBags(ArrayList<MoneyBag> moneybagList) {

        for (int i = 0; i < howManyMoneybags; i++) {

            MoneyBag moneybag;

            if (i < howManySmallCoins)
                moneybag = new MoneyBag(moneyBagImage1, smallCoinValue, manager);
            else moneybag = new MoneyBag(moneyBagImage2, normalCoinValue, manager);

            double px = manager.getRightBorder() * 9 / 10 * Math.random();
            double py = manager.getBottomBorder() * 8 / 10 * Math.random();

            moneybag.setPosition(px, py);
            moneybagList.add(moneybag);
        }
    }

    public void renderMonsters(ArrayList<Monster> monsters) {
        if (monsters != null) {
            for (Sprite m : monsters)
                m.render();
        }
    }

    public void renderMoneyBags(ArrayList<MoneyBag> moneyBags) {
        if (moneyBags != null) {
            for (MoneyBag m : moneyBags)
                m.render();
        }

    }

    public void updateMonsters(Sprite target, ArrayList<Monster> monsters) {

        for (Monster m : monsters)
            m.update(target, monsters);
    }

    public abstract void makeLevel(ArrayList<MoneyBag> moneybagList, ArrayList<Monster> monsters);

    public abstract void renderLevel(ArrayList<Monster> monsters, ArrayList<MoneyBag> moneyBags, ArrayList<ShotSprite> shots, int score);

    public abstract Point2D playerStartPosition();

    protected Point2D playerDefaultStartPosition() {
        double positionX = manager.getRightBorder() / 2;
        double positionY = manager.getBottomBorder() / 2;
        return new Point2D(positionY, positionY);
    }

    public abstract void playBackgroundSound();

    public void defaultRenderLevel(ArrayList<Monster> monsters, ArrayList<MoneyBag> moneyBags, ArrayList<ShotSprite> shots, int score, Image backgroundImage) {
        drawBackground(getManager().getGraphicContext(), backgroundImage);
        renderMonsters(monsters);
        renderMoneyBags(moneyBags);
        renderScoreText(score);
        renderShots(shots);
    }

    public abstract boolean isEnd(Player player);

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
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

    public void renderShots(ArrayList<ShotSprite> shotSprites) {
        Iterator<ShotSprite> shots = shotSprites.iterator();
        while (shots.hasNext())
            shots.next().render();
    }

    public void updateShots(ArrayList<ShotSprite> shotSprites, double elapsedTime) {
        Iterator<ShotSprite> shotsIterator = shotSprites.iterator();
        while (shotsIterator.hasNext()) {
            ShotSprite shot = shotsIterator.next();
            shot.update(elapsedTime);

            if (shot.isOutOfBoundary()) {
                shot.doOutOfBoundaryAction();
                shotsIterator.remove();
            }
        }
    }

    public void renderText(String text, int posX, int posY) {
        manager.getGraphicContext().fillText(text, posX, posY);
    }

    public void renderScoreText(int score) {
        String pointsText = "Kasa na walentynki: $" + score;
        manager.getGraphicContext().setFill(Color.TAN);
        manager.getGraphicContext().fillText(pointsText, ViewManager.WIDTH - POINTS_TEXT_OFFSET_X, POINTS_TEXT_OFFSET_Y);
    }

}
