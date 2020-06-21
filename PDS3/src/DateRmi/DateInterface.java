package DateRmi;/*
 *  Koszalin 2002
 *  DateInterface.java
 *  Interfejs obiektu zdalnego RMI
 *  Dariusz Rataj (C)
 */

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface DateInterface extends Remote {

   /* deklaracja metody zdalnej */
   public String getDate() throws RemoteException;
   
 }  // DateInterface