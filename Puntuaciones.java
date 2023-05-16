//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class Puntuaciones {
    private static final String RUTA_PUNTUACIONES = "puntuaciones.txt";

    public Puntuaciones() {
    }

    public Map<String, Integer> leerPuntuaciones() {
        Map<String, Integer> puntuaciones = new HashMap();

        try {
            Files.lines(Paths.get("puntuaciones.txt")).map((linea) -> {
                return linea.split(", ");
            }).forEach((arr) -> {
                puntuaciones.put(arr[0], Integer.parseInt(arr[1]));
            });
        } catch (IOException var3) {
            System.out.println("Error al leer el archivo de puntuaciones.");
        }

        return puntuaciones;
    }

    public void escribirPuntuacion(String nombre, int puntuacion) {
        try {
            FileWriter writer = new FileWriter("puntuaciones.txt", true);

            try {
                writer.write(nombre + ", " + puntuacion + "\n");
            } catch (Throwable var7) {
                try {
                    writer.close();
                } catch (Throwable var6) {
                    var7.addSuppressed(var6);
                }

                throw var7;
            }

            writer.close();
        } catch (IOException var8) {
            System.out.println("Error al escribir en el archivo de puntuaciones.");
        }

    }
}
