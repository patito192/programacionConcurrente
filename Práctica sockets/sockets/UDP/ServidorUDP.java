
import java.net.*;
import java.io.*;

public class ServidorUDP {
  static final int MAX=1000;
  static final int PUERTO=3000;   
  public static void main (String args[]) {

    
    try {

      DatagramSocket socketUDP = new DatagramSocket(PUERTO);
      byte[] bufer = new byte[MAX];

      while (true) {
        // Construimos el paquete para recibir peticiones
        DatagramPacket paquete = new DatagramPacket(bufer, bufer.length);

        //Leemos una petición 
        socketUDP.receive(paquete);

        System.out.println("Conexion: " + paquete.getAddress()+" Puerto: "+paquete.getPort());

        System.out.println("Servidor: He recibido -> "+new String(paquete.getData()));
        
        String respuesta="Bien!\0";
        
        byte[] respuestaBytes=respuesta.getBytes();
        
        // Construimos el paquetet para enviar la respuesta
        paquete = new DatagramPacket(respuestaBytes, respuesta.length(),
                             paquete.getAddress(), paquete.getPort());

        //Enviamos la respuesta
        socketUDP.send(paquete);
      }

    } catch (SocketException e) {
      System.out.println("Socket: " + e.getMessage());
    } catch (IOException e) {
      System.out.println("IO: " + e.getMessage());
    }
  }

}

