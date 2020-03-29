# Mode d'emploi
- [Mode d'emploi](#mode-demploi)
  - [Installation](#installation)
  - [Utilisation](#utilisation)
  - [Résumé du projet](#r%c3%a9sum%c3%a9-du-projet)
  - [Images du projet](#images-du-projet)

## Installation
Mode d'emploi d'installation:
- installez les fichiers SQL dans /projet/models/fichier SQL/ sur votre base de données
- changez le fichier /projet/models/Config_bd.java avec vos identifiants de votre base de données
## Utilisation
Allez dans le répertoire projet<br /> 
Sous windows:
```bash
del /s *.class
```
```bash
javac .\Main.java
```
```bash
java -cp ".;models/mariadb-client.jar" Main
```
Sous linux:
```bash
make
```
## Résumé du projet
Le projet avait pour but de créer une application de gestion de jardin en suivant le modèle MVC. Les données sont sauvegardées via une base de données. L'utilisateur peut en premier créer un jardin et y accéder. Il peut ensuite parcourir son jardin, le séparer en parcelles, y planter des légumes, effectuer des actions sur ses parcelles, ajouter de nouveaux légumes. Toutes les actions effectuées sur le jardin sont récapitulées sur un calendrier

## Images du projet
| ![https://github.com/aurelien-castel/DUT-Oct-2019-Jardin/blob/master/images/1.PNG?raw=true](https://github.com/aurelien-castel/DUT-Oct-2019-Jardin/blob/master/images/1.PNG?raw=true) | 
|:--:| 
| Création jardin |

| ![https://github.com/aurelien-castel/DUT-Oct-2019-Jardin/blob/master/images/2.PNG?raw=true](https://github.com/aurelien-castel/DUT-Oct-2019-Jardin/blob/master/images/2.PNG?raw=true) | 
|:--:| 
| Navigation dans les différents jardins |

| ![https://github.com/aurelien-castel/DUT-Oct-2019-Jardin/blob/master/images/3.PNG?raw=true](https://github.com/aurelien-castel/DUT-Oct-2019-Jardin/blob/master/images/3.PNG?raw=true) | 
|:--:| 
| Fenêtre principale sur un jardin |

| ![https://github.com/aurelien-castel/DUT-Oct-2019-Jardin/blob/master/images/4.PNG?raw=true](https://github.com/aurelien-castel/DUT-Oct-2019-Jardin/blob/master/images/4.PNG?raw=true) | 
|:--:| 
| Calendrier des différentes actions d'un jardin |

| ![https://github.com/aurelien-castel/DUT-Oct-2019-Jardin/blob/master/images/5.PNG?raw=true](https://github.com/aurelien-castel/DUT-Oct-2019-Jardin/blob/master/images/5.PNG?raw=true) | 
|:--:| 
| Le récapitulatif des actions effectuées pour une certaine date sur un jardin |