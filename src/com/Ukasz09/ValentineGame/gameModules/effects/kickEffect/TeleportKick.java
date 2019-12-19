package com.Ukasz09.ValentineGame.gameModules.effects.kickEffect;

import com.Ukasz09.ValentineGame.gameModules.utilitis.Logger;
import com.Ukasz09.ValentineGame.gameModules.utilitis.ViewManager;
import com.Ukasz09.ValentineGame.gameModules.sprites.creatures.Monster;
import com.Ukasz09.ValentineGame.gameModules.sprites.creatures.Player;

public class TeleportKick implements KindOfKick {

    /**
     * Move player instantly by amount of pixels in kickDirection
     */
    @Override
    public void kick(Monster m, Player player, KickDirection kickDirection, ViewManager manager) {
        switch (kickDirection) {
            case LEFT: {
                if (!player.leftSideFrameCollision())
                    player.setPosition(player.getPositionX() - m.getKickSize(), player.getPositionY());
            }
            break;

            case RIGHT: {
                if (!player.rightSideFrameCollision())
                    player.setPosition(player.getPositionX() + m.getKickSize(), player.getPositionY());
            }
            break;

            case UP: {
                if (!player.topSideFrameCollision())
                    player.setPosition(player.getPositionX(), player.getPositionY() - m.getKickSize());
            }
            break;

            case DOWN: {
                if (!player.downSideFrameCollision())
                    player.setPosition(player.getPositionX(), player.getPositionY() + m.getKickSize());
            }
            break;

            case DOWN_RIGHT: {
                if ((!player.downSideFrameCollision()) && (!player.rightSideFrameCollision()))
                    player.setPosition(player.getPositionX() + m.getKickSize(), player.getPositionY() + m.getKickSize());
            }
            break;

            case UP_RIGHT: {
                if ((!player.topSideFrameCollision()) && (!player.rightSideFrameCollision()))
                    player.setPosition(player.getPositionX() + m.getKickSize(), player.getPositionY() - m.getKickSize());
            }
            break;

            case DOWN_LEFT: {
                if ((!player.downSideFrameCollision()) && (!player.leftSideFrameCollision()))
                    player.setPosition(player.getPositionX() - m.getKickSize(), player.getPositionY() + m.getKickSize());
            }
            break;

            case UP_LEFT: {
                if ((!player.topSideFrameCollision()) && (!player.leftSideFrameCollision()))
                    player.setPosition(player.getPositionX() - m.getKickSize(), player.getPositionY() - m.getKickSize());
            }
            break;

            default:
                Logger.logError(getClass(), "Incorrect direction in teleport kick");
        }
    }
}
