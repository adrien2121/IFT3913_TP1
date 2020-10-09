
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;


import java.util.concurrent.atomic.AtomicInteger;

/**
 * Par :    Sophie Parent
 *			Adrien Adam
 *
 * Date :   4 octobre 2020
 *
 * Visiteur pour les classes.
 * Ce visiteur calcule le WMC. (Weighted methods per class).
 * Pour la classe, ce visiteur apelle la visiteur de méthodes pour chaque méthode de la classe
 * et additione les CC de chaque méthodes pour retourner le WMC.
 * 
 */
public class VisiteurClasse extends VoidVisitorAdapter<Metrique.VisitorState> {
    private AtomicInteger wmc = new AtomicInteger(0);

    @Override
    public void visit(MethodDeclaration dec, Metrique.VisitorState infos) {
    	
    	// Apelle le visiteur de méthodes pour chaque méthodes.
        var cc = new AtomicInteger(1);
        var ccVisiteur = new VisiteurMethode();
        ccVisiteur.visit(dec, cc);
        
        wmc.addAndGet(cc.get());
        
    }
    
    /**
     * Retourne le WMC.
     * 
     */
    public int getWMC() {
        return wmc.get();
    }
}