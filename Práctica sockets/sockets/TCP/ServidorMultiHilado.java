
import java.net.*;
import java.io.*;



public class ServidorMultiHilado implements Runnable {
	static final int PUERTO=4002;
	Socket s;

	public ServidorMultiHilado(){

		initServidor();

	}

	public ServidorMultiHilado(Socket s) {

		this.s=s;

	}



	//SERVIDOR
	public void initServidor(){


		ServerSocket sc;

		Socket so;


		try{

			sc = new ServerSocket(PUERTO );/* crea socket servidor que escuchara en puerto 5000*/

			while(true) {
				System.out.println("Esperando una conexion:");
				so = sc.accept();

				ServidorMultiHilado hilo = new ServidorMultiHilado(so);
				Thread tcliente = new Thread(hilo);
				tcliente.start();
				
				
				//Inicia el socket, ahora esta esperando una conexion por parte del cliente

				System.out.println("Un cliente se ha conectado.");

			}



		}catch(Exception e ){

			System.out.println("Error: "+e.getMessage());

		}
	}

	@Override
	public void run() {
		//Canales de entrada y salida de datos
		PrintWriter salida=null;

		String mensajeRecibido="";

		BufferedReader entrada=null;

		try {
			entrada = new BufferedReader(new InputStreamReader(s.getInputStream()));
		} catch (IOException e3) {
			// TODO Auto-generated catch block
			e3.printStackTrace();
		}

		try {
			salida = new PrintWriter(s.getOutputStream(), true);
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}

		System.out.println("Confirmando conexion al cliente....");

		salida.println("Mensaje 1");

		try {
			mensajeRecibido = entrada.readLine();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		System.out.println(mensajeRecibido);

		salida.println("Mensaje 2");

		salida.println("Mensaje 3");

		System.out.println("Cerrando conexion...");

		try {
			s.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}
	public static void  main(String[] args){

		ServidorMultiHilado s = new ServidorMultiHilado();   

	}
}
