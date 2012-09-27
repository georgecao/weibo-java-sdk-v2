package weibo4j;

import weibo4j.http.HttpClient;

/**
 * @author sinaWeibo
 */

public class Weibo implements java.io.Serializable {

    private static final long serialVersionUID = 4282616848978535016L;

    protected HttpClient client = new HttpClient();
    public static String clientId;
    public static String clientSecret;
    public static String redirectUrl;
    private String token;

    public Weibo(String clientId, String clientSecret, String redirectUrl) {
        Weibo.clientId = clientId;
        Weibo.clientSecret = clientSecret;
        Weibo.redirectUrl = redirectUrl;
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
        this.token = accessToken;
        client.setToken(accessToken);
    }

    /**
     * Sometimes some APIs do not need any credentials.
     * Then you should use this constructor instead.
     */
    public Weibo() {
    }

    public Users getUserService() {
        return new Users(token);
    }

    public DirectMessages getDirectMessageService() {
        return new DirectMessages(token);
    }

    public Account getAccountService() {
        return new Account(token);
    }

    public Comments getCommentService() {
        return new Comments(token);
    }

    public Favorite getFavoriteService() {
        return new Favorite(token);
    }

    public Friendships getFriendshipService() {
        return new Friendships(token);
    }

    public OAuth getOAuthService() {
        return new OAuth(token);
    }

    public Search getSearchService() {
        return new Search(token);
    }

    public Suggestion getSuggestionService() {
        return new Suggestion(token);
    }

    public Tags getTagService() {
        return new Tags(token);
    }

    public Timeline getTimelineService() {
        return new Timeline(token);
    }

    public Trend getTrendService() {
        return new Trend(token);
    }

    public TinyUrls getTinyUrlService() {
        return new TinyUrls(token);
    }

    public Commons getCommonService() {
        return new Commons(token);
    }

    public Notifications getNotificationService() {
        return new Notifications(token);
    }
}