package utnydragones.criaturas;


public class Personalizado extends Guerrero {
    private int puntosDisponibles;

    public Personalizado(String nombre, int salud, int ataque, int defensa, int velocidad, int energia, int evasion) {
        super(nombre, salud, ataque, defensa, velocidad, energia, evasion);
        this.puntosDisponibles = 100;
    }

    public int getPuntosDisponibles() {
        return puntosDisponibles;
    }

    public void incrementarSalud(int puntos) {
        if (puntosDisponibles >= puntos) {
            super.salud += puntos;
            puntosDisponibles -= puntos;
        }
    }

    public void incrementarAtaque(int puntos) {
        if (puntosDisponibles >= puntos) {
            super.ataque += puntos;
            puntosDisponibles -= puntos;
        }
    }

    public void incrementarDefensa(int puntos) {
        if (puntosDisponibles >= puntos) {
            super.defensa += puntos;
            puntosDisponibles -= puntos;
        }
    }

    public void incrementarVelocidad(int puntos) {
        if (puntosDisponibles >= puntos) {
            super.velocidad += puntos;
            puntosDisponibles -= puntos;
        }
    }

    public void incrementarEnergia(int puntos) {
        if (puntosDisponibles >= puntos) {
            super.energia += puntos;
            puntosDisponibles -= puntos;
        }
    }

    public void incrementarEvasion(int puntos) {
        if (puntosDisponibles >= puntos) {
            super.evasion += puntos;
            puntosDisponibles -= puntos;
        }
    }
}