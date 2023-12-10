package Entidades;

import java.util.ArrayList;
import java.util.List;

public class Jugador {
    private static final int MAX_CRIATURAS = 10;

    private List<Criatura> criaturas;

    // Constructor
    public Jugador() {
        this.criaturas = new ArrayList<>();
    }

    // Método para agregar criatura si no se ha alcanzado el límite
    public boolean agregarCriatura(Criatura criatura) {
        if (criaturas.size() < MAX_CRIATURAS) {
            criaturas.add(criatura);
            return true; // La criatura se agregó con éxito
        } else {
            return false; // No se puede agregar más criaturas
        }
    }

}