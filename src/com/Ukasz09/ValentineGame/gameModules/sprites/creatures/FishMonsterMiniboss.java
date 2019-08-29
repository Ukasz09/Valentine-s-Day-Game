package com.Ukasz09.ValentineGame.gameModules.sprites.creatures;

import com.Ukasz09.ValentineGame.gameModules.sprites.effects.collisionAvoidEffect.ICollisionAvoidWay;
import com.Ukasz09.ValentineGame.gameModules.sprites.effects.imageByPositionEffect.ProperImageSet;
import com.Ukasz09.ValentineGame.gameModules.utilitis.ViewManager;
import com.Ukasz09.ValentineGame.gameModules.sprites.effects.kickEffect.KickPlayer;
import com.Ukasz09.ValentineGame.gameModules.sprites.effects.shieldsEffect.ShieldKindOfRender;
import com.Ukasz09.ValentineGame.soundsModule.soundsPath.SoundsPath;
import com.Ukasz09.ValentineGame.soundsModule.soundsPath.SoundsPlayer;
import com.Ukasz09.ValentineGame.gameModules.sprites.effects.shieldsEffect.AutoActivateShield;
import com.Ukasz09.ValentineGame.gameModules.sprites.effects.healthStatusBars.HealthStatusBar;
import com.Ukasz09.ValentineGame.gameModules.sprites.effects.shieldsEffect.Shield;
import javafx.geometry.Rectangle2D;
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
    private final int howBigKickSize = 0; //todo: tmp
    private final double maxLive = 20;
    private final double velocityX = 3;
    private final double velocityY = 3;
    private final int defaultShieldTimer = 5000;
    private final int defaultShieldDuration = 10000;

    private Shield shield;
    private HealthStatusBar healthBar;

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public FishMonsterMiniboss(Image right, Image shieldImage, KickPlayer kickMethod, ViewManager manager, ICollisionAvoidWay collisionAvoidWay) {
        super(right, kickMethod, manager, collisionAvoidWay);
        setLives(maxLive);
        setProtectionTime(0);
        shield = new AutoActivateShield(defaultShieldTimer, defaultShieldDuration, shieldImage, this);
        setHitSound(new SoundsPlayer(hitSoundPath));
        setDeathSound(new SoundsPlayer(deathSoundPath));
        setMissSound(new SoundsPlayer(missSoundPath));
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

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

//    public void setImageByPosition(Image left, Image leftUp, Image leftDown, Image right, Image rightUp, Image rightDown, Image down, Image up, Sprite target) {
//        getImageSetWay().byTargetPosition(left, leftUp, leftDown, right, rightUp, rightDown, down, up, this, target);
//    }

    @Override
    public void update(Sprite player, ArrayList<Monster> monsters) {
        super.update(player, monsters);
//        setImageByPosition(imageLeft, imageLeftUp, imageLeftDown, imageRight, imageRightUp,imageRightDown, imageDown, imageUp, player);
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
