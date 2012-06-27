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
public class DirectMessages {
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
     */
    public DirectMessage sendDirectMessage(String userId, String text) throws WeiboException {
        return new DirectMessage(Weibo.client.post(WeiboConfig.getBaseUrl() + "direct_messages/new.json",
                new PostParameter[]{new PostParameter("user_id", userId),
                        new PostParameter("text", text)}, true).asJSONObject());
    }

    /**
     * 发送一条私信
     *
     * @param screenName 用户的昵称
     * @param text       文本大小必须小于300个汉字。
     * @return DirectMessage
     * @throws WeiboException
     */
    public DirectMessage sendDirectMessageByScreenName(String screenName, String text) throws WeiboException {
        return new DirectMessage(Weibo.client.post(WeiboConfig.getBaseUrl() + "direct_messages/new.json",
                new PostParameter[]{new PostParameter("screen_name", screenName),
                        new PostParameter("text", text)}, true).asJSONObject());
    }
}
