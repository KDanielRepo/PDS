package HostInfoFile;

import java.io.*;
import java.util.Properties;

import HostInfoFile.hostinfo.*;           // klasy skladowe zdalnego iterfejsu
import org.omg.CosNaming.*;  // naming service
import org.omg.CORBA.*;      // klasy corba

/* glowna klasa - aplikacja klienta */
public class hostInfoClientFile {
    public static void main(String args[]) {
        try {

            Properties props = new Properties();
            props.put("org.omg.CORBA.ORBInitialPort", "1050");
            props.put("org.omg.CORBA.ORBInitialHost", "127.0.0.1");

            ORB orb = ORB.init(args, props);

            // odczyt String-IOR z pliku i zamiana IOR na obiekt
            //   String filename = System.getProperty("user.home")+
            //                    System.getProperty("file.separator")+"infoRef.ior";
            String filename = "infoRef.ior";
            BufferedReader br = new BufferedReader(new FileReader(filename));
            String ior = br.readLine();
            org.omg.CORBA.Object obj = orb.string_to_object(ior);
            hostInfo infoRef = hostInfoHelper.narrow(obj);

            // wywolanie zdalnych metod obiektu zdalnego
            String address = infoRef.getAddress();
            String daytime = infoRef.getDayTime();
            System.out.println(" ------------------------------------------------------------- ");
            System.out.println("     Zdalny obiekt zwraca adres: " + address);
            System.out.println("   Zdalny obiekt zwraca daytime: " + daytime);
            System.out.println(" ------------------------------------------------------------- ");

        } catch (Exception ex) {
            System.err.println("Blad: " + ex);
        }
    }
}


