package com.Ukasz09.ValentineGame.gameModules.sprites.effects.kickEffect;

import com.Ukasz09.ValentineGame.gameModules.utilitis.ViewManager;
import com.Ukasz09.ValentineGame.gameModules.sprites.creatures.Monster;
import com.Ukasz09.ValentineGame.gameModules.sprites.creatures.Player;

public interface KickPlayer {

    void kickPlayerByMonsterPostion(Monster m, Player p, ViewManager manager);

    String getKickDirection(Monster m, Player p);
}
