//package mx.uam.pc.candado;

import java.util.concurrent.locks.ReentrantLock; 
import java.util.concurrent.Semaphore;

class consumidor implements Runnable{ 

	//Declaraciones
	private int[] Dato;
	private ReentrantLock candado; 
	private Semaphore SemaphoreProductor;
	private Semaphore SemaphoreConsumidor;

	public consumidor(ReentrantLock candado, int[] Dato, Semaphore SemaphoreProductor, Semaphore SemaphoreConsumidor) 
	{ 
		//Asignación a valores de argumentos
		this.candado = candado; 
		this.Dato = Dato;
		this.SemaphoreConsumidor = SemaphoreConsumidor;
		this.SemaphoreProductor = SemaphoreProductor;
	} 
	public void run(){
		for (int i=0; i<5; i++) {

			//Evitar un error para el semáforo
			try {
					SemaphoreConsumidor.acquire();//Wait
				} 
				catch (InterruptedException exception) {
					exception.printStackTrace();
			}

			//Aquí un candado proteginedo dato

			//Se crea el Dato
			System.out.println("soy el consumidor y el dato: "+Dato[0]);
			Dato[0] = -1;

			//Aviso al productor que ya puede producir (Liberar a productor)
			SemaphoreProductor.release();
		}
	} 
} 
