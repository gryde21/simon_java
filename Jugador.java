import java.util.Scanner;

public class Jugador {
    private String nombre;

    public Jugador(String nombre) {
        this.nombre = nombre;
    }

    public int jugar(int[] numerosCPU) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Introduce tus números separados por coma: ");
        String numerosJugadorString = scanner.nextLine();
        String[] numerosJugadorArray = numerosJugadorString.split(",");
        int[] numerosJugador = new int[numerosJugadorArray.length];

        for (int i = 0; i < numerosJugadorArray.length; i++) {
            numerosJugador[i] = Integer.parseInt(numerosJugadorArray[i].trim());
        }

        int intentosAciertados = 0;

        for (int i = 0; i < numerosJugador.length; i++) {
            if (numerosJugador[i] == numerosCPU[i]) {
                intentosAciertados++;
            } else {
                break;
            }
        }

        return intentosAciertados;
    }
}
