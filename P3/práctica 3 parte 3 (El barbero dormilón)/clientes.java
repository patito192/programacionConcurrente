//package mx.uam.pc.candado;

import java.util.concurrent.locks.ReentrantLock; 
import java.util.concurrent.Semaphore;

class clientes implements Runnable{ 

	//Declaraciones
	private int id;
	private int[] sillas_clientes;
	private ReentrantLock candadoClientes = new ReentrantLock();
	private Semaphore SemaphoreBarbero;
	private Semaphore SemaphoreClientes;

	public clientes(int id, eentrantLock candadoClientes, int[] sillas_clientes, Semaphore SemaphoreProductor, Semaphore SemaphoreConsumidor) { 
		//Asignación a valores de argumentos
		this.id = id;
		this.candadoClientes = candadoClientes;
		this.sillas_clientes = sillas_clientes;
		this.SemaphoreConsumidor = SemaphoreConsumidor;
		this.SemaphoreProductor = SemaphoreProductor;
	} 

	public void run(){
		for (int i=0; i<10; i++) {

			//Evitar un error para el semáforo
			try {
					SemaphoreConsumidor.acquire();//Wait
				} 
				catch (InterruptedException exception) {
					exception.printStackTrace();
			}


			while () {

			//Wait para clientes
			for(int i=0;i<MAX_clientes;i++){

				try {
					clientes[i].join();

				} catch (InterruptedException e) {

					System.out.println("Error: en la espera del hilo");
				}
			}




			//EMPIEZA SECCIÓN CRÍTICA
			despertando.lock();

			//Se crean las sillas_clientes
			System.out.println("Soy el barbero " + id + " y las sillas: " + candadoSilla_barbero[producto.salida] + '\n');
			
			candadoSilla_barbero[producto.salida] = -1;
			//Pendiente
			producto.salida = (producto.salida + 1) % 1;

			//Aviso a cliente que ya puede pasar (Liberar silla)
			SemaphoreSillas_clientes.release();

			//TERMINA SECCIÓN CRÍTICA
			rasurando.unlock();
			







		}

	        
	        //EMPIEZA SECCIÓN CRÍTICA
			candadoClientes.lock();

			//Se crean las sillas_clientes
			System.out.println("Soy el consumidor " + id + " y consumo: " + sillas_clientes[producto.salida] + '\n');
			
			sillas_clientes[producto.salida] = -1;
			producto.salida = (producto.salida + 1) % 1;

			//TERMINA SECCIÓN CRÍTICA
			candadoClientes.unlock();


			//Aviso al productor que ya puede producir (Liberar a productor)
			SemaphoreProductor.release();
		}
	} 
} 
