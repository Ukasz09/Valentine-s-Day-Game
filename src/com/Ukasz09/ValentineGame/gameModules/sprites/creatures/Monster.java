package com.Ukasz09.ValentineGame.gameModules.sprites.creatures;

import com.Ukasz09.ValentineGame.gameModules.effects.collisionAvoidEffect.ICollisionAvoidWay;
import com.Ukasz09.ValentineGame.gameModules.utilitis.DirectionEnum;
import com.Ukasz09.ValentineGame.gameModules.utilitis.Logger;
import com.Ukasz09.ValentineGame.gameModules.utilitis.ViewManager;
import com.Ukasz09.ValentineGame.gameModules.effects.kickEffect.KickPlayer;
import com.Ukasz09.ValentineGame.graphicModule.texturesPath.ImageSheetProperty;
import com.Ukasz09.ValentineGame.soundsModule.soundsPath.SoundsPlayer;
import javafx.scene.SnapshotParameters;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public abstract class Monster extends Creature {
    private double kickSize;
    private double livesTake;
    private KickPlayer kickMethod;
    private ICollisionAvoidWay collisionAvoidWay;

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public Monster(ImageSheetProperty spriteSheetProperty, double creatureWidth, double creatureHeight, ViewManager manager) {
        super(spriteSheetProperty, creatureWidth, creatureHeight, manager);
        setStartedPosition();
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    protected void setMonsterProperties(KickPlayer kickMethod, double kickSize, double livesTake, ICollisionAvoidWay collisionAvoidWay) {
        this.kickMethod = kickMethod;
        this.kickSize = kickSize;
        this.livesTake = livesTake;
        this.collisionAvoidWay = collisionAvoidWay;
    }

    protected void hueImage(double minRandHueOffset, double maxRandHueOffset) {
        ImageView iv = new ImageView(spriteImage);
        ColorAdjust hueEffect = new ColorAdjust();
        double randHue = (Math.random() * (maxRandHueOffset - minRandHueOffset + 1)) + minRandHueOffset;
        hueEffect.setHue(randHue);
        iv.setEffect(hueEffect);
        SnapshotParameters params = new SnapshotParameters();
        params.setFill(Color.TRANSPARENT);
        Image huedImage = iv.snapshot(params, null);
        spriteImage = huedImage;
    }

    @Override
    public void render() {
        renderSpriteWithRotation();
    }

    abstract public SoundsPlayer getHitSoundOrNull();

    abstract public SoundsPlayer getMissSoundOrNull();

    abstract public SoundsPlayer getDeathSoundOrNull();

    abstract public void setStartedPosition();

    abstract public boolean hasActiveShield();

    public void update(Creature target, ArrayList<Monster> enemiesList) {
        updateSpriteSheetFrame();
        collisionAvoidWay.updateCords(target, this, enemiesList);
        updateMonsterRotate(target);
    }

    protected abstract void updateMonsterRotate(Creature target);

    public void updateByVelocity(Creature target) {
        double dx = this.getPositionX();
        double dy = this.getPositionY();
        float angle = getAngleToTarget(target);
        dx += this.getActualVelocityX() * Math.cos(angle);
        dy += this.getActualVelocityY() * Math.sin(angle);
        this.setPosition(dx, dy);
    }

    protected void kickPlayer(Player p) {
        if (doKick())
            kickMethod.kickPlayerByMonsterPosition(this, p, getManager());
    }

    private boolean doKick() {
        return (kickSize > 0);
    }

    protected void actionWhenDead() {
        if (getDeathSoundOrNull() != null)
            getDeathSoundOrNull().playSound();
        else Logger.logError(getClass(), "DeathSound is null");
    }

    protected void actionWhenHit() {
        if (getHitSoundOrNull() != null)
            getHitSoundOrNull().playSound();
        else Logger.logError(getClass(), "HitSound is null");
    }

    public void actionWhenMissHit() {
        if (getMissSoundOrNull() != null)
            getMissSoundOrNull().playSound();
        else Logger.logError(getClass(), "MissHitSound is null");
    }

    protected boolean isDead() {
        return (getLives() <= 0);
    }

    public boolean isLeftSideToTarget(Creature target) {
        double creatureMinX = this.getBoundary().getMinX();
        return (creatureMinX <= target.getBoundary().getMinX());
    }

    public boolean isUpSideToTarget(Creature target) {
        double creatureMinY = this.getBoundary().getMinY();
        return creatureMinY <= target.getBoundary().getMinY();
    }

    protected void setPositionByDirection(boolean north, boolean south, boolean east, boolean west, double offset) {
        DirectionEnum direction = DirectionEnum.getRandomDirection(north, south, east, west);
        setPositionByDirection(direction, offset);
    }

    private void setPositionByDirection(DirectionEnum direction, double offset) {
        double positionX, positionY;
        if (direction.equals(DirectionEnum.UP) || direction.equals(DirectionEnum.DOWN)) {
            positionX = Math.random() * getManager().getRightFrameBorder();
            if (direction.equals(DirectionEnum.UP))
                positionY = -offset;
            else positionY = getManager().getBottomFrameBorder() + offset;
        } else {
            positionY = Math.random() * getManager().getBottomFrameBorder();
            if (direction.equals(DirectionEnum.LEFT))
                positionX = -offset;
            else positionX = getManager().getRightFrameBorder() + offset;
        }

        this.setPosition(positionX, positionY);
    }

    public double getKickSize() {
        return kickSize;
    }

    public double getLivesTake() {
        return livesTake;
    }
}
