package com.Ukasz09.ValentineGame.gameModules.effects.kickEffect;

import com.Ukasz09.ValentineGame.gameModules.utilitis.ViewManager;
import com.Ukasz09.ValentineGame.gameModules.sprites.creatures.Monster;
import com.Ukasz09.ValentineGame.gameModules.sprites.creatures.Player;

public class KickByLittleMonster implements KickPlayer {
    private KindOfKick kickMethod;

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public KickByLittleMonster(KindOfKick kickMethod) {
        this.kickMethod = kickMethod;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @Override
    public void kickPlayerByMonsterPosition(Monster m, Player p, ViewManager manager) {
        kickMethod.kick(m, p, getKickDirection(m, p), manager);
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
        if ((monsterMinX > playerMinX) && (monsterMaxX < playerMaxX)) {
            if (monsterMinY < playerMinY)
                return KickDirection.DOWN;
            else return KickDirection.UP;
        }

        //right kick
        if (monsterMaxX < playerMaxX) {
            if ((monsterMaxY < playerMaxY) && (monsterMinY > playerMinY))
                return KickDirection.RIGHT;
            else if (monsterMinY < playerMinY)
                return KickDirection.DOWN_RIGHT;
            else return KickDirection.UP_RIGHT;
        }

        //left kick
        if ((monsterMaxY < playerMaxY) && (monsterMinY > playerMinY))
            return KickDirection.LEFT;
        else if (monsterMinY < playerMinY)
            return KickDirection.DOWN_LEFT;
        else return KickDirection.UP_LEFT;
    }
}
