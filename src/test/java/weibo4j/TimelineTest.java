package weibo4j;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import weibo4j.model.User;
import weibo4j.model.UserWrapper;

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
    Weibo weibo = new Weibo("2.00m8ATIB0X69K40e3374481asw5TbB");

    @Test
    public void testGetStatusCount() throws Exception {
        Timeline tl = weibo.getTimelineService();
        tl.getHomeTimeline();

    }

    @Test
    public void testGetUser() throws Exception {
        User u = weibo.getUserService().showUserById("1246205697");
        LOG.info("{}", u);
    }

    @Test
    public void testGetFriends() throws Exception {
        UserWrapper w =weibo.getFriendshipService().getFriendsBilateral("1246205697");
        LOG.info("{}",w.getTotalNumber());
    }
}
