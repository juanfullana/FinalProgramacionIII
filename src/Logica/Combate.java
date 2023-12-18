package Logica;

import java.util.Scanner;
import java.util.Random;
import utnydragones.criaturas.*;

public class Combate {
    private final Guerrero jugador;
    private final Criatura enemigo;
    private final RegistroPartida registro;
    private final String nombreGuerreroElegido;
    private int rondas = 0;
    private int danioTotalJugador = 0;
    private int danioTotalEnemigo = 0;

    //private int danioEspecialJugador = 0;
    //private boolean habilidadEspecialUsada = false;

    public Combate(Guerrero jugador, Criatura enemigo) {
        this.jugador = jugador;
        this.enemigo = enemigo;
        this.nombreGuerreroElegido = jugador.getNombre();
        this.registro = new RegistroPartida();
    }

    public void iniciarCombate() {
        System.out.println("\n---- COMBATE ----");
        System.out.println("Te enfrentas a un " + enemigo.getNombre() + ".");
        chequeoPercepcion();

        while (jugador.getSalud() > 0 && enemigo.getSalud() > 0) {
            rondas++;

            mostrarOpcionesCombate();
            int opcion = obtenerOpcionUsuario();

            switch (opcion) {
                case 1:
                    realizarAtaque(jugador, enemigo);
                    break;
                case 2:
                    System.out.println("Habilidad Especial deshabilitada en este combate.");
                    break;
                default:
                    System.out.println("Opción inválida. Inténtalo de nuevo.");
                    break;
            }

            realizarTurnoJugador();

            if (enemigo.getSalud() <= 0) {
                ofrecerMejoraDespuesEnemigo();
                break;
            }

            if (jugador.getSalud() <= 0) {
                break;
            }
        }
    }

    private void mostrarOpcionesCombate() {
        System.out.println("Opciones de Logica.Combate:");
        System.out.println("1. Atacar");
        System.out.println("2. Habilidad Especial (Deshabilitada)");
    }

