import java.util.LinkedList;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {



        // Se crea el circuito
        // y se añaden los pilotos:
        Circuito circuito = new Circuito( 5, 75);
        for (int i =0; i<20; i++){
            circuito.anadirPiloto("piloto__"+i);
        }

        // Se ejecuta la carrera
        circuito.recorrerCircuito();
        circuito.mostrarClasificacion();

    }
}