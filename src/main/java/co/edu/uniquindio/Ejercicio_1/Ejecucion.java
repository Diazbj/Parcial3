package co.edu.uniquindio.Ejercicio_1;



import co.edu.uniquindio.Ejercicio_1.Utils.Persistencia;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Ejecucion extends Thread {

    public static void main(String[] args) {
        // Definir el array de caracteres
        // Leer archivo letras.txt
        List<Character> caracteres = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("src/main/resources/Letras.txt"))) {
            String line = br.readLine();
            for (String part : line.split(",")) {
                caracteres.add(part.charAt(0));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Crear el buffer y la cola de caracteres objetivo
        Buffer buffer = new Buffer();
        Queue<Character> caracteresObjetivo = new LinkedList<>();
        String palabraObjetivo = "progr@macion_3#2023%";
//        String palabraObjetivo = "prog";
        for (char c : palabraObjetivo.toCharArray()) {
            caracteresObjetivo.add(c);
        }

        // Lista para letras no utilizadas
        List<Character> letrasNoUsadas = new ArrayList<>();

        // Crear productores y consumidor con el array de caracteres
        P2 p2 = new P2(buffer, caracteres, caracteresObjetivo);
        P1 p1 = new P1(buffer, caracteres, caracteresObjetivo);
        Consumidor consumidor = new Consumidor(buffer, letrasNoUsadas, palabraObjetivo);

        // Iniciar hilos
        p2.start();
        p1.start();
        consumidor.start();

        // Esperar a que todos los hilos terminen
        try {
            consumidor.join();
            p2.join();
            p1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Detener la producción del consumidor
        consumidor.detenerProduccion();

        // Mostrar resultados
        System.out.println("Palabra formada: " + consumidor.getPalabraFormada());
        System.out.println("Letras no utilizadas: " + letrasNoUsadas);
        Persistencia.guardarLetrasSobrantes(letrasNoUsadas);

    }
}