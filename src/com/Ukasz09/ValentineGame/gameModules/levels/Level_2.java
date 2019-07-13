package com.Ukasz09.ValentineGame.gameModules.levels;

import com.Ukasz09.ValentineGame.gameModules.utilitis.ViewManager;
import com.Ukasz09.ValentineGame.gameModules.sprites.creatures.FishMonster;
import com.Ukasz09.ValentineGame.gameModules.sprites.creatures.FishMonsterMiniboss;
import com.Ukasz09.ValentineGame.gameModules.sprites.creatures.Monster;
import com.Ukasz09.ValentineGame.gameModules.sprites.creatures.Sprite;
import com.Ukasz09.ValentineGame.gameModules.sprites.items.MoneyBag;
import com.Ukasz09.ValentineGame.gameModules.sprites.effects.kickEffect.KickByBigMonster;
import com.Ukasz09.ValentineGame.gameModules.sprites.effects.kickEffect.TeleportKick;
import com.Ukasz09.ValentineGame.graphicModule.texturesPath.BackgroundImages;
import com.Ukasz09.ValentineGame.graphicModule.texturesPath.SpritesImages;
import javafx.geometry.Point2D;
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
    public Level_2(ViewManager manager) {
        super(manager);
        setMoneyBagImage1(MONEY_BAG_IMAGE_1);
        setMoneyBagImage2(MONEY_BAG_IMAGE_2);
        setHowManyMoneybags(howManyMoneybags);
        setSmallCoinValue(smallCoinValue);
        setNormalCoinValue(normalCoinValue);
        setHowManySmallCoins(howManySmallCoins);
        setHowManyLittleMonsters(howManyLittleMonsters);
        setHowManyAllMonsters(howManyAllMonsters);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /* Metody */

    public void spawnLittleMonsters(ArrayList<Monster> monsters) {

        for (int i = 0; i < howManyLittleMonsters; i++) {
            FishMonster m = new FishMonster(FISH_MONSTER_LEFT_IMAGE, FISH_MONSTER_RIGHT_IMAGE, FISH_MONSTER_BOTTOM_IMAGE, FISH_MONSTER_TOP_IMAGE, new KickByBigMonster(new TeleportKick()), getManager());
            monsters.add(m);
        }
    }

    public void spawnMiniboss(ArrayList<Monster> monsters) {

        FishMonsterMiniboss miniBoss = new FishMonsterMiniboss(FISH_MONSTER_MINIBOSS_LEFT_IMAGE, FISH_MONSTER_MINIBOSS_RIGHT_IMAGE, FISH_MONSTER_MINIBOSS_BOTTOM_IMAGE, FISH_MONSTER_MINIBOSS_TOP_IMAGE, FISH_MINIBOSS_SHIELD_IMAGE, new KickByBigMonster(new TeleportKick()), getManager());

        int random = (int) (Math.random() * 2);

        //pokaz z lewej strony
        if (random == 0)
            miniBoss.setPosition(getManager().getLeftBorder() - miniBoss.getWidth(), getManager().getBottomBorder() / 2);
            //pokaz z prawej strony
        else
            miniBoss.setPosition(getManager().getRightBorder() + miniBoss.getWidth(), getManager().getBottomBorder() / 2);

        monsters.add(miniBoss);
    }

    public void setPositionMonsters(ArrayList<Monster> monsters) {

        for (Sprite m : monsters) {

            int random = (int) (Math.random() * 4 + 1);
            int spawnPositionY = (int) (Math.random() * getManager().getBottomBorder());
            int spawnPositionX = (int) (Math.random() * getManager().getRightBorder());

            //spawn na lewym brzegu
            if (random == 1)
                m.setPosition(getManager().getLeftBorder(), spawnPositionY);

            //spawn na prawym brzegu
            if (random == 2)
                m.setPosition(getManager().getRightBorder(), spawnPositionY);
            //spawn na gornym brzegu
            if (random == 3)
                m.setPosition(spawnPositionX, getManager().getTopBorder());

            //spawn na dolnym brzegu
            if (random == 4)
                m.setPosition(spawnPositionX, getManager().getBottomBorder());
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
    public void renderLevel(ArrayList<Monster> monsters, int score) {
        drawBackground(getManager().getGraphicContext(), BACKGROUND_IMAGE);
        renderMonsters(monsters);
        renderScoreText(score);
    }

    @Override
    public void makeLevel(ArrayList<MoneyBag> moneybagList, ArrayList<Monster> monsters) {
        makeMoneyBags(moneybagList);
        spawnLittleMonsters(monsters);
        setPositionMonsters(monsters);
    }

    @Override
    public Point2D playerStartPosition() {
        return playerDefaultStartPosition();
    }

    @Override
    public void playBackgroundSound() {
        setBackgroundSound(Level_1.BACKGROUND_SOUND);
        Levels.playBackgroundSound(Level_1.BACKGROUND_SOUND_VOLUME,true);
    }
}
