import java.net.*;
import java.io.*;
import javax.net.ssl.*;
import javax.security.cert.X509Certificate;
import java.security.KeyStore;

public class SSLSocketClientWithAuth {

  public static void main(String[] args) throws Exception {
     String address = "127.0.0.1";
     int port = 443;

	try {
    if (args.length >= 1 ) address = args[0];
    System.out.println(" ---- Connection: " + address + " port: " + port + " -----");
    SSLSocketFactory factory = null;
	  try {
		SSLContext ctx;
		KeyManagerFactory kmf;
		KeyStore ks;
		char[] passphrase = "testpass".toCharArray(); 

		ctx = SSLContext.getInstance("TLS");
		kmf = KeyManagerFactory.getInstance("SunX509");
		ks = KeyStore.getInstance("JKS");

		ks.load(new FileInputStream("clientstore"), passphrase);

		kmf.init(ks, passphrase);
		ctx.init(kmf.getKeyManagers(), null, null);

		factory = ctx.getSocketFactory();
	 } catch (Exception e) {
		throw new IOException(e.getMessage());
	  }

	   SSLSocket socket = (SSLSocket)factory.createSocket(address, port);

       socket.startHandshake();

	   PrintWriter out = new PrintWriter(new BufferedWriter(
                             new OutputStreamWriter( socket.getOutputStream())));
	   BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                             
       String message = "K: Hello I am SSLSocketClientWithAuth";
       out.println(message);
       System.out.println(message);
	    out.println();
	    out.flush();
	    if (out.checkError()) System.out.println("SSL I/O: java.io.PrintWriter error");
	    
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
	    System.out.println(e.toString());
	}
    }
}
