import java.util.Random;

public class Juego {
    private Puntuaciones puntuaciones;

    public Juego() {
        puntuaciones = new Puntuaciones();
    }

    public void partidaJugadorVsCPU(String nombreJugador) {
        System.out.println("Iniciando partida jugador vs CPU...");
        int maxIntentos = 15;
        int[] numerosCPU = generarNumerosCPU(maxIntentos);
        Jugador jugador = new Jugador(nombreJugador);
        int intentosAciertados = jugador.jugar(numerosCPU);

        if (intentosAciertados == maxIntentos) {
            System.out.println("¡Felicidades, has ganado! Has acertado todos los números.");
        } else {
            System.out.println("¡Error! Número de aciertos: " + intentosAciertados);
        }

        puntuaciones.escribirPuntuacion(nombreJugador, intentosAciertados);
    }

    public void partidaArchivoVsArchivo(String rutaCPU, String rutaJugador) {
        // Implementación de la partida a partir de los archivos
    }

    public void mostrarPuntuaciones() {
        // Implementación para mostrar las puntuaciones
    }

    private int[] generarNumerosCPU(int cantidad) {
        int[] numeros = new int[cantidad];
        Random random = new Random();

        for (int i = 0; i < cantidad; i++) {
            numeros[i] = random.nextInt(4) + 1;
        }

        return numeros;
    }
}
