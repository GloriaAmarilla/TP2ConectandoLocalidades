package logica;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CargaDatos {

    private static String archivosDeLocalidades = "localidades.txt";
    private static String archivosDeAristas     = "aristas.txt";


    // Guarda todas las localidades del grafo
    public static void guardarLocalidades(List<Localidad> localidades) {
    	
    	List<Localidad> existentes = cargarLocalidades();
    	
        try (PrintWriter pw = new PrintWriter(new FileWriter(archivosDeLocalidades, true))) {
            for (Localidad l : localidades) {
            	
            	boolean existe = false;
            	
            	for (Localidad e : existentes) {
                    if (e.getNombre().equalsIgnoreCase(l.getNombre()) &&
                        e.getProvincia().equalsIgnoreCase(l.getProvincia()) &&
                        e.getLatitud() == l.getLatitud() &&
                        e.getLongitud() == l.getLongitud()) {
                        existe = true;
                        break;
                    }
                }
            	if (!existe) {
            		pw.println(l.getNombre() + "," +
            				l.getProvincia() + "," +
            				l.getLatitud() + "," +
            				l.getLongitud());
            	}
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Carga las localidades desde el archivo
    public static List<Localidad> cargarLocalidades() {
        List<Localidad> lista = new ArrayList<>();
        File f = new File(archivosDeLocalidades);
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
    	
    	List<Arista> existentes = cargarAristas();
    	
        try (PrintWriter pw = new PrintWriter(new FileWriter(archivosDeAristas, true))) {
            for (Arista a : aristas) {
            	boolean existe = false;

                for (Arista e : existentes) {
                    if (e.getOrigen().getNombre().equalsIgnoreCase(a.getOrigen().getNombre()) &&
                    	e.getDestino().getNombre().equalsIgnoreCase(a.getDestino().getNombre()) &&
                    	e.getCosto() == a.getCosto()) {
                    	existe = true;
                        break;
                    }
                }
                
                if (!existe) {
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
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Carga las aristas del AGM
    public static List<Arista> cargarAristas() {
        List<Arista> lista = new ArrayList<>();
        File f = new File(archivosDeAristas);
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
    
    public static void limpiarArchivos() {
    	
        try {
            new PrintWriter(new FileWriter(archivosDeLocalidades, false)).close();
            new PrintWriter(new FileWriter(archivosDeAristas, false)).close();
        }
        
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}