/*
 *  Koszalin 2004
 *  getAllAddresses.java
 *  Przyklad - pobranie z DNS wszystkich IP przypisanych do nazwy
 *  Dariusz Rataj (C)
 */

import java.net.*;

class getAllAddresses {

 public static void main (String[] args) {

  String host = "kos.man.koszalin.pl";
  if (args.length > 0) host = args[0];
  
  try {
    System.out.println("Adresy przypisane do nazwy " + host + ":\n");
    InetAddress[] adresy = InetAddress.getAllByName(host);
    for (int i = 0; i < adresy.length; i++) {
       System.out.println(adresy[i]);
     }
   }
   catch (UnknownHostException ex) {
    System.err.println("Nie moge znalezc hosta " + host + " :( !");
  } // koniec catch
 } // koniec main

} // koniec getAllAddresses
