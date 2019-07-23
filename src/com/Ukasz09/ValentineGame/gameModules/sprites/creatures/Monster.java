package com.Ukasz09.ValentineGame.gameModules.sprites.creatures;

import com.Ukasz09.ValentineGame.gameModules.utilitis.ViewManager;
import com.Ukasz09.ValentineGame.gameModules.sprites.effects.kickEffect.KickPlayer;
import javafx.scene.image.Image;

import java.util.ArrayList;

public abstract class Monster extends Sprite {
    private double howManyLivesTake;
    private double howBigKickSize;
    private KickPlayer kickMethod;

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public Monster(Image image, KickPlayer kickMethod, ViewManager manager) {
        super(image, manager);
        howManyLivesTake = 0;
        howBigKickSize = 0;
        setVelocity(0, 0);
        this.kickMethod = kickMethod;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public abstract void isDeadAction();

    public abstract void isHitAction();

    public abstract void missHitAction();

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public boolean kickAfterHit() {
        if (howBigKickSize > 0) return true;

        return false;
    }

    //    //todo: refactor
    public void newupdate(Sprite target, ArrayList<Monster> monsters) {
        if (!this.intersects(target)) {
            boolean doFixPosition;

            double dx = this.getPositionX();
            double dy = this.getPositionY();
            double diffX = target.getPositionX() - dx;
            double diffY = target.getPositionY() - dy;
            float angle = (float) Math.atan2(diffY, diffX);
            dx += this.getVelocityX() * Math.cos(angle);
            dy += this.getVelocityY() * Math.sin(angle);
            this.setPosition(dx, dy);

            for (Sprite m : monsters) {

                doFixPosition = false;

                if (m != this) {

                    //zapobiega nakladaniu sie potworow

                    //kolizja z lewym brzegiem
                    if ((m.getBoundary().getMinX() < this.getBoundary().getMaxX()) && (m.getBoundary().getMaxX() > this.getBoundary().getMaxX()) && (m.intersects(this)) && (doFixPosition == false)) {

                        //jesli potworek nad drugim potworekiem to daj tylko do gory
                        if (m.getBoundary().getMinY() < this.getBoundary().getMinY()) {

                            //kolizja z dolem
                            if ((m.getBoundary().getMaxY() > this.getBoundary().getMinY()) && (m.getBoundary().getMinY() < this.getBoundary().getMinY()) && (m.intersects(this)) && (doFixPosition == false)) {
                                dy = m.getBoundary().getMinY() + m.getHeight();
                                doFixPosition = true;
                            }

                        }

                        //jesli potworek pod drugim
                        else if (m.getBoundary().getMaxY() > this.getBoundary().getMaxY()) {

                            //kolizja z gora
                            if ((m.getBoundary().getMinY() < this.getBoundary().getMaxY()) && (m.getBoundary().getMinY() > this.getBoundary().getMinY()) && (m.intersects(this)) && (doFixPosition == false)) {
                                dy = m.getBoundary().getMinY() - m.getHeight();
                                doFixPosition = true;
                            }
                        }

                        //zwykle dzialanie dla lewego brzegu
                        else {
                            dx = m.getBoundary().getMinX() - m.getWidth();
                            doFixPosition = true;
                        }
                    }

                    //kolizja z prawym brzegiem
                    if ((m.getBoundary().getMaxX() > this.getBoundary().getMinX()) && (m.getBoundary().getMinX() < this.getBoundary().getMinX()) && (m.intersects(this)) && (doFixPosition == false)) {

                        //jesli potworek nad drugim potworekiem to daj tylko do gory
                        if (m.getBoundary().getMinY() < this.getBoundary().getMinY()) {

                            //kolizja z dolem
                            if ((m.getBoundary().getMaxY() > this.getBoundary().getMinY()) && (m.getBoundary().getMinY() < this.getBoundary().getMinY()) && (m.intersects(this)) && (doFixPosition == false)) {
                                dy = m.getBoundary().getMinY() + m.getHeight();
                                doFixPosition = true;
                            }

                            //kolizja z gora
                            if ((m.getBoundary().getMinY() < this.getBoundary().getMaxY()) && (m.getBoundary().getMinY() > this.getBoundary().getMinY()) && (m.intersects(this)) && (doFixPosition == false)) {
                                dy = m.getBoundary().getMinY() - m.getHeight();
                                doFixPosition = true;
                            }
                        }

                        //jesli potworek pod drugim
                        else if (m.getBoundary().getMaxY() > this.getBoundary().getMaxY()) {

                            //kolizja z gora
                            if ((m.getBoundary().getMinY() < this.getBoundary().getMaxY()) && (m.getBoundary().getMinY() > this.getBoundary().getMinY()) && (m.intersects(this)) && (doFixPosition == false)) {
                                dy = m.getBoundary().getMinY() - m.getHeight();
                                doFixPosition = true;
                            }

                            //kolizja z dolem
                            if ((m.getBoundary().getMaxY() > this.getBoundary().getMinY()) && (m.getBoundary().getMinY() < this.getBoundary().getMinY()) && (m.intersects(this)) && (doFixPosition == false)) {
                                dy = m.getBoundary().getMinY() + m.getHeight();
                                doFixPosition = true;
                            }
                        } else {
                            dx = m.getBoundary().getMinX() + m.getWidth();
                            doFixPosition = true;
                        }

                    }

                    //kolizja z gora
                    if ((m.getBoundary().getMinY() < this.getBoundary().getMaxY()) && (m.getBoundary().getMinY() > this.getBoundary().getMinY()) && (m.intersects(this)) && (doFixPosition == false)) {

                        dy = m.getBoundary().getMinY() - m.getHeight();
                        doFixPosition = true;
                    }

                    //kolizja z dolem
                    if ((m.getBoundary().getMaxY() > this.getBoundary().getMinY()) && (m.getBoundary().getMinY() < this.getBoundary().getMinY()) && (m.intersects(this)) && (doFixPosition == false)) {

                        dy = m.getBoundary().getMinY() + m.getHeight();
                        doFixPosition = true;
                    }

                    this.setPosition(dx, dy);
                }

            } //zamyka for'a

            //ustaw na nowo polozenie
            this.setPosition(dx, dy);
        }
    }

    /////////////
    public void update(Sprite target, ArrayList<Monster> monsters) {
        if (!this.intersects(target)) {
            updateByVelocity(target);
            double dx = getPositionX();
            double dy = getPositionY();

            for (Monster m : monsters) {
                if (this != m) {
                    if (m.intersects(this)) {
                        //nad lub pod
                        if (this.underOrAboveTarget(m)) { //todo: zrobic poziomami wysokosc
                            if (this.exactlyAboveTarget(m)) { //todo: zrobic jesli nad i pod. jesli pod to dac w prawo lub lewo (pionami)
                                dy += this.getBoundary().getMinY() - this.getHeight();
                            } else dy += this.getBoundary().getMinY() + this.getHeight();
                        }
                        //po bokach
                        else {  //todo: zrobic jesli z boku to w gore albo w dol niech leci (poziomami)
                            //dy += this.getBoundary().getMinY() - this.getHeight();
//                        //na lewo
//                        if (this.leftSideToTarget(m) && m.intersects(this)) {
//
//                        }
//
//                        //na prawo
//                        else {
//
//                        }
                        }
                        this.setPosition(dx, dy);
                        return;
                    }

                }
            }
        }
    }

    private void updateByVelocity(Sprite target) {
        double dx = this.getPositionX();
        double dy = this.getPositionY();
        double diffX = target.getPositionX() - dx;
        double diffY = target.getPositionY() - dy;
        float angle = (float) Math.atan2(diffY, diffX);

        dx += this.getVelocityX() * Math.cos(angle);
        dy += this.getVelocityY() * Math.sin(angle);
        this.setPosition(dx, dy);
    }

    ////////
    public void setImageByPosition(Image left, Image right, Image bottom, Image top, Sprite target) {
        if (rightSideToTarget(target))
            this.setActualImage(left);
        else this.setActualImage(right);

        if (underOrAboveTarget(target)) {
            if (exactlyAboveTarget(target))
                this.setActualImage(bottom);
            else this.setActualImage(top);
        }
    }

    private boolean rightSideToTarget(Sprite target) {
        double monsterMinX = this.getBoundary().getMinX();
        if (monsterMinX + 0.1 * monsterMinX > target.getBoundary().getMaxX())
            return true;

        return false;
    }

    private boolean leftSideToTarget(Sprite target) {
        double monsterMaxX = this.getBoundary().getMaxX();
        if (monsterMaxX + 0.1 * monsterMaxX < target.getBoundary().getMinX())
            return true;

        return false;
    }

    private boolean underOrAboveTarget(Sprite target) {
        double monsterMinX = this.getBoundary().getMinX();
        double monsterMaxX = this.getBoundary().getMaxX();

        if ((monsterMinX > target.getBoundary().getMinX()) && (monsterMaxX < target.getBoundary().getMaxX()))
            return true;

        return false;
    }

    private boolean exactlyAboveTarget(Sprite target) {
        double monsterMinX = this.getBoundary().getMinX();
        double monsterMaxX = this.getBoundary().getMaxX();
        double monsterMaxY = this.getBoundary().getMaxY();

        if (underOrAboveTarget(target))
            if (monsterMaxY - 0.15 * monsterMaxY < target.getBoundary().getMinY())
                return true;

        return false;
    }

    public void kickPlayer(Player p) {
        kickMethod.kickPlayerByMonsterPostion(this, p, getManager());
    }

    protected void defaultIsDeadAction(double soundsVolume) {
        getDeathSound().playSound(soundsVolume, false);
    }

    protected void defaultIsHitAction(double soundsVolume) {
        getHitSound().playSound(soundsVolume, false);
    }

    protected void defaultMissHitAction(double soundsVolume) {
        getMissSound().playSound(soundsVolume, false);
    }

    public boolean isDead() {
        return (getLives() <= 0);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public void setHowManyLivesTake(double howManyLivesTake) {
        this.howManyLivesTake = howManyLivesTake;
    }

    public void setHowBigKickSize(double howBigKickSize) {
        this.howBigKickSize = howBigKickSize;
    }

    public double getHowBigKickSize() {
        return howBigKickSize;
    }

    public double getHowManyLivesTake() {
        return howManyLivesTake;
    }

}
