import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Collections;

public class Circuito {
    private List<Piloto> listaPilotos = new LinkedList<Piloto>();
    private Integer numeroVueltas = 75;
    private Integer numeroPilotos = 20;

    private Integer capacidadPitLane = 5;
    private Piloto [] pitLane = new Piloto[5];

    private int numerosPilotosPitLane =0;

    /************************* Constructores *************************/
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


    /************************* Métodos propios*************************/

    public void run() {
        Iterator it = this.listaPilotos.iterator();
        while (it.hasNext()) {
            Piloto pilotoCorriendo = (Piloto) it.next();
            recorrerCircuito(pilotoCorriendo);
        }
        mostrarClasificacion();
    }


    public void recorrerCircuito(Piloto piloto) {
        for (int i = 0; i < numeroVueltas; i++) {
            piloto.recorrerVuelta(i);
            comprobarSiParada(i, piloto);
        }
    }

    public void comprobarSiParada(Integer numVuelta, Piloto piloto) {
        for (int i = 0; i < piloto.getParadas().length; i++) {
            if (piloto.getParadas()[i].getVuelta() == numVuelta) {
                System.out.println("\uD83D\uDED1 Piloto: " + piloto.getNombre() + " entrando en PitLane");
                piloto.setTimeEnd(piloto.getTimeEnd() + piloto.getParadas()[i].getTimeParada());
                anadirPilotoPitLane(piloto);
                System.out.println("\uD83D\uDD27 Piloto: " + piloto.getNombre()+ " salio del PitLane - Time PitLane: " + piloto.getParadas()[i].getTimeParada());
                sacarPilotoPitLane(piloto);
            }
        }
    }
    public void sacarPilotoPitLane(Piloto piloto) {
        for (int i = 0; i < pitLane.length; i++) {
            if ((this.pitLane[i] != null) && (this.pitLane[i].getNombre() == piloto.getNombre())) {
                this.pitLane[i] = null;
            }
        }
    }

    public void anadirPilotoPitLane(Piloto piloto) {
        this.pitLane[numerosPilotosPitLane] = piloto;
    }

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

    /************************* Getter and Setter **************************/

    public List<Piloto> getListaPilotos() {
        return listaPilotos;
    }

    public void setListaPilotos(List<Piloto> listaPilotos) {
        this.listaPilotos = new LinkedList<Piloto>();
        Iterator it = listaPilotos.iterator();
        int countMax = 0;
        while (it.hasNext() && countMax < this.numeroPilotos) {
            this.listaPilotos.add((Piloto) it.next());
            countMax++;
        }
    }

    public void addPiloto(Piloto piloto) {
        this.listaPilotos.add(piloto);
    }

    public Integer getNumeroVueltas() {
        return numeroVueltas;
    }

    public void setNumeroVueltas(Integer numeroVueltas) {
        this.numeroVueltas = numeroVueltas;
    }

    public Integer getNumeroPilotos() {
        return numeroPilotos;
    }

    public void setNumeroPilotos(Integer numeroPilotos) {
        this.numeroPilotos = numeroPilotos;
    }

    public Integer getCapacidadPitLane() {
        return capacidadPitLane;
    }

    public void setCapacidadPitLane(Integer capacidadPitLane) {
        this.capacidadPitLane = capacidadPitLane;
    }

    public Piloto[] getPitLane() {
        return pitLane;
    }

    public void setPitLane(Piloto[] pitLane) {
        this.pitLane = pitLane;
    }
}
