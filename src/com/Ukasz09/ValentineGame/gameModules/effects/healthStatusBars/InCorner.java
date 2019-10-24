package com.Ukasz09.ValentineGame.gameModules.effects.healthStatusBars;

import com.Ukasz09.ValentineGame.gameModules.utilitis.ViewManager;
import com.Ukasz09.ValentineGame.gameModules.sprites.creatures.Creature;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;

public class InCorner extends HeartsRender implements IHeartsPosition {

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public InCorner(ViewManager manager){
        super(manager);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @Override
    public Point2D calculateHeartCoords(Creature creature) {
        double heartWidth=getHeartFullImage().getWidth();
        double heartHeight=getHeartFullImage().getHeight();

        double positionX = getManager().getRightBorder() - creature.getMaxLives() * heartWidth;
        double positionY = getManager().getBottomBorder() - heartHeight;
        return new Point2D(positionX,positionY);
    }

    @Override
    public void renderHearts(Creature creature) {
        super.renderHearts(calculateHeartCoords(creature), creature.getMaxLives(), creature.getLives());
    }

    @Override
    public void renderHearts(Creature creature, Image heartFull, Image heartHalf, Image heartEmpty) {
        super.renderHearts(calculateHeartCoords(creature), creature.getMaxLives(), creature.getLives(), heartFull, heartHalf, heartEmpty);
    }
}
