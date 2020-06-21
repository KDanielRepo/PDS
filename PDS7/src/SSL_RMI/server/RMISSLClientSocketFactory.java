package SSL_RMI.server;

import java.io.*;
import java.net.*;
import java.rmi.server.*;
import javax.net.ssl.*;

public class RMISSLClientSocketFactory implements RMIClientSocketFactory, Serializable {

    public Socket createSocket(String host, int port) throws IOException {
        System.out.println("jestem w kliencie");
        SSLSocketFactory factory =
		(SSLSocketFactory)SSLSocketFactory.getDefault();
        System.out.println("jestem w kliencie 2");
	    SSLSocket socket = (SSLSocket)factory.createSocket(host, port);
	    System.out.println("Utworzono gniazdo SSL klienta...");
	    return socket;
	}
}
