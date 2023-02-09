package JXpress;

import JXpress.callback.RequestHandler;
import JXpress.enums.HttpStatusCode;
import JXpress.enums.Method;
import JXpress.routing.Request;
import JXpress.routing.Response;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class App {

    private final Map<String, RequestHandler> routes = new HashMap<>();


    public void addRoute(Method method, String path, RequestHandler requestHandler) {
        routes.put(method.toString() + path, requestHandler);
    }

    public void listen(int port) {
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            while (true) {
                //accept connection
                Socket clientSocket = serverSocket.accept();
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

                //get request
                StringBuilder rawRequestBuilder = new StringBuilder();
                String line;
                while ((line = in.readLine()) != null && !line.equals("")) {
                    rawRequestBuilder.append(line).append("\n");
                }
                String rawRequest = rawRequestBuilder.toString();
                Request request = new Request(rawRequest, in);

                //run requestHandler
                Response response = new Response().setHttpStatusCode(HttpStatusCode.NOT_FOUND);
                if (request.getMethod() != null && routes.containsKey(request.getMethod().toString() + request.getPath())) {
                    RequestHandler requestHandler = routes.get(request.getMethod().toString() + request.getPath());
                    response = requestHandler.run(request);
                }
                //send answer
                out.print(response);
                out.flush();

                //close connection
                in.close();
                out.close();
                clientSocket.close();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
