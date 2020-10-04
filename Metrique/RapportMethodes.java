package Metrique;

import java.util.ArrayList;

/**
 * Par :    Sophie Parent
 *			Adrien Adam
 *
 * Date :   3 octobre 2020
 *
 * Objet servant pour la création du rapport des métriques pour les méthodes.
 */
public class RapportMethodes {
    /**
     * Path
     */
    public final String path;

    /**
     * Nom de la classe
     */
    public final String parentPath;

    /**
     * Nom de la méthode
     */
    public final String methodName;

    /**
     * Nombre de lignes de code de la méthode
     */
    public final int mLOC;

    /**
     * Nombre de lignes de code de la méthode avec des commentaires
     */
    public final int mCLOC;

    /**
     * Densité de commentaires d'une méthode :  DC = mCLOC / mLOC
     */
    private double getDC() {
        return (double) mCLOC / mLOC;
    }
    
    /**
     * Constructeur
     */
    public RapportMethodes(String path, String parentPath, String className, int mLOC, int mCLOC, int jdLOC) {
        this.path = path;
        this.parentPath = parentPath;
        this.methodName = className;
        this.mLOC = mLOC + jdLOC;
        this.mCLOC = mCLOC + jdLOC;
    }

    /**
     * Titre des colonnes
     */
    public static String getHeader() {
        return "chemin, class, methode1, methode_LOC, methode_CLOC, methode_DC";
    }

    @Override
    public String toString() {
        var out = new ArrayList<String>() {
        	{
	            add(path);
	            add(parentPath);
	            add(methodName);
	            add("" + mLOC);
	            add("" + mCLOC);
	            add("" + getDC());
        	}
        };
        return String.join(",", out);
    }
}
