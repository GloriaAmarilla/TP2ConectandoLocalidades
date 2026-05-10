package testUnitarios;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import logica.Arista;
import logica.Localidad;

public class AristasTest {

	@Test
	public void testAristaValida() {
        Localidad origen = new Localidad("A", "BsAs", 0, 0);
        Localidad destino = new Localidad("B", "BsAs", 1, 1);
        Arista arista = new Arista(origen, destino, 100);
        assertTrue(arista.esValida());
    }

    @Test
    public void testAristaConOrigenNull() {
        Localidad destino = new Localidad("B", "BsAs", 1, 1);
        Arista arista = new Arista(null, destino, 100);
        assertFalse(arista.esValida());
    }

    @Test
    public void testAristaConDestinoNull() {
        Localidad origen = new Localidad("A", "BsAs", 0, 0);
        Arista arista = new Arista(origen, null, 100);
        assertFalse(arista.esValida());
    }
    
}
