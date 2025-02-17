package eu.htic.duquesgame.model;

import org.junit.Test;

import static org.junit.Assert.*;

public class DuquesNumberTest {

    @Test
    public void testValidDuquesNumber() {
        try {
            DuquesNumber duquesNumber = new DuquesNumber("0123");
            assertNotNull("El número generado no debe ser null", duquesNumber);
        } catch (Exception e) {
            throw new AssertionError(e);
        }
        try {
            DuquesNumber duquesNumber = new DuquesNumber("7890");
            assertNotNull("El número generado no debe ser null", duquesNumber);
        } catch (Exception e) {
            throw new AssertionError(e);
        }
    }

    @Test
    public void testInvalidDuquesNumberThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> new DuquesNumber("123")); //less than four digits
        assertThrows(IllegalArgumentException.class, () -> new DuquesNumber("1123")); //duplicated digit
        assertThrows(IllegalArgumentException.class, () -> new DuquesNumber("12a4")); //contains non digits
        assertThrows(IllegalArgumentException.class, () -> new DuquesNumber("12.4")); //contains non digits
        assertThrows(IllegalArgumentException.class, () -> new DuquesNumber("12345")); // more than four digits
    }

    @Test
    public void testNewDuquesNumberIsValid() {
        for (int i = 0; i < 50; i++) { // Repite el test 50 veces
            try {
                DuquesNumber duquesNumber = new DuquesNumber();
                assertNotNull("El número generado no debe ser null", duquesNumber);
            } catch (Exception e) {
                throw new AssertionError("La generación del número falló en la iteración " + i, e);
            }
        }
    }

}