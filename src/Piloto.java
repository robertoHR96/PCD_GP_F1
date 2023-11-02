import java.util.Arrays;
import java.util.Objects;
import java.util.Random;
import java.util.concurrent.Semaphore;

/**
 * La clase Piloto representa a un piloto de carreras en el circuito.
 */
public class Piloto implements Comparable, Runnable {

    private int id = 0; // Identificador único del piloto.
    private String nombre; // Nombre del piloto.
    private ParadaPitLane[] paradas; // Paradas en el pit lane que realizará el piloto.
    private Integer vueltaActual = 0; // Número de la vuelta actual.
    private long timeEnd = 0; // Tiempo total de finalización de la carrera en segundos.
    private long timePitLane = 0; // Tiempo total de finalización de la carrera en segundos.
    private Integer numeroVueltas = 75; // Número total de vueltas de la carrera.
    private static Integer[] pitLane = new Integer[5]; // Representa el pit lane.
    private static Integer numerosPilotosPitLane = 0; // Número de pilotos en el pit lane.
    private static Object object = new Object(); // Objeto de sincronización para el pit lane.
    private Semaphore semaforoCola = new Semaphore(1);
    private Semaphore semaforoCola2 = new Semaphore(1);
    private Semaphore mutex;

    /**
     * Constructor de la clase Piloto.
     *
     * @param paradas          Las paradas en el pit lane que realizará el piloto.
     * @param nombre           El nombre del piloto.
     * @param id               El identificador único del piloto.
     * @param numeroVueltas    El número total de vueltas de la carrera.
     * @param capacidadPitLane La capacidad del pit lane.
     */
    public Piloto(ParadaPitLane[] paradas, String nombre, int id, int numeroVueltas, int capacidadPitLane) {
        this.id = id;
        this.timeEnd = 0;
        this.numeroVueltas = numeroVueltas;
        this.nombre = nombre;
        this.vueltaActual = 0;
        this.paradas = new ParadaPitLane[capacidadPitLane];

        this.semaforoCola = new Semaphore(1);
        this.semaforoCola2 = new Semaphore(1);
        this.mutex = new Semaphore(1);
        numerosPilotosPitLane = 0;
        for (int i = 0; i < 5; i++) {
            this.paradas[i] = paradas[i];
        }
    }

    /**
     * Método que representa la lógica del piloto durante la carrera.
     */
    @Override
    public void run() {
        for (int i = 0; i < numeroVueltas; i++) {
            recorrerVuelta(i);
            comprobarSiParada(i);
        }
    }

