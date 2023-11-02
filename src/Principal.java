
public class Principal {

	public static void main(String[] args) {
		//int pueRandom = (int) (Math.random() * 4) + 1;
		//int vehRandom = (int) (Math.random() * 30) + 20;
		
		int pueRandom = 2;
		int vehRandom = 20;
		
		ITV itv = new ITV();
		
		System.out.println(vehRandom + " Vehículos serán atendidos por " + pueRandom + " puestos.");
		
		// Creación de vehículos
		Vehiculo[] v = new Vehiculo[vehRandom];
		for (int i = 0; i < vehRandom; i++) {
			v[i] = new Vehiculo(i + 1, itv);
			v[i].start();
		}
		
		// Creación de puestos
		Puesto[] p = new Puesto[pueRandom];
		for (int i = 0; i < pueRandom; i++) {
			p[i] = new Puesto(i + 1, itv);
			p[i].start();
		}

		// Se espera a que terminen todos los puestos
		for (int i = 0; i < pueRandom; i++) {
			try {
				p[i].join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		// Se espera a que terminen todos vehículos
		for (int i = 0; i < vehRandom; i++) {
			try {
				v[i].join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		// Se cierra la itv
		System.out.println("Se cierra la ITV con un tiempo acumulado de " + itv.getTiempoTotal());
	}
}