package weibo4j;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import weibo4j.model.ImageItem;
import weibo4j.model.Status;
import weibo4j.model.StatusWrapper;
import weibo4j.model.User;
import weibo4j.model.UserWrapper;

import java.io.File;
import java.io.FileInputStream;
import java.util.Date;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

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
    Weibo weibo = WeiboService.getOne();

    @Test
    public void testGetStatusCount() throws Exception {
        Timeline tl = weibo.getTimelineService();
        StatusWrapper wrapper = tl.getHomeTimeline();
        assertNotNull(wrapper);
        LOG.info("{}", wrapper);
    }

    @Test
    public void testGetUser() throws Exception {
        User u = weibo.getUserService().showUserById("1246205697");
        LOG.info("{}", u);
        assertNotNull(u);
    }

    @Test
    public void testGetFriends() throws Exception {
        UserWrapper w = weibo.getFriendshipService().getFriendsBilateral("1246205697");
        assertNotNull(w);
        LOG.info("{}", w.getTotalNumber());
    }

    @Test
    public void testUpdateStatus() throws Exception {
        Status status =
                weibo.getTimelineService().updateStatus("时不我待啊" + new Date());
        assertNotNull(status);
        assertTrue(Long.valueOf(status.getId()) > 0);
    }

    @Test
    public void testUpdateStatusWithImage() throws Exception {
        byte[] content = new byte[0];
        File image = new File("D:\\download\\test.jpg");
        FileInputStream in = new FileInputStream(image);
        byte[] buffer = new byte[8192];
        int count = 0;
        while ((count = in.read(buffer)) != -1) {
            byte[] b = new byte[content.length + count];
            System.arraycopy(content, 0, b, 0, content.length);
            System.arraycopy(buffer, 0, b, content.length, count);
            content = b;
        }
        ImageItem item = new ImageItem(content);
        Status status =
                weibo.getTimelineService().uploadStatus("时不我待啊" + new Date(), item);
        assertNotNull(status);
        assertTrue(Long.valueOf(status.getId()) > 0);
    }
}
