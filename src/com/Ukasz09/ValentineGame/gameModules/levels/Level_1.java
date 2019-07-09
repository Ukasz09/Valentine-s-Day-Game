package com.Ukasz09.ValentineGame.gameModules.levels;

import com.Ukasz09.ValentineGame.gameModules.Boundary;
import com.Ukasz09.ValentineGame.gameModules.sprites.creatures.LittleMonster;
import com.Ukasz09.ValentineGame.gameModules.sprites.others.MoneyBag;
import com.Ukasz09.ValentineGame.gameModules.sprites.creatures.Monster;
import com.Ukasz09.ValentineGame.gameModules.sprites.creatures.Sprite;

import com.Ukasz09.ValentineGame.gameModules.sprites.others.kickEffect.KickByBigMonster;
import com.Ukasz09.ValentineGame.gameModules.sprites.others.kickEffect.KickByLittleMonster;
import com.Ukasz09.ValentineGame.gameModules.sprites.others.kickEffect.TeleportKick;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;

import java.util.ArrayList;

public class Level_1 extends AllLevel{

    private final int howManyMoneybags=10;
    private final int howManySmallCoins=8;
    private final int smallCoinValue=50;
    private final int normalCoinValue=100;
    private final int howManyLittleMonsters=1;
    private final int howManyAllMonsters=howManyLittleMonsters;

    private Image littleMonsterImage;

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /* Konstruktory */

    public Level_1(Image moneyBagImage1, Image moneyBagImage2, Image littleMonsterImage, Canvas canvas){

        this.littleMonsterImage=littleMonsterImage;

        setMoneyBagImage1(moneyBagImage1);
        setMoneyBagImage2(moneyBagImage2);
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

    public void spawnLittleMonsters(ArrayList<Monster> monsters){

        for (int i=0; i<getHowManyLittleMonsters(); i++)
            monsters.add(new LittleMonster(littleMonsterImage, new KickByLittleMonster(new TeleportKick())));

    }

    public void setPositionMonsters(ArrayList<Monster> monsters){

        for(Sprite m: monsters){

            int random=(int)(Math.random()*3+1);
            int spawnPositionY=(int)(Math.random()*Boundary.getAtBottomBorder(canvas));
            int spawnPositionX=(int)(Math.random()*Boundary.getAtRightBorder(canvas));

            //spawn na lewym brzegu
            if(random==1)
                m.setPosition(Boundary.getAtLeftBorder(canvas),spawnPositionY);

            //spawn na prawym brzegu
            if(random==2)
                m.setPosition(Boundary.getAtRightBorder(canvas),spawnPositionY);

            //spawn na gornym brzegu
            if(random==3)
                m.setPosition(spawnPositionX,Boundary.getAtTopBorder(canvas));
        }

    }

    @Override
    public void makeLevel(ArrayList<MoneyBag> moneybagList, ArrayList<Monster> monsters){

        super.makeLevel(moneybagList, monsters);
        spawnLittleMonsters(monsters);
        setPositionMonsters(monsters);
    }


}
