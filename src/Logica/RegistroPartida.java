package Logica;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class RegistroPartida {
    public static final String NOMBRE_ARCHIVO_LOG = "log_combate.txt";
    private static final String nombreArchivo = NOMBRE_ARCHIVO_LOG;

    // Método para registrar un evento en el archivo de log
    public static void registrarEvento(String evento) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(nombreArchivo, true))) {
            writer.println(evento);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void limpiarArchivoLog() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(nombreArchivo, false))) {
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}