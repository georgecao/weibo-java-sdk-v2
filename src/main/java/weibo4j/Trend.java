package weibo4j;

import weibo4j.model.*;
import weibo4j.org.json.JSONException;
import weibo4j.org.json.JSONObject;
import weibo4j.util.WeiboConfig;

import java.util.List;

public class Trend extends Weibo {

    /*----------------------------话题接口----------------------------------------*/

    /**
     * 获取某人的话题列表
     *
     * @param uid 需要获取话题的用户的UID
     * @return list of the userTrend
     * @throws WeiboException when Weibo service or network is unavailable
     * @version weibo4j-V2 1.0.1
     * @see <a href="http://open.weibo.com/wiki/2/trends">trends</a>
     * @since JDK 1.5
     */
    public List<UserTrend> getTrends(String uid) throws WeiboException {
        return UserTrend
                .constructTrendList(client.get(
                        WeiboConfig.getValue("baseURL") + "trends.json",
                        new PostParameter[]{new PostParameter("uid", uid)}));
    }

    /**
     * 获取某人的话题列表
     *
     * @param uid  需要获取话题的用户的UID
     * @param page 返回结果的页码，默认为1
     * @return list of the userTrend
     * @throws WeiboException when Weibo service or network is unavailable
     * @version weibo4j-V2 1.0.1
     * @see <a href="http://open.weibo.com/wiki/2/trends">trends</a>
     * @since JDK 1.5
     */
    public List<UserTrend> getTrends(String uid, Paging page)
            throws WeiboException {
        return UserTrend
                .constructTrendList(client.get(
                        WeiboConfig.getValue("baseURL") + "trends.json",
                        new PostParameter[]{new PostParameter("uid", uid)}, page));
    }

    /**
     * 判断当前用户是否关注某话题
     *
     * @param trend_name 话题关键字，必须做URLencode
     * @return jsonobject
     * @throws WeiboException when Weibo service or network is unavailable
     * @throws JSONException
     * @version weibo4j-V2 1.0.1
     * @see <a
     *      href="http://open.weibo.com/wiki/2/trends/is_follow">trends/is_follow</a>
     * @since JDK 1.5
     */
    public JSONObject isFollow(String trend_name) throws WeiboException {
        return client.get(WeiboConfig.getValue("baseURL") + "trends/is_follow.json",
                new PostParameter[]{
                        new PostParameter("trend_name", trend_name)}).asJSONObject();
    }

    /**
     * 返回最近一小时内的热门话题
     *
     * @return list of trends
     * @throws WeiboException when Weibo service or network is unavailable
     * @throws JSONException
     * @version weibo4j-V2 1.0.1
     * @see <a
     *      href="http://open.weibo.com/wiki/2/trends/hourly">trends/hourly</a>
     * @since JDK 1.5
     */
    public List<Trends> getTrendsHourly() throws WeiboException {
        return Trends.constructTrendsList(client.get(
                WeiboConfig.getValue("baseURL") + "trends/hourly.json"));
    }

    public List<Trends> getTrendsHourly(Integer base_app) throws WeiboException {
        return Trends.constructTrendsList(client.get(
                WeiboConfig.getValue("baseURL") + "trends/hourly.json",
                new PostParameter[]{new PostParameter("base_app", base_app.toString())}));
    }

    /**
     * 返回最近一天内的热门话题
     *
     * @return list of trends
     * @throws WeiboException when Weibo service or network is unavailable
     * @throws JSONException
     * @version weibo4j-V2 1.0.1
     * @see <a href="http://open.weibo.com/wiki/2/trends/daily">trends/daily</a>
     * @since JDK 1.5
     */
    public List<Trends> getTrendsDaily() throws WeiboException {
        return Trends.constructTrendsList(client.get(
                WeiboConfig.getValue("baseURL") + "trends/daily.json"));
    }

    public List<Trends> getTrendsDaily(Integer base_app) throws WeiboException {
        return Trends.constructTrendsList(client.get(
                WeiboConfig.getValue("baseURL") + "trends/daily.json",
                new PostParameter[]{new PostParameter("base_app", base_app
                        .toString())}));
    }

    /**
     * 返回最近一周内的热门话题
     *
     * @return list of trends
     * @throws WeiboException when Weibo service or network is unavailable
     * @throws JSONException
     * @version weibo4j-V2 1.0.1
     * @see <a
     *      href="http://open.weibo.com/wiki/2/trends/weekly">trends/weekly</a>
     * @since JDK 1.5
     */
    public List<Trends> getTrendsWeekly() throws WeiboException {
        return Trends.constructTrendsList(client.get(
                WeiboConfig.getValue("baseURL") + "trends/weekly.json"));
    }

    public List<Trends> getTrendsWeekly(Integer base_app) throws WeiboException {
        return Trends.constructTrendsList(client.get(
                WeiboConfig.getValue("baseURL") + "trends/weekly.json",
                new PostParameter[]{new PostParameter("base_app", base_app.toString())}));
    }

    /**
     * 关注某话题
     *
     * @param trend_name 要关注的话题关键词。
     * @return UserTrend
     * @throws WeiboException when Weibo service or network is unavailable
     * @throws JSONException
     * @version weibo4j-V2 1.0.1
     * @see <a
     *      href="http://open.weibo.com/wiki/2/trends/follow">trends/follow</a>
     * @since JDK 1.5
     */
    public UserTrend trendsFollow(String trend_name) throws WeiboException {
        return new UserTrend(client.post(WeiboConfig.getValue("baseURL")
                + "trends/follow.json",
                new PostParameter[]{new PostParameter("trend_name",
                        trend_name)}));
    }

    /**
     * 取消对某话题的关注
     *
     * @param trend_id 要取消关注的话题ID
     * @return jsonobject
     * @throws WeiboException when Weibo service or network is unavailable
     * @throws JSONException
     * @version weibo4j-V2 1.0.1
     * @see <a
     *      href="http://open.weibo.com/wiki/2/trends/destroy">trends/destroy</a>
     * @since JDK 1.5
     */
    public JSONObject trendsDestroy(Integer trend_id) throws WeiboException {
        return client.post(WeiboConfig.getValue("baseURL")
                + "trends/destroy.json",
                new PostParameter[]{new PostParameter("trend_id", trend_id.toString())}).asJSONObject();
    }

}
