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

    private final int amountOfMonsters = 0;
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
    //TODO: zrobione
    @Override
    public void prepareLevel(ArrayList<Coin> coinsList, ArrayList<Monster> enemiesList, Player player) {
        super.prepareLevel(coinsList, enemiesList, player);
    }

    // TODO: 03.09.2019: zrobione
    @Override
    public void spawnEnemies(ArrayList<Monster> enemiesList) {
        spawnMonsters(enemiesList);
        spawnBosses(enemiesList);
    }

    //TODO: zrobione
    private void spawnMonsters(ArrayList<Monster> enemiesList) {
        Image fishMonsterImage = new Image(SpritesPath.FISH_MONSTER_PATH);
        FishMonster fishMonster = new FishMonster(fishMonsterImage, new KickByLittleMonster(new TeleportKick()), getManager(), new NormalCollisionAvoid());
        for (int i = 0; i < amountOfMonsters; i++) {
            fishMonster.setStartedPosition();
            enemiesList.add(fishMonster);
        }
    }

    //TODO: zrobione
    private void spawnBosses(ArrayList<Monster> enemiesList) {
        Image fishMonsterBossImage = new Image(SpritesPath.FISH_MONSTER_BOSS_PATH);
        Image shieldImage = new Image(SpritesPath.FISH_MONSTER_BOSS_SHIELD_PATH);
        FishMonsterBoss fishMonsterBoss = new FishMonsterBoss(fishMonsterBossImage, shieldImage, new KickByBigMonster(new TeleportKick()), getManager(), new NormalCollisionAvoid());
        for (int i = 0; i < amountOfBosses; i++) {
            fishMonsterBoss.setStartedPosition();
            enemiesList.add(fishMonsterBoss);
        }
    }

    //todo: zrobione
    @Override
    public void setPlayerStartPosition(Player player) {
        double posX = getDefaultPlayerPosition().getX();
        double posY = getDefaultPlayerPosition().getY();
        player.setPosition(posX, posY);
    }

    // TODO: 03.09.2019 :zrobione
    @Override
    public void update(Player player, ArrayList<Monster> enemiesList, double elapsedTime) {
        super.update(player, enemiesList, elapsedTime);
        updateBosses(player, enemiesList);

    }

    private void updateBosses(Player player, ArrayList<Monster> enemiesList) {
        if (bossesSpawnIsNeed(player.getCollectedCoinsOnLevel(), enemiesList.isEmpty()))
            spawnBosses(enemiesList);
    }

    // TODO: 03.09.2019 : zrobione
    @Override
    public void render(ArrayList<Monster> enemiesList, ArrayList<Coin> coinsList, ArrayList<ShotSprite> shotsList, Player player) {
        defaultLevelRender(enemiesList, coinsList, shotsList, player.getTotalScore());
    }

    //todo: zrobione
    /**
     * @param amountOfCollectedCoins - (amount of coins in pcs.)
     */
    private boolean bossesSpawnIsNeed(int amountOfCollectedCoins, boolean playerKillAllMonsters) {
        boolean playerCollectAllCoins = amountOfCollectedCoins == getAmountOfAllCoins() ? true : false;
        if (playerCollectAllCoins && playerKillAllMonsters)
            return true;
        return false;
    }


    //todo: zrobione
    public Image getBackgroundImage() {
        return new Image(BACKGROOUND_IMAGE_PATH);
    }
}
