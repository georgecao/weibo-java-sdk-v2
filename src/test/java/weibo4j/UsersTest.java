package weibo4j;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import weibo4j.model.User;

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
        User u = weibo.getUserService().showUserById("1472483604");
        LOG.info("{}", u.getBiFollowersCount());
    }
}
