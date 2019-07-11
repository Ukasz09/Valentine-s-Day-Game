package com.Ukasz09.ValentineGame.gameModules.sprites.effects.kickEffect;

import com.Ukasz09.ValentineGame.gameModules.utilitis.ViewManager;
import com.Ukasz09.ValentineGame.gameModules.sprites.creatures.Monster;
import com.Ukasz09.ValentineGame.gameModules.sprites.creatures.Player;

public class KickByLittleMonster implements KickPlayer {

    KindOfKick kickMethod;

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public KickByLittleMonster(KindOfKick kickMethod) {
        this.kickMethod = kickMethod;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @Override
    public void kickPlayerByMonsterPostion(Monster m, Player p, ViewManager manager) {
        kickMethod.kick(m, p, getKickDirection(m, p), manager);
    }

    /**
     * @return: kickDirection
     * <p>
     * W - UP
     * S - DOWN
     * A - LEFT
     * D - RIGHT
     **/
    @Override
    public String getKickDirection(Monster m, Player p) {
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
                return "S";
            else return "W";
        }

        //right kick
        if (monsterMaxX < playerMaxX) {
            if ((monsterMaxY < playerMaxY) && (monsterMinY > playerMinY))
                return "D";
            else if (monsterMinY < playerMinY)
                return "SD";
            else return "WD";
        }

        //left kick
        if ((monsterMaxY < playerMaxY) && (monsterMinY > playerMinY))
            return "A";
        else if (monsterMinY < playerMinY)
            return "SA";
        else return "WA";
    }
}
