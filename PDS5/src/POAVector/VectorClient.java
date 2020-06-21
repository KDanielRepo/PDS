package POAVector;/*
 *  Koszalin 2003
 *  VectorClient.java
 *  Klient obiektu VectorObject
 *  Dariusz Rataj (C)
 */

import POAVector.vtools.*;
import org.omg.CORBA.*;
import org.omg.CosNaming.*;
import org.omg.CosNaming.NamingContextPackage.*;
public class VectorClient {


  public static void printVector(int size, int[] v){
   System.out.print("\n\r");
   for (int i=0; i < size; i++) System.out.print(" " + v[i]);
   System.out.print("\n\r");
  }
  
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
      String name = "VectorObject";
      Vector impl = VectorHelper.narrow(ncRef.resolve_str(name));

      // wykorzystanie (wywolanie) metod
      int a[] = {2, 3, 5, 4, 27, 3, 3 ,44 , 44, 55};
      int b[] = {3, 4, 6, 5, 45, 65, 5, 6, 32, 56};

      System.out.println(" --------------------------------------- ");
      System.out.println(" Wywolanie metody add obiektu zdalnego ");
      int c[] = impl.add((short)10, a, b);
      System.out.println(" --------------------------------------- ");
      printVector(10, a); 
      System.out.print(" + "); 
      printVector(10, b); 
      System.out.print(" = ");
      printVector(10, c);
      System.out.println(" --------------------------------------- ");
      System.out.println(" Wywolanie metody sub obiektu zdalnego ");
      System.out.println(" --------------------------------------- ");
      int d[] = impl.sub((short)10, a, b);
      printVector(10, a); 
      System.out.print(" - "); 
      printVector(10, b); 
      System.out.print(" = ");
      printVector(10, d);
      System.out.println(" --------------------------------------- ");

    } catch (Exception ex) {
        System.out.println(" !!! Blad odwolania do obiektu: \n\r" + ex.toString()) ;
    }
  } // main
} // VectorClient
