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
public class OAuthTest {
    private static final Logger LOG = LoggerFactory.getLogger(OAuthTest.class);
    private static final boolean debug = LOG.isDebugEnabled();

    @Test
    public void testGetAccessTokenByUserCredential() throws Exception {
        Weibo weibo = new Weibo();
        String username = "1732066773@qq.com";
        String password = "test-password";
        Weibo.setClientId("651393360");
        Weibo.setClientSecret("a800c969df31c9ab1f77d7ca0940fe0a");
        try {
            AccessToken token = weibo.getOAuthService().getAccessTokenByUserCredential(username, password);
            LOG.info("Access token: {}", token);
        } catch (Exception e) {
            LOG.error("User credential ", e);
        }
    }

    @Test
    public void testAuthorize() throws Exception {
        Weibo w = new Weibo();
        try {
            String code = w.getOAuthService().authorize("code");
            LOG.info("{}", code);
        } catch (Exception e) {
            LOG.error("", e);
        }
    }

    @Test
    public void testGetAccessTokenByCode() throws Exception {
        Weibo w = new Weibo();
        try {
            AccessToken token = w.getOAuthService().getAccessTokenByCode("this is a code");
            LOG.info("{}", token);
        } catch (Exception e) {
            LOG.info("Error ", e);
        }
    }
}
