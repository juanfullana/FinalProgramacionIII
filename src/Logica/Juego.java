package Logica;

import java.util.Scanner;
    import utnydragones.criaturas.*;

    public class Juego {
        private Guerrero jugador;

        private void mostrarBienvenida() {
            System.out.println(" #     # ####### #     #    #     #    ######  ######     #     #####  ####### #     # #######  #####  ");
            System.out.println(" #     #    #    ##    #     #   #     #     # #     #   # #   #     # #     # ##    # #       #     # ");
            System.out.println(" #     #    #    # #   #      # #      #     # #     #  #   #  #       #     # # #   # #       #       ");
            System.out.println(" #     #    #    #  #  #       #       #     # ######  #     # #  #### #     # #  #  # #####    #####  ");
            System.out.println(" #     #    #    #   # #       #       #     # #   #   ####### #     # #     # #   # # #             # ");
            System.out.println(" #     #    #    #    ##       #       #     # #    #  #     # #     # #     # #    ## #       #     # ");
            System.out.println("  #####     #    #     #       #       ######  #     # #     #  #####  ####### #     # #######  #####  ");
            System.out.println();
        }

        private boolean preguntarInstructivo() {
            Scanner scanner = new Scanner(System.in);
            String respuesta;

            while (true) {
                System.out.println("¿Desea leer un instructivo antes de jugar? (SI/NO): ");
                respuesta = scanner.nextLine().toUpperCase();

                if (respuesta.equals("SI") || respuesta.equals("NO")) {
                    break;
                } else {
                    System.out.println("Por favor, ingrese 'SI' o 'NO'.");
                }
            }

            return respuesta.equals("SI");
        }

        private void mostrarInformacion() {
            // Información adicional
            System.out.println("---- INFORMACIÓN ----");
            System.out.println("Este es un juego de rol que cuenta con los aspectos básicos de los mismos.");
            System.out.println("Usted elegirá un personaje de entre 3 posibilidades:");
            System.out.println("1. Bárbaro - Especialista en daño y brutalidad");
            System.out.println("2. Paladin - Guerrero equilibrado especialista en las artes sagradas");
            System.out.println("3. Mago - Enigmático ser especialista en las artes elementales");
            System.out.println("Gracias al poder del guión, nuestros guerreros atacarán más veces que sus enemigos");

            System.out.println("\n---- OBJETIVO ----");
            System.out.println("El objetivo del juego es derrotar a las 4 criaturas que encontrará en las salas de este calabozo.");
            System.out.println("El calabozo es progresivo y lineal. Cada oponente que derrote le otorgará beneficios para los combates venideros.");
            System.out.println("Escójalos con cuidado.");
            System.out.println();
        }

        private Guerrero elegirPersonaje() {
            Scanner scanner = new Scanner(System.in);

            System.out.println("\n---- ELEGIR PERSONAJE ----");
            System.out.println("Seleccione un personaje:");
            System.out.println("1. Bárbaro");
            System.out.println("2. Paladin");
            System.out.println("3. Mago");

            int opcion = 0;
            while (opcion < 1 || opcion > 3) {
                System.out.print("Ingrese el número de su elección: ");
                try {
                    opcion = Integer.parseInt(scanner.nextLine());
                } catch (NumberFormatException e) {
                    System.out.println("Por favor, ingrese un número válido.");
                }
            }

            Guerrero jugador = null;

            // Crear el personaje seleccionado por el jugador
            switch (opcion) {
                case 1:
                    jugador = new Barbaro();
                    break;
                case 2:
                    jugador = new Paladin();
                    break;
                case 3:
                    jugador = new Mago();
                    break;
                default:
                    break;
            }

            System.out.println("Has seleccionado a " + jugador.getNombre() + ". ¡Que comience la aventura!\n");
            return jugador;
        }

        private void mostrarEstadisticasPersonaje(Criatura jugador) {
            // Mostrar las estadísticas del personaje seleccionado
            System.out.println("\n---- ESTADÍSTICAS DEL PERSONAJE ----");
            System.out.println("Nombre: " + jugador.getNombre());
            System.out.println("Salud: " + jugador.getSalud());
            System.out.println("Ataque: " + jugador.getAtaque());
            System.out.println("Defensa: " + jugador.getDefensa());
            System.out.println("Velocidad: " + jugador.getVelocidad());
            System.out.println("Energía: " + jugador.getEnergia());
            System.out.println("Evasión: " + jugador.getEvasion());

            System.out.println("\nCopia esta información en un papel, ya que es de vital importancia y no podrás consultarla hasta después de haber elegido un beneficio.");
        }

        private void relatoPrimeraSala() {
            System.out.println("\n---- PRIMERA SALA ----");
            System.out.println("Al entrar en la primera sala, te encuentras con una escena macabra.");
            System.out.println("Un Ghoul se está alimentando del cadáver de uno de tus compañeros caídos.");

            // Generar relato basado en el tipo de personaje seleccionado
            if (jugador instanceof Barbaro) {
                System.out.println("Enceguecido por la furia, cargas contra el Ghoul con determinación.");
            } else if (jugador instanceof Paladin) {
                System.out.println("Tras rezar por el alma de tu compañero, te enfrentas al Ghoul con la determinación de vengar a tu camarada caído.");
            } else if (jugador instanceof Mago) {
                System.out.println("Te lamentas por la pérdida, pero reconoces que ahora el botín será solo para ti.");
            }
        }

        public void comenzarJuego() {
            boolean jugarOtraVez;

            do {
                mostrarBienvenida();

                if (preguntarInstructivo()) {
                    mostrarInformacion();
                }

                jugador = elegirPersonaje();
                System.out.println("Guerrero elegido: " + jugador.getNombre() + ".");
                System.out.println("------------------------------");
                RegistroPartida.registrarEvento("Guerrero elegido: " + jugador.getNombre() + ".");
                mostrarEstadisticasPersonaje(jugador);

                relatoPrimeraSala();
                Criatura[] enemigos = {new Ghoul(), new Esqueleto(), new Fantasma(), new Demonio()};

                for (Criatura enemigo : enemigos) {
                    Combate combate = new Combate(jugador, enemigo);
                    combate.iniciarCombate();

                    if (jugador.getSalud() <= 0) {
                        System.out.println("¡Has sido derrotado! Fin del juego.");
                        RegistroPartida.registrarEvento("¡Has perdido! Tu guerrero ha sido derrotado.");
                        break;
                    }

                    obtenerMejoraDespuesEnemigo(jugador, enemigo);
                    mostrarEstadisticasPersonaje(jugador);
                }

                if (jugador.getSalud() > 0) {
                    System.out.println("¡Felicidades, has completado el juego!");
                    RegistroPartida.registrarEvento("¡Has ganado! Has derrotado a todos los enemigos y completado la aventura.");
                    RegistroPartida.registrarEvento("------------------------------");
                }

                // Preguntar al usuario si desea jugar otra vez
                jugarOtraVez = preguntarJugarOtraVez();

            } while (jugarOtraVez);

            System.out.println("¡Gracias por jugar! Hasta luego.");
        }


        private void obtenerMejoraDespuesEnemigo(Guerrero jugador, Criatura enemigo) {
            if (enemigo instanceof Ghoul) {
                mostrarMensajeDespuesMejoraGhoul(jugador);
            } else if (enemigo instanceof Esqueleto) {
                mostrarMensajeDespuesMejoraEsqueleto(jugador);
            } else if (enemigo instanceof Fantasma) {
                mostrarMensajeDespuesMejoraFantasma(jugador);
            } else if (enemigo instanceof Demonio) {
                mostrarMensajeDespuesMejoraDemonio(jugador);
            }
        }


        void mostrarMensajeDespuesMejoraGhoul(Guerrero jugador) {
            // Mensaje narrativo después de la mejora
            System.out.println("Mientras te terminas de colocar los guantes, sientes una figura alzándose detrás de ti.");

            // Mensajes específicos según la clase del jugador
            if (jugador instanceof Barbaro) {
                System.out.println("Es el cuerpo reanimado de tu compañero bárbaro. Te sientes honrado de darle descanso eterno a su compañero.");
            } else if (jugador instanceof Paladin) {
                System.out.println("Es el cuerpo reanimado de tu compañero paladín. Con mucho pesar, acabarás con quien fue su amigo.");
            } else if (jugador instanceof Mago) {
                System.out.println("Es el cuerpo reanimado de tu compañero mago. Hasta muerto te sigue fastidiando.");
            }

        }

        void mostrarMensajeDespuesMejoraEsqueleto(Guerrero jugador) {

            System.out.println("Habiéndote colocado la joya, una extraña figura comienza a emerger de los restos de tu difunto compañero.");


            if (jugador instanceof Barbaro) {
                System.out.println("Es un espíritu, optimista, crees que es el de tu compañero, a quien acabas de liberar del eterno tormento.");
            } else if (jugador instanceof Paladin) {
                System.out.println("Es un espíritu, optimista, crees que es el de tu compañero, a quien acabas de liberar del eterno tormento.");
            } else if (jugador instanceof Mago) {
                System.out.println("Es el espíritu de tu compañero mago. Verdaderamente un fastidio, ni muerto muere.");
            }
        }

        void mostrarMensajeDespuesMejoraFantasma(Guerrero jugador) {

            if (jugador instanceof Barbaro) {
                System.out.println("Juntos hasta el final, compañero.");
            } else if (jugador instanceof Paladin) {
                System.out.println("Que tu luz me guíe, compañero.");
            } else if (jugador instanceof Mago) {
                System.out.println("Ya no sé cómo deshacerme de ti.");
            }
        }

        void mostrarMensajeDespuesMejoraDemonio(Guerrero jugador) {

            if (jugador instanceof Barbaro) {
                System.out.println("La fuerza prevalece, siempre.");
            } else if (jugador instanceof Paladin) {
                System.out.println("La luz siempre triunfará.");
            } else if (jugador instanceof Mago) {
                System.out.println("Esos cuernos deben valer bien caro.");
            }
        }
        private boolean preguntarJugarOtraVez() {
            Scanner scanner = new Scanner(System.in);
            System.out.println("¿Quieres jugar otra vez? (SI/NO): ");
            String respuesta = scanner.nextLine().toUpperCase();
            return respuesta.equals("SI");
        }

    }