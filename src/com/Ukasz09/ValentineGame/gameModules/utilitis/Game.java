/*
    todo:
        - responsywnosc na wszystkie rozdzielczosci
        - nowe levele
        - nowi przeciwnicy
        - ekrany tutorialowe (opis potworow, sterowanie, bonusy, tipy)

*/

//Todo: LAST DIRECTION NA ENUM

package com.Ukasz09.ValentineGame.gameModules.utilitis;

import com.Ukasz09.ValentineGame.gameModules.levels.*;
import com.Ukasz09.ValentineGame.gameModules.sprites.creatures.Monster;
import com.Ukasz09.ValentineGame.gameModules.sprites.creatures.Player;
import com.Ukasz09.ValentineGame.gameModules.sprites.items.MoneyBag;
import com.Ukasz09.ValentineGame.gameModules.sprites.weapons.BombSprite;
import com.Ukasz09.ValentineGame.gameModules.sprites.weapons.BulletSprite;
import com.Ukasz09.ValentineGame.gameModules.sprites.weapons.ShotSprite;
import com.Ukasz09.ValentineGame.gameModules.utilitis.wrappers.LongValue;
import com.Ukasz09.ValentineGame.graphicModule.texturesPath.SpritesImages;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.stage.Stage;

import java.util.ArrayList;

public class Game extends Application {
    private ViewManager manager;
    private ArrayList<MoneyBag> moneybagList;
    private ArrayList<Monster> monstersList;
    private ArrayList<String> inputsList;

    private double elapsedTime;
    private LongValue lastNanoTime;

    private Levels actualLevel;
    private Panels actualPanel;

