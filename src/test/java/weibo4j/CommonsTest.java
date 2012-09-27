package weibo4j;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

import static org.junit.Assert.assertNotNull;

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
        assertNotNull(map);
        LOG.info("{}", map);
    }

    @Test
    public void testGetCityNameOfChina() throws Exception {
        String province = "11";
        String city = "14";
        String name = weibo.getCommonService().getCityNameOfChina(province, city);
        assertNotNull(name);
    }

    @Test
    public void testGetProvinceNameOfChina() throws Exception {
        String value = weibo.getCommonService().getProvinceNameOfChina("11");
        assertNotNull(value);
        LOG.info("{}", value);
    }

    @Test
    public void testCodeToLoc() throws Exception {
        Map<String, String> value = weibo.getCommonService().codesToLocation("001");
        assertNotNull(value);
        LOG.info("{}", value);
    }
}
