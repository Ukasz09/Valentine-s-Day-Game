package com.Ukasz09.ValentineGame.gameModules.sprites.creatures;

import com.Ukasz09.ValentineGame.gameModules.effects.collisionAvoidEffect.ICollisionAvoidWay;
import com.Ukasz09.ValentineGame.gameModules.effects.rotateEffect.RotateEffect;
import com.Ukasz09.ValentineGame.gameModules.utilitis.ViewManager;
import com.Ukasz09.ValentineGame.gameModules.effects.kickEffect.KickPlayer;
import com.Ukasz09.ValentineGame.soundsModule.soundsPath.SoundsPath;
import com.Ukasz09.ValentineGame.soundsModule.soundsPath.SoundsPlayer;
import javafx.scene.image.Image;

import java.util.ArrayList;

public class LittleMonster extends Monster {

    private enum KindOfMovement {
        FLY(0, 882, 0, 450, 60);

        private double minX;
        private double maxX;
        private double minY;
        private double maxY;
        private int amountOfFrame;

        KindOfMovement(double minX, double maxX, double minY, double maxY, int amountOfFrame) {
            this.minX = minX;
            this.maxX = maxX;
            this.minY = minY;
            this.maxY = maxY;
            this.amountOfFrame = amountOfFrame;
        }
    }

    private KindOfMovement actualKindOfMovement;

    private static final String HIT_SOUND_PATH = SoundsPath.LITTLE_MONSTER_HIT_SOUND_PATH;
    private static final String DEATH_SOUND_PATH = SoundsPath.LITTLE_MONSTER_DEATH_SOUND_PATH;

    private static final double DEATH_SOUND_VOLUME = 1;
    private static final double HIT_SOUND_VOLUME = 1;

    private static final SoundsPlayer HIT_SOUND = new SoundsPlayer(HIT_SOUND_PATH, HIT_SOUND_VOLUME, false);
    private static final SoundsPlayer DEATH_SOUND = new SoundsPlayer(DEATH_SOUND_PATH, DEATH_SOUND_VOLUME, false);

    private static final double SPRITE_WIDTH = 98;
    private static final double SPRITE_HEIGHT = 90;
    private static final double DURATION_PER_FRAME = 1.5;

    private static final double WIDTH_OF_ONE_FRAME = 98;
    private static final double HEIGHT_OF_ONE_FRAME = 90;

    private final double defaultLives = 3;
    private final double defaultLivesTake = 0.5;
    private final double defaultKickSize = 0;
    private final double defaultVelocityX = 1;
    private final double defaultVelocityY = 1;
    private final double rotateOffset = Math.random() * 360;


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public LittleMonster(Image spriteSheet, KickPlayer kickMethod, ViewManager manager, ICollisionAvoidWay collisionAvoidWay) {
        super(spriteSheet, SPRITE_WIDTH, SPRITE_HEIGHT, WIDTH_OF_ONE_FRAME, HEIGHT_OF_ONE_FRAME, DURATION_PER_FRAME, kickMethod, manager, collisionAvoidWay);
        setDefaultProperties();
        actualKindOfMovement = KindOfMovement.FLY;
//        ImageView iv=new ImageView(image);
//        ColorAdjust monochrome = new ColorAdjust();
////        monochrome.setSaturation(-1.0);
//        Random random=new Random();
//        boolean negative=random.nextInt(1)==0;
//        double neagativeMul=1;
//        if (negative)
//            neagativeMul=-1;
//        double hue=(double)(random.nextInt(100))/100*neagativeMul;
//
//        monochrome.setHue(hue);
//        iv.setEffect(monochrome);
//        SnapshotParameters params = new SnapshotParameters();
//        params.setFill(Color.TRANSPARENT);
//        Image rotatedImage = iv.snapshot(params, null);
//
//        setActualImage(rotatedImage);
    }

//    public LittleMonster(Image image, KickPlayer kickMethod, ViewManager manager, ICollisionAvoidWay collisionAvoidWay, double livesTake, double lives, double kickSize, double velocityX, double velocityY){
//        super(image,kickMethod,manager,collisionAvoidWay);
//        setProperties(kickSize, livesTake,lives, velocityX, velocityY);
//    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @Override
    protected void setDefaultProperties() {
        setProperties(defaultKickSize, defaultLivesTake, defaultLives, defaultVelocityX, defaultVelocityY);
    }

    @Override
    public void setStartedPosition() {
        setPositionByDirection(true, false, true, true, getWidth());
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @Override
    public void actionWhenMissHit() {
        //nothing
    }

    @Override
    public void updateMonsterRotate(Creature target) {
        double rotate = RotateEffect.setRotateByAngle(this, target);
        rotate += rotateOffset;
        setActualRotation(rotate);
    }


    @Override
    public SoundsPlayer getHitSoundOrNull() {
        return LittleMonster.HIT_SOUND;
    }

    @Override
    public SoundsPlayer getMissSoundOrNull() {
        return null;
    }

    @Override
    public SoundsPlayer getDeathSoundOrNull() {
        return LittleMonster.DEATH_SOUND;
    }

    @Override
    public boolean hasActiveShield() {
        return false;
    }

    @Override
    public void render() {
        drawBoundaryForTests();
        getManager().getGraphicContext().drawImage(spriteSheet, actualFramePositionX, actualFramePositionY, widthOfOneFrame, heightOfOneFrame, getPositionX(), getPositionY(), width, height);
    }

    @Override
    public void update(double elapsedTime, Creature target, ArrayList<Monster> enemiesList) {
        super.update(elapsedTime, target, enemiesList);
        setPositionOfNextFrame(actualKindOfMovement.minX, actualKindOfMovement.maxX, actualKindOfMovement.minY, actualKindOfMovement.maxY);
    }
}
