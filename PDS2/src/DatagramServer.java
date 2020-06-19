/*
 *  Koszalin 2004
 *  DatagramServer.java
 *  Przyklad serwera uslugi daytime - UDP
 *  Dariusz Rataj (C)
 */

import java.net.*;
import java.util.*;
import java.io.*;

public class DatagramServer{
 public static void main(String args[]){
    try{
      DatagramSocket socket = new DatagramSocket(13);
      while(true){
      
        /* Odebranie pustego datagramu */
        byte[] buf = new byte[100];
        DatagramPacket packet = new DatagramPacket(buf, buf.length);
        socket.receive( packet );
        System.out.println("Odebrano datagram: [" + new String(buf).trim() + "]"); 
        
        /* Wyslanie datagramu z data i godzina */
        buf = new Date().toString().getBytes();
        socket.send( new DatagramPacket( buf, buf.length,
                               packet.getAddress(), packet.getPort() ) );
        System.out.println("Odeslano datagram: [" + new String(buf).trim() + "]");                       
        } // while
      }  // try
       catch(UnknownHostException e) { System.err.println("Jakis blad - UnknownHostEx"); }
       catch(SocketException e) { System.err.println("Jakis blad - SocketEx"); }
       catch(IOException e) { System.err.println("Jakis blad - IOEx"); }
    } // main
} // DatagramServer
