package com.Ukasz09.ValentineGame.gameModules.effects.collisionAvoidEffect;

import com.Ukasz09.ValentineGame.gameModules.sprites.creatures.Monster;
import com.Ukasz09.ValentineGame.gameModules.sprites.creatures.Creature;
import com.Ukasz09.ValentineGame.gameModules.utilitis.ViewManager;

import java.util.ArrayList;

public class TeleportCollisionAvoid implements ICollisionAvoidWay {

    @Override
    public void updateCords(Creature target, Monster monster, ArrayList<Monster> monstersList) {
        if (!monster.intersects(target)) {
            monster.updateByVelocity(target);

            for (Monster m : monstersList) {
                if (monster.intersects(m) && (monster != m)) {
                    double dx = (int) (Math.random() * ViewManager.WIDTH);
                    double dy = (int) (Math.random() * ViewManager.HEIGHT);
                    monster.setPosition(dx, dy);
                    return;
                }
            }
        }
    }
}
