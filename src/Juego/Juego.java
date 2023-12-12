package Juego;
import Entidades.Criatura;
import Entidades.Elfo;
import Entidades.Orco;
import Entidades.Humano;
import Entidades.Jugador;
import Entidades.Maquina;

import java.util.*;

public class Juego {
    private final Jugador jugador;
    private final Maquina maquina;
    int rondas = 0;
    int victoriasJugador = 0;
    int victoriasMaquina = 0;
    int empates = 0;
    int maxRondas = 10;

    public Juego() {
        jugador = new Jugador();
        maquina = new Maquina();
    }

    public void ejecutarJuego() {
        // Crear listas de nombres para cada tipo de criatura
        List<String> nombresHumanos = new ArrayList<>(List.of("Antonio", "José", "Manuel", "Francisco", "David", "Juan", "José Antonio", "Javier", "José Luis", "Daniel"));
        List<String> nombresElfos = new ArrayList<>(List.of("Caranthir", "Celegorm", "Círdan", "Eldar", "Elrond", "Eru", "Fëanor", "Fingolfin", "Finrod", "Isildur"));
        List<String> nombresOrcos = new ArrayList<>(List.of("Chakub", "Duffthug", "Gollik", "Zogstuf", "Rok", "Grimfang", "Rokblorggor", "Wortsnaga", "Bagul", "Naznob"));

        if (nombresHumanos.size() < 10 || nombresElfos.size() < 10 || nombresOrcos.size() < 10) {
            System.out.println("Error: Deben haber al menos 10 nombres disponibles para cada tipo de criatura.");
            return;
        }

        // Interacción con el usuario
        Scanner scanner = new Scanner(System.in);
        System.out.println("¡Bienvenido al juego de cartas de criaturas!");

        // Crear jugador y su ejército
        Set<String> nombresElegidosJugador = new HashSet<>();
        for (int i = 0; i < 10; i++) {
            System.out.println("Selecciona tu criatura (1: Humano, 2: Elfo, 3: Orco): ");
            int seleccionUsuario = scanner.nextInt();
            Criatura criaturaUsuario = obtenerCriatura(seleccionUsuario, nombresHumanos, nombresElfos, nombresOrcos, nombresElegidosJugador);
            jugador.agregarCriatura(criaturaUsuario);
            System.out.println("Criatura agregada: " + criaturaUsuario.getNombre());
        }

        // Crear máquina y su ejército
        Set<String> nombresElegidosMaquina = new HashSet<>();
        for (int i = 0; i < 10; i++) {
            Random random = new Random();
            int seleccionMaquina = random.nextInt(3) + 1;
            Criatura criaturaMaquina = obtenerCriatura(seleccionMaquina, nombresHumanos, nombresElfos, nombresOrcos, nombresElegidosMaquina);
            maquina.agregarCriatura(criaturaMaquina);
        }

        // Mostrar ejércitos
        System.out.println("\nEl ejército del jugador está conformado por:");
        jugador.getCriaturas().forEach(criatura -> System.out.println(criatura.getNombre() + " (" + obtenerTipoCriatura(criatura) + ")"));

        System.out.println("\nEl ejército de la máquina está conformado por:");
        maquina.getCriaturas().forEach(criatura -> System.out.println(criatura.getNombre() + " (" + obtenerTipoCriatura(criatura) + ")"));

        // Simular combate
        while (rondas < maxRondas && victoriasJugador < 6 && victoriasMaquina < 6) {
            rondas++;


            // Almacena la salud antes del ataque
            int saludJugadorAntes = jugador.getCriaturaActual().getSalud();
            int saludMaquinaAntes = maquina.getCriaturaActual().getSalud();

            // Muestra inicio del enfrentamiento
            System.out.println("\nRonda " + rondas + ".");
            System.out.println("Representando al Jugador se encuentra " + jugador.getCriaturaActual().getNombre());
            System.out.println("Representando a la Maquina se encuentra " + maquina.getCriaturaActual().getNombre());
            System.out.println("Que comience la batalla.");

            // Ataques y daños
            while (jugador.getCriaturaActual().getSalud() > 0 && maquina.getCriaturaActual().getSalud() > 0) {
                // Ataques
                jugador.atacar(maquina.getCriaturaActual());
                maquina.getCriaturaActual().atacar(jugador.getCriaturaActual());

                // Calcula el daño en esta ronda
                int danioEnEstaRondaJugador = saludMaquinaAntes - maquina.getCriaturaActual().getSalud();
                int danioEnEstaRondaMaquina = saludJugadorAntes - jugador.getCriaturaActual().getSalud();

                // Muestra resultados de la ronda
                System.out.println("\n" + jugador.getCriaturaActual().getNombre() + " le ocasiona " +
                        danioEnEstaRondaJugador + " de daño a " + maquina.getCriaturaActual().getNombre());
                System.out.println(maquina.getCriaturaActual().getNombre() + " le ocasiona " +
                        danioEnEstaRondaMaquina + " de daño a " + jugador.getCriaturaActual().getNombre());
                System.out.println("La salud restante de " + maquina.getCriaturaActual().getNombre() + " es " +
                        maquina.getCriaturaActual().getSalud());
                System.out.println("La salud restante de " + jugador.getCriaturaActual().getNombre() + " es " +
                        jugador.getCriaturaActual().getSalud());
            }

            // Verificar ganador de la ronda
            if (jugador.getCriaturaActual().getSalud() <= 0 && maquina.getCriaturaActual().getSalud() > 0) {
                System.out.println("El jugador ha perdido esta ronda.");
                victoriasMaquina++;
            } else if (maquina.getCriaturaActual().getSalud() <= 0 && jugador.getCriaturaActual().getSalud() > 0) {
                System.out.println("El jugador ha ganado esta ronda.");
                victoriasJugador++;
            } else {
                System.out.println("Esta ronda ha terminado en empate.");
                empates++;
            }

            // Cambiar criatura actual del jugador y la máquina
            jugador.siguienteCriatura();
            maquina.siguienteCriatura();
        }

        // Mostrar resultados finales
        if (victoriasJugador > victoriasMaquina) {
            System.out.println("\n¡Felicidades! El jugador ha ganado el juego.");
        } else if (victoriasMaquina > victoriasJugador) {
            System.out.println("\n¡Derrota! El jugador ha sido vencido por la máquina.");
        } else {
            System.out.println("\n¡Empate! Ambos jugadores han tenido un rendimiento igual.");
        }

        System.out.println("\nResumen del juego:");
        System.out.println("Rondas: " + rondas);
        System.out.println("Victorias del jugador: " + victoriasJugador);
        System.out.println("Victorias de la máquina: " + victoriasMaquina);
        System.out.println("Empates: " + empates);
    }
    private Criatura obtenerCriatura(int seleccion, List<String> nombresHumanos, List<String> nombresElfos, List<String> nombresOrcos, Set<String> nombresElegidos) {
        Criatura criatura;
        String nombre;
        switch (seleccion) {
            case 1:
                criatura = new Humano();
                nombre = obtenerNombreUnico(nombresHumanos, nombresElegidos);
                break;
            case 2:
                criatura = new Elfo();
                nombre = obtenerNombreUnico(nombresElfos, nombresElegidos);
                break;
            case 3:
                criatura = new Orco();
                nombre = obtenerNombreUnico(nombresOrcos, nombresElegidos);
                break;
            default:
                System.out.println("Selección no válida. Seleccionando Humano por defecto.");
                criatura = new Humano();
                nombre = obtenerNombreUnico(nombresHumanos, nombresElegidos);
        }
        criatura.setNombre(nombre);
        return criatura;
    }
    private static String obtenerNombreUnico(List<String> nombresDisponibles, Set<String> nombresElegidos) {
        for (String nombre : nombresDisponibles) {
            if (!nombresElegidos.contains(nombre)) {
                nombresElegidos.add(nombre);
                return nombre;
            }
        }
        // Si no hay nombres disponibles, regresar uno por defecto
        return "NombreDefault";
    }
    private String obtenerTipoCriatura(Criatura criatura) {
        if (criatura instanceof Humano) {
            return "Humano";
        } else if (criatura instanceof Elfo) {
            return "Elfo";
        } else if (criatura instanceof Orco) {
            return "Orco";
        } else {
            return "Desconocido";
        }
    }
}