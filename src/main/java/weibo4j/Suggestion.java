package weibo4j;

import weibo4j.model.*;
import org.json.JSONArray;
import weibo4j.util.WeiboConfig;

public class Suggestion extends Weibo {
    public Suggestion(String accessToken) {
        super(accessToken);
    }

    public Suggestion() {
    }
    //---------------------------------推荐接口---------------------------------------------------

    /**
     * 返回系统推荐的热门用户列表
     *
     * @return list of the users
     * @throws WeiboException when Weibo service or network is unavailable
     * @version weibo4j-V2 1.0.1
     * @see <a
     *      href="http://open.weibo.com/wiki/2/suggestions/users/hot">suggestions/users/hot</a>
     * @since JDK 1.5
     */

    public JSONArray suggestionsUsersHot() throws WeiboException {
        return client.get(WeiboConfig.getValue("baseURL") + "suggestions/users/hot.json").asJSONArray();
    }

    public JSONArray suggestionsUsersHot(String category) throws WeiboException {
        return client.get(WeiboConfig.getValue("baseURL") + "suggestions/users/hot.json", new HttpParameter[]{
                new HttpParameter("category", category)
        }).asJSONArray();
    }

    /**
     * 获取用户可能感兴趣的人
     *
     * @return list of the user's id
     * @throws WeiboException when Weibo service or network is unavailable
     * @version weibo4j-V2 1.0.1
     * @see <a
     *      href="http://open.weibo.com/wiki/2/suggestions/users/may_interested">suggestions/users/may_interested</a>
     * @since JDK 1.5
     */
    public JSONArray suggestionsUsersMayInterested() throws WeiboException {
        return client.get(WeiboConfig.getValue("baseURL") + "suggestions/users/may_interested.json").asJSONArray();
    }

    public JSONArray suggestionsUsersMayInterested(int count, int page) throws WeiboException {
        return client.get(WeiboConfig.getValue("baseURL") + "suggestions/users/may_interested.json", new HttpParameter[]{
                new HttpParameter("count", count),
                new HttpParameter("page", page)
        }).asJSONArray();
    }

    /**
     * 根据一段微博正文推荐相关微博用户
     *
     * @return list of the users
     * @throws WeiboException when Weibo service or network is unavailable
     * @version weibo4j-V2 1.0.1
     * @see <a
     *      href="http://open.weibo.com/wiki/2/suggestions/users/by_status">suggestions/users/by_status</a>
     * @since JDK 1.5
     */
    public UserWrapper suggestionsUsersByStatus(String content) throws WeiboException {
        return User.constructWrapperUsers(client.get(WeiboConfig.getValue("baseURL") + "suggestions/users/by_status.json", new HttpParameter[]{
                new HttpParameter("content", content)
        }));
    }

    public UserWrapper suggestionsUsersByStatus(String content, int num) throws WeiboException {
        return User.constructWrapperUsers(client.get(WeiboConfig.getValue("baseURL") + "suggestions/users/by_status.json", new HttpParameter[]{
                new HttpParameter("content", content),
                new HttpParameter("num", num)
        }));
    }

    /**
     * 获取微博精选推荐
     *
     * @return list of the status
     * @throws WeiboException when Weibo service or network is unavailable
     * @version weibo4j-V2 1.0.1
     * @see <a
     *      href="http://open.weibo.com/wiki/2/suggestions/statuses/hot">suggestions/statuses/hot</a>
     * @since JDK 1.5
     */
    public StatusWrapper suggestionsStatusesHot(int type, int isPic) throws WeiboException {
        return Status.constructWrapperStatus(client.get(WeiboConfig.getValue("baseURL") + "suggestions/statuses/hot.json", new HttpParameter[]{
                new HttpParameter("type", type),
                new HttpParameter("is_pic", isPic)
        }));
    }

    public StatusWrapper suggestionsStatusesHot(int type, int isPic, Paging page) throws WeiboException {
        return Status.constructWrapperStatus(client.get(WeiboConfig.getValue("baseURL") + "suggestions/statuses/hot.json", new HttpParameter[]{
                new HttpParameter("type", type),
                new HttpParameter("is_pic", isPic)
        }, page));
    }

    /**
     * 返回系统推荐的热门收藏
     *
     * @return list of the status
     * @throws WeiboException when Weibo service or network is unavailable
     * @version weibo4j-V2 1.0.1
     * @see <a
     *      href="http://open.weibo.com/wiki/2/suggestions/favorites/hot">suggestions/favorites/hot</a>
     * @since JDK 1.5
     */
    public JSONArray suggestionsFavoritesHot() throws WeiboException {
        return client.get(WeiboConfig.getValue("baseURL") + "suggestions/favorites/hot.json").asJSONArray();
    }

    public JSONArray suggestionsFavoritesHot(int page, int count) throws WeiboException {
        return client.get(WeiboConfig.getValue("baseURL") + "suggestions/favorites/hot.json", new HttpParameter[]{
                new HttpParameter("page", page),
                new HttpParameter("count", count)
        }).asJSONArray();
    }

    /**
     * 把某人标识为不感兴趣的人
     *
     * @return user
     * @throws WeiboException when Weibo service or network is unavailable
     * @version weibo4j-V2 1.0.1
     * @see <a
     *      href="http://open.weibo.com/wiki/2/suggestions/users/not_interested">suggestions/users/not_interested</a>
     * @since JDK 1.5
     */
    public User suggestionsUsersNotInterested(String uid) throws WeiboException {
        return new User(client.post(WeiboConfig.getValue("baseURL") + "suggestions/users/not_interested.json", new HttpParameter[]{
                new HttpParameter("uid", uid)
        }).asJSONObject());
    }
}
