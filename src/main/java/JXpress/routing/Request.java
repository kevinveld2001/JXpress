package JXpress.routing;

import JXpress.enums.Method;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Request {
    private final Map<String, String> headers = new HashMap<>();
    private Method method;
    private String path;
    private String body = "";

    private final Map<String, String> params = new HashMap<>();
    private final Map<String, List<String>> paramlists = new HashMap<String, List<String>>();


    public Request(String rawRequest) {
        if (rawRequest.length() == 0) {
            return; //return if no request is found
        }
        String[] splitRequests = rawRequest.split("\n");
        String[] firstLine = splitRequests[0].split(" ");

        method = Method.fromString(firstLine[0]);
        String[] urlSplit = firstLine[1].split("\\?");
        path = urlSplit[0];

        if (urlSplit.length > 1) {
            addParams(urlSplit[1]);
        }

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

    private void addParams(String params) {
        String[] rawParams = params.split("&");
        for (String rawParam : rawParams) {
            String[] paramKeyValue = rawParam.split("=");

            String value = "";
            if (paramKeyValue.length > 1) value = paramKeyValue[1];

            // if param key ends with [] it's a list of params with that name
            if (paramKeyValue[0].endsWith("[]")) {
                String key = paramKeyValue[0].replace("[]", "");
                if (!paramlists.containsKey(key)) {
                    paramlists.put(key, new ArrayList<>());
                }
                paramlists.get(key).add(value);

            } else {
                //not a list just a param value that can be set.
                this.params.put(paramKeyValue[0], value);
            }
        }
    }

    public Map<String, String> getParams() {
        return params;
    }
    public String getParams(String key) {
        return params.get(key);
    }

    public Map<String, List<String>> getParamlists() {
        return paramlists;
    }
    public List<String> getParamlists(String key) {
        return paramlists.get(key);
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
