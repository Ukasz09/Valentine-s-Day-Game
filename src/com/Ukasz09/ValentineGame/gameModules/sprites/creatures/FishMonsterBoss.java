package com.Ukasz09.ValentineGame.gameModules.sprites.creatures;

import com.Ukasz09.ValentineGame.gameModules.effects.collisionAvoidEffect.ICollisionAvoidWay;
import com.Ukasz09.ValentineGame.gameModules.effects.rotateEffect.RotateEffect;
import com.Ukasz09.ValentineGame.gameModules.utilitis.DirectionEnum;
import com.Ukasz09.ValentineGame.gameModules.utilitis.ViewManager;
import com.Ukasz09.ValentineGame.gameModules.effects.kickEffect.KickPlayer;
import com.Ukasz09.ValentineGame.graphicModule.texturesPath.ImageSheetProperty;
import com.Ukasz09.ValentineGame.graphicModule.texturesPath.KindOfState;
import com.Ukasz09.ValentineGame.soundsModule.soundsPath.SoundsPath;
import com.Ukasz09.ValentineGame.gameModules.sprites.items.shields.AutoActivateShield;
import com.Ukasz09.ValentineGame.gameModules.effects.healthStatusBars.HealthStatusBar;
import com.Ukasz09.ValentineGame.gameModules.sprites.items.shields.Shield;
import com.Ukasz09.ValentineGame.soundsModule.soundsPath.SoundsPlayer;

import java.util.ArrayList;

public class FishMonsterBoss extends Monster {
    private static final String HIT_SOUND_PATH = SoundsPath.FISH_MINIBOSS_HIT_SOUND_PATH;
    private static final String DEATH_SOUND_PATH = SoundsPath.FISH_MINIBOSS_DEATH_SOUND_PATH;
    private static final String MISS_SHOT_SOUND_PATH = SoundsPath.FISH_MONSTER_MISS_SHOT_SOUND_PATH;

    private static final double DEATH_SOUND_VOLUME = 1;
    private static final double HIT_SOUND_VOLUME = 1;
    private static final double MISS_SHOT_SOUND_VOLUME = 1;

    private static final SoundsPlayer HIT_SOUND = new SoundsPlayer(HIT_SOUND_PATH, HIT_SOUND_VOLUME, false);
    private static final SoundsPlayer MISS_SOUND = new SoundsPlayer(MISS_SHOT_SOUND_PATH, MISS_SHOT_SOUND_VOLUME, false);
    private static final SoundsPlayer DEATH_SOUND = new SoundsPlayer(DEATH_SOUND_PATH, DEATH_SOUND_VOLUME, false);

    private static final double DEFAULT_SPRITE_WIDTH = 318;
    private static final double DEFAULT_SPRITE_HEIGHT = 308;

    private static final double DEFAULT_SHIELD_WIDTH = 318;
    private static final double DEFAULT_SHIELD_HEIGHT = 308;

    private static final double DEFAULT_LIVES = 20;
    private static final double DEFAULT_LIVES_TAKE = 1.5;
    private static final int DEFAULT_KICK_SIZE = 25;
    private static final double DEFAULT_VELOCITY_X = 3;
    private static final double DEFAULT_VELOCITY_Y = 3;
    private static final int DEFAULT_SHIELD_COOLDOWN = 5000;
    private static final int DEFAULT_SHIELD_DURATION = 10000;

    private final Shield shield;
    private final HealthStatusBar healthBar;
    private KindOfState actualState;

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public FishMonsterBoss(ImageSheetProperty spriteSheetProperty, ImageSheetProperty shieldSheetProperty, KickPlayer kickMethod, ViewManager manager, ICollisionAvoidWay collisionAvoidWay) {
        super(spriteSheetProperty, DEFAULT_SPRITE_WIDTH, DEFAULT_SPRITE_HEIGHT, manager);
        hueImage(-1, 1);
//        setSpriteSheetProperties(WIDTH_OF_ONE_FRAME, HEIGHT_OF_ONE_FRAME);
        setCreatureProperties(DEFAULT_LIVES, DEFAULT_VELOCITY_X, DEFAULT_VELOCITY_Y);
        setMonsterProperties(kickMethod, DEFAULT_KICK_SIZE, DEFAULT_LIVES_TAKE, collisionAvoidWay);

        actualState = spriteSheetProperty.getMove();
        shield = new AutoActivateShield(shieldSheetProperty, DEFAULT_SHIELD_WIDTH, DEFAULT_SHIELD_HEIGHT, DEFAULT_SHIELD_COOLDOWN, DEFAULT_SHIELD_DURATION, manager);
        healthBar = new HealthStatusBar(DEFAULT_LIVES, getWidth(), getPositionX(), getPositionY());
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public void updateShield() {
        shield.updateShield();
    }

    public void activateShield() {
        shield.activateShield();
    }

    @Override
    public void setStartedPosition() {
        setPositionByDirection(false, false, true, true, getWidth() * 3);
    }

    @Override
    public void updateMonsterRotate(Creature target) {
        double properRotate = RotateEffect.setRotateByAngle(this, target);
        setActualRotation(properRotate);
    }

    @Override
    public void update(Creature player, ArrayList<Monster> enemiesList) {
        super.update(player, enemiesList);
        healthBar.update(getLives(), getPositionX(), getPositionY());
        updateShield();
    }

    @Override
    public void render() {
        super.render();
        healthBar.draw(getManager().getGraphicContext());
        renderShield();
    }

    //todo: zmienic render shielda
    private void renderShield() {
        if (shield.isActive()) {
            double centerPositionX;
            if (getImageDirection().equals(DirectionEnum.LEFT))
                centerPositionX = getPositionX() + 40;
            else centerPositionX = getPositionX();
            double centerPositionY = getPositionY();

            shield.render(centerPositionX, centerPositionY);
        }
    }

    @Override
    public SoundsPlayer getHitSoundOrNull() {
        return FishMonsterBoss.HIT_SOUND;
    }

    @Override
    public SoundsPlayer getMissSoundOrNull() {
        return FishMonsterBoss.MISS_SOUND;
    }

    @Override
    public SoundsPlayer getDeathSoundOrNull() {
        return FishMonsterBoss.DEATH_SOUND;
    }

    @Override
    public boolean hasActiveShield() {
        return shield.isActive();
    }

    @Override
    protected void setPositionOfNextFrame() {
        setPositionOfNextFrame(actualState.getMinX(), actualState.getMaxX(), actualState.getMinY(), actualState.getMaxY(), spriteImage.getWidth());
    }
}
