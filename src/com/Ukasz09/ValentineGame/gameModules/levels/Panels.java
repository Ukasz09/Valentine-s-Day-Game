package com.Ukasz09.ValentineGame.gameModules.levels;

import com.Ukasz09.ValentineGame.gameModules.utilitis.ViewManager;
import com.Ukasz09.ValentineGame.soundsModule.soundsPath.SoundsPlayer;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public abstract class Panels {
    private ViewManager manager;
    private Image backgroundImage;

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public Panels(ViewManager manager) {
        this.manager = manager;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public void render(){
     drawBackground();
    }

    public void drawBackground() {
        manager.getGraphicContext().drawImage(backgroundImage, 0, 0);
    }

    abstract public SoundsPlayer getBackgroundSound();
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public ViewManager getManager() {
        return manager;
    }

    public void setBackgroundImage(Image backgroundImage) {
        this.backgroundImage = backgroundImage;
    }
}
