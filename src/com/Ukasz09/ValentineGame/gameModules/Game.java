/*
    todo:
        - responsywnosc na wszystkie rozdzielczosci
        - nowe levele
        - nowi przeciwnicy
        - ekrany tutorialowe (opis potworow, sterowanie, bonusy, tipy)
*/

package com.Ukasz09.ValentineGame.gameModules;

import com.Ukasz09.ValentineGame.gameModules.levels.AllLevel;
import com.Ukasz09.ValentineGame.gameModules.levels.Level_1;
import com.Ukasz09.ValentineGame.gameModules.levels.Level_2;
import com.Ukasz09.ValentineGame.gameModules.sprites.creatures.FishMonsterMiniboss;
import com.Ukasz09.ValentineGame.gameModules.sprites.creatures.Monster;
import com.Ukasz09.ValentineGame.gameModules.sprites.creatures.Sprite;
import com.Ukasz09.ValentineGame.gameModules.sprites.creatures.Player;
import com.Ukasz09.ValentineGame.gameModules.sprites.others.MoneyBag;
import com.Ukasz09.ValentineGame.gameModules.sprites.weapons.BombSprite;
import com.Ukasz09.ValentineGame.gameModules.sprites.weapons.BulletSprite;
import com.Ukasz09.ValentineGame.gameModules.sprites.weapons.ShootSprite;
import com.Ukasz09.ValentineGame.gameModules.wrappers.IntValue;
import com.Ukasz09.ValentineGame.gameModules.wrappers.LongValue;
import com.Ukasz09.ValentineGame.graphicModule.texturesPath.BackgroundImages;
import com.Ukasz09.ValentineGame.graphicModule.texturesPath.BackgroundPath;
import com.Ukasz09.ValentineGame.graphicModule.texturesPath.SpritesImages;
import com.Ukasz09.ValentineGame.soundsModule.Sounds;
import com.Ukasz09.ValentineGame.soundsModule.SoundsPlayer;

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

import java.util.ArrayList;

public class Game extends Application {

    //Sprites
//    private Image backgroundImage;
//    private Image moneyBagImage1;
//    private Image moneyBagImage2;
//    private Image endImage;
//    private Image kasiaImage;
//    private Image startImage;
//    private Image heartFlareImage;
//    private Image kasiaWingsImage;
//    private Image ukaszShotImage;
//    private Image[] ukaszBombShotImages;
//    private Image littleMonsterImage;
//    private Image fishMonsterRightImage;
//    private Image fishMonsterLeftImage;
//    private Image fishMonsterBottomImage;
//    private Image fishMonsterTopImage;
//    private Image fishMonsterMinibossRightImage;
//    private Image fishMonsterMinibossLeftImage;
//    private Image fishMonsterMinibossBottomImage;
//    private Image fishMonsterMinibossTopImage;
//    //private Image ukaszShieldImage;
//    private Image heartFullImage;
//    private Image heartHalfImage;
//    private Image heartEmptyImage;
//    private Image[] batteryImages;
//    private Image fishMinibossShieldImage;

    //Sounds
    private SoundsPlayer playerWingsSound;
    private SoundsPlayer backgroundSound;
    private SoundsPlayer backgroundStartSound;
    private SoundsPlayer collectMoneySound;
    private SoundsPlayer backgroundEndSound;
    private SoundsPlayer bulletShotSound;
    private SoundsPlayer bombShotSound;

    Scene theScene;
    GraphicsContext gc;
    Canvas canvas;
    Group root;

    private ArrayList<MoneyBag> moneybagList;
    private ArrayList<Monster> monsters;
    private ArrayList<String> input;
    private ArrayList<ShootSprite> playerShots;

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

