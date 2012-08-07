package weibo4j.model;

/**
 * 作者筛选类型，0：全部、1：我关注的人、2：陌生人，默认为0。
 * <pre>
 * User: George
 * Date: 12-8-7
 * Time: 下午5:42
 * </pre>
 */
public enum CommentAuthor {
    ALL(0),
    FOLLOWING(1),
    STRANGER(2);
    private int code;

    CommentAuthor(int code) {
        this.code = code;

    }

    public int getCode() {
        return code;
    }
}
