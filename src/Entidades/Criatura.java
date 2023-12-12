package Entidades;

import java.util.Random;

public class Criatura {
    private String nombre;
    private int salud;
    private int ataque;
    private int velocidad;
    private int defensa;

    public Criatura(String nombre, int salud, int ataque, int velocidad, int defensa) {
        this.nombre = nombre;
        this.salud = salud;
        this.ataque = ataque;
        this.velocidad = velocidad;
        this.defensa = defensa;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nuevoNombre) {
        this.nombre = nuevoNombre;
    }

    public int getSalud() {
        return salud;
    }

    public int getAtaque() {
        return ataque;
    }

    public int getVelocidad() {
        return velocidad;
    }

    public int getDefensa() {
        return defensa;
    }

    public void atacar(Criatura objetivo) {
        double multiplicador = 1.0;

        if (this instanceof Elfo) {
            if (objetivo instanceof Orco) {
                multiplicador = 2.0;  // Daño doble del Elfo al Orco
            } else if (objetivo instanceof Humano) {
                multiplicador = 0.75;  // Mitad de daño del Elfo al Humano
            }
        } else if (this instanceof Orco) {
            if (objetivo instanceof Elfo) {
                multiplicador = 0.75;  // Mitad de daño del Orco al Elfo
            } else if (objetivo instanceof Humano) {
                multiplicador = 2.0;  // Daño doble del Orco al Humano
            }
        } else if (this instanceof Humano) {
            if (objetivo instanceof Elfo) {
                multiplicador = 2.0;  // Daño doble del Humano al Elfo
            } else if (objetivo instanceof Orco) {
                multiplicador = 0.75;  // Mitad de daño del Humano al Orco
            }
        }

        double danio = (this.ataque * 0.7 + this.velocidad * 0.5) * multiplicador;
        danio -= objetivo.getDefensa();

        if (danio > 0) {
            int nuevaSalud = Math.max(objetivo.getSalud() - (int) danio, 0);
            objetivo.actualizarSalud(nuevaSalud);
        }
    }

    // Método para actualizar la salud desde fuera de la clase
    public void actualizarSalud(int nuevaSalud) {
        this.salud = nuevaSalud;
    }

    public static String obtenerNombreAleatorio(String[] nombres) {
        Random random = new Random();
        int indice = random.nextInt(nombres.length);
        return nombres[indice];
    }
    @Override
    public String toString() {
        return this.getNombre() + " - Tipo: " + this.getClass().getSimpleName() + " - Salud: " + this.getSalud();
    }
}