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
import javafx.geometry.Point2D;
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

    private final int amountOfMonsters = 2;
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

    @Override
    public void spawnEnemies(ArrayList<Monster> enemiesList) {
        spawnMonsters(enemiesList);
    }

    //TODO: zrobione
    private void spawnMonsters(ArrayList<Monster> enemiesList) {
        Image fishMonsterImage = new Image(SpritesPath.FISH_MONSTER_PATH);
        FishMonster fishMonster = new FishMonster(fishMonsterImage, new KickByLittleMonster(new TeleportKick()), getManager(), new NormalCollisionAvoid());
        for (int i = 0; i < amountOfMonsters; i++) {
            fishMonster.setPosition();
            enemiesList.add(fishMonster);
        }
    }

    public void spawnMiniboss(ArrayList<Monster> monsters) {
        Image fishMonsterBossImage = new Image(SpritesPath.FISH_MONSTER_BOSS_PATH);
        Image shieldImage = new Image(SpritesPath.FISH_MONSTER_BOSS_SHIELD_PATH);
        FishMonsterBoss fishMonsterBoss = new FishMonsterBoss(fishMonsterBossImage, shieldImage, new KickByBigMonster(new TeleportKick()), getManager(), new NormalCollisionAvoid());
        for (int i = 0; i < amountOfMonsters; i++) {
            fishMonsterBoss.setPosition();
            enemiesList.add(fishMonsterBoss);
        }


        int random = (int) (Math.random() * 2);

        //pokaz z lewej strony
        if (random == 0)
            miniBoss.setPosition(getManager().getLeftBorder() - miniBoss.getWidth(), getManager().getBottomBorder() / 2);
            //pokaz z prawej strony
        else
            miniBoss.setPosition(getManager().getRightBorder() + miniBoss.getWidth(), getManager().getBottomBorder() / 2);

        monsters.add(miniBoss);
    }

    //uzupelnic w fishMonster (najlepiej dac do monster metode dla 3 i 4 kierunkow)
    public void setPositionMonsters(ArrayList<Monster> monsters) {

        for (Sprite m : monsters) {

            int random = (int) (Math.random() * 4 + 1);
            int spawnPositionY = (int) (Math.random() * getManager().getBottomBorder());
            int spawnPositionX = (int) (Math.random() * getManager().getRightBorder());

            //spawn na lewym brzegu
            if (random == 1)
                m.setPosition(getManager().getLeftBorder(), spawnPositionY);

            //spawn na prawym brzegu
            if (random == 2)
                m.setPosition(getManager().getRightBorder(), spawnPositionY);
            //spawn na gornym brzegu
            if (random == 3)
                m.setPosition(spawnPositionX, getManager().getTopBorder());

            //spawn na dolnym brzegu
            if (random == 4)
                m.setPosition(spawnPositionX, getManager().getBottomBorder());
        }

    }

    public boolean needToSpawnMiniboss(int collectedMoneyBags, boolean killedAllMonsters) {
        if ((collectedMoneyBags >= getAmountOfAllCoins()) && (killedAllMonsters))
            return true;

        return false;
    }

    @Override
    public void renderLevel(ArrayList<Monster> monsters, ArrayList<Coin> coins, ArrayList<ShotSprite> shots, Player player) {
        defaultRenderLevel(monsters, coins, shots, player.getTotalScore(), BACKGROUND_IMAGE);
        if (needToSpawnMiniboss(player.getCollectedCoinsOnLevel(), monsters.isEmpty()))
            spawnMiniboss(monsters);
    }

    @Override
    public Point2D playerStartPosition() {
        return getDefaultPlayerPosition();
    }

    @Override
    public void playBackgroundSound() {
        setBackgroundSound(Level_1.BACKGROUND_SOUND);
        AllLevels.playBackgroundSound(Level_1.BACKGROUND_SOUND_VOLUME, true);
    }
}
