package weibo4j.model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Say something?
 * <pre>
 * User: George
 * Date: 12-7-11
 * Time: 下午8:43
 * </pre>
 */
public class NotificationWrapper extends WeiboResponse implements Serializable {
    private static final Logger LOG = LoggerFactory.getLogger(NotificationWrapper.class);
    private static final boolean debug = LOG.isDebugEnabled();
    private static final long serialVersionUID = -3192197475223018844L;
    private Notification notification;
    private List<Long> failedUserIds;

    public NotificationWrapper() {
        super();
    }

    public NotificationWrapper(JSONObject json) throws WeiboException {
        super();
        init(json);
    }

    private void init(JSONObject json) throws WeiboException {
        if (null != json) {
            try {
                JSONObject notice = json.getJSONObject("notification");
                this.notification = new Notification(notice);
                JSONArray jsonArray = json.getJSONArray("failed_uid");
                if (null != jsonArray) {
                    failedUserIds = new ArrayList<Long>(jsonArray.length());
                    for (int i = 0; i < jsonArray.length(); i++) {
                        long userId = jsonArray.optLong(i, 0);
                        if (0 != userId) {
                            failedUserIds.add(userId);
                        }
                    }
                }
            } catch (JSONException e) {
                LOG.error("Error occurred:", e);
            }
        }
    }

    public Notification getNotification() {
        return notification;
    }

    public void setNotification(Notification notification) {
        this.notification = notification;
    }

    public List<Long> getFailedUserIds() {
        return failedUserIds;
    }

    public void setFailedUserIds(List<Long> failedUserIds) {
        this.failedUserIds = failedUserIds;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("NotificationWrapper");
        sb.append("{notification=").append(notification);
        sb.append(", failedUserIds=").append(failedUserIds);
        sb.append('}');
        return sb.toString();
    }
}
