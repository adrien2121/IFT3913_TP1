package Metrique;

import com.github.javaparser.ParseProblemException;
import com.github.javaparser.StaticJavaParser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import static java.lang.System.err;

/**
 *
 * Par :    Sophie Parent
 * 			Adrien Adam
 * 
 * Date :   2 octobre 2020
 */
public final class Metrique {
    
    private VisitorState infos = new VisitorState();

	/**
     * Store le nom du path, et les informations pour les rapports de classes et méthodes.
     * Passé au visiteur qui popule les informations pour les rapports.
     */
    public static class VisitorState {
        public String currentFilePath; // Nom fichier
        public List<RapportClasses> rClasses = new ArrayList<>(); //Infos du rapport pour les classes
        public List<RapportMethodes> rMethodes = new ArrayList<>(); //Infos du rapport pour les méthodes
    }
    
	/**
     * Visiter un fichier de code Java et ajoute les données de métriques.
     * @param path Path du fichier Java.
     */
    void visitFile(String path) throws ParseProblemException, IOException {
        try (var scanner = new Scanner(new File(path))) {
            var source = new StringBuilder();
            while (scanner.hasNextLine()) {
                var line = scanner.nextLine();
                if (!line.trim().isEmpty()) {
                    source.append(line).append("\n");
                }
            }

            try {
            	
            	//Insere nom du fichier dans les infos
            	infos.currentFilePath = path;
            	
                // parse le fichier courant
                var cu = StaticJavaParser.parse(source.toString().trim());
                
                var visiteur = new Visiteur();

                visiteur.visit(cu, infos);
            } catch (ParseProblemException e) {
                err.println("Erreur de parsing du fichier : " + path);
                throw e;
            }
        } catch (FileNotFoundException e) {
            err.println("Erreur ouverture du fichier : " + path);
            throw e;
        }
    }
    
	/**
     * Forme la String qui servira pour creer le fichier rapport des classes.
     */
    String getClassReport() {
        var res = new StringBuilder();
        //Entete
        res.append(RapportClasses.getHeader()).append("\n");
        //Lignes
        for (var report: infos.rClasses) {
            res.append(report).append("\n");
        }
        return res.toString();
    }

	/**
     * Forme la String qui servira pour creer le fichier methodes des classes.
     */
    String getMethodReports() {
        var res = new StringBuilder();
        //Entete
        res.append(RapportMethodes.getHeader()).append("\n");
        //Lignes
        for (var report: infos.rMethodes) {
            res.append(report).append("\n");
        }
        return res.toString();
    }
    
	
}