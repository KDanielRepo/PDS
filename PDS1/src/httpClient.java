/*
 *  Koszalin 2004
 *  httpClient.java
 *  Przyklad prostego klienta protokolu http
 *  Dariusz Rataj (C)
 */

import java.io.*;
import java.net.*;

public class httpClient
{
 // pobranie domyslnego dokumentu w glownym katalogu serwera http (/) 
 // najczescie index.html
 String message = "GET / HTTP/1.1\r\n"+
         "Accept: */*\r\n" + // */
         "Connection: close\r\n"+
         "Referer: 192.168.8.100\r\n"+
         "User-Agent: Mozilla/4.0 (compatible; MSIE 5.5; Windows 98; IDG.pl)\r\n"+
         "Host: 192.168.8.100\r\n"+
         "Content-Type: text/html\r\n"; 
         
 void getDocument()
 {
  Socket socket;
  BufferedReader in;
  String tekst = "", all = "";
  PrintWriter out = null;    
  
  try {
   socket = new Socket("192.168.8.100", 80);
   in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
   out = new PrintWriter(socket.getOutputStream(), true);
   out.println(message);
   System.out.println("---------------- Komunikat http wyslany --------------------");
   System.out.println("------------------ Odpowiedz serwera: ----------------------");
   while( (tekst=in.readLine()) !=null){
        System.out.println(tekst);
        }
   in.close();
   socket.close();   
  }
  catch (UnknownHostException e) {
    System.err.println("Blad URL: sprawdz poprawnosc url");
   }
  catch (IOException e) {
    System.err.println("Blad I/O");
   }
   
  System.out.println("------------------- Koniec odpowiedzi ----------------------");
 }
 
 public httpClient()  // konstruktor
 {
  getDocument();      
 }

 public static void main(String args[])
 {
  new httpClient(); // utworzenie obiektu klienta
 }
}