    private void realizarTurnoJugador() {
        if (enemigo.getSalud() > 0) {
            Scanner scanner = new Scanner(System.in);
            int opcion = 0;

            while (opcion < 1 || opcion > 2) {
                System.out.print("Elige una opción (1-2): ");
                try {
                    opcion = Integer.parseInt(scanner.nextLine());
                    if (opcion < 1 || opcion > 2) {
                        System.out.println("Por favor, ingresa una opción válida.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Por favor, ingrese un número válido.");
                }
            }

            switch (opcion) {
                case 1:
                    realizarAtaque(jugador, enemigo);
                    break;
                case 2:
                    System.out.println("Habilidad Especial deshabilitada en este combate.");
                    break;
                default:
                    System.out.println("Opción inválida. Se termina tu turno.");
                    break;
            }

            if (enemigo.getSalud() <= 0) {
                System.out.println("¡Has derrotado al " + enemigo.getNombre() + "!");
                registrarFinCombate();
                return;
            }

            realizarAtaque(enemigo, jugador);

            if (jugador.getSalud() <= 0) {
                System.out.println("¡Has sido derrotado por el " + enemigo.getNombre() + "!");
                registrarFinCombate();
                return;
            }
        } else {
            System.out.println("¡Has derrotado al " + enemigo.getNombre() + "!");
            registrarFinCombate();
        }
    }

    private void realizarAtaque(Criatura atacante, Criatura objetivo) {
        int dano = calcularDano(atacante, objetivo);
        objetivo.reducirSalud(dano);

        System.out.println(atacante.getNombre() + " ataca a " + objetivo.getNombre() + " y le inflige " + dano + " de daño.");
        System.out.println(objetivo.getNombre() + " tiene ahora " + objetivo.getSalud() + " de salud.");

        atacante.incrementarEnergia(Criatura.ENERGIA_GENERADA_ATACAR);

        if (atacante == jugador) {
            danioTotalJugador += dano;
        } else {
            danioTotalEnemigo += dano;
        }
    }

    private int calcularDano(Criatura atacante, Criatura objetivo) {
        if (atacante instanceof Barbaro) {
            Barbaro barbaro = (Barbaro) atacante;
            return barbaro.calcularDano(objetivo);
        } else {
            double danoCalculado = (atacante.getAtaque() + 0.5 * atacante.getVelocidad()) - objetivo.getDefensa();

            Random random = new Random();
            double probabilidadEvasion = objetivo.getEvasion() / 100.0;
            double resultadoEvasion = random.nextDouble();

            if (resultadoEvasion <= probabilidadEvasion) {
                System.out.println("¡" + objetivo.getNombre() + " ha evadido el ataque!");
                return 0;
            } else {
                return Math.max(0, (int) danoCalculado);
            }
        }
    }

    private void chequeoPercepcion() {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        System.out.println("Realizas un chequeo de percepción antes del combate.");

        int resultadoDado = random.nextInt(20) + 1;

        System.out.println("Resultado del chequeo de percepción: " + resultadoDado);

        if (resultadoDado == 1) {
            System.out.println("¡Has tropezado con una trampa! Recibes 15 de daño.");
            jugador.reducirSalud(15);
        } else if (resultadoDado <= 5) {
            System.out.println("Obtienes información sobre la salud del " + enemigo.getNombre() + ": " + enemigo.getSalud());
        } else if (resultadoDado <= 10) {
            System.out.println("Obtienes información sobre el " + enemigo.getNombre() +
                    ": Salud " + enemigo.getSalud() + ", Defensa " + enemigo.getDefensa());
        } else if (resultadoDado <= 15) {
            System.out.println("Obtienes información detallada sobre el " + enemigo.getNombre() +
                    ": Salud " + enemigo.getSalud() + ", Defensa " + enemigo.getDefensa() +
                    ", Ataque " + enemigo.getAtaque());
        } else {
            System.out.println("Obtienes información completa sobre el " + enemigo.getNombre() +
                    ": Salud " + enemigo.getSalud() + ", Defensa " + enemigo.getDefensa() +
                    ", Ataque " + enemigo.getAtaque() + ", Velocidad " + enemigo.getVelocidad() +
                    ", Energía " + enemigo.getEnergia() + ", Evasión " + enemigo.getEvasion());
        }
    }

    private int obtenerOpcionUsuario() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            try {
                System.out.print("Elige una opción (1-2): ");
                int opcion = Integer.parseInt(scanner.nextLine());

                if (opcion == 1 || opcion == 2) {
                    return opcion;
                } else {
                    System.out.println("Por favor, ingresa una opción válida (1 o 2).");
                }
            } catch (NumberFormatException e) {
                System.out.println("Por favor, ingrese un número válido.");
            }
        }
    }

    private void ofrecerMejoraGhoul() {
        System.out.println("Una presencia mágica se hace presente en la habitación y te ofrece una mejora.");

        System.out.println("Opciones de mejora:");
        System.out.println("1. Unos guantes que mejoran la defensa en 2 puntos.");
        System.out.println("2. Unas botas que mejoran la evasión en 4 puntos.");

        System.out.print("Elige una opción (1-2): ");

        int opcionMejora = obtenerOpcionUsuario();

        switch (opcionMejora) {
            case 1:
                mejorarDefensa();
                break;
            case 2:
                mejorarEvasion();
                break;
            default:
                System.out.println("Opción inválida. No se realiza ninguna mejora.");
                break;
        }
    }

    private void ofrecerMejoraEsqueleto() {
        System.out.println("Buscas entre los restos de tu compañero y encuentras un botín, solo puedes llevarte uno.");

        System.out.println("Opciones de mejora:");
        System.out.println("1. Un anillo que mejora la salud en 10 puntos.");
        System.out.println("2. Un collar que aumenta la energía en 40 puntos.");

        System.out.print("Elige una opción (1-2): ");

        int opcionMejora = obtenerOpcionUsuario();

        switch (opcionMejora) {
            case 1:
                mejorarSalud();
                break;
            case 2:
                mejorarEnergia();
                break;
            default:
                System.out.println("Opción inválida. No se realiza ninguna mejora.");
                break;
        }
    }

    private void ofrecerMejoraFantasma() {
        System.out.println("El espíritu comienza a rodearte");
        mejorarAtaque();
        System.out.println("El daño de tu arma se ha incrementado!");
    }

    private void mejorarDefensa() {
        System.out.println("Te has equipado con unos guantes que mejoran la defensa en 2 puntos. ¡Efecto permanente!");
        jugador.setDefensa(jugador.getDefensa() + 2);
    }

    private void mejorarEvasion() {
        System.out.println("Te has equipado con unas botas que mejoran la evasión en 4 puntos. ¡Efecto permanente!");
        jugador.setEvasion(jugador.getEvasion() + 4);
    }

    private void mejorarSalud() {
        System.out.println("Te has equipado un anillo que mejora la salud en 10 puntos.");
        jugador.setSalud(jugador.getSalud() + 10);
    }

    private void mejorarEnergia() {
        System.out.println("Te has equipado un collar que mejora la energía en 40 puntos");
        jugador.setEnergia(jugador.getEnergia() + 40);
    }

    private void mejorarAtaque() {
        System.out.println("Tu arma ha sido imbuida por el espíritu de tu compañero, quien te acompañará en tu aventura");
        jugador.setAtaque(jugador.getAtaque() + 10);
    }

    private void ofrecerMejoraDespuesEnemigo() {
        if (enemigo instanceof Ghoul) {
            ofrecerMejoraGhoul();
        } else if (enemigo instanceof Esqueleto) {
            ofrecerMejoraEsqueleto();
        } else if (enemigo instanceof Fantasma) {
            ofrecerMejoraFantasma();
        }
    }

    public void registrarFinCombate() {
        RegistroPartida.registrarEvento("Combate vs " + enemigo.getNombre());
        RegistroPartida.registrarEvento("Total de rondas: " + rondas);
        RegistroPartida.registrarEvento("Total de daño recibido en este combate: " + danioTotalEnemigo);
        RegistroPartida.registrarEvento("Total de daño infligido en este combate: " + danioTotalJugador);

        RegistroPartida.registrarEvento("------------------------------");

        rondas = 0;
        danioTotalJugador = 0;
        danioTotalEnemigo = 0;
    }
}
