package utnydragones.criaturas;

public class Mago extends Guerrero {
    //private static final int ENERGIA_COSTO_RAYO_HIELO = 30;
    private static final int ENERGIA_GENERADA_ATACAR = 20;

    public Mago() {
        super("Mago", 10, 30, 10, 15, 10, 20, 60);
    }

    // Método para realizar la habilidad especial "Machacar"
    /*public void Rayo_Hielo(Criatura objetivo) {
        if (getEnergia() >= ENERGIA_COSTO_RAYO_HIELO) {
            int dano = (int) (calcularDano(objetivo) * 1.5);
            objetivo.reducirSalud(dano);
            System.out.println("¡Has usado Rayo de Hielo!");
            System.out.println(getNombre() +
                    " inflige " + dano + " de daño.");
            System.out.println(objetivo.getNombre() + " tiene ahora " + objetivo.getSalud() + " de salud.");
            reducirEnergia(ENERGIA_COSTO_RAYO_HIELO);
        } else {
            System.out.println(getNombre() + " no tiene suficiente energía para usar la habilidad especial 'Rayo de Hielo'.");
        }
    }*/

    // Método para usar la habilidad especial
    /*@Override
    public int usarHabilidadEspecial(Criatura objetivo) {
        Rayo_Hielo(objetivo);
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