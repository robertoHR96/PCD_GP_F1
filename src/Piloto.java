import java.util.Random;
import java.util.SplittableRandom;

public class Piloto implements Comparable {
    private String nombre;

    private ParadaPitLane[] paradas;

    private Integer vueltaActual = 0;

    private long timeEnd = 0;

    /************************* Constructores *************************/
    public Piloto(ParadaPitLane[] paradas, String nombre) {
        this.timeEnd = 0;
        this.nombre = nombre;
        this.vueltaActual = 0;
        this.paradas = new ParadaPitLane[5];
        for (int i = 0; i < 5; i++) {
            this.paradas[i] = paradas[i];
        }
    }

    /************************* Métodos propios*************************/
    public void recorrerVuelta(Integer numVuelta) {
        this.vueltaActual = numVuelta;
        // Se comprueba si hay una parada en el pitlane en esa vuelta
        System.out.println("\uD83C\uDFCE\uFE0F Piloto: " + this.nombre + " recorriendo al vuelta: " + numVuelta);
        //comprobarSiParada(numVuelta);
        // Se añade el tiempo de la vuelta al timeEnd
        long timeOld = this.timeEnd;
        anadirTiempoVuelta();
        long timeNew = this.timeEnd;
        System.out.println("\uD83C\uDFCE\uFE0F Piloto: " + this.nombre + " -> \uD83D\uDD53 termino la vuelta: " + numVuelta + " en: " + (timeNew - timeOld) + "s - tiempo total: " + timeEnd + "s");
    }

    public void anadirPilotoPitLane(Piloto[] pitLane, int numerosPilotosPitLane) {
        pitLane[numerosPilotosPitLane] = this;
    }

    public void anadirTiempoVuelta() {
        Random rdn = new Random();
        long tiempoVuelta = (rdn.nextLong() % (350 - 200 - 1) + 200);
        this.timeEnd = this.timeEnd + tiempoVuelta;
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
