import java.util.LinkedList;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {

        // paradas en las que se pararan los pilotos
        int parada1 = 5;
        int parada2 = 10;
        int parada3 = 15;
        int parada4 = 20;
        int parada5 = 25;
        // Se crea el array de paradas
        ParadaPitLane[] paradasPiloto = {new ParadaPitLane(parada1), new ParadaPitLane(parada2), new ParadaPitLane(parada3), new ParadaPitLane(parada4), new ParadaPitLane(parada5)};
        // Se crean los pilotos

        // Se añaden los pilotos a lista

        // Se crea el circuito
        Circuito circuito = new Circuito( 5, 31);
        for (int i =0; i<20; i++){
            circuito.anadirPiloto("piloto__"+i);
        }

        // Se ejecuta la carrera
        circuito.recorrerCircuito();
        circuito.mostrarClasificacion();

    }
}