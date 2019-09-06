package com.Ukasz09.ValentineGame.gameModules.levels;

import com.Ukasz09.ValentineGame.gameModules.sprites.creatures.Player;
import com.Ukasz09.ValentineGame.gameModules.utilitis.ViewManager;
import com.Ukasz09.ValentineGame.gameModules.sprites.items.Coin;
import com.Ukasz09.ValentineGame.gameModules.sprites.creatures.Monster;
import com.Ukasz09.ValentineGame.gameModules.sprites.creatures.Sprite;

import com.Ukasz09.ValentineGame.gameModules.sprites.weapons.ShotSprite;
import com.Ukasz09.ValentineGame.graphicModule.texturesPath.SpritesPath;
import com.Ukasz09.ValentineGame.soundsModule.soundsPath.SoundsPath;
import com.Ukasz09.ValentineGame.soundsModule.soundsPath.SoundsPlayer;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.Iterator;

public abstract class AllLevels {
    private static final String MONEY_COMMUNICATE = "Kasa na walentynki: $";
    private static final double WINGS_SOUND_VOLUME = 1;
    private static SoundsPlayer backgroundSound;
    private static final String WINGS_SOUND_PATH = SoundsPath.PLAYER_WINGS_SOUND_PATH; //TODO: dac wings do PLAYER'a
    private static SoundsPlayer wingsSound;

    private int amountOfAllCoins;
    private int amountOfSmallCoins;
    private int amountOfNormalCoins;
    private int amountOfBigCoins;
    private Image smallCoinImage;
    private Image normalCoinImage;
    private Image bigCoinImage;
    private int smallCoinValue;
    private int normalCoinValue;
    private int bigCoinValue;

    private Image backgroundImage;

    private int amountOfMonsters;
    private int amountOfBosses;
    private int amountOfAllEnemies;

