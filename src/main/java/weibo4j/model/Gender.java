package weibo4j.model;

/**
 * @author SinaWeibo
 */
public enum Gender {
    MALE("m"),
    FEMALE("f");
    String name;

    private Gender(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static Gender parse(String name) {
        for (Gender gender : Gender.values()) {
            if (gender.getName().equals(name)) {
                return gender;
            }
        }
        return valueOf(name);
    }

    public static String valueOf(Gender gender) {
        int ordinal = gender.ordinal();
        if (ordinal == 0)
            return "m";
        return "f";
    }

    @Override
    public String toString() {
        return getName();
    }

}
