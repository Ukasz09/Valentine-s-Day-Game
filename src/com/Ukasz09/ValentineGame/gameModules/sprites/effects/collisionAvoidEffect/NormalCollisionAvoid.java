package com.Ukasz09.ValentineGame.gameModules.sprites.effects.collisionAvoidEffect;

import com.Ukasz09.ValentineGame.gameModules.sprites.creatures.Monster;
import com.Ukasz09.ValentineGame.gameModules.sprites.creatures.Sprite;
import javafx.geometry.Point2D;

import java.util.ArrayList;

public abstract class NormalCollisionAvoid implements ICollisionAvoidWay {

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
