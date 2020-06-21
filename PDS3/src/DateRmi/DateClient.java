package DateRmi;/*
 *  Koszalin 2002
 *  DateClient.java
 *  Klient wywolujacy zdalna metode zdalnego obiektu RMI
 *  Dariusz Rataj (C)
 */

import java.rmi.*;

public class DateClient {

    public static void main(String[] args) {

        /* ustawienie zarzadcy ochrony */
        System.setProperty("java.security.policy", "C:\\PDS\\PDS3\\src\\DateRmi\\java.mypolicy");
        System.setSecurityManager(new SecurityManager());
        try {

            /* utworzenie obiektu zdalnego */

            DateInterface robject = (DateInterface) Naming.lookup("rmi://localhost/DateObject");

            /* wywolanie metody zdalnej */
            String date = robject.getDate();

            System.out.println("Data na zdalnym hoscie: " + date);

        } catch (Exception ex) {
            System.out.println("Blad wywolania obiektu: " + ex);
        }
    } // main

} // DateClient   


