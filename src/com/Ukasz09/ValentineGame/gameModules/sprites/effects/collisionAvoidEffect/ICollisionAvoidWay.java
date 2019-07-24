package com.Ukasz09.ValentineGame.gameModules.sprites.effects.collisionAvoidEffect;

import com.Ukasz09.ValentineGame.gameModules.sprites.creatures.Monster;
import com.Ukasz09.ValentineGame.gameModules.sprites.creatures.Sprite;

import java.util.ArrayList;

public interface ICollisionAvoidWay {

    void updateCords(Sprite target, Monster monster, ArrayList<Monster> monsters);
}
