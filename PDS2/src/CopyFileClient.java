/*
 *  Koszalin 2004
 *  CopyFileClient.java
 *  Przyklad klienta odbierajacego plik w kawalkach przez UDP
 *  Dariusz Rataj (C)
 */
 
import java.net.*;
import java.io.*;

public class CopyFileClient {
  int port = 5501;
  FileOutputStream file;
  int filesize;
  String filename;

private boolean openFile(String filename) {
 try {
  file = new FileOutputStream(filename);
 }
  catch (FileNotFoundException ex) 
   { System.out.println("Nieznaleziono pliku!"); return false;}
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


public CopyFileClient(String localfile, int size) {

 filename = localfile; filesize = size; // parametry pliku: nazwa i rozmiar
 try{
  /* zgloszenie uslugi - datagram pusty */
  InetAddress servAddr = InetAddress.getByName("localhost");
  byte buf[] = " ".getBytes();
  DatagramSocket socket= new DatagramSocket();
  socket.setSoTimeout(3000); // timeout dla gniazda 3 sekundy
  socket.send(new DatagramPacket(buf, buf.length, servAddr, port ));
  
  /* odbior potwierdzenia przyjecia zgloszenia */    
  buf = new byte[100];
  DatagramPacket packet = new DatagramPacket(buf, buf.length);
  socket.receive(packet);
  String answer = new String(packet.getData()).trim();
  System.out.println("Informacja od serwera: " + answer);
  
  if (!answer.equals("OK")) {
     System.out.println("Blad po stronie serwera! Koniec");
     System.exit(0);
    } 
    
  /* otwarcie pliku do zapisu */    
  openFile(filename);
  int chunks = (int)(filesize/8192); // ilosc wysylanych kawalkow 
  
  for (int i = 0 ; i <= chunks; i++) {
   byte[] buffer;   // bufor datagramu - maksymalny rozmiar kawalka 8192
   if (filesize < 8192) buffer = new byte[filesize-i*8192]; 
    else
   if (filesize-i*8192 >= 8192) buffer = new byte[8192]; else buffer = new byte[filesize-i*8192];
   
   packet = new DatagramPacket(buffer, buffer.length);
   socket.receive(packet);  // odbior kawalka   
   file.write(packet.getData());  // zapis do pliku
   socket.send(new DatagramPacket(new byte[1], 1, servAddr, 5501 )); // wyslanie potwierdzenia
  } // for  
  
 } // try 
   catch(UnknownHostException e) { System.err.println("Jakis blad - UnknownHostEx"); }
   catch(SocketException e) { System.err.println("Jakis blad - SocketEx"); }
   catch(IOException e) { System.err.println("Jakis blad - IOEx"); }
   finally { if (file != null) closeFile(); } // zamkniecie pliku
 } // konstruktor
 
  /* program gwny main*/
 public static void main(String args[]){
  new CopyFileClient("test.txt", 32);
 } 
  /* program gwny koniec main*/
} // CopyFileClient



