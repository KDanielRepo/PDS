import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class ScannerPortsMulti extends Thread{
    Socket socket;
    String host = "localhost";
    int ammount = 10;
    int current = 0;
    int max = 1024/ammount;

    @Override
    public void run() {
        long time = System.nanoTime();
        for (int i = 1; i < max; i++) {
            try {
                socket = new Socket(host, i+(max*current));
                System.out.println("na porcie " + (i+(max*current)) + " dziala serwer");
                socket.close();
            } catch (UnknownHostException e) {
                System.err.println(e);
                break;
            } catch (IOException e) {
                System.out.println("na porcie: " + (i+(max*current)) + " nic sie nie dzieje");
            }
        }
        long stop = System.nanoTime();
        System.out.println("czas wykonania: "+(stop-time));
    }

    public static void main(String[] args) {
        //System.out.println(1024/5);

        for (int i = 0; i < 10; i++) {
            ScannerPortsMulti scpm = new ScannerPortsMulti();
            scpm.current = i;
            if (args.length > 0) scpm.host = args[0];
            scpm.start();
        }//202,383833382   3,3 minuty


    }

}
