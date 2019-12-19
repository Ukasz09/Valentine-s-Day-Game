package com.Ukasz09.ValentineGame.gameModules.levels;

import com.Ukasz09.ValentineGame.gameModules.utilitis.ViewManager;
import com.Ukasz09.ValentineGame.graphicModule.texturesPath.BackgroundPath;
import com.Ukasz09.ValentineGame.soundsModule.soundsPath.SoundsPath;
import com.Ukasz09.ValentineGame.soundsModule.soundsPath.SoundsPlayer;
import javafx.scene.image.Image;

public class EndGameOverPanel extends Panels {
    private static final String BACKGROUND_IMAGE_PATH = BackgroundPath.END_GAME_OVER_IMAGE_PATH;
    private static final String BACKGROUND_SOUND_PATH = SoundsPath.END_GAME_OVER_SOUND_PATH;
    private static final double BACKGROUND_SOUND_VOLUME = 0.3;

    private SoundsPlayer soundsPlayer;
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public EndGameOverPanel(ViewManager manager) {
        super(manager);
        setBackgroundImage(getBackgroundImage());
        soundsPlayer=new SoundsPlayer(BACKGROUND_SOUND_PATH, BACKGROUND_SOUND_VOLUME, true);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @Override
    public void render() {
        super.render();
    }

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