package com.Ukasz09.ValentineGame.levels;

import com.Ukasz09.ValentineGame.gameModules.Boundary;

import com.Ukasz09.ValentineGame.sprites.creatures.FishMonster;
import com.Ukasz09.ValentineGame.sprites.creatures.FishMonsterMiniboss;
import com.Ukasz09.ValentineGame.sprites.creatures.Monster;
import com.Ukasz09.ValentineGame.sprites.creatures.Sprite;
import com.Ukasz09.ValentineGame.sprites.others.MoneyBag;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;

import java.util.ArrayList;

public class Level_2 extends AllLevel{

    private final int howManyMoneybags=8;
    private final int howManySmallCoins=5;
    private final int smallCoinValue=50;
    private final int normalCoinValue=100;

    private final int howManyLittleMonsters=10;
    private final int howManyAllMonsters=howManyLittleMonsters+1; //male potworki+boss

    private Image littleMonsterLeftImage;
    private Image littleMonsterRightImage;
    private Image littleMonsterBottomImage;
    private Image littleMonsterTopImage;

    private Image minibossLeftImage;
    private Image minibossRightImage;
    private Image minibossBottomImage;
    private Image minibossTopImage;
    private Image minibossShieldImage;

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /* Konstruktory */

    public Level_2( Image moneyBagImage1, Image moneyBagImage2, Canvas canvas){

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
    /* Settery */

    public void setLittleMonsterImages(Image littleMonsterLeftImage, Image littleMonsterRightImage, Image littleMonsterBottomImage, Image littleMonsterTopImage){

        this.littleMonsterLeftImage=littleMonsterLeftImage;
        this.littleMonsterRightImage=littleMonsterRightImage;
        this.littleMonsterBottomImage=littleMonsterBottomImage;
        this.littleMonsterTopImage=littleMonsterTopImage;
    }

    public void setMinibossMonsterImages(Image minibossLeftImage, Image minibossRightImage, Image minibossBottomImage, Image minibossTopImage, Image minibossShieldImage){

        this.minibossLeftImage=minibossLeftImage;
        this.minibossRightImage=minibossRightImage;
        this.minibossBottomImage=minibossBottomImage;
        this.minibossTopImage=minibossTopImage;
        this.minibossShieldImage=minibossShieldImage;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /* Metody */

    public void spawnLittleMonsters(ArrayList<Monster> monsters){

        for (int i = 0; i< howManyLittleMonsters; i++)
            monsters.add(new FishMonster(littleMonsterLeftImage, littleMonsterRightImage, littleMonsterBottomImage, littleMonsterTopImage));
    }

    public void spawnMiniboss(ArrayList<Monster> monsters){

            FishMonsterMiniboss miniBoss=new FishMonsterMiniboss(minibossLeftImage, minibossRightImage, minibossBottomImage, minibossTopImage,minibossShieldImage);

            int random=(int)(Math.random()*2);

            //pokaz z lewej strony
            if(random==0)
                miniBoss.setPosition(Boundary.getAtLeftBorder(canvas)-miniBoss.getWidth(),Boundary.getAtBottomBorder(canvas)/2);
            //pokaz z prawej strony
            else  miniBoss.setPosition(Boundary.getAtRightBorder(canvas)+miniBoss.getWidth(),Boundary.getAtBottomBorder(canvas)/2);

            monsters.add(miniBoss);
    }

    public void setPositionMonsters(ArrayList<Monster> monsters){

        for(Sprite m: monsters){

            int random=(int)(Math.random()*4+1);
            int spawnPositionY=(int)(Math.random()* Boundary.getAtBottomBorder(canvas));
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

            //spawn na dolnym brzegu
            if(random==4)
                m.setPosition(spawnPositionX,Boundary.getAtBottomBorder(canvas));
        }

    }

    @Override
    public void makeLevel(ArrayList<MoneyBag> moneybagList, ArrayList<Monster> monsters){

        super.makeLevel(moneybagList,monsters);
        spawnLittleMonsters(monsters);
        setPositionMonsters(monsters);
    }

}
