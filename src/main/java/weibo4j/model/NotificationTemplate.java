package weibo4j.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.util.Collection;

import static weibo4j.util.ParamUtils.*;

/**
 * Say something?
 * <pre>
 * User: George
 * Date: 12-7-11
 * Time: 下午7:36
 * </pre>
 */
public class NotificationTemplate implements Serializable {
    private static final Logger LOG = LoggerFactory.getLogger(NotificationTemplate.class);
    private static final boolean debug = LOG.isDebugEnabled();
    private static final long serialVersionUID = -3514893814187849258L;
    @Required
    @SerializedName("uids")
    String userIds;
    @Required
    @SerializedName("tpl_id")
    Long tplId;
    @SerializedName("objects1")
    String objects1;
    @SerializedName("objects1_count")
    Integer objects1Count;
    @SerializedName("objects2")
    String objects2;
    @SerializedName("objects2_count")
    Integer objects2Count;
    @SerializedName("objects3")
    String objects3;
    @SerializedName("objects3_count")
    Integer objects3Count;
    @SerializedName("action_url")
    String actionUrl;

    public NotificationTemplate() {
    }

    public NotificationTemplate(String userIds, Long tplId, String objects1, Integer objects1Count, String objects2, Integer objects2Count, String objects3, Integer objects3Count, String actionUrl) {
        this.userIds = userIds;
        this.tplId = tplId;
        this.objects1 = objects1;
        this.objects1Count = objects1Count;
        this.objects2 = objects2;
        this.objects2Count = objects2Count;
        this.objects3 = objects3;
        this.objects3Count = objects3Count;
        this.actionUrl = actionUrl;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("NotificationTemplate");
        sb.append("{userIds='").append(userIds).append('\'');
        sb.append(", tplId=").append(tplId);
        sb.append(", objects1='").append(objects1).append('\'');
        sb.append(", objects1Count=").append(objects1Count);
        sb.append(", objects2='").append(objects2).append('\'');
        sb.append(", objects2Count=").append(objects2Count);
        sb.append(", objects3='").append(objects3).append('\'');
        sb.append(", objects3Count=").append(objects3Count);
        sb.append(", actionUrl='").append(actionUrl).append('\'');
        sb.append('}');
        return sb.toString();
    }

    public String getUserIds() {
        return userIds;
    }

    public void setUserIds(String userIds) {
        this.userIds = userIds;
    }

    public Long getTplId() {
        return tplId;
    }

    public void setTplId(long tplId) {
        this.tplId = tplId;
    }

    public String getObjects1() {
        return objects1;
    }

    public void setObjects1(String objects1) {
        this.objects1 = objects1;
    }

    public Integer getObjects1Count() {
        return objects1Count;
    }

    public void setObjects1Count(Integer objects1Count) {
        this.objects1Count = objects1Count;
    }

    public String getObjects2() {
        return objects2;
    }

    public void setObjects2(String objects2) {
        this.objects2 = objects2;
    }

    public Integer getObjects2Count() {
        return objects2Count;
    }

    public void setObjects2Count(Integer objects2Count) {
        this.objects2Count = objects2Count;
    }

    public String getObjects3() {
        return objects3;
    }

    public void setObjects3(String objects3) {
        this.objects3 = objects3;
    }

    public Integer getObjects3Count() {
        return objects3Count;
    }

    public void setObjects3Count(Integer objects3Count) {
        this.objects3Count = objects3Count;
    }

    public String getActionUrl() {
        return actionUrl;
    }

    public void setActionUrl(String actionUrl) {
        this.actionUrl = actionUrl;
    }

    public static class Builder {
        private static final String SEP = "、";
        private static final String DEL = ",";
        String userIds;
        Long tplId;
        String objects1;
        Integer objects1Count = 1;
        String objects2;
        Integer objects2Count = 1;
        String objects3;
        Integer objects3Count = 1;
        String actionUrl;

        public static Builder newBuilder() {
            return new Builder();
        }

        public Builder tplId(Long tplId) {
            this.tplId = tplId;
            return this;
        }

        private String collection2String(Collection<?> collection) {
            return join(collection, SEP);
        }

        public Builder userIdList(Collection<?> collection) {
            if (isNotEmpty(collection)) {
                userIds(join(collection, DEL));
            }
            return this;
        }

        public Builder userIds(String userIds) {
            this.userIds = userIds;
            return this;
        }

        public Builder actionUrl(String actionUrl) {
            this.actionUrl = actionUrl;
            return this;
        }

        public Builder objects1(String objects1) {
            this.objects1 = objects1;
            return this;
        }

        public Builder objects1List(Collection<?> objects) {
            if (isNotEmpty(objects)) {
                objects1(collection2String(objects));
                objects1Count(objects.size());
            }
            return this;
        }

        public Builder objects1Count(Integer objects1Count) {
            this.objects1Count = objects1Count;
            return this;
        }

        public Builder objects2(String objects2) {
            this.objects2 = objects2;
            return this;
        }

        public Builder objects2List(Collection<?> objects) {
            if (isNotEmpty(objects)) {
                objects2(collection2String(objects));
                objects2Count(objects.size());
            }
            return this;
        }

        public Builder objects2Count(Integer objects2Count) {
            this.objects2Count = objects2Count;
            return this;
        }

        public Builder objects3(String objects3) {
            this.objects3 = objects3;
            return this;
        }

        public Builder objects3List(Collection<?> objects) {
            if (isNotEmpty(objects)) {
                objects3(collection2String(objects));
                objects3Count(objects.size());
            }
            return this;
        }

        public Builder objects3Count(Integer objects3Count) {
            this.objects3Count = objects3Count;
            return this;
        }

        private void reset() {
            if (isEmpty(objects1) && null != objects1Count) {
                objects1Count = null;
            } else if (!isEmpty(objects1) && null == objects1Count) {
                objects1Count = 1;
            }
            if (isEmpty(objects2) && null != objects2Count) {
                objects2Count = null;
            } else if (!isEmpty(objects2) && null == objects2Count) {
                objects2Count = 1;
            }
            if (isEmpty(objects3) && null != objects3Count) {
                objects3Count = null;
            } else if (!isEmpty(objects3) && null == objects3Count) {
                objects3Count = 1;
            }
        }

        public NotificationTemplate build() {
            if (isEmpty(userIds)) {
                throw new IllegalArgumentException("Required parameter userIds is empty.");
            }
            if (tplId < 1) {
                throw new IllegalArgumentException("Required parameter tplId is empty.");
            }
            if (!isEmpty(actionUrl) && actionUrl.length() > 20) {
                throw new IllegalArgumentException("action url exceed the length limit 20 at MAX.");
            }
            reset();
            return new NotificationTemplate(userIds, tplId,
                    objects1, objects1Count,
                    objects2, objects2Count,
                    objects3, objects3Count,
                    actionUrl);
        }
    }
}
