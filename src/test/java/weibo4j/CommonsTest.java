package weibo4j;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * Say something?
 * <pre>
 * User: George
 * Date: 12-9-26
 * Time: 16:25
 * </pre>
 */
public class CommonsTest {
    private static final Logger LOG = LoggerFactory.getLogger(CommonsTest.class);
    private static final boolean DEBUG = LOG.isDebugEnabled();
    Weibo weibo = WeiboService.getOne();

    @Test
    public void testGetCity() throws Exception {
        String province = "001011";
        Map<String, String> map = weibo.getCommonService().getCities(province);
        LOG.info("{}", map);
    }

    @Test
    public void testGetProvinceNameOfChina() throws Exception {
        String value = weibo.getCommonService().getProvinceNameOfChina("11");
        LOG.info("{}", value);
    }

    @Test
    public void testCodeToLoc() throws Exception {
        String value = weibo.getCommonService().codesToLocation("001");
        LOG.info("{}", value);
    }
}
