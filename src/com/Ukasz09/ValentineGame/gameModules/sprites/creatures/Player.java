package com.Ukasz09.ValentineGame.gameModules.sprites.creatures;

import com.Ukasz09.ValentineGame.gameModules.effects.rotateEffect.RotateEffect;
import com.Ukasz09.ValentineGame.gameModules.sprites.items.others.Coin;
import com.Ukasz09.ValentineGame.gameModules.sprites.items.weapons.Weapon;
import com.Ukasz09.ValentineGame.gameModules.utilitis.DirectionEnum;
import com.Ukasz09.ValentineGame.gameModules.utilitis.Logger;
import com.Ukasz09.ValentineGame.gameModules.utilitis.ViewManager;
import com.Ukasz09.ValentineGame.gameModules.effects.healthStatusBars.HeartsRender;
import com.Ukasz09.ValentineGame.gameModules.effects.healthStatusBars.InCorner;
import com.Ukasz09.ValentineGame.gameModules.sprites.items.weapons.Bomb;
import com.Ukasz09.ValentineGame.gameModules.sprites.items.weapons.Bullet;
import com.Ukasz09.ValentineGame.graphicModule.texturesPath.ImageSheetProperty;
import com.Ukasz09.ValentineGame.graphicModule.texturesPath.FrameStatePositions;
import com.Ukasz09.ValentineGame.graphicModule.texturesPath.KindOfState;
import com.Ukasz09.ValentineGame.graphicModule.texturesPath.SpritesProperties;
import com.Ukasz09.ValentineGame.soundsModule.soundsPath.SoundsPath;
import com.Ukasz09.ValentineGame.soundsModule.soundsPath.SoundsPlayer;
import com.Ukasz09.ValentineGame.gameModules.sprites.items.shields.Shield;
import com.Ukasz09.ValentineGame.gameModules.sprites.items.shields.ManualActivateShield;

import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.Iterator;

public class Player extends Creature {
    private static final double DEFAULT_VELOCITY = 700;
    private static final int DEFAULT_LIVES = 5;
    private static final int DEFAULT_SHIELD_DURATION = 7500;
    private static final int DEFAULT_ANTICOLLISION_TIMER = 4000;
    private static final double DEFAULT_HIT_SOUND_VOLUME = 0.2;
    private static final double WINGS_SOUND_VOLUME = 1;
    private static final double DEFAULT_SPRITE_WIDTH = 200;
    private static final double DEFAULT_SPRITE_HEIGHT = 200;
    private static final double DEFAULT_SHIELD_WIDTH = 200;
    private static final double DEFAULT_SHIELD_HEIGHT = 200;
    private static final String WINGS_SOUND_PATH = SoundsPath.PLAYER_WINGS_SOUND_PATH;

    private final ImageSheetProperty shootBallSheetProperty = SpritesProperties.playerShotBallProperty();
    private final ImageSheetProperty[] shootBombsSheetProperty = SpritesProperties.playerShotBombsPoperty();
    private Image[] batteryImages = SpritesProperties.batteryImages();

    private final double amountOfToPixelRotate = 15;
    private ArrayList<Weapon> shotsList;
    private Shield shield;
    private SoundsPlayer[] playerHitSounds;
    private int totalScore;
    private double bombOverheating;
    private double bulletOverheating;
    private HeartsRender heartsRender;
    private int levelNumber;
    private int collectedCoinsOnLevel;
    private int killedMonstersOnLevel;
    private boolean collisionFromRightSide;
    private boolean collisionFromLeftSide;
    private boolean collisionFromUpSide;
    private boolean collisionFromDownSide;
    private int anticollisionTimer; //to avoid jammed
    private SoundsPlayer wingsSound;

    private boolean pressedKey_A;
    private boolean pressedKey_D;
    private boolean pressedKey_W;
    private boolean pressedKey_S;

    private double velocityXPoints;
    private double velocityYPoints;

