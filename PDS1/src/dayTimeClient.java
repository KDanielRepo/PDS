/*
 *  Koszalin 2004
 *  dayTimeClient.java
 *  Przyklad prostego klienta daty i czasu
 *  Dariusz Rataj (C)
 */

import java.net.*;
import java.io.*;

public class dayTimeClient {

 public static void main(String[] args) {

  Socket socket;
  String host = "localhost";
  BufferedReader in;
  PrintWriter out = null;
  String message = "GET / HTTP/1.1\r\n"+
          "Accept: */*\r\n" + // */
          "Connection: close\r\n"+
          "Referer: 192.168.8.100\r\n"+
          "User-Agent: Mozilla/4.0 (compatible; MSIE 5.5; Windows 98; IDG.pl)\r\n"+
          "Host: 192.168.8.100\r\n"+
          "Content-Type: text/html\r\n";

     if (args.length > 0) host = args[0];
    
  /*   tworzenie gniazda klienta, pobranie danych, wydruk informacji na konsoli */
  try {
    socket = new Socket(host, 13);
    //socket.setSoTimeout(1);
      out = new PrintWriter(socket.getOutputStream(), true);
      out.println(message);
    in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    String time = in.readLine();
    System.out.println("Host: " + host + " zwraca na porcie nr 13:");
    System.out.println("date i czas: " + time);
   } 
  catch (UnknownHostException e) {
    System.err.println(e);
   }
  catch (IOException e) {
    System.err.println(e);
   }
    
 }  // koniec main
} // koniec dayTimeClient
