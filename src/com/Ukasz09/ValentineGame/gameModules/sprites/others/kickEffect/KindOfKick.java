package com.Ukasz09.ValentineGame.gameModules.sprites.others.kickEffect;

import com.Ukasz09.ValentineGame.gameModules.sprites.creatures.Monster;
import com.Ukasz09.ValentineGame.gameModules.sprites.creatures.Player;
import javafx.scene.canvas.Canvas;

public interface KindOfKick {

    void kick(Monster m, Player p, Canvas canvas, String kickDirection);

}
