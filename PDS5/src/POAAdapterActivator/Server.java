package POAAdapterActivator;

import POAAdapterActivator.test.Tester;
import POAAdapterActivator.test.TesterHelper;
import org.omg.CORBA.*;
import org.omg.CosNaming.*;
import org.omg.PortableServer.*;
import org.omg.CosNaming.NamingContextPackage.*;



public class Server {

    public void run(String[] args) {
        try {
            args = new String[4];
            args[0] = "-ORBInitialPort";
            args[1] = "1035";
            args[2] = "-ORBInitialHost";
            args[3] = "127.0.0.1";
            ORB orb = ORB.init(args, null);
            POA rootPOA = POAHelper.narrow(
                orb.resolve_initial_references("RootPOA"));
            System.out.println("\n Server: aktywacja ORB i RootPOA");    

            rootPOA.the_activator(new MyAdapterActivator());
            System.out.println(" Server: ustawienie aktyatora POA dla RootPOA");

            // utworzenie childPOA z parametrem true - aktywator RootPOA
            // zostanie wywolany gdy poszukiwany TesterPOA nie istnieje
            POA childPOA = rootPOA.find_POA("TesterPOA", true);

            // utworzenie referencji obiektu i wiazanie w Naming Service
            org.omg.CORBA.Object obj = childPOA.id_to_reference("alamakota".getBytes());
            Tester testRef = TesterHelper.narrow(obj);

            NamingContext namingContext = NamingContextHelper.narrow(
                orb.resolve_initial_references("NameService"));
            NameComponent[] nc = { new NameComponent("TesterServer", "") };
            namingContext.rebind(nc, testRef);
            System.out.println(" Server: wiazanie referencji w Naming Service");

            // niszczenie TesterPOA, ktory zostanie odtworzony gdy broker odwola
            // sie do niego
            childPOA.destroy(true, true);
            System.out.println(" Server: usuniecie TesterPOA");

            // aktywacja rootPOA
            rootPOA.the_POAManager().activate();

            System.out.println(" Server: obiekt gotowy ... czekam ...");
            orb.run();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Server().run(args);
    }
}

class MyAdapterActivator extends LocalObject implements AdapterActivator {

    public boolean unknown_adapter(POA parent, String name) {
        System.out.println(" Activator: unknown_adapter() wywolana dla POA: " + name);
        try {
            // odtworzenie POA z ustawion polityka
            Policy[] policy = new Policy[3];
            policy[0] = parent.create_lifespan_policy(
                LifespanPolicyValue.TRANSIENT);
            policy[1] = parent.create_id_assignment_policy(
                IdAssignmentPolicyValue.USER_ID);
            policy[2] = parent.create_implicit_activation_policy(
                ImplicitActivationPolicyValue.NO_IMPLICIT_ACTIVATION);

            POA child = parent.create_POA(name, null, policy);
            System.out.println(" Activator: odtworzenie " + name);

            // powiazanie servant z POA
            TesterServant tester = new TesterServant();
            child.activate_object_with_id("alamakota".getBytes(), tester);
            System.out.println(" Activator: utworzenie obiektu servant");

            // aktywacja POA
            child.the_POAManager().activate();
            System.out.println(" Activator: aktywacja " + name);
            
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }
}
