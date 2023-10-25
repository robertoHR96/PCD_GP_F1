import java.util.LinkedList;

/**
 * La clase Main contiene el método `main`, que inicia la simulación de una carrera en un circuito.
 */
public class Main {
    /**
     * Método principal que inicia la simulación de una carrera en un circuito.
     *
     * @param args Argumentos de línea de comandos (no se utilizan en este caso).
     */
    public static void main(String[] args) {

        // Paradas en las que se pararán los pilotos
        int parada1 = 5;
        int parada2 = 10;
        int parada3 = 15;
        int parada4 = 20;
        int parada5 = 25; // 5 paradas entre las 0 y 60 y que sean aletorias

        // Se crea el array de paradas
        ParadaPitLane[] paradasPiloto = {
                new ParadaPitLane(parada1),
                new ParadaPitLane(parada2),
                new ParadaPitLane(parada3),
                new ParadaPitLane(parada4),
                new ParadaPitLane(parada5)
        };

        // Se crean los pilotos
        Piloto piloto1 = new Piloto(paradasPiloto, "Piloto 1");
        Piloto piloto2 = new Piloto(paradasPiloto, "Piloto 2");
        Piloto piloto3 = new Piloto(paradasPiloto, "Piloto 3");

        // Se añaden los pilotos a la lista
        LinkedList<Piloto> listaPilotos = new LinkedList<Piloto>();
        listaPilotos.add(piloto1);
        listaPilotos.add(piloto2);
        listaPilotos.add(piloto3);

        // Se crea el circuito
        Circuito circuito = new Circuito(listaPilotos, 5, 75);

        // Se ejecuta la carrera
        circuito.run();
    }
}
