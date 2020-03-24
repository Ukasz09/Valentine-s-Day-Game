#!/usr/bin/bash 

$1/bin/java --module-path /usr/lib/jvm/$1/lib/javafx.base.jar:\
$1/lib/javafx.controls.jar:\
$1/lib/javafx.fxml.jar:\
$1/lib/javafx.graphics.jar:\
$1/lib/javafx.media.jar:\
$1/lib/javafx.swing.jar:\
$1/lib/javafx.web.jar:\
$1/lib/javafx-swt.jar:\
$1/lib/javafx.base.jar:\
$1/lib/javafx.controls.jar:\
$1/lib/javafx.fxml.jar:\
$1/lib/javafx.graphics.jar:\
$1/lib/javafx.media.jar:\
$1/lib/javafx.swing.jar:\
$1/lib/javafx.web.jar:\
$1/lib/javafx-swt.jar --add-modules ALL-MODULE-PATH -jar ValentinesGame.jar
