package weibo4j.model;

/**
 * 来源筛选类型，0：全部、1：来自微博的评论、2：来自微群的评论，默认为0。
 */
public enum CommentSource {
    ALL(0),
    WEIBO(1),
    Group(2);
    private int code;

    CommentSource(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
