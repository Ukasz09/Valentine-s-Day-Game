package com.Ukasz09.ValentineGame.gameModules.levels;

import com.Ukasz09.ValentineGame.gameModules.gameUtils.ViewManager;
import com.Ukasz09.ValentineGame.graphicModule.texturesPath.BackgroundImages;
import com.Ukasz09.ValentineGame.soundsModule.Sounds;
import com.Ukasz09.ValentineGame.soundsModule.SoundsPlayer;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class StartPanel extends Panels {
    private static final Image BACKGROUND_IMAGE = BackgroundImages.startImage;
    private static final SoundsPlayer BACKGROUND_SOUND = Sounds.backgroundStartSound;
    private static final double SOUND_VOLUME = 0.6;

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public StartPanel(ViewManager manager) {
        super(manager);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @Override
    public void makeLevel() {
        BACKGROUND_SOUND.playSound(SOUND_VOLUME, true);
    }

    @Override
    public void endLevel() {
        BACKGROUND_SOUND.stopSound();
    }

    @Override
    public void renderLevel() {
        drawBackground(getManager().getGraphicContext(), BACKGROUND_IMAGE);
    }
}
