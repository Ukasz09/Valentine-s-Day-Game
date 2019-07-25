package com.Ukasz09.ValentineGame.gameModules.sprites.creatures;

import com.Ukasz09.ValentineGame.gameModules.sprites.effects.collisionAvoidEffect.ICollisionAvoidWay;
import com.Ukasz09.ValentineGame.gameModules.sprites.effects.imageByPositionEffect.PropperImageSet;
import com.Ukasz09.ValentineGame.gameModules.sprites.effects.positionByTargetEffect.IPositionByTarget;
import com.Ukasz09.ValentineGame.gameModules.utilitis.ViewManager;
import com.Ukasz09.ValentineGame.gameModules.sprites.effects.kickEffect.KickPlayer;
import com.Ukasz09.ValentineGame.gameModules.sprites.effects.shieldsEffect.ShieldKindOfRender;
import com.Ukasz09.ValentineGame.soundsModule.soundsPath.SoundsPath;
import com.Ukasz09.ValentineGame.soundsModule.soundsPath.SoundsPlayer;
import com.Ukasz09.ValentineGame.gameModules.sprites.effects.shieldsEffect.AutoActivateShield;
import com.Ukasz09.ValentineGame.gameModules.sprites.effects.healthStatusBars.HealthStatusBar;
import com.Ukasz09.ValentineGame.gameModules.sprites.effects.shieldsEffect.Shield;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.util.ArrayList;

public class FishMonsterMiniboss extends Monster implements ShieldKindOfRender {
    private final String hitSoundPath = SoundsPath.FISH_MINIBOSS_HIT_SOUND_PATH;
    private final String deathSoundPath = SoundsPath.FISH_MINIBOSS_DEATH_SOUND_PATH;
    private final String missSoundPath = SoundsPath.FISH_MONSTER_MISS_SHOT_SOUND_PATH;
    private static final double DEATH_SOUND_VOLUME = 1;
    private static final double HIT_SOUND_VOLUME = 1;
    private static final double MISS_SOUND_VOLUME = 1;

    private final double howManyLivesTake = 1.5;
    private final int howBigKickSize = 150;
    private final double maxLive = 20;
    private final double velocityX = 3;
    private final double velocityY = 3;
    private final int defaultShieldTimer = 5000;
    private final int defaultShieldDuration = 10000;

    private Shield shield;
    private Image imageLeft;
    private Image imageRight;
    private Image imageBottom;
    private Image imageTop;
    private HealthStatusBar healthBar;
    private PropperImageSet imageSetWay;

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public FishMonsterMiniboss(Image imageLeft, Image imageRight, Image imageBottom, Image imageTop, Image shieldImage, KickPlayer kickMethod, ViewManager manager, ICollisionAvoidWay collisionAvoidWay, IPositionByTarget positionByTarget) {
        super(imageLeft, kickMethod, manager, collisionAvoidWay, positionByTarget);
        setLives(maxLive);
        setProtectionTime(0);
        shield = new AutoActivateShield(defaultShieldTimer, defaultShieldDuration, shieldImage, this);
        setHitSound(new SoundsPlayer(hitSoundPath));
        setDeathSound(new SoundsPlayer(deathSoundPath));
        setMissSound(new SoundsPlayer(missSoundPath));
        setHowBigKickSize(howBigKickSize);
        setHowManyLivesTake(howManyLivesTake);
        setVelocity(velocityX, velocityY);

        this.imageLeft = imageLeft;
        this.imageRight = imageRight;
        this.imageBottom = imageBottom;
        this.imageTop = imageTop;
        healthBar = new HealthStatusBar(maxLive, getWidth(), getPositionX(), getPositionY());
        this.imageSetWay = new PropperImageSet();
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public void updateShield() {
        shield.updateShield();
    }

    public void activateShield() {
        shield.activateShield();
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public void setImageByPosition(Image left, Image right, Image bottom, Image top, Sprite target) {
        imageSetWay.byTargetPosition(left, right, bottom, top, this, target);
    }

    @Override
    public void update(Sprite player, ArrayList<Monster> monsters) {
        super.update(player, monsters);
        setImageByPosition(imageLeft, imageRight, imageBottom, imageTop, player);
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
            if (getActualImage() == imageLeft)
                centerPositionX = getPositionX() + 40;
            else centerPositionX = getPositionX();
            double centerPositionY = getPositionY();

            gc.drawImage(shield.getShieldImage(), centerPositionX, centerPositionY);
        }
    }

    @Override
    public void isDeadAction() {
        defaultIsDeadAction(DEATH_SOUND_VOLUME);
    }

    @Override
    public void isHitAction() {
        defaultIsHitAction(HIT_SOUND_VOLUME);
    }

    @Override
    public void missHitAction() {
        defaultMissHitAction(MISS_SOUND_VOLUME);
    }
}
