import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Timestamp;
import java.util.Date;

public class DayTimeServer {
    private int port = 13;

    public String getAnswer(){
        return "data: "+new Timestamp(new Date().getTime());
    }

    public DayTimeServer(int port){
        this.port = port;
        Socket socket = null;
        try {
            ServerSocket server = new ServerSocket(port);
            while (true) {
                socket = server.accept();
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(socket.getInputStream()));
                System.out.println("---------------- Pierwsza linia zapytania --------------------");
                System.out.println(in.readLine());
                System.out.println("---------------- Wysylam odpowiedz ---------------------------");
                System.out.println(getAnswer());
                System.out.println("---------------- Koniec odpowiedzi ---------------------------");
                out.println(getAnswer());
                out.flush();
                socket.close();
                if (socket != null) socket.close();
            }
        }  catch (IOException e) {
            System.out.println("Blad otwarcia");
        }
    }

    public static void main(String[] args) {
        DayTimeServer dayTimeServer = new DayTimeServer(13);
        //System.out.println(new Timestamp(new Date().getTime()));
    }
}
