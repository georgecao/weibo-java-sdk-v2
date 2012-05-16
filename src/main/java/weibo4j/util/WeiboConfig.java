package weibo4j.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class WeiboConfig {
    private static final Logger LOG = LoggerFactory.getLogger(WeiboConfig.class);

    public WeiboConfig() {
    }

    private static Properties props = new Properties();

    static {
        try {
            props.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("configs.properties"));
        } catch (FileNotFoundException e) {
            LOG.error("Load config file error", e);
        } catch (IOException e) {
            LOG.error("Load config file error", e);
        }
    }

    public static String getValue(String key) {
        return props.getProperty(key);
    }

    public static String getBaseUrl() {
        return getValue("baseURL");
    }

    public static String getClientId() {
        return getValue("clientId");
    }

    public static String getAccessTokenURL() {
        return getValue("accessTokenURL");
    }

    public static String getClientSecret() {
        return getValue("clientSecret");
    }

    public static String getRedirectUrl() {
        return getValue("redirectURL");
    }


    public static void updateProperties(String key, String value) {
        props.setProperty(key, value);
    }
}
