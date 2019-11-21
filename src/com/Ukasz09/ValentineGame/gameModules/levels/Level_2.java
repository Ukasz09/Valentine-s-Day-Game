package com.Ukasz09.ValentineGame.gameModules.levels;

import com.Ukasz09.ValentineGame.gameModules.sprites.creatures.*;
import com.Ukasz09.ValentineGame.gameModules.effects.collisionAvoidEffect.*;
import com.Ukasz09.ValentineGame.gameModules.effects.kickEffect.KickByLittleMonster;
import com.Ukasz09.ValentineGame.gameModules.sprites.items.weapons.Weapon;
import com.Ukasz09.ValentineGame.gameModules.utilitis.ViewManager;
import com.Ukasz09.ValentineGame.gameModules.sprites.items.others.Coin;
import com.Ukasz09.ValentineGame.gameModules.effects.kickEffect.KickByBigMonster;
import com.Ukasz09.ValentineGame.gameModules.effects.kickEffect.TeleportKick;
import com.Ukasz09.ValentineGame.graphicModule.texturesPath.*;
import com.Ukasz09.ValentineGame.soundsModule.soundsPath.SoundsPlayer;
import javafx.scene.image.Image;

import java.util.ArrayList;

public class Level_2 extends AllLevels {
    public static final String BACKGROOUND_IMAGE_PATH = BackgroundPath.BACKGROUD_IMAGE_PATH_L_1;

    private final int amountOfSmallCoins = 3;
    private final int amountOfNormalCoins = 5;
    private final int amountOfBigCoins = 2;

    private final int amountOfMonsters = 6;
    private final int amountOfBosses = 1;

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public Level_2(ViewManager manager) {
        super(manager, new Image(BACKGROOUND_IMAGE_PATH));
        setAmountOfCoins(amountOfSmallCoins, amountOfNormalCoins, amountOfBigCoins);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @Override
    public void spawnEnemies(ArrayList<Monster> enemiesList) {
        spawnMonsters(enemiesList);
    }

    private void spawnMonsters(ArrayList<Monster> enemiesList) {
        CreatureSheetProperty sheetProperty = SpritesProperties.fishMonsterSheetProperty();
//        Image fishMonsterImage = new Image(SpritesPath.FISH_MONSTER_SHEET_PATH);
        for (int i = 0; i < amountOfMonsters; i++)
            enemiesList.add(new FishMonster(sheetProperty, new KickByLittleMonster(new TeleportKick()), getManager(), new NormalCollisionAvoid()));
    }

    private void spawnBosses(ArrayList<Monster> enemiesList) {
        CreatureSheetProperty sheetProperty = SpritesProperties.fishMonsterSheetProperty();
//        Image fishMonsterBossImage = new Image(SpritesPath.FISH_MONSTER_BOSS_PATH);
        ImageSheetProperty shieldProperty = SpritesProperties.fishBossShieldSheetProperty();
        for (int i = 0; i < amountOfBosses; i++)
            enemiesList.add(new FishMonsterBoss(sheetProperty, shieldProperty, new KickByBigMonster(new TeleportKick()), getManager(), new NormalCollisionAvoid()));

    }

    @Override
    public void setPlayerStartPosition(Player player) {
        double posX = getDefaultPlayerPosition().getX();
        double posY = getDefaultPlayerPosition().getY();
        player.setPosition(posX, posY);
    }

    @Override
    public void update(Player player, ArrayList<Monster> enemiesList, ArrayList<Coin> coinsList, double elapsedTime) {
        super.update(player, enemiesList, coinsList, elapsedTime);
        spawnBossIfNeed(player, enemiesList);
    }

    private void spawnBossIfNeed(Player player, ArrayList<Monster> enemiesList) {
        if (bossesSpawnIsNeed(player.getCollectedCoinsOnLevel(), enemiesList.isEmpty()))
            spawnBosses(enemiesList);
    }

    @Override
    public void render(ArrayList<Monster> enemiesList, ArrayList<Coin> coinsList, ArrayList<Weapon> shotsList, Player player) {
        defaultLevelRender(enemiesList, coinsList, shotsList, player.getTotalScore());
    }

    /**
     * @param amountOfCollectedCoins - (amount of coins in pcs.)
     */
    private boolean bossesSpawnIsNeed(int amountOfCollectedCoins, boolean playerKillAllMonsters) {
        boolean playerCollectAllCoins = amountOfCollectedCoins == getAmountOfAllCoins() ? true : false;
        if (playerCollectAllCoins && playerKillAllMonsters)
            return true;
        return false;
    }

    @Override
    public SoundsPlayer getBackgroundSound() {
        return new SoundsPlayer(Level_1.BACKGROUND_SOUND_PATH, Level_1.BACKGROUND_SOUND_VOLUME, true);
    }

    @Override
    public int amountOfSpawnEnemies() {
        return amountOfBosses + amountOfMonsters;
    }
}
