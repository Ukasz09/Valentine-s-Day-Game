package com.Ukasz09.ValentineGame.gameModules.sprites.creatures;

import com.Ukasz09.ValentineGame.gameModules.sprites.effects.imageByPositionEffect.PropperImageSet;
import com.Ukasz09.ValentineGame.gameModules.sprites.items.MoneyBag;
import com.Ukasz09.ValentineGame.gameModules.sprites.weapons.ShotSprite;
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

import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.Iterator;

public class Player extends Sprite implements ShieldKindOfRender {
    public static final int DEFAULT_VELOCITY = 700;
    public static final int DEFAULT_LIVES = 5;
    public static final int DEFAULT_SHIELD_DURATION = 7500;
    public static final int DEFAULT_BATTERY_OVERHEATING_REDUCE = 50;
    public static final int DEFAULT_BULLET_OVERHEATING_REDUCE = 50;
    public static final int DEFAULT_ANTICOLLISION_TIMER = 4000;
    public static final double DEFAULT_HIT_SOUND_VOLUME = 0.2;
    private static final Image PLAYER_RIGHT_IMAGE = SpritesImages.playerRightImage;
    private static final Image PLAYER_LEFT_IMAGE = SpritesImages.playerLeftImage;
    private static final Image PLAYER_SHIELD_IMAGE = SpritesImages.playerShieldImage;

    private Image playerRightImage;
    private Image playerLeftImage;
    private ArrayList<ShotSprite> shotsList;
    private Shield shield;
    private SoundsPlayer[] playerHitSounds;
    private Image[] batteryImages = SpritesImages.getBatteryImages();
    private String lastDirectionX;
    private int totalScore;
    private double bombOverheating;
    private double bulletOverheating;
    private HeartsRender heartsRender;
    private int levelNumber;
    private int collectedMoneyBagsOnLevel;
    private int killedMonstersOnLevel;
    private boolean collisionFromRightSide;
    private boolean collisionFromLeftSide;
    private boolean collisionFromUpSide;
    private boolean collisionFromDownSide;
    private int anticollisionTimer; //to avoid jammed
    private PropperImageSet imageSetWay;

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

        playerHitSounds = new SoundsPlayer[2];
        playerHitSounds[0] = new SoundsPlayer(SoundsPath.PLAYER_HIT_SOUND_PATH_1);
        playerHitSounds[1] = new SoundsPlayer(SoundsPath.PLAYER_HIT_SOUND_PATH_2);

        lastDirectionX = "D";
        totalScore = 0;
        bombOverheating = 0;
        bulletOverheating = 0;
        levelNumber = 0;
        collectedMoneyBagsOnLevel = 0;
        killedMonstersOnLevel = 0;
        heartsRender = new InCorner(manager);
        shotsList = new ArrayList<>();
        imageSetWay=new PropperImageSet();

