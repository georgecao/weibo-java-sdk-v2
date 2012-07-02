package weibo4j.model;

/**
 * 证件类型，1-身份证、2-学生证、3-军官证、4-护照、5-港澳台身份证。
 * <pre>
 * User: zhangzhi.cao
 * Date: 12-7-2
 * Time: 下午12:28
 * </pre>
 */
public enum CredentialType {
    ID(1),
    STUDENT(2),
    MILITARY_OFFICER(3),
    PASSPORT(4),
    HK_MO_TW_ID(5);
    int code;

    CredentialType(int code) {
        this.code = code;
    }

    int getCode() {
        return code;
    }

    public static CredentialType valueOf(int code) {
        for (CredentialType type : CredentialType.values()) {
            if (code == type.getCode()) {
                return type;
            }
        }
        throw new IllegalArgumentException("Not supported CredentialType code " + code);
    }

    @Override
    public String toString() {
        return String.valueOf(code);
    }
}
