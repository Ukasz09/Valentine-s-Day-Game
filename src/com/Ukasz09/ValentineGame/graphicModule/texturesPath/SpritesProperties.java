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
            ImageSheetProperty sheetProperty = ImageSheetProperty.bulider(10,256,256)
                    .withImagePath(spritePath)
                    .withSizeOfOneFrame(256, 256)
                    .withDefaultDurationPerOneFrame(5)
                    .withAddActionState(KindOfState.MOVE, 0, 13)
                    .build();
            playerUkaszSheetProperty = sheetProperty;
        }

        return playerUkaszSheetProperty;
    }

    private static ImageSheetProperty littleMonsterSheetProperty = null;

    public static ImageSheetProperty littleMonsterSheetProperty() {
        if (littleMonsterSheetProperty == null) {
            String spritePath = "\\com\\Ukasz09\\ValentineGame\\graphicModule\\texturesModel\\sprites\\littleMonster1.png";
            ImageSheetProperty sheetProperty = ImageSheetProperty.bulider(10,256,256)
                    .withImagePath(spritePath)
                    .withSizeOfOneFrame(256, 256)
                    .withDefaultDurationPerOneFrame(1)
                    .withAddActionState(KindOfState.MOVE, 0, 60)
                    .build();
            littleMonsterSheetProperty = sheetProperty;
        }

        return littleMonsterSheetProperty;
    }

    private static ImageSheetProperty fishMonsterSheetProperty = null;

    public static ImageSheetProperty fishMonsterSheetProperty() {
        if (fishMonsterSheetProperty == null) {
            String spritePath = "\\com\\Ukasz09\\ValentineGame\\graphicModule\\texturesModel\\sprites\\fishMonster.png";
            ImageSheetProperty sheetProperty = ImageSheetProperty.bulider(10,256,256)
                    .withImagePath(spritePath)
                    .withSizeOfOneFrame(256, 256)
                    .withDefaultDurationPerOneFrame(2)
                    .withAddActionState(KindOfState.MOVE, 0, 75)
                    .build();
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
            ImageSheetProperty sheetProperty = ImageSheetProperty.bulider(10,256,256)
                    .withImagePath(spritePath)
                    .withSizeOfOneFrame(256, 256)
                    .withDefaultDurationPerOneFrame(0)
                    .withAddActionState(KindOfState.MOVE, 0, 30)
                    .build();
            fishBossShieldSheetProperty = sheetProperty;
        }

        return fishBossShieldSheetProperty;
    }

    private static ImageSheetProperty smallCoinSheetProperty = null;

    public static ImageSheetProperty smallCoinSheetProperty() {
        if (smallCoinSheetProperty == null) {
            String spritePath = "\\com\\Ukasz09\\ValentineGame\\graphicModule\\texturesModel\\sprites\\smallCoin.png";
            ImageSheetProperty sheetProperty = ImageSheetProperty.bulider(10,64,64)
                    .withImagePath(spritePath)
                    .withSizeOfOneFrame(64, 64)
                    .withDefaultDurationPerOneFrame(4)
                    .withAddActionState(KindOfState.MOVE, 0, 40)
                    .build();
            smallCoinSheetProperty = sheetProperty;
        }

        return smallCoinSheetProperty;
    }

    private static ImageSheetProperty normalCoinSheetProperty = null;

    public static ImageSheetProperty normalCoinSheetProperty() {
        if (normalCoinSheetProperty == null) {
            String spritePath = "\\com\\Ukasz09\\ValentineGame\\graphicModule\\texturesModel\\sprites\\normalCoin.png";
            ImageSheetProperty sheetProperty = ImageSheetProperty.bulider(10,64,64)
                    .withImagePath(spritePath)
                    .withSizeOfOneFrame(64, 64)
                    .withDefaultDurationPerOneFrame(4)
                    .withAddActionState(KindOfState.MOVE, 0, 10)
                    .build();
            normalCoinSheetProperty = sheetProperty;
        }

        return normalCoinSheetProperty;
    }

    private static ImageSheetProperty bigCoinSheetProperty = null;

    public static ImageSheetProperty bigCoinSheetProperty() {
        if (bigCoinSheetProperty == null) {
            String spritePath = "\\com\\Ukasz09\\ValentineGame\\graphicModule\\texturesModel\\sprites\\bigCoin.png";
            ImageSheetProperty sheetProperty = ImageSheetProperty.bulider(10,64,64)
                    .withImagePath(spritePath)
                    .withSizeOfOneFrame(64, 64)
                    .withDefaultDurationPerOneFrame(3)
                    .withAddActionState(KindOfState.MOVE, 0, 151)
                    .build();
            bigCoinSheetProperty = sheetProperty;
        }

        return bigCoinSheetProperty;
    }

    private static ImageSheetProperty playerShieldSheetProperty = null;

    public static ImageSheetProperty playerShieldSheetProperty() {
        if (playerShieldSheetProperty == null) {
            String spritePath = "\\com\\Ukasz09\\ValentineGame\\graphicModule\\texturesModel\\sprites\\playerShield.png";
            ImageSheetProperty sheetProperty = ImageSheetProperty.bulider(10,256,256)
                    .withImagePath(spritePath)
                    .withSizeOfOneFrame(256, 256)
                    .withDefaultDurationPerOneFrame(3)
                    .withAddActionState(KindOfState.MOVE, 0, 50)
                    .build();
            playerShieldSheetProperty = sheetProperty;
        }

        return playerShieldSheetProperty;
    }

    private static ImageSheetProperty playerShotBallProperty = null;

    public static ImageSheetProperty playerShotBallProperty() {
        if (playerShotBallProperty == null) {
            String spritePath = "\\com\\Ukasz09\\ValentineGame\\graphicModule\\texturesModel\\sprites\\iceBallSmall.png";
            ImageSheetProperty sheetProperty = ImageSheetProperty.bulider(10,128,74)
                    .withImagePath(spritePath)
                    .withSizeOfOneFrame(128, 74)
                    .withDefaultDurationPerOneFrame(1)
                    .withAddActionState(KindOfState.MOVE, 0, 8)
                    .build();
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
            sheetProperty[0] = ImageSheetProperty.bulider(10,128,128)
                    .withImagePath(spriteStarPath)
                    .withSizeOfOneFrame(128, 128)
                    .withDefaultDurationPerOneFrame(5)
                    .withAddActionState(KindOfState.MOVE, 0, 9)
                    .build();
            sheetProperty[1] = ImageSheetProperty.bulider(10,128,128)
                    .withImagePath(spriteHeartPath)
                    .withSizeOfOneFrame(128, 128)
                    .withDefaultDurationPerOneFrame(5)
                    .withAddActionState(KindOfState.MOVE, 0, 9)
                    .build();
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
