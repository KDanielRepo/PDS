package POAServantLocator;

import org.omg.CORBA.ORB;
import org.omg.CosNaming.*;

import POAServantLocator.test.*;

public class Client {
    public Client(String[] args) {
      try {
          args = new String[4];
          args[0] = "-ORBInitialPort";
          args[1] = "1035";
          args[2] = "-ORBInitialHost";
          args[3] = "127.0.0.1";
       ORB orb = ORB.init(args, System.getProperties());
	    NamingContext rootContext = NamingContextHelper.narrow(
                orb.resolve_initial_references("NameService"));

       // Pobranie referencji obiektu zdalnego 
	    NameComponent name[] = {new NameComponent("TesterServer", "")};
	    Tester testRef = TesterHelper.narrow(rootContext.resolve(name));
       System.out.println("\n Client: Pobranie referencji obiektu zdalnego");

       // wywolanie metody zdalnej getMessage
       System.out.println(" Client: metoda zdalna getMessage:");
	    System.out.println(testRef.getMessage());
        } catch (Exception e) {
            System.err.println("\nClient: exception: " + e);
            e.printStackTrace();
        }
    }

    public static void main(String [] args) {
	   new Client(args);
    }
}
