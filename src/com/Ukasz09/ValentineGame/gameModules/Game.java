/*
    todo:
        - responsywnosc na wszystkie rozdzielczosci
        - nowe levele
        - nowi przeciwnicy
        - ekrany tutorialowe (opis potworow, sterowanie, bonusy, tipy)
*/

package com.Ukasz09.ValentineGame.gameModules;

//pakiety

import com.Ukasz09.ValentineGame.gameModules.levels.AllLevel;
import com.Ukasz09.ValentineGame.gameModules.levels.Level_1;
import com.Ukasz09.ValentineGame.gameModules.levels.Level_2;
import com.Ukasz09.ValentineGame.soundsModule.SoundsPath;
import com.Ukasz09.ValentineGame.gameModules.sprites.*;
import com.Ukasz09.ValentineGame.gameModules.sprites.creatures.FishMonsterMiniboss;
import com.Ukasz09.ValentineGame.gameModules.sprites.creatures.Monster;
import com.Ukasz09.ValentineGame.gameModules.sprites.creatures.Sprite;
import com.Ukasz09.ValentineGame.gameModules.sprites.creatures.Ukasz;
import com.Ukasz09.ValentineGame.gameModules.sprites.others.MoneyBag;
import com.Ukasz09.ValentineGame.gameModules.sprites.weapons.BombSprite;
import com.Ukasz09.ValentineGame.gameModules.sprites.weapons.BulletSprite;
import com.Ukasz09.ValentineGame.gameModules.sprites.weapons.ShootSprite;
import com.Ukasz09.ValentineGame.gameModules.wrappers.IntValue;
import com.Ukasz09.ValentineGame.gameModules.wrappers.LongValue;
import com.Ukasz09.ValentineGame.soundsModule.Sounds;

//javaFx
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

//pozostale
import java.util.ArrayList;

public class Game extends Application {

    //sprites
    private Image ukaszLeftImage;
    private Image ukaszRightImage;
    private Image backgroundImage;
    private Image moneyBagImage1;
    private Image moneyBagImage2;
    private Image endImage;
    private Image kasiaImage;
    private Image startImage;
    private Image heartFlareImage;
    private Image kasiaWingsImage;
    private Image ukaszShotImage;           //Bullet
    private Image[] ukaszBombShotImages;
    private Image littleMonsterImage;
    private Image fishMonsterRightImage;
    private Image fishMonsterLeftImage;
    private Image fishMonsterBottomImage;
    private Image fishMonsterTopImage;
    private Image fishMonsterMinibossRightImage;
    private Image fishMonsterMinibossLeftImage;
    private Image fishMonsterMinibossBottomImage;
    private Image fishMonsterMinibossTopImage;
    private Image ukaszShieldImage;
    private Image heartFullImage;
    private Image heartHalfImage;
    private Image heartEmptyImage;
    private Image[] batteryImages;
    private Image fishMinibossShieldImage;

    //dzwiek
    private Sounds ukaszWingsSound;
    private Sounds backgroundSound;
    private Sounds backgroundStartSound;
    private Sounds collectMoneySound;
    private Sounds backgroundEndSound;
    private Sounds bulletShotSound;
    private Sounds bombShotSound;

    //fx components
    Scene theScene;
    GraphicsContext gc;
    Canvas canvas;
    Group root;

    //komponenty gry
    private ArrayList<MoneyBag> moneybagList;
    private ArrayList<Monster> monsters;
    private ArrayList<String> input;            //Przechowuje wybrane klawisze (bez powtorek)
    private ArrayList<ShootSprite> ukaszShots;  //oddane strzaly gracza

    private double elapsedTime;
    private LongValue lastNanoTime;

    private double bulletOverheating;
    private double bombOverheating;

    private int levelNumber;                //numer okreslajacy level
    private Level_1 level1;
    private Level_2 level2;
    private IntValue score;                  //wynik czesciowy za poziom
    private IntValue antiCollisionTimer;     //czas w jakim gracz moze przelatywac przez potwory (zapobiega zablokowaniu ruchu gracza)
    private IntValue killedMonstersOnLevel;
    private IntValue collectedMoneybags;
    private boolean playerFightWithBoss;
    private boolean showTutorialPage;
    private boolean playerReadyToGame;

    private Ukasz ukasz;
    private ShootSprite ukaszShotSprite;

