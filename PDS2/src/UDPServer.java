import java.io.IOException;
import java.net.*;

public class UDPServer {
    private int port = 5501;
    int in = 0;
    int out = 0;
    public UDPServer(int port) throws IOException {
        this.port = port;
        try {
            DatagramSocket serverSocket = new DatagramSocket(port);
            while (true) {
                byte[] buf = new byte[100];
                DatagramPacket datagramPacket = new DatagramPacket(buf, buf.length);
                serverSocket.receive(datagramPacket);
                System.out.println("Zgloszenie od klienta...");
                //Thread.sleep(6000);
                buf = "OK".getBytes();
                serverSocket.send(new DatagramPacket(buf, buf.length, datagramPacket.getAddress(), datagramPacket.getPort()));
            }

        }catch(UnknownHostException e) { System.err.println("Jakis blad - UnknownHostEx"); }
        catch(SocketException e) { System.err.println("Jakis blad - SocketEx"); }
        /*catch(IOException e) { System.err.println("Jakis blad - IOEx"); } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
    }

    public static void main(String[] args) throws IOException {
        new UDPServer(5501);
    }
}
