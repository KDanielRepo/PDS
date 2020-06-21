package DateRmi;/*
 *  Koszalin 2002
 *  DateServer.java
 *  Serwer udostepniajacy zdalny obiekt RMI
 *  Dariusz Rataj (C)
 */

import java.rmi.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.*;

public class DateServer {

    public static void main(String[] args) {
        try {
            LocateRegistry.createRegistry(1099);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        System.setProperty("java.rmi.server.hostname","127.0.0.1");
        System.setProperty("java.security.policy", "C:\\PDS\\PDS3\\src\\DateRmi\\java.mypolicy");
        System.setSecurityManager(new RMISecurityManager());

        try {
            /* utworzenie instancji zdalnego obiektu */
            DateImplementation robject = new DateImplementation();

            /* wystawienie (rejestracja) obiektu */
            Naming.rebind("DateObject", robject);

            System.out.println("Obiekt DateObject przygotowany");
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Blad aktywacji obiektu" + ex);
        }
    }

} // DateServer 
