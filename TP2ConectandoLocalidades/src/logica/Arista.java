package logica;

public class Arista {
    private Localidad origen;
    private Localidad destino;
    private double costo;

    public Arista(Localidad Origen, Localidad Destino, double Costo) {
    	this.origen=Origen;
    	this.destino=Destino;
    	this.costo=Costo;
    }



    public boolean esValida() {
        return origen != null && destino != null;
    }
	public Localidad getOrigen() {
		return origen;
	}

	public void setOrigen(Localidad origen) {
		this.origen = origen;
	}

	public Localidad getDestino() {
		return destino;
	}

	public void setDestino(Localidad destino) {
		this.destino = destino;
	}

	public double getCosto() {
		return costo;
	}

	public void setCosto(double costo) {
		this.costo = costo;
	}
}
