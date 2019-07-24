package com.Ukasz09.ValentineGame.gameModules.sprites.effects.collisionAvoidEffect;

import com.Ukasz09.ValentineGame.gameModules.sprites.creatures.Monster;
import com.Ukasz09.ValentineGame.gameModules.sprites.creatures.Sprite;
import javafx.geometry.Point2D;

import java.util.ArrayList;

public abstract class NormalCollisionAvoid implements ICollisionAvoidWay {

//    @Override
//    public void updateCords(Sprite target, Monster monster, ArrayList<Monster> monstersList) {
//        if (!monster.intersects(target)) {
//            monster.updateByVelocity(target);
//            double dx = monster.getPositionX();
//            double dy = monster.getPositionY();
//
//            double addX = 0;
//            double addY = 0;
//
//            for (Monster m : monstersList)
//                if (m.intersects(monster) && (monster != m)) {
//                    //above
//                    if (monster.isUpSideToTarget(m)) {
//                        if (monster.isRightSideToTarget(target)) {
//                            addX += -monster.getVelocityX();
//                            addY += -monster.getVelocityY();
//                        } else {
//                            addX += monster.getVelocityX();
//                            addY += -monster.getVelocityY();
//                        }
//                    }
//                    //down
//                    else if (monster.isDownSideToTarget(m)) {
//                        if (monster.isRightSideToTarget(target)) {
//                            addX += -monster.getVelocityX();
//                            addY += monster.getVelocityY();
//                        } else {
//                            addX += monster.getVelocityX();
//                            addY += monster.getVelocityY();
//                        }
//                    } //left
//                    else if (monster.isLeftSideToTarget(m)) {
//                        if (monster.isUpSideToTarget(target)) {
//                            addY += monster.getVelocityY();
//                            addX += -monster.getVelocityY();
//                        } else {
//                            addY += -monster.getVelocityY();
//                            addX += -monster.getVelocityX();
//                        }
//                    } //right
//                    else if (monster.isRightSideToTarget(m)) {
//                        if (monster.isUpSideToTarget(target)) {
//                            addY += monster.getVelocityY();
//                            addX += monster.getVelocityX();
//                        } else {
//                            addY += -monster.getVelocityY();
//                            addX += monster.getVelocityX();
//                        }
//                    }
//
//                    dx += addX / 1.5;
//                    dy += addY / 1.5;
//                    monster.setPosition(dx, dy);
//                }
//        }
//    }

    public Point2D offsetAfterCollision(Monster m1, Monster m2, Sprite target, ArrayList<Monster> monstersList) {
        double addX = 0;
        double addY = 0;

            if (m2.intersects(m1) && (m1 != m2)) {
                //above
                if (m1.isUpSideToTarget(m2)) {
                    if (m1.isRightSideToTarget(target)) {
                        addX += -m1.getVelocityX();
                        addY += -m1.getVelocityY();
                    } else {
                        addX += m1.getVelocityX();
                        addY += -m1.getVelocityY();
                    }
                }
                //down
                else if (m1.isDownSideToTarget(m2)) {
                    if (m1.isRightSideToTarget(target)) {
                        addX += -m1.getVelocityX();
                        addY += m1.getVelocityY();
                    } else {
                        addX += m1.getVelocityX();
                        addY += m1.getVelocityY();
                    }
                } //left
                else if (m1.isLeftSideToTarget(m2)) {
                    if (m1.isUpSideToTarget(target)) {
                        addY += m1.getVelocityY();
                        addX += -m1.getVelocityY();
                    } else {
                        addY += -m1.getVelocityY();
                        addX += -m1.getVelocityX();
                    }
                } //right
                else if (m1.isRightSideToTarget(m2)) {
                    if (m1.isUpSideToTarget(target)) {
                        addY += m1.getVelocityY();
                        addX += m1.getVelocityX();
                    } else {
                        addY += -m1.getVelocityY();
                        addX += m1.getVelocityX();
                    }
                }
                return new Point2D(addX, addY);
            }

            return new Point2D(addX,addY);

    }

}
