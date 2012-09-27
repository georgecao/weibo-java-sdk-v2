package weibo4j.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import weibo4j.model.HttpParameter;
import weibo4j.model.Required;
import weibo4j.model.SerializedName;

import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.util.*;

/**
 * Say something?
 * <pre>
 * User: zhangzhi.cao
 * Date: 12-7-2
 * Time: 下午2:55
 * </pre>
 */
public class ParamUtils {
    private static final Logger LOG = LoggerFactory.getLogger(ParamUtils.class);
    private static final boolean debug = LOG.isDebugEnabled();
    private static final String COMMA = ",";

    public static String nullToEmpty(String value) {
        return null == value ? "" : value;
    }

    public static String padStartWithZero(String string, int minLength) {
        return padStart(string, minLength, '0');
    }

    public static String padStart(String string, int minLength, char padChar) {
        if (string.length() >= minLength) {
            return string;
        }
        StringBuilder sb = new StringBuilder(minLength);
        for (int i = string.length(); i < minLength; i++) {
            sb.append(padChar);
        }
        sb.append(string);
        return sb.toString();
    }

    public static boolean isNotEmpty(HttpParameter[] arrays) {
        return !isEmpty(arrays);
    }

    public static boolean isEmpty(HttpParameter[] arrays) {
        return null == arrays || arrays.length == 0;
    }

    public static boolean isEmpty(String value) {
        return null == value || value.trim().length() == 0;
    }

    public static List<HttpParameter> get(Object obj) {
        LinkedList<HttpParameter> params = new LinkedList<HttpParameter>();
        Class<?> clazz = obj.getClass();
        Field[] fields = clazz.getDeclaredFields();
        Map<String, Field> map = new HashMap<String, Field>();
        for (Field field : fields) {
            map.put(field.getName(), field);
        }
        List<String> missed = new LinkedList<String>();
        try {
            PropertyDescriptor[] pds = Introspector.getBeanInfo(clazz).getPropertyDescriptors();
            for (PropertyDescriptor pd : pds) {
                String name = pd.getName();
                String paramName = name;
                if (map.containsKey(name)) {
                    Field field = map.get(name);
                    if (field.isAnnotationPresent(SerializedName.class)) {
                        paramName = field.getAnnotation(SerializedName.class).value();
                    }
                    boolean isRequired = field.isAnnotationPresent(Required.class);
                    Object o = pd.getReadMethod().invoke(obj);
                    String paramValue = "";
                    if (o != null) {
                        paramValue = o.toString();
                    }
                    if (null == paramValue || paramValue.trim().length() == 0) {
                        if (isRequired) {
                            missed.add(paramName);
                        }
                        continue;
                    }
                    params.add(new HttpParameter(paramName, paramValue));
                }
            }
        } catch (Exception e) {
            LOG.error("Error occurred:", e);
        }
        if (missed.size() > 0) {
            throw new IllegalArgumentException("These parameters (" + missed + ") are required, but not specified.");
        }
        return params;
    }

    public static List<HttpParameter> convert(HttpParameter[] params) {
        List<HttpParameter> parameters;
        if (isEmpty(params)) {
            parameters = new ArrayList<HttpParameter>(0);
        } else {
            parameters = new ArrayList<HttpParameter>(Arrays.asList(params));
        }
        return parameters;
    }

    public static HttpParameter[] convert(List<HttpParameter> parameters) {
        if (isEmpty(parameters)) {
            return new HttpParameter[0];
        }
        HttpParameter[] params = new HttpParameter[parameters.size()];
        return parameters.toArray(params);
    }

    public static boolean isEmpty(Collection<?> collection) {
        return null == collection || collection.size() == 0;
    }

    public static boolean isNotEmpty(Collection<?> collection) {
        return !isEmpty(collection);
    }

    public static String joinWithComma(Collection<?> collection) {
        return join(collection, COMMA);
    }

    public static String join(Collection<?> collection, String sep) {
        StringBuilder b = new StringBuilder("");
        int length = collection.size();
        int count = 0;
        for (Object userId : collection) {
            b.append(userId);
            if (++count >= length) {
                return b.toString();
            }
            b.append(sep);

        }
        return b.toString();
    }
}

