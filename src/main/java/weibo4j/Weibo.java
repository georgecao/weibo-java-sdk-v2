package weibo4j;

import weibo4j.http.HttpClient;

/**
 * @author sinaWeibo
 */

public class Weibo implements java.io.Serializable {

    private static final long serialVersionUID = 4282616848978535016L;

    public static HttpClient client = new HttpClient();

    public static String clientId;
    public static String clientSecret;
    public static String redirectUrl;

    /**
     * Sets token information
     *
     * @param token
     */
    public synchronized void setToken(String token) {
        client.setToken(token);
    }

    public Weibo(String clientId, String clientSecret, String redirectUrl) {
        Weibo.clientId = clientId;
        Weibo.clientSecret = clientSecret;
        Weibo.redirectUrl = redirectUrl;
    }

    public static HttpClient getClient() {
        return client;
    }

    public static void setClient(HttpClient client) {
        Weibo.client = client;
    }

    public static String getRedirectUrl() {
        return redirectUrl;
    }

    public static void setRedirectUrl(String redirectUrl) {
        Weibo.redirectUrl = redirectUrl;
    }

    public static String getClientId() {
        return clientId;
    }

    public static void setClientId(String clientId) {
        Weibo.clientId = clientId;
    }

    public static String getClientSecret() {
        return clientSecret;
    }

    public static void setClientSecret(String clientSecret) {
        Weibo.clientSecret = clientSecret;
    }

    public Weibo(String accessToken) {
        setToken(accessToken);
    }

    public Weibo() {
    }

    public Users getUserService() {
        return new Users();
    }

    public Account getAccountService() {
        return new Account();
    }

    public Comments getCommentService() {
        return new Comments();
    }

    public Favorite getFavoriteService() {
        return new Favorite();
    }

    public Friendships getFriendshipService() {
        return new Friendships();
    }

    public Oauth getOAuthService() {
        return new Oauth();
    }

    public Search getSearchService() {
        return new Search();
    }

    public Suggestion getSuggestionService() {
        return new Suggestion();
    }

    public Tags getTagService() {
        return new Tags();
    }

    public Timeline getTimelineService() {
        return new Timeline();
    }

    public Trend getTrendService() {
        return new Trend();
    }
}