package com.Ukasz09.ValentineGame.gameModules.effects.collisionAvoidEffect;

import com.Ukasz09.ValentineGame.gameModules.sprites.creatures.Monster;
import com.Ukasz09.ValentineGame.gameModules.sprites.creatures.Creature;

import java.util.ArrayList;

public class OldNormalCollisonAvoid implements ICollisionAvoidWay {
    @Override
    public void updateCords(Creature target, Monster monster, ArrayList<Monster> monsters) {
        if (!monster.intersects(target)) {
            boolean doFixPosition;
            double dx = monster.getPositionX();
            double dy = monster.getPositionY();
            double diffX = target.getPositionX() - dx;
            double diffY = target.getPositionY() - dy;
            float angle = (float) Math.atan2(diffY, diffX);
            dx += monster.getActualVelocityX() * Math.cos(angle);
            dy += monster.getActualVelocityY() * Math.sin(angle);
            monster.setPosition(dx, dy);

            for (Creature m : monsters) {
                doFixPosition = false;
                if (m != monster) {
                    if ((m.getBoundary().getMinX() < monster.getBoundary().getMaxX()) && (m.getBoundary().getMaxX() > monster.getBoundary().getMaxX()) && (m.intersects(monster)) && (doFixPosition == false)) {
                        if (m.getBoundary().getMinY() < monster.getBoundary().getMinY()) {
                            if ((m.getBoundary().getMaxY() > monster.getBoundary().getMinY()) && (m.getBoundary().getMinY() < monster.getBoundary().getMinY()) && (m.intersects(monster)) && (doFixPosition == false)) {
                                dy = m.getBoundary().getMinY() + m.getHeight();
                                doFixPosition = true;
                            }
                        } else if (m.getBoundary().getMaxY() > monster.getBoundary().getMaxY()) {
                            if ((m.getBoundary().getMinY() < monster.getBoundary().getMaxY()) && (m.getBoundary().getMinY() > monster.getBoundary().getMinY()) && (m.intersects(monster)) && (doFixPosition == false)) {
                                dy = m.getBoundary().getMinY() - m.getHeight();
                                doFixPosition = true;
                            }
                        } else {
                            dx = m.getBoundary().getMinX() - m.getWidth();
                            doFixPosition = true;
                        }
                    }

                    if ((m.getBoundary().getMaxX() > monster.getBoundary().getMinX()) && (m.getBoundary().getMinX() < monster.getBoundary().getMinX()) && (m.intersects(monster)) && (doFixPosition == false)) {
                        if (m.getBoundary().getMinY() < monster.getBoundary().getMinY()) {
                            if ((m.getBoundary().getMaxY() > monster.getBoundary().getMinY()) && (m.getBoundary().getMinY() < monster.getBoundary().getMinY()) && (m.intersects(monster)) && (doFixPosition == false)) {
                                dy = m.getBoundary().getMinY() + m.getHeight();
                                doFixPosition = true;
                            }
                            if ((m.getBoundary().getMinY() < monster.getBoundary().getMaxY()) && (m.getBoundary().getMinY() > monster.getBoundary().getMinY()) && (m.intersects(monster)) && (doFixPosition == false)) {
                                dy = m.getBoundary().getMinY() - m.getHeight();
                                doFixPosition = true;
                            }
                        } else if (m.getBoundary().getMaxY() > monster.getBoundary().getMaxY()) {
                            if ((m.getBoundary().getMinY() < monster.getBoundary().getMaxY()) && (m.getBoundary().getMinY() > monster.getBoundary().getMinY()) && (m.intersects(monster)) && (doFixPosition == false)) {
                                dy = m.getBoundary().getMinY() - m.getHeight();
                                doFixPosition = true;
                            }
                            if ((m.getBoundary().getMaxY() > monster.getBoundary().getMinY()) && (m.getBoundary().getMinY() < monster.getBoundary().getMinY()) && (m.intersects(monster)) && (doFixPosition == false)) {
                                dy = m.getBoundary().getMinY() + m.getHeight();
                                doFixPosition = true;
                            }
                        } else {
                            dx = m.getBoundary().getMinX() + m.getWidth();
                            doFixPosition = true;
                        }
                    }

                    if ((m.getBoundary().getMinY() < monster.getBoundary().getMaxY()) && (m.getBoundary().getMinY() > monster.getBoundary().getMinY()) && (m.intersects(monster)) && (doFixPosition == false)) {
                        dy = m.getBoundary().getMinY() - m.getHeight();
                        doFixPosition = true;
                    }

                    if ((m.getBoundary().getMaxY() > monster.getBoundary().getMinY()) && (m.getBoundary().getMinY() < monster.getBoundary().getMinY()) && (m.intersects(monster)) && (doFixPosition == false)) {
                        dy = m.getBoundary().getMinY() + m.getHeight();
                    }
                    monster.setPosition(dx, dy);
                }
            }
            monster.setPosition(dx, dy);
        }
    }
}
