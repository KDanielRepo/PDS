package CarPrice;

import CarPrice.examples.carinfo.*;   // klasy skladowe zdalnego iterfejsu

import org.omg.CORBA.*;      // naming service
import org.omg.CosNaming.*;  // corba

import java.util.Properties;

/* glowna klasa - aplikacja klienta */
public class carInfoClient {

    public static void main(String[] args) {
        Properties props = new Properties();
        props.put("org.omg.CORBA.ORBInitialPort", "1050");
        props.put("org.omg.CORBA.ORBInitialHost", "127.0.0.1");

        ORB orb = ORB.init(args, props);

        try {
            // Pobranie referencji obiektu zdalnego
            org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
            NamingContext ncRef = NamingContextHelper.narrow(objRef);
            NameComponent nc = new NameComponent("CarInfo", "");
            NameComponent[] path = {nc};
            carPrice carRef = carPriceHelper.narrow(ncRef.resolve(path));

            // wywolanie metod zdalnych
            System.out.print("\n\r ----------------------------------------------------------------");
            float price = carRef.getCarPrice("Audi 80");
            System.out.print("\n\r getCarPrice() zwraca cene:     Audi 80: cena = " + price + " zl");
            if (price == -1) System.out.print(" (brak danych)");

            price = carRef.getCarPrice("Fiat 126p");
            System.out.print("\n\r getCarPrice() zwraca cene:   Fiat 126p: cena = " + price + " zl");
            if (price == -1) System.out.print(" (brak danych)");

            price = carRef.getCarPrice("Rower Zosia II");
            System.out.print("\n\r getCarPrice() zwraca cene: Rower Zosia: cena = " + price + " zl");
            if (price == -1) System.out.print(" (brak danych)");
            System.out.println("\n\r ----------------------------------------------------------------");

        } catch (Exception ex) {
            System.out.println("Blad: " + ex.toString());
        }

    } // main
} // carInfoClient
