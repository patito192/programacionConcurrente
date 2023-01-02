package N_Reinas;

import java.util.concurrent.locks.ReentrantLock;

public class Rodrigo implements Runnable {
	static int N = 0;
	static int soluciones;
	private int[] tablero = null;

	private ReentrantLock candado;

	Rodrigo(int columna, ReentrantLock candado) {
		tablero = new int[N];
		tablero[0] = columna;
		this.candado = candado;

	}

	@Override
	public void run() {

		candado.lock();
		
		// Inicio Seccion Critica
		soluciones += backtrackReinas(1);
		// Fin Seccion Critica
		
		candado.unlock();
	}

	/** Inicio de Programa **/
	public static void main(String[] args) {

		int i;
		Thread[] trabajadores = null;
		ReentrantLock candado = new ReentrantLock();

		try {
			N = Integer.parseInt(args[0]);
		} catch (NumberFormatException e) {

			System.out.println("Error: No es posible convertir a entero");
			System.exit(0);
		}

		trabajadores = new Thread[N];

		for (i = 0; i < N; i++) {

			trabajadores[i] = new Thread(new ReinasHilos(i, candado));
			trabajadores[i].start();
		}

		for (i = 0; i < N; i++) {
			try {
				trabajadores[i].join();

			} catch (InterruptedException e) {

				System.out.println("Error: en la espera del hilo");
			}
		}
		System.out.println("Tablero " + N + "x" + N + " : " + soluciones);
	}

	/** Otros Metodos**/
	
	boolean esComida(int reng, int col) {
		int i, j;
		boolean comida = true;
		i = reng - 1;
		while ((i >= 0) && (tablero[i] != col))
			i--;
		if (i < 0) {
			i = reng - 1;
			j = col - 1;
			while ((i >= 0) && (j >= 0) && tablero[i] != j) {
				i--;
				j--;
			}
			if ((i < 0) || (j < 0)) {
				i = reng - 1;
				j = col + 1;
				while ((i >= 0) && (j <= tablero.length) && (tablero[i] != j)) {
					i--;
					j++;
				}
				if ((i < 0) || (j > tablero.length))
					comida = false;
			}
		}
		return (comida);
	}

	int backtrackReinas(int r) {
		int c;
		int soluciones = 0;
		if (r == tablero.length)
			return 1;
		for (c = 0; c < tablero.length; c++) {

			if (!esComida(r, c)) {

				tablero[r] = c;
				soluciones += backtrackReinas(r + 1);
				tablero[r] = 0;
			}
		}
		return soluciones;
	}
}