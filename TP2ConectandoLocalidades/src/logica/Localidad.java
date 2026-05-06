package logica;

public class Localidad {

	private String nombre;
	private String provincia;
	private double latitud;
	private double longitud;

	public Localidad(String nombre, String provincia, double latitud, double longitud) {
		if (nombre.isEmpty() || provincia.isEmpty()) {
			throw new IllegalArgumentException("Nombre o Provincia vacios");
		}
		if(latitud<-90||latitud>90||longitud<-180||longitud>180)
			throw new IllegalArgumentException("Latidud o Longitud incorrecta");
		this.nombre = nombre;
		this.provincia = provincia;
		this.latitud = latitud;
		this.longitud = longitud;
	}
	public String getNombre() {
		return nombre;
	}

	public String getProvincia() {
		return provincia;
	}

	public double getLatitud() {
		return latitud;
	}

	public double getLongitud() {
		return longitud;
	}

}
