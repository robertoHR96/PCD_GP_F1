import java.util.Random;
import java.util.SplittableRandom;

public class Piloto implements Comparable, Runnable {

    private int id=0;
    private String nombre;

    private ParadaPitLane[] paradas;

    private Integer vueltaActual = 0;

    private long timeEnd = 0;

    private Integer numeroVueltas = 75;

    private static Integer[] pitLane = new Integer [5];
    private static Integer numerosPilotosPitLane = 0;

    /************************* Constructores *************************/
    public Piloto(ParadaPitLane[] paradas, String nombre, int id, int numeroVueltas) {
        this.id=id;
        this.timeEnd = 0;
        this.numeroVueltas=numeroVueltas;
        this.nombre = nombre;
        this.vueltaActual = 0;
        this.paradas = new ParadaPitLane[5];
        numerosPilotosPitLane = 0;
        for (int i = 0; i < 5; i++) {
            this.paradas[i] = paradas[i];
        }
    }

    /************************* Métodos propios*************************/
    @Override
    public void run() {
        for(int i =0; i<numeroVueltas; i++){
            recorrerVuelta(i);
            // aqui es donde entra la sincronizacion ???
            comprobarSiParada(i);
        }
    }
    public void recorrerVuelta(Integer numVuelta) {
        this.vueltaActual = numVuelta;
        // Se comprueba si hay una parada en el pitlane en esa vuelta
        System.out.println("\uD83C\uDFCE\uFE0F Piloto: " + this.nombre + " recorriendo al vuelta: " + numVuelta);
        //comprobarSiParada(numVuelta);
        // Se añade el tiempo de la vuelta al timeEnd
        // se guarda el tiempo antes de calcular el tiempo de vuelta
        long timeOld = this.timeEnd;
        // se le añade el teimpo de vuelta al piloto
        anadirTiempoVuelta();
        // se obiten el tiempo nuevo
        long timeNew = this.timeEnd;
        // aqui emepiza lo secuencial
        System.out.println("\uD83C\uDFCE\uFE0F Piloto: " + this.nombre + " -> \uD83D\uDD53 termino la vuelta: " + numVuelta + " en: " + (timeNew - timeOld) + "s - tiempo total: " + timeEnd + "s");
    }

    public void anadirTiempoVuelta() {
        Random rdn = new Random();
        long tiempoVuelta = (rdn.nextLong() % (350 - 200 - 1) + 200);
        this.timeEnd = this.timeEnd + tiempoVuelta;
    }

    public void comprobarSiParada(Integer numVuelta ) {
        for (int i = 0; i < this.paradas.length; i++) {
            if (this.paradas[i].getVuelta() == numVuelta) {
                System.out.println("\uD83D\uDED1 Piloto: " + this.nombre + " entrando en PitLane");
                this.timeEnd= this.timeEnd + this.paradas[i].getTimeParada();
                anadirPilotoPitLane();
                System.out.println("\uD83D\uDD27 Piloto: " + this.nombre + " salio del PitLane - Time PitLane: " + this.paradas[i].getTimeParada());
                sacarPilotoPitLane();
            }
        }
    }

    public void sacarPilotoPitLane() {
        for (int i = 0; i < pitLane.length; i++) {
            if ((this.pitLane[i] != null) && (this.pitLane[i] == this.id )) {
                this.pitLane[i] = null;
            }
        }
    }

    public void anadirPilotoPitLane() {
        this.pitLane[numerosPilotosPitLane] = this.id;
    }


    /************************* Getter and Setter *************************/

    public ParadaPitLane[] getParadas() {
        return paradas;
    }

    public void setParadas(ParadaPitLane[] paradas) {
        this.paradas = new ParadaPitLane[5];
        for (int i = 0; i < 5; i++) {
            this.paradas[i] = new ParadaPitLane(paradas[i].getVuelta());
        }
    }

    public Piloto(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getVueltaActual() {
        return vueltaActual;
    }

    public void setVueltaActual(Integer vueltaActual) {
        this.vueltaActual = vueltaActual;
    }

    public long getTimeEnd() {
        return timeEnd;
    }

    public void setTimeEnd(long timeEnd) {
        this.timeEnd = timeEnd;
    }

    @Override
    public int compareTo(Object o) {
        Piloto otroPiloto = (Piloto) o;
        return Long.compare(this.timeEnd, otroPiloto.timeEnd);
    }


}
