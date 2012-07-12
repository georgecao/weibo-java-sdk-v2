package weibo4j.model;

/**
 * 0：普通网页、1：视频、2：音乐、3：活动、5、投票
 * <pre>
 * User: George
 * Date: 12-7-12
 * Time: 上午11:44
 * </pre>
 */
public enum UrlType {
    WEB(0),
    VIDEO(1),
    MUSIC(2),
    ACTIVITY(3),
    VOTE(5);

    private int code;

    private UrlType(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public static UrlType valueOf(int code) {
        for (UrlType type : UrlType.values()) {
            if (code == type.getCode()) {
                return type;
            }
        }
        throw new UnsupportedOperationException("Not supported url type code " + code);
    }
}
