package weibo4j.model;

import org.json.JSONObject;

import java.io.Serializable;

/**
 * Say something?
 * <pre>
 * User: George
 * Date: 12-7-12
 * Time: 上午11:43
 * </pre>
 */
public class TinyUrl extends WeiboResponse implements Serializable {
    private static final long serialVersionUID = -1880030018403198381L;
    @SerializedName("url_short")
    private String shortUrl;
    @SerializedName("url_long")
    private String longUrl;
    private UrlType type;
    private boolean result;

    public TinyUrl() {
        super();
    }


    public TinyUrl(JSONObject url) {
        super();
        init(url);
    }

    private void init(JSONObject url) {
        if (null != url) {
            this.shortUrl = url.optString("url_short", "");
            this.longUrl = url.optString("url_long", "");
            this.type = UrlType.valueOf(url.optInt("type", 0));
            this.result = url.optBoolean("result", false);
        }
    }

    public String getShortUrl() {
        return shortUrl;
    }

    public void setShortUrl(String shortUrl) {
        this.shortUrl = shortUrl;
    }

    public String getLongUrl() {
        return longUrl;
    }

    public void setLongUrl(String longUrl) {
        this.longUrl = longUrl;
    }

    public UrlType getType() {
        return type;
    }

    public void setType(UrlType type) {
        this.type = type;
    }

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("TinyUrl");
        sb.append("{shortUrl='").append(shortUrl).append('\'');
        sb.append(", longUrl='").append(longUrl).append('\'');
        sb.append(", type=").append(type);
        sb.append(", result=").append(result);
        sb.append('}');
        return sb.toString();
    }
}
