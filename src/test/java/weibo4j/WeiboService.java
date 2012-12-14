package weibo4j;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;

/**
 * Say something?
 * <pre>
 * User: George
 * Date: 12-8-16
 * Time: 下午4:36
 * </pre>
 */
public class WeiboService {
    private static final Logger LOG = LoggerFactory.getLogger(WeiboService.class);
    private static final boolean debug = LOG.isDebugEnabled();
    private static String[] validAccessToken = new String[]{
            "2.00tBx13B0WKLFi456926a42fy2QUgE"};
    private static Random random = new Random();

    static {
        Weibo.clientId = "651393360";
        Weibo.clientSecret = "a800c969df31c9ab1f77d7ca0940fe0a";
        Weibo.redirectUrl = "http://www.dajie.com/oauth/sina/enterprise";
    }

    public static Weibo getOne() {
        return new Weibo(validAccessToken[random.nextInt(validAccessToken.length)]);
    }
}
