# 💝 Valentine's Game [![License](https://img.shields.io/badge/licence-MIT-blue)](https://choosealicense.com/licenses/mit/) [![Contributions welcome](https://img.shields.io/badge/contributions-welcome-orange.svg)](https://github.com/Ukasz09/Valentines-Game) [![Status](https://img.shields.io/badge/status-finished-brightgreen)](https://github.com/Ukasz09/Valentines-Game)

My first simple 2D game, with dedication to my friend Kasia, who was inspiration for this project 
<br/>

💻 All resolutions support <br/>
🔉 Sounds and music <br/>

## Gameplay
<p align="center"><img width=95% src="https://raw.githubusercontent.com/Ukasz09/Valentines-Game/master/readmeImages/gameplay1.gif"></p>
<p align="center"><img width=95% src="https://raw.githubusercontent.com/Ukasz09/Valentines-Game/master/readmeImages/gameplay2.gif"></p>
<p align="center"><img width=95% src="https://raw.githubusercontent.com/Ukasz09/Valentines-Game/master/readmeImages/gameplay3.gif"></p>
<br/>

## Screenshots 
![alt text](https://raw.githubusercontent.com/Ukasz09/Valentines-Game/master/readmeImages/1.png)
![alt text](https://raw.githubusercontent.com/Ukasz09/Valentines-Game/master/readmeImages/2.png)
![alt text](https://raw.githubusercontent.com/Ukasz09/Valentines-Game/master/readmeImages/3.png)
![alt text](https://raw.githubusercontent.com/Ukasz09/Valentines-Game/master/readmeImages/4.png)

## Game controls
- `Arrows left/right`: flame shot
- `Space`: bomb shot
- `WSAD`: sprite control

## How to use it
If there is a problem with running, try to open it by console with command:
```cmd
java -jar ValentinesGame.jar
```

- Linux 

```bash
java -jar ValentinesGame.jar
```
If you will see errors about not having error like this:

```bash
java.lang.NoClassDefFoundError: javafx/application/Application
```
it means that you don't have javafx libraries and you need to follow this steps: <br/>
- Download javaFx libraries for linux [javafx-oracle.com](https://www.oracle.com/java/technologies/java-archive-javafx-downloads.html#javafx_sdk-1.3.1-oth-JPR)
- Unpack files into your java library destination, for example: `/usr/lib/jvm/java-14-oracle`
- Run script made by myself for you:
  - `-d` or `--default` flag: open with default java installation folder
  - `-p=JAVA_PATH` or `--path=JAVA_PATH` flag: open with custom java installation folder by JAVA_PATH     

Examples: <br/>

```bash
	./runGame.sh --default
	./runGame.sh -d
	./runGame.sh -p=/usr/lib/jvm/java-14-oracle
	./runGame.sh --path=/usr/lib/jvm/java-14-oracle
```

## Software design stuff
**Used Designs Patterns:**
<br/><br/>
✅ Builder <br/>
✅ Strategy <br/>

**Code overview:**
<br/><br/>
✔️ 48 classes (including enums and interfaces) <br/>
✔️ over 3600 lines of code <br/>

___
## 📫 Contact 
Created by <br/>
<a href="https://github.com/Ukasz09" target="_blank"><img src="https://avatars0.githubusercontent.com/u/44710226?s=460&v=4"  width="100px;"></a>
<br/> gajerski.lukasz@gmail.com - feel free to contact me! ✊
