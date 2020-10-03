package Metrique;
import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.FileSystems;
import java.nio.file.NoSuchFileException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;
import java.util.stream.Stream;

import static java.lang.System.err;
//

/**
 *
 * <p>
 * Par :    Sophie Parent
 * <p>
 * Date :   2 octobre 2020
 *
 * Calcule les métriques d'un dossier pour un projet java.
 */
public final class Main {
    /**
     * @param folder dossier de racine
     * Liste récursivement les fichiers et répertoires d'un dossier.
     */
    private static Stream<String> findJavaFilesFromDirectory(final File folder) throws IOException {
        var matcher = FileSystems.getDefault().getPathMatcher("glob:**/*.java");
        return Files.walk(folder.toPath())
            .filter(matcher::matches)
            .map(Path::toString);
    }

    /**
     * @param path Source d'un projet java. Un fichier ou un répertoire
     * @return liste de tout les fichiers.
     */
    private static Stream<String> getFileList(String path) throws IOException {
        var folder = new File(path);

        // Retourne le chemin absolu d’un fichier
        // Si c'est un répertoire, on trouve ses fichiers
        return folder.isFile() ? Stream.of(folder.getAbsolutePath()) : findJavaFilesFromDirectory(folder);
    }

    /**
     * Argument : le chemin d’accès au dossier du projet ou du fichier .java
     * @param args Command line arguments
     * @throws IOException IO errors
     */
    public static void main(String[] args) throws Exception {
        var metrique = new Metrique();
        var source = args.length == 0 ? "" : args[0];

        if (source.isEmpty()) {
            throw new IllegalArgumentException("Vous devez fournir un chemin.");
        }

        try{
            for (String path : getFileList(source).toArray(String[]::new)) {
            	//Cherche les informations et calculs
            	metrique.visitFile(path);
            }
        }catch (NoSuchFileException e) {
            err.println("Erreur d’ouverture du fichier : " + source);
        }


    }
}
