//package mx.uam.pc.candado;

import java.util.concurrent.locks.ReentrantLock; 
import java.util.concurrent.Semaphore;
import java.util.Random;

class barbero implements Runnable{ 
	//Declaraciones
	/*CANDADOS*/
	/* (Q)
	private ReentrantLock candadoDespertando;
	private ReentrantLock candadoRasurando;*/

	//Semeforos (A)
	Semaphore SemaphoreDespertando;
	Semaphore SemaphoreRasurando;

	//Constructor (Q)
	/*
	public barbero(ReentrantLock candadoDespertando, ReentrantLock candadoRasurando) { 
		this.candadoDespertando = candadoDespertando;
		this.candadoRasurando = candadoRasurando;
	}*/

	//Constructor
	public barbero(Semaphore SemaphoreDespertando, Semaphore SemaphoreRasurando) { 
		this.SemaphoreDespertando = SemaphoreDespertando;
		this.SemaphoreRasurando = SemaphoreRasurando;
	}

	
	public void run(){ 

		System.out.println("Soy el barbero :p");


		try{
			SemaphoreDespertando.acquire();
		}catch (InterruptedException e) {

			System.out.println("Error: en la espera del hilo");
		}

		
		//candadoDespertando.lock(); (Q)
		

		//seccion critica
		System.out.println("Cortando");

		//candadoRasurando.unlock(); (Q)
		SemaphoreRasurando.release();
	



	for (int i = 0; i < 2; i++) {
		
	}


	 
	}
} 
