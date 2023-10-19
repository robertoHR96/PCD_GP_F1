import java.util.LinkedList;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
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
        int estrategia = 0;
        for (int i = 0; i < numPilotos; i++) {
            circuito.anadirPiloto("piloto__" + i, listaEstragias.get(i % 2));
        }

        // Se ejecuta la carrera
        circuito.recorrerCircuito();
        circuito.mostrarClasificacion();

    }
}