package com.Ukasz09.ValentineGame.gameModules.sprites.creatures;

import com.Ukasz09.ValentineGame.gameModules.Boundary;
import com.Ukasz09.ValentineGame.soundsModule.SoundsPath;
import com.Ukasz09.ValentineGame.soundsModule.Sounds;

import com.Ukasz09.ValentineGame.gameModules.sprites.others.Shield;
import com.Ukasz09.ValentineGame.gameModules.sprites.others.UkaszShield;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;

public class Ukasz extends Sprite {

    private static final int defaultVelocity=700;
    private static final int defaultLives=5;
    private final int defaultShieldDuration=7500;

    private Shield shield;

    private final String hitSoundPath1=SoundsPath.ukaszHitSoundPath1;
    private final String hitSoundPath2=SoundsPath.ukaszHitSoundPath2;
    private final String hitSoundPath3=SoundsPath.ukaszHitSoundPath3;

    private Sounds[] ukaszHitSounds;

    private String lastDirectionX;
    private String lastDirectionY;
    private int totalScore;

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /* Konstruktor */
    public Ukasz(Image image, Image shieldImage){

        super(image);

        shield=new UkaszShield(0,defaultShieldDuration,shieldImage,this);
        setLives(defaultLives);
        setMaxLives(defaultLives);

        ukaszHitSounds=new Sounds[3];
        ukaszHitSounds[0]=new Sounds(hitSoundPath1);
        ukaszHitSounds[1]=new Sounds(hitSoundPath2);
        ukaszHitSounds[2]=new Sounds(hitSoundPath3);

        lastDirectionX="D";
        lastDirectionY="W";
        totalScore=0;


    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /* Gettery */

    public Sounds[] getUkaszHitSounds() {
        return ukaszHitSounds;
    }

    public Sounds getUkaszRandomHitSound(){

        int random=(int)(Math.random()*3);
        System.out.println(random);
        return ukaszHitSounds[random];
    }

    public static int getDefaultVelocity() {
        return defaultVelocity;
    }

    public String getLastDirectionX() {
        return lastDirectionX;
    }

    public int getTotalScore() {
        return totalScore;
    }

    public Shield getShield() {
        return shield;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /* Settery */

    public void setLastDirectionX(String lastDirectionX) {
        this.lastDirectionX = lastDirectionX;
    }

    public void setLastDirectionY(String lastDirectionY) {
        this.lastDirectionY = lastDirectionY;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /* Metody */

    //odrzut gracza wedlug ostatnio kliknietego klawisza
    public void kickPlayer(double kickSize, Canvas canvas){

        //PRAWO/LEWO

            //w lewo
            if(lastDirectionX.equals("D")){

                setPosition(getPositionX()-kickSize, getPositionY());

                //dopoki kolizja z granica mapy
                while (Boundary.boundaryCollisionFromLeft(canvas,this))
                    setPosition(getPositionX()+kickSize/4, getPositionY());

            }

            //w prawo
            else  {

                setPosition(getPositionX()+kickSize, getPositionY());

                //dopoki kolizja z granica mapy
                while (Boundary.boundaryCollisionFromRight(canvas,this))
                    setPosition(getPositionX()-kickSize/4, getPositionY());

            }

        //GORA/DOL

            //w dol
            if(lastDirectionY.equals("W")){

                setPosition(getPositionX(),getPositionY()+kickSize);

                //dopoki kolizja z granica mapy
                while (Boundary.boundaryCollisionFromBottom(canvas,this))
                    setPosition(getPositionX(),getPositionY()-kickSize/4);
            }

            //w gore
            else {

                setPosition(getPositionX(),getPositionY()-kickSize);

                //dopoki kolizja z granica mapy
                while (Boundary.boundaryCollisionFromTop(canvas,this))
                    setPosition(getPositionX(),getPositionY()+kickSize/4);
            }
    }

    //odrzut gracza wedlug pozycji potwora
    public void kickPlayerByMonsterPostion(Monster m, Canvas canvas) {

        double monsterMinX=m.getBoundary().getMinX();
        double monsterMaxX=m.getBoundary().getMaxX();
        double monsterMaxY=m.getBoundary().getMaxY();

        String kickDirection;

        if(monsterMinX+0.1*monsterMinX>this.getBoundary().getMaxX())
            kickDirection="A";
        else kickDirection="D";

        //jesli potwor doklanie nad/pod graczem (korekta bo potwor wiekszy niz gracz)
        if((monsterMinX+0.25*monsterMinX>this.getBoundary().getMinX())&&(monsterMaxX-0.25*monsterMaxX<this.getBoundary().getMaxX())) {

            //ustawiona w dol
            if (monsterMaxY - 0.15 * monsterMaxY < this.getBoundary().getMinY())
                kickDirection = "S";
            else kickDirection = "W";
        }

        switch (kickDirection){

            case "A":{

                if(Boundary.boundaryCollisionFromLeft(canvas,this)==false)
                    setPosition(getPositionX()-m.getHowBigKickSize(), getPositionY());

            } break;

            case "D":{

                if(Boundary.boundaryCollisionFromRight(canvas,this)==false)
                    setPosition(getPositionX()+m.getHowBigKickSize(), getPositionY());

            } break;

            case "W":{
                if(Boundary.boundaryCollisionFromTop(canvas,this)==false)
                    setPosition(getPositionX(),getPositionY()-m.getHowBigKickSize());

            } break;

            case "S":{
                if(Boundary.boundaryCollisionFromBottom(canvas,this)==false)
                    setPosition(getPositionX(),getPositionY()+m.getHowBigKickSize());
            }
        } //zamyka switcha

    }

    public void addTotalScore(int score){
        totalScore+=score;
    }
}