    private Player player;

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public Game() {
        manager = new ViewManager(); //do NOT touch
        lastNanoTime = new LongValue(System.nanoTime());
        player = new Player(SpritesImages.playerRightImage, SpritesImages.playerLeftImage, SpritesImages.playerShieldImage, manager);
        inputsList = new ArrayList<>();
        moneybagList = new ArrayList<>();
        monstersList = new ArrayList<>();
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @Override
    public void start(Stage theStage) {
        manager.initialize("Valentines Game", true); //do NOT touch
        theStage = manager.getMainStage();  //do NOT touch
        manager.readKeyboardAction(inputsList);

        //todo: dodac mozliwosc wczytwywania poziomu z pliku
        int levelNumber = 0;
        if (levelNumber == 0) {
            actualPanel = new StartPanel(manager);
            actualPanel.makePanel();
        } else startGame(levelNumber);

        class gameAnimationTimer extends AnimationTimer {
            @Override
            public void handle(long currentNanoTime) {
                switch (player.getLevelNumber()) {
                    case 0: {
                        if (!playerReadyToGame())
                            actualPanel.renderPanel();
                        else {
                            actualPanel.endPanel();
                            actualPanel = null;
                            startGame(1);
                        }
                    }
                    break;

                    case 1: {
                        if (!actualLevel.isEnd(player))
                            play(currentNanoTime, actualLevel);
                        else {
                            endLevel();
                            actualLevel = new Level_2(manager);
                            actualLevel.makeLevel(moneybagList, monstersList);
                        }
                    }
                    break;

                    case 2: {
                        if (!actualLevel.isEnd(player))
                            play(currentNanoTime, actualLevel);
                        else {
                            endLevel();
                            actualLevel = null;
                            actualPanel = new EndPanel(manager);
                            actualPanel.makePanel();
                        }
                    }
                    break;

                    default: {
                        actualPanel.renderPanel();
                    }
                }
            }
        }

        new gameAnimationTimer().start();
        theStage.show();
    }

    private void endLevel() {
        player.setNextLevel();
        player.setCollectedMoneyBagsOnLevel(0);
        player.setKilledMonstersOnLevel(0);
        moneybagList.clear();
        monstersList.clear();
        player.clearShotsList();
    }

    private void checkPlayerMove(int velocity) {
        player.setVelocity(0, 0);

        if (inputsList.contains("A")) {
            if (!player.boundaryCollisionFromLeftSide(manager.getLeftBorder())) {
                player.setLastDirectionX("A"); //must be before setting image
                player.setProperActualImage();

                if ((!player.collisionWithMonstersFromLeftSide(monstersList)) || (player.checkPlayerCanDoAnyMove())) {
                    player.addVelocity(-velocity, 0);
                    player.setCollisionFromLeftSide(false);
                } else player.setCollisionFromLeftSide(true);
            } else player.setCollisionFromLeftSide(true);
        }

        if (inputsList.contains("D")) {
            if (!player.boundaryCollisionFromRightSide(manager.getRightBorder())) {
                player.setLastDirectionX("D");
                player.setProperActualImage();

                if ((!player.collisionWithMonstersFromRightSide(monstersList)) || (player.checkPlayerCanDoAnyMove())) {
                    player.addVelocity(velocity, 0);
                    player.setCollisionFromRightSide(false);
                } else player.setCollisionFromRightSide(true);
            } else player.setCollisionFromRightSide(true);
        }

        if (inputsList.contains("W")) {
            if (!player.boundaryCollisionFromTop(manager.getTopBorder())) {

                if ((!player.collisionWithMonstersFromBottom(monstersList, player)) || (player.checkPlayerCanDoAnyMove())) {
                    player.addVelocity(0, -velocity);
                    player.setCollisionFromUpSide(false);
                } else player.setCollisionFromUpSide(true);
            } else player.setCollisionFromUpSide(true);
        }

        if (inputsList.contains("S")) {
            if (!player.boundaryCollisionFromBottom(manager.getBottomBorder())) {

                if ((!player.collisionWithMonstersFromTop(monstersList)) || (player.checkPlayerCanDoAnyMove())) {
                    player.addVelocity(0, velocity);
                    player.setCollisionFromDownSide(false);
                } else player.setCollisionFromDownSide(true);
            } else player.setCollisionFromDownSide(true);
        }

        if (inputsList.contains("SPACE")) {
            if (player.getBulletOverheating() <= 0) {
                ShotSprite shotSprite = new BulletSprite(player.getLastDirectionX(), manager);
                shotSprite.prepareToShot(player);

                player.addShot(shotSprite);
                shotSprite.playShotSound();
                player.overheatBullet();
            }
        }

        if (inputsList.contains("P")) {
            if (player.getBombOverheating() <= 0) {
                ShotSprite shotSprite = new BombSprite(manager);
                shotSprite.prepareToShot(player);

                player.addShot(shotSprite);
                shotSprite.playShotSound();
                player.overheatBomb();
            }
        }
    }

    private boolean playerReadyToGame() {
        if (inputsList.contains("ENTER")) return true;
        else return false;
    }

    private void play(long currentNanoTime, Levels level) {
        calculateTime(currentNanoTime);
        player.checkCollision(moneybagList, monstersList);
        level.renderLevel(monstersList, moneybagList, player.getShotsList(), player);
        checkPlayerMove(Player.getDefaultVelocity());
        player.update(elapsedTime);
        player.render();
        level.updateShots(player.getShotsList(), elapsedTime);
        level.updateMonsters(player, monstersList);

    }

    private void calculateTime(long currentNanoTime) {
        elapsedTime = (currentNanoTime - lastNanoTime.getValue()) / 1000000000.0;
        lastNanoTime.setValue(currentNanoTime);
    }

    private void prepareGame(Levels startLevel) {
        double playerPositionX = startLevel.playerStartPosition().getX();
        double playerPositionY = startLevel.playerStartPosition().getY();
        player.setPosition(playerPositionX, playerPositionY);
        startLevel.makeLevel(moneybagList, monstersList);
        startLevel.playBackgroundSound();
        Levels.playWingsSound();
    }

    private Levels chooseLevel(int levelNumber) {
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
        prepareGame(actualLevel);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public static void main(String[] args) {
        Game gameTest = new Game();
        launch(args);
    }
}
