//package mx.uam.pc.candado;

import java.util.concurrent.locks.ReentrantLock; 
import java.util.concurrent.Semaphore;

public class Principal{ 
	//Declaraciones
	static final int MAX_productores = 2;
	static final int MAX_consumidores = 1;
	public static void main(String[] args){ 

		//Cración de valores de sincronización
		ReentrantLock candado = new ReentrantLock();
		Semaphore SemaphoreProductor = new Semaphore(1); //Tamaño almacén
		Semaphore SemaphoreConsumidor = new Semaphore(0);
		
		//Inizialización de valores de sincronización
		int[] Dato = new int[1]; //Tamaño almacén
		Thread[] productores = new Thread[MAX_productores];
		Thread[] consumidores = new Thread[MAX_consumidores];

		
		//Creación de Productores y su ejecución
		for(int i=0;i<MAX_productores;i++) {
			productores[i] = new Thread(new productor(i, candado, Dato, SemaphoreProductor, SemaphoreConsumidor)); // productores0
			productores[i].start();
		}

		//Creación de Cosumidores y su ejecución
		for(int i=0;i<MAX_consumidores;i++) {
			consumidores[i]=  new Thread(new consumidor(i, candado, Dato, SemaphoreProductor, SemaphoreConsumidor)); // consumidores0
			consumidores[i].start();
		}


		//Wait para productores
		for(int i=0;i<MAX_productores;i++){

			try {
				productores[i].join();

			} catch (InterruptedException e) {

				System.out.println("Error: en la espera del hilo");
			}
		}

		//Wait para consumidores
		for(int i=0;i<MAX_consumidores;i++){

			try {
				consumidores[i].join();

			} catch (InterruptedException e) {

				System.out.println("Error: en la espera del hilo");
			}
		}

		//Fin programa
		System.out.println("Termina programa");
		
	} 
} 