package Metrique;

import com.github.javaparser.Range;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.EnumDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.comments.JavadocComment;
import com.github.javaparser.ast.nodeTypes.NodeWithJavadoc;
import com.github.javaparser.ast.nodeTypes.NodeWithSimpleName;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
//import metrics.ParserUtil;
//import metrics.reports.ClassReport;
//import metrics.reports.MethodReport;

import java.util.Optional;
import java.util.WeakHashMap;
import java.util.concurrent.atomic.AtomicInteger;

//import static metrics.Metrics.VisitorState;
import static java.lang.System.out;
/**

 */
public class Visiteur extends VoidVisitorAdapter<String> {
	
	

	//private Visiteur() { };
    @Override
    public void visit(MethodDeclaration method, String path) {
        super.visit(method, path);

        
        System.out.println(path + " " + getMethodParentPath(method) + " " + method.getNameAsString() + " " + getNodeLineCount(method) + " " + getCommentLineCount(method) + " " + getJavaDocCommentLineCount(method.getJavadocComment()));

    }

    @Override
    public void visit(EnumDeclaration dec, String path) {
        super.visit(dec, path);
        visitClassLike(dec, path, 0);
    }

    @Override
    public void visit(ClassOrInterfaceDeclaration dec, String path) {
        super.visit(dec, path);
//System.out.println("#########################################");


    }

    /**
     * Used for visiting Class, Interface and Enums.
     */
    private void visitClassLike(Node declaration, String path, int wmc) {
        var className = ((NodeWithSimpleName) declaration).getNameAsString();
        var javadoc = ((NodeWithJavadoc<Node>) declaration).getJavadocComment();


        
        System.out.println(path + " " + className + " " + getNodeLineCount(declaration) + " " + getCommentLineCount(declaration) + " " + getJavaDocCommentLineCount(javadoc));


    }
    
    public static String getMethodParentPath(MethodDeclaration method) {
        return method.getParentNode()
                .filter(parent -> parent instanceof ClassOrInterfaceDeclaration)
                .flatMap(parent -> ((ClassOrInterfaceDeclaration) parent).getFullyQualifiedName())
                .orElse("");
    }

    public static int getNodeLineCount(Node node) {
        return node.getRange().map(Range::getLineCount).orElse(0);
    }

    private static int getRangeLine(Optional<Range> range) {
        return range.map(Range::getLineCount).orElse(0);
    }

    public static int getJavaDocCommentLineCount(Optional<JavadocComment> comment) {
        return getRangeLine(comment.flatMap(Node::getRange));
    }

    public static int getCommentLineCount(Node node) {
        var comments = node.getAllContainedComments();
        return comments.stream()
            .map(Node::getRange)
            .map(Visiteur::getRangeLine)
            .mapToInt(Integer::intValue)
            .sum();
    }
    
}
