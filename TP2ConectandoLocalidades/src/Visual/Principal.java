package Visual;

import java.awt.Color;
import java.awt.EventQueue;
import java.util.ArrayList;

import javax.swing.JFrame;

import org.openstreetmap.gui.jmapviewer.Coordinate;
import org.openstreetmap.gui.jmapviewer.JMapViewer;
import org.openstreetmap.gui.jmapviewer.MapMarkerDot;
import org.openstreetmap.gui.jmapviewer.MapPolygonImpl;
import org.openstreetmap.gui.jmapviewer.interfaces.MapMarker;
import org.openstreetmap.gui.jmapviewer.interfaces.MapPolygon;

import logica.Grafo;
import logica.Localidad;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Dimension;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Principal {

	private JFrame frmMapa;
	private JMapViewer mapa;
	private JPanel panelControles;
	private JPanel panelMapa;
	private JTextField nombreLocalidad;
	private JTextField provincia;
	private JTextField latitudLocalidad;
	private JTextField longitudLocalidad;
	private Grafo grafoInicial;
	private Grafo conexionOptima; //para crear un nuevo grafo que sea el AGM basado en las aristas dadas por prim

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Principal window = new Principal();
					window.frmMapa.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Principal() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmMapa = new JFrame();
		frmMapa.setTitle("Mapa");
		frmMapa.setBounds(100, 100, 1000, 700);
		frmMapa.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmMapa.getContentPane().setLayout(null);
		
		panelMapa = new JPanel();
		panelMapa.setBounds(12, 11, 500, 800);
		frmMapa.getContentPane().add(panelMapa);
		
		panelControles = new JPanel();
		panelControles.setBounds(549, 11, 402, 604);
		frmMapa.getContentPane().add(panelControles);
		panelControles.setLayout(null);
		
		JLabel indicaIngreso = new JLabel("Ingrese las localidades ");
		indicaIngreso.setFont(new Font("Arial", Font.BOLD, 20));
		indicaIngreso.setHorizontalAlignment(SwingConstants.CENTER);
		indicaIngreso.setBounds(10, 36, 382, 35);
		panelControles.add(indicaIngreso);
		
		JLabel indicaIngreso2 = new JLabel("que desee conectar");
		indicaIngreso2.setHorizontalAlignment(SwingConstants.CENTER);
		indicaIngreso2.setFont(new Font("Arial", Font.BOLD, 20));
		indicaIngreso2.setBounds(10, 67, 382, 35);
		panelControles.add(indicaIngreso2);
		
		JLabel indicaIngresoNombre = new JLabel("Nombre de localidad: ");
		indicaIngresoNombre.setFont(new Font("Arial", Font.PLAIN, 15));
		indicaIngresoNombre.setBounds(10, 144, 149, 35);
		panelControles.add(indicaIngresoNombre);
		
		nombreLocalidad = new JTextField();
		nombreLocalidad.setFont(new Font("Arial", Font.PLAIN, 14));
		nombreLocalidad.setBounds(185, 148, 172, 27);
		panelControles.add(nombreLocalidad);
		nombreLocalidad.setColumns(10);
		
		JLabel indicaIngresoProvincia = new JLabel("Provincia: ");
		indicaIngresoProvincia.setFont(new Font("Arial", Font.PLAIN, 15));
		indicaIngresoProvincia.setBounds(10, 206, 149, 35);
		panelControles.add(indicaIngresoProvincia);

		provincia = new JTextField();
		provincia.setFont(new Font("Arial", Font.PLAIN, 14));
		provincia.setColumns(10);
		provincia.setBounds(185, 206, 172, 27);
		panelControles.add(provincia);
		
		JLabel indicaIngresoLatitud = new JLabel("Latitud:");
		indicaIngresoLatitud.setFont(new Font("Arial", Font.PLAIN, 15));
		indicaIngresoLatitud.setBounds(10, 266, 149, 35);
		panelControles.add(indicaIngresoLatitud);
		
		latitudLocalidad = new JTextField();
		latitudLocalidad.setFont(new Font("Arial", Font.PLAIN, 14));
		latitudLocalidad.setColumns(10);
		latitudLocalidad.setBounds(185, 270, 172, 27);
		panelControles.add(latitudLocalidad);
		
		JLabel indicaIngresoLongitud = new JLabel("Longitud: ");
		indicaIngresoLongitud.setFont(new Font("Arial", Font.PLAIN, 15));
		indicaIngresoLongitud.setBounds(10, 330, 149, 35);
		panelControles.add(indicaIngresoLongitud);
		
		longitudLocalidad = new JTextField();
		longitudLocalidad.setFont(new Font("Arial", Font.PLAIN, 14));
		longitudLocalidad.setColumns(10);
		longitudLocalidad.setBounds(185, 330, 172, 27);
		panelControles.add(longitudLocalidad);
		
		grafoInicial = new Grafo(5.5,6.6,7.7); //datos provisorios para costoPorKm,porcentajeExtra,costoInterprovincial
		
		JButton btnAgregarLocalidad = new JButton("Agregar Localidad");
		btnAgregarLocalidad.setFont(new Font("Arial", Font.BOLD, 16));
		btnAgregarLocalidad.setBounds(107, 387, 172, 44);
		panelControles.add(btnAgregarLocalidad);
		
		btnAgregarLocalidad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//Creamos la localidad
				double latitud = Double.parseDouble(latitudLocalidad.getText());
				double longitud = Double.parseDouble(longitudLocalidad.getText());
				Localidad localidadActual = new Localidad (nombreLocalidad.getText(), provincia.getText(), latitud, longitud);
				
				//Agregar la localidad al grafo con los datos ingresados
				grafoInicial.agregarLocalidad(localidadActual);
				
				//Reiniciar los campos de datos de la localidad
				nombreLocalidad.setText("");
				provincia.setText("");
				latitudLocalidad.setText("");
				longitudLocalidad.setText("");
			}
		});
		
		JLabel informaCuandoCalcular = new JLabel("Si ya ingresaste la ultima localidad podes presionar:");
		informaCuandoCalcular.setHorizontalAlignment(SwingConstants.CENTER);
		informaCuandoCalcular.setFont(new Font("Arial", Font.BOLD, 15));
		informaCuandoCalcular.setBounds(10, 456, 382, 35);
		panelControles.add(informaCuandoCalcular);
		
		JButton btnDarConexionOptima = new JButton("Conectar");
		btnDarConexionOptima.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Aca usamos prim para obtener el AGM y mostrar las localidades conectadas en el mapa 
				//Tambien tenemos que mostrar los datos:
				//conexiones de fibra optica a construir y el costo total de las instalaciones
			}
		});
		btnDarConexionOptima.setFont(new Font("Arial", Font.BOLD, 16));
		btnDarConexionOptima.setBounds(107, 499, 172, 44);
		panelControles.add(btnDarConexionOptima);
		
		mapa = new JMapViewer();
		mapa.setPreferredSize(new Dimension(400, 600));
		mapa.setZoom(5);
		mapa.setBackground(new Color(240, 240, 240));
		mapa.setZoomControlsVisible(true);
		
		//Nos posicionamos en el area y alrededores de la UNGS
		Coordinate coordenadaInicial = new Coordinate (-34.521, -58.719);
		mapa.setDisplayPosition(coordenadaInicial, 12);
		
		//Agregamos un punto posicionando la UNGS
		MapMarker puntoUNGS = new MapMarkerDot("UNGS", coordenadaInicial);
		puntoUNGS.getStyle().setBackColor(Color.red);
		puntoUNGS.getStyle().setColor(Color.orange);
		mapa.addMapMarker(puntoUNGS);
		
		//Para agregar poligono
		Coordinate otraCoordenada = new Coordinate (-34.521, -58.708);
		Coordinate otraCoordMas = new Coordinate (-34.546, -58.719);
		Coordinate otraCoordMasAca = new Coordinate(-34.559, -58.721);
		ArrayList<Coordinate> coordenadas = new ArrayList<>();
		
		coordenadas.add(coordenadaInicial);
		coordenadas.add(otraCoordenada);
		coordenadas.add(otraCoordMas);
		coordenadas.add(otraCoordMasAca);
		
		MapPolygon poligono = new MapPolygonImpl(coordenadas);
		mapa.addMapPolygon(poligono);
		
		panelMapa.add(mapa);
		
		
		
		
		
		
	}
}
