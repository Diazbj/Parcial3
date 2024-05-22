package co.edu.uniquindio.Ejercicio_1;

import java.util.List;
import java.util.Queue;
import java.util.Random;

public class P2 extends Thread {
    private Buffer buffer;
    private List<Character> caracteres;
    private Queue<Character> caracteresObjetivo;

    public P2(Buffer buffer, List<Character> caracteres, Queue<Character> caracteresObjetivo) {
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
            if (esCaracter(caracter)||esConsonante(caracter)) {
                buffer.lanzar(caracter);
                if(esCaracter(caracter)){
                    System.out.println("Productor P2 lanza el caracter: " + caracter);
                }else {
                    System.out.println("Productor P2 lanza la consonante: " + caracter);
                }
            }
            index++;
            try {
                Thread.sleep(150);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    // Método para verificar si un caracter es una vocal
    public static boolean esCaracter(char c) {
        // Verificar si el carácter es una caracter especial.
        if (c == '@' || c == '#' || c == '/' || c == '%' || c == '+' || c == ':' || c == ';' || c=='_') {
            return true;
        }
        return false;
    }
    private boolean esConsonante(char c) {
        return !P1.esVocal(c)&&!P1.esDigito(c)&&!esCaracter(c);
    }
}