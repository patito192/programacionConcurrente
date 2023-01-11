package mx.uam.pc.atomic;

import java.util.concurrent.atomic.AtomicInteger;

class SumaConcurrente implements Runnable{ 
	static final int N=200000;
	private String nombre; 
	private AtomicInteger atomic; 
	public SumaConcurrente(AtomicInteger atomic, String n) 
	{ 
		this.atomic = atomic; 
		nombre = n; 
	} 
	public void run() 
	{ 
		System.out.println("Hilo: "+nombre);
		for(int i=0;i<N;i++)
		{ 
			
			atomic.getAndIncrement();
			


		} 

	} 
} 
