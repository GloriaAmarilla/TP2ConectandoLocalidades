package Visual;

import java.awt.Color;
import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.List;

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
	private JTextField campoCostoPorKm;
	private JTextField campoPorcentajeExtra;
	private JTextField campoCostoInterprovincial;
	private Grafo grafoInicial = null;

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

	public Principal() {
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

		JLabel indicaIngreso = new JLabel("Ingrese las localidades");
		indicaIngreso.setFont(new Font("Arial", Font.BOLD, 20));
		indicaIngreso.setHorizontalAlignment(SwingConstants.CENTER);
		indicaIngreso.setBounds(10, 10, 400, 30);
		panelControles.add(indicaIngreso);

		JLabel indicaIngreso2 = new JLabel("que desee conectar");
		indicaIngreso2.setHorizontalAlignment(SwingConstants.CENTER);
		indicaIngreso2.setFont(new Font("Arial", Font.BOLD, 20));
		indicaIngreso2.setBounds(10, 40, 400, 30);
		panelControles.add(indicaIngreso2);

		// costo
		JLabel lblCostoPorKm = new JLabel("Costo por km ($):");
		lblCostoPorKm.setFont(new Font("Arial", Font.PLAIN, 15));
		lblCostoPorKm.setBounds(10, 85, 160, 25);
		panelControles.add(lblCostoPorKm);

		campoCostoPorKm = new JTextField();
		campoCostoPorKm.setFont(new Font("Arial", Font.PLAIN, 14));
		campoCostoPorKm.setBounds(185, 85, 172, 25);
		panelControles.add(campoCostoPorKm);

		JLabel lblPorcentaje = new JLabel("% extra +300 km:");
		lblPorcentaje.setFont(new Font("Arial", Font.PLAIN, 15));
		lblPorcentaje.setBounds(10, 118, 160, 25);
		panelControles.add(lblPorcentaje);

		campoPorcentajeExtra = new JTextField();
		campoPorcentajeExtra.setFont(new Font("Arial", Font.PLAIN, 14));
		campoPorcentajeExtra.setBounds(185, 118, 172, 25);
		panelControles.add(campoPorcentajeExtra);

		JLabel lblCostoInter = new JLabel("Costo interprov. ($):");
		lblCostoInter.setFont(new Font("Arial", Font.PLAIN, 15));
		lblCostoInter.setBounds(10, 151, 160, 25);
		panelControles.add(lblCostoInter);

		campoCostoInterprovincial = new JTextField();
		campoCostoInterprovincial.setFont(new Font("Arial", Font.PLAIN, 14));
		campoCostoInterprovincial.setBounds(185, 151, 172, 25);
		panelControles.add(campoCostoInterprovincial);

		// localidad
		JLabel indicaIngresoNombre = new JLabel("Nombre de localidad:");
		indicaIngresoNombre.setFont(new Font("Arial", Font.PLAIN, 15));
		indicaIngresoNombre.setBounds(10, 200, 160, 25);
		panelControles.add(indicaIngresoNombre);

		nombreLocalidad = new JTextField();
		nombreLocalidad.setFont(new Font("Arial", Font.PLAIN, 14));
		nombreLocalidad.setBounds(185, 200, 172, 25);
		nombreLocalidad.setColumns(10);
		panelControles.add(nombreLocalidad);

		JLabel indicaIngresoProvincia = new JLabel("Provincia:");
		indicaIngresoProvincia.setFont(new Font("Arial", Font.PLAIN, 15));
		indicaIngresoProvincia.setBounds(10, 238, 160, 25);
		panelControles.add(indicaIngresoProvincia);

		provincia = new JTextField();
		provincia.setFont(new Font("Arial", Font.PLAIN, 14));
		provincia.setColumns(10);
		provincia.setBounds(185, 238, 172, 25);
		panelControles.add(provincia);

		JLabel indicaIngresoLatitud = new JLabel("Latitud:");
		indicaIngresoLatitud.setFont(new Font("Arial", Font.PLAIN, 15));
		indicaIngresoLatitud.setBounds(10, 276, 160, 25);
		panelControles.add(indicaIngresoLatitud);

		latitudLocalidad = new JTextField();
		latitudLocalidad.setFont(new Font("Arial", Font.PLAIN, 14));
		latitudLocalidad.setColumns(10);
		latitudLocalidad.setBounds(185, 276, 172, 25);
		panelControles.add(latitudLocalidad);

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
					if (grafoInicial == null) {
						double costoPorKm = Double.parseDouble(campoCostoPorKm.getText().trim());
						double porcentajeExtra = Double.parseDouble(campoPorcentajeExtra.getText().trim());
						double costoInter = Double.parseDouble(campoCostoInterprovincial.getText().trim());
						grafoInicial = new Grafo(costoPorKm, porcentajeExtra, costoInter);

						campoCostoPorKm.setEditable(false);
						campoPorcentajeExtra.setEditable(false);
						campoCostoInterprovincial.setEditable(false);
					}

					double latitud = Double.parseDouble(latitudLocalidad.getText().trim());
					double longitud = Double.parseDouble(longitudLocalidad.getText().trim());
					Localidad localidadActual = new Localidad(nombreLocalidad.getText().trim(),
							provincia.getText().trim(), latitud, longitud);
					grafoInicial.agregarLocalidad(localidadActual);

					// Marcar en mapa
					Coordinate coord = new Coordinate(latitud, longitud);
					MapMarker marker = new MapMarkerDot(localidadActual.getNombre(), coord);
					marker.getStyle().setBackColor(Color.BLUE);
					mapa.addMapMarker(marker);
					mapa.setDisplayPosition(coord, mapa.getZoom());

					nombreLocalidad.setText("");
					provincia.setText("");
					latitudLocalidad.setText("");
					longitudLocalidad.setText("");

				} catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(frmMapa,
							"Verificá que latitud, longitud y los parámetros de costo sean números válidos.",
							"Error de formato", JOptionPane.ERROR_MESSAGE);
				} catch (IllegalArgumentException ex) {
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
					if (grafoInicial == null || grafoInicial.getLocalidades().size() < 2) {
						JOptionPane.showMessageDialog(frmMapa, "Ingresá al menos 2 localidades antes de conectar.",
								"Atención", JOptionPane.WARNING_MESSAGE);
						return;
					}

					List<Arista> mst = grafoInicial.prim();
					mapa.removeAllMapPolygons();
					// Dibujar cada arista del AGM en rojo
					for (Arista arista : mst) {
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
					for (Arista arista : mst) {
						sb.append(String.format("• %s  →  %s\n   Costo: $%.2f\n\n", arista.getOrigen().getNombre(),
								arista.getDestino().getNombre(), arista.getCosto()));
					}
					sb.append(String.format("Costo total de instalación: $%.2f", grafoInicial.costoTotal(mst)));

					// Mostrar con scroll
					JTextArea textArea = new JTextArea(sb.toString());
					textArea.setFont(new Font("Monospaced", Font.PLAIN, 13));
					textArea.setEditable(false);
					JScrollPane scrollPane = new JScrollPane(textArea);
					scrollPane.setPreferredSize(new Dimension(420, 300));

					JOptionPane.showMessageDialog(frmMapa, scrollPane, "Conexión Óptima - Árbol Generador Mínimo",
							JOptionPane.INFORMATION_MESSAGE);

				} catch (IllegalStateException ex) {
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
}