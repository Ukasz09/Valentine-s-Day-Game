package com.Ukasz09.ValentineGame.gameModules.sprites.others.kickEffect;

import com.Ukasz09.ValentineGame.gameModules.gameUtils.ViewManager;
import com.Ukasz09.ValentineGame.gameModules.sprites.creatures.Monster;
import com.Ukasz09.ValentineGame.gameModules.sprites.creatures.Player;
import javafx.scene.canvas.Canvas;

public interface KickPlayer {

    void kickPlayerByMonsterPostion(Monster m, Player p, ViewManager manager);

    String getKickDirection(Monster m, Player p);
}
