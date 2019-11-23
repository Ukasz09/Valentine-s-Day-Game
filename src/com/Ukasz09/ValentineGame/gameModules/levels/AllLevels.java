package com.Ukasz09.ValentineGame.gameModules.levels;

import com.Ukasz09.ValentineGame.gameModules.sprites.creatures.Player;
import com.Ukasz09.ValentineGame.gameModules.utilitis.ViewManager;
import com.Ukasz09.ValentineGame.gameModules.sprites.items.others.Coin;
import com.Ukasz09.ValentineGame.gameModules.sprites.creatures.Monster;
import com.Ukasz09.ValentineGame.gameModules.sprites.creatures.Creature;

import com.Ukasz09.ValentineGame.gameModules.sprites.items.weapons.Weapon;
import com.Ukasz09.ValentineGame.graphicModule.texturesPath.BackgroundPath;
import com.Ukasz09.ValentineGame.graphicModule.texturesPath.ImageSheetProperty;
import com.Ukasz09.ValentineGame.graphicModule.texturesPath.SpritesPath;
import com.Ukasz09.ValentineGame.graphicModule.texturesPath.SpritesProperties;
import com.Ukasz09.ValentineGame.soundsModule.soundsPath.SoundsPlayer;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.Iterator;

public abstract class AllLevels {

    private enum CoinTypes {
        SMALL(25, 20, 20, SpritesProperties.smallCoinSheetProperty()),
        NORMAL(50, 30, 30, SpritesProperties.normalCoinSheetProperty()),
        BIG(100, 40, 40, SpritesProperties.bigCoinSheetProperty());

        int value;
        double width;
        double height;
        ImageSheetProperty sheetProperty;

        CoinTypes(int value, double width, double height, ImageSheetProperty sheetProperty) {
            this.value = value;
            this.width = width;
            this.height = height;
            this.sheetProperty = sheetProperty;
        }
    }

    private static final String MONEY_COMMUNICATE = "  Money for Valentine's Day: $";

    private int amountOfAllCoins;
    private int amountOfSmallCoins;
    private int amountOfNormalCoins;
    private int amountOfBigCoins;

    private Image backgroundImage;
    private ViewManager manager;

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public AllLevels(ViewManager manager) {
        this(manager, new Image(BackgroundPath.DEFAULT_IMAGE_PATH));
    }

