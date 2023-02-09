import JXpress.App;
import JXpress.enums.HttpStatusCode;
import JXpress.enums.Method;
import JXpress.routing.Response;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.net.Socket;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class StatusCodeTest {

    @Test
    void post404Test() throws IOException, InterruptedException {
        int port = PortServes.getPort();
        new Thread(() -> {
            App app = new App();

            for (HttpStatusCode code:HttpStatusCode.values()) {
                app.addRoute(Method.GET, "/" + code.getCode(), req -> new Response().setHttpStatusCode(code));
            }

            app.listen(port);
        }).start();
        Thread.sleep(1000);

        for (HttpStatusCode code:HttpStatusCode.values()) {
            Socket socket = new Socket("localhost", port);
            InputStream inStream = socket.getInputStream();
            BufferedReader in = new BufferedReader(new InputStreamReader(inStream));
            OutputStream outStream = socket.getOutputStream();
            PrintWriter out = new PrintWriter(outStream);


            System.out.println("GET /"+code.getCode()+" HTTP/1.1");
            out.println("GET /"+code.getCode()+" HTTP/1.1");
            out.println("");
            out.flush();

            String line = in.readLine();
            System.out.println(line);
            assertEquals("HTTP/1.1 " + code.getCode() + " " + code.getDescription(), line);

            out.close();
            outStream.close();
            in.close();
            inStream.close();
            socket.close();
        }
    }


}
