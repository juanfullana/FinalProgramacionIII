package utnydragones.criaturas;

public class Barbaro extends Guerrero {
    //private static final int ENERGIA_COSTO_MACHACAR = 30;
    private static final int ENERGIA_GENERADA_ATACAR = 20;

    public Barbaro() {
        super("Bárbaro", 100, 20, 8, 10, 10, 20, 70);
    }

    // Método para realizar la habilidad especial "Machacar"
   /*public void machacar(Criatura objetivo) {
        if (getEnergia() >= ENERGIA_COSTO_MACHACAR) {
            int dano = (int) (calcularDano(objetivo) * 1.5);
            objetivo.reducirSalud(dano);
            System.out.println("¡Has usado Machacar!");
            System.out.println(getNombre() + " ejecuta un poderoso ataque de machacar contra " + objetivo.getNombre() +
                    " y le inflige " + dano + " de daño.");
            System.out.println(objetivo.getNombre() + " tiene ahora " + objetivo.getSalud() + " de salud.");
            reducirEnergia(ENERGIA_COSTO_MACHACAR);
        } else {
            System.out.println(getNombre() + " no tiene suficiente energía para usar la habilidad especial 'Machacar'.");
        }
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