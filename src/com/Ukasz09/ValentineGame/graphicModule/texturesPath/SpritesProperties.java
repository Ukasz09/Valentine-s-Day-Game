package com.Ukasz09.ValentineGame.graphicModule.texturesPath;

import javafx.scene.image.Image;

public class SpritesProperties {
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //                                             CREATURES
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private static ImageSheetProperty playerUkaszSheetProperty = null;

    public static ImageSheetProperty playerUkaszSheetProperty() {
        if (playerUkaszSheetProperty == null) {
            String spritePath = "\\com\\Ukasz09\\ValentineGame\\graphicModule\\texturesModel\\sprites\\ukaszAngel.png";
            ImageSheetProperty sheetProperty = new ImageSheetProperty(spritePath, 222, 262, 10);
            sheetProperty.setMove(0, 13);
            playerUkaszSheetProperty = sheetProperty;
        }

        return playerUkaszSheetProperty;
    }

    private static ImageSheetProperty littleMonsterSheetProperty = null;

    public static ImageSheetProperty littleMonsterSheetProperty() {
        if (littleMonsterSheetProperty == null) {
            String spritePath = "\\com\\Ukasz09\\ValentineGame\\graphicModule\\texturesModel\\sprites\\littleMonster1.png";
            ImageSheetProperty sheetProperty = new ImageSheetProperty(spritePath, 400, 400, 10);
            sheetProperty.setMove(0, 60);
            littleMonsterSheetProperty = sheetProperty;
        }

        return littleMonsterSheetProperty;
    }

    private static ImageSheetProperty fishMonsterSheetProperty = null;

