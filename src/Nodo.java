import java.util.HashMap;
import java.util.Map;

public class Nodo {
    private String name;
    private Map<String, Integer[]> tiemposViaje;

    public Node (String nombre) {
        this.name = nombre;
        this.tiemposViaje = new HashMap<>();
    }

    public String getNombre() {
        return this.name;
    }

    public void addDistance(String ciudadDestino, Integer[] tiempos) {
        this.tiemposViaje.put(ciudadDestino, tiempos);
    }

    public Map<String, Integer[]> getTiemposViaje() {
        return this.tiemposViaje;
    }
}

