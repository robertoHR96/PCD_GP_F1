import java.util.*;
import java.util.*;
/**
 * La clase Circuito representa un circuito de carreras con pilotos y un pit lane.
 */
public class Circuito {
    /**
     * Lista de pilotos en el circuito.
     */
    private List<Piloto> listaPilotos = new LinkedList<Piloto>();

    /**
     * Número de vueltas en la carrera.
     */
    private Integer numeroVueltas = 75;

    /**
     * Número máximo de pilotos en el circuito.
     */
    private Integer numeroPilotos = 20;

    /**
     * Capacidad del pit lane.
     */
    private Integer capacidadPitLane = 5;

    /**
     * Array que representa el pit lane.
     */
    private Piloto[] pitLane = new Piloto[5];

    /**
     * Número de pilotos en el pit lane.
     */
    private int numerosPilotosPitLane = 0;

    /**
     * Constructor de la clase Circuito.
     *
     * @param listaPilotos     Lista de pilotos en el circuito.
     * @param capacidadPitLane Capacidad del pit lane.
     * @param numeroVueltas    Número de vueltas en la carrera.
     */
    public Circuito(List<Piloto> listaPilotos, Integer capacidadPitLane, Integer numeroVueltas) {
        this.capacidadPitLane = capacidadPitLane;
        this.pitLane = new Piloto[capacidadPitLane];
        this.numeroVueltas = numeroVueltas;
        this.numeroPilotos = 20;
        this.listaPilotos = new LinkedList<Piloto>();
        this.numerosPilotosPitLane = 0;
        Iterator it = listaPilotos.iterator();

        int countMax = 0;
        while (it.hasNext() && countMax < this.numeroPilotos) {
            this.listaPilotos.add((Piloto) it.next());
            countMax++;
        }
    }

    /**
     * Ejecuta la carrera para todos los pilotos en el circuito.
     */
    public void run() {
        Iterator it = this.listaPilotos.iterator();
        while (it.hasNext()) {
            Piloto pilotoCorriendo = (Piloto) it.next();
            recorrerCircuito(pilotoCorriendo);
        }
        mostrarClasificacion();
    }

    /**
     * Hace que un piloto recorra el circuito durante un número de vueltas.
     *
     * @param piloto El piloto que recorrerá el circuito.
     */
    public void recorrerCircuito(Piloto piloto) {
        for (int i = 0; i < numeroVueltas; i++) {
            piloto.recorrerVuelta(i);
            comprobarSiParada(i, piloto);
        }
    }

    /**
     * Comprueba si un piloto necesita realizar una parada en el pit lane en una vuelta específica.
     *
     * @param numVuelta La vuelta actual.
     * @param piloto    El piloto que se está comprobando.
     */
    public void comprobarSiParada(Integer numVuelta, Piloto piloto) {
        for (int i = 0; i < piloto.getParadas().length; i++) {
            if (piloto.getParadas()[i].getVuelta() == numVuelta) {
                System.out.println("\uD83D\uDED1 Piloto: " + piloto.getNombre() + " entrando en PitLane");
                piloto.setTimeEnd(piloto.getTimeEnd() + piloto.getParadas()[i].getTimeParada());
                anadirPilotoPitLane(piloto);
                System.out.println("\uD83D\uDD27 Piloto: " + piloto.getNombre() + " salió del PitLane - Time PitLane: " + piloto.getParadas()[i].getTimeParada());
                sacarPilotoPitLane(piloto);
            }
        }
    }

    /**
     * Elimina un piloto del pit lane.
     *
     * @param piloto El piloto a eliminar del pit lane.
     */
    public void sacarPilotoPitLane(Piloto piloto) {
        for (int i = 0; i < pitLane.length; i++) {
            if ((this.pitLane[i] != null) && (this.pitLane[i].getNombre() == piloto.getNombre())) {
                this.pitLane[i] = null;
            }
        }
    }

    /**
     * Añade un piloto al pit lane.
     *
     * @param piloto El piloto a añadir al pit lane.
     */
    public void anadirPilotoPitLane(Piloto piloto) {
        this.pitLane[numerosPilotosPitLane] = piloto;
    }

    /**
     * Muestra la clasificación de la carrera.
     */
    public void mostrarClasificacion() {
        String[] medallas = {"\uD83E\uDD47", "\uD83E\uDD48", "\uD83E\uDD49"};
        Collections.sort(listaPilotos);
        Iterator it = listaPilotos.iterator();
        Integer count = 1;
        while (it.hasNext()) {
            Piloto piloto = (Piloto) it.next();
            if (count <= 3) {
                System.out.println(medallas[count - 1] + count.toString() + "ª posición -> Piloto:  " + piloto.getNombre() + ", Time : " + piloto.getTimeEnd());
            } else {
                System.out.println(count.toString() + "ª posición -> Piloto:  " + piloto.getNombre() + ", Time : " + piloto.getTimeEnd());
            }
            count++;
        }
    }

