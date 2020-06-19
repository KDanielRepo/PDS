package POASaveObject;

import org.omg.CORBA.ORB;
import org.omg.CosNaming.*;

import POASaveObject.dbtest.*;

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
	    NameComponent name[] = {new NameComponent("dbFaceObject", "")};
	    dbFace dbFaceRef = dbFaceHelper.narrow(rootContext.resolve(name));
       System.out.println("\n Client: Pobranie referencji obiektu zdalnego");

       // wywolanie metod zdalnych getLastQuery i getName 
       System.out.println(" Client: metoda zdalna getLastQuery(): " + dbFaceRef.getLastQuery());
       System.out.println(" Client: metoda zdalna getName(id):");
        for (int i=1; i<=3; i++) {
          System.out.println(" id = " + i + ": name = " + dbFaceRef.getName(i));
        } // for 
       } catch (Exception e) {
            System.err.println(" Client: exception: " + e);
            e.printStackTrace();
        }
    }

    public static void main(String [] args) {
	  Client servantLocatorClient = new Client(args);
    }
}
