package weibo4j;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import weibo4j.model.TinyUrl;
import weibo4j.model.TinyUrlWrapper;

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
    TinyUrls tinyUrls = new TinyUrls("2.00H6RDyC0WKLFi769ec2d56d4GZ5VD");

    @Test
    public void testShortUrl() throws Exception {
        TinyUrlWrapper tuw = tinyUrls.shortUrl("http://www.dajie.com/account/login");
        TinyUrl tu = tuw.getFirst();
        LOG.info("{}", tu.getShortUrl());
    }

    @Test
    public void testExpandUrl() throws Exception {
        TinyUrlWrapper tuw = tinyUrls.expandUrl("http://t.cn/S2CaKm");
        TinyUrl tu = tuw.getFirst();
        LOG.info("{}", tu.getLongUrl());
    }
}
