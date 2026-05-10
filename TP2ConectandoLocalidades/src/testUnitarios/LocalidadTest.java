package testUnitarios;

import org.junit.Test;

import logica.Localidad;

public class LocalidadTest {

	@Test (expected = IllegalArgumentException.class)
	public void nombreYProvinciaVaciosTest() {
		Localidad loc = new Localidad("","",3.4,5.6);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void longitudIncorrectaTest() {
		Localidad loc = new Localidad("localidad","provincia",-90.0001,10.1010);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void latitudIncorrectaTest() {
		Localidad loc = new Localidad("localidad","provincia",-90.0000,180.0001);
	}

}
