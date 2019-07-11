/*
    todo:
        - responsywnosc na wszystkie rozdzielczosci
        - nowe levele
        - nowi przeciwnicy
        - ekrany tutorialowe (opis potworow, sterowanie, bonusy, tipy)

*/

//Todo: LAST DIRECTION NA ENUM

package com.Ukasz09.ValentineGame.gameModules.gameUtils;

import com.Ukasz09.ValentineGame.gameModules.levels.*;
import com.Ukasz09.ValentineGame.gameModules.sprites.creatures.Monster;
import com.Ukasz09.ValentineGame.gameModules.sprites.creatures.Sprite;
import com.Ukasz09.ValentineGame.gameModules.sprites.creatures.Player;
import com.Ukasz09.ValentineGame.gameModules.sprites.others.MoneyBag;
import com.Ukasz09.ValentineGame.gameModules.sprites.weapons.BombSprite;
import com.Ukasz09.ValentineGame.gameModules.sprites.weapons.BulletSprite;
import com.Ukasz09.ValentineGame.gameModules.sprites.weapons.ShotSprite;
import com.Ukasz09.ValentineGame.gameModules.wrappers.IntValue;
import com.Ukasz09.ValentineGame.gameModules.wrappers.LongValue;
import com.Ukasz09.ValentineGame.graphicModule.texturesPath.SpritesImages;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.util.ArrayList;

public class Game extends Application {
    private ViewManager manager;
    private ArrayList<MoneyBag> moneybagList;
    private ArrayList<Monster> monsters;
    private ArrayList<String> input;
    private ArrayList<ShotSprite> playerShots;

    private double elapsedTime;
    private LongValue lastNanoTime;

    private double bulletOverheating;

    private int levelNumber;                //numer okreslajacy level
    private Levels actualLevel;
    private Panels actualPanel;
    private IntValue score;                  //wynik czesciowy za poziom
    private IntValue antiCollisionTimer;     //czas w jakim gracz moze przelatywac przez potwory (zapobiega zablokowaniu ruchu gracza)
    private IntValue killedMonstersOnLevel;
    private IntValue collectedMoneybags;
    private boolean showTutorialPage;
    private boolean playerReadyToGame;

