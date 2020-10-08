package Metrique;

import com.github.javaparser.ast.stmt.*;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Par :    Sophie Parent
 *			Adrien Adam
 *
 * Date :   4 octobre 2020
 *
 * Visiteur pour les m�thodes. A chaque noeud pr�dicat trouv�, On incr�mente le compteur pass�
 * en parametre. Donc, ce compteur retourne le nombre de noeuds pr�dicats de la m�thode.
 * 
 */
class VisiteurMethode extends VoidVisitorAdapter<AtomicInteger> {
    @Override
    public void visit(IfStmt dec, AtomicInteger count) {
        super.visit(dec, count);
        count.incrementAndGet();
    }

    @Override
    public void visit(WhileStmt dec, AtomicInteger count) {
        super.visit(dec, count);
        count.incrementAndGet();
    }

    @Override
    public void visit(ForStmt dec, AtomicInteger count) {
        super.visit(dec, count);
        count.incrementAndGet();
    }

    @Override
    public void visit(DoStmt dec, AtomicInteger count) {
        super.visit(dec, count);
        count.incrementAndGet();
    }

    @Override
    public void visit(TryStmt dec, AtomicInteger count) {
        super.visit(dec, count);
        count.incrementAndGet();
    }

    @Override
    public void visit(SwitchStmt dec, AtomicInteger count) {
        super.visit(dec, count);
        count.addAndGet(dec.getEntries().size());
    }
}
