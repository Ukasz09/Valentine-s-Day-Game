package com.Ukasz09.ValentineGame.gameModules.sprites.others.kickEffect;

import com.Ukasz09.ValentineGame.gameModules.gameUtils.ViewManager;
import com.Ukasz09.ValentineGame.gameModules.sprites.creatures.Monster;
import com.Ukasz09.ValentineGame.gameModules.sprites.creatures.Player;
import javafx.scene.canvas.Canvas;

public class KickByBigMonster implements KickPlayer {

    KindOfKick kindOfKick;

    public KickByBigMonster(KindOfKick kindOfKick) {
        this.kindOfKick = kindOfKick;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @Override
    public void kickPlayerByMonsterPostion(Monster m, Player p, ViewManager manager) {
        kindOfKick.kick(m, p, getKickDirection(m, p), manager);
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
        if ((playerMinX> monsterMinX) && (playerMaxX< monsterMaxX)) {
            if (monsterMinY < playerMinY)
                return "S";
            else return "W";
        }

        //right kick
        if (monsterMaxX < playerMaxX) {
            if ((playerMaxY - 0.25 * playerMaxY < monsterMaxY) && (playerMinY + 0.25 * playerMinY > monsterMinY))
                return "D";
            else if (monsterMinY < playerMinY)
                return "SD";
            else return "WD";
        }

        //left kick
        if ((playerMaxY - 0.25 * playerMaxY < monsterMaxY) && (playerMinY + 0.25 * playerMinY > monsterMinY))
            return "A";
        else if (monsterMinY < playerMinY)
            return "SA";
        else return "WA";
    }
}
