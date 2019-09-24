package com.Ukasz09.ValentineGame.tmp;

import com.Ukasz09.ValentineGame.gameModules.sprites.creatures.Sprite;
import javafx.scene.canvas.Canvas;

public class NotUsed {

//    //odrzut gracza wedlug ostatnio kliknietego klawisza
//    public void kickPlayer(double kickSize, Canvas canvas) {
//
//        //PRAWO/LEWO
//
//        //w lewo
//        if (lastDirectionX.equals("D")) {
//
//            setPosition(getPositionX() - kickSize, getPositionY());
//
//            //dopoki kolizja z granica mapy
//            while (Boundary.boundaryCollisionFromLeft(canvas, this))
//                setPosition(getPositionX() + kickSize / 4, getPositionY());
//
//        }
//
//        //w prawo
//        else {
//
//            setPosition(getPositionX() + kickSize, getPositionY());
//
//            //dopoki kolizja z granica mapy
//            while (Boundary.boundaryCollisionFromRight(canvas, this))
//                setPosition(getPositionX() - kickSize / 4, getPositionY());
//
//        }
//
//        //GORA/DOL
//
//        //w dol
//        if (lastDirectionY.equals("W")) {
//
//            setPosition(getPositionX(), getPositionY() + kickSize);
//
//            //dopoki kolizja z granica mapy
//            while (Boundary.boundaryCollisionFromBottom(canvas, this))
//                setPosition(getPositionX(), getPositionY() - kickSize / 4);
//        }
//
//        //w gore
//        else {
//
//            setPosition(getPositionX(), getPositionY() - kickSize);
//
//            //dopoki kolizja z granica mapy
//            while (Boundary.boundaryCollisionFromTop(canvas, this))
//                setPosition(getPositionX(), getPositionY() + kickSize / 4);
//        }
//    }

//    public boolean isDownSideToTarget(Sprite target) {
//        return positionByTarget.isDownSideToTarget(this, target);
//    }
//
//    public boolean isExactlyUnderOrAboveTarget(Sprite target) {
//        return positionByTarget.isExactlyUnderOrAboveTarget(this, target);
//    }
//
//    public boolean isExactlyAboveTarget(Sprite target) {
//        return positionByTarget.isExactlyAboveTarget(this, target);
//    }

//    public boolean isRightSideToTarget(Sprite target) {
//        return positionByTarget.isRightSideToTarget(this, target);
//    }

//    public boolean isExactlyUnderOrAboveTarget(Sprite creature, Sprite target) {
//        double creatureMinX = creature.getBoundary().getMinX();
//        double creatureMaxX = creature.getBoundary().getMaxX();
//
//        if ((creatureMinX >= target.getBoundary().getMinX()) && (creatureMaxX <= target.getBoundary().getMaxX()))
//            return true;
//
//        return false;
//    }
//
//    public boolean isExactlyAboveTarget(Sprite creature, Sprite target) {
//        if (isExactlyUnderOrAboveTarget(creature, target) && isUpSideToTarget(creature, target))
//            return true;
//
//        return false;
//    }
}
