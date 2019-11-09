package com.Ukasz09.ValentineGame.gameModules.effects.kickEffect;

import com.Ukasz09.ValentineGame.gameModules.utilitis.ViewManager;
import com.Ukasz09.ValentineGame.gameModules.sprites.creatures.Monster;
import com.Ukasz09.ValentineGame.gameModules.sprites.creatures.Player;

public class TeleportKick implements KindOfKick {

    /**
     * Move player instantly by amount of pixels in kickDirection
     */
    @Override
    public void kick(Monster m, Player player, String kickDirection, ViewManager manager) {
        switch (kickDirection) {
            case "A": {
                if (!player.leftSideFrameCollision())
                    player.setPosition(player.getPositionX() - m.getKickSize(), player.getPositionY());
            }
            break;

            case "D": {
                if (!player.rightSideFrameCollision())
                    player.setPosition(player.getPositionX() + m.getKickSize(), player.getPositionY());
            }
            break;

            case "W": {
                if (!player.topSideFrameCollision())
                    player.setPosition(player.getPositionX(), player.getPositionY() - m.getKickSize());
            }
            break;

            case "S": {
                if (!player.downSideFrameCollision())
                    player.setPosition(player.getPositionX(), player.getPositionY() + m.getKickSize());
            }
            break;

            case "SD": {
                if ((!player.downSideFrameCollision()) && (!player.rightSideFrameCollision()))
                    player.setPosition(player.getPositionX() + m.getKickSize(), player.getPositionY() + m.getKickSize());
            }
            break;

            case "WD": {
                if ((!player.topSideFrameCollision()) && (!player.rightSideFrameCollision()))
                    player.setPosition(player.getPositionX() + m.getKickSize(), player.getPositionY() - m.getKickSize());
            }
            break;

            case "SA": {
                if ((!player.downSideFrameCollision()) && (!player.leftSideFrameCollision()))
                    player.setPosition(player.getPositionX() - m.getKickSize(), player.getPositionY() + m.getKickSize());
            }
            break;

            case "WA": {
                if ((!player.topSideFrameCollision()) && (!player.leftSideFrameCollision()))
                    player.setPosition(player.getPositionX() - m.getKickSize(), player.getPositionY() - m.getKickSize());
            }
            break;

            default:
                System.out.println("Wrong. Kick direction");
        }
    }
}
