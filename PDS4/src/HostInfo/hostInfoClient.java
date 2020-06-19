package HostInfo;


import HostInfo.hostinfo.*;          // klasy skladowe zdalnego iterfejsu
import org.omg.CosNaming.*;  // naming service
import org.omg.CORBA.*;      // klasy corba

import java.util.Properties;

/* glowna klasa - aplikacja klienta */
public class hostInfoClient
{
  public static void main(String args[])
  {
    try{
      /* inicjalizacja ORB */
      ORB orb = ORB.init(args, null);
      System.out.println("ORB zainicjowany...");

      /* pobranie glownego kontekstu uslugi nazw */
      org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
      NamingContext ncRef = NamingContextHelper.narrow(objRef);
      
      /* pobranie referencji do obiektu zdalnego */
      NameComponent nc = new NameComponent("hostInfo", "");
      NameComponent path[] = {nc};
      hostInfo infoRef = hostInfoHelper.narrow(ncRef.resolve(path));
      System.out.println("referencja obiektu zdalnego pobrana...");

      /* wywolanie zdalnych metod */
      String address = infoRef.getAddress();
      String daytime = infoRef.getDayTime();
      System.out.println(" ------------------------------------------------------------- ");
      System.out.println("     Zdalny obiekt zwraca adres: " + address);
      System.out.println("   Zdalny obiekt zwraca daytime: " + daytime);
      System.out.println(" ------------------------------------------------------------- ");
         
     } catch(Exception ex) {
        System.err.println("Blad: " + ex);
      }  
  }
}