    private Player player;
    private ShootSprite ukaszShotSprite;

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public Game() {
        //Player
//        ukaszShotImage = SpritesImages.ukaszShotImage;
//        ukaszBombShotImages = SpritesImages.getUkaszBombShotImages();
//        heartFullImage = SpritesImages.heartFullImage;
//        heartHalfImage = SpritesImages.heartHalfImage;
//        heartEmptyImage = SpritesImages.heartEmptyImage;
//        batteryImages = SpritesImages.getBatteryImages();
//
//        //Other
//        moneyBagImage1 = SpritesImages.moneyBagImage1;
//        moneyBagImage2 = SpritesImages.moneyBagImage2;
//
//        kasiaImage = SpritesImages.kasiaImage;
//        heartFlareImage = SpritesImages.heartFlareImage;
//        kasiaWingsImage = SpritesImages.kasiaWingsImage;
//
//        //Background
//        startImage = BackgroundImages.startImage;
//        backgroundImage = BackgroundImages.backgroundImage1;
//        endImage = BackgroundImages.endImage;
//
//        //Enemy's
//        littleMonsterImage = SpritesImages.littleMonsterImage;
//
//        fishMonsterRightImage = SpritesImages.fishMonsterRightImage;
//        fishMonsterLeftImage = SpritesImages.fishMonsterLeftImage;
//        fishMonsterTopImage = SpritesImages.fishMonsterTopImage;
//        fishMonsterBottomImage = SpritesImages.fishMonsterBottomImage;
//
//        fishMonsterMinibossRightImage = SpritesImages.fishMonsterMinibossRightImage;
//        fishMonsterMinibossLeftImage = SpritesImages.fishMonsterMinibossLeftImage;
//        fishMonsterMinibossTopImage = SpritesImages.fishMonsterMinibossTopImage;
//        fishMonsterMinibossBottomImage = SpritesImages.fishMonsterMinibossBottomImage;
//        fishMinibossShieldImage = SpritesImages.fishMinibossShieldImage;

        //Sounds
        playerWingsSound = Sounds.ukaszWingsSound;
        backgroundSound = Sounds.backgroundSound;
        backgroundStartSound = Sounds.backgroundStartSound;
        collectMoneySound = Sounds.collectMoneySound;
        backgroundEndSound = Sounds.backgroundEndSound;
        bulletShotSound = Sounds.bulletShotSound;
        bombShotSound = Sounds.bombShotSound;

        //Game components
        input = new ArrayList<>();
        lastNanoTime = new LongValue(System.nanoTime());

        player = new Player(SpritesImages.playerRightImage, SpritesImages.playerShieldImage);
        playerShots = new ArrayList<>();
        antiCollisionTimer = new IntValue(0);

        moneybagList = new ArrayList<>();
        collectedMoneybags = new IntValue(0);

        monsters = new ArrayList<>();
        killedMonstersOnLevel = new IntValue(0);

        levelNumber = 0;
        bulletOverheating = 0;
        bombOverheating = 0;
        playerFightWithBoss = false;
        showTutorialPage = false;
        playerReadyToGame = false;
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    //TODO zrobic view manager, tu skonczylem
    public void start(Stage theStage) {
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
                            double centerPositionX = Boundary.getAtRightBorder(canvas) / 2 - player.getWidth();
                            double centerPositionY = Boundary.getAtBottomBorder(canvas) / 2 - player.getHeight();

                            player.setPosition(centerPositionX, centerPositionY);

                            level1 = new Level_1(canvas);
                            level1.makeLevel(moneybagList, monsters);

                            collectedMoneybags.setValue(0);
                            playerShots.clear();

                            levelNumber++;

                            backgroundStartSound.stopSound();
                            playerWingsSound.playSound(1, true);
                            backgroundSound.playSound(0.1, true);
                        }

                    }
                    break;

                    //POZIOM 1
                    case 1: {

                        if (!levelIsEnd(level1))
                            play(theStage, currentNanoTime, level1);

                        else {

                            //Przygotowanie nastepnego poziomu

                            endLevel();
                            backgroundImage = new Image(getClass().getResourceAsStream(BackgroundPath.backgroudImagePath_l1));

                            level2 = new Level_2(canvas);
                            level2.makeLevel(moneybagList, monsters);
                        }


                    }
                    break;

