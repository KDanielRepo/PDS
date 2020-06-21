package SSLSocket.SSL_client;

import java.net.*;
import java.io.*;
import javax.net.ssl.*;

public class SSLSocketClient {

    public static void main(String[] args) throws Exception {
     String address = "127.0.0.1";
     int port = 443;
	try {
	    if (args.length >= 1 ) address = args[0];
       System.out.println(" ---- Connection: " + address + " port: " + port + " -----");
       
	    SSLSocketFactory factory =
		(SSLSocketFactory)SSLSocketFactory.getDefault();
	    SSLSocket socket =
		(SSLSocket)factory.createSocket(address, port);

	    /*  rozpoczecie negocjacji - procedura Handshake - "uscisk dloni"
        *  wywolanie metody startHandshake nie jest konieczne
        */ 
	    socket.startHandshake();

	    PrintWriter out = new PrintWriter(new BufferedWriter(
                             new OutputStreamWriter( socket.getOutputStream())));
	    BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                             
       String message = "K: Hello I am SSLSocketClient - without authorization";
       out.println(message);
       System.out.println(message);
	    out.println();
	    out.flush();
	    if (out.checkError()) System.out.println(  "SSL IO: java.io.PrintWriter error");
	    
	    String inputLine = "";
	    inputLine = in.readLine();
	    System.out.println(inputLine);
	    
	    while ((inputLine = in.readLine()) != null)
                 		System.out.println(inputLine); 
		                 
       System.out.println(" ---- Client info: ----- \r\n" +socket.toString());
       System.out.println(" --------------- END --------------- \r\n");

	    in.close();
	    out.close();
	    socket.close();

	} catch (Exception e) {
	    e.printStackTrace();
	}
    }
}
