package POAServantActivator;

import org.omg.CORBA.ORB;
import org.omg.CosNaming.*;

import POAServantActivator.test.*;

public class Client {
    public Client(String[] args) {
     try {
         args = new String[4];
         args[0] = "-ORBInitialPort";
         args[1] = "1035";
         args[2] = "-ORBInitialHost";
         args[3] = "127.0.0.1";
  	    ORB orb = ORB.init(args, null);
	    NamingContext rootContext = NamingContextHelper.narrow(
                orb.resolve_initial_references("NameService"));

       System.out.println("\n Client: pobranie referencji obiektu");

       // pobranie zdalnej referencji obiektu
	    NameComponent name[] = {new NameComponent("TesterServer", "")};
	    Tester testRef = TesterHelper.narrow(rootContext.resolve(name));

       System.out.println(" Client: Wywolanie metody zdalnej ...");
       // wywolanie metody zdalnej getMessage
	    System.out.println(testRef.getMessage());
        } catch (Exception e) {
            System.err.println("\n Client: exception: " + e);
            e.printStackTrace();
        }
    }
    public static void main(String [] args) {
	   new Client(args);
    }
}