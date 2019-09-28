/*
    todo:
        - responsywnosc na wszystkie rozdzielczosci
        - nowe levele
        - nowi przeciwnicy
        - ekrany tutorialowe (opis potworow, sterowanie, bonusy, tipy)

*/

package com.Ukasz09.ValentineGame.gameModules.utilitis;

import com.Ukasz09.ValentineGame.gameModules.levels.*;
import com.Ukasz09.ValentineGame.gameModules.sprites.creatures.Monster;
import com.Ukasz09.ValentineGame.gameModules.sprites.creatures.Player;
import com.Ukasz09.ValentineGame.gameModules.sprites.creatures.Creature;
import com.Ukasz09.ValentineGame.gameModules.sprites.items.others.Coin;
import com.Ukasz09.ValentineGame.gameModules.sprites.items.weapons.Bomb;
import com.Ukasz09.ValentineGame.gameModules.sprites.items.weapons.Bullet;
import com.Ukasz09.ValentineGame.gameModules.sprites.items.weapons.Weapon;
import com.Ukasz09.ValentineGame.graphicModule.texturesPath.SpritesImages;

import com.Ukasz09.ValentineGame.soundsModule.soundsPath.SoundsPlayer;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.stage.Stage;

import java.util.ArrayList;

public class Game extends Application {
    private ViewManager manager;
    private ArrayList<Coin> coinsList;
    private ArrayList<Monster> enemiesList;
    private ArrayList<String> inputsList;

    private double elapsedTime;
    private LongValue lastNanoTime;

    private AllLevels actualLevel;
    private Panels actualPanel;

    private Player player;
    private SoundsPlayer backgroundSound;
//    private static SoundsPlayer effectSound; //to avoid remove object by garbage collctor before sound effect end

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public Game() {
        manager = new ViewManager(); //do NOT touch
        lastNanoTime = new LongValue(System.nanoTime());
        player = new Player(SpritesImages.playerRightImage, SpritesImages.playerShieldImage, manager);
        inputsList = new ArrayList<>();
        coinsList = new ArrayList<>();
        enemiesList = new ArrayList<>();
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
            backgroundSound = actualPanel.getBackgroundSound();
            backgroundSound.playSound();
        } else startGame(levelNumber);

        class gameAnimationTimer extends AnimationTimer {
            @Override
            public void handle(long currentNanoTime) {
                switch (player.getLevelNumber()) {
                    case 0: {
                        if (!playerReadyToGame())
                            actualPanel.render();
                        else {
                            backgroundSound.stopSound();
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
                            actualLevel = new Level_2(manager);
                            actualLevel.prepareLevel(coinsList, enemiesList, player);
                        }
                    }
                    break;

                    case 2: {
                        if (!actualLevel.levelIsEnd(player))
                            play(currentNanoTime, actualLevel);
                        else {
                            endLevel();
                            backgroundSound.stopSound();
                            player.stopWingsSound();
                            actualPanel = new EndPanel(manager);
                            backgroundSound = actualPanel.getBackgroundSound();
                            backgroundSound.playSound();
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
        player.setCollectedMoneyBagsOnLevel(0);
        player.setKilledMonstersOnLevel(0);
        coinsList.clear();
        enemiesList.clear();
        player.clearShotsList();
    }

    private void checkPlayerMove(int velocity) {
        player.setVelocity(0, 0);

        if (inputsList.contains("A")) {

            player.setPressedKey_A(true);

            //TODO: dac wyzej (?)
            if (!player.leftSideFrameCollision()) {
                player.setImageDirection(Creature.YAxisDirection.LEFT); //must be before setting image

                if ((!player.collisionWithMonstersFromLeftSide(enemiesList)) || (player.checkPlayerCanDoAnyMove())) {
                    player.addVelocity(-velocity, 0);
                    player.setCollisionFromLeftSide(false);
                } else player.setCollisionFromLeftSide(true);
            } else player.setCollisionFromLeftSide(true);
        } else player.setPressedKey_A(false);

        if (inputsList.contains("D")) {

            player.setPressedKey_D(true);

            if (!player.boundaryCollisionFromRightSide()) {
                player.setImageDirection(Creature.YAxisDirection.RIGHT);

                if ((!player.collisionWithMonstersFromRightSide(enemiesList)) || (player.checkPlayerCanDoAnyMove())) {
                    player.addVelocity(velocity, 0);
                    player.setCollisionFromRightSide(false);
                } else player.setCollisionFromRightSide(true);
            } else player.setCollisionFromRightSide(true);
        } else player.setPressedKey_D(false);

        if (inputsList.contains("W")) {

            player.setPressedKey_W(true);

            if (!player.boundaryCollisionFromTop()) {

                if ((!player.collisionWithMonstersFromBottom(enemiesList, player)) || (player.checkPlayerCanDoAnyMove())) {
                    player.addVelocity(0, -velocity);
                    player.setCollisionFromUpSide(false);
                } else player.setCollisionFromUpSide(true);
            } else player.setCollisionFromUpSide(true);
        } else player.setPressedKey_W(false);

        if (inputsList.contains("S")) {

            player.setPressedKey_S(true);

            if (!player.boundaryCollisionFromBottom()) {

                if ((!player.collisionWithMonstersFromTop(enemiesList)) || (player.checkPlayerCanDoAnyMove())) {
                    player.addVelocity(0, velocity);
                    player.setCollisionFromDownSide(false);
                } else player.setCollisionFromDownSide(true);
            } else player.setCollisionFromDownSide(true);
        } else player.setPressedKey_S(false);

        if (inputsList.contains("SPACE")) {
            if (player.getBulletOverheating() <= 0) {
                Weapon weapon = new Bullet(player.getImageDirection(), manager);
                weapon.prepareToShot(player);

                player.addShot(weapon);
                player.overheatBullet();
            }
        }

        if (inputsList.contains("P")) {
            if (player.getBombOverheating() <= 0) {
                Weapon weapon = new Bomb(manager);
                weapon.prepareToShot(player);

                player.addShot(weapon);
                player.overheatBomb();
            }
        }
    }

    private boolean playerReadyToGame() {
        if (inputsList.contains("ENTER")) return true;
        else return false;
    }

    //todo: poprawic
    private void play(long currentNanoTime, AllLevels level) {
        updateTime(currentNanoTime);
        level.render(enemiesList, coinsList, player.getShotsList(), player);
        checkPlayerMove(Player.getDefaultVelocity());
        player.update(elapsedTime, coinsList, enemiesList);
        player.render();
        level.update(player, enemiesList, elapsedTime);

    }

    private void updateTime(long currentNanoTime) {
        elapsedTime = (currentNanoTime - lastNanoTime.getValue()) / 1000000000.0;
        lastNanoTime.setValue(currentNanoTime);
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
        backgroundSound = actualLevel.getBackgroundSound();
        backgroundSound.playSound();

        if (player != null)
            player.playWingsSound();
        else System.out.println(player.toString() + " is null");
    }

//    public static void playEffectSound(SoundsPlayer sound){
//        effectSound=sound;
//        effectSound.playSound();
//    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public static void main(String[] args) {
        Game gameTest = new Game();
        launch(args);
    }
}
