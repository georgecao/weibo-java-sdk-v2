package weibo4j;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import weibo4j.model.Paging;
import weibo4j.model.UserWrapper;

/**
 * Say something?
 * <pre>
 * User: George
 * Date: 12-8-17
 * Time: 下午6:08
 * </pre>
 */
public class FriendshipsTest {
    private static final Logger LOG = LoggerFactory.getLogger(FriendshipsTest.class);
    private static final boolean debug = LOG.isDebugEnabled();
    Weibo weibo = WeiboService.getOne();

    @Test
    public void testGetFriends() throws Exception {
        UserWrapper friends = weibo.getFriendshipService().getFriendsBilateral("1472483604",
                0,
                new Paging(1, 200));
        LOG.warn("{}", friends.getUsers().size());
    }
}