    //na czas testow
    private boolean testMode;

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /* Konstruktor */

    public Game() {

        // obrazki, sprites

        //player

        ukaszLeftImage = new Image(getClass().getResourceAsStream(ImagesPath.ukaszLeftPath));
        ukaszRightImage = new Image(getClass().getResourceAsStream(ImagesPath.ukaszRightPath));
        ukaszShieldImage = new Image(getClass().getResourceAsStream(ImagesPath.protectionOrbitPath));
        ukaszShotImage = new Image(getClass().getResourceAsStream(ImagesPath.ukaszBulletShotPath));
        ukaszBombShotImages = new Image[2];
        ukaszBombShotImages[0] = new Image(getClass().getResourceAsStream(ImagesPath.ukaszBombShotPath1));
        ukaszBombShotImages[1] = new Image(getClass().getResourceAsStream(ImagesPath.ukaszBombShotPath2));

        //inne

        moneyBagImage1 = new Image(getClass().getResourceAsStream(ImagesPath.moneyBagPath1));
        moneyBagImage2 = new Image(getClass().getResourceAsStream(ImagesPath.moneyBagPath2));
        heartFullImage = new Image(getClass().getResourceAsStream(ImagesPath.heartFullPath));
        heartHalfImage = new Image(getClass().getResourceAsStream(ImagesPath.heartHalfPath));
        heartEmptyImage = new Image(getClass().getResourceAsStream(ImagesPath.heartEmptyPath));
        batteryImages = new Image[5];

        for (int i = 0; i < batteryImages.length; i++) {
            batteryImages[i] = new Image(getClass().getResourceAsStream(ImagesPath.batteryPrefix + (i + 1) + ".png"));
        }

        fishMinibossShieldImage = new Image(getClass().getResourceAsStream(ImagesPath.fishMinibossShield));

        //grafiki koncowe/startowe/komunikaty

        startImage = new Image(getClass().getResourceAsStream(ImagesPath.startImagePath));
        backgroundImage = new Image(getClass().getResourceAsStream(ImagesPath.backgroudImagePath_l0));
        endImage = new Image(getClass().getResourceAsStream(ImagesPath.endImagePath));
        kasiaImage = new Image(getClass().getResourceAsStream(ImagesPath.kasiaImagePath));
        heartFlareImage = new Image(getClass().getResourceAsStream(ImagesPath.heartFlarePath));
        kasiaWingsImage = new Image(getClass().getResourceAsStream(ImagesPath.wingsPath));


        //potwory

        littleMonsterImage = new Image(getClass().getResourceAsStream(ImagesPath.littleMonster_1Path));

        fishMonsterRightImage = new Image(getClass().getResourceAsStream(ImagesPath.fishMonsterRightPath));
        fishMonsterLeftImage = new Image(getClass().getResourceAsStream(ImagesPath.fishMonsterLeftPath));
        fishMonsterTopImage = new Image(getClass().getResourceAsStream(ImagesPath.fishMonsterTopPath));
        fishMonsterBottomImage = new Image(getClass().getResourceAsStream(ImagesPath.fishMonsterBottomPath));

        fishMonsterMinibossRightImage = new Image(getClass().getResourceAsStream(ImagesPath.fishMonsterMinibossRightPath));
        fishMonsterMinibossLeftImage = new Image(getClass().getResourceAsStream(ImagesPath.fishMonsterMinibossLeftPath));
        fishMonsterMinibossTopImage = new Image(getClass().getResourceAsStream(ImagesPath.fishMonsterMinibossTopPath));
        fishMonsterMinibossBottomImage = new Image(getClass().getResourceAsStream(ImagesPath.fishMonsterMinibossBottomPath));


        //dzwiek

        ukaszWingsSound = new Sounds(SoundsPath.ukaszWingsSoundPath);
        backgroundSound = new Sounds(SoundsPath.backgroundSoundPath1);
        backgroundStartSound = new Sounds(SoundsPath.startSoundPath);
        collectMoneySound = new Sounds(SoundsPath.collectMoneySoundPath);
        backgroundEndSound = new Sounds(SoundsPath.endSoundPath);
        bulletShotSound = new Sounds(SoundsPath.bulletShotSoundPath);
        bombShotSound = new Sounds(SoundsPath.bombShotSoundPath);

        //komponenty gry

        input = new ArrayList<String>();
        lastNanoTime = new LongValue(System.nanoTime());

        ukasz = new Ukasz(ukaszRightImage, ukaszShieldImage);
        ukaszShots = new ArrayList<ShootSprite>();
        antiCollisionTimer = new IntValue(0);

        moneybagList = new ArrayList<MoneyBag>();
        collectedMoneybags = new IntValue(0);

        monsters = new ArrayList<Monster>();
        killedMonstersOnLevel = new IntValue(0);

        levelNumber = 0;
        bulletOverheating = 0;
        bombOverheating = 0;
        playerFightWithBoss = false;
        testMode = false;
        showTutorialPage = false;
        playerReadyToGame = false;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /* Metody */

    //Glowna metoda gry
    public void start(Stage theStage) {

        // Utworzenie okna, komponentow

        theStage.setTitle("Valentines_Game");
        theStage.setWidth(1600);
        theStage.setHeight(900);
        theStage.setFullScreen(true);

        root = new Group();
        theScene = new Scene(root);
        theStage.setScene(theScene);

        canvas = new Canvas(theStage.getWidth(), theStage.getHeight());
        root.getChildren().add(canvas);

        //keyboard listener
        readKeyboardAction();

        gc = canvas.getGraphicsContext2D();

        setFont("Helvetica", FontWeight.BOLD, 34, Color.TAN);

        // Przygotowanie ekranu startowego
        backgroundStartSound.playSound(0.6, true);

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        class AnimationTimer2 extends AnimationTimer {

            public void handle(long currentNanoTime) {

                switch (levelNumber) {

                    //EKRAN STARTOWY
                    case 0: {

                        //TODO: Przetestowac

                        if (playerReadyToGame == false) {

                            gc.drawImage(startImage, 0, 0);

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

                            score = new IntValue(0);
                            double centerPositionX = Boundary.getAtRightBorder(canvas) / 2 - ukasz.getWidth();
                            double centerPositionY = Boundary.getAtBottomBorder(canvas) / 2 - ukasz.getHeight();

                            ukasz.setPosition(centerPositionX, centerPositionY);

                            level1 = new Level_1(moneyBagImage1, moneyBagImage2, littleMonsterImage, canvas);
                            level1.makeLevel(moneybagList, monsters);

                            collectedMoneybags.setValue(0);
                            ukaszShots.clear();

                            levelNumber++;

                            backgroundStartSound.stopSound();
                            ukaszWingsSound.playSound(1, true);
                            backgroundSound.playSound(0.1, true);
                        }

                    }
                    break;

                    //POZIOM 1
                    case 1: {

                        if ((!levelIsEnd(level1)) && (testMode == false))
                            play(theStage, currentNanoTime, level1);

                        else {

                            //Przygotowanie nastepnego poziomu

                            endLevel();
                            backgroundImage = new Image(getClass().getResourceAsStream(ImagesPath.backgroudImagePath_l1));

                            level2 = new Level_2(moneyBagImage1, moneyBagImage2, canvas);
                            level2.setLittleMonsterImages(fishMonsterLeftImage, fishMonsterRightImage, fishMonsterBottomImage, fishMonsterTopImage);
                            level2.setMinibossMonsterImages(fishMonsterMinibossLeftImage, fishMonsterMinibossRightImage, fishMonsterMinibossBottomImage, fishMonsterMinibossTopImage, fishMinibossShieldImage);
                            level2.makeLevel(moneybagList, monsters);
                        }


                    }
                    break;

                    //POZIOM2
                    case 2: {

                        if ((!levelIsEnd(level2)) && (testMode == false)) {

                            if (playerFightWithBoss == false) {

                                play(theStage, currentNanoTime, level2);

                                if (needToSpawnBoss(level2))
                                    level2.spawnMiniboss(monsters);
                            }

                            //gracz walczy z bossem
                            else {

                                play(theStage, currentNanoTime, level2);

                                //jesli boss zyje
                                if (monsters.isEmpty() == false) {

                                    ((FishMonsterMiniboss) monsters.get(0)).getShield().updateAndDrawShield(gc);
                                }
                            }


                        } else {

                            //Przygotowanie do uruchomienia ekranu koncowego

                            endLevel();

                            ukaszWingsSound.stopSound();
                            backgroundSound.stopSound();

                            backgroundEndSound.playSound(0.3, false);
                        }

                    }
                    break;

                    //EKRAN KONCOWY
                    default: {

                        endGame();
                    }
                } //zamyka switcha

            } //zamyka handle

        } //zamyka wewnetrzna klase

        new AnimationTimer2().start();
        theStage.show();

    }

    //ustawia odpowiednio zmienne przed rozpoczeciem nowego levelu
    public void endLevel() {

        levelNumber++;
        ukasz.addTotalScore(score.getValue());
        score.setValue(0);
        collectedMoneybags.setValue(0);
        moneybagList.clear();
        monsters.clear();
        ukaszShots.clear();
        killedMonstersOnLevel.setValue(0);
        playerFightWithBoss = false;
        testMode = false;
    }

    //sprawdza klikniety klawisz i wykonuje akcje dla niego
    public void checkPlayerMove(int velocity) {

        ukasz.setVelocity(0, 0);
        boolean playerCantDoAnyMove = Collision.collisionFromAllDirection(monsters, ukasz, canvas);

        if (playerCantDoAnyMove)
            antiCollisionTimer.setValue(Collision.getDefaultAntiCollisionsTimer());

        if (input.contains("A")) {

            //kolizja z ramka
            if (Boundary.boundaryCollisionFromLeft(canvas, ukasz) == false) {

                ukasz.setImage(ukaszLeftImage);
                ukasz.setLastDirectionX("A");

                if ((Collision.collisionWithMonstersFromRight(monsters, ukasz) == false) || (antiCollisionTimer.getValue() > 0))
                    ukasz.addVelocity(-velocity, 0);

            }
        }

        if (input.contains("D")) {

            //kolizja z ramka
            if (Boundary.boundaryCollisionFromRight(canvas, ukasz) == false) {

                ukasz.setImage(ukaszRightImage);
                ukasz.setLastDirectionX("D");

                if ((Collision.collisionWithMonstersFromLeft(monsters, ukasz) == false) || (antiCollisionTimer.getValue() > 0))
                    ukasz.addVelocity(velocity, 0);

            }
        }

        if (input.contains("W")) {

            //kolizja z ramka
            if (Boundary.boundaryCollisionFromTop(canvas, ukasz) == false) {

                ukasz.setLastDirectionY("W");

                if ((Collision.collisionWithMonstersFromBottom(monsters, ukasz) == false) || (antiCollisionTimer.getValue() > 0))
                    ukasz.addVelocity(0, -velocity);
            }
        }

        if (input.contains("S")) {

            //kolizja z ramka
            if (Boundary.boundaryCollisionFromBottom(canvas, ukasz) == false) {

                ukasz.setLastDirectionY("W");

                if ((Collision.collisionWithMonstersFromTop(monsters, ukasz) == false) || (antiCollisionTimer.getValue() > 0))
                    ukasz.addVelocity(0, velocity);
            }
        }

        //dla dzialka
        if (input.contains("SPACE")) {

            if (bulletOverheating <= 0) {

                ukaszShotSprite = new BulletSprite(ukaszShotImage, ukasz.getLastDirectionX(), BulletSprite.getDefaultShotVelocity());
                ((BulletSprite) ukaszShotSprite).setPosition(ukasz);
                ukaszShotSprite.setVelocity(ukaszShotSprite.getShotVelocity(), 0);

                ukaszShots.add(ukaszShotSprite);

                bulletShotSound.playSound(0.2, false);

                bulletOverheating = ((BulletSprite) ukaszShotSprite).getMaxOverheating();
            }
        }

        //Dla bomby
        if (input.contains("P")) {

            if (bombOverheating <= 0) {

                ukaszShotSprite = new BombSprite(ukaszBombShotImages[(int) (Math.random() * 2)], BombSprite.getDefaultShotVelocity());
                ((BombSprite) ukaszShotSprite).setPosition(ukasz);
                ukaszShotSprite.setVelocity(0, ukaszShotSprite.getShotVelocity());

                ukaszShots.add(ukaszShotSprite);

                bombShotSound.playSound(0.5, false);

                bombOverheating = ((BombSprite) ukaszShotSprite).getMaxOverheating();
            }
        }

        //tryb testowy
        if (input.contains("T")) {
            testMode = true;
        }
    }

    public boolean playerReadyToGame() {

        if (input.contains("ENTER")) return true;
        else return false;
    }

    //keyboard listner
    public void readKeyboardAction() {

        //wcisniety klawisz
        theScene.setOnKeyPressed(
                new EventHandler<KeyEvent>() {
                    public void handle(KeyEvent e) {
                        String code = e.getCode().toString();

                        // only add once... prevent duplicates
                        if (!input.contains(code))
                            input.add(code);
                    }
                });

        //zwolniony klawisz
        theScene.setOnKeyReleased(
                new EventHandler<KeyEvent>() {
                    public void handle(KeyEvent e) {
                        String code = e.getCode().toString();
                        input.remove(code);
                    }
                });

    }

    //ustawia wszystko co trzeba by zakonczyc gre i wyswietla koncowe obrazki/animacje
    public void endGame() {

        gc.drawImage(endImage, 0, 0);
        gc.drawImage(heartFlareImage, 10, 0);
        gc.drawImage(kasiaWingsImage, 440, 500);
        gc.drawImage(kasiaImage, 600, 230);
    }

    //glowna metoda dla poszczegolnych poziomow
    public void play(Stage theStage, long currentNanoTime, AllLevel level) {

        elapsedTime = (currentNanoTime - lastNanoTime.getValue()) / 1000000000.0;
        lastNanoTime.setValue(currentNanoTime);

        checkPlayerMove(Ukasz.getDefaultVelocity());
        ukasz.update(elapsedTime);

        //detekcja kolizji z pieniedzmi
        Collision.checkMoneybagsCollisions(moneybagList, ukasz, score, collectedMoneybags, collectMoneySound);

        //render tla
        gc.drawImage(backgroundImage, 0, 0);

        //render napisu
        String pointsText = "Kasa na walentynki: $" + (ukasz.getTotalScore() + (score.getValue()));
        gc.setFill(Color.TAN);
        gc.fillText(pointsText, theStage.getWidth() - 450, 70);

        ukasz.getShield().updateAndDrawShield(gc);

        //Dla strzalu
        if (bulletOverheating > 0)
            bulletOverheating -= 50;

        //dla bomby
        if (bombOverheating > 0)
            bombOverheating -= 50;

        //render serc
        AllLevel.drawHearts(gc, canvas, ukasz, heartFullImage, heartHalfImage, heartEmptyImage);

        //render baterii
        AllLevel.drawBattery(gc, canvas, bombOverheating, BombSprite.getMaxOverheating(), batteryImages);

        //render pieniedzy
        for (Sprite moneyBag : moneybagList)
            moneyBag.render(gc);

        //sprawdzenie czy pociski wylatuja poza mape, update i render
        Boundary.updateAndBoundaryActionForShots(gc, ukaszShots, elapsedTime, canvas);

        //render gracza
        ukasz.render(gc);

        if (antiCollisionTimer.getValue() > 0)
            antiCollisionTimer.removeValue(100);

        //Potwory

        //render
        level.renderMonsters(monsters, gc);

        //kolizja z graczem
        Collision.playerCollisionWithMonster(monsters, ukasz, canvas);

        //kolizja z pociskami
        Collision.playerShotCollision(monsters, ukaszShots, killedMonstersOnLevel);

        level.updateMonsters(ukasz, monsters);

    }

    public void setFont(String family, FontWeight weight, int size, Color color) {

        Font font = Font.font(family, weight, size);

        gc.setFont(font);
        gc.setFill(color);
    }

    public boolean levelIsEnd(AllLevel level) {

        if ((collectedMoneybags.getValue() < level.getHowManyMoneybags()) || (killedMonstersOnLevel.getValue() < level.getHowManyAllMonsters())) {
            return false;
        } else return true;
    }

    //jesli zebrano cala kase z poziomu i zabito wszystkie potowry
    public boolean needToSpawnBoss(AllLevel level) {

        if ((collectedMoneybags.getValue() >= level.getHowManyMoneybags()) && (monsters.isEmpty())) {

            playerFightWithBoss = true;
            return true;
        } else return false;
    }

    //wyswietla strony z tutorialem dla gracza
    public void showTutorialPage(Image[] tutorialPages, int howManyPages) {

        int i = 0;

        while (i < howManyPages) {

            gc.drawImage(tutorialPages[i], 0, 0);

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
