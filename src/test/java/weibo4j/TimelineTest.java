package weibo4j;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import weibo4j.model.Count;

import java.util.List;

/**
 * Say something?
 * <pre>
 * User: zhangzhi.cao
 * Date: 12-5-14
 * Time: 上午11:06
 * </pre>
 */
public class TimelineTest {
    private static final Logger LOG = LoggerFactory.getLogger(TimelineTest.class);
    private static final boolean debug = LOG.isDebugEnabled();

    @Test
    public void testGetStatusCount() throws Exception {
        Weibo weibo = new Weibo("2.00tBx13B0X69K483e08dfec6dC7XHE");
        List<Count> list = weibo.getTimelineService().getStatusCount("3445546242703367");
        LOG.info("{}", list);
    }
}
