package com.Ukasz09.ValentineGame.sprites.others;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class HealthStatusBar {

    private double maxHealth;
    private double actualHealth;
    private double percents;
    private final double barWidth=25;
    private double maxBarLength;
    private double actualBarLength;
    private Color color;

    private double positionX;
    private double positionY;

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /* Konstruktor */

    public HealthStatusBar(double maxHealth, double barWidth, double positionX, double positionY){

        this.maxHealth=maxHealth;
        actualHealth=maxHealth;
        percents=100;
        maxBarLength=barWidth;
        color= Color.WHITE;

        this.positionX=positionX;
        this.positionY=positionY;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /* Settery/Gettery */

    public void setPosition(double x, double y){

        positionX=x;
        positionY=y;
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /* Metody */

    public void update(double health, double positionX, double positionY){

        double r=187;
        double g=6;
        double b=6;

        percents=health/maxHealth*100;
        actualBarLength=maxBarLength*percents/100;

        if (health>maxHealth/2){

            r = 6+((100-percents)*3.5);

            if(r>186)
                r=186;

            g = 187;
        } else {

            r=186;
            g=-20+percents*2.8;

            if(g<6)
                g=6;
        }

        b=6;
        color=Color.rgb((int)r,(int)g,(int)b,0.9);

        this.positionX=positionX;
        this.positionY=positionY;

    }

    public void draw(GraphicsContext gc){

        gc.setFill(color);
        gc.fillRect(positionX,positionY,actualBarLength,barWidth);

    }

}
