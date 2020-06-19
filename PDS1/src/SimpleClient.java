/*
 *  Koszalin 2004
 *  SimpleClient.java
 *  Przyklad prostego klienta CHAT
 *  Dariusz Rataj (C)
 */

import java.io.*;
import java.net.*;

public class SimpleClient {

 public static void main(String[] args) throws IOException {
  Socket socket = null;    // gniazdko
  PrintWriter out = null;    // strumien wyjsciowy - dane wysylane na server 
  BufferedReader in = null;  // strumien wejsciowy - dane czytane z servera 
  String fromServer, fromUser;

  try {
   // zainicjowanie gniazdka 
   socket = new Socket("192.168.66.5", 4444);
   System.out.println("Czekam na wiadomosc z Servera...");
   
   // pobranie strumienia wyjsciowego serwera
   out = new PrintWriter(socket.getOutputStream(), true);  
   // pobranie strumienia wejsciowego serwera
   in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
   } 
   catch (UnknownHostException e) {
     System.err.println("Nieznany adres hosta");
     System.exit(1);
    } 
   catch (IOException e) {
     System.err.println("Blad operacji Wejscia/Wyjscia - I/O error");
     System.exit(1);
    }

   // stdIn - wejscie standardowe (wej. konsoli java)
   BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));  
   out.println("Jest na linii! Pisz Serwerze!!!");
      
   while ((fromServer = in.readLine()) != null) // petla odczytu i zapisu do gniazdka 
    {
     System.out.println("Server: " + fromServer);  // wydruk na konsoli danych z serwera 
     if (fromServer.equals("exit") || fromServer.equals("quit")) // zakonczenie przez serwer
      {
       System.out.println("Zostalem wyrzucony przez Server!");
       break;
      } 
	  System.out.print("Client: ");
     fromUser = stdIn.readLine(); // pobranie danych z konsoli
     if (fromUser != null) {
       out.println(fromUser);  // wyslanie danych do serwera 
      }
     }
     out.close();      // zamkniecie otwartych strumieni i gniazdka 
     in.close();
     stdIn.close();
     socket.close();
  }
}
