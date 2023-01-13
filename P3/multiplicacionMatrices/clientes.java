//package mx.uam.pc.candado;

import java.util.concurrent.locks.ReentrantLock; 
import java.util.concurrent.Semaphore;

class clientes implements Runnable{ 

	//Declaraciones
	/*CANDADOS*/
	/*
	private ReentrantLock candadoSilla_barbero;
	private ReentrantLock candadoDespertando;
	private ReentrantLock candadoRasurando;*/
	private int id;

	//Semeforos (A)
	Semaphore SemaphoreSilla_barbero;
	Semaphore SemaphoreDespertando;
	Semaphore SemaphoreRasurando;
	//Semáforos
	Semaphore SemaphoreSillas_clientes; //Tamaño de sillas


					//Semaphore SemaphoreSillas_clientes, ReentrantLock candadoSilla_barbero, ReentrantLock candadoRasurando, ReentrantLock candadoDespertando
	public clientes(int id, Semaphore SemaphoreSillas_clientes, Semaphore SemaphoreSilla_barbero, Semaphore SemaphoreRasurando, Semaphore SemaphoreDespertando) { 
		this.id = id;
		//Asignación a valores de argumentos (Constructor)

		//Asignacion candados (Q)
		/*
		this.candadoSilla_barbero = candadoSilla_barbero;
		this.candadoRasurando = candadoRasurando;
		this.candadoDespertando = candadoDespertando;*/

		//Asignacion semaforos (A)
		this.SemaphoreSilla_barbero = SemaphoreSilla_barbero;
		this.SemaphoreRasurando = SemaphoreRasurando;
		this.SemaphoreDespertando = SemaphoreDespertando;

		//Asignacion semaforos
		this.SemaphoreSillas_clientes = SemaphoreSillas_clientes;
	}

	public void run(){

		System.out.println("soy el cliente "+id+" y voy llegando");


		for (int i = 0; i < 1; i++) {
			//Create WAIT
			System.out.println("soy el cliente " + id + " y me siento");
			try {
				SemaphoreSillas_clientes.acquire();

			} catch (InterruptedException e) {

				System.out.println("Error: en la espera del hilo");
			}

			System.out.println("soy el cliente "+id+ " y me espero al barbero a que me atienda");


			try {
				SemaphoreSilla_barbero.acquire();

			} catch (InterruptedException e) {

				System.out.println("Error: en la espera del hilo");
			}


			System.out.println("soy el cliente " + id + " y me paro de la silla");
			//Aviso al consumidor que ya puede consumir (Liberar a consumidor)
			SemaphoreSillas_clientes.release();


			//candadoDespertando.unlock(); (Q)
			SemaphoreDespertando.release();



			try {
				SemaphoreRasurando.acquire();

			} catch (InterruptedException e) {

				System.out.println("Error: en la espera del hilo");
			}


			//candadoSilla_barbero.unlock(); (Q)
			SemaphoreSilla_barbero.release();



			//candadoSilla_barbero.lock(); (Q)
			

			//Aviso al consumidor que ya puede consumir (Liberar a consumidor)
				//SemaphoreSillas_clientes.release();

			

			//candadoRasurando.lock(); (Q)
			

			
		}



		System.out.println("Soy el cliente " +id +" y ya me voy :p");
	} 
} 
