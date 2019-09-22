package com.Ukasz09.ValentineGame.gameModules.sprites.creatures;

import com.Ukasz09.ValentineGame.gameModules.sprites.effects.collisionAvoidEffect.ICollisionAvoidWay;
import com.Ukasz09.ValentineGame.gameModules.sprites.effects.positionByTargetEffect.PositionByTarget;
import com.Ukasz09.ValentineGame.gameModules.sprites.effects.rotateEffect.RotateEffect;
import com.Ukasz09.ValentineGame.gameModules.utilitis.DirectionEnum;
import com.Ukasz09.ValentineGame.gameModules.utilitis.ViewManager;
import com.Ukasz09.ValentineGame.gameModules.sprites.effects.kickEffect.KickPlayer;
import com.Ukasz09.ValentineGame.soundsModule.soundsPath.SoundsPlayer;
import javafx.scene.image.Image;

import java.security.InvalidParameterException;
import java.util.ArrayList;

/*
hitSound, deathSound, missSound - is static to avoid remove object by garbage collector before sound stop play effect
 */
public abstract class Monster extends Sprite {
    private double howManyLivesTake;
    private double howBigKickSize;
    private KickPlayer kickMethod;
    private ICollisionAvoidWay collisionAvoidWay;
    private PositionByTarget positionByTarget;
    private RotateEffect rotateWay;

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public Monster(Image image, KickPlayer kickMethod, ViewManager manager, ICollisionAvoidWay collisionAvoidWay) {
        super(image, manager);
        howManyLivesTake = 0;
        howBigKickSize = 0;
        setVelocity(0, 0);
        this.kickMethod = kickMethod;
        this.collisionAvoidWay = collisionAvoidWay;
        this.positionByTarget = new PositionByTarget();
        rotateWay = new RotateEffect();
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public boolean kickAfterHit() {
        if (howBigKickSize > 0) return true;

        return false;
    }

    public void update(Sprite target, ArrayList<Monster> enemiesList) {
        collisionAvoidWay.updateCords(target, this, enemiesList);
        updateMonsterRotate(target);
    }

    public void updateByVelocity(Sprite target) {
        double dx = this.getPositionX();
        double dy = this.getPositionY();
        float angle = getAngleToTarget(target);
        dx += this.getVelocityX() * Math.cos(angle);
        dy += this.getVelocityY() * Math.sin(angle);
        this.setPosition(dx, dy);
    }

    public void updateMonsterRotate(Sprite target) {
        double properRotate = rotateWay.setRotateByAngle(this, target);
        setActualRotate(properRotate);
    }

    public void kickPlayer(Player p) {
        kickMethod.kickPlayerByMonsterPostion(this, p, getManager());
    }

    public void isDeadAction() {
        if (getDeathSoundOrNull() != null)
            getDeathSoundOrNull().playSound();
        else System.out.println("Error: deathSound is null");
    }

    public void isHitAction() {
        if (getHitSoundOrNull() != null)
            getHitSoundOrNull().playSound();
        else System.out.println("Error: hitSound is null");
    }

    public void missHitAction() {
        if (getMissSoundOrNull() != null)
            getMissSoundOrNull().playSound();
        else System.out.println("Error: missHitSound is null");
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

    //todo: zrobione
    public abstract void setStartedPosition();

    //todo: zrobione
    private DirectionEnum randomPositionByDirection(boolean north, boolean south, boolean east, boolean west) {
        DirectionEnum direction;
        try {
            direction = DirectionEnum.getRandomDirection(north, south, east, west);
            return direction;
        } catch (InvalidParameterException anyDirection) {
            return DirectionEnum.NORTH;
        }
    }

    //todo: zrobione
    public void setPositionByDirection(boolean north, boolean south, boolean east, boolean west, double offset) {
        DirectionEnum direction = randomPositionByDirection(north, south, east, west);
        setPositionByDirection(direction, offset);
    }

    //todo: zrobione
    private void setPositionByDirection(DirectionEnum direction, double offset) {
        switch (direction) {
            case NORTH:
                setPositionFromNorth(offset);
                break;
            case SOUTH:
                setPositionFromSouth(offset);
                break;
            case EAST:
                setPositionFromEast(offset);
                break;
            case WEST:
                setPositionFromWest(offset);
                break;
        }
    }

    //todo: zrobione
    private void setPositionFromNorth(double offset) {
        double positionX = Math.random() * getManager().getRightBorder();
        double positionY = getManager().getTopBorder() - offset;
        this.setPosition(positionX, positionY);
    }

    //todo: zrobione
    private void setPositionFromSouth(double offset) {
        double positionX = Math.random() * getManager().getRightBorder();
        double positionY = getManager().getBottomBorder() + offset;
        this.setPosition(positionX, positionY);
    }

    //todo: zrobione
    private void setPositionFromEast(double offset) {
        double positionX = getManager().getLeftBorder() - offset;
        double positionY = Math.random() * getManager().getBottomBorder();
        this.setPosition(positionX, positionY);
    }

    //todo: zrobione
    private void setPositionFromWest(double offset) {
        double positionX = getManager().getRightBorder() + offset;
        double positionY = Math.random() * getManager().getBottomBorder();
        this.setPosition(positionX, positionY);
    }

    abstract public  SoundsPlayer getHitSoundOrNull();


    abstract public SoundsPlayer getMissSoundOrNull();


    abstract public SoundsPlayer getDeathSoundOrNull();

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
