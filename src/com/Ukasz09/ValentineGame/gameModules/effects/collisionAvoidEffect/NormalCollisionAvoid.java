package com.Ukasz09.ValentineGame.gameModules.effects.collisionAvoidEffect;

import com.Ukasz09.ValentineGame.gameModules.sprites.creatures.Monster;
import com.Ukasz09.ValentineGame.gameModules.sprites.creatures.Creature;
import javafx.geometry.Point2D;

import java.util.ArrayList;

public class NormalCollisionAvoid implements ICollisionAvoidWay {

    private Point2D offsetAfterCollision(Monster m1, Monster m2) {
        double velocityX = m1.getActualVelocityX() * 2;
        double velocityY = m1.getActualVelocityY() * 2;

        double addX = 0;
        double addY = 0;

        //above or below
        if (m1.isUpSideToTarget(m2))
            addY -= velocityY;
        else addY += velocityY;

        //left or right
        if (m1.isLeftSideToTarget(m2))
            addX -= velocityX;
        else addX += velocityX;

        return new Point2D(addX, addY);
    }

    @Override
    public void updateCords(Creature target, Monster monster, ArrayList<Monster> monstersList) {
        //if no collision with target
        if (!monster.intersects(target)) {
            monster.updateByVelocity(target);

            for (Monster m : monstersList) {
                if (monster.intersects(m) && monster != m) {
                    double addPositionX = offsetAfterCollision(monster, m).getX();
                    double addPositionY = offsetAfterCollision(monster, m).getY();
                    double monsterPositionX = monster.getPositionX() + addPositionX;
                    double monsterPositionY = monster.getPositionY() + addPositionY;

                    monster.setPosition(monsterPositionX, monsterPositionY);
                    m.setPosition(m.getPositionX() - addPositionX, m.getPositionY() - addPositionY);
                    return;
                }
            }
        }
    }
}
