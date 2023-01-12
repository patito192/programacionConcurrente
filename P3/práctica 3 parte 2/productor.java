//package mx.uam.pc.candado;

import java.util.concurrent.locks.ReentrantLock; 
import java.util.concurrent.Semaphore;
import java.util.Random;

//import producto;

class productor implements Runnable{ 

	//Declaraciones
	private int id;
	private int[] almacen;
	private ReentrantLock candadoProductor;
	private Semaphore SemaphoreProductor;
	private Semaphore SemaphoreConsumidor;

	public productor(int id, ReentrantLock candadoProductor, int[] almacen, Semaphore SemaphoreProductor, Semaphore SemaphoreConsumidor) { 
		//Asignación a valores de argumentos
		this.id = id;
		this.almacen = almacen;
		this.candadoProductor = candadoProductor;
		this.SemaphoreProductor = SemaphoreProductor;
		this.SemaphoreConsumidor = SemaphoreConsumidor;
	} 
	public void run(){ 
		//Random
		Random r = new Random();

		for (int i=0; i<31; i++) {

			//Evitar un error para el semáforo
			try {
					SemaphoreProductor.acquire();//Wait
				} 
				catch (InterruptedException exception) {
					exception.printStackTrace();
			}


			//EMPIEZA SECCIÓN 
			candadoProductor.lock();


			
			//Se crea el almancen
			System.out.println("Entrada:" + almacen[producto.entrada]);
			almacen[producto.entrada] = r.nextInt(100);
			System.out.println("Soy el productor " + id + " y creo: " + almacen[producto.entrada]);

			producto.entrada = (producto.entrada + 1) % almacen.length;

			candadoProductor.unlock();
			//TERMINA SECCIÓN 

			//Aviso al consumidor que ya puede consumir (Liberar a consumidor)
			SemaphoreConsumidor.release();
		}
	}
} 
