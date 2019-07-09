package com.Ukasz09.ValentineGame.gameModules.sprites.creatures;

import com.Ukasz09.ValentineGame.graphicModule.texturesPath.SpritesImages;
import com.Ukasz09.ValentineGame.soundsModule.SoundsPath;
import com.Ukasz09.ValentineGame.soundsModule.SoundsPlayer;
import com.Ukasz09.ValentineGame.gameModules.sprites.others.Shield;
import com.Ukasz09.ValentineGame.gameModules.sprites.others.UkaszShield;

import javafx.scene.image.Image;

public class Player extends Sprite {
    private static final int DEFAULT_VELOCITY = 700;
    private static final int DEFAULT_LIVES = 5;
    private static final int DEFAULT_SHIELD_DURATION = 7500;

    private Shield shield;
    private SoundsPlayer[] ukaszHitSounds;
    private String lastDirectionX;
    private String lastDirectionY;
    private int totalScore;

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public Player(Image image, Image shieldImage) {
        super(image);

        shield = new UkaszShield(0, DEFAULT_SHIELD_DURATION, shieldImage, this);
        setLives(DEFAULT_LIVES);
        setMaxLives(DEFAULT_LIVES);

        ukaszHitSounds = new SoundsPlayer[2];
        ukaszHitSounds[0] = new SoundsPlayer(SoundsPath.ukaszHitSoundPath1);
        ukaszHitSounds[1] = new SoundsPlayer(SoundsPath.ukaszHitSoundPath2);

        lastDirectionX = "D";
        lastDirectionY = "W";
        totalScore = 0;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public void addTotalScore(int score) {
        totalScore += score;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public SoundsPlayer[] getUkaszHitSounds() {
        return ukaszHitSounds;
    }

    public SoundsPlayer getUkaszRandomHitSound() {

        int random = (int) (Math.random() * 2);
        System.out.println(random);
        return ukaszHitSounds[random];
    }

    public static int getDefaultVelocity() {
        return DEFAULT_VELOCITY;
    }

    public String getLastDirectionX() {
        return lastDirectionX;
    }

    public int getTotalScore() {
        return totalScore;
    }

    public Shield getShield() {
        return shield;
    }

    public void setLastDirectionX(String lastDirectionX) {
        this.lastDirectionX = lastDirectionX;
    }

    public void setLastDirectionY(String lastDirectionY) {
        this.lastDirectionY = lastDirectionY;
    }

}
