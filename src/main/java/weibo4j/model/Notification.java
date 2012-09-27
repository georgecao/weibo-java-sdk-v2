package weibo4j.model;

import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
    private static final boolean DEBUG = LOG.isDebugEnabled();
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
            if (DEBUG) {
                LOG.debug("{}", notice.toString());
            }
            try {
                notificationId = notice.getLong("notification_id");
                String titleStr = notice.optString("title", EMPTY_STRING);
                if (!NULL_STRING.equalsIgnoreCase(titleStr)) {
                    title = titleStr;
                }
                String contentStr = notice.optString("content", EMPTY_STRING);
                if (!NULL_STRING.equalsIgnoreCase(contentStr)) {
                    content = contentStr;
                }
                createdAt = parseDate(notice.getString("created_at"), DATE_FORMAT);
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

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("Notification");
        sb.append("{notificationId=").append(notificationId);
        sb.append(", title='").append(title).append('\'');
        sb.append(", content='").append(content).append('\'');
        sb.append(", createdAt=").append(createdAt);
        sb.append(", senderApp=").append(senderApp);
        sb.append(", senderUserId=").append(senderUserId);
        sb.append('}');
        return sb.toString();
    }
}
