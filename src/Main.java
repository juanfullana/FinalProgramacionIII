import Logica.Juego;
import Logica.RegistroPartida;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Main {
    public static void main(String[] args) {
        // Obtener la fecha y hora actual
        LocalDateTime fechaHoraInicio = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String fechaHoraInicioStr = fechaHoraInicio.format(formatter);

        // Registrar la fecha y hora de inicio en el archivo de log
        RegistroPartida.registrarEvento("Inicio de ejecución: " + fechaHoraInicioStr);

        // Iniciar el juego
        Juego juego = new Juego();
        juego.comenzarJuego();
    }
}