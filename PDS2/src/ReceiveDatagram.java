/*
 *  Koszalin 2004
 *  ReceiveDatagram.java
 *  Przyklad prostej aplikacji odbierajacej datagramy
 *  Dariusz Rataj (C)
 */

import java.net.*;

public class ReceiveDatagram {

public static void main(String args[]){

 int port = 5500;
 System.out.println("Aplikacja ReceiveDatagram czeka na datagramy: port " + port);
 try{
   DatagramSocket socket = new DatagramSocket(port);
   while(true) {
      byte[] buf = new byte[100];
      DatagramPacket packet = new DatagramPacket(buf, buf.length);
      socket.receive( packet );
      System.out.println("Dane odebrane [" + new String(packet.getData()).trim() + "]" );
    } // while
  }  // try
   catch(Exception e) { System.err.println(e); }
 } // main
} // ReceiveDatagram
