/*
 *  Koszalin 2004
 *  SendDatagram.java
 *  Przyklad prostej aplikacji wysylajacej datagramy
 *  Dariusz Rataj (C)
 */

import java.net.*;
import java.util.*; 

public class SendDatagram {

public static void main(String args[]) {

 int port = 5500;
 for (int i =0; i<10; i++) { // petla wysyla 10 datagramow
  try {
    InetAddress servAddr = InetAddress.getByName("localhost");
    String data = i + ". Data i godzina wyslania: " + new Date();
    byte buf[] = data.getBytes();
    DatagramSocket socket= new DatagramSocket();
    DatagramPacket packet = new DatagramPacket(buf, buf.length, servAddr, port );
    socket.send(packet);
    System.out.println("Wyslano datagram");

    /* Zatrzymanie na 1s */
     try {
       Thread.sleep(1000); 
     }
      catch (InterruptedException ex) {System.err.println("Blad sleep()!"); }; 
    
   } // try
    catch(Exception ex) { 
      System.err.println(ex); 
    }
      
  } // for
 } // main  
} // SendDatagram



