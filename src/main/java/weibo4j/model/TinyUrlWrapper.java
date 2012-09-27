package weibo4j.model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

/**
 * Say something?
 * <pre>
 * User: George
 * Date: 12-7-12
 * Time: 上午11:50
 * </pre>
 */
public class TinyUrlWrapper extends WeiboResponse implements Serializable {
    private static final Logger LOG = LoggerFactory.getLogger(TinyUrlWrapper.class);
    private static final boolean debug = LOG.isDebugEnabled();
    private static final long serialVersionUID = -4827565705008435505L;
    List<TinyUrl> urls;

    public TinyUrlWrapper(JSONObject tiny) {
        super();
        init(tiny);
    }

    private void init(JSONObject tiny) {
        if (null != tiny) {
            try {
                JSONArray tinyUrls = tiny.getJSONArray("urls");
                if (null != tinyUrls) {
                    urls = new LinkedList<TinyUrl>();
                    for (int i = 0; i < tinyUrls.length(); i++) {
                        urls.add(new TinyUrl(tinyUrls.getJSONObject(i)));
                    }
                }
            } catch (JSONException e) {
                LOG.error("Error occurred:", e);
            }
        }
    }

    public List<TinyUrl> getUrls() {
        return urls;
    }

    public TinyUrl getFirst() {
        if (urls == null || urls.size() == 0) {
            return null;
        }
        return urls.get(0);
    }

    public void setUrls(List<TinyUrl> urls) {
        this.urls = urls;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("TinyUrlWrapper");
        sb.append("{urls=").append(urls);
        sb.append('}');
        return sb.toString();
    }
}
