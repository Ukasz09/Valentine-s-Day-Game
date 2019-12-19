package com.Ukasz09.ValentineGame.graphicModule.texturesPath;

import javafx.scene.image.Image;

public class SpritesProperties {
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //                                             CREATURES
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public static ImageSheetProperty playerUkaszSheetProperty() {
        String spritePath = "resources/texturesModel/sprites/ukaszAngel.png";
        ImageSheetProperty sheetProperty = ImageSheetProperty.bulider(10, 256, 256)
                .withImagePath(spritePath)
                .withSizeOfOneFrame(256, 256)
                .withDefaultDurationPerOneFrame(5)
                .withAddActionState(KindOfState.MOVE, 0, 13)
                .build();
        return sheetProperty;
    }

    public static ImageSheetProperty littleMonsterSheetProperty() {
        String spritePath = "resources/texturesModel/sprites/littleMonster1.png";
        ImageSheetProperty sheetProperty = ImageSheetProperty.bulider(10, 256, 256)
                .withImagePath(spritePath)
                .withSizeOfOneFrame(256, 256)
                .withDefaultDurationPerOneFrame(1)
                .withAddActionState(KindOfState.MOVE, 0, 60)
                .build();
        return sheetProperty;
    }

    public static ImageSheetProperty fishMonsterSheetProperty() {
        String spritePath = "resources/texturesModel/sprites/fishMonster.png";
        ImageSheetProperty sheetProperty = ImageSheetProperty.bulider(10, 256, 256)
                .withImagePath(spritePath)
                .withSizeOfOneFrame(256, 256)
                .withDefaultDurationPerOneFrame(2)
                .withAddActionState(KindOfState.MOVE, 0, 75)
                .build();
        return sheetProperty;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //                                              ITEMS
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public static ImageSheetProperty fishBossShieldSheetProperty() {
        String spritePath = "resources/texturesModel/sprites/fishMinibossShield.png";
        ImageSheetProperty sheetProperty = ImageSheetProperty.bulider(10, 256, 256)
                .withImagePath(spritePath)
                .withSizeOfOneFrame(256, 256)
                .withDefaultDurationPerOneFrame(0)
                .withAddActionState(KindOfState.MOVE, 0, 30)
                .build();
        return sheetProperty;
    }

    public static ImageSheetProperty smallCoinSheetProperty() {
        String spritePath = "resources/texturesModel/sprites/smallCoin.png";
        ImageSheetProperty sheetProperty = ImageSheetProperty.bulider(10, 64, 64)
                .withImagePath(spritePath)
                .withSizeOfOneFrame(64, 64)
                .withDefaultDurationPerOneFrame(4)
                .withAddActionState(KindOfState.MOVE, 0, 40)
                .build();
        return sheetProperty;
    }

    public static ImageSheetProperty normalCoinSheetProperty() {
        String spritePath = "resources/texturesModel/sprites/normalCoin.png";
        ImageSheetProperty sheetProperty = ImageSheetProperty.bulider(10, 64, 64)
                .withImagePath(spritePath)
                .withSizeOfOneFrame(64, 64)
                .withDefaultDurationPerOneFrame(4)
                .withAddActionState(KindOfState.MOVE, 0, 10)
                .build();
        return sheetProperty;
    }

    public static ImageSheetProperty bigCoinSheetProperty() {
        String spritePath = "resources/texturesModel/sprites/bigCoin.png";
        ImageSheetProperty sheetProperty = ImageSheetProperty.bulider(10, 64, 64)
                .withImagePath(spritePath)
                .withSizeOfOneFrame(64, 64)
                .withDefaultDurationPerOneFrame(3)
                .withAddActionState(KindOfState.MOVE, 0, 151)
                .build();
        return sheetProperty;
    }

    public static ImageSheetProperty playerShieldSheetProperty() {
        String spritePath = "resources/texturesModel/sprites/playerShield.png";
        ImageSheetProperty sheetProperty = ImageSheetProperty.bulider(10, 256, 256)
                .withImagePath(spritePath)
                .withSizeOfOneFrame(256, 256)
                .withDefaultDurationPerOneFrame(3)
                .withAddActionState(KindOfState.MOVE, 0, 50)
                .build();
        return sheetProperty;
    }

    public static ImageSheetProperty playerShotBallProperty() {
        String spritePath = "resources/texturesModel/sprites/iceBallSmall.png";
        ImageSheetProperty sheetProperty = ImageSheetProperty.bulider(10, 128, 74)
                .withImagePath(spritePath)
                .withSizeOfOneFrame(128, 74)
                .withDefaultDurationPerOneFrame(1)
                .withAddActionState(KindOfState.MOVE, 0, 8)
                .build();
        return sheetProperty;
    }

    public static ImageSheetProperty[] playerShotBombsPoperty() {
        String spriteStarPath = "resources/texturesModel/sprites/legoStar.png";
        String spriteHeartPath = "resources/texturesModel/sprites/legoHeart.png";
        ImageSheetProperty[] sheetProperty = new ImageSheetProperty[2];
        sheetProperty[0] = ImageSheetProperty.bulider(10, 128, 128)
                .withImagePath(spriteStarPath)
                .withSizeOfOneFrame(128, 128)
                .withDefaultDurationPerOneFrame(5)
                .withAddActionState(KindOfState.MOVE, 0, 9)
                .build();
        sheetProperty[1] = ImageSheetProperty.bulider(10, 128, 128)
                .withImagePath(spriteHeartPath)
                .withSizeOfOneFrame(128, 128)
                .withDefaultDurationPerOneFrame(5)
                .withAddActionState(KindOfState.MOVE, 0, 9)
                .build();
        return sheetProperty;
    }

    public static Image[] batteryImages() {
        String batteryPrefixPath = "resources/texturesModel/sprites/battery";
        Image[] batteryImages = new Image[5];
        for (int i = 0; i < batteryImages.length; i++)
            batteryImages[i] = new Image(batteryPrefixPath + (i + 1) + ".png");
        return batteryImages;
    }

    public static Image heartFullImage = new Image(SpritesPath.HEART_FULL_PATH);
    public static Image heartHalfImage = new Image(SpritesPath.HEART_HALF_PATH);
    public static Image heartEmptyImage = new Image(SpritesPath.HEART_EMPTY_PATH);
}
