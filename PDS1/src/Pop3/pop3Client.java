package Pop3;/*
 *  Koszalin 2004
 *  pop3Client.java
 *  Dariusz Rataj (C)
 */

import java.io.*;
import java.net.*;

public class pop3Client {

  // obiekt gniazda i strumienie I/O
  Socket socket = null;              // gniazdo
  PrintWriter out = null;         // input
  BufferedReader in = null; // output


  // wysylanie komunikatow przez strumien out
  public void sendText(String text)
    {
      out.println(text);
      System.out.println(" K: " + text);
    }

  // odbieranie komunikatow przez strumien in
  public String recvText() throws IOException
    {
      String odp = in.readLine();
      System.out.println(" S: " + odp);
      return odp;
    }

  // konstruktor: argumenty <host> <login> <password>
  public pop3Client(String host, String login, String password) throws IOException {
 
      // pr�ba nawi�zania po��czenia z serwerem POP3
      try {
        socket = new Socket(host, 110);
        out = new PrintWriter(socket.getOutputStream(), true);
        in = new BufferedReader(
                      new InputStreamReader(socket.getInputStream()));
      } catch (UnknownHostException e) {
          System.err.println("Nieznany host: "+host);
          System.exit(1);
      } catch (IOException e) {
          System.err.println("Problem z polaczeniem z "+host);
          System.exit(1);
      }

      String odpowiedz; // odpowiedz serwera
      System.out.println(" ------ Powitanie ------- ");
      recvText();

      System.out.println(" ------ Autoryzacja: login password ------- ");
      sendText("USER "+login);
      if ((odpowiedz = recvText()).charAt(0)=='+')
      {
        // login zosta� zaakceptowany
        sendText("PASS "+password);

        if ((odpowiedz = recvText()).charAt(0)=='+')
        {
          // has�o zosta�o zaakceptowane
          System.out.println(" ------ Pobieranie informacji ------- ");
          sendText("STAT");
          odpowiedz = recvText();

          // szukamy liczby wiadomo�ci w komunikacie
          int od  = odpowiedz.indexOf(' ')+1; 
          int doo = odpowiedz.indexOf(' ',od); 
          int ilosc = Integer.parseInt(odpowiedz.substring(od,doo));
          String bajtow = odpowiedz.substring(doo+1);
          System.out.println(" Liczba wiadomosci: " + ilosc + " (" + bajtow +" bajtow)");

          System.out.println(" ------ Pobieranie wiadomosci do plikow listX.txt------- ");
          PrintWriter plik=null;

          for (int t = 1; t <= ilosc; t++) {
            plik = new PrintWriter(new FileOutputStream("list"+t+".txt"));
            sendText("RETR "+t);
            in.readLine();   // pobranie linii odpowiedzi

            // odbieranie cia�a wiadomo�ci i zapisanie do pliku
            while ( !(odpowiedz = in.readLine()).equals(".") ) {
              if (odpowiedz.length() > 0 && odpowiedz.charAt(0) == '.')
                {
                plik.println(odpowiedz.substring(1));
                }
              else
                plik.println(odpowiedz);
            }

            // tu mo�na wstawi� komend� usuwaj�c� pobran� wiadomo�c DELE:
            // out.println("DELE "+t);

            plik.close();
          } 
        
          // roz��czenie
          System.out.println(" ------ Rozlaczanie ------- ");
          sendText("QUIT");
          recvText();
         }
        else
          System.out.println(odpowiedz);  // has�o nie zosta�o zaakceptowane
      }
      else
        System.out.println(odpowiedz);    // login nie zosta� zaakceptowany

      // zamkni�cie strumieni�w gniazdka i samego gniazdka
      out.close();
      in.close();
      socket.close();

  }
   
  public static void main(String[] args) throws IOException {
  
   if (args.length < 3)
    {
      System.out.println("uruchom poleceniem: java pop3Client adres_hosta uzytkownik haslo");
      System.exit(1);
    }

   String host = args[0];
   String login = args[1];
   String password = args[2];
   
   new pop3Client(host, login, password);
  }
}
