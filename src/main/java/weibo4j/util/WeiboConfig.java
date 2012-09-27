package weibo4j.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import weibo4j.Weibo;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class WeiboConfig {
    private static final Logger LOG = LoggerFactory.getLogger(WeiboConfig.class);
    private static final String baseURL = "https://api.weibo.com/2/";
    private static final String accessTokenURL = "https://api.weibo.com/oauth2/access_token";
    private static final String authorizeURL = "https://api.weibo.com/oauth2/authorize";

    private static final String BASE_URL_KEY = "baseURL";
    private static final String ACCESS_TOKEN_URL_KEY = "accessTokenURL";
    private static final String AUTHORIZE_URL_KEY = "authorizeURL";
    private static final String CLIENT_ID_KEY = "clientId";
    private static final String CLIENT_SECRET_KEY = "clientSecret";
    private static final String REDIRECT_URL_KEY = "redirectURL";

    public WeiboConfig() {
    }

    private static Properties props = new Properties();

    static {
        try {
            InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream("configs.properties");
            if (null == in) {
                LOG.warn("Config file does NOT exist.Use default values instead.");
            } else {
                props.load(in);
            }
        } catch (IOException e) {
            LOG.error("Load config file error", e);
        }
    }

    private static String getProperty(String key) {
        return props.getProperty(key);
    }

    public static String getValue(String key) {
        if (CLIENT_ID_KEY.equals(key)) {
            return getClientId();
        } else if (CLIENT_SECRET_KEY.equals(key)) {
            return getClientSecret();
        } else if (BASE_URL_KEY.equals(key)) {
            return getBaseUrl();
        } else if (AUTHORIZE_URL_KEY.equals(key)) {
            return getAuthorizeURL();
        } else if (REDIRECT_URL_KEY.equals(key)) {
            return getRedirectUrl();
        } else if (ACCESS_TOKEN_URL_KEY.equals(key)) {
            return getAccessTokenURL();
        } else {
            throw new UnsupportedOperationException("NOT supported key " + key);
        }
    }

    private static boolean isNotEmpty(String value) {
        return !isEmpty(value);
    }

    private static boolean isEmpty(String value) {
        return (null == value || value.trim().length() == 0);
    }

    public static String getBaseUrl() {
        String value = getProperty(BASE_URL_KEY);
        if (isEmpty(value)) {
            value = baseURL;
        }
        return value;
    }

    public static String getClientId() {
        String value = getProperty(CLIENT_ID_KEY);
        if (isEmpty(value)) {
            value = Weibo.getClientId();
        }
        return value;
    }

    public static String getAuthorizeURL() {
        String value = getProperty(AUTHORIZE_URL_KEY);
        if (isEmpty(value)) {
            value = authorizeURL;
        }
        return value;
    }

    public static String getAccessTokenURL() {
        String value = getProperty(ACCESS_TOKEN_URL_KEY);
        if (isEmpty(value)) {
            value = accessTokenURL;
        }
        return value;
    }

    public static String getClientSecret() {
        String value = getProperty(CLIENT_SECRET_KEY);
        if (isEmpty(value)) {
            value = Weibo.getClientSecret();
        }
        return value;
    }

    public static String getRedirectUrl() {
        String value = getProperty("redirectURL");
        if (isEmpty(value)) {
            value = Weibo.getRedirectUrl();
        }
        return value;
    }
}
