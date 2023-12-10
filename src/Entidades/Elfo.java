package Entidades;

public class Elfo extends Criatura {
    private static String[] nombres = {"Caranthir", "Celegorm", "Círdan", "Eldar", "Elrond", "Eru", "Fëanor", "Fingolfin", "Finrod", "Isildur"};
    private static int idCounter = 1; // Contador para asignar identificadores únicos

    private int id;

    // Constructor específico para Elfo
    public Elfo() {
        super(generarNombre(), 18, 10, 7, 7);
        this.id = idCounter++;
    }

    // Método para generar nombres en orden
    private static String generarNombre() {
        int index = idCounter - 1;
        if (index >= 0 && index < nombres.length) {
            return nombres[index];
        } else {
            return "ElfoDesconocido";
        }
    }

    // Método getter para el ID
    public int getId() {
        return id;
    }
}