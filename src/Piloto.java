import java.util.Arrays;
import java.util.Objects;
import java.util.Random;

/**
 * La clase Piloto representa a un piloto de carreras con su nombre, paradas en el pit lane,
 * vuelta actual y tiempo total.
 */
public class Piloto implements Comparable {
    /**
     * El nombre del piloto.
     */
    private String nombre;

    /**
     * Arreglo de paradas en el pit lane del piloto.
     */
    private ParadaPitLane[] paradas;

    /**
     * La vuelta actual en la que se encuentra el piloto.
     */
    private Integer vueltaActual = 0;

    /**
     * El tiempo total que ha tomado al piloto completar todas las vueltas.
     */
    private long timeEnd = 0;

    /**
     * Constructor de la clase Piloto.
     *
     * @param paradas Arreglo de paradas en el pit lane.
     * @param nombre  El nombre del piloto.
     */
    public Piloto(ParadaPitLane[] paradas, String nombre) {
        this.timeEnd = 0;
        this.nombre = nombre;
        this.vueltaActual = 0;
        this.paradas = new ParadaPitLane[5];
        for (int i = 0; i < 5; i++) {
            this.paradas[i] = paradas[i];
        }
    }

    /**
     * Registra una vuelta recorrida por el piloto.
     *
     * @param numVuelta Número de la vuelta recorrida.
     */
    public void recorrerVuelta(Integer numVuelta) {
        this.vueltaActual = numVuelta;
        System.out.println("\uD83C\uDFCE\uFE0F Piloto: " + this.nombre + " recorriendo la vuelta: " + numVuelta);
        long timeOld = this.timeEnd;
        anadirTiempoVuelta();
        long timeNew = this.timeEnd;
        System.out.println("\uD83C\uDFCE\uFE0F Piloto: " + this.nombre + " -> \uD83D\uDD53 terminó la vuelta: " + numVuelta + " en: " + (timeNew - timeOld) + "s - tiempo total: " + timeEnd + "s");
    }

    /**
     * Añade al piloto al pit lane.
     *
     * @param pitLane               Arreglo de pilotos en el pit lane.
     * @param numerosPilotosPitLane Número de pilotos en el pit lane.
     */
    public void anadirPilotoPitLane(Piloto[] pitLane, int numerosPilotosPitLane) {
        pitLane[numerosPilotosPitLane] = this;
    }

    /**
     * Añade el tiempo de una vuelta al tiempo total del piloto.
     */
    public void anadirTiempoVuelta() {
        Random rdn = new Random();
        long tiempoVuelta = (rdn.nextLong() % (350 - 200 - 1) + 200);
        this.timeEnd = this.timeEnd + tiempoVuelta;
    }

    /**
     * Obtiene el arreglo de paradas en el pit lane del piloto.
     *
     * @return Arreglo de paradas en el pit lane.
     */
    public ParadaPitLane[] getParadas() {
        return paradas;
    }

    /**
     * Establece el arreglo de paradas en el pit lane del piloto.
     *
     * @param paradas Nuevo arreglo de paradas en el pit lane.
     */
    public void setParadas(ParadaPitLane[] paradas) {
        this.paradas = new ParadaPitLane[5];
        for (int i = 0; i < 5; i++) {
            this.paradas[i] = new ParadaPitLane(paradas[i].getVuelta());
        }
    }

    /**
     * Constructor de la clase Piloto con solo el nombre.
     *
     * @param nombre El nombre del piloto.
     */
    public Piloto(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene el nombre del piloto.
     *
     * @return El nombre del piloto.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre del piloto.
     *
     * @param nombre El nuevo nombre del piloto.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene la vuelta actual del piloto.
     *
     * @return La vuelta actual del piloto.
     */
    public Integer getVueltaActual() {
        return vueltaActual;
    }

    /**
     * Establece la vuelta actual del piloto.
     *
     * @param vueltaActual La nueva vuelta actual del piloto.
     */
    public void setVueltaActual(Integer vueltaActual) {
        this.vueltaActual = vueltaActual;
    }

    /**
     * Obtiene el tiempo total del piloto.
     *
     * @return El tiempo total del piloto.
     */
    public long getTimeEnd() {
        return timeEnd;
    }

    /**
     * Establece el tiempo total del piloto.
     *
     * @param timeEnd El nuevo tiempo total del piloto.
     */
    public void setTimeEnd(long timeEnd) {
        this.timeEnd = timeEnd;
    }

    @Override
    public int compareTo(Object o) {
        Piloto otroPiloto = (Piloto) o;
        return Long.compare(this.timeEnd, otroPiloto.timeEnd);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Piloto piloto)) return false;
        return getTimeEnd() == piloto.getTimeEnd() && Objects.equals(getNombre(), piloto.getNombre()) && Arrays.equals(getParadas(), piloto.getParadas()) && Objects.equals(getVueltaActual(), piloto.getVueltaActual());
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(getNombre(), getVueltaActual(), getTimeEnd());
        result = 31 * result + Arrays.hashCode(getParadas());
        return result;
    }

    @Override
    public String toString() {
        return "Piloto{" +
                "nombre='" + nombre + '\'' +
                ", paradas=" + Arrays.toString(paradas) +
                ", vueltaActual=" + vueltaActual +
                ", timeEnd=" + timeEnd +
                '}';
    }
}
