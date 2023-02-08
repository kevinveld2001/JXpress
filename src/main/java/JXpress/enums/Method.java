package JXpress.enums;


public enum Method {
    GET("GET"),
    POST("POST"),
    PATCH("PATCH"),
    PUT("PUT"),
    DELETE("DELETE"),
    CHECKOUT("CHECKOUT"),
    COPY("COPY"),
    HEAD("HEAD"),
    LOCK("LOCK"),
    MERGE("MERGE"),
    MKACTIVITY("MKACTIVITY"),
    MKCOL("MKCOL"),
    MOVE("MOVE"),
    NOTIFY("NOTIFY"),
    OPTIONS("OPTIONS"),
    PURGE("PURGE"),
    REPORT("REPORT"),
    SEARCH("SEARCH"),
    SUBSCRIBE("SUBSCRIBE"),
    TRACE("TRACE"),
    UNLOCK("UNLOCK"),
    UNSUBSCRIBE("UNSUBSCRIBE");

    private String string;

    @Override
    public String toString() {
        if (string == null) {
            return "UNKNOWN";
        }
        return string;
    }
    Method(String string) {
        this.string = string;
    }

    public static Method fromString(String targetMethod) {
        for (Method method : Method.values()) {
            if (method.string.equalsIgnoreCase(targetMethod)) return method;
        }
        return null;
    }
}
