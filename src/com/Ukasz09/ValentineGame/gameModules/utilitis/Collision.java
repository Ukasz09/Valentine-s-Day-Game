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
    /* Metody */

    //sprawdza kolizje sprita z monetami
    public static void checkMoneybagsCollisions(ArrayList<MoneyBag> moneybagList, Sprite sprite, IntValue collectedMoneyBags) {

        Iterator<MoneyBag> moneybagIter = moneybagList.iterator();

        while (moneybagIter.hasNext()) {

            MoneyBag moneybag = moneybagIter.next();

            //jesli nastapila kolizja
            if (sprite.intersects(moneybag)) {

                //jesli sprawdzamy kolizje gracza

                if (sprite instanceof Player) {

                    moneybag.playCollectSound();
                    moneybagIter.remove();
                    ((Player) sprite).addTotalScore(moneybag.getValue());
                    collectedMoneyBags.incValue();
                }
            }
        } //zamyka while

    }

    //sprawdza kolizje z pociskami gracza
    public static void playerShotCollision(ArrayList<Monster> monsters, ArrayList<ShotSprite> shots, IntValue monsterKilledOnLevel) {

        Iterator<Monster> monstersIter = monsters.iterator();

        while (monstersIter.hasNext()) {

            Monster monster = monstersIter.next();

            Iterator<ShotSprite> shootIter = shots.iterator();

            //iteracja na strzaly
            while (shootIter.hasNext()) {

                ShotSprite shot = shootIter.next();

                if (monster.intersects(shot)) {

                    //jesli potwor nie jest chroniony tarcza
                    if (monster.getProtectionTime() <= 0) {

                        //jesli dostal bomba
                        if (shot instanceof BombSprite) {

                            ((BombSprite) shot).getRandomBoomSound().playSound(0.4, false);
                            monster.removeLives(shot.getHowManyLivesTake());
                        }

                        //jesli dostal pociskiem
                        if (shot instanceof BulletSprite) {

                            monster.removeLives(shot.getHowManyLivesTake());
                        }

                        //jesli potwor zginal
                        if (monster.getLives() <= 0) {

                            monster.getDeathSound().playSound(1, false);
                            monstersIter.remove();
                            monsterKilledOnLevel.incValue();
                        } else monster.getHitSound().playSound(0.5, false);

                    } else if (monster.getMissSound() != null)
                        monster.getMissSound().playSound(1, false);

                    shootIter.remove();
                }

            } //zamyka petle na pociski
        } //zamyka petle na potwory
    }

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
