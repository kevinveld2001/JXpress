package JXpress.converters;

import JXpress.callback.KeyValueCallback;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Form {
    public static Map<String, String> decodeXFormData(String body) {
        Map<String, String> data = new HashMap<>();

        loopXFormKeyValues(body, false, data::put);

        return data;
    }

    public static Map<String, List<String>> decodeXFormListData(String body) {
        Map<String, List<String>> data = new HashMap<>();

        loopXFormKeyValues(body, true, (key, value) -> {
            if (!data.containsKey(key)) {
                data.put(key, new ArrayList<>());
            }
            data.get(key).add(value);
        });

        return data;
    }

    private static void loopXFormKeyValues(String body, boolean isList, KeyValueCallback keyValueCallback) {
        String[] rawParams = body.split("&");
        for (String rawParam : rawParams) {
            String[] paramKeyValue = rawParam.split("=");
            String key = URLDecoder.decode(paramKeyValue[0], StandardCharsets.UTF_8);
            String value = "";
            if (paramKeyValue.length > 1) value = paramKeyValue[1];
            if (key.endsWith("[]") == isList) {
                key = key.replace("[]", "");
                keyValueCallback.run(key, URLDecoder.decode(value, StandardCharsets.UTF_8));
            }
        }
    }

}
