package com.Ukasz09.ValentineGame.gameModules.levels;

import com.Ukasz09.ValentineGame.gameModules.Boundary;
import com.Ukasz09.ValentineGame.gameModules.sprites.others.MoneyBag;
import com.Ukasz09.ValentineGame.gameModules.sprites.creatures.Monster;
import com.Ukasz09.ValentineGame.gameModules.sprites.creatures.Sprite;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.util.ArrayList;

public abstract class AllLevel {

    private int howManyMoneybags;
    private Image moneyBagImage1;
    private Image moneyBagImage2;

    private int smallCoinValue;
    private int normalCoinValue;
    private int howManySmallCoins;

    private int howManyLittleMonsters;   //potworki
    private int howManyAllMonsters;      //potworki + (bossy / minibossy)

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

    public void makeLevel(ArrayList<MoneyBag> moneybagList, ArrayList<Monster> monsters) {
        makeMoneyBags(moneybagList);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /* Metody Statyczne */

    public static void drawHearts(GraphicsContext gc, Canvas canvas, Sprite ukasz, Image heartFull, Image heartHalf, Image heartEmpty) {

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

    public static void drawBattery(GraphicsContext gc, Canvas canvas, double overheating, double maxOverheating, Image[] batteryImages) {

        double overheatingPercents = overheating / maxOverheating * 100;
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

}