    private Player player;
    private ShotSprite ukaszShotSprite;

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public Game() {
        manager = new ViewManager(); //do NOT touch

        //Game components
        input = new ArrayList<>();
        lastNanoTime = new LongValue(System.nanoTime());

        player = new Player(SpritesImages.playerRightImage, SpritesImages.playerLeftImage, SpritesImages.playerShieldImage, manager);
        playerShots = new ArrayList<>();
        antiCollisionTimer = new IntValue(0);

        moneybagList = new ArrayList<>();
        collectedMoneybags = new IntValue(0);

        monsters = new ArrayList<>();
        killedMonstersOnLevel = new IntValue(0);

        levelNumber = 0;
        bulletOverheating = 0;
        showTutorialPage = false;
        playerReadyToGame = false;
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @Override
    public void start(Stage theStage) {
        manager.initialize("Valentines Game", true); //do NOT touch
        theStage = manager.getMainStage();  //do NOT touch
        manager.readKeyboardAction(input);

        actualPanel = new StartPanel(manager);
        actualPanel.makeLevel();

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        class AnimationTimer2 extends AnimationTimer {

            @Override
            public void handle(long currentNanoTime) {

                switch (levelNumber) {

                    //EKRAN STARTOWY
                    case 0: {

                        if (playerReadyToGame == false) {

                            actualPanel.renderLevel();

                            if (input.contains("ENTER"))
                                playerReadyToGame = true;
                        }
//                                else showTutorialPage=true;
//
//                                if(showTutorialPage==true){
//
//                                    //  showTutorialPage(tutorialImages[], amountOfTutPage);
//                                }

                        else {

                            //Ustawienia dla poziomu 1
                            actualPanel.endLevel();
                            actualPanel = null;

                            score = new IntValue(0);
                            double centerPositionX = manager.getRightBorder() / 2 - player.getWidth();
                            double centerPositionY = manager.getBottomBorder() / 2 - player.getHeight();

                            player.setPosition(centerPositionX, centerPositionY);

                            actualLevel = new Level_1(manager);
                            actualLevel.makeLevel(moneybagList, monsters);

                            collectedMoneybags.setValue(0);
                            playerShots.clear();

                            levelNumber++;
                        }

                    }
                    break;

                    //POZIOM 1
                    case 1: {

                        if (!levelIsEnd(actualLevel))
                            play(currentNanoTime, actualLevel);

                        else {

                            //Przygotowanie nastepnego poziomu

                            endLevel(actualLevel);
                            actualLevel = new Level_2(manager);
                            actualLevel.makeLevel(moneybagList, monsters);
                        }


                    }
                    break;

                    //POZIOM2
                    case 2: {

                        if (!levelIsEnd(actualLevel)) {

                            if (((Level_2) actualLevel).needToSpawnMiniboss(collectedMoneybags.getValue(), monsters.isEmpty()))
                                ((Level_2) actualLevel).spawnMiniboss(monsters);

                            play(currentNanoTime, actualLevel);

                        } else {

                            //Przygotowanie do uruchomienia ekranu koncowego
                            actualPanel = new EndPanel(manager);
                            actualPanel.makeLevel();
                            endLevel(actualLevel);
                            actualLevel = null;
                        }

                    }
                    break;

                    //EKRAN KONCOWY
                    default: {

                        actualPanel.renderLevel();
                    }
                } //zamyka switcha

            } //zamyka handle

        } //zamyka wewnetrzna klase

        new AnimationTimer2().start();
        theStage.show();

    }

    //ustawia odpowiednio zmienne przed rozpoczeciem nowego levelu
    public void endLevel(Levels level) {
        level.endLevel();
        levelNumber++;
        player.addTotalScore(score.getValue());
        score.setValue(0);
        collectedMoneybags.setValue(0);
        moneybagList.clear();
        monsters.clear();
        playerShots.clear();
        killedMonstersOnLevel.setValue(0);
    }

    //sprawdza klikniety klawisz i wykonuje akcje dla niego
    public void checkPlayerMove(int velocity) {

        player.setVelocity(0, 0);
        boolean playerCantDoAnyMove = Collision.collisionFromAllDirection(monsters, player, manager);

        if (playerCantDoAnyMove)
            antiCollisionTimer.setValue(Collision.getDefaultAntiCollisionsTimer());

        if (input.contains("A")) {

            //kolizja z ramka
            if (!player.boundaryCollisionFromLeft(manager.getLeftBorder())) {
                player.setActualImage(player.playerLeftImage);
                player.setLastDirectionX("A");

                if ((Collision.collisionWithMonstersFromRight(monsters, player) == false) || (antiCollisionTimer.getValue() > 0))
                    player.addVelocity(-velocity, 0);
            }
        }

        if (input.contains("D")) {

            //kolizja z ramka
            if (!player.boundaryCollisionFromRight(manager.getRightBorder())) {
                player.setActualImage(player.playerRightImage);
                player.setLastDirectionX("D");

                if ((Collision.collisionWithMonstersFromLeft(monsters, player) == false) || (antiCollisionTimer.getValue() > 0))
                    player.addVelocity(velocity, 0);
            }
        }

        if (input.contains("W")) {

            //kolizja z ramka
            if (!player.boundaryCollisionFromTop(manager.getTopBorder())) {
                player.setLastDirectionY("W");

                if ((Collision.collisionWithMonstersFromBottom(monsters, player) == false) || (antiCollisionTimer.getValue() > 0))
                    player.addVelocity(0, -velocity);
            }
        }

        if (input.contains("S")) {

            //kolizja z ramka
            if (!player.boundaryCollisionFromBottom(manager.getBottomBorder())) {
                player.setLastDirectionY("W");

                if ((Collision.collisionWithMonstersFromTop(monsters, player) == false) || (antiCollisionTimer.getValue() > 0))
                    player.addVelocity(0, velocity);
            }
        }

        //dla dzialka
        if (input.contains("SPACE")) {

            if (bulletOverheating <= 0) {

                ukaszShotSprite = new BulletSprite(player.getLastDirectionX(), manager);
                ((BulletSprite) ukaszShotSprite).setPosition(player);
                ukaszShotSprite.setVelocity(ukaszShotSprite.getShotVelocity(), 0);

                playerShots.add(ukaszShotSprite);

                ukaszShotSprite.playShotSound();

                bulletOverheating = ((BulletSprite) ukaszShotSprite).getMaxOverheating();
            }
        }

        //Dla bomby
        if (input.contains("P")) {

            if (player.getBombOverheating() <= 0) {

                ukaszShotSprite = new BombSprite(manager);
                ((BombSprite) ukaszShotSprite).setPosition(player);
                ukaszShotSprite.setVelocity(0, ukaszShotSprite.getShotVelocity());

                playerShots.add(ukaszShotSprite);

                ukaszShotSprite.playShotSound();

                player.overheatBomb();
            }
        }
    }

    public boolean playerReadyToGame() {

        if (input.contains("ENTER")) return true;
        else return false;
    }

//    //keyboard listner
//    public void readKeyboardAction() {
//
//        //wcisniety klawisz
//        theScene.setOnKeyPressed(
//                new EventHandler<KeyEvent>() {
//                    public void handle(KeyEvent e) {
//                        String code = e.getCode().toString();
//
//                        // only add once... prevent duplicates
//                        if (!input.contains(code))
//                            input.add(code);
//                    }
//                });
//
//        //zwolniony klawisz
//        theScene.setOnKeyReleased(
//                new EventHandler<KeyEvent>() {
//                    public void handle(KeyEvent e) {
//                        String code = e.getCode().toString();
//                        input.remove(code);
//                    }
//                });
//
//    }

    //glowna metoda dla poszczegolnych poziomow
    public void play(long currentNanoTime, Levels level) {
        elapsedTime = (currentNanoTime - lastNanoTime.getValue()) / 1000000000.0;
        lastNanoTime.setValue(currentNanoTime);


        //detekcja kolizji z pieniedzmi
        Collision.checkMoneybagsCollisions(moneybagList, player, score, collectedMoneybags);

        //render poziomu (tlo, potwory)
        level.renderLevel(monsters);

        //render napisu
        String pointsText = "Kasa na walentynki: $" + (player.getTotalScore() + (score.getValue()));
        //gc.setFill(Color.TAN);
        manager.getGraphicContext().fillText(pointsText, ViewManager.WIDTH - 450, 70);

        //Dla strzalu
        if (bulletOverheating > 0)
            bulletOverheating -= 50;

        //render serc
        level.drawHearts(player);

        //render pieniedzy
        for (Sprite moneyBag : moneybagList)
            moneyBag.render();


        if (antiCollisionTimer.getValue() > 0)
            antiCollisionTimer.decValue(100);

        //kolizja z graczem
        Collision.playerCollisionWithMonster(monsters, player);

        //kolizja z pociskami
        Collision.playerShotCollision(monsters, playerShots, killedMonstersOnLevel);

        checkPlayerMove(Player.getDefaultVelocity());
        player.update(elapsedTime);
        player.render();
        level.updateShots(playerShots, elapsedTime);
        level.renderShots(playerShots);
        level.updateMonsters(player, monsters);

    }

//    public void setFont(String family, FontWeight weight, int size, Color color) {
//
//        Font font = Font.font(family, weight, size);
//
//        gc.setFont(font);
//        gc.setFill(color);
//    }

    public boolean levelIsEnd(Levels level) {

        if ((collectedMoneybags.getValue() < level.getHowManyMoneybags()) || (killedMonstersOnLevel.getValue() < level.getHowManyAllMonsters())) {
            return false;
        } else return true;
    }

    //wyswietla strony z tutorialem dla gracza
    public void showTutorialPage(Image[] tutorialPages, int howManyPages) {

        int i = 0;

        while (i < howManyPages) {

            manager.getGraphicContext().drawImage(tutorialPages[i], 0, 0);

            if (input.contains("ENTER"))
                i++;

        }

        showTutorialPage = false;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public static void main(String[] args) {

        Game gameTest = new Game();
        launch(args);
    }
}
