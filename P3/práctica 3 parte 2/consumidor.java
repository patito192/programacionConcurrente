//package mx.uam.pc.candado;

import java.util.concurrent.locks.ReentrantLock; 
import java.util.concurrent.Semaphore;

//import producto;

class consumidor implements Runnable{ 

	//Declaraciones
	private int id;
	private int[] almacen;
	private ReentrantLock candadoConsumidor = new ReentrantLock();
	private Semaphore SemaphoreProductor;
	private Semaphore SemaphoreConsumidor;

	public consumidor(int id, ReentrantLock candadoConsumidor, int[] almacen, Semaphore SemaphoreProductor, Semaphore SemaphoreConsumidor) { 
		//Asignación a valores de argumentos
		this.id = id;
		this.candadoConsumidor = candadoConsumidor;
		this.almacen = almacen;
		this.SemaphoreConsumidor = SemaphoreConsumidor;
		this.SemaphoreProductor = SemaphoreProductor;
	} 

	public void run(){
		for (int i=0; i<50; i++) {

			//Evitar un error para el semáforo
			try {
					SemaphoreConsumidor.acquire();//Wait
				} 
				catch (InterruptedException exception) {
					exception.printStackTrace();
			}

	        
	        //EMPIEZA SECCIÓN 
			candadoConsumidor.lock();

			//Se crea el almacen
			System.out.println("Soy el consumidor " + id + " y consumo: " + almacen[producto.salida] + '\n');
			
			almacen[producto.salida] = -1;
			producto.salida = (producto.salida + 1) % almacen.length;

			//TERMINA SECCIÓN 
			candadoConsumidor.unlock();


			//Aviso al productor que ya puede producir (Liberar a productor)
			SemaphoreProductor.release();
		}
	} 
} 
