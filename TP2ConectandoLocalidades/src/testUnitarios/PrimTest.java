package testUnitarios;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

import logica.Arista;
import logica.Grafo;
import logica.Localidad;

class PrimTest {

	 @Test
    public void costoBasicoTest() {
    	
        Localidad a = new Localidad("A", "X", 0, 0);
        Localidad b = new Localidad("B", "X", 0, 3); // distancia = 3
       
        double costoPorKm = 10;
        double porcentajeExtra = 0;
        double costoInterprovincial = 0;
        Grafo grafo = new Grafo(costoPorKm, porcentajeExtra, costoInterprovincial);
        grafo.agregarLocalidad(a);
        grafo.agregarLocalidad(b);

        List<Arista> agm = grafo.prim();

        double costoRecibido = agm.get(0).getCosto();
        double costoEsperado = 30.0; // 3 * 10
        double margenDeError = 0.01;
        
        assertEquals(costoEsperado, costoRecibido, margenDeError); 
    }
	    
    @Test
    public void costoConPorcentajeExtraTest() {
    	double costoPorKm = 1;
        double porcentajeExtra = 10;
        double costoInterprovincial = 0;
        Grafo grafo = new Grafo(costoPorKm, porcentajeExtra, costoInterprovincial);

        Localidad a = new Localidad("A", "X", 0, -150);
        Localidad b = new Localidad("B", "X", 0, 160); 

        grafo.agregarLocalidad(a);
        grafo.agregarLocalidad(b);

        List<Arista> aristasRecibidas = grafo.prim();

        double costoRecibido = aristasRecibidas.get(0).getCosto();
        double distanciaEntreLatitudes = 310;
        double costoDeDistancia = distanciaEntreLatitudes*costoPorKm;
        double porcentajeReal = 0.1;
        double costoEsperado = costoDeDistancia+(costoDeDistancia*porcentajeReal);
        double margenDeError = 0.01;

        assertEquals(costoEsperado, costoRecibido, margenDeError);
    }
	    
    @Test
    public void costoInterprovincialTest() {
    	double costoPorKm = 1;
        double porcentajeExtra = 0;
        double costoInterprovincial = 50;
        Grafo grafo = new Grafo(costoPorKm, porcentajeExtra, costoInterprovincial);

        Localidad a = new Localidad("A", "BsAs", 0, 0);
        Localidad b = new Localidad("B", "Cordoba", 0, 10);

        grafo.agregarLocalidad(a);
        grafo.agregarLocalidad(b);

        List<Arista> aristasRecibidas = grafo.prim();

        double costoRecibido = aristasRecibidas.get(0).getCosto();
        double distanciaEntreLatitudes = 10;
        double costoDeDistancia = distanciaEntreLatitudes*costoPorKm;
        double costoEsperado = costoDeDistancia+costoInterprovincial;
        double margenDeError = 0.01;

        assertEquals(costoEsperado, costoRecibido, margenDeError);
    }
	    
    @Test
    public void eligeMenorCostoTest() {
    	double costoPorKm = 1;
        double porcentajeExtra = 0;
        double costoInterprovincial = 0;
        Grafo grafo = new Grafo(costoPorKm, porcentajeExtra, costoInterprovincial);

        Localidad a = new Localidad("A", "X", 0, 0);
        Localidad b = new Localidad("B", "X", 0, 10);
        Localidad c = new Localidad("C", "X", 0, 1);

        grafo.agregarLocalidad(a);
        grafo.agregarLocalidad(b);
        grafo.agregarLocalidad(c);

        List<Arista> aristasRecibidas = grafo.prim();

        // debería incluir la conexión corta (A-C)
        boolean existeCorta = aristasRecibidas.stream().anyMatch(ar ->
            (ar.getOrigen().equals(a) && ar.getDestino().equals(c)) ||
            (ar.getOrigen().equals(c) && ar.getDestino().equals(a))
        );

        assertTrue(existeCorta);
    }
}
