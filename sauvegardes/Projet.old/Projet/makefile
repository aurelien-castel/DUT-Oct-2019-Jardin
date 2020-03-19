JFLAGS=-implicit:none
JC=javac
JVM=java
APP_NAME=Jardin

.SUFFIXES: .java .class
.java.class:
	$(JC) $(JFLAGS) $*.java
	
CLASSES= \
		Main.java \

		view/Fenetre.java \
		view/RadioCombo.java \
		view/VueAccueil.java \
		view/VueAddNewJardin.java \
		view/VueHistorique.java \
		view/VueMenu.java \
		view/VuenavHistorique.java \
		view/VuenavParcelle.java \
		view/VuepanelHistorique.java \
		view/VuepanelParcelle.java \
		view/VueParcelle.java \

		controller/ControlAccueil.java \
		controller/ControlAddNewJardin.java \
		controller/ControlAddNewLegume.java \
		controller/ControlnavHist.java \
		controller/ControlnavPar.java \
		controller/ControlpanelHistorique.java \
		controller/ControlpanelParcelle.java \

		jardin/AbstractJardinFactory.java \
		jardin/AbstractLegumeFactory.java \
		jardin/Action.java \
		jardin/ActionLegume.java \
		jardin/ActionLegumeType.java \
		jardin/ActionSol.java \
		jardin/ActionSolType.java \
		jardin/Example.java \
		jardin/FamilleLegume.java \
		jardin/JardinFactoryJ.java \
		jardin/Legume.java \
		jardin/LegumeFactoryBD.java \
		jardin/LegumeFactoryJ.java \
		jardin/LegumeFactoryJ.java \
		jardin/LegumeJ.java \
		jardin/Orientation.java \
		jardin/Parcelle.java \
		jardin/ParcelleJ.java \

		models/Config_bd.java \
		models/JardinFactoryBD.java \
		models/LegumeFactoryBD.java \

		
MAIN = Main
default: compile

compile: $(CLASSES:.java=.class)

run: compile
	$(JVM) "-cp" models/mariadb-client.jar:. Main

clean: 
	$(RM) -f *.class
