
class Puesto extends Thread {
	private int identif;
	private ITV itv;
	private Integer tiempoPuesto;

	public Puesto(int identif, ITV itv) {
		this.identif = identif;
		this.itv = itv;
		this.tiempoPuesto=0;
	}

	public void run() {
		int retardo;
		int numeroCoche;
		while (itv.isCochesPendientes()) {
			try {
				retardo = (int) (Math.random() * 90 + 10);
				tiempoPuesto +=retardo;
				numeroCoche=itv.terminarCoche(retardo);
				sleep(retardo);
				System.out.println("El puesto " + identif + " ha revisado el coche " + numeroCoche + " en un tiempo de " + retardo);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("Fin del puesto " + identif + ", que termina con un tiempo parcial de " + tiempoPuesto);
	}
}
