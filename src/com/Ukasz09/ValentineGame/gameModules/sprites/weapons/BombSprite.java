package com.Ukasz09.ValentineGame.gameModules.sprites.weapons;

import com.Ukasz09.ValentineGame.gameModules.sprites.creatures.Monster;
import com.Ukasz09.ValentineGame.gameModules.utilitis.ViewManager;
import com.Ukasz09.ValentineGame.graphicModule.texturesPath.SpritesImages;
import com.Ukasz09.ValentineGame.soundsModule.soundsPath.SoundsPath;
import com.Ukasz09.ValentineGame.soundsModule.soundsPath.SoundsPlayer;
import com.Ukasz09.ValentineGame.gameModules.sprites.creatures.Player;

import javafx.geometry.Point2D;
import javafx.scene.image.Image;

public class BombSprite extends ShotSprite {
    public static final double DEFAULT_LIVES_TAKES = 2;
    public static final double DEFAULT_MAX_OVERHEATING = 10000;
    public static final double DEFAULT_SHOT_VELOCITY = 300;

    private SoundsPlayer shotSound;
    private static final double DEFAULT_SHOT_VOLUME = 0.5;
    private static final String BOMB_BOOM_SOUND_PATH_1 = SoundsPath.BOMB_BOOM_SOUND_PATH_1;
    private static final String BOMB_BOOM_SOUND_PATH_2 = SoundsPath.BOMB_BOOM_SOUND_PATH_2;
    private static final String BOMB_BOOM_SOUND_PATH_3 = SoundsPath.BOMB_BOOM_SOUND_PATH_3;
    private static final String BOMB_BOOM_SOUND_PATH_4 = SoundsPath.BOMB_BOOM_SOUND_PATH_4;
    private static final Image[] DEFAULT_BOMB_IMAGES = SpritesImages.getUkaszBombShotImages();
    private static final String BOMB_SHOT_SOUND_PATH=SoundsPath.BOMB_SHOT_SOUND_PATH;

    private static double maxOverheating = DEFAULT_MAX_OVERHEATING;
    private SoundsPlayer[] bombBoomSound;

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public BombSprite(ViewManager manager) {
        this(DEFAULT_BOMB_IMAGES[(int) (Math.random() * 2)], manager);
    }

    public BombSprite(Image image, ViewManager manager) {
        this(image, DEFAULT_SHOT_VELOCITY, DEFAULT_LIVES_TAKES, DEFAULT_MAX_OVERHEATING, manager);
    }

    public BombSprite(Image image, double shotVelocity, double howManyLiveTakes, double maxOverheating, ViewManager manager) {
        super(image, shotVelocity, howManyLiveTakes, manager);
        this.maxOverheating = maxOverheating;

        shotSound=new SoundsPlayer(BOMB_SHOT_SOUND_PATH,DEFAULT_SHOT_VOLUME,false);
        bombBoomSound = getBombBoomSound();
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @Override
    public void update(double time) {
        addPositionY(getVelocityY() * time);
    }

    @Override
    public void playShotSound() {
        shotSound.playSound();
    }

    public void playBoomSound() {
        getRandomBoomSound().playSound();
    }

    @Override
    public boolean isOutOfBoundary() {
        if ((getBoundary().getMaxY() > getManager().getBottomBorder()))
            return true;

        return false;
    }

    @Override
    public void doOutOfBoundaryAction() {
        playBoomSound();
    }

    @Override
    public void hitMonster(Monster monster) {
        playBoomSound();
        monster.removeLives(getHowManyLivesTake());
    }

    @Override
    public void prepareToShot(Player player) {
        Point2D bombPosition=player.getBombPosition();
        setPosition(bombPosition.getX(),bombPosition.getY());
        setVelocity(0, getShotVelocity());
    }

    public SoundsPlayer[] getBombBoomSound(){
        SoundsPlayer[] boomSound = new SoundsPlayer[4];
        boomSound[0] = new SoundsPlayer(BOMB_BOOM_SOUND_PATH_1,DEFAULT_SHOT_VOLUME, false);
        boomSound[1] = new SoundsPlayer(BOMB_BOOM_SOUND_PATH_2, DEFAULT_SHOT_VOLUME, false);
        boomSound[2] = new SoundsPlayer(BOMB_BOOM_SOUND_PATH_3, DEFAULT_SHOT_VOLUME, false);
        boomSound[3] = new SoundsPlayer(BOMB_BOOM_SOUND_PATH_4, DEFAULT_SHOT_VOLUME, false);
        return bombBoomSound;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public SoundsPlayer getRandomBoomSound() {
        int random = (int) (Math.random() * bombBoomSound.length);
        return bombBoomSound[random];

    }

    public static double getMaxOverheating() {
        return maxOverheating;
    }
}
