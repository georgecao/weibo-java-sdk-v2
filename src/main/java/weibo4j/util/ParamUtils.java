package weibo4j.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import weibo4j.model.AccountUser;
import weibo4j.model.PostParameter;
import weibo4j.model.SerializedName;

import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
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
                    Object o = pd.getReadMethod().invoke(user);
                    if (o != null) {
                        params.add(new PostParameter(paramName, o.toString()));
                    }
                }
            }
        } catch (IntrospectionException e) {
            LOG.error("Error occurred:", e);
        } catch (InvocationTargetException e) {
            LOG.error("Error occurred:", e);
        } catch (IllegalAccessException e) {
            LOG.error("Error occurred:", e);
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

