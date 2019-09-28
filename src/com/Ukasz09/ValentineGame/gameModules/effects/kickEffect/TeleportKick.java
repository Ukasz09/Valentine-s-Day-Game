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
                if (!player.boundaryCollisionFromRightSide())
                    player.setPosition(player.getPositionX() + m.getKickSize(), player.getPositionY());
            }
            break;

            case "W": {
                if (!player.boundaryCollisionFromTop())
                    player.setPosition(player.getPositionX(), player.getPositionY() - m.getKickSize());
            }
            break;

            case "S": {
                if (!player.boundaryCollisionFromBottom())
                    player.setPosition(player.getPositionX(), player.getPositionY() + m.getKickSize());
            }
            break;

            case "SD": {
                if ((!player.boundaryCollisionFromBottom()) && (!player.boundaryCollisionFromRightSide()))
                    player.setPosition(player.getPositionX() + m.getKickSize(), player.getPositionY() + m.getKickSize());
            }
            break;

            case "WD": {
                if ((!player.boundaryCollisionFromTop()) && (!player.boundaryCollisionFromRightSide()))
                    player.setPosition(player.getPositionX() + m.getKickSize(), player.getPositionY() - m.getKickSize());
            }
            break;

            case "SA": {
                if ((!player.boundaryCollisionFromBottom()) && (!player.leftSideFrameCollision()))
                    player.setPosition(player.getPositionX() - m.getKickSize(), player.getPositionY() + m.getKickSize());
            }
            break;

            case "WA": {
                if ((!player.boundaryCollisionFromTop()) && (!player.leftSideFrameCollision()))
                    player.setPosition(player.getPositionX() - m.getKickSize(), player.getPositionY() - m.getKickSize());
            }
            break;

            default:
                System.out.println("Wrong. Kick direction");
        }
    }
}
