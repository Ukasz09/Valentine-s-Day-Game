package com.Ukasz09.ValentineGame.gameModules.levels;

import com.Ukasz09.ValentineGame.gameModules.sprites.creatures.Player;
import com.Ukasz09.ValentineGame.gameModules.sprites.effects.collisionAvoidEffect.NormalCollisionAvoid;
import com.Ukasz09.ValentineGame.gameModules.sprites.weapons.ShotSprite;
import com.Ukasz09.ValentineGame.gameModules.utilitis.ViewManager;
import com.Ukasz09.ValentineGame.gameModules.sprites.creatures.LittleMonster;
import com.Ukasz09.ValentineGame.gameModules.sprites.items.Coin;
import com.Ukasz09.ValentineGame.gameModules.sprites.creatures.Monster;
import com.Ukasz09.ValentineGame.gameModules.sprites.effects.kickEffect.KickByLittleMonster;
import com.Ukasz09.ValentineGame.gameModules.sprites.effects.kickEffect.TeleportKick;
import com.Ukasz09.ValentineGame.graphicModule.texturesPath.BackgroundPath;
import com.Ukasz09.ValentineGame.graphicModule.texturesPath.SpritesPath;
import com.Ukasz09.ValentineGame.soundsModule.soundsPath.SoundsPath;
import com.Ukasz09.ValentineGame.soundsModule.soundsPath.SoundsPlayer;

import javafx.scene.image.Image;

import java.util.ArrayList;

//ZROBIONE
public class Level_1 extends AllLevels {
    public static final String BACKGROOUND_IMAGE_PATH = BackgroundPath.BACKGROUD_IMAGE_PATH_L_0;
    public static final String BACKGROUND_SOUND_PATH = SoundsPath.BACKGROUND_SOUND_PATH_1;
    public static final double BACKGROUND_SOUND_VOLUME = 0.1;

    private final int amountOfSmallCoins = 5;
    private final int amountOfNormalCoins = 3;
    private final int amountOfBigCoins = 2;
    private final int smallCoinValue = 25;
    private final int normalCoinValue = 50;
    private final int bigCoinValue = 100;

    private final int amountOfMonsters = 4;

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public Level_1(ViewManager manager) {
        super(manager);
        setAmountOfCoins(amountOfSmallCoins, amountOfNormalCoins, amountOfBigCoins);
        setCoinsValue(smallCoinValue, normalCoinValue, bigCoinValue);
        setAmountOfMonsters(amountOfMonsters, 0);
        setBackgroundImage(getBackgroundImage());
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @Override
    public void prepareLevel(ArrayList<Coin> coinsList, ArrayList<Monster> enemiesList, Player player) {
        super.prepareLevel(coinsList, enemiesList, player);
        playBackgroundSound(getBackgroundSound());
    }

    @Override
    public void spawnEnemies(ArrayList<Monster> enemiesList) {
        Image littleMonsterImage = new Image(SpritesPath.LITTLE_MONSTER_1_PATH);
        LittleMonster littleMonster = new LittleMonster(littleMonsterImage, new KickByLittleMonster(new TeleportKick()), getManager(), new NormalCollisionAvoid());
        for (int i = 0; i < getAmountOfAllEnemies(); i++) {
            littleMonster.setPosition();
            enemiesList.add(littleMonster);
        }
    }

    @Override
    public void setPlayerStartPosition(Player player) {
        double posX = getDefaultPlayerPosition().getX();
        double posY = getDefaultPlayerPosition().getY();
        player.setPosition(posX, posY);
    }

    @Override
    public void renderLevel(ArrayList<Monster> monsters, ArrayList<Coin> coins, ArrayList<ShotSprite> shots, Player player) {
        defaultLevelRender(monsters, coins, shots, player.getTotalScore());
    }

    public SoundsPlayer getBackgroundSound() {
        return new SoundsPlayer(BACKGROUND_SOUND_PATH, BACKGROUND_SOUND_VOLUME, true);
    }

    public Image getBackgroundImage() {
        return new Image(BACKGROOUND_IMAGE_PATH);
    }

}
