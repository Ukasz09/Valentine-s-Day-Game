package com.Ukasz09.ValentineGame.gameModules.sprites.others.kickEffect;

import com.Ukasz09.ValentineGame.gameModules.gameUtils.Game;
import com.Ukasz09.ValentineGame.gameModules.sprites.creatures.Monster;
import com.Ukasz09.ValentineGame.gameModules.sprites.creatures.Player;

public class TeleportKick implements KindOfKick {
    @Override
    public void kick(Monster m, Player player, String kickDirection) {
        switch (kickDirection) {
            case "A": {
                if (!player.boundaryCollisionFromLeft(Game.boundary.getAtLeftBorder()))
                    player.setPosition(player.getPositionX() - m.getHowBigKickSize(), player.getPositionY());
            }
            break;

            case "D": {
                if (!player.boundaryCollisionFromRight(Game.boundary.getAtRightBorder()))
                    player.setPosition(player.getPositionX() + m.getHowBigKickSize(), player.getPositionY());
            }
            break;

            case "W": {
                if (!player.boundaryCollisionFromTop(Game.boundary.getAtTopBorder()))
                    player.setPosition(player.getPositionX(), player.getPositionY() - m.getHowBigKickSize());
            }
            break;

            case "S": {
                if (!player.boundaryCollisionFromBottom(Game.boundary.getAtBottomBorder()))
                    player.setPosition(player.getPositionX(), player.getPositionY() + m.getHowBigKickSize());
            }
            break;

            case "SD": {
                if ((!player.boundaryCollisionFromBottom(Game.boundary.getAtBottomBorder())) && (!player.boundaryCollisionFromRight(Game.boundary.getAtRightBorder())))
                    player.setPosition(player.getPositionX() + m.getHowBigKickSize(), player.getPositionY() + m.getHowBigKickSize());
            }
            break;

            case "WD": {
                if ((!player.boundaryCollisionFromTop(Game.boundary.getAtTopBorder())) && (!player.boundaryCollisionFromRight(Game.boundary.getAtRightBorder())))
                    player.setPosition(player.getPositionX() + m.getHowBigKickSize(), player.getPositionY() - m.getHowBigKickSize());
            }
            break;

            case "SA": {
                if ((!player.boundaryCollisionFromBottom(Game.boundary.getAtBottomBorder())) && (!player.boundaryCollisionFromLeft(Game.boundary.getAtLeftBorder())))
                    player.setPosition(player.getPositionX() - m.getHowBigKickSize(), player.getPositionY() + m.getHowBigKickSize());
            }
            break;

            case "WA": {
                if ((!player.boundaryCollisionFromTop(Game.boundary.getAtTopBorder())) && (!player.boundaryCollisionFromLeft(Game.boundary.getAtLeftBorder())))
                    player.setPosition(player.getPositionX() - m.getHowBigKickSize(), player.getPositionY() - m.getHowBigKickSize());
            }
            break;

            default:
                System.out.println("Wrong. Kick direction");
        }
    }
}
