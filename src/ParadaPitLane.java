import java.util.Objects;
import java.util.Random;

/**
 * La clase ParadaPitLane representa una parada en el pit lane de una carrera.
 */
public class ParadaPitLane {
    private Integer vuelta; // La vuelta en la que se realiza la parada en el pit lane.
    private long timeParada; // El tiempo de duración de la parada en el pit lane en segundos.

    /**
     * Constructor de la clase ParadaPitLane.
     *
     * @param vuelta La vuelta en la que se realiza la parada en el pit lane.
     */
    public ParadaPitLane(Integer vuelta) {
        Random rdn = new Random();
        this.timeParada = rdn.nextLong() % (5 - 2 + 1) + 2;
        this.vuelta = vuelta;
    }

    /**
     * Obtiene la vuelta en la que se realiza la parada en el pit lane.
     *
     * @return La vuelta de la parada.
     */
    public Integer getVuelta() {
        return vuelta;
    }

    /**
     * Establece la vuelta en la que se realiza la parada en el pit lane.
     *
     * @param vuelta La vuelta de la parada a establecer.
     */
    public void setVuelta(Integer vuelta) {
        this.vuelta = vuelta;
    }

    /**
     * Obtiene el tiempo de duración de la parada en el pit lane.
     *
     * @return El tiempo de duración de la parada en segundos.
     */
    public long getTimeParada() {
        return timeParada;
    }

    /**
     * Establece el tiempo de duración de la parada en el pit lane.
     *
     * @param timeParada El tiempo de duración de la parada en segundos a establecer.
     */
    public void setTimeParada(long timeParada) {
        this.timeParada = timeParada;
    }

    /**
     * Compara dos objetos de tipo ParadaPitLane para determinar si son iguales.
     *
     * @param o El objeto con el que se compara.
     * @return `true` si los objetos son iguales, de lo contrario, `false`.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ParadaPitLane that)) return false;
        return getTimeParada() == that.getTimeParada() && Objects.equals(getVuelta(), that.getVuelta());
    }

    /**
     * Calcula el valor hash de la instancia de ParadaPitLane.
     *
     * @return El valor hash calculado.
     */
    @Override
    public int hashCode() {
        return Objects.hash(getVuelta(), getTimeParada());
    }

    /**
     * Convierte la instancia de ParadaPitLane en una cadena de texto.
     *
     * @return Una representación en cadena de la instancia de ParadaPitLane.
     */
    @Override
    public String toString() {
        return "ParadaPitLane{" +
                "vuelta=" + vuelta +
                ", timeParada=" + timeParada +
                '}';
    }
}
