package Entidades;

import java.util.ArrayList;
import java.util.List;

public class Jugador {
    protected static final int MAX_CRIATURAS = 10;
    private List<Criatura> criaturas;
    private int criaturaActualIndex;

    public Jugador() {
        this.criaturas = new ArrayList<>();
        this.criaturaActualIndex = 0;
    }

    public boolean agregarCriatura(Criatura criatura) {
        if (criaturas.size() < MAX_CRIATURAS) {
            criaturas.add(criatura);
            return true; // La criatura se agregó con éxito
        } else {
            return false; // No se puede agregar más criaturas
        }
    }

    public Criatura getCriaturaActual() {
        if (criaturaActualIndex >= 0 && criaturaActualIndex < criaturas.size()) {
            return criaturas.get(criaturaActualIndex);
        } else {
            return null; // Devolver null si no hay criatura actual disponible
        }
    }

    public void siguienteCriatura() {
        criaturaActualIndex = (criaturaActualIndex + 1) % criaturas.size();
    }

    public boolean tieneCriaturas() {
        return !criaturas.isEmpty();
    }

    public List<Criatura> getCriaturas() {
        return criaturas;
    }

    public void atacar(Criatura objetivo) {
        Criatura jugador = getCriaturaActual();
        if (jugador != null) {
            jugador.atacar(objetivo);
        }
    }
    public void eliminarCriatura() {
        if (!criaturas.isEmpty()) {
            criaturas.remove(0);
        }
    }

    public void eliminarCriaturaActual() {
        if (!criaturas.isEmpty()) {
            criaturas.remove(criaturaActualIndex);
        }
    }
}