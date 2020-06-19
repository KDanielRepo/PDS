import java.io.IOException;
import java.net.*;

public class UDPClient {
    InetAddress inetAddress = InetAddress.getByName("localhost");
    int port = 5501;
    int ammount = 2;
    int in = 0;
    int out = 0;
    public UDPClient(int port, int ammount) throws UnknownHostException {
        this.port = port;
        this.ammount = ammount;
        for (int i = 0; i < ammount; i++) {
            byte buf[] = "msg".getBytes();
            try {
                DatagramSocket datagramSocket = new DatagramSocket();
                DatagramPacket datagramPacket = new DatagramPacket(buf, buf.length,inetAddress,port);
                datagramSocket.setSoTimeout(5000);
                datagramSocket.send(datagramPacket);
                out++;

                buf = new byte[100];
                DatagramPacket packet = new DatagramPacket(buf, buf.length);
                datagramSocket.receive(packet);
                String answer = new String(packet.getData());
                System.out.println(answer);
                if(!answer.equals("")){
                    in++;
                }
                Thread.sleep(5000);
            }catch (IOException e){
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("in: "+in+" out: "+out);
    }

    public static void main(String[] args) throws UnknownHostException {
        new UDPClient(5501,2);
    }
}
