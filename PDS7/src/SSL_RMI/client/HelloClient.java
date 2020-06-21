package SSL_RMI.client;
import SSL_RMI.server.Hello;

import java.net.InetAddress;
import java.rmi.Naming;
import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;

public class HelloClient {

    public static void main(String args[]) {
	try {
        System.setProperty("javax.net.ssl.trustStore","C:\\PDS\\PDS7\\src\\SSL_RMI\\gen_key\\cacerts");
        System.setProperty("javax.net.ssl.trustStorePassword","testpass");    

        if (System.getSecurityManager() == null)
            System.setProperty("java.security.policy","C:\\PDS\\PDS7\\src\\SSL_RMI\\client\\java.mypolicy");
         System.setSecurityManager(new SecurityManager());

	    Hello obj = null;
	    obj = (Hello)Naming.lookup("rmi://127.0.0.1:1099/HelloObject");

	    String message = obj.sayHello("Client: Hallo!");
	    System.out.println(" Message: " + message);
        System.out.println("------------------------");
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }
}
