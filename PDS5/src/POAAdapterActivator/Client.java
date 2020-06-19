package POAAdapterActivator;

import POAAdapterActivator.test.Tester;
import POAAdapterActivator.test.TesterHelper;
import org.omg.CORBA.ORB;
import org.omg.CosNaming.NamingContext;
import org.omg.CosNaming.NamingContextHelper;
import org.omg.CosNaming.NameComponent;


public class Client {

    public void run(String[] args) {
        try {
            args = new String[4];
            args[0] = "-ORBInitialPort";
            args[1] = "1035";
            args[2] = "-ORBInitialHost";
            args[3] = "127.0.0.1";
            ORB orb = ORB.init(args, null);
            System.out.println("\n Client: aktywacja ORB");    

            NamingContext namingContext = NamingContextHelper.narrow(
                orb.resolve_initial_references("NameService"));
            NameComponent[] nc = { new NameComponent("TesterServer", "") };

            // wywolanie metody zdalnej getMessage
            Tester testRef = TesterHelper.narrow(namingContext.resolve(nc));
            System.out.println("\n Client: wywolanie metody zdalnej getMessage");    

            System.out.println(testRef.getMessage());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Client().run(args);
    }
}
