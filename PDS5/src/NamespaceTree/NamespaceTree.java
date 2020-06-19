package NamespaceTree;

import java.util.Properties;
import org.omg.CORBA.*; 	 
import org.omg.CosNaming.*; 	 

public class NamespaceTree 	 
{ 	 
	// odczyt i "rysowanie" zawartoci wza, jeeli skadowa jest kontekstem
	// metoda printNode wywoa sam siebie dla kolejnego wza (wywoanie iteracyjne)
	public void printNode(ORB orb, NamingContext nc, int level){ 	 
		try {	 
			BindingListHolder blh = new BindingListHolder();	 
			BindingIteratorHolder blIt= new BindingIteratorHolder(); 	 
			
			// pobranie listy dowiazanych skladowych do biezacego kontekstu	 
			nc.list(1000, blh, blIt);	 
			Binding bindings[] = blh.value; 	 
			
			if (bindings.length == 0) return; 	 
			
			for (int i=0; i < bindings.length; i++) {
			 	 
				// pobranie referencji obiektu (lub kontekstu)	 
				org.omg.CORBA.Object obj = nc.resolve(bindings[i].binding_name); 	 
				int lastIx = bindings[i].binding_name.length-1; 	 
				for (int j=0; j<=level; j++) System.out.print(" "); // odstep 	 
					// jezeli dowiazanie jest typu ncontext (kontekst), j. nie jest to obiekt 	 
					if (bindings[i].binding_type == BindingType.ncontext) {	 
						System.out.println("|- " + bindings[i].binding_name[lastIx].id+
							"("+bindings[i].binding_name[lastIx].kind+")");	 
						NamingContext ncx = NamingContextHelper.narrow(obj); 	 
						printNode(orb, ncx, level+1); // wyw. rekurencyjne	 
					} else {	 
						System.out.println("|- " + bindings[i].binding_name[lastIx].id+	 
							"("+bindings[i].binding_name[lastIx].kind+")");	 
					} // if	 
				} // for	 
		} catch (Exception e) {	 
			e.printStackTrace(System.err);	 
		}	 
	} 	 
	public void printBegin(String args[]){ 	 
		try {
			args = new String[4];
			args[0] = "-ORBInitialPort";
			args[1] = "1035";
			args[2] = "-ORBInitialHost";
			args[3] = "127.0.0.1";
			ORB orb = ORB.init(args, null);	 
			NamingContext nc =	 
			NamingContextHelper.narrow(orb.resolve_initial_references("NameService"));	 
			System.out.println(" root ctx");	 
			printNode(orb, nc, 0);	 
		} catch (Exception e) {	 
			e.printStackTrace(System.err);	 
		}	 
	} 	 
	public static void main(String args[]) {	 
		System.out.println("------------------------------------------------------");	 
		NamespaceTree ntree = new NamespaceTree();	 
		ntree.printBegin(args); // poczatek rysowania drzewa	 
		System.out.println("------------------------------------------------------");	 
	} 	 
} // NamespaceTree