import java.net.*;
import java.io.*;



public class Servidor {

    final int PUERTO=4002;

    ServerSocket sc;

    Socket so;

    PrintWriter salida;

    String mensajeRecibido;

    //SERVIDOR
    public void initServidor(){

        BufferedReader entrada;
        try{

            sc = new ServerSocket(PUERTO );/* crea socket servidor que escuchara en puerto 5000*/
            System.out.println("Esperando una conexion:");
            so = sc.accept();

            //Inicia el socket, ahora esta esperando una conexion por parte del cliente

            System.out.println("Un cliente se ha conectado.");

            //Canales de entrada y salida de datos

            entrada = new BufferedReader(new InputStreamReader(so.getInputStream()));

            salida = new PrintWriter(so.getOutputStream(), true);

            System.out.println("Confirmando conexion al cliente....");

            salida.println("Mensaje 1");

            mensajeRecibido = entrada.readLine();

            System.out.println(mensajeRecibido);

            salida.println("Mensaje 2");

            salida.println("Mensaje 3");

            System.out.println("Cerrando conexion...");

            so.close();

            sc.close();

        }catch(Exception e ){

            System.out.println("Error: "+e.getMessage());

        }
    }
    public static void  main(String[] args){

        Servidor s = new Servidor();   
        s.initServidor();  

    }
}

