package Entidades;

public class Humano extends Criatura {
    private static String[] nombres = {"Antonio", "José", "Manuel", "Francisco", "David", "Juan", "José Antonio", "Javier", "José Luis", "Daniel"};
    private static int idCounter = 1; // Contador para asignar identificadores únicos

    private int id;

    // Constructor específico para Humano
    public Humano() {
        super(generarNombre(), 20, 5, 10, 10);
        this.id = idCounter++;
    }

    // Método para generar nombres en orden
    private static String generarNombre() {
        int index = idCounter - 1;
        if (index >= 0 && index < nombres.length) {
            return nombres[index];
        } else {
            return "HumanoDesconocido";
        }
    }

    // Método getter para el ID
    public int getId() {
        return id;
    }
}