package testUnitarios;

import org.junit.Test;

import logica.Localidad;

public class LocalidadTest {

	@Test (expected = IllegalArgumentException.class)
	public void nombreYProvinciaVaciosTest() {
		Localidad loc = new Localidad("","",3.4,5.6);
	}

}
