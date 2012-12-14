package weibo4j;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import weibo4j.model.DirectMessage;

import java.util.Date;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Say something?
 * <pre>
 * User: zhangzhi.cao
 * Date: 12-6-27
 * Time: 下午5:21
 * </pre>
 */
public class DirectMessagesTest {
    private static final Logger LOG = LoggerFactory.getLogger(DirectMessagesTest.class);
    private static final boolean debug = LOG.isDebugEnabled();
    Weibo weibo = WeiboService.getOne();

    @Test
    public void testSendDirectMessage() throws Exception {
        DirectMessage dm = weibo.getDirectMessageService().sendDirectMessage("2399566160", "时不我待啊，" + new Date());
        assertNotNull(dm);
        LOG.info("{}", dm);
        assertTrue(dm.getId() > 0);
    }

    @Test
    public void testSendDirectMessageByScreenName() throws Exception {
        DirectMessage dm = weibo.getDirectMessageService().sendDirectMessageByScreenName(
                "勤快内鬼", "时不我待啊，" + new Date());
        assertNotNull(dm);
        LOG.info("{}", dm);
        assertTrue(dm.getId() > 0);
    }
}
