package Entidades;

public class Orco extends Criatura {
    private static String[] nombres = {"Chakub", "Duffthug", "Gollik", "Zogstuf", "Rok", "Grimfang", "Rokblorggor", "Wortsnaga", "Bagul", "Naznob"};
    private static int idCounter = 1; // Contador para asignar identificadores únicos

    private int id;

    // Constructor específico para Orco
    public Orco() {
        super(generarNombre(), 30, 3, 13, 4);
        this.id = idCounter++;
    }

    // Métodos getter
    public int getId() {
        return id;
    }

    // Método para generar nombres en orden
    private static String generarNombre() {
        int index = idCounter - 1;
        return nombres[index];
    }
}