import java.net.*;
import java.io.*;

public class ClienteUDP {

  static final String SERVIDOR="127.0.0.1";
  
  static final int PUERTO=3000;
  
  static final int MAX=1000;
  
  // Los argumentos proporcionan el mensaje y el nombre del servidor
  public static void main(String args[]) {

    try {
      DatagramSocket socketUDP = new DatagramSocket();
      String mensaje="Hola, ¿como estas?\0";
      byte[] mensajeBytes = mensaje.getBytes();
      InetAddress hostServidor = InetAddress.getByName(SERVIDOR);

      // Construimos un datagrama para enviar el mensaje al servidor
      DatagramPacket paquete =
        new DatagramPacket(mensajeBytes, mensaje.length(), hostServidor,
                           PUERTO);

      // Enviamos el datagrama
      socketUDP.send(paquete);

      // Construimos el DatagramPacket que contendrá la respuesta
      byte[] bufer = new byte[MAX];
     
      paquete = new DatagramPacket(bufer, bufer.length);
      socketUDP.receive(paquete);

      // Enviamos la respuesta del servidor a la salida estandar
      System.out.println("Respuesta: " + new String(paquete.getData()));

      // Cerramos el socket
      socketUDP.close();

    } catch (SocketException e) {
      System.out.println("Socket: " + e.getMessage());
    } catch (IOException e) {
      System.out.println("IO: " + e.getMessage());
    }
  }
}
