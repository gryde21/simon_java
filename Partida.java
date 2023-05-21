import java.util.*;
import java.io.*;

public class Partida {
    private static final int MAX_INTENTOS = 15; //intentos maximos
    private static final Random random = new Random(); //aleaotorios
    private final String nombreJugador; //constructor crea una instancia de la partida con el nombre del jugador proporcionado.

    public Partida(String nombreJugador) {
        this.nombreJugador = nombreJugador;
    } //constructor crea una instancia de la partida con un nombre de jugador predeterminado.

    public Partida() {
        this.nombreJugador = "Jugador Desconocido";
    }

    public void jugarPartidaJugadorVsCPU() { // inicia la partida entre el jugador y la CPU.
        System.out.println("Comienza la partida Jugador vs CPU. ¡Buena suerte, " + nombreJugador + "!");
        List<Integer> secuenciaCPU = new ArrayList<>(); //se utiliza para almacenar la secuencia generada por la CPU.
        int numAciertos = 0; //se utiliza para contar el número de aciertos del jugador.

        for (int i = 0; i < MAX_INTENTOS; i++) { //bucle for que repite 15 veces, representando los intentos del jugador en la partida.
            int numeroCPU = generarNumeroAleatorio(); // se genera un número aleatorio numeroCPU mediante el método generarNumeroAleatorio() y se agrega a la lista secuenciaCPU.
            secuenciaCPU.add(numeroCPU);

            System.out.print("Secuencia CPU: "); //Se imprime la secuencia generada por la CPU.
            for (int num : secuenciaCPU) {
                System.out.print(num + " ");
            }
            System.out.println();

            System.out.print("Ingrese su número: ");
            Scanner scanner = new Scanner(System.in);
            String entradaJugador = scanner.nextLine();

            List<Integer> secuenciaJugador = parsearSecuencia(entradaJugador); //entrada del jugador se analiza y se convierte en una lista de enteros utilizando el método
            if (secuenciaJugador.get(0).equals(secuenciaCPU.get(0))) { //verifacamos si coinciden los numeros
                System.out.println("¡Has acertado el número!");
                numAciertos++; //si es que si, + aciertos mas menasje
            } else {
                System.out.println("¡Fallaste! El número correcto era: " + secuenciaCPU.get(0));
                guardarPuntuacion(nombreJugador, numAciertos);
                return; //si es que no, error se guarda puntuacion y adios a la partida
            }

            if (secuenciaJugador.size() == secuenciaCPU.size()) { // verifica si la secuencia del jugador tiene la misma longitud que la secuencia de la CPU.
                if (i == MAX_INTENTOS - 1) {
                    System.out.println("¡Felicidades, " + nombreJugador + "! Has completado la secuencia.");
                    guardarPuntuacion(nombreJugador, MAX_INTENTOS);
                } else {
                    System.out.println("¡Bien hecho! Continúa con la siguiente ronda.");
                }
            }
        }

        guardarPuntuacion(nombreJugador, numAciertos); //guardamos puntuacion
        System.out.println("Has llegado al final de los " + MAX_INTENTOS + " intentos disponibles."); //si el jugador es un pro
    }


