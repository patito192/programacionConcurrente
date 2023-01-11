//package mx.uam.pc.candado;

import java.util.concurrent.locks.ReentrantLock; 
import java.util.concurrent.Semaphore;
import java.util.Random;

class productor implements Runnable{ 

	//Declaraciones
	private int[] Dato;
	private ReentrantLock candado; 
	private Semaphore SemaphoreProductor;
	private Semaphore SemaphoreConsumidor;

	public productor(ReentrantLock candado, int[] Dato, Semaphore SemaphoreProductor, Semaphore SemaphoreConsumidor) 
	{ 
		//Asignación a valores de argumentos
		this.candado = candado; 
		this.Dato = Dato;
		this.SemaphoreProductor = SemaphoreProductor;
		this.SemaphoreConsumidor = SemaphoreConsumidor;
	} 
	public void run(){ 
		//Random
		Random r = new Random();

		for (int i=0; i<5; i++) {

			//Evitar un error para el semáforo
			try {
					SemaphoreProductor.acquire();//Wait
				} 
				catch (InterruptedException exception) {
					exception.printStackTrace();
			}

			//Se crea el Dato
			Dato[0] = r.nextInt(100);
			System.out.println("\nsoy el productor y el dato: "+Dato[0]);

			//Aviso al consumidor que ya puede consumir (Liberar a consumidor)
			SemaphoreConsumidor.release();
		}
	}
} 
