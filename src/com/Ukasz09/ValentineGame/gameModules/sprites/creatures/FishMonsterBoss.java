package com.Ukasz09.ValentineGame.gameModules.sprites.creatures;

import com.Ukasz09.ValentineGame.gameModules.sprites.effects.collisionAvoidEffect.ICollisionAvoidWay;
import com.Ukasz09.ValentineGame.gameModules.utilitis.ViewManager;
import com.Ukasz09.ValentineGame.gameModules.sprites.effects.kickEffect.KickPlayer;
import com.Ukasz09.ValentineGame.gameModules.sprites.effects.shieldsEffect.ShieldKindOfRender;
import com.Ukasz09.ValentineGame.soundsModule.soundsPath.SoundsPath;
import com.Ukasz09.ValentineGame.gameModules.sprites.effects.shieldsEffect.AutoActivateShield;
import com.Ukasz09.ValentineGame.gameModules.sprites.effects.healthStatusBars.HealthStatusBar;
import com.Ukasz09.ValentineGame.gameModules.sprites.effects.shieldsEffect.Shield;
import com.Ukasz09.ValentineGame.soundsModule.soundsPath.SoundsPlayer;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.util.ArrayList;

public class FishMonsterBoss extends Monster implements ShieldKindOfRender {
    public static final String HIT_SOUND_PATH = SoundsPath.FISH_MINIBOSS_HIT_SOUND_PATH;
    public static final String DEATH_SOUND_PATH = SoundsPath.FISH_MINIBOSS_DEATH_SOUND_PATH;
    public static final String MISS_SHOT_SOUND_PATH = SoundsPath.FISH_MONSTER_MISS_SHOT_SOUND_PATH;
    public static final double DEATH_SOUND_VOLUME = 1;
    public static final double HIT_SOUND_VOLUME = 1;
    public static final double MISS_SHOT_SOUND_VOLUME = 1;

    private static final SoundsPlayer HIT_SOUND = new SoundsPlayer(HIT_SOUND_PATH, HIT_SOUND_VOLUME, false);
    private static final SoundsPlayer MISS_SOUND = new SoundsPlayer(MISS_SHOT_SOUND_PATH, MISS_SHOT_SOUND_VOLUME, false);
    private static final SoundsPlayer DEATH_SOUND = new SoundsPlayer(DEATH_SOUND_PATH, DEATH_SOUND_VOLUME, false);

    private final double howManyLivesTake = 1.5;
    private final int howBigKickSize = 0; //todo: tmp
    private final double maxLive = 20;
    private final double velocityX = 3;
    private final double velocityY = 3;
    private final int defaultShieldTimer = 5000;
    private final int defaultShieldDuration = 10000;

    private Shield shield;
    private HealthStatusBar healthBar;

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public FishMonsterBoss(Image right, Image shieldImage, KickPlayer kickMethod, ViewManager manager, ICollisionAvoidWay collisionAvoidWay) {
        super(right, kickMethod, manager, collisionAvoidWay);
        setLives(maxLive);
        setProtectionTime(0);
        shield = new AutoActivateShield(defaultShieldTimer, defaultShieldDuration, shieldImage, this);
//        setHitSound(HIT_SOUND_PATH, HIT_SOUND_VOLUME);
//        setDeathSound(DEATH_SOUND_PATH, DEATH_SOUND_VOLUME);
//        setMissSound(MISS_SHOT_SOUND_PATH, MISS_SHOT_SOUND_VOLUME);
        setHowBigKickSize(howBigKickSize);
        setHowManyLivesTake(howManyLivesTake);
        setVelocity(velocityX, velocityY);

        healthBar = new HealthStatusBar(maxLive, getWidth(), getPositionX(), getPositionY());
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
    public void update(Sprite player, ArrayList<Monster> enemiesList) {
        super.update(player, enemiesList);
        healthBar.update(getLives(), getPositionX(), getPositionY());
        updateShield();
    }

    @Override
    public void render() {
        super.render();
        healthBar.draw(getManager().getGraphicContext());
        renderShield(getManager().getGraphicContext());
    }

    @Override
    public void renderShield(GraphicsContext gc) {
        if (getProtectionTime() != 0) {
            double centerPositionX;
            if (getImageDirection().equals(YAxisDirection.LEFT))
                centerPositionX = getPositionX() + 40;
            else centerPositionX = getPositionX();
            double centerPositionY = getPositionY();

            gc.drawImage(shield.getShieldImage(), centerPositionX, centerPositionY);
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
}
