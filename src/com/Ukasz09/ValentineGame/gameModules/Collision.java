package com.Ukasz09.ValentineGame.gameModules;

import com.Ukasz09.ValentineGame.sounds.Sounds;
import com.Ukasz09.ValentineGame.sprites.creatures.Monster;
import com.Ukasz09.ValentineGame.sprites.creatures.Sprite;
import com.Ukasz09.ValentineGame.sprites.creatures.Ukasz;
import com.Ukasz09.ValentineGame.sprites.others.MoneyBag;
import com.Ukasz09.ValentineGame.sprites.weapons.BombSprite;
import com.Ukasz09.ValentineGame.sprites.weapons.BulletSprite;
import com.Ukasz09.ValentineGame.sprites.weapons.ShootSprite;
import com.Ukasz09.ValentineGame.wrappers.IntValue;
import javafx.scene.canvas.Canvas;

import java.util.ArrayList;
import java.util.Iterator;

public class Collision {

    private static final int defaultAntiCollisionsTimer=2000/(Ukasz.getDefaultVelocity()/400);

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /* Gettery */

    public static int getDefaultAntiCollisionsTimer() {
        return defaultAntiCollisionsTimer;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /* Metody */

    //sprawdza kolizje sprita z monetami
    public static void checkMoneybagsCollisions(ArrayList<MoneyBag> moneybagList, Sprite sprite, IntValue score, IntValue collectedMoneyBags, Sounds collectSound){

        Iterator<MoneyBag> moneybagIter = moneybagList.iterator();

        while (moneybagIter.hasNext()) {

            MoneyBag moneybag = moneybagIter.next();

            //jesli nastapila kolizja
            if (sprite.intersects(moneybag)) {

                //jesli sprawdzamy kolizje gracza

                if(sprite instanceof Ukasz){

                    collectSound.playSound(0.1,false);

                    moneybagIter.remove();
                    score.addValue(moneybag.getValue());
                    collectedMoneyBags.incValue();
                }
            }
        } //zamyka while

    }

    //sprawdza kolizje z pociskami gracza
    public static void playerShotCollision(ArrayList<Monster> monsters, ArrayList<ShootSprite> shots, IntValue monsterKilledOnLevel){

        Iterator<Monster> monstersIter = monsters.iterator();

        while (monstersIter.hasNext()) {

            Monster monster = monstersIter.next();

            Iterator<ShootSprite> shootIter=shots.iterator();

            //iteracja na strzaly
            while (shootIter.hasNext()){

                ShootSprite shot=shootIter.next();

                if (monster.intersects(shot)) {

                    //jesli potwor nie jest chroniony tarcza
                    if(monster.getProtectionTime()<=0) {

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

                    } else if(monster.getMissSound()!=null)
                                monster.getMissSound().playSound(1,false);

                    shootIter.remove();
                }

            } //zamyka petle na pociski
        } //zamyka petle na potwory
    }

    //zwraca czy nastapila kolizja gracza z potworem
    public static boolean playerCollisionWithMonster(ArrayList<Monster> monsters, Ukasz ukasz, Canvas canvas){

        for(Sprite m: monsters){

            if(m.intersectsWithMonster(ukasz)){

                  if (ukasz.getProtectionTime()<=0){

                      if(((Monster)m).getHowBigKickSize()>0)
                        ukasz.kickPlayerByMonsterPostion(((Monster)m),canvas);

                      ukasz.removeLives(((Monster)m).getHowManyLivesTake());
                      ukasz.getUkaszRandomHitSound().playSound(0.5,false);

                      ukasz.getShield().setActualShieldTimer(0);
                }

                return true;
            }
        }

        return false;
    }

    //sprawdza kolizje gracza z lewym brzegiem potwora
    public static boolean collisionWithMonstersFromLeft(ArrayList<Monster> monsters, Sprite sprite){

        for(Sprite m: monsters) {

            if(m!=sprite){

                if ((m.getBoundary().getMinX()<sprite.getBoundary().getMaxX())&&(m.getBoundary().getMaxX()>sprite.getBoundary().getMaxX())&&(m.intersectsWithMonster(sprite)))
                    return true;
            }
        }

        return false;
    }

    //sprawdza kolizje gracza z lewym brzegiem potwora
    public static boolean collisionWithMonstersFromRight(ArrayList<Monster> monsters, Sprite ukasz){

        for(Sprite m: monsters) {

            if(m!=ukasz){

                if ((m.getBoundary().getMaxX()>ukasz.getBoundary().getMinX())&&(m.getBoundary().getMinX()<ukasz.getBoundary().getMinX())&&(m.intersectsWithMonster(ukasz)))
                    return true;
            }
        }

        return false;
    }

    //sprawdza kolizje gracza z gornym brzegiem potwora
    public static boolean collisionWithMonstersFromTop(ArrayList<Monster> monsters, Sprite ukasz){

        for(Sprite m: monsters) {

            if ((m.getBoundary().getMinY()<ukasz.getBoundary().getMaxY())&&(m.getBoundary().getMinY()>ukasz.getBoundary().getMinY())&&(m.intersectsWithMonster(ukasz)))
                return true;
        }



        return false;
    }

    //sprawdza kolizje gracza z dolnym brzegiem potwora
    public static boolean collisionWithMonstersFromBottom(ArrayList<Monster> monsters, Sprite ukasz){

        for(Sprite m: monsters) {
            if ((m.getBoundary().getMaxY()>ukasz.getBoundary().getMinY())&&(m.getBoundary().getMinY()<ukasz.getBoundary().getMinY())&&(m.intersectsWithMonster(ukasz)))
                return true;
        }

        return false;
    }

    //zwraca czy gracz ma kolizje z potworem/ramka z kazdej strony
    public static boolean collisionFromAllDirection(ArrayList<Monster> monsters, Sprite ukasz, Canvas canvas){

        if (
                ((collisionWithMonstersFromBottom(monsters, ukasz))||(Boundary.boundaryCollisionFromTop(canvas,ukasz)))&&
                        ((collisionWithMonstersFromTop(monsters, ukasz))||(Boundary.boundaryCollisionFromBottom(canvas,ukasz)))&&
                            ((collisionWithMonstersFromLeft(monsters, ukasz))||(Boundary.boundaryCollisionFromRight(canvas,ukasz)))&&
                                ((collisionWithMonstersFromRight(monsters, ukasz))||(Boundary.boundaryCollisionFromLeft(canvas,ukasz)))
        ) return true;

         return false;
    }

}
