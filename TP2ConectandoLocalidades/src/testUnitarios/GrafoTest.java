package testUnitarios;

import logica.*;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.List;

public class GrafoTest {

    // agm tiene que tener 1 aristas menos que el total
    @Test
    public void testCantidadAristasAGM() {

        Grafo grafo = new Grafo(10, 0, 0);

        grafo.agregarLocalidad(new Localidad("A", "X", 0, 0));
        grafo.agregarLocalidad(new Localidad("B", "X", 0, 1));
        grafo.agregarLocalidad(new Localidad("C", "X", 1, 0));

        List<Arista> agm = grafo.prim();

        assertEquals(2, agm.size()); // 3 - 1
    }

    // costo es positivo
    @Test
    public void testCostoTotal() {

        Grafo grafo = new Grafo(1, 0, 0);

        grafo.agregarLocalidad(new Localidad("A", "X", 0, 0));
        grafo.agregarLocalidad(new Localidad("B", "X", 0, 1));

        List<Arista> agm = grafo.prim();

        double total = grafo.costoTotal(agm);

        assertTrue(total > 0);
    }

    // da error con 1 localidad
    @Test(expected = IllegalStateException.class)
    public void testPocasLocalidades() {

        Grafo grafo = new Grafo(10, 0, 0);

        grafo.agregarLocalidad(new Localidad("A", "X", 0, 0));

        grafo.prim();
    }



    // agm con 2 nodos
    @Test
    public void testDosLocalidades() {

        Grafo grafo = new Grafo(10, 0, 0);

        grafo.agregarLocalidad(new Localidad("A", "X", 0, 0));
        grafo.agregarLocalidad(new Localidad("B", "X", 0, 1));

        List<Arista> agm = grafo.prim();

        assertEquals(1, agm.size());
    }
}