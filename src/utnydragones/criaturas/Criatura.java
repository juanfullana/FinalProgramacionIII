package utnydragones.criaturas;

public class Criatura {
    public static final int ENERGIA_GENERADA_ATACAR = 20;
    private String nombre;
    private int salud;
    private int ataque;
    private int defensa;
    private int velocidad;
    protected Integer energia;
    private int evasion;
    private int saludMaxima;



    // Constructor
    public Criatura(String nombre, int salud, int ataque, int defensa, int velocidad, Integer energia, int evasion, int saludMaxima) {
        this.nombre = nombre;
        this.salud = salud;
        this.ataque = ataque;
        this.defensa = defensa;
        this.velocidad = velocidad;
        this.energia = energia;
        this.evasion = evasion;
        this.saludMaxima = saludMaxima;
    }

    public String getNombre() {
        return nombre;
    }

    public int getSalud() {
        return salud;
    }

    public void setSalud(int salud) {
        this.salud = salud;
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

    public int getVelocidad() {
        return velocidad;
    }

    public Integer getEnergia() {
        return energia;
    }

    public void setEnergia(Integer energia) {
        this.energia = energia;
    }

    public int getEvasion() {
        return evasion;
    }

    public void setEvasion(int evasion) {
        this.evasion = evasion;
    }

    // Reducir la salud asegurándose de que no sea negativa
    public void reducirSalud(int cantidad) {
        salud = Math.max(0, salud - cantidad);
    }

    // Método para calcular el daño
    protected int calcularDano(Criatura objetivo) {
        // Calcular el daño según la fórmula dada
        double factorVelocidad = 0.5;
        double danoCalculado = (ataque + velocidad * factorVelocidad) - objetivo.getDefensa();

        // Asegurarse de que el daño no sea negativo
        return Math.max(0, (int) danoCalculado);
    }

    // Método para incrementar la energía
    public void incrementarEnergia(int cantidad) {
        setEnergia(getEnergia() + cantidad);
    }

}
