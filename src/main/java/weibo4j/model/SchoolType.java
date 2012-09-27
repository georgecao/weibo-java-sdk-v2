package weibo4j.model;

/**
 * Say something?
 * <pre>
 * User: George
 * Date: 12-9-26
 * Time: 17:30
 * </pre>
 */
public enum SchoolType {
    ELEMENTARY_SCHOOL(5, "小学"),
    JUNIOR_HIGH_SCHOOL(4, "初中"),
    VOCATIONAL_SCHOOL(3, "中专技校"),
    SENIOR_HIGH_SCHOOL(2, "高中"),
    UNIVERSITY(1, "大学");
    private int code;
    private String name;

    private SchoolType(int code, String name) {
        this.code = code;
        this.name = name;
    }

    public int getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public static SchoolType valueOf(int code) {
        for (SchoolType type : SchoolType.values()) {
            if (code == type.getCode()) {
                return type;
            }
        }
        return null;
    }
}
