package CarPrice.DIIClient;

import org.omg.CORBA.*;     // corba
import org.omg.CosNaming.*; // naming service

import java.util.Properties;


/* glowna klasa - aplikacja klienta */
public class carDIIClient {

    public static void main(String[] args) {
        Properties props = new Properties();
        props.put("org.omg.CORBA.ORBInitialPort", "1050");
        props.put("org.omg.CORBA.ORBInitialHost", "127.0.0.1");

        ORB orb = ORB.init(args, props);

        try {

            // Pobranie referencji obiektu zdalnego
            org.omg.CORBA.Object ncRef = orb.resolve_initial_references("NameService");
            NamingContext nc = NamingContextHelper.narrow(ncRef);
            NameComponent nComp = new NameComponent("CarInfo", "");
            NameComponent[] path = {nComp};
            org.omg.CORBA.Object objRef = nc.resolve(path);

            // Utworzenie dynamicznego wywolania metody:
            // 1. Utworzenie listy argumentow (arg_list)
            NVList arg_list = orb.create_list(1);
            Any argument = orb.create_any();
            argument.insert_string("Audi 80");
            arg_list.add_value("model", argument, org.omg.CORBA.ARG_IN.value);

            // 2. Utworzenie obiektu przechowujacego wynik (result)
            Any result = orb.create_any();
            result.insert_float(0);
            NamedValue resultVal = orb.create_named_value("price", result, org.omg.CORBA.ARG_OUT.value);

            // 3. Utworzenie wywolania metody z podaniem listy argumentow
            //    oraz obiektu wyniku
            Request thisReq = objRef._create_request(null, "getCarPrice", arg_list, resultVal);
            thisReq.invoke();

            // 4. Wywo�anie dynamicznie utworzonego wywolania metody, pobranie wyniku
            result = thisReq.result().value();
            System.out.println("metoda getCarPrice() zwraca cene Audi 80:  " + result.extract_float());


            // Ponowne wywolanie metody
            // 1. Zmiana wartosci argumentu
            argument.insert_string("Fiat 126p");
            arg_list.remove(0);  // usuniecie argumentu z listy
            arg_list.add_value("model", argument, org.omg.CORBA.ARG_IN.value);

            // 2. Wywolanie metody
            thisReq = objRef._create_request(null, "getCarPrice", arg_list, resultVal);
            thisReq.invoke();

            // 3 Pobranie wyniku
            result = thisReq.result().value();
            System.out.println("metoda getCarPrice() zwraca cene Fiat 126p:  " + result.extract_float());


        } catch (Exception ex) {
            ex.printStackTrace();
            //System.out.println("Blad DII: " + ex.toString());

        }

    } // main
} // carDIIClient
