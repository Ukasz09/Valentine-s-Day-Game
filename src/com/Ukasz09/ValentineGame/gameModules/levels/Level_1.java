package com.Ukasz09.ValentineGame.gameModules.levels;

import com.Ukasz09.ValentineGame.gameModules.gameUtils.Game;
import com.Ukasz09.ValentineGame.gameModules.gameUtils.ViewManager;
import com.Ukasz09.ValentineGame.gameModules.sprites.creatures.LittleMonster;
import com.Ukasz09.ValentineGame.gameModules.sprites.others.MoneyBag;
import com.Ukasz09.ValentineGame.gameModules.sprites.creatures.Monster;
import com.Ukasz09.ValentineGame.gameModules.sprites.creatures.Sprite;

import com.Ukasz09.ValentineGame.gameModules.sprites.others.kickEffect.KickByLittleMonster;
import com.Ukasz09.ValentineGame.gameModules.sprites.others.kickEffect.TeleportKick;
import com.Ukasz09.ValentineGame.graphicModule.texturesPath.BackgroundImages;
import com.Ukasz09.ValentineGame.graphicModule.texturesPath.SpritesImages;
import com.Ukasz09.ValentineGame.soundsModule.Sounds;
import com.Ukasz09.ValentineGame.soundsModule.SoundsPlayer;
import javafx.scene.image.Image;

import java.util.ArrayList;

public class Level_1 extends Levels {
    private static final Image LITTLE_MONSTER_IMAGE = SpritesImages.littleMonsterImage;
    private static final Image MONEY_BAG_IMAGE_1 = SpritesImages.moneyBagImage1;
    private static final Image MONEY_BAG_IMAGE_2 = SpritesImages.moneyBagImage2;
    private static final Image BACKGROUND_IMAGE = BackgroundImages.backgroundImage1;

    private static final SoundsPlayer BACKGROUND_SOUND=Sounds.backgroundSound;
    private static final double BACKGROUND_SOUND_VOLUME = 0.1;

    private final int howManyMoneybags = 10;
    private final int howManySmallCoins = 8;
    private final int smallCoinValue = 50;
    private final int normalCoinValue = 100;
    private final int howManyLittleMonsters = 1;
    private final int howManyAllMonsters = howManyLittleMonsters;

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public Level_1(ViewManager manager) {
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

        for (int i = 0; i < getHowManyLittleMonsters(); i++){
            LittleMonster m=new LittleMonster(LITTLE_MONSTER_IMAGE, new KickByLittleMonster(new TeleportKick()),getManager());
            monsters.add(m);
        }

    }

    public void setPositionMonsters(ArrayList<Monster> monsters) {

        for (Sprite m : monsters) {

            int random = (int) (Math.random() * 3 + 1);
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
        }

    }

    @Override
    public void endLevel() {
        //nothing to do
    }

    @Override
    public void renderLevel(ArrayList<Monster> monsters) {
        drawBackground(getManager().getGraphicContext(), BACKGROUND_IMAGE);
        renderMonsters(monsters);
    }

    @Override
    public void makeLevel(ArrayList<MoneyBag> moneybagList, ArrayList<Monster> monsters) {
        makeMoneyBags(moneybagList);
        spawnLittleMonsters(monsters);
        setPositionMonsters(monsters);
        setBackgroundSound(BACKGROUND_SOUND);
        Levels.playBackgroundSound(BACKGROUND_SOUND_VOLUME,true);
        Levels.playWingsSound();

    }


}
