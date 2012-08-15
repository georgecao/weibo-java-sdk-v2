package weibo4j;

import weibo4j.model.*;
import weibo4j.org.json.JSONObject;
import weibo4j.util.WeiboConfig;

import java.util.List;

import static weibo4j.util.ParamUtils.convert;
import static weibo4j.util.ParamUtils.get;

/**
 * @author sinaWeibo
 */
public class Account extends Weibo {
    private static final String CREATE_ACCOUNT_URL = "http://i2.api.weibo.com/2/account/profile/basic_update.json ";

    public Account(String accessToken) {
        super(accessToken);
    }

    public Account() {
    }

    /**
     * OAuth授权之后，获取授权用户的UID
     *
     * @return uid
     * @throws WeiboException when Weibo service or network is unavailable
     * @version weibo4j-V2 1.0.1
     * @see <a
     *      href="http://open.weibo.com/wiki/2/account/get_uid">account/get_uid</a>
     * @since JDK 1.5
     */
    public JSONObject getUid() throws WeiboException {
        return client.get(
                WeiboConfig.getValue("baseURL") + "account/get_uid.json")
                .asJSONObject();
    }

    /**
     * 获取当前登录用户的隐私设置
     *
     * @return User's privacy
     * @throws WeiboException when Weibo service or network is unavailable
     * @version weibo4j-V2 1.0.1
     * @see <a
     *      href="http://open.weibo.com/wiki/2/account/get_privacy">account/get_privacy</a>
     * @since JDK 1.5
     */
    public JSONObject getAccountPrivacy() throws WeiboException {
        return client.get(
                WeiboConfig.getValue("baseURL") + "account/get_privacy.json")
                .asJSONObject();
    }

    /**
     * 获取所有学校列表
     *
     * @return list of school
     * @throws WeiboException when Weibo service or network is unavailable
     * @version weibo4j-V2 1.0.1
     * @see <a
     *      href="http://open.weibo.com/wiki/2/account/profile/school_list">account/profile/school_list</a>
     * @since JDK 1.5
     */
    public List<School> getAccountProfileSchoolList() throws WeiboException {
        return School.constructSchool(client.get(WeiboConfig
                .getValue("baseURL") + "account/profile/school_list.json"));
    }

    /**
     * 获取所有学校列表
     *
     * @param province ,city,area,type,capital,keyword,count
     * @return list of school
     * @throws WeiboException when Weibo service or network is unavailable
     * @version weibo4j-V2 1.0.1
     * @see <a
     *      href="http://open.weibo.com/wiki/2/account/profile/school_list">account/profile/school_list</a>
     * @since JDK 1.5
     */
    public List<School> getAccountProfileSchoolList(Integer province,
                                                    Integer city, Integer area, Integer type, String capital,
                                                    String keyword, Integer count) throws WeiboException {
        return School.constructSchool(client.get(
                WeiboConfig.getValue("baseURL")
                        + "account/profile/school_list.json",
                new HttpParameter[]{
                        new HttpParameter("province", province.toString()),
                        new HttpParameter("city", city.toString()),
                        new HttpParameter("area", area.toString()),
                        new HttpParameter("type", type.toString()),
                        new HttpParameter("capital", capital),
                        new HttpParameter("keyword", keyword),
                        new HttpParameter("count", count.toString())}));
    }

    /**
     * 获取当前登录用户的API访问频率限制情况
     *
     * @return ratelimit
     * @throws WeiboException when Weibo service or network is unavailable
     * @version weibo4j-V2 1.0.1
     * @see <a
     *      href="http://open.weibo.com/wiki/2/account/rate_limit_status">account/rate_limit_status</a>
     * @since JDK 1.5
     */
    public RateLimitStatus getAccountRateLimitStatus() throws WeiboException {
        return new RateLimitStatus(client.get(WeiboConfig
                .getValue("baseURL") + "account/rate_limit_status.json"));
    }

    public Long createAccount(AccountUser accountUser) throws WeiboException {
        Long userId = 0L;
        HttpParameter[] params = convert(get(accountUser));
        if (null == params || params.length == 0) {
            return userId;
        }
        JSONObject jsonObject = client.post(CREATE_ACCOUNT_URL, params).asJSONObject();
        if (null != jsonObject) {
            System.out.println(jsonObject.toString());
            userId = jsonObject.optLong("id", 0);
        }
        return userId;
    }
}
