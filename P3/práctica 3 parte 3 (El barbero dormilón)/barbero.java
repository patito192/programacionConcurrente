//package mx.uam.pc.candado;

import java.util.concurrent.locks.ReentrantLock; 
import java.util.concurrent.Semaphore;
import java.util.Random;

class barbero implements Runnable{ 

	//Declaraciones
	private int id;
	private int[] almacen;
	private ReentrantLock candadobarbero;
	private Semaphore Semaphorebarbero;
	private Semaphore SemaphoreConsumidor;

	public barbero(int id, ReentrantLock candadobarbero, int[] almacen, Semaphore Semaphorebarbero, Semaphore SemaphoreConsumidor) { 
		//Asignación a valores de argumentos
		this.id = id;
		this.candadobarbero = candadobarbero;
		this.Dato = Dato;
		this.Semaphorebarbero = Semaphorebarbero;
		this.SemaphoreConsumidor = SemaphoreConsumidor;
	} 
	public void run(){ 
		//Random
		Random r = new Random();

		for (int i=0; i<10; i++) {

			//Evitar un error para el semáforo
			try {
					Semaphorebarbero.acquire();//Wait
				} 
				catch (InterruptedException exception) {
					exception.printStackTrace();
			}


			//EMPIEZA SECCIÓN CRÍTICA
			candadobarbero.lock();


			
			//Se crea el almancen
			almacen[producto.entrada;] = r.nextInt(100);
			System.out.println("Soy el barbero " + id + " y creo: " + almacen[0]);

			producto.entrada = (producto.entrada + 1)%1

			candadobarbero.unlock();
			//TERMINA SECCIÓN CRÍTICA

			//Aviso al consumidor que ya puede consumir (Liberar a consumidor)
			SemaphoreConsumidor.release();
		}
	}
} 
