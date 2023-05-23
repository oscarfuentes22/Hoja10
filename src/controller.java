import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
public class controller {
    private Map<String, Nodo> city= new HashMap<>();
    private Map<String, Integer> cityIndex = new HashMap<>();
    private String[] cityNames;

    private edu.uvg.graphs.FloydWarshall floydWarshall;

    private ArrayList<String> logica = new ArrayList<>();

    public controller() {
        achieve();
        crearFloydWarshall();
    }
    public void mod(String linea){
        File archivo = new File(".\\src\\logistica.txt");

        try {
            FileWriter writer = new FileWriter(archivo);
            for (int i = 0; i < logica.size(); i++) {
                writer.write(logica.get(i) + "\n");
            }
            writer.write(linea);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void achieve() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(".\\src\\logistica.txt"));

            String line;
            while ((line = reader.readLine()) != null) {
                logica.add(line);
                String[] datos = line.split(" ");

                // Verificamos si los datos son correctos y completos
                if (datos.length != 6) {
                    System.out.println("Línea con formato incorrecto: " + line);
                    continue; // Ignoramos esta línea y pasamos a la siguiente
                }

                String ciudadOrigenNombre = datos[0];
                String ciudadDestinoNombre = datos[1];

                Integer[] tiempos = new Integer[4];
                for (int i = 0; i < 4; i++) {
                    tiempos[i] = Integer.parseInt(datos[i + 2]);
                }

