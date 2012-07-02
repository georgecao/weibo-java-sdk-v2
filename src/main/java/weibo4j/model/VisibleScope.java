package weibo4j.model;

/**
 * Say something?
 * <pre>
 * User: zhangzhi.cao
 * Date: 12-7-2
 * Time: 下午12:10
 * </pre>
 */
public enum VisibleScope {
    /**
     * 0：自己可见
     */
    SELF(0),
    /**
     * 1：我关注人可见
     */
    FOLLOWING(1),
    /**
     * 2：所有人可见。
     */
    ALL(2);
    private int code;

    VisibleScope(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public static VisibleScope valueOf(int code) {
        for (VisibleScope scope : VisibleScope.values()) {
            if (code == scope.getCode()) {
                return scope;
            }
        }
        throw new IllegalArgumentException("Not supported this code yet." + code);
    }

    @Override
    public String toString() {
        return String.valueOf(code);
    }
}
