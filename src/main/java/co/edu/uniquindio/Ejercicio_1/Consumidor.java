package co.edu.uniquindio.Ejercicio_1;



import co.edu.uniquindio.Ejercicio_1.Utils.ArchivoLogger;
import co.edu.uniquindio.Ejercicio_1.Utils.Persistencia;

import java.util.Arrays;
import java.util.List;
public class Consumidor extends Thread {
    private Buffer buffer;
    private List<Character> letrasNoUsadas;
    private String palabraFormada = "";
    private String palabraObjetivo;
    private boolean detenerProduccion = false;

    public Consumidor(Buffer buffer, List<Character> letrasNoUsadas, String palabraObjetivo) {
        this.buffer = buffer;
        this.letrasNoUsadas = letrasNoUsadas;
        this.palabraObjetivo = palabraObjetivo;
    }

    public void detenerProduccion() {
        detenerProduccion = true;
    }


    @Override
    public void run() {
        while (!palabraCompleta() && !detenerProduccion) {
            char[] letras = buffer.recoger();
            System.out.println("Consumidor recogio: " + Arrays.toString(letras));


            for (char letra : letras) {
                if (palabraFormada.length() < palabraObjetivo.length()) {
                    if (esParteDeLaPalabra(letra)) {
                        int indice = palabraFormada.length();
                        palabraFormada=palabraFormada.substring(0,indice)+letra+palabraFormada.substring(indice);
                    } else {
                        if (letra == ' '||letra=='\0' ){

                        }else{
                            letrasNoUsadas.add(letra);
                        }

                    }
                }
            }
            System.out.println("Palabra formada hasta ahora: " + palabraFormada);
            if (palabraCompleta()) {
                // Acción cuando se completa la palabra
                String mensaje = "Palabra formada completada: " + palabraFormada;
                ArchivoLogger.guardarRegistroLog(mensaje, 1, "PalabraCompletada", "src/main/resources/Log/PalabraFormadaLog.txt");
            }
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Palabra formada final: " + palabraFormada);
        System.out.println("Letras no utilizadas final: " + letrasNoUsadas);
        Persistencia.guardarLetrasSobrantes(letrasNoUsadas);

    }

    private boolean palabraCompleta() {
        return palabraFormada.equals(palabraObjetivo);
    }

    private boolean esParteDeLaPalabra(char letra) {
        int indice = palabraFormada.length();
        return indice < palabraObjetivo.length() && palabraObjetivo.charAt(indice) == letra;
    }

    public String getPalabraFormada() {
        return palabraFormada;
    }
}