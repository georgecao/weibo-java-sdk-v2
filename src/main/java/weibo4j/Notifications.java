package weibo4j;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import weibo4j.model.HttpParameter;
import weibo4j.model.NotificationTemplate;
import weibo4j.model.NotificationWrapper;
import weibo4j.model.WeiboException;
import weibo4j.util.ParamUtils;
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
    private static final long serialVersionUID = -190875028810478908L;

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
     * @param tplId         true 	long 	发送通知所对应的模版ID，必须是通过审核，且启用中的模版。
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

    public NotificationWrapper sendNotification(String userIds, long tplId,
                                                String objects1, int objects1Count,
                                                String objects2, int objects2Count,
                                                String objects3, int objects3Count,
                                                String actionUrl) throws WeiboException {
        NotificationTemplate tpl = NotificationTemplate.Builder.newBuilder()
                .userIds(userIds)
                .tplId(tplId)
                .objects1(objects1)
                .objects1Count(objects1Count)
                .objects2(objects2)
                .objects2Count(objects2Count)
                .objects3(objects3)
                .objects3Count(objects3Count)
                .actionUrl(actionUrl)
                .build();
        return sendNotification(tpl);
    }

    public NotificationWrapper sendNotification(NotificationTemplate template) throws WeiboException {
        if (null == template) {
            throw new NullPointerException("notification template is null.");
        }
        HttpParameter[] params = ParamUtils.convert(ParamUtils.get(template));
        if (params.length == 0) {
            throw new IllegalArgumentException("You have not set any parameters yet.");
        }
        return new NotificationWrapper(client.post(
                WeiboConfig.getBaseUrl() + "notification/send.json", params
        ).asJSONObject());
    }

    public NotificationWrapper sendNotification(String userIds, long tplId) throws WeiboException {
        return sendNotification(NotificationTemplate.Builder.newBuilder()
                .userIds(userIds)
                .tplId(tplId)
                .build());
    }
}
