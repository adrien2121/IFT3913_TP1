package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import currencyConverter.Currency;
import currencyConverter.MainWindow;


class MainWindowTest {

	private ArrayList<Currency> currencies = Currency.init();
	ArrayList<Currency> emptyArray = null;

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

    //Test partie 3 : T4 (Couverture des chemins indépendants du graphe de flot de contrôle)
    @Test
    void t4() {
        assertEquals(0, MainWindow.convert("US Dollar", "Euro", emptyArray, 100.0));
        assertEquals(0, MainWindow.convert("Invalide", "Invalide", currencies, 100.0));
        assertEquals(0, MainWindow.convert("Invalide", "Euro", currencies, 100.0));
        assertEquals(0, MainWindow.convert("US Dollar", "Euro", currencies, 100.0));
    }

    //Test partie 4 T5 (Couverture des i-chemins)
    @Test
    void t5() {
        assertEquals(0, MainWindow.convert("US Dollar", "Euro", emptyArray, 100.0));
        assertEquals(100.0, MainWindow.convert("US Dollar", "US Dollar", currencies, 100.0));
        assertEquals(93.0, MainWindow.convert("US Dollar", "Euro", currencies, 100.0));
        assertEquals(66.0, MainWindow.convert("US Dollar", "British Pound", currencies, 100.0));
        assertEquals(101.00, MainWindow.convert("US Dollar", "Swiss Franc", currencies, 100.0));
        assertEquals(636.00, MainWindow.convert("US Dollar", "Chinese Yuan Renminbi", currencies, 100.0));
        assertEquals(12354.00, MainWindow.convert("US Dollar", "Japanese Yen", currencies, 100.0));
        assertEquals(0, MainWindow.convert("US Dollar", "Invalide", currencies, 100.0));
    }

}