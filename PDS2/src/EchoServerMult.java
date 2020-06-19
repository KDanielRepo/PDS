import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServerMult extends Thread{
    Socket socket = null;
    public EchoServerMult(Socket socket){
        this.socket = socket;
        start();
    }
    public void run(){
        int portNumber = 7;
        try (
                ServerSocket serverSocket =
                        new ServerSocket(7);
                Socket clientSocket = serverSocket.accept();
                PrintWriter out =
                        new PrintWriter(clientSocket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(clientSocket.getInputStream()));
        ) {
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                out.println(inputLine);
            }
        } catch (IOException e) {
            System.out.println("Exception caught when trying to listen on port "
                    + portNumber + " or listening for a connection");
            System.out.println(e.getMessage());
        }
    }

}
class EchoServerImpl{
    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(7);
        try {
            while (true) {
                Socket socket = server.accept();
                new EchoServerMult(socket);
            } // while
        } // try
        finally { server.close();}

    } // main
}