package com.Ukasz09.ValentineGame.gameModules.sprites.creatures;

import com.Ukasz09.ValentineGame.gameModules.sprites.effects.collisionAvoidEffect.ICollisionAvoidWay;
import com.Ukasz09.ValentineGame.gameModules.sprites.effects.positionByTargetEffect.PositionByTarget;
import com.Ukasz09.ValentineGame.gameModules.utilitis.ViewManager;
import com.Ukasz09.ValentineGame.gameModules.sprites.effects.kickEffect.KickPlayer;
import javafx.scene.image.Image;

import java.util.ArrayList;

public abstract class Monster extends Sprite {
    private double howManyLivesTake;
    private double howBigKickSize;
    private KickPlayer kickMethod;
    private ICollisionAvoidWay collisionAvoidWay;
    private PositionByTarget positionByTarget;

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public Monster(Image image, KickPlayer kickMethod, ViewManager manager, ICollisionAvoidWay collisionAvoidWay) {
        super(image, manager);
        howManyLivesTake = 0;
        howBigKickSize = 0;
        setVelocity(0, 0);
        this.kickMethod = kickMethod;
        this.collisionAvoidWay = collisionAvoidWay;
        this.positionByTarget = new PositionByTarget();
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

    public void update(Sprite target, ArrayList<Monster> monsters) {
        collisionAvoidWay.updateCords(target, this, monsters);
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

    public boolean isRightSideToTarget(Sprite target) {
        return positionByTarget.isRightSideToTarget(this, target);
    }

    public boolean isLeftSideToTarget(Sprite target) {
        return positionByTarget.isLeftSideToTarget(this, target);
    }

    public boolean isUpSideToTarget(Sprite target) {
        return positionByTarget.isUpSideToTarget(this, target);
    }

    public boolean isDownSideToTarget(Sprite target) {
        return positionByTarget.isDownSideToTarget(this, target);
    }

    public boolean isExactlyUnderOrAboveTarget(Sprite target) {
        return positionByTarget.isExactlyUnderOrAboveTarget(this, target);
    }

    public boolean isExactlyAboveTarget(Sprite target) {
        return positionByTarget.isExactlyAboveTarget(this, target);
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
