package logica;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Grafo {

	private ArrayList<Localidad> localidades;
    private ArrayList<Arista> aristas;

    private double costoPorKm;
    private double porcentajeExtra;
    private double costoInterprovincial;
    
    public Grafo(double costoPorKm,
            double porcentajeExtra,
            double costoInterprovincial) {

   this.localidades = new ArrayList<>();
   this.aristas = new ArrayList<>();
   this.costoPorKm = costoPorKm;
   this.porcentajeExtra = porcentajeExtra;
   this.costoInterprovincial = costoInterprovincial;
}
    
    //distancia
    private double distancia(Localidad a, Localidad b) {
        double dx = a.getLatitud() - b.getLatitud();
        double dy = a.getLongitud() - b.getLongitud();
        return Math.sqrt(dx * dx + dy * dy);
    }
    //costo
    private double calcularCosto(Localidad a, Localidad b) {
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

    // prim
    public List<Arista> prim() {

        if (localidades.size() < 2) {
            throw new IllegalStateException("Se necesitan al menos 2 localidades");
        }
        List<Arista> resultado = new ArrayList<>();
        Set<Localidad> visitadas = new HashSet<>();
        Localidad inicio = localidades.get(0);
        visitadas.add(inicio);
        while (visitadas.size() < localidades.size()) {
            Arista mejor = null;
            double mejorCosto = Double.MAX_VALUE;
            for (Localidad a : visitadas) {
                for (Localidad b : localidades) {
                    if (!visitadas.contains(b)) {
                        double costo = calcularCosto(a, b);
                        if (costo < mejorCosto) {
                            mejorCosto = costo;
                            mejor = new Arista(a, b, costo);
                        }
                    }
                }
            }
            if (mejor != null) {
                resultado.add(mejor);
                visitadas.add(mejor.getDestino());
            }
        }
        return resultado;
    }

    public double costoTotal(List<Arista> mst) {
        double total = 0;

        for (Arista a : mst) {
            total += a.getCosto();
        }

        return total;
    }
    
    public void agregarLocalidad(Localidad l) {
        localidades.add(l);
    }

	public ArrayList<Localidad> getLocalidades() {
		return localidades;
	}
}
