package POAVector;/*
 *  Koszalin 2003
 *  CounterClient.java
 *  Klient obiektu CounterObject
 *  Dariusz Rataj (C)
 */

import POAVector.vtools.*;
import org.omg.CORBA.*;
import org.omg.CosNaming.*;
import org.omg.CosNaming.NamingContextPackage.*;

public class CounterClient {

  public static void main(String args[]) {
    try {
      // inicjalizacja orb
      args = new String[4];
      args[0] = "-ORBInitialPort";
      args[1] = "1050";
      args[2] = "-ORBInitialHost";
      args[3] = "127.0.0.1";
      ORB orb = ORB.init(args, null);

      // pobranie glownego kontekstu uslugi nazw
      org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
      NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);
 
       // pobranie referencji z uslugi nazw
      String name = "CounterObject";
      Counter impl = CounterHelper.narrow(ncRef.resolve_str(name));

      // wykorzystanie (wywolanie) metod
      System.out.println(" ------------------------------------------ ");
      System.out.println(" Wywolania metody inc (x5) obiektu zdalnego ");
      for (int i=0; i < 5; i++)impl.inc();
      System.out.println(" ------------------------------------------ ");
      System.out.println(" Stan zdalnego licznika " + impl.count());
      System.out.println(" ------------------------------------------ ");
      System.out.println(" Wywolania metody dec (x4) obiektu zdalnego ");
      for (int i=0; i < 4; i++)impl.dec();
      System.out.println(" ------------------------------------------ ");
      System.out.println(" Stan zdalnego licznika " + impl.count());
      System.out.println(" ------------------------------------------ ");

    } catch (Exception ex) {
        System.out.println(" !!! Blad odwolania do obiektu: \n\r" + ex.toString()) ;
    }
  } // main
} // CounterClient
