package CarPrice.DSIServer;

import java.util.*;
import org.omg.CosNaming.*; // naming service
import java.io.*;
import org.omg.CORBA.*;     // corba 

// klasa obiektu DSI rozszerza DynamicImplementation
class carDSIServant extends DynamicImplementation
{
  static String[] myIDs = {"IDL:examples/carinfo/carPrice:1.0"};
  ORB orb;
 
  Hashtable cars = new Hashtable(); // tablica danych
 
  // konstruktor
  carDSIServant(ORB orb) {
   this.orb = orb;
   // wypelnienie tablicy danymi
   cars.put("Audi 80", new Float(18000.0f)); 
   cars.put("VW Golf", new Float(12000.0f)); 
   cars.put("Fiat 126p", new Float(25000.0f)); 
   cars.put("Peugeot 306", new Float(17500.0f)); 
   cars.put("Mazda 626", new Float(8000.0f)); 
 }
  
 // metoda obslugujaca zdalne wywolania metod
 public void invoke(ServerRequest request) {
  System.out.println("Wywolanie invoke");
  try {
    System.out.println("DSI: invoke called, op = "+request.operation());
    
    // 1. Utworzenie tablicy przechowujacej argumenty
    NVList nvlist = orb.create_list(1);

	 // 2. Pobranie nazwy wywolywanej metody
    if (request.operation().equals("getCarPrice") == true) {
   
	  // 3. Pobranie argumentw wywoania
     Any any1 = orb.create_any();
     any1.insert_string("");
     nvlist.add_value("arg1", any1, ARG_IN.value);

	  // pobranie parametrow wywolania metody
     request.arguments(nvlist);

     // pobranie argumentu pierwszego
     String arg1 = nvlist.item(0).value().extract_string();
     System.err.println("Argument 1: wartosc: " + arg1);

     // pobranie ceny z tablicy cars
     float price;
     Float price_obj = (Float)cars.get(arg1); 
     if (price_obj != null) { 
      price = price_obj.floatValue(); 
      System.out.println( "Zwracam wartosc: " + arg1 + ": cena = " + price); 
     } else 
     {
      price = -1.0f;
      System.out.println( "Zwracam wartosc: " + arg1 + " : Brak danych!" );
     } 

     // 4. Utworzenie i zwrot wyniku
     Any result = orb.create_any(); // utworzenie wyniku
     result.insert_float(price);
                  
     request.set_result(result); // zwrot wyniku metody
    }
   } catch (Exception ex) {
       ex.printStackTrace();
       System.out.println("DSIExample: Exception thrown: " + ex);
      }
 }    

// metoda zwracajaca identyfikator obiektu zdalnego
// wykorzystywana przez orb
public String[] _ids() {    
        return myIDs;
 }
}

/* glowna klasa - aplikacja serwera */
public class carDSIServer {
 
 public static void main(String args[])
 {
  try{
   // zainicjowanie obiektu brokera
      args = new String[4];
      args[0] = "-ORBInitialPort";
      args[1] = "1035";
      args[2] = "-ORBInitialHost";
      args[3] = "127.0.0.1";
      /*Properties prop = new Properties();
      prop.put("com.sun.CORBA.ORBServerPort", "1035");
      prop.put("com.sun.CORBA.ORBServerHost", "127.0.0.1");*/
   ORB orb = ORB.init(args, null);
 
   // utworzenie i zgloszenie obiektu zdalnego 
   carDSIServant carRef = new carDSIServant(orb);
   orb.connect(carRef); 

   // pobranie glownego kontekstu usugi nazw
   org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
   NamingContext ncRef = NamingContextHelper.narrow(objRef);
      
   // Zwizanie referencji obiektu z nazw w Naming Service
   NameComponent nc = new NameComponent("CarInfo", "");
   NameComponent path[] = {nc};
   ncRef.rebind(path, carRef);
   System.out.println("Obiekt carDSIServant gotowy...czekam");
 
   // oczekiwanie na zgloszenie klienta
   java.lang.Object sync = new java.lang.Object();
   synchronized (sync) {
    sync.wait();
   }
  } catch (Exception e) {
     e.printStackTrace();
      //System.err.println("Blad inicjalizacji obiektu!");
    }
 } // main
} // carDSIServer      


