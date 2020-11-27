package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import currencyConverter.Currency;
import currencyConverter.MainWindow;


class MainWindowTest {

	private ArrayList<Currency> currencies = Currency.init();

	//Test partie 2 : T1 (Couverture des instructions)
    @Test
    void t1() {
        assertEquals(93.0, MainWindow.convert("US Dollar", "Euro", currencies, 100.0));
    }
    
	//Test partie 2 : T3 (couverture des arcs du graphe de flot de contrôle)
    @Test
    void t3() {
    	assertEquals(93.0, MainWindow.convert("US Dollar", "Euro", currencies, 100.0));		// valide-valide
        assertEquals(0.0, MainWindow.convert("Invalide", "Invalide", currencies, 100.0));	// invalide-invalide
        assertEquals(0.0, MainWindow.convert("Invalide", "Euro", currencies, 100.0));		// invalide-valide
    }

}