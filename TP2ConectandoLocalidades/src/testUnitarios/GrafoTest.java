package testUnitarios;

import logica.*;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.List;

public class GrafoTest {

    // agm tiene que tener 1 aristas menos que el total
    @Test
    public void cantidadAristasAGMTest() {
    	double costoPorKm = 10;
        double porcentajeExtra = 0;
        double costoInterprovincial = 0;
        Grafo grafo = new Grafo(costoPorKm, porcentajeExtra, costoInterprovincial);

        Localidad a = new Localidad("A", "X", 0, 0);
        Localidad b = new Localidad("B", "X", 0, 1);
        Localidad c = new Localidad("C", "X", 1, 0);
        grafo.agregarLocalidad(a);
        grafo.agregarLocalidad(b);
        grafo.agregarLocalidad(c);

        List<Arista> aristasRecibidas = grafo.prim();
        int cantidadDeAristasEsperadas = 2;

        assertEquals(cantidadDeAristasEsperadas, aristasRecibidas.size()); // 3 - 1
    }

    // da error con 1 localidad
    @Test(expected = IllegalStateException.class)
    public void pocasLocalidadesTest() {
    	double costoPorKm = 10;
        double porcentajeExtra = 0;
        double costoInterprovincial = 0;
        Grafo grafo = new Grafo(costoPorKm, porcentajeExtra, costoInterprovincial);

        Localidad inicial = new Localidad("A", "X", 0, 0);
        grafo.agregarLocalidad(inicial);

        grafo.prim();
    }

    // agm con 2 nodos
    @Test
    public void dosLocalidadesTest() {

    	double costoPorKm = 10;
        double porcentajeExtra = 0;
        double costoInterprovincial = 0;
        Grafo grafo = new Grafo(costoPorKm, porcentajeExtra, costoInterprovincial);
         
        grafo.agregarLocalidad(new Localidad("A", "X", 0, 0));
        grafo.agregarLocalidad(new Localidad("B", "X", 0, 1));

        List<Arista> agm = grafo.prim();

        assertEquals(1, agm.size());
    }
}