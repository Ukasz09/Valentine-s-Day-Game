package com.Ukasz09.ValentineGame.gameModules.levels;

import com.Ukasz09.ValentineGame.gameModules.utilitis.ViewManager;
import com.Ukasz09.ValentineGame.graphicModule.texturesPath.BackgroundPath;
import com.Ukasz09.ValentineGame.graphicModule.texturesPath.SpritesPath;
import com.Ukasz09.ValentineGame.soundsModule.soundsPath.SoundsPath;
import com.Ukasz09.ValentineGame.soundsModule.soundsPath.SoundsPlayer;
import javafx.scene.image.Image;

public class EndWinPanel extends Panels {
    private static final String BACKGROUND_IMAGE_PATH = BackgroundPath.END_WIN_IMAGE_PATH;
    private static final String BACKGROUND_SOUND_PATH = SoundsPath.END_WIN_SOUND_PATH;
    private static final double BACKGROUND_SOUND_VOLUME = 0.3;

    private Image heartFlare;
    private SoundsPlayer soundsPlayer;

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public EndWinPanel(ViewManager manager) {
        super(manager);
        setBackgroundImage(getBackgroundImage());
        heartFlare=new Image(SpritesPath.HEART_FLARE_PATH);
        soundsPlayer=new SoundsPlayer(BACKGROUND_SOUND_PATH,BACKGROUND_SOUND_VOLUME,false);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @Override
    public void render() {
       super.render();
       renderFlare();

    }

    private void renderFlare() {
        getManager().getGraphicContext().drawImage(heartFlare, 10, 0);
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
;