    public AllLevels(ViewManager manager, Image backgroundImage) {
        this.manager = manager;
        this.amountOfAllCoins = 0;
        this.backgroundImage = backgroundImage;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public int getAmountOfAllCoins() {
        return amountOfAllCoins;
    }

    public ViewManager getManager() {
        return manager;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    abstract public SoundsPlayer getBackgroundSound();

    public void update(Player player, ArrayList<Monster> enemiesList, ArrayList<Coin> coinsList, double elapsedTime) {
        updateShots(player.getShotsList(), elapsedTime);
        updateEnemies(elapsedTime, player, enemiesList);
        updateCoins(coinsList, elapsedTime);
    }

    public boolean levelIsEnd(Player player) {
        if ((player.getCollectedCoinsOnLevel() < getAmountOfAllCoins()) || (player.getKilledEnemiesOnLevel() < amountOfSpawnEnemies()))
            return false;
        else return true;
    }

    public abstract int amountOfSpawnEnemies();

    public void spawnCoins(ArrayList<Coin> coinsList, int amountOfSmallCoins, int amountOfNormalCoins, int amountOfBigCoins) {
        if (amountOfSmallCoins > 0 && amountOfNormalCoins > 0 && amountOfBigCoins > 0) {
            for (int i = 0; i < amountOfSmallCoins; i++)
                coinsList.add(new Coin(CoinTypes.SMALL.sheetProperty, CoinTypes.SMALL.width, CoinTypes.SMALL.height, CoinTypes.SMALL.value, manager));

            for (int i = 0; i < amountOfNormalCoins; i++)
                coinsList.add(new Coin(CoinTypes.NORMAL.sheetProperty, CoinTypes.NORMAL.width, CoinTypes.NORMAL.height, CoinTypes.NORMAL.value, manager));

            for (int i = 0; i < amountOfBigCoins; i++)
                coinsList.add(new Coin(CoinTypes.BIG.sheetProperty, CoinTypes.BIG.width, CoinTypes.BIG.height, CoinTypes.BIG.value, manager));
        }
    }

    public void renderMonsters(ArrayList<Monster> enemiesList) {
        if (enemiesList != null) {
            for (Monster m : enemiesList)
                m.render();
        }
    }

    private void updateCoins(ArrayList<Coin> coinsList, double elapsedTime) {
        if (coinsList != null)
            for (Coin m : coinsList)
                m.update(elapsedTime, 1, 1);
    }

    public void renderCoins(ArrayList<Coin> coinsList) {
        if (coinsList != null) {
            for (Coin m : coinsList)
                m.render();
        }
    }

    private void updateEnemies(double elapsedTime, Creature target, ArrayList<Monster> enemiesList) {
        for (Monster m : enemiesList)
            m.update(target, enemiesList);
    }

    /**
     * Need to set position of enemies, add enemySprite to enemiesList specify by level
     */
    public abstract void spawnEnemies(ArrayList<Monster> enemiesList);

    public void prepareLevel(ArrayList<Coin> coinsList, ArrayList<Monster> enemiesList, Player player) {
        spawnCoins(coinsList, amountOfSmallCoins, amountOfNormalCoins, amountOfBigCoins);
        spawnEnemies(enemiesList);
        setPlayerStartPosition(player);
    }

    public abstract void render(ArrayList<Monster> monsters, ArrayList<Coin> coins, ArrayList<Weapon> shots, Player player);

    public Point2D getDefaultPlayerPosition() {
        double positionX = manager.getRightFrameBorder() / 2;
        double positionY = manager.getBottomFrameBorder() / 2;
        return new Point2D(positionX, positionY);
    }

    public abstract void setPlayerStartPosition(Player player);

    public void defaultLevelRender(ArrayList<Monster> monsters, ArrayList<Coin> coins, ArrayList<Weapon> shots, int playerScore) {
        renderBackground(backgroundImage);
        renderScoreText(playerScore);
        renderCoins(coins);
        renderShots(shots);
        renderMonsters(monsters);
    }

    public void renderBackground(Image backgroundImage) {
        getManager().getGraphicContext().drawImage(backgroundImage, 0, 0);
    }

    public void renderShots(ArrayList<Weapon> weapons) {
        Iterator<Weapon> shots = weapons.iterator();
        while (shots.hasNext())
            shots.next().render();
    }

    private void updateShots(ArrayList<Weapon> shotsList, double elapsedTime) {
        Iterator<Weapon> shotsIterator = shotsList.iterator();
        while (shotsIterator.hasNext()) {
            Weapon shot = shotsIterator.next();
            shot.update(elapsedTime);

            if (shot.isOutOfBoundary()) {
                shot.doOutOfBoundaryAction();
                shotsIterator.remove();
            }
        }
    }

    public void renderText(String text, Color color, double posX, double posY) {
        manager.getGraphicContext().setFill(color);
        manager.getGraphicContext().fillText(text, posX, posY);
    }

    public void renderScoreText(int score) {
        String pointsText = MONEY_COMMUNICATE + score;
        double posX = manager.getLeftFrameBorder();
        double posY = manager.getTopFrameBorder() + 75;
        renderText(pointsText, Color.TAN, posX, posY);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public void setAmountOfCoins(int amountOfSmallCoins, int amountOfNormalCoins, int amountOfBigCoins) {
        this.amountOfSmallCoins = amountOfSmallCoins;
        this.amountOfNormalCoins = amountOfNormalCoins;
        this.amountOfBigCoins = amountOfBigCoins;
        this.amountOfAllCoins = amountOfSmallCoins + amountOfNormalCoins + amountOfBigCoins;
    }
}
