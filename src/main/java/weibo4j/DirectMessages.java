package weibo4j;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import weibo4j.model.DirectMessage;
import weibo4j.model.PostParameter;
import weibo4j.model.WeiboException;
import weibo4j.util.WeiboConfig;

/**
 * Say something?
 * <pre>
 * User: zhangzhi.cao
 * Date: 12-6-27
 * Time: 下午4:20
 * </pre>
 */
public class DirectMessages extends Weibo {
    public DirectMessages(String accessToken) {
        super(accessToken);
    }

    public DirectMessages() {
    }

    private static final Logger LOG = LoggerFactory.getLogger(DirectMessages.class);
    private static final boolean debug = LOG.isDebugEnabled();

    /**
     * 发送一条私信
     *
     * @param userId 用户的userid
     * @param text   文本大小必须小于300个汉字。
     * @return DirectMessage
     * @throws WeiboException when Weibo service or network is unavailable
     * @see <a href="http://open.t.sina.com.cn/wiki/index.php/Direct_messages/new">direct messages/sent </a>
     * @see #sendDirectMessage(String, String, Long)
     */
    public DirectMessage sendDirectMessage(String userId, String text) throws WeiboException {
        return new DirectMessage(client.post(WeiboConfig.getBaseUrl() + "direct_messages/new.json",
                new PostParameter[]{new PostParameter("uid", userId),
                        new PostParameter("text", text)}, true).asJSONObject());
    }

    /**
     * Send DM.
     *
     * @param userId   私信接收方的用户UID。
     * @param text     要发送的私信内容，需要做URLencode，内容小于300个汉字
     * @param statusId 需要发送的微博ID。
     * @return DM
     * @throws WeiboException
     */
    public DirectMessage sendDirectMessage(String userId, String text, Long statusId) throws WeiboException {
        return new DirectMessage(client.post(WeiboConfig.getBaseUrl() + "direct_messages/new.json",
                new PostParameter[]{new PostParameter("uid", userId),
                        new PostParameter("text", text),
                        new PostParameter("id", statusId)}, true).asJSONObject());
    }

    /**
     * 发送一条私信
     *
     * @param screenName 用户的昵称
     * @param text       文本大小必须小于300个汉字。
     * @return DirectMessage
     * @throws WeiboException
     * @see #sendDirectMessageByScreenName(String, String, Long)
     */
    public DirectMessage sendDirectMessageByScreenName(String screenName, String text) throws WeiboException {
        return new DirectMessage(client.post(WeiboConfig.getBaseUrl() + "direct_messages/new.json",
                new PostParameter[]{new PostParameter("screen_name", screenName),
                        new PostParameter("text", text)}, true).asJSONObject());
    }


    /**
     * Send DM.
     *
     * @param screenName 私信接收方的微博昵称。。
     * @param text       要发送的私信内容，需要做URLencode，内容小于300个汉字
     * @param statusId   需要发送的微博ID。
     * @return DM
     * @throws WeiboException
     */
    public DirectMessage sendDirectMessageByScreenName(String screenName, String text, Long statusId) throws WeiboException {
        return new DirectMessage(client.post(WeiboConfig.getBaseUrl() + "direct_messages/new.json",
                new PostParameter[]{new PostParameter("screen_name", screenName),
                        new PostParameter("text", text),
                        new PostParameter("id", statusId)}, true).asJSONObject());
    }
}
