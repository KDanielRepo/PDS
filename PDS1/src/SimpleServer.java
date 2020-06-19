/*
 *  Koszalin 2004
 *  SimpleServer.java
 *  Przyklad prostego serwera CHAT
 *  Dariusz Rataj (C)
 */
 
import java.net.*;
import java.io.*;

public class SimpleServer {
    public static void main(String[] args) throws IOException {
        String fromClient="", fromServer="";
        ServerSocket serverSocket = null; // gniazdko serwera
        try {
            // zainicjowanie gniazdka na porcie nr 4444
            serverSocket = new ServerSocket(4444); 
        } catch (IOException e) {
            System.err.println("Nie moge oczekiwac na porcie 4444!");
            System.exit(1);
        }

       BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
       while (true) 
       {
        Socket clientSocket = null;  // gniazdko klienta
        System.out.println("Jestem Serwer:");
        System.out.println(" 'exit' - wyrzucenie Klienta i ponowne oczekiwanie");
        System.out.println(" 'quit' - zakonczenie pracy");
        System.out.println("Oczekiwanie na polaczenie...");
        try {
            clientSocket = serverSocket.accept(); // wlaczenie nasluchu i ew. nawiazanie polaczenia
            } catch (IOException e) 
             {
               System.err.println("Blad Accept");
               System.exit(1);
              }
        // pobranie strumienia wyjsciowego klienta
        PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
        // pobranie strumienia wejsciowego klienta
        BufferedReader in = new BufferedReader(
                            new InputStreamReader(clientSocket.getInputStream()));
                
        while ((fromClient = in.readLine()) != null) // petla odczytu i zapisu do gniazdka 
           {
             System.out.println("Client: " + fromClient); // wydruk na konsoli danych klienta
             if (fromClient.equals("exit"))  // zakonczenie przez klienta
               {
                System.out.println("Client sie odlaczyl!!! ");
                break;
               } 
             if (fromServer.equals("quit")) // zakonczenie przez serwer 
                break;  
             System.out.print("Server: ");
             fromServer = stdIn.readLine(); // pobranie danych z konsoli
             if (fromServer != null) 
  	           {
               out.println(fromServer); // wyslanie danych do klienta 
              }            
           }
 
        out.close(); // zamkniecie otwartych strumieni i gniazdka 
        in.close();
        clientSocket.close();
        if (fromServer.equals("quit")) // zakonczenie przez serwer
                break;
       }    
       
     stdIn.close(); // zamkniecie strumienia z konsoli i gniazdka serwera
     serverSocket.close();
    }
}
