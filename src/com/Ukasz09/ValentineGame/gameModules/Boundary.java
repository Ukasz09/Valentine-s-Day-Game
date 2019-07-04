package com.Ukasz09.ValentineGame.gameModules;

import com.Ukasz09.ValentineGame.sprites.creatures.Sprite;
import com.Ukasz09.ValentineGame.sprites.weapons.BombSprite;
import com.Ukasz09.ValentineGame.sprites.weapons.BulletSprite;
import com.Ukasz09.ValentineGame.sprites.weapons.ShootSprite;
import javafx.geometry.Bounds;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

import java.util.ArrayList;
import java.util.Iterator;

public class Boundary {

    private static Bounds bounds;
    private static double atRightBorder;
    private static double atLeftBorder;
    private static double atBottomBorder;
    private static double atTopBorder;

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /* Gettery */

    public static double getAtRightBorder(Canvas canvas) {

        checkWindowBoundary(canvas);
        return atRightBorder;
    }

    public static double getAtLeftBorder(Canvas canvas){

        checkWindowBoundary(canvas);
        return atLeftBorder;
    }

    public static double getAtBottomBorder(Canvas canvas){

        checkWindowBoundary(canvas);
        return atBottomBorder;

    }

    public static double getAtTopBorder(Canvas canvas) {

        checkWindowBoundary(canvas);
        return atTopBorder;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /* Metody */

    //zwraca czy nastapila kolizja z lewa strona ramki
    public static boolean boundaryCollisionFromLeft(Canvas canvas, Sprite sprite) {

        checkWindowBoundary(canvas);
        if((sprite.getBoundary().getMinX())<=atLeftBorder) return true;
        else return false;
    }

    //zwraca czy nastapila kolizja z prawa strona ramki
    public static boolean boundaryCollisionFromRight(Canvas canvas, Sprite sprite) {

        checkWindowBoundary(canvas);
        if((sprite.getBoundary().getMaxX())>=atRightBorder) return true;
        else return false;
    }

    //zwraca czy nastapila kolizja dolem ramki
    public static boolean boundaryCollisionFromBottom(Canvas canvas, Sprite sprite) {

        checkWindowBoundary(canvas);
        if((sprite.getBoundary().getMaxY())>=atBottomBorder) return true;
        else return false;

    }

    //zwraca czy nastapila kolizja z gora ramki
    public static boolean boundaryCollisionFromTop(Canvas canvas, Sprite sprite) {

        checkWindowBoundary(canvas);
        if((sprite.getBoundary().getMinY())<=atTopBorder) return true;
        else return false;
    }

    //zwraca wymiary ramki
    public static void checkWindowBoundary(Canvas canvas){

        bounds= canvas.getBoundsInLocal();
        atRightBorder = bounds.getMaxX();
        atLeftBorder = bounds.getMinX();
        atBottomBorder = bounds.getMaxY();
        atTopBorder = bounds.getMinY();
    }

    //usuwa pociski ktore wyszly poza mape
    public static void updateAndBoundaryActionForShots(GraphicsContext gc, ArrayList<ShootSprite> ukaszShots, double elapsedTime, Canvas canvas){

        Iterator<ShootSprite> shotIter = ukaszShots.iterator();

        while (shotIter.hasNext()) {

            ShootSprite shot = shotIter.next();
            shot.update(elapsedTime);

            //jesli jest to bullet
            if(shot instanceof BulletSprite){

                if ((shot.getPositionX()>getAtRightBorder(canvas))||(shot.getPositionX()<getAtLeftBorder(canvas))) {

                    shotIter.remove();
                } else shot.render(gc);
            }

            //jesli jest to bomba
            if(shot instanceof BombSprite){

                if ((shot.getBoundary().getMaxY()>getAtBottomBorder(canvas))) {

                    ((BombSprite) shot).getRandomBoomSound().playSound(0.4,false);
                    shotIter.remove();
                } else shot.render(gc);
            }

        } //zamyka while

    }

}
