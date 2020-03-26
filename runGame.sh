#!/usr/bin/bash
# Copyright (C) 2020 Lukasz Gajerski <https://github.com/Ukasz09>

##### CONSTANTS
MIN_JAVA_VERSION=8

##### FUNCTIONS
check_version_correctness(){
	if [ "$JAVA_VERSION_NUMBER" -lt $MIN_JAVA_VERSION ]; then
		echo "Error:  Yout java version is to incorrect. You need at least java $MIN_JAVA_VERSION" 1>&2
		exit 1
	fi
}

find_java_version()
{
	JAVA_VERSION_NUMBER=$(java -version 2>&1 | grep -i version | cut -d'"' -f2 | cut -d'.' -f1-2)
	check_version_correctness
}

find_java_library_folder()
{ 
 find_java_version	
 if [[ -d /usr/lib/jvm/java-$JAVA_VERSION_NUMBER-oracle ]]; then
	JAVA_PATH=/usr/lib/jvm/java-$JAVA_VERSION_NUMBER-oracle
 elif [[ -d /usr/lib/jvm/java-$JAVA_VERSION_NUMBER ]]; then
	JAVA_PATH=/usr/lib/jvm/java-$JAVA_VERSION_NUMBER
 else 
	echo "Error: Not found java installation folder" 1>&2
	exit 1
 fi
}

##### FLAGS MAINTAINING
 for arg in "$@"
 do
    case $arg in
        -d|--default)
		find_java_library_folder
		shift;;
	-p=*|--path=*)
		JAVA_PATH="${arg#*=}"
		shift;;
    esac
 done

##### MAIN
$JAVA_PATH/bin/java --module-path $JAVA_PATH/lib/javafx.base.jar:\
$JAVA_PATH/lib/javafx.controls.jar:\
$JAVA_PATH/lib/javafx.fxml.jar:\
$JAVA_PATH/lib/javafx.graphics.jar:\
$JAVA_PATH/lib/javafx.media.jar:\
$JAVA_PATH/lib/javafx.swing.jar:\
$JAVA_PATH/lib/javafx.web.jar:\
$JAVA_PATH/lib/javafx-swt.jar:\
$JAVA_PATH/lib/javafx.base.jar:\
$JAVA_PATH/lib/javafx.controls.jar:\
$JAVA_PATH/lib/javafx.fxml.jar:\
$JAVA_PATH/lib/javafx.graphics.jar:\
$JAVA_PATH/lib/javafx.media.jar:\
$JAVA_PATH/lib/javafx.swing.jar:\
$JAVA_PATH/lib/javafx.web.jar:\
$JAVA_PATH/lib/javafx-swt.jar --add-modules ALL-MODULE-PATH -jar *.jar
