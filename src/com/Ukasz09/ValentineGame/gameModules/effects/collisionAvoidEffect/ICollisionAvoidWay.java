package com.Ukasz09.ValentineGame.gameModules.effects.collisionAvoidEffect;

import com.Ukasz09.ValentineGame.gameModules.sprites.creatures.Monster;
import com.Ukasz09.ValentineGame.gameModules.sprites.creatures.Creature;

import java.util.ArrayList;

public interface ICollisionAvoidWay {

    void updateCords(Creature target, Monster monster, ArrayList<Monster> monsters);
}
