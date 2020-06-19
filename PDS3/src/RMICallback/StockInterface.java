package RMICallback;/*
 *  Koszalin 2004
 *  Interfejs metod zdalnych serwera
 *  Dariusz Rataj (C)
 */

import java.rmi.*;

public interface StockInterface extends java.rmi.Remote {
  /* rejestracja obiektu klienta na licie do aktualizacji */
  void regCallback(StockUpdate obj) throws RemoteException;
  /* wyrejestrowanie obiektu klienta */
  void unregCallback(StockUpdate obj) throws RemoteException;
}
