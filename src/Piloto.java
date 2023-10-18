import java.util.Random;
import java.util.SplittableRandom;

public class Piloto implements Comparable, Runnable {

    private int id = 0;
    private String nombre;

    private ParadaPitLane[] paradas;

    private Integer vueltaActual = 0;

    private long timeEnd = 0;

    private Integer numeroVueltas = 75;

    private static Integer[] pitLane = new Integer[5];
    private static Integer numerosPilotosPitLane = 0;

    private static Object object = new Object();

    /************************* Constructores *************************/
    public Piloto(ParadaPitLane[] paradas, String nombre, int id, int numeroVueltas) {
        this.id = id;
        this.timeEnd = 0;
        this.numeroVueltas = numeroVueltas;
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
        for (int i = 0; i < numeroVueltas; i++) {
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

    public void comprobarSiParada(Integer numVuelta) {
        for (int i = 0; i < this.paradas.length; i++) {
            if (this.paradas[i].getVuelta() == numVuelta) {
                this.timeEnd = this.timeEnd + this.paradas[i].getTimeParada();

                synchronized (object) {
                    while (numerosPilotosPitLane == 3) {
                        try {
                            System.out.println(" -----------------------");
                            System.out.println(this.nombre + " esparando para entrar al pitlane: " + numerosPilotosPitLane);
                            System.out.println(" -----------------------");
                            object.wait();
                        } catch (Exception e) {
                        }
                    }
                    System.out.println("\uD83D\uDD27 Piloto: " + this.nombre + " entro al PitLane - Time PitLane: " + this.paradas[i].getTimeParada());
                    anadirPilotoPitLane();
                    try {
                        Thread.sleep(1);
                    } catch (Exception e) {
                    }
                    System.out.println("\uD83D\uDD27 Piloto: " + this.nombre + " salio del PitLane - Time PitLane: " + this.paradas[i].getTimeParada());
                    sacarPilotoPitLane();
                    object.notify();
                }

            }
        }
    }

    public void sacarPilotoPitLane() {
        numerosPilotosPitLane--;
        for (int i = 0; i < pitLane.length; i++) {
            if (this.pitLane[i] != null) {
                if (this.pitLane[i] == this.id) {
                    //this.pitLane[i] = null;
                }
            }
        }

    }

    public void anadirPilotoPitLane() {
        System.out.println(this.id + " Numero pilotos pitLane antes : " + numerosPilotosPitLane);
        //this.pitLane[numerosPilotosPitLane] = this.id;
        numerosPilotosPitLane++;
        System.out.println(this.id + " Numero pilotos pitLane despues : " + numerosPilotosPitLane);
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
