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
import com.Ukasz09.ValentineGame.gameModules.utilitis.wrappers.IntValue;
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
    // private ArrayList<ShotSprite> shotsList;

    private double elapsedTime;
    private LongValue lastNanoTime;

    private Levels actualLevel;
    private Panels actualPanel;
    private IntValue antiCollisionTimer;     //czas w jakim gracz moze przelatywac przez potwory (zapobiega zablokowaniu ruchu gracza)
    // private IntValue killedMonstersOnLevel;
    //private IntValue collectedMoneybags; //todo: wrzucic do playera

    private Player player;
    private ShotSprite playerShotSprite;

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public Game() {
        manager = new ViewManager(); //do NOT touch
        lastNanoTime = new LongValue(System.nanoTime());
        player = new Player(SpritesImages.playerRightImage, SpritesImages.playerLeftImage, SpritesImages.playerShieldImage, manager);
        //collectedMoneybags = new IntValue(0);
        //  killedMonstersOnLevel = new IntValue(0);

        inputsList = new ArrayList<>();
        // shotsList = new ArrayList<>();
        moneybagList = new ArrayList<>();
        monstersList = new ArrayList<>();

        antiCollisionTimer = new IntValue(0); //todo: wrzucic do playera
    }

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
                            endLevel(actualLevel);
                            actualLevel = new Level_2(manager);
                            actualLevel.makeLevel(moneybagList, monstersList);
                        }
                    }
                    break;

                    case 2: {
                        if (!actualLevel.isEnd(player)) {
                            if (((Level_2) actualLevel).needToSpawnMiniboss(player.getCollectedMoneyBagsOnLevel(), monstersList.isEmpty()))
                                ((Level_2) actualLevel).spawnMiniboss(monstersList);

                            play(currentNanoTime, actualLevel);
                        } else {
                            endLevel(actualLevel);
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

    //ustawia odpowiednio zmienne przed rozpoczeciem nowego levelu
    private void endLevel(Levels level) {
        player.setNextLevel();
        //collectedMoneybags.setValue(0);
        player.setCollectedMoneyBagsOnLevel(0);
        player.setKilledMonstersOnLevel(0);
        moneybagList.clear();
        monstersList.clear();
        player.clearShotsList();
    }

    private void checkPlayerMove(int velocity) {

        player.setVelocity(0, 0);
        boolean playerCantDoAnyMove = Collision.collisionFromAllDirection(monstersList, player, manager);

        if (playerCantDoAnyMove)
            antiCollisionTimer.setValue(Collision.getDefaultAntiCollisionsTimer());

        if (inputsList.contains("A")) {

            //kolizja z ramka
            if (!player.boundaryCollisionFromLeft(manager.getLeftBorder())) {
                player.setActualImage(player.playerLeftImage);
                player.setLastDirectionX("A");

                if ((Collision.collisionWithMonstersFromRight(monstersList, player) == false) || (antiCollisionTimer.getValue() > 0))
                    player.addVelocity(-velocity, 0);
            }
        }

        if (inputsList.contains("D")) {

            //kolizja z ramka
            if (!player.boundaryCollisionFromRight(manager.getRightBorder())) {
                player.setActualImage(player.playerRightImage);
                player.setLastDirectionX("D");

                if ((Collision.collisionWithMonstersFromLeft(monstersList, player) == false) || (antiCollisionTimer.getValue() > 0))
                    player.addVelocity(velocity, 0);
            }
        }

        if (inputsList.contains("W")) {

            //kolizja z ramka
            if (!player.boundaryCollisionFromTop(manager.getTopBorder())) {
                player.setLastDirectionY("W");

                if ((Collision.collisionWithMonstersFromBottom(monstersList, player) == false) || (antiCollisionTimer.getValue() > 0))
                    player.addVelocity(0, -velocity);
            }
        }

        if (inputsList.contains("S")) {

            //kolizja z ramka
            if (!player.boundaryCollisionFromBottom(manager.getBottomBorder())) {
                player.setLastDirectionY("W");

                if ((Collision.collisionWithMonstersFromTop(monstersList, player) == false) || (antiCollisionTimer.getValue() > 0))
                    player.addVelocity(0, velocity);
            }
        }

        //dla dzialka
        if (inputsList.contains("SPACE")) {

            if (player.getBulletOverheating() <= 0) {

                playerShotSprite = new BulletSprite(player.getLastDirectionX(), manager);
                playerShotSprite.setPosition(player);
                playerShotSprite.setVelocity(playerShotSprite.getShotVelocity(), 0);

                player.addShot(playerShotSprite);
                playerShotSprite.playShotSound();
                player.overheatBullet();
            }
        }

        //Dla bomby
        if (inputsList.contains("P")) {

            if (player.getBombOverheating() <= 0) {

                playerShotSprite = new BombSprite(manager);
                playerShotSprite.setPosition(player);
                playerShotSprite.setVelocity(0, playerShotSprite.getShotVelocity());

                player.addShot(playerShotSprite);
                playerShotSprite.playShotSound();
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
        player.checkCollision(moneybagList,monstersList);
//        Collision.checkMoneybagsCollisions(moneybagList, player);

        level.renderLevel(monstersList, moneybagList, player.getShotsList(), player.getTotalScore());

        if (antiCollisionTimer.getValue() > 0)
            antiCollisionTimer.decValue(100);

        Collision.playerCollisionWithMonster(monstersList, player);

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
//        score = new IntValue(0);
        double playerPositionX = startLevel.playerStartPosition().getX();
        double playerPositionY = startLevel.playerStartPosition().getY();
        player.setPosition(playerPositionX, playerPositionY);
//        collectedMoneybags.setValue(0);
//        playerShotsList.clear();
        startLevel.makeLevel(moneybagList, monstersList);
        startLevel.playBackgroundSound();
        Levels.playWingsSound();
    }

    private Levels chooseLevel(int levelNumber) {
        player.setLevelNumber(levelNumber);
        switch (levelNumber) {
            case 1: {
                return new Level_1(manager);
            }
            case 2: {
                return new Level_2(manager);
            }

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
