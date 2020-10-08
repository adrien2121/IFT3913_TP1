package Metrique;

import java.util.ArrayList;

/**
 * Par :    Sophie Parent
 *			Adrien Adam
 *
 * Date :   3 octobre 2020
 *
 * Objet servant pour la cr�ation du rapport des m�triques pour les classes.
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
     * Densit� de commentaires d'une classe : cDC = cCLOC / cLOC
     */
    private double getDC() {
        return (double) cCLOC / cLOC;
    }

    /**
     * Somme ponderee des complexites des methodes dune classe
     */
    private final int wmc;

    /**
     * Degre selon lequel une classe est bien commentee
     */
    private double getBC() {return (double) getDC() / wmc; }

    /**
     * Constructeur
     */
    public RapportClasses(String path, String className, int cLOC, int cCLOC, int jdLOC, int wmc) {
        this.path = path;
        this.className = className;
        this.cLOC = cLOC + jdLOC;
        this.cCLOC = cCLOC + jdLOC;
        this.wmc = wmc;
    }

    /**
     * Titre des colonnes
     */
    public static String getHeader() {
        return "chemin, class, classe_LOC, classe_CLOC, classe_DC, WMC, classe_BC";
    }

    @Override
    public String toString() {
        var out = new ArrayList<String>() {{
            add(path);
            add(className);
            add("" + cLOC);
            add("" + cCLOC);
            add("" + getDC());
            add("" + wmc);
            add("" + getBC());
        }};

        return String.join(",", out);
    }
}
