package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import currencyConverter.Currency;


class CurrencyTest {

	//private ArrayList<Currency> currencies = Currency.init();

	//Test partie 2 : T2 (Couverture des instructions)
    @Test
    void t2() {
        assertEquals(50.0, Currency.convert(100.0, 0.5));
    }

}