    public static ImageSheetProperty fishMonsterSheetProperty() {
        if (fishMonsterSheetProperty == null) {
            String spritePath = "\\com\\Ukasz09\\ValentineGame\\graphicModule\\texturesModel\\sprites\\fishMonster.png";
            ImageSheetProperty sheetProperty = new ImageSheetProperty(spritePath, 318, 308, 10);
            sheetProperty.setMove(0, 75);
            fishMonsterSheetProperty = sheetProperty;
        }

        return fishMonsterSheetProperty;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //                                              ITEMS
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private static ImageSheetProperty fishBossShieldSheetProperty = null;

    public static ImageSheetProperty fishBossShieldSheetProperty() {
        if (fishBossShieldSheetProperty == null) {
            String spritePath = "\\com\\Ukasz09\\ValentineGame\\graphicModule\\texturesModel\\sprites\\fishMinibossShield.png";
            ImageSheetProperty sheetProperty = new ImageSheetProperty(spritePath, 640, 640, 10);
            sheetProperty.setMove(0, 30);
            fishBossShieldSheetProperty = sheetProperty;
        }

        return fishBossShieldSheetProperty;
    }

    private static ImageSheetProperty smallCoinSheetProperty = null;

    public static ImageSheetProperty smallCoinSheetProperty() {
        if (smallCoinSheetProperty == null) {
            String spritePath = "\\com\\Ukasz09\\ValentineGame\\graphicModule\\texturesModel\\sprites\\smallCoin.png";
            ImageSheetProperty sheetProperty = new ImageSheetProperty(spritePath, 70, 70, 10);
            sheetProperty.setMove(0, 40);
            smallCoinSheetProperty = sheetProperty;
        }

        return smallCoinSheetProperty;
    }

    private static ImageSheetProperty normalCoinSheetProperty = null;

    public static ImageSheetProperty normalCoinSheetProperty() {
        if (normalCoinSheetProperty == null) {
            String spritePath = "\\com\\Ukasz09\\ValentineGame\\graphicModule\\texturesModel\\sprites\\normalCoin.png";
            ImageSheetProperty sheetProperty = new ImageSheetProperty(spritePath, 600, 600, 10);
            sheetProperty.setMove(0, 10);
            normalCoinSheetProperty = sheetProperty;
        }

        return normalCoinSheetProperty;
    }

    private static ImageSheetProperty bigCoinSheetProperty = null;

    public static ImageSheetProperty bigCoinSheetProperty() {
        if (bigCoinSheetProperty == null) {
            String spritePath = "\\com\\Ukasz09\\ValentineGame\\graphicModule\\texturesModel\\sprites\\bigCoin.png";
            ImageSheetProperty sheetProperty = new ImageSheetProperty(spritePath, 372, 370, 10);
            sheetProperty.setMove(0, 70);
            bigCoinSheetProperty = sheetProperty;
        }

        return bigCoinSheetProperty;
    }

    private static ImageSheetProperty playerShieldSheetProperty = null;

    public static ImageSheetProperty playerShieldSheetProperty() {
        if (playerShieldSheetProperty == null) {
            String spritePath = "\\com\\Ukasz09\\ValentineGame\\graphicModule\\texturesModel\\sprites\\playerShield.png";
            ImageSheetProperty sheetProperty = new ImageSheetProperty(spritePath, 200, 198, 10);
            sheetProperty.setMove(0, 50);
            playerShieldSheetProperty = sheetProperty;
        }

        return playerShieldSheetProperty;
    }

    private static ImageSheetProperty playerShotBallProperty = null;

    public static ImageSheetProperty playerShotBallProperty() {
        if (playerShotBallProperty == null) {
            String spritePath = "\\com\\Ukasz09\\ValentineGame\\graphicModule\\texturesModel\\sprites\\iceBallSmall.png";
            ImageSheetProperty sheetProperty = new ImageSheetProperty(spritePath, 355, 206, 10);
            sheetProperty.setMove(0, 8);
            playerShotBallProperty = sheetProperty;
        }

        return playerShotBallProperty;
    }

    private static ImageSheetProperty[] playerShotBombPoperty = null;

    private static ImageSheetProperty[] playerShotBombPoperty() {
        if (playerShotBombPoperty == null) {
            String spriteStarPath = "\\com\\Ukasz09\\ValentineGame\\graphicModule\\texturesModel\\sprites\\legoStar.png";
            String spriteHeartPath = "\\com\\Ukasz09\\ValentineGame\\graphicModule\\texturesModel\\sprites\\legoHeart.png";
            ImageSheetProperty[] sheetProperty = new ImageSheetProperty[2];
            sheetProperty[0] = new ImageSheetProperty(spriteStarPath, 391, 385, 10);
            sheetProperty[1] = new ImageSheetProperty(spriteHeartPath, 381, 383, 10);
            sheetProperty[0].setMove(0, 9);
            sheetProperty[1].setMove(0, 9);
            playerShotBombPoperty = sheetProperty;
        }

        return playerShotBombPoperty;
    }

    public static ImageSheetProperty randomPlayerShotBombPoperty() {
        ImageSheetProperty[] sheetProperty = playerShotBombPoperty();
        int randOffset = (int) (Math.random() * sheetProperty.length);
        return sheetProperty[randOffset];
    }

    private static Image[] batteryImages = null;

    public static Image[] batteryImages() {
        if (batteryImages == null) {
            String batteryPrefixPath = "\\com\\Ukasz09\\ValentineGame\\graphicModule\\texturesModel\\sprites\\battery";
            Image[] newBatteryImages = new Image[5];
            for (int i = 0; i < newBatteryImages.length; i++)
                newBatteryImages[i] = new Image(batteryPrefixPath + (i + 1) + ".png");
            batteryImages = newBatteryImages;
        }

        return batteryImages;
    }

    ////
    public static Image heartFullImage = new Image(SpritesPath.HEART_FULL_PATH);
    public static Image heartHalfImage = new Image(SpritesPath.HEART_HALF_PATH);
    public static Image heartEmptyImage = new Image(SpritesPath.HEART_EMPTY_PATH);
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


}
