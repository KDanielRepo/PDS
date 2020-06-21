package Callback;

import Callback.CallbackApp.Message;
import Callback.CallbackApp.MessageHelper;
import org.omg.CosNaming.*;
import org.omg.CORBA.*;

// implementacja klasy obiektu zdalnego 
class MsgCallbackServant extends Callback.CallbackApp._MsgCallbackImplBase {
    // metoda zdalna wywolywana przez serwer
    public void callback(String msgBack) {
        System.out.println("callback: Odebrano wiadomosc: " + msgBack);
    }
}

public class CallbackClient {
    public static void main(String args[]) {
        try {
            // inicjalizacja ORB
            args = new String[4];
            args[0] = "-ORBInitialPort";
            args[1] = "1050";
            args[2] = "-ORBInitialHost";
            args[3] = "127.0.0.1";
            ORB orb = ORB.init(args, null);

            // pobranie glownego kontekstu uslugi nazw
            org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
            NamingContext ncRef = NamingContextHelper.narrow(objRef);

            // pobranie referencji do obiektu zdalnego
            NameComponent nc = new NameComponent("MessageCallback", "");
            NameComponent path[] = {nc};
            Message helloRef = MessageHelper.narrow(ncRef.resolve(path));

            // utworzenie obiektu zdalnego i rejestracja w ORB
            MsgCallbackServant msgCallbackRef = new MsgCallbackServant();
            orb.connect(msgCallbackRef);

            // wywolanie zdalnej metody i wydruk wyniku
            String toServer = " Wiadomosc od klienta ";
            System.out.println("\nWywolanie metody sendMessage(\"" + toServer + "\")");
            String msg = helloRef.sendMessage(msgCallbackRef, toServer);
            System.out.println("Wiadomosc zwracana przez metode sendMessage: " + msg);

        } catch (Exception e) {
            System.out.println("ERROR : " + e);
            e.printStackTrace(System.out);
        }
    }
}

 
