/*
 *  Koszalin 2004
 *  DatagramClient.java
 *  Przyklad klienta uslugi daytime - UDP
 *  Dariusz Rataj (C)
 */

import java.net.*;
import java.io.*;

public class DatagramClient {
public static void main(String args[]) {
    try{
      InetAddress servAddr = InetAddress.getByName("localhost");

      /* wyslanie pustego datagramu */
      byte buf[] = new byte[1];
      DatagramSocket socket= new DatagramSocket();
      socket.send(new DatagramPacket(buf, buf.length, servAddr, 13 ));
      System.out.println("Wyslano datagram: [" + new String(buf).trim() + "]"); // datagram pusty
      
      /* odbior odpowiedzi */
      buf = new byte[100];
      DatagramPacket packet = new DatagramPacket(buf, buf.length);
      socket.setSoTimeout(2000);
      socket.receive(packet);
      System.out.println("Odebrano datagram: [" + new String(buf).trim() + "]");
      } // try 
       catch(UnknownHostException e) { System.err.println("Jakis blad - UnknownHostEx"); }
       catch(SocketException e) { System.err.println("Jakis blad - SocketEx"); }
       catch(IOException e) { System.err.println("Jakis blad - IOEx"); }
    } // main
} // DatagramClient



