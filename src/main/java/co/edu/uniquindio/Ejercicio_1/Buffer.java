package co.edu.uniquindio.Ejercicio_1;


public class Buffer {
    private char[] buffer = new char[15];
    private int siguiente = 0;
    private boolean estaLleno = false;
    private boolean estaVacio = true;

    public synchronized void lanzar(char c) {
        while (estaLleno) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        buffer[siguiente] = c;
        siguiente++;

        if (siguiente == 15) {
            estaLleno = true;
        }
        estaVacio = false;
        notifyAll();
    }

    public synchronized char[] recoger() {
        while (siguiente < 5) { // Esperar hasta que haya al menos 3 elementos en el buffer
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        int cantidadCaracteres = Math.min(5, siguiente); // Determinar cuántos caracteres recoger
        char[] letras = new char[cantidadCaracteres];

        for (int i = 0; i < cantidadCaracteres; i++) {
            letras[i] = buffer[siguiente - cantidadCaracteres + i];
        }

        siguiente -= cantidadCaracteres;

        if (siguiente == 0) {
            estaVacio = true;
            // Notificar al productor que el buffer está vacío
            notifyAll();
        }
        estaLleno = false;
        notifyAll();

        return letras;
    }
}