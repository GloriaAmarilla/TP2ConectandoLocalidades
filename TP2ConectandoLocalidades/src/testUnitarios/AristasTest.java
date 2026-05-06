package testUnitarios;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import logica.Arista;
import logica.Localidad;

public class AristasTest {

	@Test
	public void testAristaValida() {
        Localidad a = new Localidad("A", "BsAs", 0, 0);
        Localidad b = new Localidad("B", "BsAs", 1, 1);
        Arista arista = new Arista(a, b, 100);
        assertTrue(arista.esValida());
    }

    @Test
    public void testAristaConOrigenNull() {
        Localidad b = new Localidad("B", "BsAs", 1, 1);
        Arista arista = new Arista(null, b, 100);
        assertFalse(arista.esValida());
    }

    @Test
    public void testAristaConDestinoNull() {
        Localidad a = new Localidad("A", "BsAs", 0, 0);
        Arista arista = new Arista(a, null, 100);
        assertFalse(arista.esValida());
    }
    
}
