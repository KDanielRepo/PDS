package Callback;

import org.omg.CosNaming.*;
import org.omg.CosNaming.NamingContextPackage.*;
import org.omg.CORBA.*;
 
class MessageServant extends Callback.CallbackApp._MessageImplBase
{

  // metoda zdalna wywolywana przez klienta
  // wywoluje metode callback po stronie klienta
  public String sendMessage(Callback.CallbackApp.MsgCallback callbackObject, String message)
  {
   System.out.println("sendMessage: Odebrano wiadomosc: " + message);
   System.out.println("sendMessage: Wywolania metody callback(\""+message+" n\")");
   for (int i=1; i<=10; i++) {
     try {
       Thread.sleep(2000); 
     } catch (InterruptedException ex){}; 
     
     // wywolanie callback co 2 sekundy 
	  callbackObject.callback(message + " " + i);
	 } 
	return " Return z sendMessage !!! ";
  }
}

 
public class CallbackServer {
 
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
	    MessageServant msgRef = new MessageServant();
	    orb.connect(msgRef);
 
	    // pobranie glownego kontekstu uslugi nazw
	    org.omg.CORBA.Object objRef = 
		 orb.resolve_initial_references("NameService");
	    NamingContext ncRef = NamingContextHelper.narrow(objRef);
 
	    // Zwiazanie referencji obiektu z nazw w Naming Service
	    NameComponent nc = new NameComponent("MessageCallback", "");
	    NameComponent path[] = {nc};
	    ncRef.rebind(path, msgRef);
       System.out.println("Obiekt MessageServant gotowy...czekam\n"); 
	    // oczekiwanie na wywoanie klienta
       java.lang.Object sync = new java.lang.Object();
       synchronized (sync) {
         sync.wait();
        }
 
	} catch (Exception e) {
	    System.err.println("ERROR: " + e);
	    e.printStackTrace(System.out);
	}
    }
}
 
