import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
public class controller {
    private final Map<String, Nodo> city= new HashMap<>();
    private final Map<String, Integer> cityIndex = new HashMap<>();
    private String[] cityNames;

    private FloydWarshall floydWarshall;

    private ArrayList<String> logica = new ArrayList<>();

    public controller() throws IOException {
        achieve();
        crearFloydWarshall();
    }

    public void achieve() throws IOException {
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

            String nameCityOrigin = datos[0];
            String DestinyCity = datos[1];

            Integer[] time = new Integer[4];
            for (int i = 0; i < 4; i++) {
                time[i] = Integer.parseInt(datos[i + 2]);
            }
            Nodo cityNames = city.getOrDefault(nameCityOrigin, new Nodo(nameCityOrigin));
            cityNames.addDistance( DestinyCity, time);  // Asume que el tiempo de regreso es el mismo
            city.putIfAbsent(nameCityOrigin, cityNames);

            Nodo  Destiny = city.getOrDefault( DestinyCity, new Nodo( DestinyCity));
            Destiny.addDistance(nameCityOrigin, time);  // Asume que el tiempo de regreso es el mismo
            city.putIfAbsent( DestinyCity, Destiny);
        }

    }
    public void mod(String linea){
        File archivo = new File(".\\src\\logistica.txt");

        try {
            FileWriter writer = new FileWriter(archivo);
            for (String s : logica) {
                writer.write(s + "\n");
            }
            writer.write(linea);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public Integer getIndiceCiudadPorNombre(String nombre) {
        Integer indice = cityIndex.get(nombre);
        if (indice == null) {
            System.out.println("No se encontró el índice para la ciudad: " + nombre);
            return -1; // o algún otro valor de error
        } else {
            return indice;
        }
    }

    public String getCentroDelGrafo() {
        int[][] distancias = floydWarshall.getDistancias();

        int minMaxDistancia = Integer.MAX_VALUE;
        String centro = null;

        for (String ciudad : city.keySet()) {
            int indiceCiudad = getIndiceCiudadPorNombre(ciudad);

            int maxDistancia = Integer.MIN_VALUE;

            for (int i = 0; i < distancias[indiceCiudad].length; i++) {
                if (i != indiceCiudad) {
                    maxDistancia = Math.max(maxDistancia, distancias[indiceCiudad][i]);
                }
            }

            if (maxDistancia < minMaxDistancia) {
                minMaxDistancia = maxDistancia;
                centro = ciudad;
            }
        }

        return centro;
    }

    private void crearFloydWarshall() {
        int n = city.size();
        // se inicializan las matrices
        int[][] distance = new int[n][n];
        String[][] travel = new String[n][n];

        //se llenan las matrices
        for (int i = 0; i < n; i++) {
            Arrays.fill(distance[i], Integer.MAX_VALUE);
            distance[i][i] = 0;
            Arrays.fill(travel[i], "-");
        }

        cityNames = new String[n];
        for (int i = 0; i < n; i++) {
            cityNames[i] = (String) city.keySet().toArray()[i];
            cityIndex.put( cityNames[i], i);
        }

        //se crea la matriz de recorridos
        for (int j = 0; j < n; j++) {
            for (int i = 0; i < n; i++) {
                if (i != j && travel[i][j].equals("-")) {
                    travel[i][j] =  cityNames[j];
                }
            }
        }


        //se crea la matriz de distancias
        for (Nodo ciudad : city.values()) {
            for (Map.Entry<String, Integer[]> entry : ciudad.getTiemposViaje().entrySet()) {
                int origen = getIndiceCiudadPorNombre(ciudad.getNombre());
                int destino = getIndiceCiudadPorNombre(entry.getKey());
                if (origen != -1 && destino != -1) { // Para asegurarnos de que origen y destino existen
                    distance[origen][destino] = entry.getValue()[0];
                }
            }
        }

        this.floydWarshall = new FloydWarshall(distance, travel, n, cityNames);

        floydWarshall.CalcularRutas();

        travel = floydWarshall.getRecorridos();
        distance = floydWarshall.getDistancias();

    }



    public String getNombreCiudadPorIndice(int index) {
        return cityNames[index];
    }

    public ArrayList<String> getShot(String cityOut, String cityIn) {
        int originIndex = getIndiceCiudadPorNombre(cityOut);
        int destinyIndex = getIndiceCiudadPorNombre(cityIn);

        if (originIndex == -1 || destinyIndex == -1) {
            return null;
        }
        String[][] recorridos = floydWarshall.getRecorridos();

        ArrayList<String> ruta = new ArrayList<>();

        if (recorridos[destinyIndex][originIndex].equals("-")) {
            ruta.add(cityOut);
            ruta.add(cityIn);
            return ruta;
        } else if (!recorridos[destinyIndex][originIndex].equals(cityOut)) {
            ruta.addAll(getShot(cityOut, recorridos[destinyIndex][originIndex]));
            ruta.add(cityIn);
        } else {
            ruta.add(cityOut);
            ruta.add(cityIn);
        }

        return ruta;
    }




}


