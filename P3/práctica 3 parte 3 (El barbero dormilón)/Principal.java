//package mx.uam.pc.candado;

import java.util.concurrent.locks.ReentrantLock; 
import java.util.concurrent.Semaphore;

public class Principal{ 
	//Declaraciones
	static final int MAX_barbero = 1;
	static final int MAX_clientes = 1;

	static final int silla_barbero = 1;
	static final int sillas_clientes = N;

	static final int despertando = 0;
	static final int rasurando = 0;

	public static void main(String[] args){ 

		//for (barbero)
		for (int i; i<silla_barbero; i++) {

			//Wait para clientes
			for(int i=0;i<MAX_clientes;i++){

				try {
					clientes[i].join();

				} catch (InterruptedException e) {

					System.out.println("Error: en la espera del hilo");
				}
			}




			//EMPIEZA SECCIÓN CRÍTICA
			candadoSilla_barbero.lock();

			//Se crean las sillas_clientes
			System.out.println("Soy el barbero " + id + " y las sillas: " + candadoSilla_barbero[producto.salida] + '\n');
			
			candadoSilla_barbero[producto.salida] = -1;
			//Pendiente
			producto.salida = (producto.salida + 1) % 1;

			//Aviso a cliente que ya puede pasar (Liberar silla)
			SemaphoreSillas_clientes.release();

			//TERMINA SECCIÓN CRÍTICA
			despertando.unlock();




			//EMPIEZA SECCIÓN CRÍTICA
			rasurando.lock();


			//TERMINA SECCIÓN CRÍTICA
			silla_barbero.unlock();
			







		}



			//Cración de valores de sincronización

		//Candados
		ReentrantLock candadoSilla_barbero = new ReentrantLock();
		ReentrantLock candadoSillas_clientes = new ReentrantLock();
		//Semáforos
		Semaphore SemaphoreSilla_barbero = new Semaphore(1); //Tamaño de la silla
		Semaphore SemaphoreSillas_clientes = new Semaphore(0);
		//Variable almacén para productos
		int[] almacen = new	int[1];

		producto p = new producto();
		
		//Inizialización de v1alores de sincronización

		Thread[] productores = new Thread[MAX_productores];
		Thread[] clientes = new Thread[MAX_clientes];

		
		//Creación de Productores y su ejecución
		for(int i=0;i<MAX_productores;i++) {
			productores[i] = new Thread(new productor(candadoProductor, almacen, SemaphoreProductor, SemaphoreSillas_clientes)); // productores0
			productores[i].start();
		}

		//Creación de Cosumidores y su ejecución
		for(int i=0;i<MAX_clientes;i++) {
			clientes[i]=  new Thread(new consumidor(candadoSillas_clientes, almacen, SemaphoreProductor, SemaphoreSillas_clientes)); // clientes0
			clientes[i].start();
		}


		//Wait para productores
		for(int i=0;i<MAX_productores;i++){

			try {
				productores[i].join();

			} catch (InterruptedException e) {

				System.out.println("Error: en la espera del hilo");
			}
		}

		

		//Fin programa
		System.out.println("Termina programa");
		
	} 
}