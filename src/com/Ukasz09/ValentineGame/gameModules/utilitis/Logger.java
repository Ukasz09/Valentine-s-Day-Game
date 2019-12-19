package com.Ukasz09.ValentineGame.gameModules.utilitis;

public class Logger {

    public static void logError(Class actualClass, String errorMsg) {
        System.out.println(actualClass.getSimpleName() + " - with error: " + errorMsg);
    }
}
