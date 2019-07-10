package com.Ukasz09.ValentineGame.gameModules.levels;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public abstract class Panels {
    public abstract void makeLevel();

    public abstract void endLevel();

    public abstract void renderLevel(GraphicsContext gc);

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    protected void drawBackground(GraphicsContext gc, Image background) {
        gc.drawImage(background, 0, 0);
    }
}
