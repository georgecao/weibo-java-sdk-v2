package weibo4j.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import weibo4j.org.json.JSONException;
import weibo4j.org.json.JSONObject;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Say something?
 * <pre>
 * User: zhangzhi.cao
 * Date: 12-6-7
 * Time: 上午10:11
 * </pre>
 */
public class Notification extends WeiboResponse implements Serializable {
    private static final long serialVersionUID = 3273568238032643388L;
    private static final Logger LOG = LoggerFactory.getLogger(Notification.class);
    private static final boolean debug = LOG.isDebugEnabled();
    private Long notificationId;
    private String title;
    private String content;
    private Date createdAt;
    private Map<String, String> senderApp;
    private Long senderUserId;

    public Notification(JSONObject json) throws WeiboException {
        super();
        init(json);
    }

    private void init(JSONObject notice) throws WeiboException {
        if (null != notice) {
            try {
                notificationId = notice.getLong("notification_id");
                title = notice.getString("title");
                content = notice.getString("content");
                createdAt = parseDate(notice.getString("created_at"), "yyyy-MM-dd hh:mm:ss");
                senderUserId = getLong("sender_uid", notice);
                JSONObject app = notice.getJSONObject("sender_app");
                if (null != app) {
                    senderApp = new HashMap<String, String>();
                    Iterator it = app.keys();
                    while (it.hasNext()) {
                        String key = (String) it.next();
                        senderApp.put(key, app.optString(key, ""));
                    }
                }
            } catch (JSONException e) {
                LOG.error("Error occurred:", e);
            }
        }
    }

    public Long getNotificationId() {
        return notificationId;
    }

    public void setNotificationId(Long notificationId) {
        this.notificationId = notificationId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Long getSenderUserId() {
        return senderUserId;
    }

    public void setSenderUserId(Long senderUserId) {
        this.senderUserId = senderUserId;
    }

    public Map<String, String> getSenderApp() {
        return senderApp;
    }

    public void setSenderApp(Map<String, String> senderApp) {
        this.senderApp = senderApp;
    }
}
