import java.util.LinkedList;
import java.util.Random;

public class ParadaPitLane {
    private Integer vuelta;

    private long timeParada;
    public ParadaPitLane(Integer vuelta){
        Random rdn = new Random();
        this.timeParada = rdn.nextLong() % (5 - 2 + 1 ) + 2;
        this.vuelta=vuelta;
    }

    public Integer getVuelta() {
        return vuelta;
    }

    public void setVuelta(Integer vuelta) {
        this.vuelta = vuelta;
    }

    public long getTimeParada() {
        return timeParada;
    }

    public void setTimeParada(long timeParada) {
        this.timeParada = timeParada;
    }
}
