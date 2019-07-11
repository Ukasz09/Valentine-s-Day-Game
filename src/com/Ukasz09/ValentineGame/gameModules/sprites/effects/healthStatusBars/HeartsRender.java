package com.Ukasz09.ValentineGame.gameModules.sprites.effects.healthStatusBars;

import com.Ukasz09.ValentineGame.gameModules.utilitis.ViewManager;
import com.Ukasz09.ValentineGame.gameModules.sprites.creatures.Sprite;
import com.Ukasz09.ValentineGame.graphicModule.texturesPath.SpritesImages;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;

public abstract class HeartsRender {
    private static final Image DEFAULT_FULL_HEART = SpritesImages.heartFullImage;
    private static final Image DEFAULT_HALF_HEART = SpritesImages.heartHalfImage;
    private static final Image DEFAULT_EMPTY_HEART = SpritesImages.heartEmptyImage;

    private Image heartFullImage;
    private Image heartHalfImage;
    private Image heartEmptyImage;

    private ViewManager manager;

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public HeartsRender(ViewManager manager) {
        this.manager = manager;
        heartFullImage = DEFAULT_FULL_HEART;
        heartHalfImage = DEFAULT_HALF_HEART;
        heartEmptyImage = DEFAULT_EMPTY_HEART;
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
    public abstract void renderHearts(Sprite sprite);

    public abstract void renderHearts(Sprite sprite, Image heartFull, Image heartHalf, Image heartEmpty);

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public ViewManager getManager() {
        return manager;
    }

    public Image getHeartFullImage() {
        return heartFullImage;
    }

    public Image getHeartHalfImage() {
        return heartHalfImage;
    }

    public Image getHeartEmptyImage() {
        return heartEmptyImage;
    }
}
