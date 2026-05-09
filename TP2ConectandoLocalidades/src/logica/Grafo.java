package logica;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Grafo {

	private ArrayList<Localidad> localidades;
    private List<Arista> aristas;

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
    
    // prim
    public List<Arista> prim() {
    	aristas = Prim.dameAGM(localidades, costoPorKm, porcentajeExtra, costoInterprovincial);
    	return aristas;
    }
    public void guardarEstado() {
        CargaDatos.guardarLocalidades(localidades);
        CargaDatos.guardarAristas(aristas);
    }

    public void cargarEstado() {
        localidades = (ArrayList<Localidad>) CargaDatos.cargarLocalidades();
        aristas = CargaDatos.cargarAristas();
    }

    public List<Arista> getAristas() {
        return aristas;
    }
    public double costoTotal() {
        double total = 0;

        for (Arista a : aristas) {
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
