
# Jardins

## Contexte
Le projet concerne une version des jardins proche de celle entrevue en préprojet.
La seule différence fondamentale est que les parcelles ne sont pas conservées dans le temps : une parcelle fermée n'existera plus dans le système.



Pour les parcelles on dispose d'une API avec plusieurs classes abstraites et interfaces.
La grande majorité des classes concrètes proposées ne sont pas persistantes mais la machinerie abstraite vous permet d'utiliser vos propres classes qui permettraient cette persistance (par exemple avec une base de données). Un exemple partiel de connexion à une base de donné est donné pour les légumes.

- Parcelle                interface, élément central de l'API
- AbstractJardinFactory   Abstraite, API pour la création de la parcelle racine d'un nouveau jardin.
- Orientation             enum
- ParcelleJ               implantation en java (sans BD et sans persistance) de Parcelle.
- JardinFactoryJ          implantation en java (sans BD et sans persistance) de AbstractJardinFactory

C'est similaire pour les légumes.

- Legume                  interface
- AbstractLegumeFactory   abstrait, API pour la gestion des légumes 
- LegumeFactoryJ          implantation en java (sans BD et sans persistance) de AbstractLegumeFactory.
- LegumeJ                 implantation en java (sans BD et sans persistance) de Legume.
- LegumeFactoryBD         implantation partielle en java (avec BD et persistance) de AbstractLegumeFactory.
- FamilleLegume           enum
- Legumes.sql             code compatible avec LegumeFactoryBD proposant une table pour les légumes.

Les actions ne sont pas très compliquées.

- Action             abstraite
- ActionLegume       concrets
- ActionSol          concrets
- ActionLegumeType   enum
- ActionSolType      enum

