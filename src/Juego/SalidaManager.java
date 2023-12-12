package Juego;
import Entidades.Criatura;
import Entidades.Elfo;
import Entidades.Orco;
import Entidades.Humano;
import Entidades.Jugador;
import Entidades.Maquina;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class SalidaManager {
    private FileWriter archivoSalida;

    public SalidaManager() {
        try {
            archivoSalida = new FileWriter("salida.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void mostrarEnPantalla(String mensaje) {
        System.out.println(mensaje);
        escribirEnArchivo(mensaje);
    }

    public void mostrarEjercitoEnPantalla(String mensaje, List<Criatura> criaturas) {
        mostrarEnPantalla(mensaje);
        criaturas.forEach(criatura -> mostrarEnPantalla(criatura.getNombre() + " (" + criatura.getClass().getSimpleName() + ")"));
    }

    public void mostrarResultadosFinales(int rondas, int victoriasJugador, int victoriasMaquina, int empates) {
        mostrarEnPantalla("\nResumen del juego:");
        mostrarEnPantalla("Rondas: " + rondas);
        mostrarEnPantalla("Victorias del jugador: " + victoriasJugador);
        mostrarEnPantalla("Victorias de la máquina: " + victoriasMaquina);
        mostrarEnPantalla("Empates: " + empates);

        cerrarArchivo();
    }

    private void escribirEnArchivo(String mensaje) {
        try {
            archivoSalida.write(mensaje + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void cerrarArchivo() {
        try {
            archivoSalida.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}