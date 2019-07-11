package com.Ukasz09.ValentineGame.gameModules.utilitis.wrappers;

public class IntValue {
    private int value;

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public IntValue(int value) {
        this.value = value;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public void decValue(int decAmount) {
        this.value -= decAmount;
    }

    public void incValue(int value) {
        this.value += value;
    }

    public void incValue() {
        value++;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
