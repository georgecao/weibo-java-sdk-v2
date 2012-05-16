package weibo4j;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import weibo4j.http.AccessToken;

/**
 * Say something?
 * <pre>
 * User: zhangzhi.cao
 * Date: 12-5-16
 * Time: 上午11:24
 * </pre>
 */
public class OauthTest {
    private static final Logger LOG = LoggerFactory.getLogger(OauthTest.class);
    private static final boolean debug = LOG.isDebugEnabled();

    @Test
    public void testGetAccessTokenByUserCredential() throws Exception {
        Weibo weibo = new Weibo();
        String username = "1732066773@qq.com";
        String password = "APP-DAJIE-COM";
        AccessToken token = weibo.getOAuthService().getAccessTokenByUserCredential(username, password);
        LOG.info("Access token: {}", token);
    }
}
