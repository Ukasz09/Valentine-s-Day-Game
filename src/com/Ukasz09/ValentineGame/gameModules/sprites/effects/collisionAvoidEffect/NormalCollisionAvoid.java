package com.Ukasz09.ValentineGame.gameModules.sprites.effects.collisionAvoidEffect;

import com.Ukasz09.ValentineGame.gameModules.sprites.creatures.Monster;
import com.Ukasz09.ValentineGame.gameModules.sprites.creatures.Sprite;
import javafx.geometry.Point2D;

import java.util.ArrayList;

public class NormalCollisionAvoid implements ICollisionAvoidWay {

    private Point2D offsetAfterCollision(Monster m1, Monster m2, Sprite target) {
        double addX = 0;
        double addY = 0;

        if (m1.intersects(m2) && (m1 != m2)) {
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
        }

        return new Point2D(addX, addY);
    }

    @Override
    public void updateCords(Sprite target, Monster monster, ArrayList<Monster> monstersList) {
        if (!monster.intersectsForCollision(target)) {
            monster.updateByVelocity(target);
            double dx = monster.getPositionX();
            double dy = monster.getPositionY();

            for (Monster m : monstersList) {
                if (monster.intersectsForCollision(m)) {
                    Point2D offsetPoint = offsetAfterCollision(monster, m, target);
                    double addX = offsetPoint.getX();
                    double addY = offsetPoint.getY();
                    dx += addX;
                    dy += addY;
                    monster.setPosition(dx, dy);
                }
            }
        }
    }
}
