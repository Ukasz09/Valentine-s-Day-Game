package com.Ukasz09.ValentineGame.gameModules.levels;

import com.Ukasz09.ValentineGame.gameModules.sprites.creatures.*;
import com.Ukasz09.ValentineGame.gameModules.sprites.effects.collisionAvoidEffect.*;
import com.Ukasz09.ValentineGame.gameModules.sprites.effects.kickEffect.KickByLittleMonster;
import com.Ukasz09.ValentineGame.gameModules.sprites.weapons.ShotSprite;
import com.Ukasz09.ValentineGame.gameModules.utilitis.ViewManager;
import com.Ukasz09.ValentineGame.gameModules.sprites.items.Coin;
import com.Ukasz09.ValentineGame.gameModules.sprites.effects.kickEffect.KickByBigMonster;
import com.Ukasz09.ValentineGame.gameModules.sprites.effects.kickEffect.TeleportKick;
import com.Ukasz09.ValentineGame.graphicModule.texturesPath.BackgroundPath;
import com.Ukasz09.ValentineGame.graphicModule.texturesPath.SpritesPath;
import com.Ukasz09.ValentineGame.soundsModule.soundsPath.SoundsPlayer;
import javafx.scene.image.Image;

import java.util.ArrayList;

public class Level_2 extends AllLevels {
    public static final String BACKGROOUND_IMAGE_PATH = BackgroundPath.BACKGROUD_IMAGE_PATH_L_1;

    private final int amountOfSmallCoins = 3;
    private final int amountOfNormalCoins = 5;
    private final int amountOfBigCoins = 2;
    private final int smallCoinValue = 25;
    private final int normalCoinValue = 50;
    private final int bigCoinValue = 100;

    private final int amountOfMonsters = 4;
    private final int amountOfBosses = 1;

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public Level_2(ViewManager manager) {
        super(manager);
        setAmountOfCoins(amountOfSmallCoins, amountOfNormalCoins, amountOfBigCoins);
        setCoinsValue(smallCoinValue, normalCoinValue, bigCoinValue);
        setAmountOfMonsters(amountOfMonsters, amountOfBosses);
        setBackgroundImage(getBackgroundImage());
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @Override
    public void spawnEnemies(ArrayList<Monster> enemiesList) {
        spawnMonsters(enemiesList);
    }

    private void spawnMonsters(ArrayList<Monster> enemiesList) {
        Image fishMonsterImage = new Image(SpritesPath.FISH_MONSTER_PATH);
        FishMonster fishMonster;
        for (int i = 0; i < amountOfMonsters; i++) {
            fishMonster = new FishMonster(fishMonsterImage, new KickByLittleMonster(new TeleportKick()), getManager(), new NormalCollisionAvoid());
            fishMonster.setStartedPosition();
            enemiesList.add(fishMonster);
        }
    }

    private void spawnBosses(ArrayList<Monster> enemiesList) {
        Image fishMonsterBossImage = new Image(SpritesPath.FISH_MONSTER_BOSS_PATH);
        Image shieldImage = new Image(SpritesPath.FISH_MONSTER_BOSS_SHIELD_PATH);
        FishMonsterBoss fishMonsterBoss;
        for (int i = 0; i < amountOfBosses; i++) {
            fishMonsterBoss = new FishMonsterBoss(fishMonsterBossImage, shieldImage, new KickByBigMonster(new TeleportKick()), getManager(), new NormalCollisionAvoid());
            fishMonsterBoss.setStartedPosition();
            enemiesList.add(fishMonsterBoss);
        }
    }

    @Override
    public void setPlayerStartPosition(Player player) {
        double posX = getDefaultPlayerPosition().getX();
        double posY = getDefaultPlayerPosition().getY();
        player.setPosition(posX, posY);
    }

    @Override
    public void update(Player player, ArrayList<Monster> enemiesList, double elapsedTime) {
        super.update(player, enemiesList, elapsedTime);
        spawnBossIfNeed(player, enemiesList);
    }

    private void spawnBossIfNeed(Player player, ArrayList<Monster> enemiesList) {
        if (bossesSpawnIsNeed(player.getCollectedCoinsOnLevel(), enemiesList.isEmpty()))
            spawnBosses(enemiesList);
    }

    @Override
    public void render(ArrayList<Monster> enemiesList, ArrayList<Coin> coinsList, ArrayList<ShotSprite> shotsList, Player player) {
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


    public Image getBackgroundImage() {
        return new Image(BACKGROOUND_IMAGE_PATH);
    }

    @Override
    public SoundsPlayer getBackgroundSound() {
        return new SoundsPlayer(Level_1.BACKGROUND_SOUND_PATH, Level_1.BACKGROUND_SOUND_VOLUME, true);
    }
}
