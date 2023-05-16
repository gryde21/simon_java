import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class Puntuaciones {
    private static final String RUTA_PUNTUACIONES = "puntuaciones.txt";

    public Map<String, Integer> leerPuntuaciones() {
        Map<String, Integer> puntuaciones = new HashMap<>();

        try {
            Files.lines(Paths.get(RUTA_PUNTUACIONES))
                    .map(linea -> linea.split(", "))
                    .forEach(arr -> puntuaciones.put(arr[0], Integer.parseInt(arr[1])));
        } catch (IOException e) {
            System.out.println("Error al leer el archivo de puntuaciones.");
        }

        return puntuaciones;
    }

    public void escribirPuntuacion(String nombre, int puntuacion) {
        try (FileWriter writer = new FileWriter(RUTA_PUNTUACIONES, true)) {
            writer.write(nombre + ", " + puntuacion + "\n");
        } catch (IOException e) {
            System.out.println("Error al escribir en el archivo de puntuaciones.");
        }
    }
}
