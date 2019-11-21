package com.Ukasz09.ValentineGame.graphicModule.texturesPath;

public class CreatureSheetProperty extends ImageSheetProperty {
    private KindOfState attack;

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public CreatureSheetProperty(String imagePath, double widthOfOneFrame, double heightOfOneFrame) {
        super(imagePath, widthOfOneFrame, heightOfOneFrame);
        attack = null;
    }

    public void setAttack(double minX, double maxX, double minY, double maxY) {
        attack = new KindOfState(minX, maxX, minY, maxY);
    }

    public KindOfState getAttack() {
        if (attack == null)
            throw new UnsupportedOperationException();
        return attack;
    }
}
