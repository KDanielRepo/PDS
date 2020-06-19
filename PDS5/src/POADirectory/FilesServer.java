package POADirectory;

import POADirectory.dirinfo.Directory;
import org.omg.CORBA.*;
import org.omg.CosNaming.*;
import org.omg.PortableServer.*;
import org.omg.CosNaming.NamingContextPackage.*;

public class FilesServer {
	
	public static void main(String args[]) {
		try{

			// inicjalizacja orb
			args = new String[4];
			args[0] = "-ORBInitialPort";
			args[1] = "1035";
			args[2] = "-ORBInitialHost";
			args[3] = "127.0.0.1";
			ORB orb = ORB.init(args, null);

			// utworzenie instancji obiektu zdalnego (servant)
			DirectoryImpl impl = new DirectoryImpl("c:/pub");

			// pobranie referencji do obiektu RootPOA i aktywacja implementacji obiektu
			POA rootpoa = POAHelper.narrow(orb.resolve_initial_references("RootPOA"));
			rootpoa.activate_object(impl);

			// pobranie glownego kontekstu uslugi nazw
			org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
			NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);

			// wiazanie referencji obiektu z nazwa w usludze nazw
			Directory href = impl._this();
			NameComponent path[] = ncRef.to_name("Dir");
			ncRef.rebind(path, href);

			// Aktywacja RootPOA
			rootpoa.the_POAManager().activate();
			System.out.println(" DirectoryServer gotowy ...");
			orb.run();
		} catch (Exception ex) {
			System.out.println(" !!! Blad : " + ex) ;
		}
		System.out.println("DirectoryServer zakonczony ....");
	} // main
} // FilesSerwer