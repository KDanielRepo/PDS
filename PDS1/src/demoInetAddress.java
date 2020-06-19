/*
 *  Koszalin 2004
 *  demoInetAddress.java
 *  Przyklad demonstrujacy podstawowe zastosowania klasy InetAddress
 *  Dariusz Rataj (C)
 */
 
import java.net.*;

class demoInetAddress {

 public static void main (String[] args) {
  try {
   // pobranie danych lokalnego hosta
   InetAddress adres1 = InetAddress.getLocalHost();
   System.out.println("Nazwa lokalnego hosta: " + adres1.getHostName());
   System.out.println("Adres lokalnego hosta: " + adres1.getHostAddress() + "\n");
   
   // pobranie danych hosta o znanej nazwie
   InetAddress adres2 = InetAddress.getByName("153.19.134.114");
   System.out.println("Nazwa: " + adres2.getHostName());
   System.out.println("Adres: " + adres2.getHostAddress() + "\n");
   
   // pobranie danych hosta o znanym adresie IP
   InetAddress adres3 = InetAddress.getByName("194.181.178.33");
   System.out.println("Nazwa: " + adres3.getHostName());
   System.out.println("Adres: " + adres3.getHostAddress() + "\n");
  }
  catch (UnknownHostException e) {
    System.err.println(e);
   }
 } // koniec main
} // koniec demoInetAddress
 

 