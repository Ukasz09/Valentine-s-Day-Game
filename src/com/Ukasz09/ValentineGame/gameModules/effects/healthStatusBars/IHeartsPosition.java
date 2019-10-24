package com.Ukasz09.ValentineGame.gameModules.effects.healthStatusBars;

import com.Ukasz09.ValentineGame.gameModules.sprites.creatures.Creature;
import javafx.geometry.Point2D;

public interface IHeartsPosition {

    Point2D calculateHeartCoords(Creature creature);
}
