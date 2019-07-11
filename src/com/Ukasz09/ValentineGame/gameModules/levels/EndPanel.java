package com.Ukasz09.ValentineGame.gameModules.levels;

import com.Ukasz09.ValentineGame.gameModules.utilitis.ViewManager;
import com.Ukasz09.ValentineGame.graphicModule.texturesPath.BackgroundImages;
import com.Ukasz09.ValentineGame.graphicModule.texturesPath.SpritesImages;
import com.Ukasz09.ValentineGame.soundsModule.soundsPath.Sounds;
import com.Ukasz09.ValentineGame.soundsModule.soundsPath.SoundsPlayer;
import javafx.scene.canvas.GraphicsContext;

public class EndPanel extends Panels {

    private static final SoundsPlayer BACKGROUND_SOUND = Sounds.backgroundEndSound;
    private static final double SOUND_VOLUME = 0.3;

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public EndPanel(ViewManager manager) {
        super(manager);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @Override
    public void makeLevel() {
        Levels.stopBackgroundSound();
        Levels.stopWingsSound();
        BACKGROUND_SOUND.playSound(SOUND_VOLUME, false);
    }

    @Override
    public void endLevel() {
        //nothing to do
    }

    @Override
    public void renderLevel() {
        drawElements(getManager().getGraphicContext());
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public void drawElements(GraphicsContext gc) {
        gc.drawImage(BackgroundImages.endImage, 0, 0);
        gc.drawImage(SpritesImages.heartFlareImage, 10, 0);
        gc.drawImage(SpritesImages.kasiaWingsImage, 440, 500);
        gc.drawImage(SpritesImages.kasiaImage, 600, 230);
    }
}
