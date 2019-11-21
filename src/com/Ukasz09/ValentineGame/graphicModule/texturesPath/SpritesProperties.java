package com.Ukasz09.ValentineGame.graphicModule.texturesPath;

import javafx.scene.image.Image;

public class SpritesProperties {
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //                                             CREATURES
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public static CreatureSheetProperty playerUkaszSheetProperty() {
        String spritePath = "\\com\\Ukasz09\\ValentineGame\\graphicModule\\texturesModel\\sprites\\ukaszAngel.png";
        CreatureSheetProperty sheetProperty = new CreatureSheetProperty(spritePath, 222, 262);
        sheetProperty.setMove(0, 666, 0, 262);
        return sheetProperty;
    }

    public static CreatureSheetProperty littleMonsterSheetProperty() {
        String spritePath = "\\com\\Ukasz09\\ValentineGame\\graphicModule\\texturesModel\\sprites\\littleMonster1.png";
        CreatureSheetProperty sheetProperty = new CreatureSheetProperty(spritePath, 400, 400);
        sheetProperty.setMove(0, 4000, 0, 2000);
        return sheetProperty;
    }

    public static CreatureSheetProperty fishMonsterSheetProperty() {
        String spritePath = "\\com\\Ukasz09\\ValentineGame\\graphicModule\\texturesModel\\sprites\\fishMonster.png";
        CreatureSheetProperty sheetProperty = new CreatureSheetProperty(spritePath, 318, 308);
        sheetProperty.setMove(0, 1590, 0, 2156);
        return sheetProperty;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //                                              ITEMS
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public static ImageSheetProperty fishBossShieldSheetProperty() {
        String spritePath = "\\com\\Ukasz09\\ValentineGame\\graphicModule\\texturesModel\\sprites\\fishMinibossShield.png";
        ImageSheetProperty sheetProperty = new ImageSheetProperty(spritePath, 640, 640);
        sheetProperty.setMove(0, 6400, 0, 1280);
        return sheetProperty;
    }

    public static ImageSheetProperty smallCoinSheetProperty() {
        String spritePath = "\\com\\Ukasz09\\ValentineGame\\graphicModule\\texturesModel\\sprites\\smallCoin.png";
        ImageSheetProperty sheetProperty = new ImageSheetProperty(spritePath, 70, 70);
        sheetProperty.setMove(0, 700, 0, 210);
        return sheetProperty;
    }

    public static ImageSheetProperty normalCoinSheetProperty() {
        String spritePath = "\\com\\Ukasz09\\ValentineGame\\graphicModule\\texturesModel\\sprites\\normalCoin.png";
        ImageSheetProperty sheetProperty = new ImageSheetProperty(spritePath, 600, 600);
        sheetProperty.setMove(0, 6000, 0, 0);
        return sheetProperty;
    }

    public static ImageSheetProperty bigCoinSheetProperty() {
        String spritePath = "\\com\\Ukasz09\\ValentineGame\\graphicModule\\texturesModel\\sprites\\bigCoin.png";
        ImageSheetProperty sheetProperty = new ImageSheetProperty(spritePath, 390, 390);
        sheetProperty.setMove(0, 390, 0, 5850);
        return sheetProperty;
    }

    public static ImageSheetProperty playerShieldSheetProperty() {
        String spritePath = "\\com\\Ukasz09\\ValentineGame\\graphicModule\\texturesModel\\sprites\\playerShield.png";
        ImageSheetProperty sheetProperty = new ImageSheetProperty(spritePath, 200, 198);
        sheetProperty.setMove(0, 2000, 0, 792);
        return sheetProperty;
    }

    public static ImageSheetProperty playerShotBallProperty() {
        String spritePath = "\\com\\Ukasz09\\ValentineGame\\graphicModule\\texturesModel\\sprites\\iceBallSmall.png";
        ImageSheetProperty sheetProperty = new ImageSheetProperty(spritePath, 355, 206);
        sheetProperty.setMove(0, 2840, 0, 0);
        return sheetProperty;
    }

    private static ImageSheetProperty[] playerShotBombPoperty() {
        String spriteStarPath = "\\com\\Ukasz09\\ValentineGame\\graphicModule\\texturesModel\\sprites\\legoStar.png";
        String spriteHeartPath = "\\com\\Ukasz09\\ValentineGame\\graphicModule\\texturesModel\\sprites\\legoHeart.png";
        ImageSheetProperty[] sheetProperty = new ImageSheetProperty[2];
        sheetProperty[0] = new ImageSheetProperty(spriteStarPath, 391, 385);
        sheetProperty[1] = new ImageSheetProperty(spriteHeartPath, 381, 383);
        sheetProperty[0].setMove(0, 3519, 0, 0);
        sheetProperty[1].setMove(0, 3429, 0, 0);
        return sheetProperty;
    }

    public static ImageSheetProperty randomPlayerShotBombPoperty(){
        ImageSheetProperty[] sheetProperty=playerShotBombPoperty();
        int randOffset=(int) (Math.random()*sheetProperty.length);
        return sheetProperty[randOffset];
    }

    public static Image[] batteryImages() {
        String batteryPrefixPath = "\\com\\Ukasz09\\ValentineGame\\graphicModule\\texturesModel\\sprites\\battery";
        Image[] batteryImages = new Image[5];
        for (int i = 0; i < batteryImages.length; i++)
            batteryImages[i] = new Image(batteryPrefixPath + (i + 1) + ".png");
        return batteryImages;
    }

    ////
    public static Image heartFullImage = new Image(SpritesPath.HEART_FULL_PATH);
    public static Image heartHalfImage = new Image(SpritesPath.HEART_HALF_PATH);
    public static Image heartEmptyImage = new Image(SpritesPath.HEART_EMPTY_PATH);
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


}
