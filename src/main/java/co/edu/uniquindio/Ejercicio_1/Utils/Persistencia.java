package co.edu.uniquindio.Ejercicio_1.Utils;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class Persistencia {
    private static final String RUTA_ARCHIVO_LETRAS = "src/main/resources/Respaldo/LetrasSobrantes.txt";
    private static final int LINE_LENGTH = 30; // Longitud máxima de la línea antes de hacer un salto de línea

    public static void guardarLetrasSobrantes(List<Character> letrasSobrantes) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(RUTA_ARCHIVO_LETRAS))) {
            StringBuilder sb = new StringBuilder("[");
            int charCount = 1; // Iniciar en 1 para incluir el primer carácter '['
            for (char letra : letrasSobrantes) {
                sb.append(letra).append(", "); // Agregar la letra seguida de una coma y un espacio
                charCount++;
                // Si se alcanza la longitud máxima de línea, hacer un salto de línea
                if (charCount == LINE_LENGTH) {
                    sb.append("\n");
                    charCount = 0;
                }
            }
            // Eliminar la última coma y espacio si hay letras sobrantes
            if (sb.length() > 1) { // Verificar que hayan letras sobrantes
                sb.setLength(sb.length() - 2); // Eliminar la coma y espacio finales
            }
            sb.append("]"); // Agregar el corchete de cierre
            // Escribir las letras sobrantes en el archivo
            writer.write(sb.toString());
            System.out.println("Lista de letras sobrantes guardada en " + RUTA_ARCHIVO_LETRAS);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}