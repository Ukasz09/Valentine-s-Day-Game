package com.Ukasz09.ValentineGame.gameModules.levels;

import com.Ukasz09.ValentineGame.gameModules.utilitis.ViewManager;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public abstract class Panels {
    private ViewManager manager;

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public Panels(ViewManager manager) {
        this.manager = manager;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public abstract void makePanel();

    public abstract void endPanel();

    public abstract void renderPanel();

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    protected void drawBackground(GraphicsContext gc, Image background) {
        gc.drawImage(background, 0, 0);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public ViewManager getManager() {
        return manager;
    }
}
