package com.Ukasz09.ValentineGame.gameModules.sprites.weapons;
import com.Ukasz09.ValentineGame.soundsModule.Sounds;
import com.Ukasz09.ValentineGame.soundsModule.SoundsPath;
import com.Ukasz09.ValentineGame.soundsModule.SoundsPlayer;

import com.Ukasz09.ValentineGame.gameModules.sprites.creatures.Sprite;
import com.Ukasz09.ValentineGame.gameModules.sprites.creatures.Player;
import javafx.scene.image.Image;

public class BombSprite extends ShootSprite {

    private final double howManyLiveTakes=2;
    private static final double maxOverheating=10000;
    public static final double DEFAULT_SHOT_VELOCITY =300;

    private static final SoundsPlayer SHOT_SOUND= Sounds.bombShotSound;
    private static final double SOUND_VOLUME= 0.5;

    private final String bombBoomSoundPath1=SoundsPath.bombBoomSoundPath1;
    private final String bombBoomSoundPath2=SoundsPath.bombBoomSoundPath2;
    private final String bombBoomSoundPath3=SoundsPath.bombBoomSoundPath3;
    private final String bombBoomSoundPath4=SoundsPath.bombBoomSoundPath4;

    private SoundsPlayer[] bombBoomSound;

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public BombSprite(Image image){
        this(image,DEFAULT_SHOT_VELOCITY);
    }


    public BombSprite(Image image, double shotVelocity){

        super(image,shotVelocity);
        setHowManyLivesTake(howManyLiveTakes);

        bombBoomSound=new SoundsPlayer[4];
        bombBoomSound[0]=new SoundsPlayer(bombBoomSoundPath1);
        bombBoomSound[1]=new SoundsPlayer(bombBoomSoundPath2);
        bombBoomSound[2]=new SoundsPlayer(bombBoomSoundPath3);
        bombBoomSound[3]=new SoundsPlayer(bombBoomSoundPath4);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /* Gettery */

    public SoundsPlayer[] getBombBoomSound() {
        return bombBoomSound;
    }

    public SoundsPlayer getRandomBoomSound(){

        int random=(int)(Math.random()*bombBoomSound.length);

        return bombBoomSound[random];

    }

    public static double getMaxOverheating() {
        return maxOverheating;
    }

    public static double getDefaultShotVelocity() {
        return DEFAULT_SHOT_VELOCITY;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /* Metody */

    @Override
    public void update(double time){

        addPositionY(getVelocityY()*time);
    }

    public void setPosition(Sprite ukasz){

        double centerPositionRightX=ukasz.getBoundary().getMaxX()-ukasz.getWidth()/2;
        double centerPositionLeftX=ukasz.getBoundary().getMaxX()-ukasz.getWidth()/1.5;

        if(((Player)ukasz).getLastDirectionX().equals("D"))
            setPosition(centerPositionRightX,ukasz.getBoundary().getMaxY()-50);
        else  setPosition(centerPositionLeftX,ukasz.getBoundary().getMaxY()-50);
    }

    @Override
    public void playSound() {
        playSound(SHOT_SOUND,SOUND_VOLUME);
    }
}
