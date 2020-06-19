package POASaveObject;

import org.omg.CORBA.*;
import org.omg.PortableServer.*;
import org.omg.PortableServer.ServantLocatorPackage.*;
import org.omg.CosNaming.*;
import java.io.*; 

import POASaveObject.dbtest.*;

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

            POA poa1 = rootPoa.create_POA("dbPoa", null, poaPolicy);
            poa1.the_POAManager().activate();
            System.out.println("Server: aktywacja " + poa1.the_name());

            poa1.set_servant_manager(new PoaServantLocator());
            System.out.println("Server: zmiana Servant Manager'a dla " +  poa1.the_name());

            org.omg.CORBA.Object dbFaceRef = poa1.create_reference(
                dbFaceHelper.id());
            System.out.println("Server: przygotowanie referencji");

            NamingContext rootContext = NamingContextHelper.narrow(
                orb.resolve_initial_references("NameService"));
            NameComponent name[] = {new NameComponent("dbFaceObject", "")};
            rootContext.rebind(name, dbFaceRef);
            System.out.println("Server: wiazanie referencji w Naming Service");

            System.out.println("Server: Obiekt gotowy... czekam ...");
            orb.run();
        } catch (Exception e) {
            System.err.println("\nServer: exception: " + e);
            e.printStackTrace();
        }
    }

    public static void main(String [] args) {
      new Server(args) ;
    }
}

class PoaServantLocator extends LocalObject implements ServantLocator {

    public Servant preinvoke(byte[] oid, POA adapter,
                             String operation,
                             CookieHolder the_cookie) throws ForwardRequest {
        try {
            System.out.println(" Locator: preinvoke(): operacja " + operation);
            DBServant servantObj = new DBServant();
            loadServant(servantObj);
            System.out.print(" Locator: ");
            /* jezeli wywolanie metody getName utworzenie polaczenia z baza*/
            if (operation.equals("getName")) servantObj.connect();
            return servantObj;
        } catch (Exception e) {
            System.err.println("preinvoke: Caught exception - " + e);
        }
        return null;
    }

    public void postinvoke(byte[] oid, POA adapter,
                           String operation,
                           java.lang.Object the_cookie,
                           Servant the_servant) {
        try {
            System.out.println(" Locator: postinvoke(): operacja " + operation);
            DBServant servantObj = (DBServant)the_servant;
            System.out.print(" Locator: ");
            /* jezeli wywolanie metody getName zamkniecie polaczenia z baza*/
            if (operation.equals("getName"))servantObj.disconnect();
            saveServant(servantObj);
        } catch (Exception e) {
            System.err.println(" postinvoke: Caught exception - " + e);
        }
    }

   /* zapis do pliku stanu servant typu DBServant */
   public void saveServant(DBServant servant){
    try {
     System.out.println(" Locator: saveServant(): zapis stanu obiektu\n");
     ObjectOutputStream o = new ObjectOutputStream( new FileOutputStream("DBServant.out")); 
     o.writeObject(servant.getDriver()); 
     o.writeObject(servant.getUrl()); 
     o.writeObject(servant.getLastQuery()); 
     o.close(); 
    } catch (Exception e) { 
      System.err.println(" postinvoke: exception: " + e); 
     } 
   }

   /* odtworzenie z pliku stanu servant typu DBServant */
   public void loadServant(DBServant servant){
    try {
     System.out.println(" Locator: loadServant(): odtworzenie stanu obiektu");
     ObjectInputStream o = new ObjectInputStream( new FileInputStream("DBServant.out")); 
     servant.setDriver((String)o.readObject()); 
     servant.setUrl((String)o.readObject()); 
     servant.setLastQuery((String)o.readObject()); 
     o.close(); 
    } catch (Exception e) { System.err.println(" postinvoke: exception: " + e); } 
   } 
}

