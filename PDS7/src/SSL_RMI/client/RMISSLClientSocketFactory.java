package SSL_RMI.client;

import java.io.*;
import java.net.*;
import java.rmi.server.*;
import javax.net.ssl.*;

public class RMISSLClientSocketFactory implements RMIClientSocketFactory, Serializable {

    public Socket createSocket(String host, int port) throws IOException {
	    SSLSocketFactory factory =
		(SSLSocketFactory)SSLSocketFactory.getDefault();
	    SSLSocket socket = (SSLSocket)factory.createSocket(host, port);
		System.out.println("Utworzono gniazdo SSL klienta...");
	    return socket;
	}
}
