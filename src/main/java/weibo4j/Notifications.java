package weibo4j;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import weibo4j.model.Notification;
import weibo4j.model.PostParameter;
import weibo4j.model.WeiboException;
import weibo4j.util.WeiboConfig;

/**
 * Say something?
 * <pre>
 * User: zhangzhi.cao
 * Date: 12-6-7
 * Time: 上午10:11
 * </pre>
 */
public class Notifications extends Weibo {
    public Notifications(String accessToken) {
        super(accessToken);
    }

    private static final Logger LOG = LoggerFactory.getLogger(Notifications.class);
    private static final boolean debug = LOG.isDebugEnabled();

    private static volatile Notifications in;

    private Notifications() {
    }

    public static Notifications getInstance() {
        if (null == in) {
            synchronized (Notifications.class) {
                if (null == in) {
                    in = new Notifications();
                }
            }
        }
        return in;
    }

    /**
     * Send notification
     *
     * @param userIds       true 	string 	接收通知的用户UID，支持1-100个用户，用逗号分隔。
     * @param tplId         true 	int 	发送通知所对应的模版ID，必须是通过审核，且启用中的模版。
     * @param objects1      false 	string 	通知的自定义变量1，可以是人也可以是系统实物，支持@用户昵称，最多不超过32个字节。
     * @param objects1Count false 	int 	通知的自定义变量1的数量，正整数，最多不超过2个字节，支持0-99。
     * @param objects2      false 	string 	通知的自定义变量2，可以是人也可以是系统实物，支持@用户昵称，最多不超过32个字节。
     * @param objects2Count false 	int 	通知的自定义变量2的数量，正整数，最多不超过2个字节，支持0-99。
     * @param objects3      false 	string 	通知的自定义变量3，可以是人也可以是系统实物，支持@用户昵称，最多不超过32个字节。
     * @param objects3Count false 	int 	通知的自定义变量3的数量，正整数，最多不超过2个字节，支持0-99。
     * @param actionUrl     false 	string 	通知的处理链接，支持短链接，最多不超过20个字节。
     * @return
     * @throws WeiboException
     */

    public Notification sendNotification(String userIds, int tplId,
                                         String objects1, int objects1Count,
                                         String objects2, int objects2Count,
                                         String objects3, int objects3Count,
                                         String actionUrl) throws WeiboException {
        return new Notification(client.post(
                WeiboConfig.getBaseUrl() + "", new PostParameter[]{
                new PostParameter("uids", userIds),
                new PostParameter("tpl_id", tplId),
                new PostParameter("object1", objects1),
                new PostParameter("objects1_count", objects1Count),
                new PostParameter("object2", objects2),
                new PostParameter("objects2_count", objects2Count),
                new PostParameter("object3", objects3),
                new PostParameter("objects3_count", objects3Count),
                new PostParameter("action_url", actionUrl)
        }
        ).asJSONObject());
    }

    public Notification sendNotification(String userIds, int tplId) throws WeiboException {
        return new Notification(client.post(
                WeiboConfig.getBaseUrl() + "", new PostParameter[]{
                new PostParameter("uids", userIds),
                new PostParameter("tpl_id", tplId)
        }
        ).asJSONObject());
    }
}
