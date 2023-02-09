import JXpress.App;
import JXpress.enums.Method;
import JXpress.routing.Response;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.net.Socket;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class BodyTest {
    @Test
    void getBodyTest() throws IOException, InterruptedException {
        int port = PortServes.getPort();
        new Thread(() -> {
            App app = new App();
            app.addRoute(Method.GET, "/body", req -> new Response().setBody(req.getBody()));
            app.listen(port);
        }).start();
        Thread.sleep(1000);

        Socket socket = new Socket("localhost", port);
        InputStream inStream = socket.getInputStream();
        BufferedReader in = new BufferedReader(new InputStreamReader(inStream));
        OutputStream outStream = socket.getOutputStream();
        PrintWriter out = new PrintWriter(outStream);

        out.println("GET /body HTTP/1.1");
        out.println("Content-Length: 5");
        out.println("");
        out.println("Hallo");
        out.flush();

        boolean resultFound = false;
        String line;
        while ((line = in.readLine()) != null) {
            if (line.contains("Hallo")) resultFound = true;
        }
        assertTrue(resultFound);
    }
}