    private ViewManager manager;

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public AllLevels(ViewManager manager) {
        this.manager = manager;
        this.amountOfAllCoins = 0;
        wingsSound=new SoundsPlayer(WINGS_SOUND_PATH,WINGS_SOUND_VOLUME,true);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public int getAmountOfAllCoins() {
        return amountOfAllCoins;
    }

    public int getAmountOfAllEnemies() {
        return amountOfAllEnemies;
    }

    public ViewManager getManager() {
        return manager;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // TODO: 03.09.2019: zrobione
    public void update(Player player, ArrayList<Monster> enemiesList, double elapsedTime) {
        updateShots(player.getShotsList(), elapsedTime);
        updateEnemies(player, enemiesList);
    }

    //TODO: zrobione
    public boolean levelIsEnd(Player player) {
        if ((player.getCollectedCoinsOnLevel() < getAmountOfAllCoins()) || (player.getKilledEnemiesOnLevel() < getAmountOfAllEnemies()))
            return false;
        else return true;
    }

    //TODO: zrobione
    public void spawnCoins(ArrayList<Coin> coinsList, int amountOfSmallCoins, int amountOfNormalCoins, int amountOfBigCoins) {
        if (amountOfSmallCoins > 0 && amountOfNormalCoins > 0 && amountOfBigCoins > 0) {
            smallCoinImage = new Image(SpritesPath.SMALL_COIN_PATH);
            normalCoinImage = new Image(SpritesPath.NORMAL_COIN_PATH);
            bigCoinImage = new Image(SpritesPath.BIG_COIN_PATH);

            Coin coin;
            for (int i = 0; i < amountOfSmallCoins; i++) {
                coin = new Coin(smallCoinImage, smallCoinValue, manager);
                coin.setPosition();
                coinsList.add(coin);
            }
            for (int i = 0; i < amountOfNormalCoins; i++) {
                coin = new Coin(normalCoinImage, normalCoinValue, manager);
                coin.setPosition();
                coinsList.add(coin);
            }
            for (int i = 0; i < amountOfBigCoins; i++) {
                coin = new Coin(bigCoinImage, bigCoinValue, manager);
                coin.setPosition();
                coinsList.add(coin);
            }
        }
    }

    //TODO: zrobione
    public void renderMonsters(ArrayList<Monster> enemiesList) {
        if (enemiesList != null) {
            for (Sprite m : enemiesList)
                m.render();
        }
    }

    //TODO: zrobione
    public void renderCoins(ArrayList<Coin> coinsList) {
        if (coinsList != null) {
            for (Coin m : coinsList)
                m.render();
        }
    }

    //TODO: zrobione
    private void updateEnemies(Sprite target, ArrayList<Monster> enemiesList) {
        for (Monster m : enemiesList)
            m.update(target, enemiesList);
    }

    //TODO: zrobione

    /**
     * Need to set position of enemies, add enemySprite to enemiesList specify by level
     */
    public abstract void spawnEnemies(ArrayList<Monster> enemiesList);

    //TODO: zrobione
    public void prepareLevel(ArrayList<Coin> coinsList, ArrayList<Monster> enemiesList, Player player) {
        spawnCoins(coinsList, amountOfSmallCoins, amountOfNormalCoins, amountOfBigCoins);
        spawnEnemies(enemiesList);
        setPlayerStartPosition(player);
    }

    //Todo: zrobione
    public abstract void render(ArrayList<Monster> monsters, ArrayList<Coin> coins, ArrayList<ShotSprite> shots, Player player);

    //TODO: zrobione
    public Point2D getDefaultPlayerPosition() {
        double positionX = manager.getRightBorder() / 2;
        double positionY = manager.getBottomBorder() / 2;
        return new Point2D(positionX, positionY);
    }

    //TODO: zrobione
    public abstract void setPlayerStartPosition(Player player);

    //TODO: zrobione
    public void defaultLevelRender(ArrayList<Monster> monsters, ArrayList<Coin> coins, ArrayList<ShotSprite> shots, int playerScore) {
        renderBackground(backgroundImage);
        renderScoreText(playerScore);
        renderCoins(coins);
        renderShots(shots);
        renderMonsters(monsters);
    }

    //TODO: zrobione
    public void renderBackground(Image backgroundImage) {
        getManager().getGraphicContext().drawImage(backgroundImage, 0, 0);
    }

    //TODO: zrobione
    public static void playWingsSound() {
       wingsSound.playSound();
    }

    //TODO: zrobione
    public static void stopWingsSound() {
       wingsSound.stopSound();
    }

    //TODO: zrobione
//    public static void playBackgroundSound(SoundsPlayer backgroundSound) {
//        AllLevels.backgroundSound = backgroundSound;
//        AllLevels.backgroundSound.playSound();
//    }

    protected static void playBackgroundSound(SoundsPlayer backgroundSound) {
        AllLevels.backgroundSound=backgroundSound
        AllLevels.backgroundSound.playSound();
    }

    //tODO: dokonczyc

    public static void stopBackgroundSound() {
        backgroundSound.stopSound();
    }

    public void renderShots(ArrayList<ShotSprite> shotSprites) {
        Iterator<ShotSprite> shots = shotSprites.iterator();
        while (shots.hasNext())
            shots.next().render();
    }

    private void updateShots(ArrayList<ShotSprite> shotsList, double elapsedTime) {
        Iterator<ShotSprite> shotsIterator = shotsList.iterator();
        while (shotsIterator.hasNext()) {
            ShotSprite shot = shotsIterator.next();
            shot.update(elapsedTime);

            if (shot.isOutOfBoundary()) {
                shot.doOutOfBoundaryAction();
                shotsIterator.remove();
            }
        }
    }

    //TODO: zrobione
    public void renderText(String text, Color color, double posX, double posY) {
        manager.getGraphicContext().setFill(color);
        manager.getGraphicContext().fillText(text, posX, posY);
    }

    //TODO: zrobione
    public void renderScoreText(int score) {
        String pointsText = MONEY_COMMUNICATE + score;
        double posX = manager.getLeftBorder();
        double posY = manager.getTopBorder() + 75;
        renderText(pointsText, Color.TAN, posX, posY);
    }

    //TODO: zrobione
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public void setAmountOfCoins(int amountOfSmallCoins, int amountOfNormalCoins, int amountOfBigCoins) {
        this.amountOfSmallCoins = amountOfSmallCoins;
        this.amountOfNormalCoins = amountOfNormalCoins;
        this.amountOfBigCoins = amountOfBigCoins;
        this.amountOfAllCoins = amountOfSmallCoins + amountOfNormalCoins + amountOfBigCoins;
    }

    public void setBackgroundImage(Image backgroundImage) {
        this.backgroundImage = backgroundImage;
    }

    public void setCoinsValue(int smallCoinValue, int normalCoinValue, int bigCoinValue) {
        this.smallCoinValue = smallCoinValue;
        this.normalCoinValue = normalCoinValue;
        this.bigCoinValue = bigCoinValue;
    }

    public void setAmountOfMonsters(int amountOfMonsters, int amountOfBosses) {
        if (amountOfMonsters > 0)
            this.amountOfMonsters = amountOfMonsters;
        else this.amountOfMonsters = 0;

        if (amountOfBosses > 0)
            this.amountOfBosses = amountOfBosses;
        else this.amountOfBosses = 0;

        this.amountOfAllEnemies = this.amountOfMonsters + this.amountOfBosses;
    }
}
