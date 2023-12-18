import Logica.Juego;
import Logica.RegistroPartida;

public class Main {
    public static void main(String[] args) {
        RegistroPartida.limpiarArchivoLog();
        Juego juego = new Juego();
        juego.comenzarJuego();
    }
}