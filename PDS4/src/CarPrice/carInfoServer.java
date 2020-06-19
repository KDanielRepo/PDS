package CarPrice;

import CarPrice.examples.carinfo.*;   // klasy skladowe zdalnego iterfejsu

import java.util.*;          // Hashtable
import org.omg.CosNaming.*;  // naming service
import org.omg.CORBA.*;      // corba


/* implementacja obiektu zdalnego */
class carPriceServant extends CarPrice.examples.carinfo._carPriceImplBase
{
  Hashtable cars = new Hashtable();

  carPriceServant() // konstruktor
  {
   // wypelnienie tablicy danymi
   cars.put("Audi 80", new Float(18000.0f)); 
   cars.put("VW Golf", new Float(12000.0f)); 
   cars.put("Fiat 126p", new Float(25000.0f)); 
   cars.put("Peugeot 306", new Float(17500.0f)); 
   cars.put("Mazda 626", new Float(8000.0f)); 
  }
  
  public float getCarPrice (String model)   // metoda zdalna
  {
   System.out.println("Wywolanie metody getCarPrice()");
   Float price_obj = (Float)cars.get(model); 
   if (price_obj != null) { 
    float price = price_obj.floatValue(); 
    System.out.println( "Zwracam wartosc: " + model + ": cena = " + price); 
    return price;
   } else 
   {
    System.out.println( "Zwracam wartosc: " + model + " : Brak danych!" );
    return -1.0f;
   } 
   
  } // getCarPrice
} // carPriceServant 

/* glowna klasa - aplikacja serwera */
public class carInfoServer 
{
  public static void main(String args[])
  {
    try{
    
      // inicjalizacja ORB
        args = new String[4];
        args[0] = "-ORBInitialPort";
        args[1] = "1035";
        args[2] = "-ORBInitialHost";
        args[3] = "127.0.0.1";
      ORB orb = ORB.init(args, null);
      
      // utworzenie obiektu zdalnego i rejestracja w ORB
      carPriceServant carRef = new carPriceServant();
      orb.connect(carRef);

      // pobranie glownego kontekstu usugi nazw
      org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
      NamingContext ncRef = NamingContextHelper.narrow(objRef);
         

      // Zwiazanie referencji obiektu z nazwa w Naming Service
      NameComponent nc = new NameComponent("CarInfo", "");
      NameComponent path[] = {nc};
      ncRef.rebind(path, carRef);
      System.out.println("Obiekt carPriceServant gotowy...czekam");

      // oczekiwanie na wywolanie klienta
      java.lang.Object sync = new java.lang.Object();
      synchronized(sync){
        sync.wait();
      }
//      orb.run();
    } catch(Exception ex) {
        System.err.println("Blad: " + ex);
      }  
  }  // main
} // carInfoServer

