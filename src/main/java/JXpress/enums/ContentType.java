package JXpress.enums;

public enum ContentType {
    APPLICATION_JSON("application/json"),
    APPLICATION_XML("application/xml"),
    TEXT_HTML("text/html"),
    TEXT_PLAIN("text/plain"),
    TEXT_XML("text/xml"),
    MULTIPART_FORM_DATA("multipart/form-data"),
    APPLICATION_OCTET_STREAM("application/octet-stream");

    private final String type;

    ContentType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}