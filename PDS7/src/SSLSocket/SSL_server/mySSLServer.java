package SSLSocket.SSL_server;

import java.io.*;
import java.net.*;
import javax.net.*;
import java.security.KeyStore;
import javax.net.ssl.*;
import javax.security.cert.X509Certificate;

public class mySSLServer implements Runnable {

    private ServerSocket server = null;
    private int port = 443;
    private String type = "TLS";

  protected mySSLServer(ServerSocket ss)  {
	server = ss;
	newListener();
  }

  public static void main(String args[]) {
   int port = 443;
   String type = "TLS";
   System.out.println("Startuje serwer: port " + port);
   
	try {
	    ServerSocketFactory ssf = getServerSocketFactory(type);
	    ServerSocket ss = ssf.createServerSocket(port);
	    if (args.length > 0 && args[0].equals("true")) {
		((SSLServerSocket)ss).setNeedClientAuth(true);
	    }
	    new mySSLServer(ss);
	 } catch (IOException e) {
	    System.out.println("Blad podczas uruchamiania Serwera: " + e.getMessage());
	    e.printStackTrace();
	 }
   }

   private static ServerSocketFactory getServerSocketFactory(String type) {
	if (type.equals("TLS")) {
	    SSLServerSocketFactory ssf = null;
      try {
		SSLContext ctx;
		KeyManagerFactory kmf;
		KeyStore ks;
		char[] passphrase = "testpass".toCharArray();

		ctx = SSLContext.getInstance("TLS");
		kmf = KeyManagerFactory.getInstance("SunX509");
		ks = KeyStore.getInstance("JKS");		
		ks.load(new FileInputStream("SSLSocket/gen_keys_certs_RSA/serverstore"), passphrase);
		kmf.init(ks, passphrase);
		ctx.init(kmf.getKeyManagers(), null, null);

		ssf = ctx.getServerSocketFactory();
		return ssf;
	    } catch (Exception e) {
		e.printStackTrace();
	    }
	} else {
	    return ServerSocketFactory.getDefault();
	}
	return null;
  }


   /**
    * Metoda run - nasluch i obsluga klienta
    */
   public void run() {
   	Socket socket;

	try {
	   socket = server.accept();
	} catch (IOException e) {
	    System.out.println("Class Server died: " + e.getMessage());
	    e.printStackTrace();
	    return;
      }

	newListener();

	try {
        DataOutputStream out = new DataOutputStream(socket.getOutputStream());
    try {
		BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		String line = in.readLine();
        System.out.println(line);
        String message = "";
		try {
          message = "S: Hello I am mySSLServer\n";
          out.writeBytes(message);
		  out.flush();
          System.out.println(message);
          out.writeBytes(" ---- Server info: ----- \r\n" +socket.toString());
		} catch (IOException ie) {
		    ie.printStackTrace();
		    return;
		  }
	    } catch (Exception e) {
  		   e.printStackTrace();
		   out.writeBytes("Nie jestes dobrym klientem!");
		   out.flush();
	    }

	} catch (IOException ex) {
	    System.out.println("error writing response: " + ex.getMessage());
	    ex.printStackTrace();

	} finally {
	    try {
		socket.close();
	    } catch (IOException e) {
	    }
	}
    }

    /**
     * Nowy wtek nasluchujacy
     */
    private void newListener()  {
     (new Thread(this)).start();
    }
}
