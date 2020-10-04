package Metrique;

import com.github.javaparser.Range;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.body.CallableDeclaration.Signature;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.EnumDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.comments.JavadocComment;
import com.github.javaparser.ast.nodeTypes.NodeWithJavadoc;
import com.github.javaparser.ast.nodeTypes.NodeWithSimpleName;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import static Metrique.Metrique.VisitorState;
import java.util.Optional;
import java.util.WeakHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import static java.lang.System.out;
/**
 * Par :    Sophie Parent
 *			Adrien Adam
 *
 * Date :   3 octobre 2020
 *
 * Visiteur pour un fichier Java en tant qu’AST.
 * Remplit les rapports dans le VisitorState (Classes et methodes)
 */
public class Visiteur extends VoidVisitorAdapter<VisitorState> {
	


    @Override
    public void visit(MethodDeclaration method, VisitorState infos) {
        super.visit(method, infos);

        
        System.out.println(infos.currentFilePath + " " + getMethodParentPath(method) + " " + (method.getParameters().isEmpty()?method.getNameAsString():getMethodSignature(method.getSignature())) + " " + getNodeLineCount(method) + " " + getCommentLineCount(method) + " " + getJavaDocCommentLineCount(method.getJavadocComment()));


    }

    @Override
    public void visit(EnumDeclaration dec, VisitorState infos) {
        super.visit(dec, infos);
        visitClassLike(dec, infos, 0);
    }

    @Override
    public void visit(ClassOrInterfaceDeclaration dec, VisitorState infos) {
        super.visit(dec, infos);
        
System.out.println("#########################################");



    }

    /**
     * Used for visiting Class, Interface and Enums.
     */
    private void visitClassLike(Node declaration, VisitorState infos, int wmc) {
        var className = ((NodeWithSimpleName) declaration).getNameAsString();
        var javadoc = ((NodeWithJavadoc<Node>) declaration).getJavadocComment();

        
        System.out.println(infos.currentFilePath + " " + className + " " + getNodeLineCount(declaration) + " " + getCommentLineCount(declaration) + " " + getJavaDocCommentLineCount(javadoc));


    }
    
    /**
     * Nom du parent de la methode
     */
    public static String getMethodParentPath(MethodDeclaration method) {
        return method.getParentNode()
                .filter(parent -> parent instanceof ClassOrInterfaceDeclaration)
                .flatMap(parent -> ((ClassOrInterfaceDeclaration) parent).getFullyQualifiedName())
                .orElse("");
    }
    
    /**
     * Nom de la methode avec signature
     */
    public static String getMethodSignature(Signature signature) {
    	
        return signature.toString().replace(", ","_").replace("(","_").replace(")", "");
    }

    /**
     * Pour le calcul du nombre de lignes de commentaires
     */
    public static int getNodeLineCount(Node node) {
        return node.getRange().map(Range::getLineCount).orElse(0);
    }

    /**
     * Pour le calcul du nombre de lignes de commentaires
     */
    private static int getRangeLine(Optional<Range> range) {
        return range.map(Range::getLineCount).orElse(0);
    }

    /**
     * Nombre de lignes de commentaires de la javadoc
     */
    public static int getJavaDocCommentLineCount(Optional<JavadocComment> comment) {
        return getRangeLine(comment.flatMap(Node::getRange));
    }

    /**
     * Nombre de lignes de commentaires
     */
    public static int getCommentLineCount(Node node) {
        var comments = node.getAllContainedComments();
        return comments.stream()
            .map(Node::getRange)
            .map(Visiteur::getRangeLine)
            .mapToInt(Integer::intValue)
            .sum();
    }
    
}
