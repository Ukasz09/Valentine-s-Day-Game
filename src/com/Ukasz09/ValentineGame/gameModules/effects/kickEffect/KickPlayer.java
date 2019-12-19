package com.Ukasz09.ValentineGame.gameModules.effects.kickEffect;

import com.Ukasz09.ValentineGame.gameModules.utilitis.ViewManager;
import com.Ukasz09.ValentineGame.gameModules.sprites.creatures.Monster;
import com.Ukasz09.ValentineGame.gameModules.sprites.creatures.Player;

public interface KickPlayer {

    void kickPlayerByMonsterPosition(Monster m, Player p, ViewManager manager);

    KickDirection getKickDirection(Monster m, Player p);
}
