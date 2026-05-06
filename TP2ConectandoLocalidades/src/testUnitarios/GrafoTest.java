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
    @Test
    public void testCostoBasico() {
        Grafo grafo = new Grafo(10, 0, 0);

        Localidad a = new Localidad("A", "X", 0, 0);
        Localidad b = new Localidad("B", "X", 0, 3); // distancia = 3

        grafo.agregarLocalidad(a);
        grafo.agregarLocalidad(b);

        List<Arista> agm = grafo.prim();

        double costo = agm.get(0).getCosto();

        assertEquals(30.0, costo, 0.01); // 3 * 10
    }
    @Test
    public void testCostoConPorcentajeExtra() {
        Grafo grafo = new Grafo(1, 10, 0);

        Localidad a = new Localidad("A", "X", 0, -150);
        Localidad b = new Localidad("B", "X", 0, 160); 

        grafo.agregarLocalidad(a);
        grafo.agregarLocalidad(b);

        List<Arista> agm = grafo.prim();

        double costo = agm.get(0).getCosto();

        assertEquals(310*1.1, costo, 0.01);
    }
    @Test
    public void testCostoInterprovincial() {
        Grafo grafo = new Grafo(1, 0, 50);

        Localidad a = new Localidad("A", "BsAs", 0, 0);
        Localidad b = new Localidad("B", "Cordoba", 0, 10);

        grafo.agregarLocalidad(a);
        grafo.agregarLocalidad(b);

        List<Arista> agm = grafo.prim();

        double costo = agm.get(0).getCosto();

        // distancia = 10 + 50 fijo
        assertEquals(60.0, costo, 0.01);
    }
    @Test
    public void testEligeMenorCosto() {
        Grafo grafo = new Grafo(1, 0, 0);

        Localidad a = new Localidad("A", "X", 0, 0);
        Localidad b = new Localidad("B", "X", 0, 10);
        Localidad c = new Localidad("C", "X", 0, 1);

        grafo.agregarLocalidad(a);
        grafo.agregarLocalidad(b);
        grafo.agregarLocalidad(c);

        List<Arista> agm = grafo.prim();

        // debería incluir la conexión corta (A-C)
        boolean existeCorta = agm.stream().anyMatch(ar ->
            (ar.getOrigen().equals(a) && ar.getDestino().equals(c)) ||
            (ar.getOrigen().equals(c) && ar.getDestino().equals(a))
        );

        assertTrue(existeCorta);
    }
}