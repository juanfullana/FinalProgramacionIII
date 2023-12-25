package Logica;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

public class RegistroPartida {
    public static final String NOMBRE_ARCHIVO_LOG = "log_combate.txt";
    private static final String nombreArchivo = NOMBRE_ARCHIVO_LOG;

    // Método para registrar un evento en el archivo de log
    public static void registrarEvento(String evento) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(nombreArchivo, true))) {
            String fechaHora = obtenerFechaHoraActual();
            writer.println(fechaHora + " - " + evento);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Método para obtener la fecha y hora actual
    private static String obtenerFechaHoraActual() {
        SimpleDateFormat formatoFechaHora = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date fecha = new Date();
        return formatoFechaHora.format(fecha);
    }
}