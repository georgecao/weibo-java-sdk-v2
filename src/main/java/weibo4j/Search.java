package weibo4j;

import org.json.JSONArray;
import weibo4j.model.HttpParameter;
import weibo4j.model.Status;
import weibo4j.model.StatusWrapper;
import weibo4j.model.WeiboException;
import weibo4j.util.WeiboConfig;

public class Search extends Weibo {
    private static final long serialVersionUID = -5152574430355165658L;

    public Search(String accessToken) {
        super(accessToken);
    }

    public Search() {
    }
    //---------------------------------搜索接口-----------------------------------------------

    public JSONArray searchSuggestionsUsers(String q) throws WeiboException {
        return client.get(WeiboConfig.getValue("baseURL") + "search/suggestions/users.json", new HttpParameter[]{
                new HttpParameter("q", q)
        }).asJSONArray();
    }

    public JSONArray searchSuggestionsUsers(String q, int count) throws WeiboException {
        return client.get(WeiboConfig.getValue("baseURL") + "search/suggestions/users.json", new HttpParameter[]{
                new HttpParameter("q", q),
                new HttpParameter("count", count)
        }).asJSONArray();
    }

    public JSONArray searchSuggestionsStatuses(String q) throws WeiboException {
        return client.get(WeiboConfig.getValue("baseURL") + "search/suggestions/statuses.json", new HttpParameter[]{
                new HttpParameter("q", q)
        }).asJSONArray();
    }

    public JSONArray searchSuggestionsStatuses(String q, int count) throws WeiboException {
        return client.get(WeiboConfig.getValue("baseURL") + "search/suggestions/statuses.json", new HttpParameter[]{
                new HttpParameter("q", q),
                new HttpParameter("count", count)
        }).asJSONArray();
    }

    public JSONArray searchSuggestionsCompanies(String q) throws WeiboException {
        return client.get(WeiboConfig.getValue("baseURL") + "search/suggestions/companies.json", new HttpParameter[]{
                new HttpParameter("q", q)
        }).asJSONArray();
    }

    public JSONArray searchSuggestionsCompanies(String q, int count) throws WeiboException {
        return client.get(WeiboConfig.getValue("baseURL") + "search/suggestions/companies.json", new HttpParameter[]{
                new HttpParameter("q", q),
                new HttpParameter("count", count)
        }).asJSONArray();
    }

    public JSONArray searchSuggestionsApps(String q) throws WeiboException {
        return client.get(WeiboConfig.getValue("baseURL") + "search/suggestions/apps.json", new HttpParameter[]{
                new HttpParameter("q", q)
        }).asJSONArray();
    }

    public JSONArray searchSuggestionsApps(String q, int count) throws WeiboException {
        return client.get(WeiboConfig.getValue("baseURL") + "search/suggestions/apps.json", new HttpParameter[]{
                new HttpParameter("q", q),
                new HttpParameter("count", count)
        }).asJSONArray();
    }

    public JSONArray searchSuggestionsSchools(String q) throws WeiboException {
        return client.get(WeiboConfig.getValue("baseURL") + "search/suggestions/schools.json", new HttpParameter[]{
                new HttpParameter("q", q)
        }).asJSONArray();
    }

    public JSONArray searchSuggestionsSchools(String q, int count, int type) throws WeiboException {
        return client.get(WeiboConfig.getValue("baseURL") + "search/suggestions/schools.json", new HttpParameter[]{
                new HttpParameter("q", q),
                new HttpParameter("count", count),
                new HttpParameter("type", type)
        }).asJSONArray();
    }

    public JSONArray searchSuggestionsAtUsers(String q, int type) throws WeiboException {
        return client.get(WeiboConfig.getValue("baseURL") + "search/suggestions/at_users.json", new HttpParameter[]{
                new HttpParameter("q", q),
                new HttpParameter("type", type)
        }).asJSONArray();
    }

    public JSONArray searchSuggestionsAtUsers(String q, int count, int type, int range) throws WeiboException {
        return client.get(WeiboConfig.getValue("baseURL") + "search/suggestions/at_users.json", new HttpParameter[]{
                new HttpParameter("q", q),
                new HttpParameter("count", count),
                new HttpParameter("type", type),
                new HttpParameter("range", range)
        }).asJSONArray();
    }

    /**
     * Search topic
     *
     * @param q     topic
     * @param page  page number , default 1
     * @param count page size ,max 50, default 20
     * @return a list of statuses
     * @throws WeiboException api exception
     */
    public StatusWrapper searchTopic(String q, int page, int count) throws WeiboException {
        return Status.constructWrapperStatus(client.get(WeiboConfig.getBaseUrl() + "/search/topics",
                new HttpParameter[]{
                        new HttpParameter("q", q),
                        new HttpParameter("count", count),
                        new HttpParameter("page", page)
                }));
    }
}