                    //POZIOM2
                    case 2: {

                        if (!levelIsEnd(level2)) {

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

                            playerWingsSound.stopSound();
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
        player.addTotalScore(score.getValue());
        score.setValue(0);
        collectedMoneybags.setValue(0);
        moneybagList.clear();
        monsters.clear();
        playerShots.clear();
        killedMonstersOnLevel.setValue(0);
        playerFightWithBoss = false;
    }

    //sprawdza klikniety klawisz i wykonuje akcje dla niego
    public void checkPlayerMove(int velocity) {

        player.setVelocity(0, 0);
        boolean playerCantDoAnyMove = Collision.collisionFromAllDirection(monsters, player, canvas);

        if (playerCantDoAnyMove)
            antiCollisionTimer.setValue(Collision.getDefaultAntiCollisionsTimer());

        if (input.contains("A")) {

            //kolizja z ramka
            if (Boundary.boundaryCollisionFromLeft(canvas, player) == false) {

                player.setImage(SpritesImages.playerLeftImage);
                player.setLastDirectionX("A");

                if ((Collision.collisionWithMonstersFromRight(monsters, player) == false) || (antiCollisionTimer.getValue() > 0))
                    player.addVelocity(-velocity, 0);

            }
        }

        if (input.contains("D")) {

            //kolizja z ramka
            if (Boundary.boundaryCollisionFromRight(canvas, player) == false) {

                player.setImage(SpritesImages.playerRightImage);
                player.setLastDirectionX("D");

                if ((Collision.collisionWithMonstersFromLeft(monsters, player) == false) || (antiCollisionTimer.getValue() > 0))
                    player.addVelocity(velocity, 0);

            }
        }

        if (input.contains("W")) {

            //kolizja z ramka
            if (Boundary.boundaryCollisionFromTop(canvas, player) == false) {

                player.setLastDirectionY("W");

                if ((Collision.collisionWithMonstersFromBottom(monsters, player) == false) || (antiCollisionTimer.getValue() > 0))
                    player.addVelocity(0, -velocity);
            }
        }

        if (input.contains("S")) {

            //kolizja z ramka
            if (Boundary.boundaryCollisionFromBottom(canvas, player) == false) {

                player.setLastDirectionY("W");

                if ((Collision.collisionWithMonstersFromTop(monsters, player) == false) || (antiCollisionTimer.getValue() > 0))
                    player.addVelocity(0, velocity);
            }
        }

        //dla dzialka
        if (input.contains("SPACE")) {

            if (bulletOverheating <= 0) {

                ukaszShotSprite = new BulletSprite(ukaszShotImage, player.getLastDirectionX(), BulletSprite.getDefaultShotVelocity());
                ((BulletSprite) ukaszShotSprite).setPosition(player);
                ukaszShotSprite.setVelocity(ukaszShotSprite.getShotVelocity(), 0);

                playerShots.add(ukaszShotSprite);

                bulletShotSound.playSound(0.2, false);

                bulletOverheating = ((BulletSprite) ukaszShotSprite).getMaxOverheating();
            }
        }

        //Dla bomby
        if (input.contains("P")) {

            if (bombOverheating <= 0) {

                ukaszShotSprite = new BombSprite(ukaszBombShotImages[(int) (Math.random() * 2)], BombSprite.getDefaultShotVelocity());
                ((BombSprite) ukaszShotSprite).setPosition(player);
                ukaszShotSprite.setVelocity(0, ukaszShotSprite.getShotVelocity());

                playerShots.add(ukaszShotSprite);

                bombShotSound.playSound(0.5, false);

                bombOverheating = ((BombSprite) ukaszShotSprite).getMaxOverheating();
            }
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

        checkPlayerMove(Player.getDefaultVelocity());
        player.update(elapsedTime);

        //detekcja kolizji z pieniedzmi
        Collision.checkMoneybagsCollisions(moneybagList, player, score, collectedMoneybags, collectMoneySound);

        //render tla
        gc.drawImage(backgroundImage, 0, 0);

        //render napisu
        String pointsText = "Kasa na walentynki: $" + (player.getTotalScore() + (score.getValue()));
        gc.setFill(Color.TAN);
        gc.fillText(pointsText, theStage.getWidth() - 450, 70);

        player.getShield().updateAndDrawShield(gc);

        //Dla strzalu
        if (bulletOverheating > 0)
            bulletOverheating -= 50;

        //dla bomby
        if (bombOverheating > 0)
            bombOverheating -= 50;

        //render serc
        AllLevel.drawHearts(gc, canvas, player, heartFullImage, heartHalfImage, heartEmptyImage);

        //render baterii
        AllLevel.drawBattery(gc, canvas, bombOverheating, BombSprite.getMaxOverheating(), batteryImages);

        //render pieniedzy
        for (Sprite moneyBag : moneybagList)
            moneyBag.render(gc);

        //sprawdzenie czy pociski wylatuja poza mape, update i render
        Boundary.updateAndBoundaryActionForShots(gc, playerShots, elapsedTime, canvas);

        //render gracza
        player.render(gc);

        if (antiCollisionTimer.getValue() > 0)
            antiCollisionTimer.decValue(100);

        //Potwory

        //render
        level.renderMonsters(monsters, gc);

        //kolizja z graczem
        Collision.playerCollisionWithMonster(monsters, player, canvas);

        //kolizja z pociskami
        Collision.playerShotCollision(monsters, playerShots, killedMonstersOnLevel);

        level.updateMonsters(player, monsters);

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
