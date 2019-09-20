package com.Ukasz09.ValentineGame.gameModules.levels;

import com.Ukasz09.ValentineGame.gameModules.utilitis.ViewManager;
import com.Ukasz09.ValentineGame.graphicModule.texturesPath.BackgroundPath;
import com.Ukasz09.ValentineGame.graphicModule.texturesPath.SpritesPath;
import com.Ukasz09.ValentineGame.soundsModule.soundsPath.SoundsPath;
import com.Ukasz09.ValentineGame.soundsModule.soundsPath.SoundsPlayer;
import javafx.scene.image.Image;

public class EndPanel extends Panels {
    public static final String BACKGROUND_IMAGE_PATH = BackgroundPath.END_IMAGE_PATH;
    public static final String BACKGROUND_SOUND_PATH = SoundsPath.END_SOUND_PATH;
    private static final double BACKGROUND_SOUND_VOLUME = 0.3;
    private Image heartFlare;
    private Image princess;

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public EndPanel(ViewManager manager) {
        super(manager);
        setBackgroundImage(getBackgroundImage());
        heartFlare=new Image(SpritesPath.HEART_FLARE_PATH);
        princess=new Image(SpritesPath.PRINCESS_IMAGE_PATH);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @Override
    public void render() {
       super.render();
       renderPrincess();

    }

    public void renderPrincess() {
        getManager().getGraphicContext().drawImage(heartFlare, 10, 0);
        getManager().getGraphicContext().drawImage(princess, 0, 0);
    }

    private Image getBackgroundImage() {
        return new Image(BACKGROUND_IMAGE_PATH);
    }

    @Override
    public SoundsPlayer getBackgroundSound() {
        return new SoundsPlayer(BACKGROUND_SOUND_PATH, BACKGROUND_SOUND_VOLUME, false);
    }
}
