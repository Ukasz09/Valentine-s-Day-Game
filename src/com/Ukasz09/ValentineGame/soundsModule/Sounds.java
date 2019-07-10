package com.Ukasz09.ValentineGame.soundsModule;

public class Sounds {
    public static SoundsPlayer ukaszWingsSound = new SoundsPlayer(SoundsPath.PLAYER_WINGS_SOUND_PATH);
    public static SoundsPlayer backgroundSound = new SoundsPlayer(SoundsPath.BACKGROUND_SOUND_PATH_1);
    public static SoundsPlayer backgroundStartSound = new SoundsPlayer(SoundsPath.START_SOUND_PATH);
    public static SoundsPlayer collectMoneySound = new SoundsPlayer(SoundsPath.COLLECT_MONEY_SOUND_PATH);
    public static SoundsPlayer backgroundEndSound = new SoundsPlayer(SoundsPath.END_SOUND_PATH);
    public static SoundsPlayer bulletShotSound = new SoundsPlayer(SoundsPath.BULLET_SHOT_SOUND_PATH);
    public static SoundsPlayer bombShotSound = new SoundsPlayer(SoundsPath.BOMB_SHOT_SOUND_PATH);
}
