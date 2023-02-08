package JXpress.routing;

import JXpress.enums.ContentType;
import JXpress.enums.HttpStatusCode;

import java.util.HashMap;
import java.util.Map;

public class Response {
    private HttpStatusCode httpStatusCode = HttpStatusCode.OK;
    private String body = "";
    private final Map<String, String> headers = new HashMap<>();

    public Response() {
        headers.put("Content-Type", ContentType.TEXT_PLAIN.getType());
        headers.put("Server", "JXpress");
        headers.put("Connection", "Closed");
    }

    public Response setBody(String body) {
        this.body = body;
        return this;
    }

    public Response setHTML(String html) {
        headers.put("Content-Type", ContentType.TEXT_HTML.getType());
        this.body = html;
        return this;
    }

    public Response setHttpStatusCode(HttpStatusCode httpStatusCode) {
        this.httpStatusCode = httpStatusCode;
        return this;
    }

    public Response setContentType(String contentType) {
        headers.put("Content-Type", contentType);
        return this;
    }

    public Response setContentType(ContentType contentType) {
        headers.put("Content-Type", contentType.getType());
        return this;
    }

    public Response addHeader(String key, String value) {
        headers.put(key, value);
        return this;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("HTTP/1.1 " + httpStatusCode.getCode() + " " + httpStatusCode.getDescription() + "\n");

        headers.forEach((key, value) -> {
            stringBuilder.append(key + ": " + value + "\n");
        });

        stringBuilder.append("Content-Length: " + body.length() + "\n");
        stringBuilder.append("\n");
        stringBuilder.append(body);

        return stringBuilder.toString();
    }

}