    public void jugarPartidaArchivoVsArchivo(String archivoCPU, String archivoJugador) { //inicia una partida utilizando las secuencias cargadas desde los archivos proporcionados.
        List<Integer> secuenciaCPU = cargarSecuenciaDesdeArchivo(archivoCPU);
        List<Integer> secuenciaJugador = cargarSecuenciaDesdeArchivo(archivoJugador);
        //Se cargan las secuencias de la CPU y del jugador utilizando el método

        String nombreJugador = obtenerNombreJugador(archivoJugador); //obtenemos el nombre del jugador
        if (nombreJugador.isEmpty()) {
            System.out.println("No se pudo obtener el nombre del jugador."); //sino lo conseguimos
            return;
        }
        //dos variables nuevas
        int numAciertos = 0;
        int rondas = Math.min(secuenciaCPU.size(), secuenciaJugador.size());
        for (int i = 0; i < rondas; i++) { //bucle que controla las rondas
            int numeroCPU = secuenciaCPU.get(i);
            int numeroJugador = secuenciaJugador.get(i);
            //en cada repeticion se obtienen los numeros de la cpu y el jugador para la ronda actual

            System.out.println("Ronda " + (i + 1) + ":");
            System.out.println("Número CPU: " + numeroCPU);
            System.out.println("Número Jugador: " + numeroJugador);
            //enseñamos el numero de la cpu y el jugador

            if (numeroCPU != numeroJugador) { //se verifica que los numeros son diferentes
                System.out.println("¡Fallaste en la ronda " + (i + 1) + "!"); //si son diferentes se muestra mensaje error
                guardarPuntuacion(nombreJugador, numAciertos);
                return;
            }

            numAciertos++;// sino incrementamos los aicertos
        }

        if (secuenciaJugador.size() > secuenciaCPU.size()) { //se verifica si la secuencia del jugador es más larga que la secuencia de la CPU.
            System.out.println("¡Felicidades, " + nombreJugador + "! Has completado la secuencia."); //
            numAciertos = secuenciaCPU.size(); // Corregir la variable numAciertos
            guardarPuntuacion(nombreJugador, numAciertos); //guardamos puntuacion
        } else {
            System.out.println("Has llegado al final de la secuencia disponible.");
            guardarPuntuacion(nombreJugador, numAciertos); ////guardamos puntuacion
        }

        System.out.println("Jugador: " + nombreJugador);
        System.out.println("Aciertos: " + numAciertos);
        //se muestra el nombre del jugador y los aciertos
    }
    private String obtenerNombreJugador(String archivoJugador) { // lee el nombre del jugador desde el archivo proporcionado.
        try (Scanner scanner = new Scanner(new File(archivoJugador))) { //para asegurarse de que el Scanner se cierre correctamente.
            if (scanner.hasNext()) {
                return scanner.next(); //Si el archivo tiene una entrada, se devuelve el siguiente token (el nombre del jugador).
            }
        } catch (FileNotFoundException e) { //si ocurre un error,  se muestra un mensaje de error y se retorna una cadena vacía.
            System.out.println("Archivo " + archivoJugador + " no encontrado.");
        }
        return "";
    }

    private int generarNumeroAleatorio() {
        return random.nextInt(4) + 1;
    } // genera un número aleatorio entre 1 y 4 (ambos inclusive) utilizando la instancia random

    private List<Integer> parsearSecuencia(String entrada) { // convierte una cadena de números separados por comas en una lista de enteros.
        List<Integer> secuencia = new ArrayList<>();
        String[] numeros = entrada.split(","); //Divide la cadena utilizando la coma como delimitador y luego convierte cada número en un entero

        for (String numero : numeros) {
            secuencia.add(Integer.parseInt(numero.trim()));
        }//Los números se agregan a la lista secuencia después de eliminar cualquier espacio en blanco alrededor de cada número.

        return secuencia; //Devuelve la lista de enteros resultante.

    }

    private List<Integer> cargarSecuenciaDesdeArchivo(String archivo) {// lee una secuencia de números desde el archivo especificado y la devuelve como una lista de enteros.
        List<Integer> secuencia = new ArrayList<>();
        try (Scanner scanner = new Scanner(new File(archivo))) {//para asegurarse de que el Scanner se cierre correctamente.
            scanner.useDelimiter(",|\\s"); // Delimitador: coma o espacio
            while (scanner.hasNext()) { // se lee un entero del archivo y se agrega a la lista secuencia.
                if (scanner.hasNextInt()) {
                    secuencia.add(scanner.nextInt());
                } else {
                    scanner.next(); // Ignorar elementos no numéricos
                }
            }
        } catch (FileNotFoundException e) {//error por si ocurre
            System.out.println("Archivo " + archivo + " no encontrado.");
        }
        return secuencia;
    }

    private void guardarPuntuacion(String nombreJugador, int puntuacion) {//guarda la puntuación del jugador en un archivo de texto llamado "puntuaciones.txt".
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(JuegoSimon.ARCHIVO_PUNTUACIONES, true))) {// para asegurarse de que el PrintWriter se cierre correctamente.
            writer.write(nombreJugador + " " + puntuacion);
            writer.newLine();//Se escribe una línea en el archivo en el formato "nombreJugador,puntuacion".
        } catch (IOException e) {//error por si acaso
            System.out.println("Error al escribir en el archivo de puntuaciones: " + e.getMessage());
        }
    }

}
