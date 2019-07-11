package com.Ukasz09.ValentineGame.gameModules.sprites.effects.kickEffect;

import com.Ukasz09.ValentineGame.gameModules.utilitis.ViewManager;
import com.Ukasz09.ValentineGame.gameModules.sprites.creatures.Monster;
import com.Ukasz09.ValentineGame.gameModules.sprites.creatures.Player;

public class TeleportKick implements KindOfKick {
    @Override
    public void kick(Monster m, Player player, String kickDirection, ViewManager manager) {
        switch (kickDirection) {
            case "A": {
                if (!player.boundaryCollisionFromLeft(manager.getLeftBorder()))
                    player.setPosition(player.getPositionX() - m.getHowBigKickSize(), player.getPositionY());
            }
            break;

            case "D": {
                if (!player.boundaryCollisionFromRight(manager.getRightBorder()))
                    player.setPosition(player.getPositionX() + m.getHowBigKickSize(), player.getPositionY());
            }
            break;

            case "W": {
                if (!player.boundaryCollisionFromTop(manager.getTopBorder()))
                    player.setPosition(player.getPositionX(), player.getPositionY() - m.getHowBigKickSize());
            }
            break;

            case "S": {
                if (!player.boundaryCollisionFromBottom(manager.getBottomBorder()))
                    player.setPosition(player.getPositionX(), player.getPositionY() + m.getHowBigKickSize());
            }
            break;

            case "SD": {
                if ((!player.boundaryCollisionFromBottom(manager.getBottomBorder())) && (!player.boundaryCollisionFromRight(manager.getRightBorder())))
                    player.setPosition(player.getPositionX() + m.getHowBigKickSize(), player.getPositionY() + m.getHowBigKickSize());
            }
            break;

            case "WD": {
                if ((!player.boundaryCollisionFromTop(manager.getTopBorder())) && (!player.boundaryCollisionFromRight(manager.getRightBorder())))
                    player.setPosition(player.getPositionX() + m.getHowBigKickSize(), player.getPositionY() - m.getHowBigKickSize());
            }
            break;

            case "SA": {
                if ((!player.boundaryCollisionFromBottom(manager.getBottomBorder())) && (!player.boundaryCollisionFromLeft(manager.getLeftBorder())))
                    player.setPosition(player.getPositionX() - m.getHowBigKickSize(), player.getPositionY() + m.getHowBigKickSize());
            }
            break;

            case "WA": {
                if ((!player.boundaryCollisionFromTop(manager.getTopBorder())) && (!player.boundaryCollisionFromLeft(manager.getLeftBorder())))
                    player.setPosition(player.getPositionX() - m.getHowBigKickSize(), player.getPositionY() - m.getHowBigKickSize());
            }
            break;

            default:
                System.out.println("Wrong. Kick direction");
        }
    }
}
