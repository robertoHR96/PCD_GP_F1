import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * La clase Main es la clase principal que contiene el método main para ejecutar el programa.
 */
public class Main {
    /**
     * El método main es el punto de entrada del programa.
     *
     * @param args Los argumentos de la línea de comandos (no se utilizan en este programa).
     */
    public static void main(String[] args) {

        // lista de estrategias
        LinkedList<ParadaPitLane[]> listaEstragias = new LinkedList<ParadaPitLane[]>();

        int parada1 = 5;
        int parada2 = 10;
        int parada3 = 20;
        int parada4 = 30;
        int parada5 = 40;
        // Se crea el array de paradas
        ParadaPitLane[] paradasPiloto = {
                new ParadaPitLane(parada1), new ParadaPitLane(parada2),
                new ParadaPitLane(parada3), new ParadaPitLane(parada4),
                new ParadaPitLane(parada5),
        };
        listaEstragias.add(paradasPiloto);

        parada1 = 6;
        parada2 = 9;
        parada3 = 21;
        parada4 = 37;
        parada5 = 70;
        // Se crea el array de paradas
        ParadaPitLane[] paradasPiloto2 = {
                new ParadaPitLane(parada1), new ParadaPitLane(parada2),
                new ParadaPitLane(parada3), new ParadaPitLane(parada4),
                new ParadaPitLane(parada5),
        };
        listaEstragias.add(paradasPiloto2);

        parada1 = 23;
        parada2 = 40;
        parada3 = 45;
        parada4 = 52;
        parada5 = 68;
        // Se crea el array de paradas
        ParadaPitLane[] paradasPiloto3 = {
                new ParadaPitLane(parada1), new ParadaPitLane(parada2),
                new ParadaPitLane(parada3), new ParadaPitLane(parada4),
                new ParadaPitLane(parada5),
        };
        listaEstragias.add(paradasPiloto3);

        // Se crea el circuito
        // y se añaden los pilotos:
        Integer numPilotos = 20;
        Circuito circuito = new Circuito(5, 75, numPilotos);
        introducirNumeroVueltas(circuito);
        int estrategia = 0;
        for (int i = 0; i < numPilotos; i++) {
            añadirPiloto(circuito, listaEstragias);
            //circuito.anadirPiloto("piloto__" + i, listaEstragias.get(i % 2));
        }

        // Se ejecuta la carrera
        circuito.recorrerCircuito();
        circuito.mostrarClasificacion();

    }

    /**
     * Añade un piloto a la carrera
     * @return Piloto El piloto añadido a la carrera
     */
    public static Piloto añadirPiloto(Circuito circuito, LinkedList<ParadaPitLane[]> listaEstrategias) {
        String nombrePiloto;
        String elegirEztrategia;
        Scanner reader = new Scanner(System.in);

        // Se pregunta por el nombre de piloto a añadir
        System.out.println("Eliga el nombre del piloto: ");
        nombrePiloto = reader.next();
        // si el nombre es erroneo se vuele a pedir
        while (nombrePiloto.equals(" ")) {
            System.out.println("Nombre de piloto no valido");
            System.out.println("Eliga el nombre del piloto: ");
            nombrePiloto = reader.next();
        }

        // Se pregunta si quiere selecionar una estragia de paradas en el pit-lane personalizada
        System.out.println("¿Quiere elegir al estrategia de pitLane para el piloto manualmente s/n ?");
        elegirEztrategia= reader.next();

        // Se controla que solo pueda eleigir s/n
        while (!elegirEztrategia.equals("s") && !elegirEztrategia.equals("n")) {
            System.out.println("Solo puede introducir s/n");
            System.out.println("¿Quiere elegir al estrategia de pitLane para el piloto manualmente s/n ?");
            elegirEztrategia= reader.next();
        }

        // Si seleciona s, se ejecuta seleccionarEstrategiaPersonalizada para que selecione una de las pre-cargadas
        if(elegirEztrategia.equals("s")) {
            ParadaPitLane[] estrategiaPersonalizada = seleccionarEstrategiaPersonalizada(listaEstrategias);
        }else{
        // Si selecciona n, se eleige una aleatoria
        }
        return new Piloto();
    }

    /**
     * Devuelve una estrategia de paradas en el pit-lane "personaliza" para el piloto
     * @return ParadaPitlane[] Estrategia de paradas personalizada
     */
    public static ParadaPitLane [] seleccionarEstrategiaPersonalizada(LinkedList<ParadaPitLane[]> listaEstrategias){
        int cont=0;
        Iterator it = listaEstrategias.iterator();
        // Se muestran todas las estrategias
        while (it.hasNext()){
            ParadaPitLane [] paradasPit = (ParadaPitLane[]) it.next();
            mostrarParadas(paradasPit, cont);
            cont++;
        }
        Scanner reader = new Scanner(System.in);
        boolean ok = false;
        // se controla que solo pueda elegir una estrategia valida
        while (ok == false) {
            try {
                // Se pregunta que estrategia quiere
                System.out.println("Introducca el ID de la estrategia seleccionada : ");
                int idEstrategia = reader.nextInt();
                // Se comprueba que el id-estrateia es valido
                if(idEstrategia<0 && idEstrategia<listaEstrategias.size()){
                    System.out.println("Estrategia: "+idEstrategia+" seleccionada correctamente");
                    ok = true;
                    return listaEstrategias(idEstrategia);
                }else{
                    // Se le dice al usuario que el id-estrategia no es valido
                    System.out.println("Id-estragia no valido");
                }
            } catch (Exception e) {
                // Se caza el error al selecionar la estragia
                System.out.println("Solo puede introducir números");
                reader.next();
            }
        }
        return new ParadaPitLane[5];
    }

    /**
     * Muestra una estrategia de paradas de pit-lane
     * @param paradasPit Estrategia de paradas ha mostrar
     * @param contadorEstrategias Numero de estrategia
     */
    public static void mostrarParadas(ParadaPitLane[] paradasPit, int contadorEstrategias){
        System.out.println("-------- Estrategia :"+contadorEstrategias+" --------");
        for(int i = 0; i< paradasPit.length; i++){
            System.out.print(paradasPit[i]+ " ");
        }
        System.out.println("------------------------------");
    }

    /**
     * Da la opción de establecer el número de vueltas que tendrá la carrera
     * @param circuito Circuito al cual se le modificará el número de vueltas
     */
    public static void introducirNumeroVueltas(Circuito circuito) {
        Scanner reader = new Scanner(System.in);
        // Se pregunta si se quiere modificar el numero de vueltas de la carrera
        System.out.println("¿Desea modificar el numero el número de vueltas que tendrá la carrera s/n ? (default 75)");
        // Se lee la respuesta
        String mod = reader.next();
        // Mientras que la respuesta no sea valida se vuelve a pedir
        while (!mod.equals("n") && !mod.equals("s")) {
            System.out.println("Solo puede introducir s/n");
            System.out.println("¿Desea modificar el numero el número de vueltas que tendrá la carrera s/n ? (default 75)");
            mod = reader.next();
        }
        // Si se desea modificar
        if (mod.equals("s")) {
            boolean ok = false;
            // Contralamos que solo se puedan introducir numeros
            while (ok == false) {
                try {
                    System.out.println("Introducca el numero de vueltas : ");
                    int numVueltas = reader.nextInt();
                    circuito.setNumeroVueltas(numVueltas);
                    System.out.println("Numero de vueltas actualizado a :" + numVueltas);
                    ok = true;
                } catch (Exception e) {
                    // En caso de no introducir un número
                    System.out.println("Solo puede introducir números");
                    reader.next();
                }
            }
        }

    }
}
