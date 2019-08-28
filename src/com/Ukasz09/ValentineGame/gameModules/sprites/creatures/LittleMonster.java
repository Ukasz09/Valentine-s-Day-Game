package com.Ukasz09.ValentineGame.gameModules.sprites.creatures;

import com.Ukasz09.ValentineGame.gameModules.sprites.effects.collisionAvoidEffect.ICollisionAvoidWay;
import com.Ukasz09.ValentineGame.gameModules.utilitis.ViewManager;
import com.Ukasz09.ValentineGame.gameModules.sprites.effects.kickEffect.KickPlayer;
import com.Ukasz09.ValentineGame.soundsModule.soundsPath.SoundsPath;
import com.Ukasz09.ValentineGame.soundsModule.soundsPath.SoundsPlayer;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;

public class LittleMonster extends Monster {
    private final String hitSoundPath = SoundsPath.LITTLE_MONSTER_HIT_SOUND_PATH;
    private final String deathSoundPath = SoundsPath.LITTLE_MONSTER_DEATH_SOUND_PATH;
    private static final double DEATH_SOUND_VOLUME = 1;
    private static final double HIT_SOUND_VOLUME = 1;

    private final double howManyLivesTake = 0.5;
    private final int howBigKickSize = 0;
    private final double velocityX = 1;
    private final double velocityY = 1;

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public LittleMonster(Image image, KickPlayer kickMethod, ViewManager manager, ICollisionAvoidWay collisionAvoidWay) {
        super(image, kickMethod, manager, collisionAvoidWay);
        setLives(3);

        setHitSound(new SoundsPlayer(hitSoundPath));
        setDeathSound(new SoundsPlayer(deathSoundPath));
        setHowBigKickSize(howBigKickSize);
        setHowManyLivesTake(howManyLivesTake);
        setVelocity(velocityX, velocityY);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @Override
    public void isDeadAction() {
        defaultIsDeadAction(DEATH_SOUND_VOLUME);
    }

    @Override
    public void isHitAction() {
        defaultIsHitAction(HIT_SOUND_VOLUME);
    }

    @Override
    public void updateImageDirection() {
        //todo: to do
    }

    @Override
    public void missHitAction() {
        //nothing
    }

    @Override
    public Rectangle2D getBoundaryForCollision() {
        return getBoundary();
    }
}