    /**
     * Obtiene la lista de pilotos en el circuito.
     *
     * @return La lista de pilotos en el circuito.
     */
    public List<Piloto> getListaPilotos() {
        return listaPilotos;
    }

    /**
     * Establece la lista de pilotos en el circuito.
     *
     * @param listaPilotos La lista de pilotos a establecer.
     */
    public void setListaPilotos(List<Piloto> listaPilotos) {
        this.listaPilotos = new LinkedList<Piloto>();
        Iterator it = listaPilotos.iterator();
        int countMax = 0;
        while (it.hasNext() && countMax < this.numeroPilotos) {
            this.listaPilotos.add((Piloto) it.next());
            countMax++;
        }
    }

    /**
     * Añade un piloto a la lista de pilotos en el circuito.
     *
     * @param piloto El piloto a añadir.
     */
    public void addPiloto(Piloto piloto) {
        this.listaPilotos.add(piloto);
    }

    /**
     * Obtiene el número de vueltas en la carrera.
     *
     * @return El número de vueltas en la carrera.
     */
    public Integer getNumeroVueltas() {
        return numeroVueltas;
    }

    /**
     * Establece el número de vueltas en la carrera.
     *
     * @param numeroVueltas El número de vueltas a establecer.
     */
    public void setNumeroVueltas(Integer numeroVueltas) {
        this.numeroVueltas = numeroVueltas;
    }

    /**
     * Obtiene el número máximo de pilotos en el circuito.
     *
     * @return El número máximo de pilotos en el circuito.
     */
    public Integer getNumeroPilotos() {
        return numeroPilotos;
    }

    /**
     * Establece el número máximo de pilotos en el circuito.
     *
     * @param numeroPilotos El número máximo de pilotos a establecer.
     */
    public void setNumeroPilotos(Integer numeroPilotos) {
        this.numeroPilotos = numeroPilotos;
    }

    /**
     * Obtiene la capacidad del pit lane.
     *
     * @return La capacidad del pit lane.
     */
    public Integer getCapacidadPitLane() {
        return capacidadPitLane;
    }

    /**
     * Establece la capacidad del pit lane.
     *
     * @param capacidadPitLane La capacidad del pit lane a establecer.
     */
    public void setCapacidadPitLane(Integer capacidadPitLane) {
        this.capacidadPitLane = capacidadPitLane;
    }

    /**
     * Obtiene el array que representa el pit lane.
     *
     * @return El array que representa el pit lane.
     */
    public Piloto[] getPitLane() {
        return pitLane;
    }

    /**
     * Establece el array que representa el pit lane.
     *
     * @param pitLane El array que representa el pit lane a establecer.
     */
    public void setPitLane(Piloto[] pitLane) {
        this.pitLane = pitLane;
    }

    /**
     * Obtiene el número de pilotos en el pit lane.
     *
     * @return El número de pilotos en el pit lane.
     */
    public int getNumerosPilotosPitLane() {
        return numerosPilotosPitLane;
    }

    /**
     * Establece el número de pilotos en el pit lane.
     *
     * @param numerosPilotosPitLane El número de pilotos en el pit lane a establecer.
     */
    public void setNumerosPilotosPitLane(int numerosPilotosPitLane) {
        this.numerosPilotosPitLane = numerosPilotosPitLane;
    }

    /**
     * Compara este Circuito con otro objeto para determinar si son iguales.
     *
     * @param o El objeto a comparar con este Circuito.
     * @return `true` si los objetos son iguales, `false` en caso contrario.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Circuito circuito)) return false;
        return getNumerosPilotosPitLane() == circuito.getNumerosPilotosPitLane() && Objects.equals(getListaPilotos(), circuito.getListaPilotos()) && Objects.equals(getNumeroVueltas(), circuito.getNumeroVueltas()) && Objects.equals(getNumeroPilotos(), circuito.getNumeroPilotos()) && Objects.equals(getCapacidadPitLane(), circuito.getCapacidadPitLane()) && Arrays.equals(getPitLane(), circuito.getPitLane());
    }

    /**
     * Calcula el código hash para este Circuito.
     *
     * @return El código hash del Circuito.
     */
    @Override
    public int hashCode() {
        int result = Objects.hash(getListaPilotos(), getNumeroVueltas(), getNumeroPilotos(), getCapacidadPitLane(), getNumerosPilotosPitLane());
        result = 31 * result + Arrays.hashCode(getPitLane());
        return result;
    }

    /**
     * Obtiene una representación de cadena del Circuito.
     *
     * @return Una representación de cadena que muestra los atributos del Circuito.
     */
    @Override
    public String toString() {
        return "Circuito{" +
                "listaPilotos=" + listaPilotos +
                ", numeroVueltas=" + numeroVueltas +
                ", numeroPilotos=" + numeroPilotos +
                ", capacidadPitLane=" + capacidadPitLane +
                ", pitLane=" + Arrays.toString(pitLane) +
                ", numerosPilotosPitLane=" + numerosPilotosPitLane +
                '}';
    }
}
