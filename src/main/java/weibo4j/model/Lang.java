package weibo4j.model;

/**
 * 语言版本，可选值：zh-cn：简体中文、zh-tw：繁体中文、en：英语。
 * <pre>
 * User: zhangzhi.cao
 * Date: 12-7-2
 * Time: 下午1:54
 * </pre>
 */
public enum Lang {
    ZH_CN("zh-cn"),
    ZH_TW("zh-tw"),
    EN("en"),
    ENGLISH("english");
    String name;

    private Lang(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static Lang parse(String name) {
        for (Lang lang : Lang.values()) {
            if (lang.getName().equals(name)) {
                return lang;
            }
        }
        return valueOf(name);
    }

    @Override
    public String toString() {
        return getName();
    }
}
