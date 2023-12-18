package utnydragones.criaturas;

public class Paladin extends Guerrero {
    private static final int ENERGIA_COSTO_LUZ_DIVINA = 30;
    private static final int ENERGIA_GENERADA_ATACAR = 20;

    public Paladin() {
        super("Paladín", 80, 20, 25, 15, 10, 20, 80);
    }

    // Método para realizar la habilidad especial "Machacar"
    public void Luz_Divina(Criatura objetivo) {
        if (getEnergia() >= ENERGIA_COSTO_LUZ_DIVINA) {
            int dano = (int) (calcularDano(objetivo) * 1.5);
            objetivo.reducirSalud(dano);
            System.out.println("¡Has usado Luz Divina!");
            System.out.println(getNombre() +
                    " inflige " + dano + " de daño.");
            System.out.println(objetivo.getNombre() + " tiene ahora " + objetivo.getSalud() + " de salud.");
            reducirEnergia(ENERGIA_COSTO_LUZ_DIVINA);
        } else {
            System.out.println(getNombre() + " no tiene suficiente energía para usar la habilidad especial 'Luz Divina'.");
        }
    }

    // Método para usar la habilidad especial
    /*@Override
    public int usarHabilidadEspecial(Criatura objetivo) {
        Luz_Divina(objetivo);
        return 0;
    }*/

    @Override
    public int calcularDano(Criatura objetivo) {
        // Calcula el daño con el ataque normal
        double danoNormal = (getAtaque() + 0.5 * getVelocidad()) - objetivo.getDefensa();

        // Incrementa la energía después de un ataque normal
        incrementarEnergia(ENERGIA_GENERADA_ATACAR);

        return Math.max(0, (int) danoNormal);
    }
}