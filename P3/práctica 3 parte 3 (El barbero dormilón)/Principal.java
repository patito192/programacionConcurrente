//package mx.uam.pc.candado;

import java.util.concurrent.locks.ReentrantLock; 
import java.util.concurrent.Semaphore;


public class Principal{ 
	//Declaraciones
	static final int MAX_BARBERO = 1;
	static final int MAX_CLIENTES = 2;
	static final int SILLAS_CLIENTES = 1;

	//(A)
	static final int SILLA_BARBERO = 1;
	static final int DESPERTANDO = 0;
	static final int RASURANDO = 0;

	

	public static void main(String[] args){ 

		//INICIO DE LA SECCION CRITICA
		//Candados(Q)
		/*
		ReentrantLock candadoSilla_barbero = new ReentrantLock(true);
		ReentrantLock candadoDespertando = new ReentrantLock(false);
		ReentrantLock candadoRasurando = new ReentrantLock(false);*/

		//Semeforos (A)
		Semaphore SemaphoreSilla_barbero = new Semaphore(SILLA_BARBERO);
		Semaphore SemaphoreDespertando = new Semaphore(DESPERTANDO);
		Semaphore SemaphoreRasurando = new Semaphore(RASURANDO);

		Semaphore SemaphoreSillas_clientes = new Semaphore(SILLAS_CLIENTES);



		//Thread hiloBarbero = new Thread(new barbero(candadoDespertando, candadoRasurando));
		Thread hiloBarbero = new Thread(new barbero(SemaphoreDespertando, SemaphoreRasurando));
		Thread[] hilosClientes = new Thread[MAX_CLIENTES];


		//Creación de clientes y su ejecución
		for(int i=0;i<MAX_CLIENTES;i++) {
			//hilosClientes[i] = new Thread(new clientes(SemaphoreSillas_clientes, candadoSilla_barbero, candadoRasurando, candadoDespertando)); // hilosClientes0
			hilosClientes[i] = new Thread(new clientes(SemaphoreSillas_clientes, SemaphoreSilla_barbero, SemaphoreRasurando, SemaphoreDespertando)); // hilosClientes0
			hilosClientes[i].start();
		}


		hiloBarbero.start();


	} 
}