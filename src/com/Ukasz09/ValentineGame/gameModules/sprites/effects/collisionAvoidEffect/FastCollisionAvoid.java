package com.Ukasz09.ValentineGame.gameModules.sprites.effects.collisionAvoidEffect;

import com.Ukasz09.ValentineGame.gameModules.sprites.creatures.Monster;
import com.Ukasz09.ValentineGame.gameModules.sprites.creatures.Sprite;
import javafx.geometry.Point2D;

import java.util.ArrayList;

public class FastCollisionAvoid extends NormalCollisionAvoid{

    @Override
    public void updateCords(Sprite target, Monster monster, ArrayList<Monster> monstersList) {
        if (!monster.intersects(target)) {
            monster.updateByVelocity(target);
            double dx = monster.getPositionX();
            double dy = monster.getPositionY();

            for (Monster m: monstersList){
                Point2D offsetPoint = offsetAfterCollision(monster, m, target, monstersList);
                dx += offsetPoint.getX()*1.5;
                dy += offsetPoint.getY()*1.5;
                monster.setPosition(dx, dy);
            }
        }
    }
}
