package weibo4j.util;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import weibo4j.Weibo;

import static org.junit.Assert.assertNotNull;

/**
 * Say something?
 * <pre>
 * User: zhangzhi.cao
 * Date: 12-5-7
 * Time: 下午7:48
 * </pre>
 */
public class WeiboConfigTest {
    private static final Logger LOG = LoggerFactory.getLogger(WeiboConfigTest.class);
    private static final boolean debug = LOG.isDebugEnabled();

    static {
        Weibo.setClientId("id");
        Weibo.setClientSecret("secret");
        Weibo.setRedirectUrl("http://www.domain.com");
    }

    @Test
    public void testGetBaseUrl() throws Exception {
        String url = WeiboConfig.getBaseUrl();
        assertNotNull(url);
        LOG.info(url);
    }

    @Test
    public void testGetClientId() throws Exception {
        String url = WeiboConfig.getClientId();
        assertNotNull(url);
        LOG.info(url);

    }

    @Test
    public void testGetClientSecret() throws Exception {
        String url = WeiboConfig.getClientSecret();
        assertNotNull(url);
        LOG.info(url);

    }

    @Test
    public void testGetRedirectUrl() throws Exception {
        String url = WeiboConfig.getRedirectUrl();
        assertNotNull(url);
        LOG.info(url);
    }

    @Test
    public void testGetAuthorizeURL() throws Exception {
        String url = WeiboConfig.getAuthorizeURL();
        assertNotNull(url);
        LOG.info(url);
    }

    @Test
    public void testGetAccessTokenURL() throws Exception {
        String url = WeiboConfig.getAccessTokenURL();
        assertNotNull(url);
        LOG.info(url);
    }
}
