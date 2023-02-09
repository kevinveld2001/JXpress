import java.io.IOException;
import java.net.ServerSocket;
import java.util.Random;

public class PortServes {
    public static int getPort() {
        return getPort(49152 , 65535);
    }
    public static int getPort(int from , int to) {
        Random random = new Random();
        while (true) {
            int port = random.ints(from, to).findFirst().getAsInt();
            if (isPortFree(port)) return port;
        }
    }
    private static boolean isPortFree(int port) {
        try {
            new ServerSocket(port).close();
        } catch (IOException e) {
            return false;
        }
        return true;
    }
}
