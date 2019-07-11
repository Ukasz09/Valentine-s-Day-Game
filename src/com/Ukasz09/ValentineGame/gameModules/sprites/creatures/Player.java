package com.Ukasz09.ValentineGame.gameModules.sprites.creatures;

import com.Ukasz09.ValentineGame.gameModules.utilitis.ViewManager;
import com.Ukasz09.ValentineGame.gameModules.sprites.effects.healthStatusBars.HeartsRender;
import com.Ukasz09.ValentineGame.gameModules.sprites.effects.healthStatusBars.InCorner;
import com.Ukasz09.ValentineGame.gameModules.sprites.effects.shieldsEffect.ShieldKindOfRender;
import com.Ukasz09.ValentineGame.gameModules.sprites.weapons.BombSprite;
import com.Ukasz09.ValentineGame.gameModules.sprites.weapons.BulletSprite;
import com.Ukasz09.ValentineGame.graphicModule.texturesPath.SpritesImages;
import com.Ukasz09.ValentineGame.soundsModule.soundsPath.SoundsPath;
import com.Ukasz09.ValentineGame.soundsModule.soundsPath.SoundsPlayer;
import com.Ukasz09.ValentineGame.gameModules.sprites.effects.shieldsEffect.Shield;
import com.Ukasz09.ValentineGame.gameModules.sprites.effects.shieldsEffect.ManualActivateShield;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Player extends Sprite implements ShieldKindOfRender {
    private static final int DEFAULT_VELOCITY = 700;
    private static final int DEFAULT_LIVES = 5;
    private static final int DEFAULT_SHIELD_DURATION = 7500;
    private static final int DEFAULT_BATTERY_OVERHEATING_REDUCE = 50;
    private static final int DEFAULT_BULLET_OVERHEATING_REDUCE = 50;

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
    private Image[] batteryImages = SpritesImages.getBatteryImages();
    private double bombOverheating;
    private double bulletOverheating;
    private HeartsRender heartsRender;

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public Player(ViewManager manager) {
        this(PLAYER_RIGHT_IMAGE, PLAYER_LEFT_IMAGE, PLAYER_SHIELD_IMAGE, manager);
    }

    public Player(Image playerRightImage, Image playerLeftImage, Image shieldImage, ViewManager manager) {
        super(playerRightImage, manager);
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
        bombOverheating = 0;
        bulletOverheating =0;
        heartsRender=new InCorner(manager);
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
        updateBattery();
        updateBulletOverheating();
    }

    @Override
    public void render() {
        super.render();
        renderShield(getManager().getGraphicContext());
        renderBattery(getManager().getGraphicContext());
        heartsRender.renderHearts(this);
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

    public void renderBattery(GraphicsContext gc) {
        double overheatingPercents = bombOverheating / BombSprite.getMaxOverheating() * 100;
        double batteryPositionX = getManager().getLeftBorder();
        double batteryPositionY = getManager().getBottomBorder() - batteryImages[0].getHeight();

        //100% charge
        if (overheatingPercents == 0)
            gc.drawImage(batteryImages[4], batteryPositionX, batteryPositionY);
            //80%
        else if (overheatingPercents < 40)
            gc.drawImage(batteryImages[3], batteryPositionX, batteryPositionY);
            //60%
        else if (overheatingPercents < 60)
            gc.drawImage(batteryImages[2], batteryPositionX, batteryPositionY);
            //40%
        else if (overheatingPercents < 80)
            gc.drawImage(batteryImages[1], batteryPositionX, batteryPositionY);
            //20%
        else
            gc.drawImage(batteryImages[0], batteryPositionX, batteryPositionY);

    }

    public void updateBattery() {
        if (bombOverheating > 0)
            bombOverheating -= DEFAULT_BATTERY_OVERHEATING_REDUCE;
        else if (bombOverheating < 0)
            bombOverheating = 0;
    }

    public void updateBulletOverheating(){
        if (bulletOverheating > 0)
            bulletOverheating -= DEFAULT_BULLET_OVERHEATING_REDUCE;
    }

    public void overheatBomb() {
        bombOverheating = BombSprite.getMaxOverheating();
    }

    public void overheatBullet(){
        bulletOverheating = BulletSprite.getMaxOverheating();
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

    public double getBombOverheating() {
        return bombOverheating;
    }

    public double getBulletOverheating() {
        return bulletOverheating;
    }
}
