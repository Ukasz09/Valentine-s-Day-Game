package com.Ukasz09.ValentineGame.gameModules.sprites.creatures;

import javafx.scene.image.Image;

import java.util.ArrayList;

public abstract class Monster extends Sprite {

    private double howManyLivesTake;
    private double howBigKickSize;

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /* Konstruktor */
    public Monster(Image image){

        super(image);
        howManyLivesTake=0;
        howBigKickSize=0;
        setVelocity(0,0);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /* Settery */

    public void setHowManyLivesTake(double howManyLivesTake) {
        this.howManyLivesTake = howManyLivesTake;
    }

    public void setHowBigKickSize(double howBigKickSize) {
        this.howBigKickSize = howBigKickSize;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /* Gettery */

    public double getHowBigKickSize() {
        return howBigKickSize;
    }

    public double getHowManyLivesTake() {
        return howManyLivesTake;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /* Metody */

    //podazanie za celem z ustalona predkoscia
    public void update(Sprite target, ArrayList<Monster> monsters){

        //jesli nie dopadly juz gracza/celu
        if(this.intersectsWithMonster(target)==false){

            boolean doFixPosition;

            //ustawienie kierunku renderu z uwzglednieniem predkosci
            double dx= this.getPositionX();
            double dy= this.getPositionY();

            double diffX = target.getPositionX() - dx;
            double diffY = target.getPositionY() - dy;

            float angle = (float)Math.atan2(diffY, diffX);

            dx += this.getVelocityX() * Math.cos(angle);
            dy += this.getVelocityY() * Math.sin(angle);

            this.setPosition(dx,dy);

            //kolizje z graczem i innymi potworami
            for(Sprite m: monsters){

                doFixPosition=false;

                if(m!=this){

                //zapobiega nakladaniu sie potworow

                    //kolizja z lewym brzegiem
                    if((m.getBoundary().getMinX()<this.getBoundary().getMaxX())&&(m.getBoundary().getMaxX()>this.getBoundary().getMaxX())&&(m.intersects(this))&&(doFixPosition==false)){

                        //jesli potworek nad drugim potworekiem to daj tylko do gory
                        if(m.getBoundary().getMinY()<this.getBoundary().getMinY()){

                            //kolizja z dolem
                            if ((m.getBoundary().getMaxY() > this.getBoundary().getMinY()) && (m.getBoundary().getMinY() < this.getBoundary().getMinY()) && (m.intersects(this))&&(doFixPosition==false)) {

                                dy=m.getBoundary().getMinY()+m.getHeight();
                                doFixPosition=true;
                            }

                        }

                        //jesli potworek pod drugim
                        else if(m.getBoundary().getMaxY()>this.getBoundary().getMaxY()){

                            //kolizja z gora
                            if ((m.getBoundary().getMinY()<this.getBoundary().getMaxY())&&(m.getBoundary().getMinY()>this.getBoundary().getMinY())&&(m.intersects(this))&&(doFixPosition==false)){

                                dy=m.getBoundary().getMinY()-m.getHeight();
                                doFixPosition=true;
                            }
                        }

                        //zwykle dzialanie dla lewego brzegu
                        else {
                            dx=m.getBoundary().getMinX()-m.getWidth();
                            doFixPosition=true;
                        }
                    }

                    //kolizja z prawym brzegiem
                    if ((m.getBoundary().getMaxX()>this.getBoundary().getMinX())&&(m.getBoundary().getMinX()<this.getBoundary().getMinX())&&(m.intersects(this))&&(doFixPosition==false)){

                        //jesli potworek nad drugim potworekiem to daj tylko do gory
                        if(m.getBoundary().getMinY()<this.getBoundary().getMinY()){

                            //kolizja z dolem
                            if ((m.getBoundary().getMaxY() > this.getBoundary().getMinY()) && (m.getBoundary().getMinY() < this.getBoundary().getMinY()) && (m.intersects(this))&&(doFixPosition==false)) {

                                dy=m.getBoundary().getMinY()+m.getHeight();
                                doFixPosition=true;
                            }

                            //kolizja z gora
                            if ((m.getBoundary().getMinY()<this.getBoundary().getMaxY())&&(m.getBoundary().getMinY()>this.getBoundary().getMinY())&&(m.intersects(this))&&(doFixPosition==false)){

                                dy=m.getBoundary().getMinY()-m.getHeight();
                                doFixPosition=true;
                            }
                        }

                        //jesli potworek pod drugim
                        else if(m.getBoundary().getMaxY()>this.getBoundary().getMaxY()){

                            //kolizja z gora
                            if ((m.getBoundary().getMinY()<this.getBoundary().getMaxY())&&(m.getBoundary().getMinY()>this.getBoundary().getMinY())&&(m.intersects(this))&&(doFixPosition==false)){

                                dy=m.getBoundary().getMinY()-m.getHeight();
                                doFixPosition=true;
                            }

                            //kolizja z dolem
                            if ((m.getBoundary().getMaxY() > this.getBoundary().getMinY()) && (m.getBoundary().getMinY() < this.getBoundary().getMinY()) && (m.intersects(this))&&(doFixPosition==false)) {

                                dy=m.getBoundary().getMinY()+m.getHeight();
                                doFixPosition=true;
                            }
                        }

                        else{

                            dx=m.getBoundary().getMinX()+m.getWidth();
                            doFixPosition=true;
                        }

                    }

                    //kolizja z gora
                    if ((m.getBoundary().getMinY()<this.getBoundary().getMaxY())&&(m.getBoundary().getMinY()>this.getBoundary().getMinY())&&(m.intersects(this))&&(doFixPosition==false)){

                        dy=m.getBoundary().getMinY()-m.getHeight();
                        doFixPosition=true;
                    }

                    //kolizja z dolem
                    if ((m.getBoundary().getMaxY() > this.getBoundary().getMinY()) && (m.getBoundary().getMinY() < this.getBoundary().getMinY()) && (m.intersects(this))&&(doFixPosition==false)) {

                        dy=m.getBoundary().getMinY()+m.getHeight();
                        doFixPosition=true;
                    }

                    this.setPosition(dx,dy);
                }

            } //zamyka for'a

            //ustaw na nowo polozenie
            this.setPosition(dx,dy);
        }
    }

    public void setImageByPosition(Image left, Image right, Image bottom, Image top, Sprite ukasz){

        double monsterMinX=this.getBoundary().getMinX();
        double monsterMaxX=this.getBoundary().getMaxX();
        double monsterMaxY=this.getBoundary().getMaxY();

        if(monsterMinX+0.1*monsterMinX>ukasz.getBoundary().getMaxX())
            this.setImage(left);
        else this.setImage(right);

        //jesli potwor doklanie nad/pod graczem
        if((monsterMinX>ukasz.getBoundary().getMinX())&&(monsterMaxX<ukasz.getBoundary().getMaxX())){

            //ustawiona w dol
            if(monsterMaxY-0.15*monsterMaxY<ukasz.getBoundary().getMinY())
                this.setImage(bottom);
            else this.setImage(top);
        }
    }

}
