/*
 *  Koszalin 2004
 *  CopyFileServer.java
 *  Przyklad serwera wysylajacego plik w kawalkach przez UDP
 *  Dariusz Rataj (C)
 */

import java.net.*;
import java.io.*;

public class CopyFileServer {
  int port = 5501;
  FileInputStream file;
  int filesize;
  String filename;
  
private boolean openFile(String filename) {
 try {
  file = new FileInputStream(filename);
  filesize = file.available(); // wielkosc pliku
 }
  catch (FileNotFoundException ex) 
   { System.out.println("Nie znaleziono pliku!"); return false;}
  catch (IOException ex) 
   { System.out.println("Blad odczytu!"); return false; }
   
 return true;  
}

private void closeFile() {
 try {
  if (file != null)  file.close();
 }
 catch (IOException ex) 
    { System.out.println("Blad przy zamykaniu pliku!"); }
}

public CopyFileServer(String localfile) {
 System.out.println("Startuje serwer CopyFile na porcie " + port);
 
 filename = localfile; // nazwa pliku
 try{
   DatagramSocket socket = new DatagramSocket(port);  // gniazdo serwera na porcie
   
 while (true) {
    /* odczyt zgloszenia - datagram pusty */
   byte[] buf = new byte[100]; // bufor do odczytu datagramu
   DatagramPacket packet = new DatagramPacket(buf, buf.length);
   socket.receive( packet );   // oczekiwanie na datagram
   System.out.println("Zgloszenie od klienta...");

   /* wyslanie potwierdzenia "OK" */
   if (openFile(filename)) buf = "OK".getBytes(); else System.exit(0);
   socket.send(new DatagramPacket(buf, buf.length, packet.getAddress(), packet.getPort()));
 
   /* wyslanie kolejnych kawalkow */
   int chunks = (int)(filesize/8192);
   for (int i =0 ; i <= chunks; i++) {
    byte[] buffer;
    if (filesize-i*8192 >= 8192) buffer = new byte[8192]; else buffer = new byte[filesize-i*8192];
    int count = file.read(buffer);
    System.out.println("Wysylam kawalek: " + count + " " + (i+1)+ "/" + (chunks+1));
    socket.send( new DatagramPacket( buffer, buffer.length, packet.getAddress(), packet.getPort()));
    socket.receive( packet ); // odbior potwierdzenia - datagram pusty
   }
   if (file != null) closeFile(); // zamkniecie pliku
  } // while
 } // try 
  catch(UnknownHostException e) { System.err.println("Jakis blad - UnknownHostEx"); }
  catch(SocketException e) { System.err.println("Jakis blad - SocketEx"); }
  catch(IOException e) { System.err.println("Jakis blad - IOEx"); }
}

public static void main(String args[]){   
  /*if (args.length > 0 ) { new CopyFileServer(args[0]); }
    else {    
     System.out.println("Poprawne uruchomienie serwera:");   
     System.out.println("java CopyFileServer <nazwa pliku>");   
    }*/
  new CopyFileServer("test.txt");
 } // main
 
} // DatagramServer
