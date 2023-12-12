package Entidades;
public class Elfo extends Criatura {
    private static final String[] nombresElfos = {"Caranthir", "Celegorm", "Círdan", "Eldar", "Elrond", "Eru", "Fëanor", "Fingolfin", "Finrod", "Isildur"};

    public Elfo() {
        super(obtenerNombreAleatorio(nombresElfos), 15, 8, 7, 3);
    }
}