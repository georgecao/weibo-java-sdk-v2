package weibo4j;

import weibo4j.model.PostParameter;
import weibo4j.model.Status;
import weibo4j.model.StatusWrapper;
import weibo4j.model.WeiboException;
import weibo4j.org.json.JSONArray;
import weibo4j.util.WeiboConfig;

public class Search extends Weibo {
    //---------------------------------搜索接口-----------------------------------------------

    public JSONArray searchSuggestionsUsers(String q) throws WeiboException {
        return client.get(WeiboConfig.getValue("baseURL") + "search/suggestions/users.json", new PostParameter[]{
                new PostParameter("q", q)
        }).asJSONArray();
    }

    public JSONArray searchSuggestionsUsers(String q, int count) throws WeiboException {
        return client.get(WeiboConfig.getValue("baseURL") + "search/suggestions/users.json", new PostParameter[]{
                new PostParameter("q", q),
                new PostParameter("count", count)
        }).asJSONArray();
    }

    public JSONArray searchSuggestionsStatuses(String q) throws WeiboException {
        return client.get(WeiboConfig.getValue("baseURL") + "search/suggestions/statuses.json", new PostParameter[]{
                new PostParameter("q", q)
        }).asJSONArray();
    }

    public JSONArray searchSuggestionsStatuses(String q, int count) throws WeiboException {
        return client.get(WeiboConfig.getValue("baseURL") + "search/suggestions/statuses.json", new PostParameter[]{
                new PostParameter("q", q),
                new PostParameter("count", count)
        }).asJSONArray();
    }

    public JSONArray searchSuggestionsCompanies(String q) throws WeiboException {
        return client.get(WeiboConfig.getValue("baseURL") + "search/suggestions/companies.json", new PostParameter[]{
                new PostParameter("q", q)
        }).asJSONArray();
    }

    public JSONArray searchSuggestionsCompanies(String q, int count) throws WeiboException {
        return client.get(WeiboConfig.getValue("baseURL") + "search/suggestions/companies.json", new PostParameter[]{
                new PostParameter("q", q),
                new PostParameter("count", count)
        }).asJSONArray();
    }

    public JSONArray searchSuggestionsApps(String q) throws WeiboException {
        return client.get(WeiboConfig.getValue("baseURL") + "search/suggestions/apps.json", new PostParameter[]{
                new PostParameter("q", q)
        }).asJSONArray();
    }

    public JSONArray searchSuggestionsApps(String q, int count) throws WeiboException {
        return client.get(WeiboConfig.getValue("baseURL") + "search/suggestions/apps.json", new PostParameter[]{
                new PostParameter("q", q),
                new PostParameter("count", count)
        }).asJSONArray();
    }

    public JSONArray searchSuggestionsSchools(String q) throws WeiboException {
        return client.get(WeiboConfig.getValue("baseURL") + "search/suggestions/schools.json", new PostParameter[]{
                new PostParameter("q", q)
        }).asJSONArray();
    }

    public JSONArray searchSuggestionsSchools(String q, int count, int type) throws WeiboException {
        return client.get(WeiboConfig.getValue("baseURL") + "search/suggestions/schools.json", new PostParameter[]{
                new PostParameter("q", q),
                new PostParameter("count", count),
                new PostParameter("type", type)
        }).asJSONArray();
    }

    public JSONArray searchSuggestionsAtUsers(String q, int type) throws WeiboException {
        return client.get(WeiboConfig.getValue("baseURL") + "search/suggestions/at_users.json", new PostParameter[]{
                new PostParameter("q", q),
                new PostParameter("type", type)
        }).asJSONArray();
    }

    public JSONArray searchSuggestionsAtUsers(String q, int count, int type, int range) throws WeiboException {
        return client.get(WeiboConfig.getValue("baseURL") + "search/suggestions/at_users.json", new PostParameter[]{
                new PostParameter("q", q),
                new PostParameter("count", count),
                new PostParameter("type", type),
                new PostParameter("range", range)
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
        return Status.constructWapperStatus(client.get(WeiboConfig.getBaseUrl() + "/search/topics",
                new PostParameter[]{
                        new PostParameter("q", q),
                        new PostParameter("count", count),
                        new PostParameter("page", page)
                }));
    }
}
