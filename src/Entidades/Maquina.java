package Entidades;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Maquina {
    private static final int NUMERO_CRIATURAS_MAQUINA = 10;
    private List<Criatura> criaturas;

    public Maquina() {
        this.criaturas = new ArrayList<>();
        inicializarEjercito();
    }

    private void inicializarEjercito() {
        // Lógica para inicializar el ejército de la máquina
        Random random = new Random();
        for (int i = 0; i < NUMERO_CRIATURAS_MAQUINA; i++) {
            int tipoCriatura = random.nextInt(3) + 1; // 1: Humano, 2: Elfo, 3: Orco

            Criatura criatura;
            switch (tipoCriatura) {
                case 1:
                    criatura = new Humano();
                    break;
                case 2:
                    criatura = new Elfo();
                    break;
                case 3:
                    criatura = new Orco();
                    break;
                default:
                    criatura = new Humano();
            }
            criaturas.add(criatura);
        }
    }

    public Criatura obtenerCriaturaAleatoria() {
        // Obtener una criatura aleatoria del ejército de la máquina
        Random random = new Random();
        int indice = random.nextInt(criaturas.size());
        return criaturas.get(indice);
    }

    public boolean tieneCriaturas() {
        return !criaturas.isEmpty();
    }

    public void eliminarCriatura(Criatura criatura) {
        criaturas.remove(criatura);
    }
}