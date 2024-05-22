package co.edu.uniquindio.Ejercicio_1;

import java.util.List;
import java.util.Queue;
import java.util.Random;

public class P1 extends Thread {
    private Buffer buffer;
    private List<Character> caracteres;
    private Queue<Character> caracteresObjetivo;

    public P1(Buffer buffer, List<Character> caracteres, Queue<Character> caracteresObjetivo) {
        this.buffer = buffer;
        this.caracteres = caracteres;
        this.caracteresObjetivo = caracteresObjetivo;
    }

    @Override
    public void run() {
        Random rand = new Random();
        int index = 0;
        while (true) {
            char caracter = caracteres.get(rand.nextInt(caracteres.size()));
            if (esDigito(caracter)||esVocal(caracter)) {
                buffer.lanzar(caracter);
                if(esDigito(caracter)){
                    System.out.println("Productor P1 lanza el numeros: " + caracter);
                }else {
                    System.out.println("Productor P1 lanza la vocal: " + caracter);
                }

            }
            index++;
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    // Método para verificar si un caracter es numero
    public static boolean esDigito(char c) {
        // Verificar si el carácter es numero
        if (c == '0' || c == '1' || c == '2' || c == '3' || c == '4' || c == '5' || c == '6' || c == '7' || c == '8' || c == '9') {
            return true;
        }
        return false;
    }
    public static boolean esVocal(char c) {
        // Verificar si el carácter es una vocal en minúscula
        if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u') {
            return true;
        }
        // Verificar si el carácter es una vocal en mayúscula
        if (c == 'A' || c == 'E' || c == 'I' || c == 'O' || c == 'U') {
            return true;
        }
        // Si no es ninguna de las anteriores, no es una vocal
        return false;
    }
}