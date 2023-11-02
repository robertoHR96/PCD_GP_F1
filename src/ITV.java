
import java.util.PriorityQueue;
import java.util.concurrent.Semaphore;

class ITV {
	private Semaphore semaforo;
	private PriorityQueue <Integer> listaCoches;
	private Integer tiempoTotal;

	public ITV() {
		semaforo = new Semaphore(1);
		listaCoches = new PriorityQueue <Integer>();
		tiempoTotal = 0;
	}

	public void nuevoCoche(Integer numeroCoche) {
		try {
			semaforo.acquire();
			listaCoches.add(numeroCoche);
			semaforo.release();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public int terminarCoche(Integer tiempoParcial) {
		int coche=0;
		try {
			if (isCochesPendientes()) {
				semaforo.acquire();
				coche = listaCoches.poll();
				tiempoTotal += tiempoParcial;
				semaforo.release();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return coche;
	}

	public boolean isCochesPendientes() {
		return listaCoches.size() > 0;
	}
	
	public Integer getTiempoTotal () {
		return tiempoTotal;
	}
	
}