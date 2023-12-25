package utnydragones.criaturas;

public class Guerrero extends Criatura {
    private static final int ENERGIA_ATACAR = 20;
    protected static final int ENERGIA_COSTO_HABILIDAD_ESPECIAL = 30;
    private boolean usandoHabilidadEspecial;
    private static boolean habilidadEspecialUtilizada;

    public Guerrero(String nombre, int salud, int ataque, int defensa, int velocidad, int evasion, Integer energia) {
        super(nombre, salud, ataque, defensa, velocidad, evasion, energia);
        this.usandoHabilidadEspecial = false;
        this.habilidadEspecialUtilizada = false;
    }

    public boolean isUsandoHabilidadEspecial() {
        return usandoHabilidadEspecial;
    }

    public void setUsandoHabilidadEspecial(boolean usandoHabilidadEspecial) {
        this.usandoHabilidadEspecial = usandoHabilidadEspecial;
    }

    public  boolean isHabilidadEspecialUtilizada() {
        return habilidadEspecialUtilizada;
    }

    public  void setHabilidadEspecialUtilizada(boolean habilidadEspecialUtilizada) {
        Guerrero.habilidadEspecialUtilizada = habilidadEspecialUtilizada;
    }

    public static void reiniciarContadorHabilidadEspecial() {
        habilidadEspecialUtilizada = false;
    }

    public void reducirEnergia(int cantidad) {
        setEnergia(Math.max(0, getEnergia() - cantidad));
    }

    /*public int usarHabilidadEspecial(Criatura objetivo) {
        if (!habilidadEspecialUtilizada && getEnergia() >= ENERGIA_COSTO_HABILIDAD_ESPECIAL) {
            int dano = (int) (calcularDano(objetivo) * 1.5);
            objetivo.reducirSalud(dano);
            System.out.println(getNombre() + " usa la habilidad especial contra " + objetivo.getNombre() +
                    " y le inflige " + dano + " de daño.");
            System.out.println(objetivo.getNombre() + " tiene ahora " + objetivo.getSalud() + " de salud.");
            reducirEnergia(ENERGIA_COSTO_HABILIDAD_ESPECIAL);
            System.out.println("¡Has usado la habilidad especial!");
            habilidadEspecialUtilizada = true; // Marcar la habilidad como utilizada
            return dano; // Devuelve el daño infligido
        } else {
            System.out.println(getNombre() + " no puede usar la habilidad especial en este momento.");
            return 0; // Si no se puede usar la habilidad, devuelve 0 de daño
        }
    }*/ //Se usará a futuro

    @Override
    public int calcularDano(Criatura objetivo) {
        if (isUsandoHabilidadEspecial() && getEnergia() >= ENERGIA_COSTO_HABILIDAD_ESPECIAL) {
            double danoHabilidadEspecial = (1.5 * (getAtaque() + 0.5 * getVelocidad())) - objetivo.getDefensa();
            setUsandoHabilidadEspecial(false);
            reducirEnergia(ENERGIA_COSTO_HABILIDAD_ESPECIAL);
            return Math.max(0, (int) danoHabilidadEspecial);
        } else {
            double danoNormal = (getAtaque() + 0.5 * getVelocidad()) - objetivo.getDefensa();
            incrementarEnergia(ENERGIA_ATACAR);
            return Math.max(0, (int) danoNormal);
        }
    }
}
