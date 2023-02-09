import JXpress.App;
import JXpress.enums.Method;
import JXpress.routing.Response;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.net.Socket;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ParamsTest {


    @Test
    void getParamTest() throws IOException, InterruptedException {
        int port = PortServes.getPort();
        new Thread(() -> {
            App app = new App();
            app.addRoute(Method.GET, "/params", req -> new Response().setBody(req.getParams("a") + req.getParams("b")));
            app.listen(port);
        }).start();
        Thread.sleep(1000);

        Socket socket = new Socket("localhost", port);
        InputStream inStream = socket.getInputStream();
        BufferedReader in = new BufferedReader(new InputStreamReader(inStream));
        OutputStream outStream = socket.getOutputStream();
        PrintWriter out = new PrintWriter(outStream);

        out.println("GET /params?a=test&b=123 HTTP/1.1");
        out.println("");
        out.flush();

        boolean resultFound = false;
        String line;
        while ((line = in.readLine()) != null) {
            if (line.contains("test123")) resultFound = true;
        }
        assertTrue(resultFound);
    }


    @Test
    void getParamListTest() throws IOException, InterruptedException {
        int port = PortServes.getPort();
        new Thread(() -> {
            App app = new App();
            app.addRoute(Method.GET, "/params", req -> new Response().setBody(req.getParamlists().toString()));
            app.listen(port);
        }).start();
        Thread.sleep(1000);

        Socket socket = new Socket("localhost", port);
        InputStream inStream = socket.getInputStream();
        BufferedReader in = new BufferedReader(new InputStreamReader(inStream));
        OutputStream outStream = socket.getOutputStream();
        PrintWriter out = new PrintWriter(outStream);

        out.println("GET /params?a[]=test&a[]=123&b=10&c[]=hi&c[]=321 HTTP/1.1");
        out.println("");
        out.flush();

        boolean resultFound = false;
        String line;
        while ((line = in.readLine()) != null) {
            if (line.contains("{a=[test, 123], c=[hi, 321]}")) resultFound = true;
        }
        assertTrue(resultFound);
    }


}