        collisionFromLeftSide = false;
        collisionFromRightSide = false;
        collisionFromUpSide = false;
        collisionFromDownSide = false;
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
        updateAnticollisionTimer();
    }

    @Override
    public void render() {
        super.render();
        renderShield(getManager().getGraphicContext());
        renderBattery(getManager().getGraphicContext());
        heartsRender.renderHearts(this);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //todo: psuje sie duzo


    @Override
    public Rectangle2D getBoundary() {
        double width=getWidth();
        double height=getHeight();
        return new Rectangle2D(getPositionX()+0.5*width, getPositionY()+0.25*height, width/2, height/2);
    }

    //////////
    private void addTotalScore(int score) {
        totalScore += score;
    }

    private void updateShield() {
        shield.updateShield();
    }

    public void activateShield() {
        shield.activateShield();
    }

    private void renderBattery(GraphicsContext gc) {
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

    private void updateBattery() {
        if (bombOverheating > 0)
            bombOverheating -= DEFAULT_BATTERY_OVERHEATING_REDUCE;
        else if (bombOverheating < 0)
            bombOverheating = 0;
    }

    private void updateBulletOverheating() {
        if (bulletOverheating > 0)
            bulletOverheating -= DEFAULT_BULLET_OVERHEATING_REDUCE;
    }

    public void overheatBomb() {
        bombOverheating = BombSprite.getMaxOverheating();
    }

    public void overheatBullet() {
        bulletOverheating = BulletSprite.getMaxOverheating();
    }

    public void checkCollision(ArrayList<MoneyBag> moneyBagsList, ArrayList<Monster> monsters) {
        checkIntersectsWithMoneyBags(moneyBagsList);
        checksShotsIntersectsWithMonsters(monsters);
        checkIntersectsWithMonsters(monsters);
    }

    private void checkIntersectsWithMoneyBags(ArrayList<MoneyBag> moneyBagsList) {
        Iterator<MoneyBag> moneyBagIterator = moneyBagsList.iterator();
        while (moneyBagIterator.hasNext()) {
            MoneyBag moneybag = moneyBagIterator.next();
            if (intersects(moneybag)) {
                moneybag.playCollectSound();
                moneyBagIterator.remove();
                addTotalScore(moneybag.getValue());
                collectedMoneyBagsOnLevel++;
            }
        }
    }

    private void checksShotsIntersectsWithMonsters(ArrayList<Monster> monsters) {
        Iterator<Monster> monstersIterator = monsters.iterator();
        while (monstersIterator.hasNext()) {
            Monster monster = monstersIterator.next();

            Iterator<ShotSprite> shotIterator = shotsList.iterator();
            while (shotIterator.hasNext()) {
                ShotSprite shot = shotIterator.next();
                //monster is hitted
                if (monster.intersects(shot)) {
                    if (!monster.shieldIsActive()) {
                        shot.hitMonster(monster);
                        if (monster.isDead()) {
                            monster.isDeadAction();
                            monstersIterator.remove();
                            killedMonstersOnLevel++;
                        } else monster.isHitAction();
                    } else monster.missHitAction();

                    shotIterator.remove();
                }
            }
        }
    }

    private void checkIntersectsWithMonsters(ArrayList<Monster> monsters) {
        for (Monster m : monsters) {
            if (this.intersects(m)) {
                if (!shieldIsActive()) {
                    if (m.kickAfterHit())
                        m.kickPlayer(this);

                    removeLives(m.getHowManyLivesTake());
                    playRandomHitSound();
                    activateShield();
                }
                return; //to avoid to much hitt in one moment
            }
        }
    }

    public boolean collisionWithMonstersFromRightSide(ArrayList<Monster> monsters) {
        for (Sprite m : monsters) {
            if ((m.getBoundary().getMinX() < this.getBoundary().getMaxX()) && (m.getBoundary().getMaxX() > this.getBoundary().getMaxX()) && (this.intersects(m)))
                return true;
        }
        return false;
    }

    public boolean collisionWithMonstersFromLeftSide(ArrayList<Monster> monsters) {
        for (Sprite m : monsters) {
            if ((m.getBoundary().getMaxX() > this.getBoundary().getMinX()) && (m.getBoundary().getMinX() < this.getBoundary().getMinX()) && (this.intersects(m)))
                return true;
        }
        return false;
    }

    public boolean collisionWithMonstersFromTop(ArrayList<Monster> monsters) {
        for (Sprite m : monsters) {
            if ((m.getBoundary().getMinY() < this.getBoundary().getMaxY()) && (m.getBoundary().getMinY() > this.getBoundary().getMinY()) && (this.intersects(m)))
                return true;
        }
        return false;
    }

    public boolean collisionWithMonstersFromBottom(ArrayList<Monster> monsters, Sprite ukasz) {
        for (Sprite m : monsters) {
            if ((m.getBoundary().getMaxY() > ukasz.getBoundary().getMinY()) && (m.getBoundary().getMinY() < ukasz.getBoundary().getMinY()) && (m.intersects(ukasz)))
                return true;
        }
        return false;
    }

    public void clearShotsList() {
        shotsList.clear();
    }

    public void addShot(ShotSprite shot) {
        shotsList.add(shot);
    }

    private boolean playerCantDoAnyMove() {
        if (collisionFromDownSide && collisionFromUpSide && collisionFromLeftSide && collisionFromRightSide)
            return true;
        else return false;
    }

    public boolean checkPlayerCanDoAnyMove() {
        if (playerCantDoAnyMove()) {
            restoreAnticollisionTimer();
            return true;
        }

        return false;
    }

    private void restoreAnticollisionTimer() {
        anticollisionTimer = DEFAULT_ANTICOLLISION_TIMER;
    }

    private void reduceAnticollisionTimer() {
        anticollisionTimer -= 100;
        if (anticollisionTimer < 0)
            anticollisionTimer = 0;
    }

    private void updateAnticollisionTimer() {
        if (anticollisionTimer > 0)
            reduceAnticollisionTimer();
    }

    public void setProperActualImage() {
      imageSetWay.byLastDirection(PLAYER_LEFT_IMAGE,PLAYER_RIGHT_IMAGE,this,lastDirectionX);
    }

    private void playRandomHitSound() {
        getRandomHitSound().playSound(DEFAULT_HIT_SOUND_VOLUME, false);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private SoundsPlayer getRandomHitSound() {
        int random = (int) (Math.random() * playerHitSounds.length);
        return playerHitSounds[random];
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

    public void setShield(Shield shield) {
        this.shield = shield;
    }

    public double getBombOverheating() {
        return bombOverheating;
    }

    public double getBulletOverheating() {
        return bulletOverheating;
    }

    public int getLevelNumber() {
        return levelNumber;
    }

    public void setLevelNumber(int levelNumber) {
        this.levelNumber = levelNumber;
    }

    public void setNextLevel() {
        levelNumber++;
    }

    public int getCollectedMoneyBagsOnLevel() {
        return collectedMoneyBagsOnLevel;
    }

    public void setCollectedMoneyBagsOnLevel(int collectedMoneyBagsOnLevel) {
        this.collectedMoneyBagsOnLevel = collectedMoneyBagsOnLevel;
    }

    public int getKilledMonstersOnLevel() {
        return killedMonstersOnLevel;
    }

    public void setKilledMonstersOnLevel(int killedMonstersOnLevel) {
        this.killedMonstersOnLevel = killedMonstersOnLevel;
    }

    public ArrayList<ShotSprite> getShotsList() {
        return shotsList;
    }

    public void setCollisionFromRightSide(boolean collisionFromRightSide) {
        this.collisionFromRightSide = collisionFromRightSide;
    }

    public void setCollisionFromLeftSide(boolean collisionFromLeftSide) {
        this.collisionFromLeftSide = collisionFromLeftSide;
    }

    public void setCollisionFromUpSide(boolean collisionFromUpSide) {
        this.collisionFromUpSide = collisionFromUpSide;
    }

    public void setCollisionFromDownSide(boolean collisionFromDownSide) {
        this.collisionFromDownSide = collisionFromDownSide;
    }
}