    /**
     * Simula una vuelta del piloto en la carrera y actualiza el tiempo y la información de la vuelta.
     *
     * @param numVuelta El número de la vuelta actual.
     */
    public void recorrerVuelta(Integer numVuelta) {
        this.vueltaActual = numVuelta;
        //System.out.println(" ----------------------\n \uD83C\uDFCE\uFE0F " + this.nombre + "\n recorriendo la vuelta: " + numVuelta);
        long timeOld = this.timeEnd;
        anadirTiempoVuelta();
        long timeNew = this.timeEnd;
        //System.out.println(" ----------------------\n \uD83C\uDFCE\uFE0F " + this.nombre + "\n \uD83D\uDD53 terminó la vuelta: " + numVuelta + " en: " + (timeNew - timeOld) + "s\n tiempo total: " + timeEnd + "s ");

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
     * Comprueba si es necesario realizar una parada en el pit lane en la vuelta dada.
     *
     * @param numVuelta El número de la vuelta actual.
     */
    public void comprobarSiParada(Integer numVuelta) {
        long tiempoEsperaInicio;
        for (int i = 0; i < this.paradas.length; i++) {
            if (this.paradas[i].getVuelta() == numVuelta) {
                tiempoEsperaInicio = System.currentTimeMillis();
                try {
                    if (numerosPilotosPitLane == 5) {
                        System.out.println(" -----------------------\n \uD83D\uDED1 " + this.nombre + " esperando para entrar en el pit lane");
                        semaforoCola.acquire();
                    }
                    mutex.acquire();
                    anadirPilotoPitLane();
                    mutex.release();
                    System.out.println(" ----------------------\n \uD83D\uDD27 Piloto: " + this.nombre + " entró al Pit Lane\n Numero de pilotos en Pit Lane despues de la entrada: " + numerosPilotosPitLane);
                    try {
                        // Simula el tiempo de espera dentro del pitlane
                        Thread.sleep(300);
                    } catch (Exception e) {
                    }
                    semaforoCola2.acquire();
                    sacarPilotoPitLane();
                    semaforoCola2.release();
                    semaforoCola.release();

                    long tiempoEsperaFin = System.currentTimeMillis();
                    long tiempoDeParada = (tiempoEsperaFin - tiempoEsperaInicio);
                    // se modifica el tiempo de pitlane total
                    this.timePitLane = this.timePitLane + tiempoDeParada;
                    System.out.println("----------------------\n \uD83D\uDD27 Piloto: " + this.nombre + " salió del Pit Lane \n \uD83D\uDD53 Tiempo en el Pit Lane: " + tiempoDeParada + "\n Numero de pilotos en el Pit Lane después de la salida: " + numerosPilotosPitLane);
                    // Deja libre un hueco en el semaforo de cola
                    //object.notify();
                } catch (Exception e) {
                    System.out.println("Error 2");
                }
            }
        }
    }

    /**
     * Saca al piloto del pit lane.
     */
    public void sacarPilotoPitLane() {
        for (int i = 0; i < pitLane.length; i++) {
            if (this.pitLane[i] != null) {
                if (this.pitLane[i] == this.id) {
                    this.pitLane[i] = null;
                    numerosPilotosPitLane--;
                    break;
                }
            }
        }
    }

    /**
     * Ingresa al piloto en el pit lane.
     */
    public void anadirPilotoPitLane() {
        for (int i = 0; i < pitLane.length; i++) {
            if (pitLane[i] == null) {
                this.pitLane[i] = this.id;
                numerosPilotosPitLane++;
                break;
            }
        }
    }

    /**
     * Obtiene las paradas planificadas para el piloto.
     *
     * @return Un array de objetos ParadaPitLane que representa las paradas planificadas.
     */
    public ParadaPitLane[] getParadas() {
        return paradas;
    }

    /**
     * Establece las paradas planificadas para el piloto.
     *
     * @param paradas Un array de objetos ParadaPitLane que representa las paradas planificadas.
     */
    public void setParadas(ParadaPitLane[] paradas) {
        this.paradas = new ParadaPitLane[5];
        for (int i = 0; i < 5; i++) {
            this.paradas[i] = new ParadaPitLane(paradas[i].getVuelta());
        }
    }

    /**
     * Constructor de la clase Piloto con el nombre del piloto.
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
     * @param nombre El nombre del piloto a establecer.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene la vuelta actual en la que se encuentra el piloto.
     *
     * @return El número de la vuelta actual.
     */
    public Integer getVueltaActual() {
        return vueltaActual;
    }

    /**
     * Establece la vuelta actual en la que se encuentra el piloto.
     *
     * @param vueltaActual El número de la vuelta actual a establecer.
     */
    public void setVueltaActual(Integer vueltaActual) {
        this.vueltaActual = vueltaActual;
    }

    /**
     * Obtiene el tiempo total de finalización de la carrera por parte del piloto.
     *
     * @return El tiempo total de finalización de la carrera en segundos.
     */
    public long getTimeEnd() {
        return timeEnd;
    }

    /**
     * Establece el tiempo total de finalización de la carrera por parte del piloto.
     *
     * @param timeEnd El tiempo total de finalización de la carrera en segundos a establecer.
     */
    public void setTimeEnd(long timeEnd) {
        this.timeEnd = timeEnd;
    }

    public long getTimePitLane() {
        return timePitLane;
    }

    public void setTimePitLane(long timePitLane) {
        this.timePitLane = timePitLane;
    }

    /**
     * Obtiene el identificador único del piloto.
     *
     * @return El identificador único del piloto.
     */
    public int getId() {
        return id;
    }

    /**
     * Establece el identificador único del piloto.
     *
     * @param id El identificador único del piloto a establecer.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Obtiene el número total de vueltas de la carrera.
     *
     * @return El número total de vueltas de la carrera.
     */
    public Integer getNumeroVueltas() {
        return numeroVueltas;
    }

    /**
     * Establece el número total de vueltas de la carrera.
     *
     * @param numeroVueltas El número total de vueltas de la carrera a establecer.
     */
    public void setNumeroVueltas(Integer numeroVueltas) {
        this.numeroVueltas = numeroVueltas;
    }

    /**
     * Obtiene el estado actual del pit lane.
     *
     * @return Un array de enteros que representa el estado actual del pit lane.
     */
    public static Integer[] getPitLane() {
        return pitLane;
    }

    /**
     * Establece el estado actual del pit lane.
     *
     * @param pitLane Un array de enteros que representa el estado actual del pit lane.
     */
    public static void setPitLane(Integer[] pitLane) {
        Piloto.pitLane = pitLane;
    }

    /**
     * Obtiene el número de pilotos en el pit lane.
     *
     * @return El número de pilotos en el pit lane.
     */
    public static Integer getNumerosPilotosPitLane() {
        return numerosPilotosPitLane;
    }

    /**
     * Establece el número de pilotos en el pit lane.
     *
     * @param numerosPilotosPitLane El número de pilotos en el pit lane a establecer.
     */
    public static void setNumerosPilotosPitLane(Integer numerosPilotosPitLane) {
        Piloto.numerosPilotosPitLane = numerosPilotosPitLane;
    }

    /**
     * Obtiene el objeto de sincronización para el pit lane.
     *
     * @return El objeto de sincronización para el pit lane.
     */
    public static Object getObject() {
        return object;
    }

    /**
     * Establece el objeto de sincronización para el pit lane.
     *
     * @param object El objeto de sincronización para el pit lane a establecer.
     */
    public static void setObject(Object object) {
        Piloto.object = object;
    }

    /**
     * Método de comparación utilizado para ordenar a los pilotos en función de su tiempo de finalización.
     *
     * @param o El objeto Piloto con el que se compara.
     * @return Un valor negativo si este piloto tiene un tiempo de finalización menor, un valor positivo si tiene un tiempo de finalización mayor, o cero si los tiempos son iguales.
     */
    @Override
    public int compareTo(Object o) {
        Piloto otroPiloto = (Piloto) o;
        return Long.compare(this.timeEnd, otroPiloto.timeEnd);
    }
}
