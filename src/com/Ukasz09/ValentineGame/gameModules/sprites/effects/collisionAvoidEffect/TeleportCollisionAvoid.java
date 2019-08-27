package com.Ukasz09.ValentineGame.gameModules.sprites.effects.collisionAvoidEffect;

import com.Ukasz09.ValentineGame.gameModules.sprites.creatures.Monster;
import com.Ukasz09.ValentineGame.gameModules.sprites.creatures.Sprite;
import com.Ukasz09.ValentineGame.gameModules.utilitis.ViewManager;
import javafx.geometry.Point2D;

import java.util.ArrayList;

public class TeleportCollisionAvoid implements ICollisionAvoidWay {

    @Override
    public void updateCords(Sprite target, Monster monster, ArrayList<Monster> monstersList) {
        if (!monster.intersects(target)) {
            monster.updateByVelocity(target);

            for (Monster m : monstersList) {
                if (monster.intersectsForCollision(m) && (monster != m)) {
                    double dx = (int) (Math.random() * ViewManager.WIDTH);
                    double dy = (int) (Math.random() * ViewManager.HEIGHT);
                    monster.setPosition(dx, dy);
                    return;
                }
            }
        }
    }
}
