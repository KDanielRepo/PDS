import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.sql.Timestamp;
import java.util.Date;

public class DayTimeServerMultiInst extends Thread {
    Socket socket = null;

    public DayTimeServerMultiInst(Socket socket) {
        this.socket = socket;
        start();
    }

    public String getAnswer() {
        return "data: " + new Timestamp(new Date().getTime());
    }

    public void run() {
        try {
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
        } catch (IOException e) {
            System.out.println("Blad IO danych!");
        } finally {
            try {
                if (socket != null) socket.close();
            } catch (IOException e) {
                System.out.println("Blad zamkni�cia gniazda!");
            }
        }
    }
}
