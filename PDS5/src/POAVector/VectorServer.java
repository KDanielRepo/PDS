package POAVector;

import POAVector.vtools.*;
import org.omg.CORBA.*;
import org.omg.CosNaming.*;
import org.omg.PortableServer.*;
import org.omg.CosNaming.NamingContextPackage.*;

public class VectorServer {

  public static void main(String args[]) {
    try{
      // inicjalizacja orb
      args = new String[4];
      args[0] = "-ORBInitialPort";
      args[1] = "1035";
      args[2] = "-ORBInitialHost";
      args[3] = "127.0.0.1";
      ORB orb = ORB.init(args, null);

      // utworzenie instancji obiektow zdalnych (servants)
      CounterImpl impl1 = new CounterImpl();
      VectorImpl impl2 = new VectorImpl();

      // pobranie referencji do obiektu RootPOA i aktywacja implementacji obiektu
      POA rootpoa = POAHelper.narrow(orb.resolve_initial_references("RootPOA"));
      POAManager mgr = rootpoa.the_POAManager();
      
      // Utworzenie dodatkowych POA
      Policy[] ps = {
       rootpoa.create_lifespan_policy( LifespanPolicyValue.PERSISTENT)
       /* , rootpoa.create_thread_policy(ThreadPolicyValue.SINGLE_THREAD_MODEL) */
      };
      
      POA childPOA1 = rootpoa.create_POA("ChildPOA1", null, null);
      POA childPOA2 = rootpoa.create_POA("ChildPOA2", null, ps);
      childPOA1.activate_object(impl1);
      childPOA2.activate_object(impl2);

      // pobranie glownego kontekstu uslugi nazw
      org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
      NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);

      // wiazanie referencji obiektu z nazwa w usludze nazw
      NameComponent path1[] = ncRef.to_name("CounterObject");
      NameComponent path2[] = ncRef.to_name("VectorObject");

      ncRef.rebind(path1, childPOA1.servant_to_reference(impl1));
      ncRef.rebind(path2, childPOA2.servant_to_reference(impl2));

      childPOA1.the_POAManager().activate();
      childPOA2.the_POAManager().activate();
     
      // Aktywacja RootPOA
      System.out.println(" VectorServer gotowy ...");
      orb.run();
    } catch (Exception ex) {
        System.out.println(" !!! Blad : " + ex) ;
    }
    System.out.println("VectorServer zakonczyl prace ....");
  } // main
} // VectorSerwer
