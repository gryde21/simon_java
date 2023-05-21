import java.io.*;
import java.util.*;

public class JuegoSimon {
    public static final String ARCHIVO_PUNTUACIONES = "puntuaciones.txt";
    //Se define una constante: ARCHIVO_PUNTUACIONES que representa el nombre del archivo de puntuaciones.

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        boolean salir = false;
        //Se inicializa la variable salir como false para controlar la ejecución del bucle del menú.

        while (!salir) {
            System.out.println("----- Menú -----");
            System.out.println("1. Partida jugador vs CPU");
            System.out.println("2. Partida fitxer_cpu vs fitxer_jugador");
            System.out.println("3. Mostrar tabla de puntuaciones");
            System.out.println("4. Salir");

            System.out.print("Seleccione una opción: ");
            int opcion = scanner.nextInt();
            //menu

            switch (opcion) { //switch para eligir opcion del menu
                case 1 -> {
                    // Opción 1: Partida jugador vs CPU
                    scanner.nextLine(); // Limpiar el buffer de entrada
                    System.out.print("Ingrese el nombre del jugador: ");
                    String nombreJugador = scanner.nextLine();
                    Partida partidaJugadorVsCPU = new Partida(nombreJugador);
                    partidaJugadorVsCPU.jugarPartidaJugadorVsCPU();
                }
                case 2 -> {
                    // Opción 2: Partida fitxer_cpu vs fitxer_jugador
                    System.out.print("Ingrese el nombre del archivo con los números de la CPU: ");
                    String archivoCPU = scanner.next();
                    System.out.print("Ingrese el nombre del archivo con las jugadas del jugador: ");
                    String archivoJugador = scanner.next();
                    Partida partidaArchivoVsArchivo = new Partida();
                    partidaArchivoVsArchivo.jugarPartidaArchivoVsArchivo(archivoCPU, archivoJugador);
                }
                case 3 ->
                    // Opción 3: Mostrar tabla de puntuaciones
                        mostrarPuntuaciones();
                case 4 ->
                    // Opción 4: Salir
                        salir = true;
                default -> System.out.println("Opción no válida. Por favor, seleccione una opción válida.");
            }

            System.out.println();
        }

        scanner.close(); //cerramos scaner
    }

    private static void mostrarPuntuaciones() {
        List<Puntuacion> puntuaciones = cargarPuntuaciones();
        puntuaciones.sort(Comparator.comparingInt(Puntuacion::getPuntuacion));

        System.out.println("----- Tabla de puntuaciones -----");
        if (puntuaciones.isEmpty()) {
            System.out.println("No hay puntuaciones registradas.");
        } else {
            for (Puntuacion puntuacion : puntuaciones) {
                System.out.println(puntuacion.getNombreJugador() + " - " + puntuacion.getPuntuacion() + " aciertos");
            }
        }
        System.out.println("---------------------------------");
    }


    private static List<Puntuacion> cargarPuntuaciones() { //metodo que ayuda al anterior a cargar las puntuaciones
        List<Puntuacion> puntuaciones = new ArrayList<>(); //Se crea una lista vacía de Puntuacion.

        try (BufferedReader reader = new BufferedReader(new FileReader(ARCHIVO_PUNTUACIONES))) { //leemos el archivo de puntuaciones
            String linea;
            while ((linea = reader.readLine()) != null) { //Se lee cada línea del archivo y se divide en partes utilizando el espacio como separador.
                String[] partes = linea.split(" ");
                if (partes.length == 2) { //Si la línea contiene exactamente dos partes, se extrae el nombre del jugador y la puntuación.
                    String nombreJugador = partes[0];
                    int puntuacion = Integer.parseInt(partes[1]);
                    puntuaciones.add(new Puntuacion(nombreJugador, puntuacion)); //Se crea un nuevo objeto Puntuacion con el nombre y la puntuación y se agrega a la lista de puntuaciones.
                }
            }
        } catch (IOException e) { //Si ocurre una excepción al leer el archivo, se muestra un mensaje de error.
            System.out.println("Error al leer el archivo de puntuaciones: " + e.getMessage());
        }

        return puntuaciones; //asegura que la lista de puntuaciones sea devuelta para su uso posterior en otro contexto.
    }
}
