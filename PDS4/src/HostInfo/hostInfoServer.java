package HostInfo;

import java.util.*; // Date
import java.net.*;  // InetAddres

import HostInfo.hostinfo.*;

import org.omg.CosNaming.*;  // naming service
import org.omg.CORBA.*;      // klasy corba
import HostInfo.hostinfo.*;


/* implementacja obiektu zdalnego */
class hostInfoServant extends _hostInfoImplBase {
    public String getDayTime()   // metoda zdalna
    {
        System.out.println("Wywolanie metody getDayTime()");
        return new Date().toString();
    }

    public String getAddress()   // metoda zdalna
    {
        String addr = "";
        try {
            InetAddress address = InetAddress.getLocalHost();
            addr = address.toString();
            System.out.println("Wywolanie metody getAddress()");
        } catch (UnknownHostException e) {
            System.err.println(e);
            addr = "Blad odczytu adresu!";
            System.out.println("Blad odczytu adresu!");
        }

        return addr;
    }
}

/* glowna klasa - aplikacja serwera */
public class hostInfoServer {

    public static void main(String args[]) {

        try {
            /* inicjalizacja ORB */

            //Narazie nie wiem jak uruchomic tnameserv z kodu, trzeba go odpalic po prostu z cmd...

            Properties props = new Properties();
            props.put("org.omg.CORBA.ORBInitialPort", "1050");
            props.put("org.omg.CORBA.ORBInitialHost", "127.0.0.1");

            ORB orb = ORB.init(args, props);
            System.out.println("ORB zainicjowany...");

            /* utworzenie obiektu zdalnego i rejestracja w ORB */
            hostInfoServant infoRef = new hostInfoServant();
            orb.connect(infoRef);
            System.out.println("Servant utworzony...");


            /* pobranie gwnego kontekstu usugi nazw */
            org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
            NamingContext ncRef = NamingContextHelper.narrow(objRef);

            /* Zwizanie referencji obiektu z nazw w Naming Service */
            NameComponent nc = new NameComponent("hostInfo", "");
            NameComponent path[] = {nc};
            ncRef.rebind(path, infoRef);
            System.out.println("Obiekt hostInfoServant w NS gotowy... czekam ...");
            System.out.println("-------------------------------------- ");

            /* oczekiwanie na wywoanie klienta */
            java.lang.Object sync = new java.lang.Object();
            synchronized (sync) {
                sync.wait();
            }

        } catch (Exception ex) {
            System.err.println("Blad: " + ex);
        }
    }
}

