package weibo4j;

import weibo4j.http.HttpClient;

/**
 * @author sinaWeibo
 */

public class Weibo implements java.io.Serializable {

    private static final long serialVersionUID = 4282616848978535016L;

    public static HttpClient client = new HttpClient();

    /**
     * Sets token information
     *
     * @param token
     */
    public synchronized void setToken(String token) {
        client.setToken(token);
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