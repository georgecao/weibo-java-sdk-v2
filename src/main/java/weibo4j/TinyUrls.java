package weibo4j;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import weibo4j.model.HttpParameter;
import weibo4j.model.TinyUrlWrapper;
import weibo4j.model.WeiboException;
import weibo4j.util.WeiboConfig;

import java.util.Arrays;
import java.util.Collection;

/**
 * Say something?
 * <pre>
 * User: George
 * Date: 12-7-12
 * Time: 上午11:35
 * </pre>
 */
public class TinyUrls extends Weibo {
    private static final Logger LOG = LoggerFactory.getLogger(TinyUrls.class);
    private static final boolean debug = LOG.isDebugEnabled();
    private static final long serialVersionUID = 4902660415496121906L;
    public static final String URL_LONG_KEY = "url_long";
    public static final String URL_SHORT_KEY = "url_short";

    public TinyUrls() {
    }

    public TinyUrls(String accessToken) {
        super(accessToken);
    }

    public TinyUrlWrapper shortUrl(Collection<String> longUrls) throws WeiboException {
        HttpParameter[] longUrlArray = new HttpParameter[longUrls.size()];
        int index = 0;
        for (String longUrl : longUrls) {
            longUrlArray[index++] = new HttpParameter(URL_LONG_KEY, longUrl);
        }
        return new TinyUrlWrapper(client.get(WeiboConfig.getBaseUrl() + "short_url/shorten.json", longUrlArray)
                .asJSONObject());
    }

    public TinyUrlWrapper shortUrl(String longUrl) throws WeiboException {
        return shortUrl(Arrays.asList(longUrl));
    }

    public TinyUrlWrapper expandUrl(String shortUrl) throws WeiboException {
        return expandUrl(Arrays.asList(shortUrl));
    }

    public TinyUrlWrapper expandUrl(Collection<String> shortUrls) throws WeiboException {
        HttpParameter[] shortUrlArray = new HttpParameter[shortUrls.size()];
        int index = 0;
        for (String shortUrl : shortUrls) {
            shortUrlArray[index++] = new HttpParameter(URL_SHORT_KEY, shortUrl);
        }
        return new TinyUrlWrapper(client.get(WeiboConfig.getBaseUrl() + "short_url/expand.json", shortUrlArray)
                .asJSONObject());
    }
}
