package com.Ukasz09.ValentineGame.gameModules.levels;

import com.Ukasz09.ValentineGame.gameModules.utilitis.ViewManager;
import com.Ukasz09.ValentineGame.graphicModule.texturesPath.BackgroundPath;
import com.Ukasz09.ValentineGame.soundsModule.soundsPath.SoundsPath;
import com.Ukasz09.ValentineGame.soundsModule.soundsPath.SoundsPlayer;
import javafx.scene.image.Image;

public class StartPanel extends Panels {
    private static final String BACKGROUND_IMAGE_PATH = BackgroundPath.START_IMAGE_PATH;
    private static final String BACKGROUND_SOUND_PATH = SoundsPath.START_SOUND_PATH;
    private static final double BACKGROUND_SOUND_VOLUME = 0.6;

    private SoundsPlayer soundsPlayer;

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public StartPanel(ViewManager manager) {
        super(manager);
        setBackgroundImage(getBackgroundImage());
        soundsPlayer = new SoundsPlayer(BACKGROUND_SOUND_PATH, BACKGROUND_SOUND_VOLUME, true);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private Image getBackgroundImage() {
        return new Image(BACKGROUND_IMAGE_PATH);
    }

    @Override
    public void playBackgroundSound() {
        soundsPlayer.playSound();
    }

    @Override
    public void stopBackgroundSound() {
        soundsPlayer.stopSound();
    }
}
