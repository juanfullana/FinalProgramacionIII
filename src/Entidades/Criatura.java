package Entidades;

public class Criatura {
    protected String nombre;
    protected int vida;
    protected int velocidad;
    protected int ataque;
    protected int defensa;

    // Constructor
    public Criatura(String nombre, int vida, int velocidad, int ataque, int defensa) {
        this.nombre = nombre;
        this.vida = vida;
        this.velocidad = velocidad;
        this.ataque = ataque;
        this.defensa = defensa;
    }

    public String getNombre() {
        return nombre;
    }

    public int getVida() {
        return vida;
    }

    public int getVelocidad() {
        return velocidad;
    }

    public int getAtaque() {
        return ataque;
    }

    public int getDefensa() {
        return defensa;
    }
}