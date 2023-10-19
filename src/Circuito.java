import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Collections;

public class Circuito {
    private List<Piloto> listaPilotos = new LinkedList<Piloto>();
    private Integer numeroPilotos = 20;
    private Integer numeroVueltas = 75;
    private Integer capacidadPitLane = 5;
    private Piloto[] pitLane = new Piloto[5];
    private int contadorIdsPilotos =0;

    private int numerosPilotosPitLane = 0;

    /************************* Constructores *************************/
    public Circuito( Integer capacidadPitLane, Integer numeroVueltas, Integer numeroPilotos) {
        this.capacidadPitLane = capacidadPitLane;
        this.pitLane = new Piloto[capacidadPitLane];
        this.numeroPilotos = numeroPilotos;
        this.listaPilotos = new LinkedList<Piloto>();
        this.numerosPilotosPitLane = 0;
        this.contadorIdsPilotos=1;
        this.numeroVueltas=numeroVueltas;
        Iterator it = listaPilotos.iterator();

        int countMax = 0;
        while (it.hasNext() && countMax < this.numeroPilotos) {
            this.listaPilotos.add((Piloto) it.next());
            countMax++;
        }
    }


    /************************* Métodos propios*************************/

    public void recorrerCircuito() {
        Thread[] vector = new Thread[numeroPilotos];
        Iterator it = listaPilotos.iterator();
        int cont = 0;
        // hacemos que los pilotos comienzen la carrera
        while (it.hasNext()) {
            Runnable runnable = (Piloto) it.next();
            vector[cont] = new Thread(runnable);
            vector[cont].start();
            cont++;
        }
        // Finalizamos los pilotos
        for(int i =0; i<numeroPilotos; i++){
            try{
            vector[i].join();
            }catch (Exception e){

            }
        }
    }

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
            if (count <= 3) {
                System.out.println(medallas[count - 1]+"0"+ count.toString() + "ª posición -> Piloto:  " + piloto.getNombre() + ", Time : " + piloto.getTimeEnd());
            } else {
                if(count<10){
                    System.out.println("- 0"+count.toString() + "ª posición -> Piloto:  " + piloto.getNombre() + ", Time : " + piloto.getTimeEnd());
                }else{
                    System.out.println("- "+count.toString() + "ª posición -> Piloto:  " + piloto.getNombre() + ", Time : " + piloto.getTimeEnd());
                }
            }
            count++;
        }
    }

    /************************* Getter and Setter *************************/
    public void anadirPiloto(String nombre, ParadaPitLane[] strategiaParadas){
        Piloto pilot = new Piloto(strategiaParadas ,nombre, this.contadorIdsPilotos, this.numeroVueltas,this.capacidadPitLane);
        contadorIdsPilotos++;
        listaPilotos.add(pilot);
    }
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