    private FrameStatePositions actualState;

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public Player(ImageSheetProperty spriteSheetProperty, ImageSheetProperty shieldSheetProperty, ViewManager manager) {
        super(spriteSheetProperty, DEFAULT_SPRITE_WIDTH, DEFAULT_SPRITE_HEIGHT, manager);
        setDefaultProperties();
        shield = new ManualActivateShield(shieldSheetProperty, DEFAULT_SHIELD_WIDTH, DEFAULT_SHIELD_HEIGHT, DEFAULT_SHIELD_DURATION, manager);
        playerHitSounds = getPlayerHitSounds();
        heartsRender = new InCorner(manager);
        shotsList = new ArrayList<>();

        collisionFromLeftSide = false;
        collisionFromRightSide = false;
        collisionFromUpSide = false;
        collisionFromDownSide = false;

        pressedKey_A = false;
        pressedKey_D = false;
        pressedKey_W = false;
        pressedKey_S = false;
        setImageDirection(DirectionEnum.RIGHT);
        wingsSound = new SoundsPlayer(WINGS_SOUND_PATH, WINGS_SOUND_VOLUME, true);
        actualState = spriteSheetProperty.getAction(KindOfState.MOVE);

        setActualFramePositionX(actualState.getMinX());
        setActualFramePositionY(actualState.getMinY());
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private void setDefaultProperties() {
        setMaxLives(DEFAULT_LIVES);
        setLives(DEFAULT_LIVES);
        velocityXPoints = DEFAULT_VELOCITY;
        velocityYPoints = DEFAULT_VELOCITY;
        setActualVelocity(DEFAULT_VELOCITY, DEFAULT_VELOCITY);
        totalScore = 0;
        bombOverheating = 0;
        bulletOverheating = 0;
        levelNumber = 0;
        collectedCoinsOnLevel = 0;
        killedMonstersOnLevel = 0;
    }

    private void renderShield() {
        GraphicsContext gc = getManager().getGraphicContext();
        if (shield.isActive())
            shield.render(getPositionX(), getPositionY());
    }

    public void update(double time, ArrayList<Coin> coinsList, ArrayList<Monster> enemiesList) {
        super.update(time, 1, 1);
        updateAllCollisions(coinsList, enemiesList);
        updateShield();
        updateBattery();
        updateBulletOverheating();
        updateAnticollisionTimer();
        updatePlayerRotate();
    }

    @Override
    public void render() {
        renderBattery(getManager().getGraphicContext());
        heartsRender.renderHearts(this);
        renderShield();
        renderSpriteWithRotation();
    }

    //do NOT touch
    @Override
    public Rectangle2D getBoundary() {
        double width = getWidth();
        double height = getHeight();
        if (getImageDirection().equals(DirectionEnum.RIGHT))
            return new Rectangle2D(getPositionX() + width - (width / 5.5) - width / 2.4, getPositionY() + height / 2.8, width / 2.4, height / 2);
        return new Rectangle2D(getPositionX() + width / 5.5, getPositionY() + height / 2.8, width / 2.4, height / 2);
    }

    private void addTotalScore(int score) {
        totalScore += score;
    }

    private void updateShield() {
        shield.updateShield();
    }

    private void activateShield() {
        shield.activateShield();
    }

    private void renderBattery(GraphicsContext gc) {
        double overheatingPercents = bombOverheating / Bomb.getMaxOverheating() * 100;
        double batteryPositionX = 0;
        double batteryPositionY = getManager().getBottomFrameBorder() - batteryImages[0].getHeight();

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
            bombOverheating -= 50;
        else if (bombOverheating < 0)
            bombOverheating = 0;
    }

    public void addBullet() {
        Point2D bulletPosition = getBulletPosition();
        Bullet bullet = new Bullet(shootBallSheetProperty, getImageDirection(), bulletPosition.getX(), bulletPosition.getY(), getManager());
        shotsList.add(bullet);
        bullet.playShotSound();
        overheatBulletGun();
    }

    private Point2D getBulletPosition() {
        double shotPositionY = getBoundary().getMaxY() - getHeight() / 4;
        double shotPositionRightX = getBoundary().getMaxX();
        double shotPositionLeftX = getBoundary().getMinX();

        if (getImageDirection().equals(DirectionEnum.RIGHT))
            return new Point2D(shotPositionRightX, shotPositionY);
        return new Point2D(shotPositionLeftX, shotPositionY);
    }

    private void overheatBulletGun() {
        bulletOverheating = Bullet.getMaxOverheating();
    }

    public void addBomb() {
        Point2D bombPosition = getBombPosition();
        Bomb bomb = new Bomb(randomPlayerShotBombProperty(), bombPosition.getX(), bombPosition.getY(), getManager());
        shotsList.add(bomb);
        bomb.playShotSound();
        overheatBombGun();
    }

    private ImageSheetProperty randomPlayerShotBombProperty() {
        int randOffset = (int) (Math.random() * shootBombsSheetProperty.length);
        return shootBombsSheetProperty[randOffset];
    }

    private Point2D getBombPosition() {
        double centerPositionRightX = getBoundary().getMaxX() - getWidth() / 3;
        return new Point2D(centerPositionRightX, getBoundary().getMaxY());
    }

    private void overheatBombGun() {
        bombOverheating = Bomb.getMaxOverheating();
    }

    private void updateBulletOverheating() {
        if (bulletOverheating > 0)
            bulletOverheating -= 50;
    }

    public boolean bulletCanBeSpawn() {
        return (bulletOverheating <= 0);
    }

    public boolean bombCanBeSpawn() {
        return (bombOverheating <= 0);
    }

    private void updateAllCollisions(ArrayList<Coin> coinsList, ArrayList<Monster> enemiesList) {
        checkIntersectsWithMoneyBags(coinsList);
        checksShotsIntersectsWithMonsters(enemiesList);
        checkIntersectsWithMonsters(enemiesList);
    }

    private void checkIntersectsWithMoneyBags(ArrayList<Coin> coinsList) {
        Iterator<Coin> coinsIterator = coinsList.iterator();
        while (coinsIterator.hasNext()) {
            Coin coin = coinsIterator.next();
            if (intersects(coin)) {
                coin.playCollectSound();
                addTotalScore(coin.getValue());
                coinsIterator.remove();
                collectedCoinsOnLevel++;
            }
        }
    }

    private void checksShotsIntersectsWithMonsters(ArrayList<Monster> monsters) {
        Iterator<Monster> monstersIterator = monsters.iterator();
        while (monstersIterator.hasNext()) {
            Monster monster = monstersIterator.next();

            Iterator<Weapon> shotIterator = shotsList.iterator();
            while (shotIterator.hasNext()) {
                Weapon shot = shotIterator.next();
                //monster is hitted
                if (monster.intersects(shot)) {
                    if (!monster.hasActiveShield()) {
                        shot.hitMonster(monster);
                        if (monster.isDead()) {
                            monster.actionWhenDead();
                            monstersIterator.remove();
                            killedMonstersOnLevel++;
                        } else monster.actionWhenHit();
                    } else monster.actionWhenMissHit();

                    shotIterator.remove();
                }
            }
        }
    }

    private void checkIntersectsWithMonsters(ArrayList<Monster> monsters) {
        for (Monster m : monsters) {
            if (this.intersects(m)) {
                if (!shield.isActive()) {
                    m.kickPlayer(this);
                    removeLives(m.getLivesTake());
                    playRandomHitSound();
                    activateShield();
                }
                return; //to avoid to much hitt in one moment
            }
        }
    }

    public boolean collisionWithMonster(DirectionEnum side, ArrayList<Monster> monsters) {
        switch (side) {
            case LEFT:
                return collisionWithMonsterFromLeft(monsters);
            case RIGHT:
                return collisionWithMonsterFromRight(monsters);
            case UP:
                return collisionWithMonsterFromTop(monsters);
            case DOWN:
                return collisionWithMonsterFromBottom(monsters);
        }
        return false;
    }

    private boolean collisionWithMonsterFromRight(ArrayList<Monster> monsters) {
        for (Creature m : monsters) {
            if ((m.getBoundary().getMinX() < this.getBoundary().getMaxX()) && (m.getBoundary().getMaxX() > this.getBoundary().getMaxX()) && (this.intersects(m)))
                return true;
        }
        return false;
    }

    private boolean collisionWithMonsterFromLeft(ArrayList<Monster> monsters) {
        for (Creature m : monsters) {
            if ((m.getBoundary().getMaxX() > this.getBoundary().getMinX()) && (m.getBoundary().getMinX() < this.getBoundary().getMinX()) && (this.intersects(m)))
                return true;
        }
        return false;
    }

    private boolean collisionWithMonsterFromBottom(ArrayList<Monster> monsters) {
        for (Creature m : monsters) {
            if ((m.getBoundary().getMinY() < this.getBoundary().getMaxY()) && (m.getBoundary().getMinY() > this.getBoundary().getMinY()) && (this.intersects(m)))
                return true;
        }
        return false;
    }

    private boolean collisionWithMonsterFromTop(ArrayList<Monster> monsters) {
        for (Creature m : monsters) {
            if ((m.getBoundary().getMaxY() > this.getBoundary().getMinY()) && (m.getBoundary().getMinY() < this.getBoundary().getMinY()) && (m.intersects(this)))
                return true;
        }
        return false;
    }

    public void clearShotsList() {
        shotsList.clear();
    }

    public boolean cannotDoAnyMove() {
        return (collisionFromDownSide && collisionFromUpSide && collisionFromLeftSide && collisionFromRightSide);
    }

    public boolean anticollisionModeIsActive() {
        return (anticollisionTimer > 0);
    }

    public void restoreAnticollisionTimer() {
        anticollisionTimer = DEFAULT_ANTICOLLISION_TIMER;
        setAllCollision(false);
    }

    public void setAllCollision(boolean condition) {
        collisionFromLeftSide = condition;
        collisionFromRightSide = condition;
        collisionFromUpSide = condition;
        collisionFromDownSide = condition;
    }

    private void updateAnticollisionTimer() {
        if (anticollisionTimer > 0)
            reduceAnticollisionTimer();
    }

    private void reduceAnticollisionTimer() {
        anticollisionTimer -= 100;
        if (anticollisionTimer < 0)
            anticollisionTimer = 0;
    }

    private void playRandomHitSound() {
        getRandomHitSound().playSound();
    }

    private void updatePlayerRotate() {
        double properRotate = RotateEffect.rotateByPressedKey(pressedKey_A, pressedKey_D, pressedKey_W, pressedKey_S, amountOfToPixelRotate);
        setActualRotation(properRotate);
    }

    @Override
    protected void setPositionOfNextFrame() {
        setPositionOfNextFrame(actualState.getMinX(), actualState.getMaxX(), actualState.getMinY(), actualState.getMaxY(), spriteImage.getWidth());
    }

    private SoundsPlayer getRandomHitSound() {
        int random = (int) (Math.random() * playerHitSounds.length);
        return playerHitSounds[random];
    }

    public int getTotalScore() {
        return totalScore;
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

    public int getCollectedCoinsOnLevel() {
        return collectedCoinsOnLevel;
    }

    public void setCollectedCoinsOnLevel(int collectedCoinsOnLevel) {
        this.collectedCoinsOnLevel = collectedCoinsOnLevel;
    }

    public int getKilledEnemiesOnLevel() {
        return killedMonstersOnLevel;
    }

    public void setKilledMonstersOnLevel(int killedMonstersOnLevel) {
        this.killedMonstersOnLevel = killedMonstersOnLevel;
    }

    public ArrayList<Weapon> getShotsList() {
        return shotsList;
    }

    public void setCollision(DirectionEnum side, boolean condition) {
        switch (side) {
            case UP:
                setCollisionFromUpSide(condition);
                break;
            case DOWN:
                setCollisionFromDownSide(condition);
                break;
            case LEFT:
                setCollisionFromLeftSide(condition);
                break;
            case RIGHT:
                setCollisionFromRightSide(condition);
                break;
        }
    }

    public boolean getCollision(DirectionEnum side) {
        switch (side) {
            case UP:
                return collisionFromUpSide;
            case DOWN:
                return collisionFromDownSide;
            case LEFT:
                return collisionFromLeftSide;
            case RIGHT:
                return collisionFromRightSide;
            default:
                return false;
        }
    }

    private void setCollisionFromRightSide(boolean collisionFromRightSide) {
        this.collisionFromRightSide = collisionFromRightSide;
    }

    private void setCollisionFromLeftSide(boolean collisionFromLeftSide) {
        this.collisionFromLeftSide = collisionFromLeftSide;
    }

    private void setCollisionFromUpSide(boolean collisionFromUpSide) {
        this.collisionFromUpSide = collisionFromUpSide;
    }

    private void setCollisionFromDownSide(boolean collisionFromDownSide) {
        this.collisionFromDownSide = collisionFromDownSide;
    }

    public void setPressedKey(String literal, boolean keyCondition) {
        switch (literal) {
            case "A":
                setPressedKey_A(keyCondition);
                break;

            case "D":
                setPressedKey_D(keyCondition);
                break;

            case "W":
                setPressedKey_W(keyCondition);
                break;

            case "S":
                setPressedKey_S(keyCondition);
                break;

            default:
        }

    }

    public void setPressedKey_A(boolean pressedKey_A) {
        this.pressedKey_A = pressedKey_A;
    }

    public void setPressedKey_D(boolean pressedKey_D) {
        this.pressedKey_D = pressedKey_D;
    }

    public void setPressedKey_W(boolean pressedKey_W) {
        this.pressedKey_W = pressedKey_W;
    }

    public void setPressedKey_S(boolean pressedKey_S) {
        this.pressedKey_S = pressedKey_S;
    }

    private SoundsPlayer[] getPlayerHitSounds() {
        SoundsPlayer hitSounds[] = new SoundsPlayer[2];
        hitSounds[0] = new SoundsPlayer(SoundsPath.PLAYER_HIT_SOUND_1_PATH, DEFAULT_HIT_SOUND_VOLUME, false);
        hitSounds[1] = new SoundsPlayer(SoundsPath.PLAYER_HIT_SOUND_2_PATH, DEFAULT_HIT_SOUND_VOLUME, false);
        return hitSounds;
    }

    public void playWingsSound() {
        if (wingsSound != null)
            wingsSound.playSound();
        else Logger.logError(getClass(), wingsSound.toString() + " is null");
    }

    public void stopWingsSound() {
        if (wingsSound != null)
            wingsSound.stopSound();
        else Logger.logError(getClass(), wingsSound.toString() + " is null");
    }

    public double getVelocityXPoints() {
        return velocityXPoints;
    }

    public double getVelocityYPoints() {
        return velocityYPoints;
    }
}
