import Entidades.Criatura;
import Entidades.Elfo;
import Entidades.Humano;
import Entidades.Orco;
import Entidades.Jugador;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        // Crear listas de nombres para cada tipo de criatura
        List<String> nombresHumanos = new ArrayList<>(Arrays.asList("Antonio", "José", "Manuel", "Francisco", "David", "Juan", "José Antonio", "Javier", "José Luis", "Daniel"));
        List<String> nombresElfos = new ArrayList<>(Arrays.asList("Caranthir", "Celegorm", "Círdan", "Eldar", "Elrond", "Eru", "Fëanor", "Fingolfin", "Finrod", "Isildur"));
        List<String> nombresOrcos = new ArrayList<>(Arrays.asList("Chakub", "Duffthug", "Gollik", "Zogstuf", "Rok", "Grimfang", "Rokblorggor", "Wortsnaga", "Bagul", "Naznob"));

        // Variables para el seguimiento del combate
        int rondas = 0;
        int danioUsuario = 0;
        int danioMaquina = 0;

        // Interacción con el usuario
        Scanner scanner = new Scanner(System.in);
        System.out.println("¡Bienvenido al juego de cartas de criaturas!");

        // Crear jugador y su ejército
        Jugador jugador = new Jugador();
        for (int i = 0; i < 10; i++) {
            System.out.println("Selecciona tu criatura (1: Humano, 2: Elfo, 3: Orco): ");
            int seleccionUsuario = scanner.nextInt();

            // Crear criatura seleccionada por el usuario con nombre aleatorio
            Criatura criaturaUsuario;
            String nombreUsuario;
            switch (seleccionUsuario) {
                case 1:
                    criaturaUsuario = new Humano();
                    nombreUsuario = Criatura.obtenerNombreAleatorio(nombresHumanos.toArray(new String[0]));
                    break;
                case 2:
                    criaturaUsuario = new Elfo();
                    nombreUsuario = Criatura.obtenerNombreAleatorio(nombresElfos.toArray(new String[0]));
                    break;
                case 3:
                    criaturaUsuario = new Orco();
                    nombreUsuario = Criatura.obtenerNombreAleatorio(nombresOrcos.toArray(new String[0]));
                    break;
                default:
                    System.out.println("Selección no válida. Seleccionando Humano por defecto.");
                    criaturaUsuario = new Humano();
                    nombreUsuario = Criatura.obtenerNombreAleatorio(nombresHumanos.toArray(new String[0]));
            }
            criaturaUsuario.setNombre(nombreUsuario);

            // Agregar criatura al ejército del jugador
            if (jugador.agregarCriatura(criaturaUsuario)) {
                System.out.println("Criatura agregada: " + criaturaUsuario.getNombre());
            } else {
                System.out.println("No se puede agregar más criaturas. Ejército completo.");
                break;
            }
        }

        // Crear criatura aleatoria para el jugador 2 con nombre aleatorio
        Criatura criaturaMaquina;
        String nombreMaquina;
        Random random = new Random();
        int seleccionMaquina = random.nextInt(3) + 1;
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
                // Esto no debería suceder, pero por si acaso
                criaturaMaquina = new Humano();
                nombreMaquina = Criatura.obtenerNombreAleatorio(nombresHumanos.toArray(new String[0]));
        }
        criaturaMaquina.setNombre(nombreMaquina);

        // Simular combate
        while (jugador.tieneCriaturas() && criaturaMaquina.getSalud() > 0) {
            rondas++;

            // Almacena la salud antes del ataque
            int saludUsuarioAntes = jugador.getCriaturaActual().getSalud();
            int saludMaquinaAntes = criaturaMaquina.getSalud();

            // Ataques
            jugador.atacar(criaturaMaquina);
            criaturaMaquina.atacar(jugador.getCriaturaActual());

            // Calcula el daño en esta ronda
            int danioEnEstaRondaUsuario = saludMaquinaAntes - criaturaMaquina.getSalud();
            int danioEnEstaRondaMaquina = saludUsuarioAntes - jugador.getCriaturaActual().getSalud();

            // Acumula el daño total
            danioUsuario += danioEnEstaRondaUsuario;
            danioMaquina += danioEnEstaRondaMaquina;

            // Muestra resultados de la ronda
            System.out.println("\nRonda " + rondas);
            System.out.println("Daño causado por " + jugador.getCriaturaActual().getNombre() + ": " + danioEnEstaRondaUsuario);
            System.out.println("Salud restante de " + criaturaMaquina.getNombre() + ": " + criaturaMaquina.getSalud());
            System.out.println("Daño causado por " + criaturaMaquina.getNombre() + ": " + danioEnEstaRondaMaquina);
            System.out.println("Salud restante de " + jugador.getCriaturaActual().getNombre() + ": " + jugador.getCriaturaActual().getSalud());

            // Cambiar criatura actual del jugador
            jugador.siguienteCriatura();
        }

        // Mostrar resultados finales
        if (jugador.tieneCriaturas()) {
            System.out.println("\n¡Felicidades! Has ganado el juego. Tu ejército ha derrotado al enemigo.");
        } else {
            System.out.println("\n¡Derrota! Tu ejército ha sido vencido por el enemigo.");
        }

        System.out.println("\nResumen del juego:");
        System.out.println("Rondas: " + rondas);
        System.out.println("Daño total causado por el jugador: " + danioUsuario);
        System.out.println("Daño total causado por la máquina: " + danioMaquina);
    }
}