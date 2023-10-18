import java.util.LinkedList;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {



        // Se crea el circuito
        // y se añaden los pilotos:
        Integer numPilotos=20;
        Circuito circuito = new Circuito( 5, 10, numPilotos);
        for (int i =0; i<numPilotos; i++){
            circuito.anadirPiloto("piloto__"+i);
        }

        // Se ejecuta la carrera
        circuito.recorrerCircuito();
        circuito.mostrarClasificacion();

    }
}