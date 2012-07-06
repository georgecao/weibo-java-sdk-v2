package weibo4j.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import weibo4j.model.AccountUser;
import weibo4j.model.PostParameter;
import weibo4j.model.Required;
import weibo4j.model.SerializedName;

import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

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

    public static List<PostParameter> get(AccountUser user) {
        LinkedList<PostParameter> params = new LinkedList<PostParameter>();
        Field[] fields = AccountUser.class.getDeclaredFields();
        Map<String, Field> map = new HashMap<String, Field>();
        for (Field field : fields) {
            map.put(field.getName(), field);
        }
        List<String> missed = new LinkedList<String>();
        try {
            PropertyDescriptor[] pds = Introspector.getBeanInfo(AccountUser.class).getPropertyDescriptors();
            for (PropertyDescriptor pd : pds) {
                String name = pd.getName();
                String paramName = name;
                if (map.containsKey(name)) {
                    Field field = map.get(name);
                    if (field.isAnnotationPresent(SerializedName.class)) {
                        paramName = field.getAnnotation(SerializedName.class).value();
                    }
                    boolean isRequired = field.isAnnotationPresent(Required.class);
                    Object o = pd.getReadMethod().invoke(user);
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
                    params.add(new PostParameter(paramName, paramValue));
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

    public static PostParameter[] convert(List<PostParameter> parameters) {
        if (null == parameters || 0 == parameters.size()) {
            return new PostParameter[0];
        }
        PostParameter[] params = new PostParameter[parameters.size()];
        return parameters.toArray(params);
    }

}

