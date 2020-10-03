package Metrique;

import com.github.javaparser.ParseProblemException;
import com.github.javaparser.StaticJavaParser;
import Metrique.Visiteur;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import static java.lang.System.err;

/**
 *
 * <p>
 * Par :    Sophie Parent
 * <p>
 * Date :   2 octobre 2020
 */
public final class Metrique {
    
	/**
     * Visiter un fichier de code Java et ajoute les données de métriques.
     * @param path Path to a Java file.
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
                // parse le fichier courant
                var cu = StaticJavaParser.parse(source.toString().trim());

                var visiteur = new Visiteur();

                visiteur.visit(cu, path);
            } catch (ParseProblemException e) {
                err.println("Erreur lors du parsing du fichier : " + path);
                throw e;
            }
        } catch (FileNotFoundException e) {
            err.println("Erreur d’ouverture du fichier : " + path);
            throw e;
        }
    }
	
}