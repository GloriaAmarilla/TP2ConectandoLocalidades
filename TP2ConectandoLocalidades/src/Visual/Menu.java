package Visual;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class Menu {

	private JFrame frame;

	private JTextField campoCostoPorKm;
	private JTextField campoPorcentajeExtra;
	private JTextField campoCostoInterprovincial;

	private ArrayList<String> provincias;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Menu window = new Menu();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Menu() {
		AgregarProvincias();
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(400, 150, 550, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel indicaIngreso = new JLabel("<html><center>Ingrese el costo que<br>corresponda.</center></html>");
		indicaIngreso.setFont(new Font("Arial", Font.BOLD, 20));
		indicaIngreso.setHorizontalAlignment(SwingConstants.CENTER);
		indicaIngreso.setBounds(10, 10, 500, 100);
		frame.add(indicaIngreso);

		JLabel lblCostoPorKm = new JLabel("Costo por km ($):");
		lblCostoPorKm.setFont(new Font("Arial", Font.PLAIN, 15));
		lblCostoPorKm.setBounds(120, 120, 160, 25);
		frame.getContentPane().add(lblCostoPorKm);

		campoCostoPorKm = new JTextField();
		campoCostoPorKm.setFont(new Font("Arial", Font.PLAIN, 14));
		campoCostoPorKm.setBounds(255, 120, 172, 25);
		frame.getContentPane().add(campoCostoPorKm);

		JLabel lblPorcentajeExtra = new JLabel("% extra +300 km:");
		lblPorcentajeExtra.setFont(new Font("Arial", Font.PLAIN, 15));
		lblPorcentajeExtra.setBounds(120, 160, 160, 25);
		frame.getContentPane().add(lblPorcentajeExtra);

		campoPorcentajeExtra = new JTextField();
		campoPorcentajeExtra.setFont(new Font("Arial", Font.PLAIN, 14));
		campoPorcentajeExtra.setBounds(255, 160, 172, 25);
		frame.getContentPane().add(campoPorcentajeExtra);

		JLabel lblCostoInter = new JLabel("Costo interprov. ($):");
		lblCostoInter.setFont(new Font("Arial", Font.PLAIN, 15));
		lblCostoInter.setBounds(120, 200, 160, 25);
		frame.getContentPane().add(lblCostoInter);

		campoCostoInterprovincial = new JTextField();
		campoCostoInterprovincial.setFont(new Font("Arial", Font.PLAIN, 14));
		campoCostoInterprovincial.setBounds(255, 200, 172, 25);
		frame.getContentPane().add(campoCostoInterprovincial);

		JButton botonSiguiente = new JButton("Siguiente");
		botonSiguiente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String costoKm = campoCostoPorKm.getText().trim();
				String porcentaje = campoPorcentajeExtra.getText().trim();
				String interprov = campoCostoInterprovincial.getText().trim();

				boolean valido = costoKm.matches("\\d+(\\.\\d+)?") && porcentaje.matches("\\d+(\\.\\d+)?")
						&& interprov.matches("\\d+(\\.\\d+)?");
				if (!valido) {
					JOptionPane.showMessageDialog(frame, "Ingrese solamente números válidos.", "Error",
							JOptionPane.ERROR_MESSAGE);
					return;
				}

				double costoPorKm = Double.parseDouble(costoKm);
				double porcentajeExtra = Double.parseDouble(porcentaje);
				double costoInter = Double.parseDouble(interprov);

				Principal principal = new Principal(costoPorKm, porcentajeExtra, costoInter);
				principal.mostrarVentana();
				frame.dispose();
			}
		});
		botonSiguiente.setFont(new Font("Arial", Font.PLAIN, 16));
		botonSiguiente.setBounds(180, 260, 162, 39);
		frame.getContentPane().add(botonSiguiente);
	}

	public void AgregarProvincias() {
		provincias = new ArrayList<>();
		try {
			Scanner sc = new Scanner(new File("Provincias.txt"));
			while (sc.hasNextLine()) {
				provincias.add(sc.nextLine());
			}
			sc.close();
		} catch (FileNotFoundException e) {
		}
	}

	public void mostrarVentana() {
		frame.setVisible(true);
	}
}
