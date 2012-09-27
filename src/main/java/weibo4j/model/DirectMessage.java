package weibo4j.model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import weibo4j.http.Response;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Say something?
 * <pre>
 * User: George
 * Date: 12-5-7
 * Time: 下午4:08
 * </pre>
 */
public class DirectMessage extends WeiboResponse implements Serializable {
    private static final Logger LOG = LoggerFactory.getLogger(DirectMessage.class);
    private static final boolean debug = LOG.isDebugEnabled();
    private static final long serialVersionUID = 6655843199922366404L;
    private Long id;
    private Long mid;
    private Long statusId;
    private String text;
    private Integer sendType;
    private long senderId;
    private long recipientId;
    private Date createdAt;
    private String senderScreenName;
    private String recipientScreenName;
    private User sender;
    private User recipient;

    public DirectMessage(JSONObject json) throws WeiboException {
        try {
            id = json.getLong("id");
            mid = json.getLong("mid");
            text = json.getString("text");
            statusId = json.getLong("status_id");
            senderId = json.getLong("sender_id");
            sendType = json.optInt("send_type");
            recipientId = json.getLong("recipient_id");
            createdAt = parseDate(json.getString("created_at"), "EEE MMM dd HH:mm:ss z yyyy");
            senderScreenName = json.getString("sender_screen_name");
            recipientScreenName = json.getString("recipient_screen_name");
            if (!json.isNull("sender"))
                sender = new User(json.getJSONObject("sender"));
            if (!json.isNull("recipient")) {
                recipient = new User(json.getJSONObject("recipient"));
            }
        } catch (JSONException e) {
            throw new WeiboException(e.getMessage() + ":" + json.toString(), e);
        }

    }

    public long getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public long getSenderId() {
        return senderId;
    }

    public long getRecipientId() {
        return recipientId;
    }

    /**
     * @return created_at
     * @since Weibo4J 1.1.0
     */
    public Date getCreatedAt() {
        return createdAt;
    }

    public String getSenderScreenName() {
        return senderScreenName;
    }

    public String getRecipientScreenName() {
        return recipientScreenName;
    }


    public User getSender() {
        return sender;
    }

    public User getRecipient() {
        return recipient;
    }

    /*package*/
    static List<DirectMessage> constructDirectMessages(Response res
    ) throws WeiboException {
        JSONArray list = res.asJSONArray();

        try {
            int size = list.length();
            List<DirectMessage> messages = new ArrayList<DirectMessage>(size);
            for (int i = 0; i < size; i++) {

                messages.add(new DirectMessage(list.getJSONObject(i)));
            }
            return messages;
        } catch (JSONException e) {
            throw new WeiboException(e);
        }
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (null == obj) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        return obj instanceof DirectMessage && ((DirectMessage) obj).id.equals(this.id);
    }

    public Long getMid() {
        return mid;
    }

    public Long getStatusId() {
        return statusId;
    }

    public Integer getSendType() {
        return sendType;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("DirectMessage");
        sb.append("{id=").append(id);
        sb.append(", mid=").append(mid);
        sb.append(", statusId=").append(statusId);
        sb.append(", text='").append(text).append('\'');
        sb.append(", sendType=").append(sendType);
        sb.append(", senderId=").append(senderId);
        sb.append(", recipientId=").append(recipientId);
        sb.append(", createdAt=").append(createdAt);
        sb.append(", senderScreenName='").append(senderScreenName).append('\'');
        sb.append(", recipientScreenName='").append(recipientScreenName).append('\'');
        sb.append(", sender=").append(sender);
        sb.append(", recipient=").append(recipient);
        sb.append('}');
        return sb.toString();
    }
}
