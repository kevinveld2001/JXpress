import JXpress.App;
import JXpress.enums.Method;
import JXpress.routing.Response;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.net.Socket;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RoutingTest {

    @Test
    void getDashTest() throws IOException, InterruptedException {
        int port = PortServes.getPort();
        new Thread(() -> {
            App app = new App();
            app.addRoute(Method.GET, "/", req -> new Response().setBody("getDash"));
            app.listen(port);
        }).start();
        Thread.sleep(1000);

        Socket socket = new Socket("localhost", port);
        InputStream inStream = socket.getInputStream();
        BufferedReader in = new BufferedReader(new InputStreamReader(inStream));
        OutputStream outStream = socket.getOutputStream();
        PrintWriter out = new PrintWriter(outStream);

        out.println("GET / HTTP/1.1");
        out.println("");
        out.flush();

        boolean resultFound = false;
        String line;
        while ((line = in.readLine()) != null) {
            if (line.contains("getDash")) resultFound = true;
        }
        assertTrue(resultFound);
    }
    @Test
    void postDashTest() throws IOException, InterruptedException {
        int port = PortServes.getPort();
        new Thread(() -> {
            App app = new App();
            app.addRoute(Method.POST, "/", req -> new Response().setBody("postDash"));
            app.listen(port);
        }).start();
        Thread.sleep(1000);

        Socket socket = new Socket("localhost", port);
        InputStream inStream = socket.getInputStream();
        BufferedReader in = new BufferedReader(new InputStreamReader(inStream));
        OutputStream outStream = socket.getOutputStream();
        PrintWriter out = new PrintWriter(outStream);

        out.println("POST / HTTP/1.1");
        out.println("");
        out.flush();

        boolean resultFound = false;
        String line;
        while ((line = in.readLine()) != null) {
            if (line.contains("postDash")) resultFound = true;
        }
        assertTrue(resultFound);
    }

    @Test
    void getDashHomeTest() throws IOException, InterruptedException {
        int port = PortServes.getPort();
        new Thread(() -> {
            App app = new App();
            app.addRoute(Method.GET, "/home", req -> new Response().setBody("getDashHome"));
            app.listen(port);
        }).start();
        Thread.sleep(1000);

        Socket socket = new Socket("localhost", port);
        InputStream inStream = socket.getInputStream();
        BufferedReader in = new BufferedReader(new InputStreamReader(inStream));
        OutputStream outStream = socket.getOutputStream();
        PrintWriter out = new PrintWriter(outStream);

        out.println("GET /home HTTP/1.1");
        out.println("");
        out.flush();

        boolean resultFound = false;
        String line;
        while ((line = in.readLine()) != null) {
            if (line.contains("getDashHome")) resultFound = true;
        }
        assertTrue(resultFound);
    }
    @Test
    void postDashHomeTest() throws IOException, InterruptedException {
        int port = PortServes.getPort();
        new Thread(() -> {
            App app = new App();
            app.addRoute(Method.POST, "/home", req -> new Response().setBody("postDashHome"));
            app.listen(port);
        }).start();
        Thread.sleep(1000);

        Socket socket = new Socket("localhost", port);
        InputStream inStream = socket.getInputStream();
        BufferedReader in = new BufferedReader(new InputStreamReader(inStream));
        OutputStream outStream = socket.getOutputStream();
        PrintWriter out = new PrintWriter(outStream);

        out.println("POST /home HTTP/1.1");
        out.println("");
        out.flush();

        boolean resultFound = false;
        String line;
        while ((line = in.readLine()) != null) {
            if (line.contains("postDashHome")) resultFound = true;
        }
        assertTrue(resultFound);
    }

    @Test
    void post404Test() throws IOException, InterruptedException {
        int port = PortServes.getPort();
        new Thread(() -> {
            App app = new App();
            app.addRoute(Method.POST, "/thispageisfound", req -> new Response().setBody("thispageisfound"));
            app.listen(port);
        }).start();
        Thread.sleep(1000);

        Socket socket = new Socket("localhost", port);
        InputStream inStream = socket.getInputStream();
        BufferedReader in = new BufferedReader(new InputStreamReader(inStream));
        OutputStream outStream = socket.getOutputStream();
        PrintWriter out = new PrintWriter(outStream);

        out.println("GET /thispageisnotfound HTTP/1.1");
        out.println("");
        out.flush();

        assertTrue(in.readLine().contains("404"));
    }

}
