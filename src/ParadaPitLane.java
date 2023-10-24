import java.util.Objects;
import java.util.Random;

/**
 * La clase ParadaPitLane representa una parada en el pit lane durante una carrera.
 */
public class ParadaPitLane {
    /**
     * La vuelta en la que se realiza la parada.
     */
    private Integer vuelta;

    /**
     * El tiempo de la parada en el pit lane.
     */
    private long timeParada;

    /**
     * Constructor de la clase ParadaPitLane.
     *
     * @param vuelta La vuelta en la que se realiza la parada.
     */
    public ParadaPitLane(Integer vuelta) {
        Random rdn = new Random();
        this.timeParada = rdn.nextLong() % (5 - 2 + 1) + 2;
        this.vuelta = vuelta;
    }

    /**
     * Obtiene la vuelta en la que se realiza la parada.
     *
     * @return La vuelta en la que se realiza la parada.
     */
    public Integer getVuelta() {
        return vuelta;
    }

    /**
     * Establece la vuelta en la que se realiza la parada.
     *
     * @param vuelta La vuelta en la que se realiza la parada.
     */
    public void setVuelta(Integer vuelta) {
        this.vuelta = vuelta;
    }

    /**
     * Obtiene el tiempo de la parada en el pit lane.
     *
     * @return El tiempo de la parada en el pit lane.
     */
    public long getTimeParada() {
        return timeParada;
    }

    /**
     * Establece el tiempo de la parada en el pit lane.
     *
     * @param timeParada El tiempo de la parada en el pit lane.
     */
    public void setTimeParada(long timeParada) {
        this.timeParada = timeParada;
    }

    /**
     * Compara esta ParadaPitLane con otro objeto para determinar si son iguales.
     *
     * @param o El objeto a comparar con esta ParadaPitLane.
     * @return `true` si los objetos son iguales, `false` en caso contrario.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ParadaPitLane that)) return false;
        return getTimeParada() == that.getTimeParada() && Objects.equals(getVuelta(), that.getVuelta());
    }

    /**
     * Calcula el código hash para esta ParadaPitLane.
     *
     * @return El código hash de la ParadaPitLane.
     */
    @Override
    public int hashCode() {
        return Objects.hash(getVuelta(), getTimeParada());
    }

    /**
     * Obtiene una representación de cadena de la ParadaPitLane.
     *
     * @return Una representación de cadena que muestra los atributos de la ParadaPitLane.
     */
    @Override
    public String toString() {
        return "ParadaPitLane{" +
                "vuelta=" + vuelta +
                ", timeParada=" + timeParada +
                '}';
    }
}
