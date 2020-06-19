import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Timestamp;
import java.util.Date;

public class DayTimeServerMulti {

    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(13);
        try {
            while (true) {
                Socket socket = server.accept();
                new DayTimeServerMultiInst(socket);
            }
        } finally {
            server.close();
        }
    }
}
