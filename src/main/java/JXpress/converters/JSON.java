package JXpress.converters;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class JSON {
    public static String stringify(Object object) {
        Class<?>[] interfaces = object.getClass().getInterfaces();
        if (Arrays.stream(interfaces).anyMatch(aClass -> aClass.getName().equals("java.util.List"))) {
            return stringifyList((List<?>)object);
        } else {
            return stringifyClass(object);
        }
    }
    private static String stringifyList(List<?> list) {
        StringBuilder builder = new StringBuilder();
        builder.append('[');
        for (int i = 0; i < list.size(); i++) {
            builder.append(stringifyValue(list.get(i)));
            if (i < list.size()-1) builder.append(",");
        }
        builder.append(']');
        return builder.toString();
    }
    private static String stringifyClass(Object object) {
        StringBuilder builder = new StringBuilder();
        builder.append('{');
        Field[] fields = object.getClass().getDeclaredFields();

        boolean hasItem = false;
        for (Field field: fields) {
            try {
                field.setAccessible(true);
                Object value = field.get(object);

                if (field.isAnnotationPresent(JSONfield.class)) {
                    if (hasItem) builder.append(",");
                    String name = field.getName();
                    String annotationName = field.getAnnotation(JSONfield.class).value();
                    if (!annotationName.equals("")) name = annotationName;
                    builder.append("\"" + name + "\"").append(": ");
                    builder.append(stringifyValue(value));
                    hasItem = true;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        builder.append('}');
        return builder.toString();
    }
    private static String stringifyValue(Object value) {

        if (value == null) {
            return "null";
        }
        if (value instanceof String) {
            return "\"" + value + "\"";
        }
        if (value instanceof Character) {
            return "\"" + value + "\"";
        }
        if (value instanceof Float) {
            return Float.toString((Float) value);
        }
        if (value instanceof Double) {
            return Double.toString((Double) value);
        }
        if (value instanceof Integer) {
            return Integer.toString((Integer) value);
        }
        if (value instanceof Boolean) {
            return Boolean.toString((Boolean) value);
        }
        return stringify(value);
    }
}
