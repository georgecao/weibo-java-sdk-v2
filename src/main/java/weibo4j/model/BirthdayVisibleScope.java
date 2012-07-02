package weibo4j.model;

/**
 * 0：保密、1：只显示月日、2：只显示星座、3：所有人可见。
 * <pre>
 * User: zhangzhi.cao
 * Date: 12-7-2
 * Time: 下午1:47
 * </pre>
 */
public enum BirthdayVisibleScope {
    SECRET(0),
    MONTH_DAY(1),
    CONSTELLATION(2),
    ALL(3);
    int code;

    BirthdayVisibleScope(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public static BirthdayVisibleScope valueOf(int code) {
        for (BirthdayVisibleScope scope : BirthdayVisibleScope.values()) {
            if (code == scope.getCode()) {
                return scope;
            }
        }
        throw new IllegalArgumentException("Not supported scope code " + code);
    }

    @Override
    public String toString() {
        return String.valueOf(code);
    }
}
