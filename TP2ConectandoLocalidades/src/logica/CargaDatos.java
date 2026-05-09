package logica;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CargaDatos {

    private static final String ARCHIVO_LOCALIDADES = "localidades.txt";
    private static final String ARCHIVO_ARISTAS     = "aristas.txt";

    // Guarda todas las localidades del grafo
    public static void guardarLocalidades(List<Localidad> localidades) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(ARCHIVO_LOCALIDADES))) {
            for (Localidad l : localidades) {
                pw.println(l.getNombre() + "," +
                           l.getProvincia() + "," +
                           l.getLatitud() + "," +
                           l.getLongitud());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Carga las localidades desde el archivo
    public static List<Localidad> cargarLocalidades() {
        List<Localidad> lista = new ArrayList<>();
        File f = new File(ARCHIVO_LOCALIDADES);
        if (!f.exists()) return lista;

        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(",");
                if (partes.length == 4) {
                    lista.add(new Localidad(
                        partes[0].trim(),
                        partes[1].trim(),
                        Double.parseDouble(partes[2].trim()),
                        Double.parseDouble(partes[3].trim())
                    ));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lista;
    }

    // Guarda las aristas del AGM
    public static void guardarAristas(List<Arista> aristas) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(ARCHIVO_ARISTAS))) {
            for (Arista a : aristas) {
                pw.println(a.getOrigen().getNombre() + "," +
                           a.getOrigen().getProvincia() + "," +
                           a.getOrigen().getLatitud() + "," +
                           a.getOrigen().getLongitud() + "," +
                           a.getDestino().getNombre() + "," +
                           a.getDestino().getProvincia() + "," +
                           a.getDestino().getLatitud() + "," +
                           a.getDestino().getLongitud() + "," +
                           a.getCosto());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Carga las aristas del AGM
    public static List<Arista> cargarAristas() {
        List<Arista> lista = new ArrayList<>();
        File f = new File(ARCHIVO_ARISTAS);
        if (!f.exists()) return lista;

        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] p = linea.split(",");
                if (p.length == 9) {
                    Localidad origen  = new Localidad(p[0].trim(), p[1].trim(),
                                            Double.parseDouble(p[2].trim()),
                                            Double.parseDouble(p[3].trim()));
                    Localidad destino = new Localidad(p[4].trim(), p[5].trim(),
                                            Double.parseDouble(p[6].trim()),
                                            Double.parseDouble(p[7].trim()));
                    lista.add(new Arista(origen, destino, Double.parseDouble(p[8].trim())));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lista;
    }
}