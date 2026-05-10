package logica;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Prim {
	
	//distancia
    private static double distancia(Localidad a, Localidad b) {
        double dx = a.getLatitud() - b.getLatitud();
        double dy = a.getLongitud() - b.getLongitud();
        return Math.sqrt(dx * dx + dy * dy);
    }
    
    //costo
    private static double calcularCosto(Localidad a, Localidad b, double costoPorKm, 
    									double porcentajeExtra, double costoInterprovincial) {
    	
        double distancia = distancia(a, b);
        double costo = distancia * costoPorKm;

        if (distancia > 300) {
            costo += costo * (porcentajeExtra / 100);
        }

        if (!a.getProvincia().equals(b.getProvincia())) {
            costo += costoInterprovincial;
        }

        return costo;
    }
    
    public static List<Arista> dameAGM(ArrayList<Localidad> localidades, double costoPorKm, 
    									double porcentajeExtra, double costoInterprovincial){
    
	    if (localidades.size() < 2) {
	        throw new IllegalStateException("Se necesitan al menos 2 localidades");
	    }
	    
	    List<Arista> aristasElegidas = new ArrayList<>();
	    Set<Localidad> localidadesVisitadas = new HashSet<>();
	    Localidad inicio = localidades.get(0);
	    localidadesVisitadas.add(inicio);
	    
	    while (localidadesVisitadas.size() < localidades.size()) {
	        Arista mejor = null;
	        mejor = dameLaAristaMasLiviana(localidades, costoPorKm, porcentajeExtra, costoInterprovincial, localidadesVisitadas,
					mejor);
	        
	        if (mejor != null) {
	            aristasElegidas.add(mejor);
	            localidadesVisitadas.add(mejor.getDestino());
	        }
	    }
	    return aristasElegidas;
    }

	private static Arista dameLaAristaMasLiviana(ArrayList<Localidad> localidades, double costoPorKm,
			double porcentajeExtra, double costoInterprovincial, Set<Localidad> visitadas, Arista mejor) {
		double mejorCosto = Double.MAX_VALUE;
      
		for (Localidad a : visitadas) {
		    for (Localidad b : localidades) {
		        if (!visitadas.contains(b)) {
		            double costo = calcularCosto(a, b,costoPorKm, porcentajeExtra, costoInterprovincial);
		            if (costo < mejorCosto) {
		                mejorCosto = costo;
		                mejor = new Arista(a, b, costo);
		            }
		        }
		    }
		}
		return mejor;
	}
}
