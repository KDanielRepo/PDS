package InfoActivate.client;/*
 *  Koszalin 2003
 *  InfoClient.java
 *  Klient wywolujacy zdalna metode zdalnego obiektu RMI
 *  Dariusz Rataj (C)
 */

import InfoActivate.serwer.InfoInterface;

import java.rmi.*;
import java.rmi.registry.LocateRegistry;

public class InfoClient {

    public static void main(String[] args) {
        System.setProperty("java.rmi.server.hostname", "127.0.0.1");
        System.setProperty("java.security.policy", "C:\\PDS\\PDS3\\src\\InfoActivate\\client\\java.mypolicy");
        System.setSecurityManager(new SecurityManager());
        try {

            /* utworzenie obiektu zdalnego */
            InfoInterface robject = (InfoInterface) Naming.lookup("rmi://127.0.0.1:1099/InfoObject");

            System.out.println(" ------------ Info pobrane z obiektu ----");
            /* wywolanie metod zdalnych */
            String date = robject.getDate();
            System.out.println("Data na zdalnym hoscie: " + date);
            String os = robject.getOSInfo();
            System.out.println("  OS na zdalnym hoscie: " + os);
            String jvm = robject.getJVMInfo();
            System.out.println("  VM na zdalnym hoscie: " + jvm);

            System.out.println(" ----------------------------------------");
        } catch (Exception ex) {
            System.err.println("Blad wywolania obiektu: " + ex);
        }
    } // main

} // InfoClient   


