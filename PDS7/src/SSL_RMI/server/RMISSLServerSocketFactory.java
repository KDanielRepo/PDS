package SSL_RMI.server;

import java.io.*;
import java.net.*;
import java.rmi.server.*;
import javax.net.ssl.*;
import java.security.*;
import javax.net.*;

public class RMISSLServerSocketFactory
        implements RMIServerSocketFactory, Serializable {

    public ServerSocket createServerSocket(int port)
            throws IOException {
        SSLServerSocketFactory ssf = null;
        try {
            // set up key manager to do server authentication
            SSLContext ctx;
            KeyManagerFactory kmf;
            KeyStore ks;
            char[] passphrase = "testpass".toCharArray();

            ctx = SSLContext.getInstance("TLS");
            kmf = KeyManagerFactory.getInstance("SunX509");
            ks = KeyStore.getInstance("JKS");

            ks.load(new FileInputStream("C:\\PDS\\PDS7\\src\\SSL_RMI\\gen_key\\serverstore"), passphrase);
            kmf.init(ks, passphrase);
            ctx.init(kmf.getKeyManagers(), null, null);

            ssf = ctx.getServerSocketFactory();
            System.out.println("Utworzono gniazdo SSL serwera...");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ssf.createServerSocket(port);
    }
}
