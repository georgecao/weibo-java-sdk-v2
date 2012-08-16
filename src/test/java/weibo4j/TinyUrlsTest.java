package weibo4j;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import weibo4j.model.TinyUrl;
import weibo4j.model.TinyUrlWrapper;
import weibo4j.util.ParamUtils;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Say something?
 * <pre>
 * User: George
 * Date: 12-7-12
 * Time: 下午12:08
 * </pre>
 */
public class TinyUrlsTest {
    private static final Logger LOG = LoggerFactory.getLogger(TinyUrlsTest.class);
    private static final boolean debug = LOG.isDebugEnabled();
    TinyUrls tinyUrls = WeiboService.getOne().getTinyUrlService();

    @Test
    public void testShortUrl() throws Exception {
        TinyUrlWrapper tuw = tinyUrls.shortUrl("http://www.dajie.com/account/login");
        assertNotNull(tuw);
        TinyUrl tu = tuw.getFirst();
        assertNotNull(tu);
        LOG.info("{}", tu.getShortUrl());
        assertTrue(!ParamUtils.isEmpty(tu.getShortUrl()));
    }

    @Test
    public void testExpandUrl() throws Exception {
        TinyUrlWrapper tuw = tinyUrls.expandUrl("http://t.cn/S2CaKm");
        assertNotNull(tuw);
        TinyUrl tu = tuw.getFirst();
        assertNotNull(tu);
        LOG.info("{}", tu.getLongUrl());
        assertTrue(!ParamUtils.isEmpty(tu.getLongUrl()));
    }
}
