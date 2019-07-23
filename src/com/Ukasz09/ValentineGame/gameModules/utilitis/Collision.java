package com.Ukasz09.ValentineGame.gameModules.utilitis;

import com.Ukasz09.ValentineGame.gameModules.sprites.creatures.Monster;
import com.Ukasz09.ValentineGame.gameModules.sprites.creatures.Sprite;
import com.Ukasz09.ValentineGame.gameModules.sprites.creatures.Player;
import com.Ukasz09.ValentineGame.gameModules.sprites.items.MoneyBag;
import com.Ukasz09.ValentineGame.gameModules.sprites.weapons.BombSprite;
import com.Ukasz09.ValentineGame.gameModules.sprites.weapons.BulletSprite;
import com.Ukasz09.ValentineGame.gameModules.sprites.weapons.ShotSprite;
import com.Ukasz09.ValentineGame.gameModules.utilitis.wrappers.IntValue;

import java.util.ArrayList;
import java.util.Iterator;

//todo: popsul sie antiblock
public class Collision {

    private static final int defaultAntiCollisionsTimer = 2000 / (Player.getDefaultVelocity() / 400);

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /* Gettery */

    public static int getDefaultAntiCollisionsTimer() {
        return defaultAntiCollisionsTimer;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    //zwraca czy nastapila kolizja gracza z potworem
    public static boolean playerCollisionWithMonster(ArrayList<Monster> monsters, Player player) {

        for (Sprite m : monsters) {

            if (m.intersectsWithMonster(player)) {

                if (player.getProtectionTime() <= 0) {

                    if (((Monster) m).getHowBigKickSize() > 0)
                        ((Monster) m).kickPlayer(player);

                    player.removeLives(((Monster) m).getHowManyLivesTake());
                    player.getUkaszRandomHitSound().playSound(0.5, false);

                    player.activateShield();
                }

                return true;
            }
        }

        return false;
    }

    //sprawdza kolizje gracza z lewym brzegiem potwora
    public static boolean collisionWithMonstersFromLeft(ArrayList<Monster> monsters, Sprite sprite) {

        for (Sprite m : monsters) {

            if (m != sprite) {

                if ((m.getBoundary().getMinX() < sprite.getBoundary().getMaxX()) && (m.getBoundary().getMaxX() > sprite.getBoundary().getMaxX()) && (m.intersectsWithMonster(sprite)))
                    return true;
            }
        }

        return false;
    }

    //sprawdza kolizje gracza z lewym brzegiem potwora
    public static boolean collisionWithMonstersFromRight(ArrayList<Monster> monsters, Sprite ukasz) {

        for (Sprite m : monsters) {

            if (m != ukasz) {

                if ((m.getBoundary().getMaxX() > ukasz.getBoundary().getMinX()) && (m.getBoundary().getMinX() < ukasz.getBoundary().getMinX()) && (m.intersectsWithMonster(ukasz)))
                    return true;
            }
        }

        return false;
    }

    //sprawdza kolizje gracza z gornym brzegiem potwora
    public static boolean collisionWithMonstersFromTop(ArrayList<Monster> monsters, Sprite ukasz) {

        for (Sprite m : monsters) {

            if ((m.getBoundary().getMinY() < ukasz.getBoundary().getMaxY()) && (m.getBoundary().getMinY() > ukasz.getBoundary().getMinY()) && (m.intersectsWithMonster(ukasz)))
                return true;
        }


        return false;
    }

    //sprawdza kolizje gracza z dolnym brzegiem potwora
    public static boolean collisionWithMonstersFromBottom(ArrayList<Monster> monsters, Sprite ukasz) {

        for (Sprite m : monsters) {
            if ((m.getBoundary().getMaxY() > ukasz.getBoundary().getMinY()) && (m.getBoundary().getMinY() < ukasz.getBoundary().getMinY()) && (m.intersectsWithMonster(ukasz)))
                return true;
        }

        return false;
    }

    //zwraca czy gracz ma kolizje z potworem/ramka z kazdej strony
    public static boolean collisionFromAllDirection(ArrayList<Monster> monsters, Sprite player, ViewManager manager) {

        if (
                ((collisionWithMonstersFromBottom(monsters, player)) || (player.boundaryCollisionFromBottom(manager.getBottomBorder()))) &&
                        ((collisionWithMonstersFromTop(monsters, player)) || (player.boundaryCollisionFromTop(manager.getTopBorder()))) &&
                        ((collisionWithMonstersFromLeft(monsters, player)) || (player.boundaryCollisionFromLeft(manager.getLeftBorder()))) &&
                        ((collisionWithMonstersFromRight(monsters, player)) || (player.boundaryCollisionFromRight(manager.getRightBorder())))
        ) return true;

        return false;
    }

}
