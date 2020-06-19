package POADirectory;

import POADirectory.dirinfo.Directory;
import POADirectory.dirinfo.DirectoryHelper;
import org.omg.CORBA.*;
import org.omg.CosNaming.*;
import org.omg.CosNaming.NamingContextPackage.*;

public class FilesClient {

	public static void main(String args[]) {
		try {

			// inicjalizacja orb
			args = new String[4];
			args[0] = "-ORBInitialPort";
			args[1] = "1035";
			args[2] = "-ORBInitialHost";
			args[3] = "127.0.0.1";
			ORB orb = ORB.init(args, null);
	
			// pobranie glownego kontekstu uslugi nazw
			org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
			NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);

			// pobranie referencji z uslugi nazw
			String name = "Dir";
			Directory impl = DirectoryHelper.narrow(ncRef.resolve_str(name));

			// wykorzystanie (wywolanie) metod getDirList, getFileInfo
			System.out.println(" --------------------------------------- ");
			String subdir = "/"; // listowany katalog
			System.out.println(" Zawartosc katalogu " + subdir + " zdalnego hosta: ");
			String dlist[] = impl.getDirList(subdir);
			for (int i=0; i<dlist.length; i++)
			{
				System.out.println(" " + dlist[i]);
			}
			System.out.println(" --------------------------------------- ");
			subdir = "/mojepliki/"; // listowany katalog
			System.out.println(" Zawartosc katalogu " + subdir + " zdalnego hosta: ");
			dlist = impl.getDirList(subdir);
			for (int i=0; i<dlist.length; i++)
			{		
				System.out.println(" " + dlist[i]);
			}
			System.out.println(" --------------------------------------- ");
			String filename = "/dirinfo.idl"; // sprawdzany plik
			System.out.println(" Informacje o pliku " + filename + ": ");
			System.out.println(impl.getFileInfo(filename));
			System.out.println(" --------------------------------------- ");
			filename = "/http"; // sprawdzany plik
			System.out.println(" Informacje o pliku " + filename + ": ");
			System.out.println(impl.getFileInfo(filename));
			System.out.println(" --------------------------------------- ");
		} catch (Exception ex) {
			System.out.println(" !!! Blad : " + ex) ;
		}
	} // main
} // FilesClient