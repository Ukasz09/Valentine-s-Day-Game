package com.Ukasz09.ValentineGame.gameModules.sprites.effects.healthStatusBars;

import com.Ukasz09.ValentineGame.gameModules.utilitis.ViewManager;
import com.Ukasz09.ValentineGame.gameModules.sprites.creatures.Sprite;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;

public class InCorner extends HeartsRender implements IHeartsPosition {

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public InCorner(ViewManager manager){
        super(manager);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @Override
    public Point2D calculateHeartCoords(Sprite sprite) {
        double heartWidth=getHeartFullImage().getWidth();
        double heartHeight=getHeartFullImage().getHeight();

        double positionX = getManager().getRightBorder() - sprite.getMaxLives() * heartWidth;
        double positionY = getManager().getBottomBorder() - heartHeight;
        return new Point2D(positionX,positionY);
    }

    @Override
    public void renderHearts(Sprite sprite) {
        super.renderHearts(calculateHeartCoords(sprite), sprite.getMaxLives(), sprite.getLives());
    }

    @Override
    public void renderHearts(Sprite sprite, Image heartFull, Image heartHalf, Image heartEmpty) {
        super.renderHearts(calculateHeartCoords(sprite), sprite.getMaxLives(), sprite.getLives(), heartFull, heartHalf, heartEmpty);
    }
}
