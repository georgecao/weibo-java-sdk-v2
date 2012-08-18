package weibo4j;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import weibo4j.model.User;

import static org.junit.Assert.assertTrue;

/**
 * Say something?
 * <pre>
 * User: George
 * Date: 12-8-17
 * Time: 下午6:00
 * </pre>
 */
public class UsersTest {
    private static final Logger LOG = LoggerFactory.getLogger(UsersTest.class);
    private static final boolean debug = LOG.isDebugEnabled();

    Weibo weibo = WeiboService.getOne();

    @Test
    public void testShowUserById() throws Exception {
        for (int i = 0; i < 200; i++) {
            User u = weibo.getUserService().showUserById("1092478650");
            LOG.info("{}", u.getBiFollowersCount());
            assertTrue(u.getBiFollowersCount() > 0);
        }
    }
}
