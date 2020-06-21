package SSL_RMI.server;

import java.net.InetAddress;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.RMISecurityManager;
import java.rmi.server.UnicastRemoteObject;

public class HelloImpl extends UnicastRemoteObject implements Hello {

    public HelloImpl() throws RemoteException {
        super(0, new RMISSLClientSocketFactory(), new RMISSLServerSocketFactory());
    }

    public String sayHello(String message) {
        System.out.println(" Message: " + message);
        System.out.println("------------------------");
        return "Server: Hello!";
    }

    public static void main(String args[]) {

        System.setProperty("java.security.policy","C:\\PDS\\PDS7\\src\\SSL_RMI\\server\\java.mypolicy");
        //System.setProperty("java.rmi.server.hostname", "127.0.0.1");
        System.setSecurityManager(new RMISecurityManager());

        try {
            HelloImpl obj = new HelloImpl();

            Naming.rebind("HelloObject", obj);

            System.out.println("HelloObject zainstalowany... czekam ...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
