import java.util.Scanner;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {
    private Juego juego;

    public Menu() {
        juego = new Juego();
    }

    public void mostrar() {
        Scanner scanner = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("");
            System.out.println("----- Menú Principal -----");
            System.out.println("1. Partida jugador vs CPU");
            System.out.println("2. Partida archivo CPU vs archivo jugador");
            System.out.println("3. Mostrar tabla de puntuaciones");
            System.out.println("4. Salir");
            System.out.print("Selecciona una opción: ");
            System.out.println("");
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    scanner.nextLine(); // Limpiar el buffer de entrada
                    System.out.print("Ingresa el nombre del jugador: ");
                    String nombreJugador = scanner.nextLine();
                    juego.partidaJugadorVsCPU(nombreJugador);
                    break;
                case 2:
                    scanner.nextLine(); // Limpiar el buffer de entrada
                    System.out.print("Ingresa la ruta del archivo CPU: ");
                    String rutaCPU = scanner.nextLine();
                    System.out.print("Ingresa la ruta del archivo jugador: ");
                    String rutaJugador = scanner.nextLine();

                    if (rutaCPU.isEmpty() || rutaJugador.isEmpty()) {
                        System.out.println("Error: Las rutas de los archivos no pueden estar vacías.");
                    } else {
                        juego.partidaArchivoVsArchivo(rutaCPU, rutaJugador);
                    }

                    break;
                case 3:
                    juego.mostrarPuntuaciones();
                    break;
                case 4:
                    System.out.println("¡Hasta luego!");
                    break;
                default:
                    System.out.println("Opción inválida. Por favor, selecciona una opción válida.");
                    break;
            }
        } while (opcion != 4);
    }
}

