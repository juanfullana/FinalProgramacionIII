package Entidades;
public class Orco extends Criatura {
    private static final String[] nombresOrcos = {"Chakub", "Duffthug", "Gollik", "Zogstuf", "Rok", "Grimfang", "Rokblorggor", "Wortsnaga", "Bagul", "Naznob"};

    public Orco() {
        super(obtenerNombreAleatorio(nombresOrcos), 30, 15, 2, 2);
    }
}