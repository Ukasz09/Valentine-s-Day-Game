package com.Ukasz09.ValentineGame.gameModules.levels;

import com.Ukasz09.ValentineGame.gameModules.utilitis.ViewManager;
import com.Ukasz09.ValentineGame.graphicModule.texturesPath.BackgroundImages;
import com.Ukasz09.ValentineGame.soundsModule.soundsPath.Sounds;
import com.Ukasz09.ValentineGame.soundsModule.soundsPath.SoundsPlayer;
import javafx.scene.image.Image;

public class StartPanel extends Panels {
    private Image BACKGROUND_IMAGE;
    private SoundsPlayer BACKGROUND_SOUND;
    private final double SOUND_VOLUME = 0.6;

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public StartPanel(ViewManager manager) {
        super(manager);
        BACKGROUND_IMAGE = BackgroundImages.startImage;
        BACKGROUND_SOUND = Sounds.backgroundStartSound;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @Override
    public void makePanel() {
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
