package DBrmi;/*
 *  Koszalin 2003
 *  DBServer.java
 *  Serwer udostepniajacy zdalny obiekt RMI oblugi baz danych
 *  Dariusz Rataj (C)
 */

import java.rmi.Naming;
import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

public class DBServer {

 public static void main(String[] args) {

     try {
         LocateRegistry.createRegistry(1099);
     } catch (RemoteException e) {
         e.printStackTrace();
     }
     System.setProperty("java.rmi.server.hostname","127.0.0.1");
     System.setProperty("java.security.policy", "C:\\PDS\\PDS3\\src\\DBrmi\\java.mypolicy");
     System.setSecurityManager(new RMISecurityManager());

   try {
   
    /* utworzenie instancji zdalnego obiektu */
    DBImplementation robject = new DBImplementation();
    
    /* wystawienie (rejestracja) obiektu */
     //Naming.rebind("rmi://localhost/DatabaseObject", robject);
    Naming.rebind("DatabaseObject", robject);

    System.out.println("Obiekt DatabaseObject przygotowany... czekam...");
   } catch (Exception ex)  { 
      System.out.println("Blad aktywacji obiektu: " + ex); 
     }
 }
 
} // DBServer 
