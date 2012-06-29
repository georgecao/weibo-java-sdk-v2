package weibo4j;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

    @Test
    public void testSendDirectMessage() throws Exception {
        Weibo weibo = new Weibo("");
        weibo.getDirectMessageService().sendDirectMessage("2323", "1111111111");
    }

    @Test
    public void testSendDirectMessageByScreenName() throws Exception {

    }
}
