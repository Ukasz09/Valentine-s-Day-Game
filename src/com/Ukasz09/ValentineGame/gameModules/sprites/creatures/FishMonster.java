package com.Ukasz09.ValentineGame.gameModules.sprites.creatures;

import com.Ukasz09.ValentineGame.gameModules.sprites.effects.collisionAvoidEffect.ICollisionAvoidWay;
import com.Ukasz09.ValentineGame.gameModules.sprites.effects.imageByPositionEffect.PropperImageSet;
import com.Ukasz09.ValentineGame.gameModules.sprites.effects.positionByTargetEffect.IPositionByTarget;
import com.Ukasz09.ValentineGame.gameModules.utilitis.ViewManager;
import com.Ukasz09.ValentineGame.gameModules.sprites.effects.kickEffect.KickPlayer;
import com.Ukasz09.ValentineGame.soundsModule.soundsPath.SoundsPath;
import com.Ukasz09.ValentineGame.soundsModule.soundsPath.SoundsPlayer;
import javafx.scene.image.Image;

import java.util.ArrayList;

public class FishMonster extends Monster {
    private final String hitSoundPath = SoundsPath.FISH_MONSTER_HIT_SOUND_PATH;
    private final String deathSoundPath = SoundsPath.FISH_MONSTER_DEATH_SOUND_PATH;
    private static final double DEFAULT_DEATH_VOLUME = 1;
    private static final double DEFAULT_HIT_VOLUME = 1;

    private final double howManyLivesTake = 0.5;
    private final int howBigKickSize = 0;
    private final double velocityX = 2;
    private final double velocityY = 2;

    private Image imageLeft;
    private Image imageRight;
    private Image imageBottom;
    private Image imageTop;

    private PropperImageSet imageSetWay;

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public FishMonster(Image imageLeft, Image imageRight, Image imageBottom, Image imageTop, KickPlayer kickMethod, ViewManager manager, ICollisionAvoidWay collisionAvoidWay, IPositionByTarget positionByTarget) {
        super(imageLeft, kickMethod, manager, collisionAvoidWay, positionByTarget);
        setLives(2);
        setHitSound(new SoundsPlayer(hitSoundPath));
        setDeathSound(new SoundsPlayer(deathSoundPath));
        setHowBigKickSize(howBigKickSize);
        setHowManyLivesTake(howManyLivesTake);
        setVelocity(velocityX, velocityY);

        this.imageLeft = imageLeft;
        this.imageRight = imageRight;
        this.imageBottom = imageBottom;
        this.imageTop = imageTop;
        this.imageSetWay = new PropperImageSet();
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @Override
    public void update(Sprite player, ArrayList<Monster> monsters) {
        super.update(player, monsters);
        setImageByPosition(imageLeft, imageRight, imageBottom, imageTop, player);
    }

    @Override
    public void isDeadAction() {
        defaultIsDeadAction(DEFAULT_DEATH_VOLUME);
    }

    @Override
    public void isHitAction() {
        defaultIsHitAction(DEFAULT_HIT_VOLUME);
    }

    @Override
    public void missHitAction() {
        //nothing
    }

    private void setImageByPosition(Image imageLeft, Image imageRight, Image imageBottom, Image imageTop, Sprite target) {
        imageSetWay.byTargetPosition(imageLeft, imageRight, imageBottom, imageTop, this, target);
    }
}
