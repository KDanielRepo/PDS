package NamespaceTree;

import org.omg.CORBA.*;
import org.omg.CosNaming.*; 	 

public class CreateTree 
{ 	 
	public static void main(String args[])	 
	{ 	 
		try {
			args = new String[4];
			args[0] = "-ORBInitialPort";
			args[1] = "1050";
			args[2] = "-ORBInitialHost";
			args[3] = "127.0.0.1";
			ORB orb = ORB.init(args, null);	 
			NamingContext ctx =	 
				NamingContextHelper.narrow(orb.resolve_initial_references("NameService")); 	 
		
			// Obiekt objref tworzony formalnie (Nie nalezy sie do niego odwolywac!) 	 
			// w tym miejscu nalezy utworzyc referencje wlasnego obiektu	 
			org.omg.CORBA.Object objref = ctx; 	 
			
			// dodanie obiektu do glownego kontekstu	 
			NameComponent nc1 = new NameComponent("carPrice", "text");	 
			NameComponent[] name1 = {nc1};	 
			ctx.rebind(name1, objref);	 
			System.out.println("Dodano alias carPrice do glownego kontekstu"); 	 

			// nowy kontekst (MyObjects) do glownego kontekstu	 
			NameComponent nc2 = new NameComponent("MyObjects", "directory");	 
			NameComponent[] name2 = {nc2};	 
			NamingContext ctx2 = ctx.bind_new_context(name2);	 
			System.out.println("Dodano nowy kontekst (MyObjects) do glownego kontekstu"); 	 
			
			// dodanie obiektu do kontekstu MyObjects	 
			NameComponent nc3 = new NameComponent("hostInfo", "text");	 
			NameComponent[] name3 = {nc3};	 
			ctx2.rebind(name3, objref);	 
			System.out.println("Dodano alias hostInfo do kontekstu MyObjects"); 	 
			
			// dodanie obiektu do kontekstu MyObjects	 
			NameComponent nc4 = new NameComponent("DatabaseObj", "text");	 
			NameComponent[] name4 = {nc4};	 
			ctx2.rebind(name4, objref);	 
			System.out.println("Dodano alias DatabaseObj do kontekstu MyObjects"); 	 
			
			// nowy kontekst (OfficeObjects) do glownego kontekstu	 
			NameComponent nc5 = new NameComponent("OfficeObjects", "directory");	 
			NameComponent[] name5 = {nc5};	 
			NamingContext ctx3 = ctx.bind_new_context(name5);	 
			System.out.println("Dodano nowy kontekst (OfficeObjects) do glownego kontekstu"); 	 
			
			// dodanie obiektu do kontekstu OfficeObjects	 
			NameComponent nc6 = new NameComponent("BossInfo", "text");	 
			NameComponent[] name6 = {nc6};	 
			ctx3.rebind(name6, objref);	 
			System.out.println("Dodano alias BossInfo do kontekstu OfficeObjects"); 	 
			
			// dodanie obiektu do kontekstu OfficeObjects 	 
			NameComponent nc7 = new NameComponent("BossDatabase", "text");	 
			NameComponent[] name7 = {nc7};	 
			ctx3.rebind(name7, objref);	 
			System.out.println("Dodano alias BossDatabase do kontekstu OfficeObjects");	 
		} catch (Exception e) {	 
			e.printStackTrace(System.err);	 
		}	 
	} 		 
} 			 
