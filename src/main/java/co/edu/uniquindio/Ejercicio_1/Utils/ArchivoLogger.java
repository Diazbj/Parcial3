package co.edu.uniquindio.Ejercicio_1.Utils;
import java.io.IOException;
import java.util.Calendar;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class ArchivoLogger {
    static String fechaSistema = "";
    private static final String LOG_FILE_PATH = "Log/PalabraFormadaLog.txt";

    public static void guardarRegistroLog(String mensajeLog, int nivel, String accion, String rutaArchivo) {
        Logger LOGGER = Logger.getLogger(accion);
        FileHandler fileHandler = null;
        cargarFechaSistema();
        try {
            fileHandler = new FileHandler(rutaArchivo, true);
            fileHandler.setFormatter(new SimpleFormatter());
            LOGGER.addHandler(fileHandler);
            String nivelStr = "";
            switch (nivel) {
                case 1:
                    nivelStr = "INFO";
                    break;
                case 2:
                    nivelStr = "WARNING";
                    break;
                case 3:
                    nivelStr = "SEVERE";
                    break;
            }
            String mensaje = String.format("[%s] %s - %s - %s", nivelStr, fechaSistema, accion, mensajeLog);
            LOGGER.log(Level.INFO, mensaje);
        } catch (SecurityException | IOException e) {
            e.printStackTrace();
        } finally {
            if (fileHandler != null) {
                fileHandler.close();
            }
        }
    }
    private static void cargarFechaSistema() {

        String diaN = "";
        String mesN = "";
        String añoN = "";

        Calendar cal1 = Calendar.getInstance();


        int  dia = cal1.get(Calendar.DATE);
        int mes = cal1.get(Calendar.MONTH)+1;
        int año = cal1.get(Calendar.YEAR);
        int hora = cal1.get(Calendar.HOUR);
        int minuto = cal1.get(Calendar.MINUTE);


        if(dia < 10){
            diaN+="0"+dia;
        }
        else{
            diaN+=""+dia;
        }
        if(mes < 10){
            mesN+="0"+mes;
        }
        else{
            mesN+=""+mes;
        }

        //		fecha_Actual+= año+"-"+mesN+"-"+ diaN;
        //		fechaSistema = año+"-"+mesN+"-"+diaN+"-"+hora+"-"+minuto;
        fechaSistema = año+"-"+mesN+"-"+diaN;
        //		horaFechaSistema = hora+"-"+minuto;
    }
}
