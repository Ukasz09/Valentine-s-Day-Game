package com.Ukasz09.ValentineGame.gameModules.levels;

import com.Ukasz09.ValentineGame.gameModules.Boundary;

import com.Ukasz09.ValentineGame.gameModules.sprites.creatures.FishMonster;
import com.Ukasz09.ValentineGame.gameModules.sprites.creatures.FishMonsterMiniboss;
import com.Ukasz09.ValentineGame.gameModules.sprites.creatures.Monster;
import com.Ukasz09.ValentineGame.gameModules.sprites.creatures.Sprite;
import com.Ukasz09.ValentineGame.gameModules.sprites.others.MoneyBag;
import com.Ukasz09.ValentineGame.gameModules.sprites.others.kickEffect.KickByBigMonster;
import com.Ukasz09.ValentineGame.gameModules.sprites.others.kickEffect.TeleportKick;
import com.Ukasz09.ValentineGame.graphicModule.texturesPath.BackgroundImages;
import com.Ukasz09.ValentineGame.graphicModule.texturesPath.SpritesImages;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.util.ArrayList;

public class Level_2 extends Levels {
    private static final Image FISH_MONSTER_LEFT_IMAGE = SpritesImages.fishMonsterLeftImage;
    private static final Image FISH_MONSTER_RIGHT_IMAGE = SpritesImages.fishMonsterRightImage;
    private static final Image FISH_MONSTER_BOTTOM_IMAGE = SpritesImages.fishMonsterBottomImage;
    private static final Image FISH_MONSTER_TOP_IMAGE = SpritesImages.fishMonsterTopImage;
    private static final Image FISH_MONSTER_MINIBOSS_LEFT_IMAGE = SpritesImages.fishMonsterMinibossLeftImage;
    private static final Image FISH_MONSTER_MINIBOSS_RIGHT_IMAGE = SpritesImages.fishMonsterMinibossRightImage;
    private static final Image FISH_MONSTER_MINIBOSS_BOTTOM_IMAGE = SpritesImages.fishMonsterMinibossBottomImage;
    private static final Image FISH_MONSTER_MINIBOSS_TOP_IMAGE = SpritesImages.fishMonsterMinibossTopImage;
    private static final Image FISH_MINIBOSS_SHIELD_IMAGE = SpritesImages.fishMinibossShieldImage;

    private static final Image MONEY_BAG_IMAGE_1 = SpritesImages.moneyBagImage1;
    private static final Image MONEY_BAG_IMAGE_2 = SpritesImages.moneyBagImage2;

    private static final Image BACKGROUND_IMAGE = BackgroundImages.backgroundImage2;

    private final int howManyMoneybags = 8;
    private final int howManySmallCoins = 5;
    private final int smallCoinValue = 50;
    private final int normalCoinValue = 100;

    private final int howManyLittleMonsters = 10;
    private final int howManyAllMonsters = howManyLittleMonsters + 1; //male potworki+boss

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public Level_2(Canvas canvas) {
        setMoneyBagImage1(MONEY_BAG_IMAGE_1);
        setMoneyBagImage2(MONEY_BAG_IMAGE_2);
        setHowManyMoneybags(howManyMoneybags);
        setSmallCoinValue(smallCoinValue);
        setNormalCoinValue(normalCoinValue);
        setHowManySmallCoins(howManySmallCoins);

        setCanvas(canvas);

        setHowManyLittleMonsters(howManyLittleMonsters);
        setHowManyAllMonsters(howManyAllMonsters);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /* Metody */

    public void spawnLittleMonsters(ArrayList<Monster> monsters) {

        for (int i = 0; i < howManyLittleMonsters; i++)
            monsters.add(new FishMonster(FISH_MONSTER_LEFT_IMAGE, FISH_MONSTER_RIGHT_IMAGE, FISH_MONSTER_BOTTOM_IMAGE, FISH_MONSTER_TOP_IMAGE, new KickByBigMonster(new TeleportKick())));
    }

    public void spawnMiniboss(ArrayList<Monster> monsters) {

        FishMonsterMiniboss miniBoss = new FishMonsterMiniboss(FISH_MONSTER_MINIBOSS_LEFT_IMAGE, FISH_MONSTER_MINIBOSS_RIGHT_IMAGE, FISH_MONSTER_MINIBOSS_BOTTOM_IMAGE, FISH_MONSTER_MINIBOSS_TOP_IMAGE, FISH_MINIBOSS_SHIELD_IMAGE, new KickByBigMonster(new TeleportKick()));

        int random = (int) (Math.random() * 2);

        //pokaz z lewej strony
        if (random == 0)
            miniBoss.setPosition(Boundary.getAtLeftBorder(canvas) - miniBoss.getWidth(), Boundary.getAtBottomBorder(canvas) / 2);
            //pokaz z prawej strony
        else
            miniBoss.setPosition(Boundary.getAtRightBorder(canvas) + miniBoss.getWidth(), Boundary.getAtBottomBorder(canvas) / 2);

        monsters.add(miniBoss);
    }

    public void setPositionMonsters(ArrayList<Monster> monsters) {

        for (Sprite m : monsters) {

            int random = (int) (Math.random() * 4 + 1);
            int spawnPositionY = (int) (Math.random() * Boundary.getAtBottomBorder(canvas));
            int spawnPositionX = (int) (Math.random() * Boundary.getAtRightBorder(canvas));

            //spawn na lewym brzegu
            if (random == 1)
                m.setPosition(Boundary.getAtLeftBorder(canvas), spawnPositionY);

            //spawn na prawym brzegu
            if (random == 2)
                m.setPosition(Boundary.getAtRightBorder(canvas), spawnPositionY);

            //spawn na gornym brzegu
            if (random == 3)
                m.setPosition(spawnPositionX, Boundary.getAtTopBorder(canvas));

            //spawn na dolnym brzegu
            if (random == 4)
                m.setPosition(spawnPositionX, Boundary.getAtBottomBorder(canvas));
        }

    }

    public boolean needToSpawnMiniboss(int collectedMoneyBags, boolean killedAllMonsters) {
        if ((collectedMoneyBags >= howManyMoneybags) && (killedAllMonsters))
            return true;

        return false;
    }

    @Override
    public void endLevel() {
        //nothing to do yet
    }

    @Override
    public void renderLevel(GraphicsContext gc, ArrayList<Monster> monsters) {
        drawBackground(gc, BACKGROUND_IMAGE);
        renderMonsters(monsters, gc);
    }

    @Override
    public void makeLevel(ArrayList<MoneyBag> moneybagList, ArrayList<Monster> monsters) {
        makeMoneyBags(moneybagList);
        spawnLittleMonsters(monsters);
        setPositionMonsters(monsters);
    }

}
