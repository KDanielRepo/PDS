package POAServantLocator;

import org.omg.CORBA.*;
import org.omg.PortableServer.*;
import org.omg.PortableServer.ServantLocatorPackage.*;
import org.omg.CosNaming.*;

import POAServantLocator.test.*;

public class Server {
    public Server(String[] args) {
        try {
            args = new String[4];
            args[0] = "-ORBInitialPort";
            args[1] = "1035";
            args[2] = "-ORBInitialHost";
            args[3] = "127.0.0.1";
            ORB orb = ORB.init(args, System.getProperties());
            POA rootPoa = (POA) orb.resolve_initial_references("RootPOA");
            rootPoa.the_POAManager().activate();
           System.out.println("\nServer: aktywacja ORB i RootPOA");

            Policy poaPolicy[] = new Policy[2];
            poaPolicy[0] = rootPoa.create_servant_retention_policy(
                ServantRetentionPolicyValue.NON_RETAIN);
            poaPolicy[1] = rootPoa.create_request_processing_policy(
                RequestProcessingPolicyValue.USE_SERVANT_MANAGER);

            POA poa1 = rootPoa.create_POA("TesterPoa", null, poaPolicy);
            poa1.the_POAManager().activate();
            System.out.println(" Server: aktywacja " + poa1.the_name());

            poa1.set_servant_manager(new PoaServantLocator());
            System.out.println(" Server: zmiana Servant Manager'a dla " +  poa1.the_name());

            // przygotowanie referencji bez aktywacji obiektu
            // referencja bedzie przekazana do klienta
            // instancja zostanie utworzona po odwolaniu do referencji
            org.omg.CORBA.Object testRef = poa1.create_reference(
                TesterHelper.id());
            System.out.println(" Server: przygotowanie referencji");  

            NamingContext rootContext = NamingContextHelper.narrow(
                orb.resolve_initial_references("NameService"));
            NameComponent name[] = {new NameComponent("TesterServer", "")};
            rootContext.rebind(name, testRef);
            System.out.println(" Server: wiazanie referencji w Naming Service");
            System.out.println(" Server: Obiekt gotowy... czekam ...");
            orb.run();
        } catch (Exception e) {
            System.err.println("\n Server: exception: " + e);
            e.printStackTrace();
        }
    }

    public static void main(String [] args) {
        Server serverServantLocator = new Server(args) ;
    }
}

class PoaServantLocator extends LocalObject implements ServantLocator {
    public Servant preinvoke(byte[] oid, POA adapter,
                             String operation,
                             CookieHolder the_cookie) throws ForwardRequest {
        try {
            TesterServant servantObj = new TesterServant();
            System.out.println(" Locator: preinvoke(): operacja " + operation);
            return servantObj;
        } catch (Exception e) {
           System.err.println(" postinvoke: exception: " + e); 
        }
        return null;
    }

    public void postinvoke(byte[] oid, POA adapter,
                           String operation,
                           java.lang.Object the_cookie,
                           Servant the_servant) {
        try {
          System.out.println(" Locator: postinvoke(): operacja " + operation);

        } catch (Exception e) {
           System.err.println(" postinvoke: exception: " + e); 
        }
    }
}

