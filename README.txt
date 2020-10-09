Noms :
------------------------------------------------------------------
Sophie Parent
Adrien Adam


Date :
------------------------------------------------------------------
Octobre 2020


Github:
------------------------------------------------------------------
https://github.com/adrien2121/IFT3913_TP1


Compilation :
------------------------------------------------------------------
Ceci est un projet Maven fait avec IntelliJ et utilise JDK11.

- Il faut importer ce projet en tant que projet Maven. Ensuite s'assurer que Maven a été cherché les dépendences requises.
Pour ce faire, dans la structure du projet, faire un clic droit et choisir : Maven -> Reload Project

- On peut ensuite exécuter le projet dans IntelliJ en fesait run -> Run(main)
Mais comme on doit spécifier un argument, on peut le faire dans : Run... -> Edit configuration
Et ajouter le chemin dans "Program arguments" avant de faire run -> Run(main).

- Pour la création du JAR exécutable, on a créer un artifact comme suit :
File -> Project Structure -> Artifacts -> + > JAR -> From modules with dependencies.
Ensuite il faut indiquer la classe Main (qui est Main dans notre cas).
S'assurer que "Extract to the target JAR" est sélectionné.
On doit changer le path du manifest pour :
<project folder>\src\main\resources
au lieu de :
<project folder>\src\main\java

Ensuite, On fait : Build -> Build Artifacts -> Metrique.jar
Le jar ce trouve ensuite dans le fichier out/artifacts du dossier du projet.


Exécution du JAR : 
------------------------------------------------------------------
java -jar Metrique.jar <chemin>

Ex: java -jar Metrique.jar C:\Users\mystn\IdeaProjects\untitled

Les fichiers SCV se trouveront dans le dossier du JAR.