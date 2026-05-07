package Visual;

import java.awt.Color;
import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import org.openstreetmap.gui.jmapviewer.Coordinate;
import org.openstreetmap.gui.jmapviewer.JMapViewer;
import org.openstreetmap.gui.jmapviewer.MapMarkerDot;
import org.openstreetmap.gui.jmapviewer.MapPolygonImpl;
import org.openstreetmap.gui.jmapviewer.interfaces.MapMarker;

import logica.Arista;
import logica.Grafo;
import logica.Localidad;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Dimension;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;

import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.awt.event.ActionEvent;

public class Principal {

	private JFrame frmMapa;
	private JMapViewer mapa;
	private JPanel panelControles;
	private JPanel panelMapa;
	
	private ArrayList<String> provincias;
	private JTextField nombreLocalidad;
	private JTextField latitudLocalidad;
	private JTextField longitudLocalidad;
	
	private Grafo grafoInicial = null;

	public Principal(double costoPorKm, double porcentajeExtra, double costoInter) {
		grafoInicial = new Grafo(costoPorKm, porcentajeExtra, costoInter);
		AgregarProvincias();
		initialize();
	}

	private void initialize() {
		frmMapa = new JFrame();
		frmMapa.setTitle("Planificador de Fibra Óptica");
		frmMapa.setBounds(100, 100, 1000, 700);
		frmMapa.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmMapa.getContentPane().setLayout(null);

		panelMapa = new JPanel();
		panelMapa.setBounds(12, 11, 500, 650);
		frmMapa.getContentPane().add(panelMapa);

		panelControles = new JPanel();
		panelControles.setBounds(549, 11, 420, 650);
		frmMapa.getContentPane().add(panelControles);
		panelControles.setLayout(null);

		JLabel indicaIngreso = new JLabel("<html><center>Ingrese las localidades<br>que desee conectar</center></html>");
		indicaIngreso.setFont(new Font("Arial", Font.BOLD, 20));
		indicaIngreso.setHorizontalAlignment(SwingConstants.CENTER);
		indicaIngreso.setBounds(10, 25, 400, 60);
		panelControles.add(indicaIngreso);	


					/*NOMBRE DE PROVINCIA Y LOCALIDAD*/
		/*---------------COMBOBOX DE PROVINCIAS---------------*/
		JLabel textProvincia = new JLabel("Provincia: ");
	    textProvincia.setBounds(10, 200, 200, 30);
	    textProvincia.setFont(new Font("Arial", Font.PLAIN, 15));
	    panelControles.add(textProvincia);
	    
	    JComboBox<String> comboBox = new JComboBox<>();
	    comboBox.setBounds(185, 202, 172, 25);
	    comboBox.setFont(new Font("Arial", Font.PLAIN, 15));
	    panelControles.add(comboBox);
	    
	    //Agrego las provincias al comboBox.
	    for(String provin  : provincias) {
	    	comboBox.addItem(provin);
	    }
	    
				/*---------------LOCALIDAD---------------*/
		JLabel indicaIngresoNombre = new JLabel("Nombre de localidad:");
		indicaIngresoNombre.setFont(new Font("Arial", Font.PLAIN, 15));
		indicaIngresoNombre.setBounds(10, 235, 160, 25);
		panelControles.add(indicaIngresoNombre);

		nombreLocalidad = new JTextField();
		nombreLocalidad.setFont(new Font("Arial", Font.PLAIN, 14));
		nombreLocalidad.setBounds(185, 238, 172, 25);
		nombreLocalidad.setColumns(10);
		panelControles.add(nombreLocalidad);
	    /*-------------------------------------------------------------*/
		
						/*COORDENADAS A INGRESAR*/
				/*---------------LATITUD---------------*/
		JLabel indicaIngresoLatitud = new JLabel("Latitud:");
		indicaIngresoLatitud.setFont(new Font("Arial", Font.PLAIN, 15));
		indicaIngresoLatitud.setBounds(10, 276, 160, 25);
		panelControles.add(indicaIngresoLatitud);

		latitudLocalidad = new JTextField();
		latitudLocalidad.setFont(new Font("Arial", Font.PLAIN, 14));
		latitudLocalidad.setColumns(10);
		latitudLocalidad.setBounds(185, 276, 172, 25);
		panelControles.add(latitudLocalidad);

				/*---------------LONGITUD---------------*/
		JLabel indicaIngresoLongitud = new JLabel("Longitud:");
		indicaIngresoLongitud.setFont(new Font("Arial", Font.PLAIN, 15));
		indicaIngresoLongitud.setBounds(10, 314, 160, 25);
		panelControles.add(indicaIngresoLongitud);

		longitudLocalidad = new JTextField();
		longitudLocalidad.setFont(new Font("Arial", Font.PLAIN, 14));
		longitudLocalidad.setColumns(10);
		longitudLocalidad.setBounds(185, 314, 172, 25);
		panelControles.add(longitudLocalidad);

		// boton localidad
		JButton btnAgregarLocalidad = new JButton("Agregar Localidad");
		btnAgregarLocalidad.setFont(new Font("Arial", Font.BOLD, 16));
		btnAgregarLocalidad.setBounds(107, 360, 190, 40);
		panelControles.add(btnAgregarLocalidad);

		btnAgregarLocalidad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
						
					double latitud = Double.parseDouble(latitudLocalidad.getText().trim());
					double longitud = Double.parseDouble(longitudLocalidad.getText().trim());
					String nombreDeLocalidad = nombreLocalidad.getText().trim();
					String nombreDeProvincia = comboBox.getSelectedItem().toString().trim();
					
					Localidad localidadActual = new Localidad(nombreDeLocalidad, nombreDeProvincia, latitud, longitud);
					grafoInicial.agregarLocalidad(localidadActual);

								/*MARCAR EN EL MAPA*/
					String nom_ProvinciaYLocalidad = nombreDeProvincia+", "+nombreDeLocalidad;
					marcarEnElMapa(latitud, longitud, nom_ProvinciaYLocalidad);

					nombreLocalidad.setText("");
					latitudLocalidad.setText("");
					longitudLocalidad.setText("");

				}
				
				catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(frmMapa,
							"Verificá que latitud, longitud y los parámetros de costo sean números válidos.",
							"Error de formato", JOptionPane.ERROR_MESSAGE);
				}
				
				catch (IllegalArgumentException ex) {
					JOptionPane.showMessageDialog(frmMapa, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		JLabel informaCuandoCalcular = new JLabel("Si ya ingresaste la última localidad podés presionar:");
		informaCuandoCalcular.setHorizontalAlignment(SwingConstants.CENTER);
		informaCuandoCalcular.setFont(new Font("Arial", Font.BOLD, 13));
		informaCuandoCalcular.setBounds(10, 415, 400, 30);
		panelControles.add(informaCuandoCalcular);

		// btn conectar
		JButton btnDarConexionOptima = new JButton("Conectar");
		btnDarConexionOptima.setFont(new Font("Arial", Font.BOLD, 16));
		btnDarConexionOptima.setBounds(107, 455, 190, 40);
		panelControles.add(btnDarConexionOptima);

		btnDarConexionOptima.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (grafoInicial.getLocalidades().size() < 2) {
						JOptionPane.showMessageDialog(frmMapa, "Ingresá al menos 2 localidades antes de conectar.",
								"Atención", JOptionPane.WARNING_MESSAGE);
						return;
					}

					List<Arista> Arbol_Generador_Minimo = grafoInicial.prim();
					mapa.removeAllMapPolygons();
					
					// Dibujar cada arista del AGM en rojo
					for (Arista arista : Arbol_Generador_Minimo) {
						Coordinate coordOrigen = new Coordinate(arista.getOrigen().getLatitud(),
								arista.getOrigen().getLongitud());
						Coordinate coordDestino = new Coordinate(arista.getDestino().getLatitud(),
								arista.getDestino().getLongitud());

						ArrayList<Coordinate> linea = new ArrayList<>();
						linea.add(coordOrigen);
						linea.add(coordDestino);
						linea.add(coordOrigen);

						MapPolygonImpl conexion = new MapPolygonImpl(linea);
						conexion.setColor(Color.RED);
						conexion.setBackColor(new Color(0, 0, 0, 0));
						mapa.addMapPolygon(conexion);
					}

					// costototal
					StringBuilder sb = new StringBuilder();
					sb.append("=== Conexiones de fibra óptica a construir ===\n\n");
					for (Arista arista : Arbol_Generador_Minimo) {
						sb.append(String.format("• %s  →  %s\n   Costo: $%.2f\n\n", arista.getOrigen().getNombre(),
								arista.getDestino().getNombre(), arista.getCosto()));
					}
					sb.append(String.format("Costo total de instalación: $%.2f", grafoInicial.costoTotal(Arbol_Generador_Minimo)));

					// Mostrar con scroll
					JTextArea textArea = new JTextArea(sb.toString());
					textArea.setFont(new Font("Monospaced", Font.PLAIN, 13));
					textArea.setEditable(false);
					JScrollPane scrollPane = new JScrollPane(textArea);
					scrollPane.setPreferredSize(new Dimension(420, 300));

					JOptionPane.showMessageDialog(frmMapa, scrollPane, "Conexión Óptima - Árbol Generador Mínimo",
							JOptionPane.INFORMATION_MESSAGE);

				}
				catch (IllegalStateException ex) {
					JOptionPane.showMessageDialog(frmMapa, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		// mapa
		mapa = new JMapViewer();
		mapa.setPreferredSize(new Dimension(490, 640));
		mapa.setZoom(5);
		mapa.setBackground(new Color(240, 240, 240));
		mapa.setZoomControlsVisible(true);

		Coordinate coordenadaInicial = new Coordinate(-34.521, -58.719);
		mapa.setDisplayPosition(coordenadaInicial, 12);

		MapMarker puntoUNGS = new MapMarkerDot("UNGS", coordenadaInicial);
		puntoUNGS.getStyle().setBackColor(Color.RED);
		puntoUNGS.getStyle().setColor(Color.ORANGE);
		mapa.addMapMarker(puntoUNGS);

		panelMapa.add(mapa);
	}
	
	private void AgregarProvincias() {
		provincias = new ArrayList<>();
		
		try {
            Scanner sc = new Scanner(new File("Provincias.txt"));
            
            while (sc.hasNextLine()) {
            	provincias.add(sc.nextLine());
            }
            
            sc.close();
        }
		
		catch (FileNotFoundException e) {
        }
		
		
	}
	
	private void marcarEnElMapa(double latitud, double longitud, String nom_ProvinciaYLocalidad) {
		Coordinate coord = new Coordinate(latitud, longitud);
		MapMarker marker = new MapMarkerDot(nom_ProvinciaYLocalidad, coord);
		marker.getStyle().setBackColor(Color.BLUE);
		mapa.addMapMarker(marker);
		mapa.setDisplayPosition(coord, mapa.getZoom());
	}
	
	public void mostrarVentana() {
		frmMapa.setVisible(true);
    }
}