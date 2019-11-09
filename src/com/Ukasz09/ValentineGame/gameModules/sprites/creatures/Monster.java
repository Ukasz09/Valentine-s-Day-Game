package com.Ukasz09.ValentineGame.gameModules.sprites.creatures;

import com.Ukasz09.ValentineGame.gameModules.effects.collisionAvoidEffect.ICollisionAvoidWay;
import com.Ukasz09.ValentineGame.gameModules.utilitis.DirectionEnum;
import com.Ukasz09.ValentineGame.gameModules.utilitis.ViewManager;
import com.Ukasz09.ValentineGame.gameModules.effects.kickEffect.KickPlayer;
import com.Ukasz09.ValentineGame.soundsModule.soundsPath.SoundsPlayer;
import javafx.scene.image.Image;

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
        setActualVelocity(0, 0);
        this.kickMethod = kickMethod;
        this.collisionAvoidWay = collisionAvoidWay;
        setStartedPosition();
    }

    public Monster(Image spriteSheet, double creatureWidth, double creatureHeight, double widthOfOneFrame, double heightOfOneFrame, double durationPerOneFrame, KickPlayer kickMethod, ViewManager manager, ICollisionAvoidWay collisionAvoidWay) {
        super(spriteSheet, creatureWidth, creatureHeight, widthOfOneFrame, heightOfOneFrame, durationPerOneFrame, manager);
        livesTake = 0;
        kickSize = 0;
        setActualVelocity(0, 0);
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
        setActualVelocity(velocityX, velocityY);
    }

    public void update(double elapsedTime, Creature target, ArrayList<Monster> enemiesList) {
        updateNeedToChangeFrame(elapsedTime);
        collisionAvoidWay.updateCords(target, this, enemiesList);
        updateMonsterRotate(target);
    }

    abstract public void updateMonsterRotate(Creature target);

    public void updateByVelocity(Creature target) {
        double dx = this.getPositionX();
        double dy = this.getPositionY();
        float angle = getAngleToTarget(target);
        dx += this.getActualVelocityX() * Math.cos(angle);
        dy += this.getActualVelocityY() * Math.sin(angle);
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
        DirectionEnum direction = DirectionEnum.getRandomDirection(north, south, east, west);
        setPositionByDirection(direction, offset);
    }

    private void setPositionByDirection(DirectionEnum direction, double offset) {
        double positionX, positionY;
        if (direction.equals(DirectionEnum.UP) || direction.equals(DirectionEnum.DOWN)) {
            positionX = Math.random() * getManager().getRightFrameBorder();
            if (direction.equals(DirectionEnum.UP))
                positionY = getManager().getTopFrameBorder() - offset;
            else positionY = getManager().getBottomFrameBorder() + offset;
        } else {
            positionY = Math.random() * getManager().getBottomFrameBorder();
            if (direction.equals(DirectionEnum.LEFT))
                positionX = getManager().getLeftFrameBorder() - offset;
            else positionX = getManager().getRightFrameBorder() + offset;
        }

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
