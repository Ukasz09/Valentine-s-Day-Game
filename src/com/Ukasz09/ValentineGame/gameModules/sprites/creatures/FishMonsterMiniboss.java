package com.Ukasz09.ValentineGame.gameModules.sprites.creatures;

import com.Ukasz09.ValentineGame.gameModules.sprites.others.kickEffect.KickPlayer;
import com.Ukasz09.ValentineGame.gameModules.sprites.others.shields.ShieldKindOfRender;
import com.Ukasz09.ValentineGame.soundsModule.SoundsPath;
import com.Ukasz09.ValentineGame.soundsModule.SoundsPlayer;
import com.Ukasz09.ValentineGame.gameModules.sprites.others.shields.AutoActivateShield;
import com.Ukasz09.ValentineGame.gameModules.sprites.others.HealthStatusBar;
import com.Ukasz09.ValentineGame.gameModules.sprites.others.shields.Shield;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.util.ArrayList;

public class FishMonsterMiniboss extends Monster implements ShieldKindOfRender {

    private final String hitSoundPath = SoundsPath.FISH_MINIBOSS_HIT_SOUND_PATH;
    private final String deathSoundPath = SoundsPath.FISH_MINIBOSS_DEATH_SOUND_PATH;
    private final String missSoundPath = SoundsPath.FISH_MONSTER_MISS_SHOT_SOUND_PATH;

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

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public FishMonsterMiniboss(Image imageLeft, Image imageRight, Image imageBottom, Image imageTop, Image shieldImage, KickPlayer kickMethod) {

        super(imageLeft, kickMethod);
        setLives(maxLive);
        //shieldTimer=0;
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
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public void updateShield() {
        shield.updateShield();
    }

    public void activateShield() {
        shield.activateShield();
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //korekta wyswietlania obrazkow dla minibossa
    @Override
    public void setImageByPosition(Image left, Image right, Image bottom, Image top, Sprite ukasz) {

        double monsterMinX = this.getBoundary().getMinX();
        double monsterMaxX = this.getBoundary().getMaxX();
        double monsterMaxY = this.getBoundary().getMaxY();

        if (monsterMinX + 0.1 * monsterMinX > ukasz.getBoundary().getMaxX())
            this.setActualImage(left);
        else this.setActualImage(right);

        //jesli potwor doklanie nad/pod graczem (korekta bo potwor wiekszy niz gracz)
        if ((monsterMinX + 0.25 * monsterMinX > ukasz.getBoundary().getMinX()) && (monsterMaxX - 0.25 * monsterMaxX < ukasz.getBoundary().getMaxX())) {

            //ustawiona w dol
            if (monsterMaxY - 0.15 * monsterMaxY < ukasz.getBoundary().getMinY())
                this.setActualImage(bottom);
            else this.setActualImage(top);

        }

    }

    @Override
    public void update(Sprite ukasz, ArrayList<Monster> monsters) {
        super.update(ukasz, monsters);
        setImageByPosition(imageLeft, imageRight, imageBottom, imageTop, ukasz);
        healthBar.update(getLives(), getPositionX(), getPositionY());
        updateShield();
    }

    @Override
    public void render(GraphicsContext gc) {
        super.render(gc);
        healthBar.draw(gc);
        renderShield(gc);
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

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public Image getImageLeft() {
        return imageLeft;
    }

    public Shield getShield() {
        return shield;
    }
}
