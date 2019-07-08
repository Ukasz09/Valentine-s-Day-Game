package com.Ukasz09.ValentineGame.graphicModule.texturesPath;

import javafx.scene.image.Image;

public class SpritesImages {
    //Player
    public static Image ukaszLeftImage = new Image(SpritesPath.ukaszLeftPath);
    public static Image ukaszRightImage = new Image(SpritesPath.ukaszRightPath);

    //Enemy's
    public static Image littleMonsterImage = new Image(SpritesPath.littleMonster_1Path);

    public static Image fishMonsterRightImage = new Image(SpritesPath.fishMonsterRightPath);
    public static Image fishMonsterLeftImage = new Image(SpritesPath.fishMonsterLeftPath);
    public static Image fishMonsterTopImage = new Image(SpritesPath.fishMonsterTopPath);
    public static Image fishMonsterBottomImage = new Image(SpritesPath.fishMonsterBottomPath);

    public static Image fishMonsterMinibossRightImage = new Image(SpritesPath.fishMonsterMinibossRightPath);
    public static Image fishMonsterMinibossLeftImage = new Image(SpritesPath.fishMonsterMinibossLeftPath);
    public static Image fishMonsterMinibossTopImage = new Image(SpritesPath.fishMonsterMinibossTopPath);
    public static Image fishMonsterMinibossBottomImage = new Image(SpritesPath.fishMonsterMinibossBottomPath);

    //Other
    public static Image ukaszShieldImage = new Image(SpritesPath.protectionOrbitPath);
    public static Image ukaszShotImage = new Image(SpritesPath.ukaszBulletShotPath);
    private static Image[] ukaszBombShotImages = new Image[2];
    public static Image heartFullImage = new Image(SpritesPath.heartFullPath);
    public static Image heartHalfImage = new Image(SpritesPath.heartHalfPath);
    public static Image heartEmptyImage = new Image(SpritesPath.heartEmptyPath);
    private static Image[] batteryImages = new Image[5];

    public static Image moneyBagImage1 = new Image(SpritesPath.moneyBagPath1);
    public static Image moneyBagImage2 = new Image(SpritesPath.moneyBagPath2);

    public static Image fishMinibossShieldImage = new Image(SpritesPath.fishMinibossShield);

    //endPanel
    public static Image kasiaImage = new Image(SpritesPath.kasiaImagePath);
    public static Image heartFlareImage = new Image(SpritesPath.heartFlarePath);
    public static Image kasiaWingsImage = new Image(SpritesPath.wingsPath);

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public static Image[] getBatteryImages() {
        for (int i = 0; i < batteryImages.length; i++)
            batteryImages[i] = new Image(SpritesPath.batteryPrefix + (i + 1) + ".png");

        return batteryImages;
    }

    public static Image[] getUkaszBombShotImages() {
        ukaszBombShotImages[0] = new Image(SpritesPath.ukaszBombShotPath1);
        ukaszBombShotImages[1] = new Image(SpritesPath.ukaszBombShotPath2);

        return ukaszBombShotImages;
    }
}
