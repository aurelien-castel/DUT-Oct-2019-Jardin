### VARIABLES ###
JFLAGS=-encoding UTF8 -implicit:none
JC=javac
JVM=java -cp ".;models/mariadb-client.jar"
APP_NAME=Jardin

.SUFFIXES: .java .class

.java.class:
	$(JC) $(JFLAGS) $*.java

JFILE= \
		view/Fenetre.class \
		view/RadioCombo.class \
		view/VueAccueil.class \
		view/VueAddNewJardin.class \
		view/VueAddNewLegume.class\
		view/VueHistorique.class \
		view/VueMenu.class \
		view/VuenavHistorique.class \
		view/VuenavParcelle.class \
		view/VuepanelHistorique.class \
		view/VuepanelParcelle.class \
		view/VueParcelle.class \
		controller/ControlAccueil.class \
		controller/ControlAddNewJardin.class \
		controller/ControlAddNewLegume.class \
		controller/ControlnavHist.class \
		controller/ControlnavPar.class \
		controller/ControlpanelHistorique.class \
		controller/ControlpanelParcelle.class \
		models/AbstractActionFactory.class \
		models/AbstractJardinFactory.class \
		models/AbstractLegumeFactory.class \
		models/Action.class \
		models/ActionFactoryBD.class \
		models/ActionLegume.class \
		models/ActionLegumeType.class \
		models/ActionParcelle.class \
		models/ActionParcelleType.class \
		models/ActionSol.class \
		models/ActionSolType.class \
		models//Config_bd.class \
		models/FamilleLegume.class \
		models/JardinFactoryBD.class \
		models/Legume.class \
		models/LegumeBD.class \
		models/LegumeFactoryBD.class \
		models/MethodeBD.class \
		models/Orientation.class \
		models/Parcelle.class \
		models/ParcelleBD.class \
		models/ParcelleJ.class\
		Main.class \

	

### BUT PAR DEFAUT ###
MAIN = Main
classes: $(JFILE:.java=.class)

run : classes
	$(JVM) $(MAIN)

### FIN ###