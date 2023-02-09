import JXpress.App;
import JXpress.enums.Method;
import JXpress.routing.Response;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.net.Socket;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class HeaderTest {
    @Test
    void getHeaderTest() throws IOException, InterruptedException {
        int port = PortServes.getPort();
        new Thread(() -> {
            App app = new App();
            app.addRoute(Method.GET, "/header", req -> new Response().setBody(req.getHeader("Test")));
            app.listen(port);
        }).start();
        Thread.sleep(1000);

        Socket socket = new Socket("localhost", port);
        InputStream inStream = socket.getInputStream();
        BufferedReader in = new BufferedReader(new InputStreamReader(inStream));
        OutputStream outStream = socket.getOutputStream();
        PrintWriter out = new PrintWriter(outStream);

        out.println("GET /header HTTP/1.1");
        out.println("Test: Hallo world");
        out.println("");
        out.flush();

        boolean resultFound = false;
        String line;
        while ((line = in.readLine()) != null) {
            System.out.println(line);
            if (line.contains("Hallo world")) resultFound = true;
        }
        assertTrue(resultFound);
    }

    @Test
    void getHeadersTest() throws IOException, InterruptedException {
        int port = PortServes.getPort();
        new Thread(() -> {
            App app = new App();
            app.addRoute(Method.GET, "/header", req -> new Response().setBody(req.getHeaders().toString()));
            app.listen(port);
        }).start();
        Thread.sleep(1000);

        Socket socket = new Socket("localhost", port);
        InputStream inStream = socket.getInputStream();
        BufferedReader in = new BufferedReader(new InputStreamReader(inStream));
        OutputStream outStream = socket.getOutputStream();
        PrintWriter out = new PrintWriter(outStream);

        out.println("GET /header HTTP/1.1");
        out.println("Test: Hallo world");
        out.println("check: 123");
        out.println("");
        out.flush();

        boolean resultFound = false;
        String line;
        while ((line = in.readLine()) != null) {
            System.out.println(line);
            if (line.contains("{Test=Hallo world, check=123}")) resultFound = true;
        }
        assertTrue(resultFound);
    }
}
