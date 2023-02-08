package JXpress.routing;

import JXpress.enums.Method;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Request {
    private final Map<String, String> headers = new HashMap<>();
    private Method method;
    private String path;
    private String body = "";

    public Request(String rawRequest) {
        if (rawRequest.length() == 0) {
            return; //return if no request is found
        }
        String[] splitRequests = rawRequest.split("\n");
        String[] firstLine = splitRequests[0].split(" ");

        method = Method.fromString(firstLine[0]);
        path = firstLine[1];

        if (splitRequests.length == 1) {
            return; // return if request has no headers and body
        }

        boolean isHeader = true;
        for (int i = 1; i < splitRequests.length; i++) {
            if (splitRequests[i].equals("")) {
                isHeader = false;
                continue;
            }
            if (isHeader) {
                String[] header = splitRequests[i].split(": ");
                headers.put(header[0], header[1]);
            } else {
                body += splitRequests[i] + "\n";
            }
        }
    }


    public Map<String, String> getHeaders() {
        return headers;
    }

    public String getHeader(String headerName) {
        return headers.get(headerName);
    }

    public Method getMethod() {
        return method;
    }

    public String getPath() {
        return path;
    }

    public String getBody() {
        return body;
    }
}
