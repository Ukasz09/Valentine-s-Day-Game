package com.Ukasz09.ValentineGame.gameModules.sprites.others.healthStatusBars;

import com.Ukasz09.ValentineGame.gameModules.sprites.creatures.Sprite;
import javafx.geometry.Point2D;

public interface IHeartsPosition {

    Point2D calculateHeartCoords(Sprite sprite);
}
