package weibo4j.util;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
}
