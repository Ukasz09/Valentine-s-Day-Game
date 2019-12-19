package com.Ukasz09.ValentineGame.gameModules.utilitis;

import com.Ukasz09.ValentineGame.gameModules.levels.*;
import com.Ukasz09.ValentineGame.gameModules.sprites.creatures.Monster;
import com.Ukasz09.ValentineGame.gameModules.sprites.creatures.Player;
import com.Ukasz09.ValentineGame.gameModules.sprites.items.others.Coin;
import com.Ukasz09.ValentineGame.graphicModule.texturesPath.ImageSheetProperty;
import com.Ukasz09.ValentineGame.graphicModule.texturesPath.SpritesProperties;

import com.Ukasz09.ValentineGame.soundsModule.soundsPath.SoundsPlayer;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.stage.Stage;

import java.util.ArrayList;

public class Game extends Application {
    private static final ImageSheetProperty PLAYER_SHEET_PROPERTY = SpritesProperties.playerUkaszSheetProperty();
    private static final ImageSheetProperty PLAYER_SHIELD_PROPERTY = SpritesProperties.playerShieldSheetProperty();
    private static String GAME_TITLE = "Valentines Game";

    private ViewManager manager;
    private ArrayList<Coin> coinsList;
    private ArrayList<Monster> enemiesList;
    private ArrayList<String> inputsList;
    private double elapsedTime;
    private double lastNanoTime;
    private AllLevels actualLevel;
    private Panels actualPanel;
    private Player player;
    private SoundsPlayer backgroundLevelSound;

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public Game() {
        manager = new ViewManager(); //do NOT touch
        player = new Player(PLAYER_SHEET_PROPERTY, PLAYER_SHIELD_PROPERTY, manager);
        inputsList = new ArrayList<>();
        coinsList = new ArrayList<>();
        enemiesList = new ArrayList<>();
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @Override
    public void start(Stage theStage) {
        manager.initialize(GAME_TITLE, true); //do NOT touch
        theStage = manager.getMainStage();  //do NOT touch
        manager.readKeyboardAction(inputsList);

        int levelNumber = 0;
        if (levelNumber == 0) {
            actualPanel = new StartPanel(manager);
            actualPanel.playBackgroundSound();
        } else startGame(levelNumber);

        class gameAnimationTimer extends AnimationTimer {
            @Override
            public void handle(long currentNanoTime) {
                switch (player.getLevelNumber()) {
                    case 0: {
                        if (!playerReadyToGame())
                            actualPanel.render();
                        else {
                            actualPanel.stopBackgroundSound();
                            actualPanel = null;
                            startGame(1);
                        }
                    }
                    break;

                    case 1: {
                        if (!actualLevel.levelIsEnd(player))
                            play(currentNanoTime, actualLevel);
                        else {
                            endLevel();
                            actualLevel = new Level_1(manager, 30);
                            actualLevel.prepareLevel(coinsList, enemiesList, player);
                        }
                    }
                    break;

                    case 2: {
                        if (!actualLevel.levelIsEnd(player))
                            play(currentNanoTime, actualLevel);
                        else {
                            endLevel();
                            actualLevel = new Level_2(manager, 15, 0);
                            actualLevel.prepareLevel(coinsList, enemiesList, player);
                        }
                    }
                    break;

                    case 3: {
                        if (!actualLevel.levelIsEnd(player))
                            play(currentNanoTime, actualLevel);
                        else {
                            endLevel();
                            actualLevel = new Level_2(manager);
                            actualLevel.prepareLevel(coinsList, enemiesList, player);
                        }
                    }
                    break;


                    case 4: {
                        if (!actualLevel.levelIsEnd(player))
                            play(currentNanoTime, actualLevel);
                        else {
                            endLevel();
                            player.stopWingsSound();
                            backgroundLevelSound.stopSound();
                            actualPanel = new EndWinPanel(manager);
                            actualPanel.playBackgroundSound();
                        }
                    }
                    break;

                    case 5: {
                        if (!actualLevel.levelIsEnd(player))
                            play(currentNanoTime, actualLevel);
                        else {
                            endLevel();
                            player.stopWingsSound();
                            backgroundLevelSound.stopSound();
                            actualPanel = new EndGameOverPanel(manager);
                            actualPanel.playBackgroundSound();
                        }
                    }
                    break;

                    default: {
                        actualPanel.render();
                    }
                }
            }
        }

        new gameAnimationTimer().start();
        theStage.show();
    }

    private void endLevel() {
        player.setNextLevel();
        player.setCollectedCoinsOnLevel(0);
        player.setKilledMonstersOnLevel(0);
        coinsList.clear();
        enemiesList.clear();
        player.clearShotsList();
    }

    private void checkPlayerActions() {
        checkPlayerMove();
        checkPlayerCombatActions();
    }

    private void checkPlayerMove() {
        player.setActualVelocity(0, 0);

        if (inputsList.contains("A"))
            doPlayerMoveAction(DirectionEnum.LEFT, player.getVelocityXPoints());
        else player.setPressedKey_A(false);

        if (inputsList.contains("D"))
            doPlayerMoveAction(DirectionEnum.RIGHT, player.getVelocityXPoints());
        else player.setPressedKey_D(false);

        if (inputsList.contains("W"))
            doPlayerMoveAction(DirectionEnum.UP, player.getVelocityYPoints());
        else player.setPressedKey_W(false);

        if (inputsList.contains("S"))
            doPlayerMoveAction(DirectionEnum.DOWN, player.getVelocityYPoints());
        else player.setPressedKey_S(false);

    }

    private void doPlayerMoveAction(DirectionEnum side, double playerVelocity) {
        player.setPressedKey(side.keypadEquivalent, true);
        player.setImageDirection(side);

        if (!player.anticollisionModeIsActive()) {
            if (player.frameCollision(side) || player.collisionWithMonster(side, enemiesList)) {
                player.setCollision(side, true);
                if (player.cannotDoAnyMove()) {
                    player.restoreAnticollisionTimer();
                    player.setAllCollision(false);
                }
            } else {
                player.setCollision(side, false);
                player.addActualVelocity(side, playerVelocity);
            }
        } else if (!player.frameCollision(side))
            player.addActualVelocity(side, playerVelocity);
    }

    private void checkPlayerCombatActions() {
        if (inputsList.contains("LEFT")) {
            player.setImageDirection(DirectionEnum.LEFT);
            spawnBullet();
        }

        if (inputsList.contains("RIGHT")) {
            player.setImageDirection(DirectionEnum.RIGHT);
            spawnBullet();
        }

        if (inputsList.contains("SPACE"))
            spawnBomb();
    }

    private void spawnBullet() {
        if (player.bulletCanBeSpawn())
            player.addBullet();
    }

    private void spawnBomb() {
        if (player.bombCanBeSpawn())
            player.addBomb();
    }

    private boolean playerReadyToGame() {
        return (inputsList.contains("ENTER"));
    }

    private void play(long currentNanoTime, AllLevels level) {
        updateTime(currentNanoTime);
        level.render(enemiesList, coinsList, player.getShotsList(), player);
        checkPlayerActions();
        player.update(elapsedTime, coinsList, enemiesList);
        player.render();
        level.update(player, enemiesList, coinsList, elapsedTime);
    }

    private void updateTime(long currentNanoTime) {
        elapsedTime = (currentNanoTime - lastNanoTime) / 1000000000.0;
        lastNanoTime = currentNanoTime;
    }

    private AllLevels chooseLevel(int levelNumber) {
        player.setLevelNumber(levelNumber);
        switch (levelNumber) {
            case 1:
                return new Level_1(manager);

            case 2:
                return new Level_2(manager);

            default: {
                player.setLevelNumber(1);
                return new Level_1(manager);
            }
        }
    }

    private void startGame(int levelNumber) {
        actualLevel = chooseLevel(levelNumber);
        actualLevel.prepareLevel(coinsList, enemiesList, player);
        backgroundLevelSound= actualLevel.getBackgroundSound();
        backgroundLevelSound.playSound();
        if (player != null)
            player.playWingsSound();
        else Logger.logError(getClass(), player.toString() + " is null");
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public static void main(String[] args) {
        launch(args);
    }
}
