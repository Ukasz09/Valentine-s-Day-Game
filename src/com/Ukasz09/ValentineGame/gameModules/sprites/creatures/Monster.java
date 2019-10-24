package com.Ukasz09.ValentineGame.gameModules.sprites.creatures;

import com.Ukasz09.ValentineGame.gameModules.effects.collisionAvoidEffect.ICollisionAvoidWay;
import com.Ukasz09.ValentineGame.gameModules.utilitis.DirectionEnum;
import com.Ukasz09.ValentineGame.gameModules.utilitis.ViewManager;
import com.Ukasz09.ValentineGame.gameModules.effects.kickEffect.KickPlayer;
import com.Ukasz09.ValentineGame.soundsModule.soundsPath.SoundsPlayer;
import javafx.scene.image.Image;

import java.security.InvalidParameterException;
import java.util.ArrayList;

/*
    hitSound, deathSound, missSound - is static to avoid remove object by garbage collector before sound stop play effect
 */
public abstract class Monster extends Creature {
    private double kickSize;
    private double livesTake;
    private KickPlayer kickMethod;
    private ICollisionAvoidWay collisionAvoidWay;

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public Monster(Image image, KickPlayer kickMethod, ViewManager manager, ICollisionAvoidWay collisionAvoidWay) {
        super(image, manager);
        livesTake = 0;
        kickSize = 0;
        setVelocity(0, 0);
        this.kickMethod = kickMethod;
        this.collisionAvoidWay = collisionAvoidWay;
        setStartedPosition();
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @Override
    public void render() {
        renderSpriteWithRotation();
    }


    abstract public SoundsPlayer getHitSoundOrNull();

    abstract public SoundsPlayer getMissSoundOrNull();

    abstract public SoundsPlayer getDeathSoundOrNull();

    abstract public void setStartedPosition();

    abstract public boolean hasActiveShield();

    protected void setProperties(double kickSize, double livesTake, double lives, double velocityX, double velocityY) {
        setLives(lives);
        this.kickSize = kickSize;
        this.livesTake = livesTake;
        setVelocity(velocityX, velocityY);
    }

    public void update(Creature target, ArrayList<Monster> enemiesList) {
        collisionAvoidWay.updateCords(target, this, enemiesList);
        updateMonsterRotate(target);
    }

    abstract public void updateMonsterRotate(Creature target);

    public void updateByVelocity(Creature target) {
        double dx = this.getPositionX();
        double dy = this.getPositionY();
        float angle = getAngleToTarget(target);
        dx += this.getVelocityX() * Math.cos(angle);
        dy += this.getVelocityY() * Math.sin(angle);
        this.setPosition(dx, dy);
    }

    public void kickPlayer(Player p) {
        if (doKick())
            kickMethod.kickPlayerByMonsterPostion(this, p, getManager());
    }

    private boolean doKick() {
        if (kickSize > 0)
            return true;
        return false;
    }

    public void actionWhenDead() {
        if (getDeathSoundOrNull() != null)
            getDeathSoundOrNull().playSound();
        else System.out.println("Error: deathSound is null");
    }

    public void actionWhenHit() {
        if (getHitSoundOrNull() != null)
            getHitSoundOrNull().playSound();
        else System.out.println("Error: hitSound is null");
    }

    public void actionWhenMissHit() {
        if (getMissSoundOrNull() != null)
            getMissSoundOrNull().playSound();
        else System.out.println("Error: missHitSound is null");
    }

    public boolean isDead() {
        return (getLives() <= 0);
    }

    public boolean isLeftSideToTarget(Creature target) {
        double creatureMinX = this.getBoundary().getMinX();
        if (creatureMinX <= target.getBoundary().getMinX())
            return true;

        return false;
    }

    public boolean isUpSideToTarget(Creature target) {
        double creatureMinY = this.getBoundary().getMinY();
        if (creatureMinY <= target.getBoundary().getMinY())
            return true;

        return false;
    }

    public void setPositionByDirection(boolean north, boolean south, boolean east, boolean west, double offset) {
        DirectionEnum direction = randomPositionByDirection(north, south, east, west);
        setPositionByDirection(direction, offset);
    }

    private DirectionEnum randomPositionByDirection(boolean north, boolean south, boolean east, boolean west) {
        DirectionEnum direction;
        try {
            direction = DirectionEnum.getRandomDirection(north, south, east, west);
            return direction;
        } catch (InvalidParameterException anyDirection) {
            return DirectionEnum.NORTH;
        }
    }

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

    private void setPositionFromNorth(double offset) {
        double positionX = Math.random() * getManager().getRightBorder();
        double positionY = getManager().getTopBorder() - offset;
        this.setPosition(positionX, positionY);
    }

    private void setPositionFromSouth(double offset) {
        double positionX = Math.random() * getManager().getRightBorder();
        double positionY = getManager().getBottomBorder() + offset;
        this.setPosition(positionX, positionY);
    }

    private void setPositionFromEast(double offset) {
        double positionX = getManager().getLeftBorder() - offset;
        double positionY = Math.random() * getManager().getBottomBorder();
        this.setPosition(positionX, positionY);
    }

    private void setPositionFromWest(double offset) {
        double positionX = getManager().getRightBorder() + offset;
        double positionY = Math.random() * getManager().getBottomBorder();
        this.setPosition(positionX, positionY);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public void setLivesTake(double livesTake) {
        this.livesTake = livesTake;
    }

    public void setKickSize(double kickSize) {
        this.kickSize = kickSize;
    }

    public double getKickSize() {
        return kickSize;
    }

    public double getLivesTake() {
        return livesTake;
    }

}
