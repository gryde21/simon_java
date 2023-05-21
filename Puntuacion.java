public class Puntuacion implements Comparable<Puntuacion> {
    private final String nombreJugador;
    private final int puntuacion;

    public Puntuacion(String nombreJugador, int puntuacion) { // se utiliza para crear una nueva instancia de Puntuacion con un nombre de jugador y una puntuación.
        this.nombreJugador = nombreJugador;
        this.puntuacion = puntuacion;
    }

    public String getNombreJugador() {
        return nombreJugador;
    }

    public int getPuntuacion() {
        return puntuacion;
    }// son dos métodos de acceso que devuelven el nombre del jugador y la puntuación, respectivamente.

    @Override
    public int compareTo(Puntuacion otraPuntuacion) {
        return Integer.compare(puntuacion, otraPuntuacion.getPuntuacion());// En este caso, se utiliza Integer.compare() para comparar las puntuaciones directamente.
    }// se implementa para permitir la comparación de puntuaciones. Compara las puntuaciones de dos objetos Puntuacion y devuelve un valor entero que indica su orden relativo.

}
