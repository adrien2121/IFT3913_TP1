package Metrique;

import java.util.ArrayList;

/**
 * Par :    Sophie Parent
 *			Adrien Adam
 *
 * Date :   3 octobre 2020
 *
 * Objet servant pour la création du rapport des métriques pour les classes.
 */
public class RapportClasses {
    /**
     * Path
     */
    private final String path;

    /**
     * Nom de la classe
     */
    private final String className;

    /**
     * Nombre de lignes de code de la classe
     */
    private final int cLOC;

    /**
     * Nombre de lignes de code de la classe qui avec des commentaires
     */
    private final int cCLOC;


    /**
     * Constructeur
     */
    public RapportClasses(String path, String className, int cLOC, int cCLOC, int jdLOC) {
        this.path = path;
        this.className = className;
        this.cLOC = cLOC + jdLOC;
        this.cCLOC = cCLOC + jdLOC;
    }

    /**
     * Titre des colonnes
     */
    public static String getHeader() {
        return "chemin, class, classe_LOC, classe_CLOC, classe_DC";
    }

    @Override
    public String toString() {
        var out = new ArrayList<String>() {{
            add(path);
            add(className);
            add("" + cLOC);
            add("" + cCLOC);
        }};

        return String.join(",", out);
    }
}
