package Metrique;

import java.util.ArrayList;

/**
 * Par :    Sophie Parent
 *			Adrien Adam
 *
 * Date :   3 octobre 2020
 *
 * Objet servant pour la cr�ation du rapport des m�triques pour les m�thodes.
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
     * Nom de la m�thode
     */
    public final String methodName;

    /**
     * Nombre de lignes de code de la m�thode
     */
    public final int mLOC;

    /**
     * Nombre de lignes de code de la m�thode avec des commentaires
     */
    public final int mCLOC;


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
        	}
        };
        return String.join(",", out);
    }
}
