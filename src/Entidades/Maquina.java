package Entidades;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Maquina {
    private static final int MAX_CRIATURAS = 10;
    private List<Criatura> criaturas;
    private int criaturaActualIndex;

    public Maquina() {
        this.criaturas = new ArrayList<>();
        this.criaturaActualIndex = 0;
        llenarEjercitoAleatorio();
    }

    private void llenarEjercitoAleatorio() {
        Random random = new Random();
        List<String> nombresHumanos = List.of("Antonio", "José", "Manuel", "Francisco", "David", "Juan", "José Antonio", "Javier", "José Luis", "Daniel");
        List<String> nombresElfos = List.of("Caranthir", "Celegorm", "Círdan", "Eldar", "Elrond", "Eru", "Fëanor", "Fingolfin", "Finrod", "Isildur");
        List<String> nombresOrcos = List.of("Chakub", "Duffthug", "Gollik", "Zogstuf", "Rok", "Grimfang", "Rokblorggor", "Wortsnaga", "Bagul", "Naznob");

        for (int i = 0; i < MAX_CRIATURAS; i++) {
            int seleccionMaquina = random.nextInt(3) + 1;
            Criatura criaturaMaquina;
            String nombreMaquina;

            switch (seleccionMaquina) {
                case 1:
                    criaturaMaquina = new Humano();
                    nombreMaquina = Criatura.obtenerNombreAleatorio(nombresHumanos.toArray(new String[0]));
                    break;
                case 2:
                    criaturaMaquina = new Elfo();
                    nombreMaquina = Criatura.obtenerNombreAleatorio(nombresElfos.toArray(new String[0]));
                    break;
                case 3:
                    criaturaMaquina = new Orco();
                    nombreMaquina = Criatura.obtenerNombreAleatorio(nombresOrcos.toArray(new String[0]));
                    break;
                default:
                    criaturaMaquina = new Humano();
                    nombreMaquina = Criatura.obtenerNombreAleatorio(nombresHumanos.toArray(new String[0]));
            }

            criaturaMaquina.setNombre(nombreMaquina);
            criaturas.add(criaturaMaquina);
        }
    }

    public boolean tieneCriaturas() {
        return !criaturas.isEmpty();
    }

    public List<Criatura> getCriaturas() {
        return criaturas;
    }

    public Criatura getCriaturaActual() {
        if (criaturaActualIndex >= 0 && criaturaActualIndex < criaturas.size()) {
            return criaturas.get(criaturaActualIndex);
        } else {
            return null;
        }
    }

    public void siguienteCriatura() {
        criaturaActualIndex = (criaturaActualIndex + 1) % criaturas.size();
    }

    public void eliminarCriatura() {
        if (!criaturas.isEmpty()) {
            criaturas.remove(0);
        }
    }

    public void atacar(Jugador jugador) {
        Criatura maquina = getCriaturaActual();
        if (maquina != null) {
            maquina.atacar(jugador.getCriaturaActual());
        }
    }

    public boolean agregarCriatura(Criatura criatura) {
        if (criaturas.size() < MAX_CRIATURAS) {
            criaturas.add(criatura);
            return true;
        } else {
            return false;
        }
    }
}