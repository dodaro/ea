package reflection;

import org.json.JSONObject;

import java.util.*;

public class JSONUtil {

    private static boolean isWrapper(Class obj)  {
        return Set.of(Boolean.class, Byte.class, Character.class, Double.class, Float.class, Integer.class, Long.class, Short.class, Void.class).contains(obj);
    }

    private static String toJSONString(Object o) throws Exception {
        if(Map.class.isAssignableFrom(o.getClass())) {
            var map = (Map) o;
            StringBuilder res = new StringBuilder("{");
            for(var element : map.keySet()) {
                res.append(element + ":" + toJSONString(map.get(element))).append(",");
            }
            if(res.charAt(res.length()-1) == ',') {
                res.replace(res.length()-1, res.length(), "}");
            }
            else
                res.append("}");
            return res.toString();
        }
        if(Iterable.class.isAssignableFrom(o.getClass())) {
            var list = (Iterable) o;
            StringBuilder res = new StringBuilder("[");
            for(var element : list) {
                res.append(toJSONString(element)).append(",");
            }
            if(res.charAt(res.length()-1) == ',')
                res.replace(res.length() - 1, res.length(), "]");
            else
                res.append("]");
            return res.toString();
        }
        if(Number.class.isAssignableFrom(o.getClass())) {
            return o.toString();
        }
        if(String.class.isAssignableFrom(o.getClass()) || isWrapper(o.getClass())) {
            return "\"" + o + "\"";
        }
        return toJSON(o).toString();
    }

    public static JSONObject toJSON(Object o) throws Exception {
        Objects.requireNonNull(o, "Object cannot be null");
        var fields = o.getClass().getDeclaredFields();
        StringBuilder parameters = new StringBuilder("{");
        boolean record = o.getClass().isRecord();
        for (var field : fields) {
            if (!field.isAnnotationPresent(Ignore.class)) {
                try {
                    String name = field.getName();
                    var method = record ? o.getClass().getDeclaredMethod(name) : o.getClass().getDeclaredMethod("get" + Character.toUpperCase(name.charAt(0)) + name.substring(1));
                    parameters.append(name).append(":").append(toJSONString(method.invoke(o))).append(",");
                } catch (NoSuchMethodException e) {
                    throw new Exception(o.getClass() + " must be a record or must have a getter for all fields!");
                }
            }
        }
        parameters.replace(parameters.length()-1, parameters.length(), "}");
        return new JSONObject(parameters.toString());
    }

    public static JSONObject toJSONUsingFields(Object o) throws Exception {
        Objects.requireNonNull(o, "Object cannot be null");
        var fields = o.getClass().getDeclaredFields();
        StringBuilder parameters = new StringBuilder("{");
        for (var field : fields) {
            if (!field.isAnnotationPresent(Ignore.class)) {
                String name = field.getName();
                field.setAccessible(true);
                parameters.append(name).append(":").append(toJSONString(field.get(o))).append(",");
            }
        }
        parameters.replace(parameters.length()-1, parameters.length(), "}");
        return new JSONObject(parameters.toString());
    }
}