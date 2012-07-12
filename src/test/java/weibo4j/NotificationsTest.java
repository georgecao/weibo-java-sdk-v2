package weibo4j;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import weibo4j.model.NotificationTemplate;
import weibo4j.model.NotificationWrapper;

/**
 * Say something?
 * <pre>
 * User: George
 * Date: 12-7-11
 * Time: 下午6:46
 * </pre>
 */
public class NotificationsTest {
    private static final Logger LOG = LoggerFactory.getLogger(NotificationsTest.class);
    private static final boolean debug = LOG.isDebugEnabled();
    private long[] tplIds = new long[]{124296493511857021L,
            124296081228530561L,
            124295943823098658L
    };

    Notifications notifications = new Notifications("2.00H6RDyC0WKLFi769ec2d56d4GZ5VD");

    @Test
    public void testGetInstance() throws Exception {
        NotificationWrapper nw = notifications.sendNotification(NotificationTemplate.Builder.newBuilder()
                .userIds("2727390203")
                .tplId(124296493511857021L)
                .objects1("@luo-bert")
                .objects2("软件工程师")
                .objects3("2012年7月12日 11:05")
                .actionUrl("http://t.cn/zOX2qqI")
                .build());
        LOG.info("{}", nw);
    }

    @Test
    public void testSendNotificationFull() throws Exception {
        String userIds = "2719627713";
        NotificationTemplate template = NotificationTemplate.Builder.newBuilder()
                .userIds(userIds)
                .tplId(tplIds[1])
                .objects1("@nick")
                .actionUrl("https://dajie.com.cn3")
                .build();
        notifications.sendNotification(template);
    }

    @Test
    public void testSendNotification() throws Exception {

    }
}
