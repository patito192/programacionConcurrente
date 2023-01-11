import java.net.*;

import java.io.*;



public class Cliente {

    final String HOST = "localhost";

    final int PUERTO=4002;

    Socket sc;

    PrintWriter mensaje;

    BufferedReader entrada;

    String mensajeRecibido;

    //Cliente

    public void initCliente(){



        try{

            sc = new Socket( HOST , PUERTO ); /*conectar a un servidor en localhost con puerto 5000*/

            //creamos el flujo de datos por el que se enviara un mensaje

            mensaje = new PrintWriter(sc.getOutputStream(), true);;

            entrada = new BufferedReader(new InputStreamReader(sc.getInputStream()));

            System.out.println("A intercambiar mensajes....");

            //enviamos el mensaje

            mensaje.println("hola que tal!!");

            mensajeRecibido = entrada.readLine();

            System.out.println(mensajeRecibido);

            mensajeRecibido = entrada.readLine();

            System.out.println(mensajeRecibido);

            mensajeRecibido = entrada.readLine();

            System.out.println(mensajeRecibido);

            //cerramos la conexion

            sc.close();

        }catch(Exception e ){

            System.out.println("Error: "+e.getMessage());

        }

    }
    public static void  main(String[] args){

        Cliente c = new Cliente();   
        c.initCliente();  

    }
}
