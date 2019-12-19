package com.Ukasz09.ValentineGame.gameModules.effects.kickEffect;

import com.Ukasz09.ValentineGame.gameModules.utilitis.ViewManager;
import com.Ukasz09.ValentineGame.gameModules.sprites.creatures.Monster;
import com.Ukasz09.ValentineGame.gameModules.sprites.creatures.Player;

public class KickByBigMonster implements KickPlayer {
    private KindOfKick kindOfKick;

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public KickByBigMonster(KindOfKick kindOfKick) {
        this.kindOfKick = kindOfKick;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @Override
    public void kickPlayerByMonsterPosition(Monster m, Player p, ViewManager manager) {
        kindOfKick.kick(m, p, getKickDirection(m, p), manager);
    }

    @Override
    public KickDirection getKickDirection(Monster m, Player p) {
        double monsterMinX = m.getBoundary().getMinX();
        double monsterMaxX = m.getBoundary().getMaxX();
        double monsterMaxY = m.getBoundary().getMaxY();
        double monsterMinY = m.getBoundary().getMinY();

        double playerMinX = p.getBoundary().getMinX();
        double playerMaxX = p.getBoundary().getMaxX();
        double playerMinY = p.getBoundary().getMinY();
        double playerMaxY = p.getBoundary().getMaxY();

        //up or down
        if ((playerMinX > monsterMinX) && (playerMaxX < monsterMaxX)) {
            if (monsterMinY < playerMinY)
                return KickDirection.DOWN;
            else return KickDirection.UP;
        }
        //right kick
        if (monsterMaxX < playerMaxX) {
            if ((playerMaxY - 0.25 * playerMaxY < monsterMaxY) && (playerMinY + 0.25 * playerMinY > monsterMinY))
                return KickDirection.RIGHT;
            else if (monsterMinY < playerMinY)
                return KickDirection.DOWN_RIGHT;
            else return KickDirection.UP_RIGHT;
        }

        //left kick
        if ((playerMaxY - 0.25 * playerMaxY < monsterMaxY) && (playerMinY + 0.25 * playerMinY > monsterMinY))
            return KickDirection.LEFT;
        else if (monsterMinY < playerMinY)
            return KickDirection.DOWN_LEFT;
        else return KickDirection.UP_LEFT;
    }
}
