package com.Ukasz09.ValentineGame.gameModules.effects.healthStatusBars;

import com.Ukasz09.ValentineGame.gameModules.utilitis.ViewManager;
import com.Ukasz09.ValentineGame.gameModules.sprites.creatures.Creature;
import com.Ukasz09.ValentineGame.graphicModule.texturesPath.SpritesProperties;
import com.sun.javaws.security.AppContextUtil;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;

public abstract class HeartsRender {
    private static final Image DEFAULT_FULL_HEART = SpritesProperties.heartFullImage;
    private static final Image DEFAULT_HALF_HEART = SpritesProperties.heartHalfImage;
    private static final Image DEFAULT_EMPTY_HEART = SpritesProperties.heartEmptyImage;

    private Image heartFullImage = DEFAULT_FULL_HEART;
    private Image heartHalfImage = DEFAULT_HALF_HEART;
    private Image heartEmptyImage = DEFAULT_EMPTY_HEART;
    private ViewManager manager;

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public HeartsRender(ViewManager manager) {
        this.manager = manager;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    protected void renderHearts(Point2D cords, double spriteMaxLives, double spriteActualLives) {
        renderHearts(cords, spriteMaxLives, spriteActualLives, heartFullImage, heartHalfImage, heartEmptyImage);
    }

    protected void renderHearts(Point2D cords, double spriteMaxLives, double heartToDraw, Image heartFull, Image heartHalf, Image heartEmpty) {
        this.heartFullImage = heartFull;
        this.heartHalfImage = heartHalf;
        this.heartEmptyImage = heartEmpty;
        double cordsX = cords.getX();
        double cordsY = cords.getY();

        for (int i = 0; i < spriteMaxLives; i++) {
            if (heartToDraw == 0.5) {
                manager.getGraphicContext().drawImage(heartHalf, cordsX, cordsY);
                heartToDraw = 0;
            } else if (heartToDraw > 0) {
                manager.getGraphicContext().drawImage(heartFull, cordsX, cordsY);
                heartToDraw--;
            } else
                manager.getGraphicContext().drawImage(heartEmpty, cordsX, cordsY);

            cordsX += heartFull.getWidth();
        }
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public abstract void renderHearts(Creature creature);

    public abstract void renderHearts(Creature creature, Image heartFull, Image heartHalf, Image heartEmpty);

    public ViewManager getManager() {
        return manager;
    }

    protected double getOneHeartWidth(){
        return heartFullImage.getWidth();
    }

    protected double getOneHeartHeight(){
        return heartFullImage.getHeight();
    }
}
