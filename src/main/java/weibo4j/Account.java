package weibo4j;

import org.json.JSONObject;
import weibo4j.http.Response;
import weibo4j.model.*;
import weibo4j.util.WeiboConfig;

import java.util.List;

import static weibo4j.util.ParamUtils.convert;
import static weibo4j.util.ParamUtils.get;

/**
 * @author sinaWeibo
 */
public class Account extends Weibo {
    private static final String ACCOUNT_CAREER_API = "account/profile/career.json";
    private static final String ACCOUNT_EDUCATION_API = "account/profile/education.json";
    private static final String CREATE_ACCOUNT_API = "account/profile/basic_update.json";
    private static final long serialVersionUID = -5704133331919248941L;

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
        JSONObject jsonObject = client.post(CREATE_ACCOUNT_API, params).asJSONObject();
        if (null != jsonObject) {
            System.out.println(jsonObject.toString());
            userId = jsonObject.optLong("id", 0);
        }
        return userId;
    }

    /**
     * Gets education info of specified user.
     *
     * @param userId weibo user id
     * @return education info
     *         {
     *         "area" : 0,
     *         "city" : 0,
     *         "department" : "商学院",
     *         "department_id" : 0,
     *         "id" : 11590757,
     *         "is_verified" : "",
     *         "province" : 11,
     *         "school" : "北京工商大学",
     *         "school_id" : 244001,
     *         "type" : 1,
     *         "visible" : 2,
     *         "year" : 2006
     *         }
     * @throws WeiboException api exception
     */
    public List<Education> getAccountEducation(String userId) throws WeiboException {
        Response response = client.get(WeiboConfig.getBaseUrl() + ACCOUNT_EDUCATION_API,
                new HttpParameter[]{
                        new HttpParameter("uid", userId)
                });
        return Education.constructEducations(response);
    }

    /**
     * Get career info of specified user
     *
     * @param userId weibo user id
     * @return json string
     *         {
     *         "city" : 14,
     *         "company" : "国务院办公厅",
     *         "department" : "主任",
     *         "end" : 9999,
     *         "id" : 19447590,
     *         "province" : 11,
     *         "start" : 2008,
     *         "visible" : 2
     *         }
     * @throws WeiboException
     */

    public List<Career> getAccountCareer(String userId) throws WeiboException {
        Response response = client.get(WeiboConfig.getBaseUrl() + ACCOUNT_CAREER_API,
                new HttpParameter[]{
                        new HttpParameter("uid", userId),
                });
        return Career.constructCareers(response);
    }
}
