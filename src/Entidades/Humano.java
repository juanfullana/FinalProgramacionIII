package Entidades;
import java.util.Random;

public class Humano extends Criatura {
    private static final String[] nombresHumanos = {"Antonio", "José", "Manuel", "Francisco", "David", "Juan", "José Antonio", "Javier", "José Luis", "Daniel"};

    public Humano() {
        super(obtenerNombreAleatorio(nombresHumanos), 20, 11, 4, 4);
    }
}