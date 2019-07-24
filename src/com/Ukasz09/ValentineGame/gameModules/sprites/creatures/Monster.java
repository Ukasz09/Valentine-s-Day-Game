package com.Ukasz09.ValentineGame.gameModules.sprites.creatures;

import com.Ukasz09.ValentineGame.gameModules.sprites.effects.collisionAvoidEffect.ICollisionAvoidWay;
import com.Ukasz09.ValentineGame.gameModules.utilitis.ViewManager;
import com.Ukasz09.ValentineGame.gameModules.sprites.effects.kickEffect.KickPlayer;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;

import java.util.ArrayList;

public abstract class Monster extends Sprite {
    private double howManyLivesTake;
    private double howBigKickSize;
    private KickPlayer kickMethod;
    private ICollisionAvoidWay collisionAvoidWay;

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public Monster(Image image, KickPlayer kickMethod, ViewManager manager, ICollisionAvoidWay collisionAvoidWay) {
        super(image, manager);
        howManyLivesTake = 0;
        howBigKickSize = 0;
        setVelocity(0, 0);
        this.kickMethod = kickMethod;
        this.collisionAvoidWay=collisionAvoidWay;
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

    //todo: tu skonczylem
    public void update(Sprite target, ArrayList<Monster> monsters) {
        collisionAvoidWay.updateCords(target,this,monsters);
    }

    public void updateByVelocity(Sprite target) {
        double dx = this.getPositionX();
        double dy = this.getPositionY();
        double diffX = target.getPositionX() - dx;
        double diffY = target.getPositionY() - dy;
        float angle = (float) Math.atan2(diffY, diffX);

        dx += this.getVelocityX() * Math.cos(angle);
        dy += this.getVelocityY() * Math.sin(angle);
        this.setPosition(dx, dy);
    }

    public void setImageByPosition(Image left, Image right, Image bottom, Image top, Sprite target) {
        if (isRightSideToTarget(target))
            this.setActualImage(left);
        else this.setActualImage(right);

        if (isExactlyUnderOrAboveTarget(target)) {
            if (isExactlyAboveTarget(target))
                this.setActualImage(bottom);
            else this.setActualImage(top);
        }
    }

    public boolean isRightSideToTarget(Sprite target) {
        double monsterMinX = this.getBoundary().getMinX();
        if (monsterMinX + 0.1 * monsterMinX > target.getBoundary().getMaxX())
            return true;

        return false;
    }

    public boolean isLeftSideToTarget(Sprite target) {
        double monsterMaxX = this.getBoundary().getMaxX();
        if (monsterMaxX - 0.1 * monsterMaxX < target.getBoundary().getMinX())
            return true;

        return false;
    }

    public boolean isUpSideToTarget(Sprite target) {
        double monsterMaxY = this.getBoundary().getMaxY();
        if (monsterMaxY - 0.1 * monsterMaxY < target.getBoundary().getMinY())
            return true;

        return false;
    }

    public boolean isDownSideToTarget(Sprite target) {
        double monsterMinY = this.getBoundary().getMinY();
        if (monsterMinY + 0.1 * monsterMinY > target.getBoundary().getMaxX())
            return true;

        return false;
    }


    private boolean isExactlyUnderOrAboveTarget(Sprite target) {
        double monsterMinX = this.getBoundary().getMinX();
        double monsterMaxX = this.getBoundary().getMaxX();

        if ((monsterMinX > target.getBoundary().getMinX()) && (monsterMaxX < target.getBoundary().getMaxX()))
            return true;

        return false;
    }

    private boolean isExactlyAboveTarget(Sprite target) {
        double monsterMinX = this.getBoundary().getMinX();
        double monsterMaxX = this.getBoundary().getMaxX();
        double monsterMaxY = this.getBoundary().getMaxY();

        if (isExactlyUnderOrAboveTarget(target))
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
