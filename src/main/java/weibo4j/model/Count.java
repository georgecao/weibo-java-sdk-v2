package weibo4j.model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import weibo4j.http.Response;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Say something?
 * <pre>
 * User: zhangzhi.cao
 * Date: 12-5-7
 * Time: 下午4:07
 * </pre>
 */
public class Count implements Serializable {
    private static final Logger LOG = LoggerFactory.getLogger(Count.class);
    private static final boolean debug = LOG.isDebugEnabled();
    private static final long serialVersionUID = 8143640162326455703L;
    private String id;
    private int comments;
    private int reposts;

    public Count(JSONObject json) throws WeiboException {
        try {
            id = json.getString("id");
            comments = json.getInt("comments");
            reposts = json.getInt("reposts");
        } catch (JSONException je) {
            throw new WeiboException(je.getMessage() + ":" + json.toString(), je);
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getComments() {
        return comments;
    }

    public void setComments(int comments) {
        this.comments = comments;
    }

    public int getReposts() {
        return reposts;
    }

    public void setReposts(int reposts) {
        this.reposts = reposts;
    }

    public static List<Count> constructCounts(Response res)
            throws WeiboException {
        try {
            JSONArray list = res.asJSONArray();
            int size = list.length();
            List<Count> counts = new ArrayList<Count>(size);
            for (int i = 0; i < size; i++) {
                counts.add(new Count(list.getJSONObject(i)));
            }
            return counts;
        } catch (JSONException e) {
            throw new WeiboException(e);
        } catch (WeiboException te) {
            throw te;
        }

    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("Count");
        sb.append("{id='").append(id).append('\'');
        sb.append(", comments=").append(comments);
        sb.append(", reposts=").append(reposts);
        sb.append('}');
        return sb.toString();
    }
}
