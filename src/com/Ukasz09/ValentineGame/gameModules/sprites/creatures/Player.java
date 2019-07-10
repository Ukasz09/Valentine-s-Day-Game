package com.Ukasz09.ValentineGame.gameModules.sprites.creatures;

import com.Ukasz09.ValentineGame.gameModules.sprites.others.shields.ShieldKindOfRender;
import com.Ukasz09.ValentineGame.graphicModule.texturesPath.SpritesImages;
import com.Ukasz09.ValentineGame.soundsModule.SoundsPath;
import com.Ukasz09.ValentineGame.soundsModule.SoundsPlayer;
import com.Ukasz09.ValentineGame.gameModules.sprites.others.shields.Shield;
import com.Ukasz09.ValentineGame.gameModules.sprites.others.shields.ManualActivateShield;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Player extends Sprite implements ShieldKindOfRender {
    private static final int DEFAULT_VELOCITY = 700;
    private static final int DEFAULT_LIVES = 5;
    private static final int DEFAULT_SHIELD_DURATION = 7500;

    private static final Image PLAYER_RIGHT_IMAGE = SpritesImages.playerRightImage;
    private static final Image PLAYER_LEFT_IMAGE = SpritesImages.playerLeftImage;
    private static final Image PLAYER_SHIELD_IMAGE = SpritesImages.playerShieldImage;

    public static Image playerRightImage;
    public static Image playerLeftImage;

    private Shield shield;
    private SoundsPlayer[] playerHittedSound;
    private String lastDirectionX;
    private String lastDirectionY;
    private int totalScore;

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public Player() {
        this(PLAYER_RIGHT_IMAGE, PLAYER_LEFT_IMAGE, PLAYER_SHIELD_IMAGE);
    }

    public Player(Image playerRightImage, Image playerLeftImage, Image shieldImage) {
        super(playerRightImage);

        this.playerRightImage = playerRightImage;
        this.playerLeftImage = playerLeftImage;
        shield = new ManualActivateShield(0, DEFAULT_SHIELD_DURATION, shieldImage, this);
        lives = DEFAULT_LIVES;
        maxLives = DEFAULT_LIVES;
        playerHittedSound = new SoundsPlayer[2];
        playerHittedSound[0] = new SoundsPlayer(SoundsPath.PLAYER_HIT_SOUND_PATH_1);
        playerHittedSound[1] = new SoundsPlayer(SoundsPath.PLAYER_HIT_SOUND_PATH_2);
        lastDirectionX = "D";
        lastDirectionY = "W";
        totalScore = 0;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @Override
    public void renderShield(GraphicsContext gc) {
        //if shield is active, 750 - delay to see when sheild dissapear before another hit
        if ((getProtectionTime() > 0) && (getProtectionTime() > 750)) {
            if (lastDirectionX.equals("D"))
                gc.drawImage(shield.getShieldImage(), getPositionX(), getPositionY());
            else gc.drawImage(shield.getShieldImage(), getPositionX() - 50, getPositionY());
        }
    }

    @Override
    public void update(double time) {
        super.update(time);
        updateShield();
    }

    @Override
    public void render(GraphicsContext gc) {
        super.render(gc);
        renderShield(gc);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public void addTotalScore(int score) {
        totalScore += score;
    }

    public void updateShield() {
        shield.updateShield();
    }

    public void activateShield() {
        shield.activateShield();
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public SoundsPlayer getUkaszRandomHitSound() {
        int random = (int) (Math.random() * playerHittedSound.length);
        return playerHittedSound[random];
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

    public void setLastDirectionX(String lastDirectionX) {
        this.lastDirectionX = lastDirectionX;
    }

    public void setLastDirectionY(String lastDirectionY) {
        this.lastDirectionY = lastDirectionY;
    }

    public void setShield(Shield shield) {
        this.shield = shield;
    }


}
