
class Vehiculo extends Thread {
	private int identif;
	private ITV itv;

	public Vehiculo(int identif, ITV itv) {
		this.identif = identif;
		this.itv = itv;
	}

	public void run() {
		itv.nuevoCoche(identif);
	}
}