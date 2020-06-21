package HostInfoFile;

import java.util.*; // Date
import java.net.*;  // InetAddres
import java.io.*;

import HostInfo.hostinfo.*;
import org.omg.CosNaming.*;  // naming service
import org.omg.CORBA.*;      // klasy corba


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
public class hostInfoServerFile {
    public static void main(String args[]) {
        try {

            Properties props = new Properties();
            props.put("org.omg.CORBA.ORBInitialPort", "1050");
            props.put("org.omg.CORBA.ORBInitialHost", "127.0.0.1");

            ORB orb = ORB.init(args, props);

            // utworzenie obiektu zdalnego i rejestracja w ORB
            hostInfoServant infoRef = new hostInfoServant();
            orb.connect(infoRef);

            // zamiana obiektu na String IOR  i zapis IOR do pliku
            String str = orb.object_to_string(infoRef);
            //   String filename = System.getProperty("user.home")+
            //                      System.getProperty("file.separator")+"infoRef.ior";
            String filename = "infoRef.ior";
            System.out.println(filename);
            FileOutputStream fos = new FileOutputStream(filename);
            PrintStream ps = new PrintStream(fos);
            ps.print(str);
            ps.close();
            System.out.println(" Obiekt przygotowany ...");
            // oczekiwanie na wywoanie klienta
            java.lang.Object sync = new java.lang.Object();
            synchronized (sync) {
                sync.wait();
            }

        } catch (Exception ex) {
            System.err.println("Blad: " + ex);
        }
    }
}

