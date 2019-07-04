package com.Ukasz09.ValentineGame.sprites.weapons;
import com.Ukasz09.ValentineGame.sounds.SoundsPath;
import com.Ukasz09.ValentineGame.sounds.Sounds;

import com.Ukasz09.ValentineGame.sprites.creatures.Sprite;
import com.Ukasz09.ValentineGame.sprites.creatures.Ukasz;
import javafx.scene.image.Image;

public class BombSprite extends ShootSprite {

    private final double howManyLiveTakes=2;
    private static final double maxOverheating=10000;
    private static final double defaultShotVelocity=300;

    private final String bombBoomSoundPath1=SoundsPath.bombBoomSoundPath1;
    private final String bombBoomSoundPath2=SoundsPath.bombBoomSoundPath2;
    private final String bombBoomSoundPath3=SoundsPath.bombBoomSoundPath3;
    private final String bombBoomSoundPath4=SoundsPath.bombBoomSoundPath4;

    private Sounds[] bombBoomSound;

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /* Konstruktor */

    public BombSprite(Image image, double shotVelocity){

        super(image,shotVelocity);
        setHowManyLivesTake(howManyLiveTakes);

        bombBoomSound=new Sounds[4];
        bombBoomSound[0]=new Sounds(bombBoomSoundPath1);
        bombBoomSound[1]=new Sounds(bombBoomSoundPath2);
        bombBoomSound[2]=new Sounds(bombBoomSoundPath3);
        bombBoomSound[3]=new Sounds(bombBoomSoundPath4);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /* Gettery */

    public Sounds[] getBombBoomSound() {
        return bombBoomSound;
    }

    public Sounds getRandomBoomSound(){

        int random=(int)(Math.random()*bombBoomSound.length);

        return bombBoomSound[random];

    }

    public static double getMaxOverheating() {
        return maxOverheating;
    }

    public static double getDefaultShotVelocity() {
        return defaultShotVelocity;
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

        if(((Ukasz)ukasz).getLastDirectionX().equals("D"))
            setPosition(centerPositionRightX,ukasz.getBoundary().getMaxY()-50);
        else  setPosition(centerPositionLeftX,ukasz.getBoundary().getMaxY()-50);
    }

}
