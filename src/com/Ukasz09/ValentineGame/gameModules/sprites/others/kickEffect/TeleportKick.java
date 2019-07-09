package com.Ukasz09.ValentineGame.gameModules.sprites.others.kickEffect;

import com.Ukasz09.ValentineGame.gameModules.Boundary;
import com.Ukasz09.ValentineGame.gameModules.sprites.creatures.Monster;
import com.Ukasz09.ValentineGame.gameModules.sprites.creatures.Player;
import javafx.scene.canvas.Canvas;

public class TeleportKick implements KindOfKick {
    @Override
    public void kick(Monster m, Player p, Canvas canvas, String kickDirection) {
        switch (kickDirection) {
            case "A": {
                if (!Boundary.boundaryCollisionFromLeft(canvas, p))
                    p.setPosition(p.getPositionX() - m.getHowBigKickSize(), p.getPositionY());
            }
            break;

            case "D": {
                if (!Boundary.boundaryCollisionFromRight(canvas, p))
                    p.setPosition(p.getPositionX() + m.getHowBigKickSize(), p.getPositionY());
            }
            break;

            case "W": {
                if (!Boundary.boundaryCollisionFromTop(canvas, p))
                    p.setPosition(p.getPositionX(), p.getPositionY() - m.getHowBigKickSize());
            }
            break;

            case "S": {
                if (!Boundary.boundaryCollisionFromBottom(canvas, p))
                    p.setPosition(p.getPositionX(), p.getPositionY() + m.getHowBigKickSize());
            }
            break;

            case "SD": {
                if ((!Boundary.boundaryCollisionFromBottom(canvas, p)) && (!Boundary.boundaryCollisionFromRight(canvas, p)))
                    p.setPosition(p.getPositionX() + m.getHowBigKickSize(), p.getPositionY() + m.getHowBigKickSize());
            }
            break;

            case "WD": {
                if ((!Boundary.boundaryCollisionFromTop(canvas, p)) && (!Boundary.boundaryCollisionFromRight(canvas, p)))
                    p.setPosition(p.getPositionX() + m.getHowBigKickSize(), p.getPositionY() - m.getHowBigKickSize());
            }
            break;

            case "SA": {
                if ((!Boundary.boundaryCollisionFromBottom(canvas, p)) && (!Boundary.boundaryCollisionFromLeft(canvas, p)))
                    p.setPosition(p.getPositionX() - m.getHowBigKickSize(), p.getPositionY() + m.getHowBigKickSize());
            }
            break;

            case "WA": {
                if ((!Boundary.boundaryCollisionFromTop(canvas, p)) && (!Boundary.boundaryCollisionFromLeft(canvas, p)))
                    p.setPosition(p.getPositionX() - m.getHowBigKickSize(), p.getPositionY() - m.getHowBigKickSize());
            }
            break;

            default:
                System.out.println("Wrong. Kick direction");
        }
    }
}
