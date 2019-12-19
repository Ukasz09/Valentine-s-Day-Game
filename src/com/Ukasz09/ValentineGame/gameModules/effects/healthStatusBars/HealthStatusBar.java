package com.Ukasz09.ValentineGame.gameModules.effects.healthStatusBars;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class HealthStatusBar {
    private final static double DEFAULT_BAR_WIDTH = 25;

    private final double barWidth = DEFAULT_BAR_WIDTH;
    private final double maxHealth;
    private double maxBarLength;
    private double actualBarLength;
    private Color color;
    private double percents;
    private double positionX;
    private double positionY;

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public HealthStatusBar(double maxHealth, double barWidth, double positionX, double positionY) {
        this.maxHealth = maxHealth;
        percents = 100;
        maxBarLength = barWidth;
        color = Color.WHITE;
        this.positionX = positionX;
        this.positionY = positionY;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public void setPosition(double x, double y) {
        positionX = x;
        positionY = y;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public void update(double health, double positionX, double positionY) {
        double r, g, b;
        percents = health / maxHealth * 100;
        actualBarLength = maxBarLength * percents / 100;

        if (health > maxHealth / 2) {
            r = 6 + ((100 - percents) * 3.5);
            if (r > 186)
                r = 186;
            g = 187;
        } else {
            r = 186;
            g = -20 + percents * 2.8;
            if (g < 6)
                g = 6;
        }
        b = 6;
        color = Color.rgb((int) r, (int) g, (int) b, 0.9);
        this.positionX = positionX;
        this.positionY = positionY;
    }

    public void draw(GraphicsContext gc) {
        gc.setFill(color);
        gc.fillRect(positionX, positionY, actualBarLength, barWidth);
    }

}
