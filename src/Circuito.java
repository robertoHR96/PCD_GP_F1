import java.util.*;

/**
 * La clase Circuito representa un circuito de carreras donde los pilotos compiten.
 */
public class Circuito {
    private List<Piloto> listaPilotos = new LinkedList<Piloto>();
    private Integer numeroPilotos = 20;
    private Integer numeroVueltas = 75;
    private Integer capacidadPitLane = 5;
    private Piloto[] pitLane = new Piloto[5];
    private int contadorIdsPilotos = 0;
    private int numerosPilotosPitLane = 0;

    /**
     * Constructor de la clase Circuito.
     *
     * @param capacidadPitLane La capacidad del pit lane.
     * @param numeroVueltas    El número de vueltas en la carrera.
     * @param numeroPilotos    El número máximo de pilotos en el circuito.
     */
    public Circuito(Integer capacidadPitLane, Integer numeroVueltas, Integer numeroPilotos) {
        this.capacidadPitLane = capacidadPitLane;
        this.pitLane = new Piloto[capacidadPitLane];
        this.numeroPilotos = numeroPilotos;
        this.listaPilotos = new LinkedList<Piloto>();
        this.numerosPilotosPitLane = 0;
        this.contadorIdsPilotos = 1;
        this.numeroVueltas = numeroVueltas;
        Iterator it = listaPilotos.iterator();

        int countMax = 0;
        while (it.hasNext() && countMax < this.numeroPilotos) {
            this.listaPilotos.add((Piloto) it.next());
            countMax++;
        }
    }

    /**
     * Inicia la carrera en el circuito, permitiendo que los pilotos compitan.
     */
    public void recorrerCircuito() {
        Thread[] vector = new Thread[numeroPilotos];
        Iterator it = listaPilotos.iterator();
        int cont = 0;
        // Hacemos que los pilotos comiencen la carrera
        while (it.hasNext()) {
            Runnable runnable = (Piloto) it.next();
            vector[cont] = new Thread(runnable);
            vector[cont].start();
            cont++;
        }
        // Finalizamos los pilotos
        for (int i = 0; i < numeroPilotos; i++) {
            try {
                vector[i].join();
            } catch (Exception e) {
            }
        }
    }

    /**
     * Muestra la clasificación de la carrera una vez que ha terminado.
     */
    public void mostrarClasificacion() {
        System.out.println("-------------------------------------------------------------");
        System.out.println("---------------------- FIN DE LA CARRERA --------------------");
        System.out.println("----------------------- CLASIFICACIONES ---------------------");
        String[] medallas = {"\uD83E\uDD47", "\uD83E\uDD48", "\uD83E\uDD49"};
        Collections.sort(listaPilotos);
        Iterator it = listaPilotos.iterator();
        Integer count = 1;
        while (it.hasNext()) {
            Piloto piloto = (Piloto) it.next();
            long tiempoTotal = (piloto.getTimeEnd() + piloto.getTimePitLane());
            if (count <= 3) {
                System.out.println(medallas[count - 1] + "0" + count.toString() + "ª posición -> Piloto:  " + piloto.getNombre() + ", Time : " + piloto.getTimeEnd() + ", Tiempo en pit-lane"+ piloto.getTimePitLane() + ", Tiempo de parada total: "+ tiempoTotal);
            } else {
                if (count < 10) {
                    System.out.println("- 0" + count.toString() + "ª posición -> Piloto:  " + piloto.getNombre() + ", Time : " + piloto.getTimeEnd() + ", Tiempo en pit-lane"+ piloto.getTimePitLane() + ", Tiempo de parada total: "+ tiempoTotal);
                } else {
                    System.out.println("- " + count.toString() + "ª posición -> Piloto:  " + piloto.getNombre() + ", Time : " + piloto.getTimeEnd() + ", Tiempo en pit-lane"+ piloto.getTimePitLane() + ", Tiempo de parada total: "+ tiempoTotal);
                }
            }
            count++;
        }
    }

    /**
     * Añade un nuevo piloto al circuito con un nombre y una estrategia de paradas en el pit lane.
     *
     * @param nombre           El nombre del piloto.
     * @param strategiaParadas La estrategia de paradas en el pit lane del piloto.
     */
    public void anadirPiloto(String nombre, ParadaPitLane[] strategiaParadas) {
        Piloto pilot = new Piloto(strategiaParadas, nombre, this.contadorIdsPilotos, this.numeroVueltas, this.capacidadPitLane);
        contadorIdsPilotos++;
        listaPilotos.add(pilot);
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
     * Agrega un piloto a la lista de pilotos en el circuito.
     *
     * @param piloto El piloto a agregar.
     */
    public void addPiloto(Piloto piloto) {
        this.listaPilotos.add(piloto);
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
     * @param numeroVueltas El número de vueltas en la carrera a establecer.
     */
    public void setNumeroVueltas(Integer numeroVueltas) {
        this.numeroVueltas = numeroVueltas;
    }

    /**
     * Obtiene el contador de IDs de pilotos.
     *
     * @return El contador de IDs de pilotos.
     */
    public int getContadorIdsPilotos() {
        return contadorIdsPilotos;
    }

    /**
     * Establece el contador de IDs de pilotos.
     *
     * @param contadorIdsPilotos El contador de IDs de pilotos a establecer.
     */
    public void setContadorIdsPilotos(int contadorIdsPilotos) {
        this.contadorIdsPilotos = contadorIdsPilotos;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Circuito circuito)) return false;
        return getContadorIdsPilotos() == circuito.getContadorIdsPilotos() && getNumerosPilotosPitLane() == circuito.getNumerosPilotosPitLane() && Objects.equals(getListaPilotos(), circuito.getListaPilotos()) && Objects.equals(getNumeroPilotos(), circuito.getNumeroPilotos()) && Objects.equals(getNumeroVueltas(), circuito.getNumeroVueltas()) && Objects.equals(getCapacidadPitLane(), circuito.getCapacidadPitLane()) && Arrays.equals(getPitLane(), circuito.getPitLane());
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(getListaPilotos(), getNumeroPilotos(), getNumeroVueltas(), getCapacidadPitLane(), getContadorIdsPilotos(), getNumerosPilotosPitLane());
        result = 31 * result + Arrays.hashCode(getPitLane());
        return result;
    }

    @Override
    public String toString() {
        return "Circuito{" +
                "listaPilotos=" + listaPilotos +
                ", numeroPilotos=" + numeroPilotos +
                ", numeroVueltas=" + numeroVueltas +
                ", capacidadPitLane=" + capacidadPitLane +
                ", pitLane=" + Arrays.toString(pitLane) +
                ", contadorIdsPilotos=" + contadorIdsPilotos +
                ", numerosPilotosPitLane=" + numerosPilotosPitLane +
                '}';
    }
}
