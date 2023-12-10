package Entidades;
public class Criatura {
    private String nombre;
    private int salud;
    private int velocidad;
    private int ataque;
    private int defensa;

    // Constructor
    public Criatura(String nombre, int salud, int velocidad, int ataque, int defensa) {
        this.nombre = nombre;
        this.salud = salud;
        this.velocidad = velocidad;
        this.ataque = ataque;
        this.defensa = defensa;
    }

    // Método de ataque
    public void atacar(Criatura otraCriatura) {
        double ataqueCalculado = (this.ataque * 0.5) + (this.velocidad * 0.3);
        ataqueCalculado = Math.max(0, ataqueCalculado); // No permitir daño negativo

        if (this instanceof Elfo && otraCriatura instanceof Orco) {
            // Elfo ataca a un Orco (doble de daño)
            ataqueCalculado *= 2;
        } else if (this instanceof Orco && otraCriatura instanceof Humano) {
            // Orco ataca a un Humano (doble de daño)
            ataqueCalculado *= 2;
        } else if (this instanceof Humano && otraCriatura instanceof Elfo) {
            // Humano ataca a un Elfo (doble de daño)
            ataqueCalculado *= 2;
        } else if (this instanceof Orco && otraCriatura instanceof Elfo) {
            // Orco ataca a un Elfo (mitad de daño)
            ataqueCalculado /= 2;
        } else if (this instanceof Humano && otraCriatura instanceof Orco) {
            // Humano ataca a un Orco (mitad de daño)
            ataqueCalculado /= 2;
        } else if (this instanceof Elfo && otraCriatura instanceof Humano) {
            // Elfo ataca a un Humano (mitad de daño)
            ataqueCalculado /= 2;
        }

        otraCriatura.salud = Math.max(0, otraCriatura.salud - (int) ataqueCalculado);
    }

    // Métodos getter y setter
    public String getNombre() {
        return nombre;
    }

    public int getSalud() {
        return salud;
    }

    public void setSalud(int salud) {
        this.salud = salud;
    }

    public int getVelocidad() {
        return velocidad;
    }

    public void setVelocidad(int velocidad) {
        this.velocidad = velocidad;
    }

    public int getAtaque() {
        return ataque;
    }

    public void setAtaque(int ataque) {
        this.ataque = ataque;
    }

    public int getDefensa() {
        return defensa;
    }

    public void setDefensa(int defensa) {
        this.defensa = defensa;
    }
}