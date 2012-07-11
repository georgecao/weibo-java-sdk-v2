package weibo4j.model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * AccountUser entity, used for create sina weibo user.
 * <pre>
 * User: zhangzhi.cao
 * Date: 12-7-2
 * Time: 下午12:19
 * </pre>
 */
public class AccountUser implements Serializable {
    private static final long serialVersionUID = 1272011199110628589L;

    //Required Parameters
    @SerializedName("screen_name")
    @Required
    private String screenName;
    @Required
    private Gender gender;
    @Required
    private Integer province;
    @Required
    private Integer city;

    //Optional Parameters
    @SerializedName("real_name")
    private String realName;
    @SerializedName("real_name_visible")
    private VisibleScope realNameVisible;

    /**
     * In yyyy-MM-dd format
     */
    private String birthday;
    @SerializedName("birthday_visible")
    private BirthdayVisibleScope birthdayVisible;
    private String qq;
    @SerializedName("qq_visible")
    private VisibleScope qqVisible;
    private String msn;
    @SerializedName("msn_visible")
    private VisibleScope msnVisible;
    private String url;
    @SerializedName("url_visible")
    private VisibleScope urlVisible;
    @SerializedName("credentials_type")
    private CredentialType credentialsType;
    @SerializedName("credentials_num")
    private String credentialsNum;
    private String email;
    @SerializedName("email_visible")
    private VisibleScope emailVisible;
    private Lang lang;
    private String description;
    @SerializedName("created_at")
    private Date createdAt;

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getScreenName() {
        return screenName;
    }

    public void setScreenName(String screenName) {
        this.screenName = screenName;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Integer getProvince() {
        return province;
    }

    public void setProvince(Integer province) {
        this.province = province;
    }

    public Integer getCity() {
        return city;
    }

    public void setCity(Integer city) {
        this.city = city;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public VisibleScope getRealNameVisible() {
        return realNameVisible;
    }

    public void setRealNameVisible(VisibleScope realNameVisible) {
        this.realNameVisible = realNameVisible;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public BirthdayVisibleScope getBirthdayVisible() {
        return birthdayVisible;
    }

    public void setBirthdayVisible(BirthdayVisibleScope birthdayVisible) {
        this.birthdayVisible = birthdayVisible;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public VisibleScope getQqVisible() {
        return qqVisible;
    }

    public void setQqVisible(VisibleScope qqVisible) {
        this.qqVisible = qqVisible;
    }

    public String getMsn() {
        return msn;
    }

    public void setMsn(String msn) {
        this.msn = msn;
    }

    public VisibleScope getMsnVisible() {
        return msnVisible;
    }

    public void setMsnVisible(VisibleScope msnVisible) {
        this.msnVisible = msnVisible;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public VisibleScope getUrlVisible() {
        return urlVisible;
    }

    public void setUrlVisible(VisibleScope urlVisible) {
        this.urlVisible = urlVisible;
    }

    public CredentialType getCredentialsType() {
        return credentialsType;
    }

    public void setCredentialsType(CredentialType credentialsType) {
        this.credentialsType = credentialsType;
    }

    public String getCredentialsNum() {
        return credentialsNum;
    }

    public void setCredentialsNum(String credentialsNum) {
        this.credentialsNum = credentialsNum;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public VisibleScope getEmailVisible() {
        return emailVisible;
    }

    public void setEmailVisible(VisibleScope emailVisible) {
        this.emailVisible = emailVisible;
    }

    public Lang getLang() {
        return lang;
    }

    public void setLang(Lang lang) {
        this.lang = lang;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("AccountUser");
        sb.append("{screenName='").append(screenName).append('\'');
        sb.append(", gender=").append(gender);
        sb.append(", province='").append(province).append('\'');
        sb.append(", city='").append(city).append('\'');
        sb.append(", realName='").append(realName).append('\'');
        sb.append(", realNameVisible=").append(realNameVisible);
        sb.append(", birthday='").append(birthday).append('\'');
        sb.append(", birthdayVisible=").append(birthdayVisible);
        sb.append(", qq='").append(qq).append('\'');
        sb.append(", qqVisible=").append(qqVisible);
        sb.append(", msn='").append(msn).append('\'');
        sb.append(", msnVisible=").append(msnVisible);
        sb.append(", url='").append(url).append('\'');
        sb.append(", urlVisible=").append(urlVisible);
        sb.append(", credentialsType=").append(credentialsType);
        sb.append(", credentialsNum='").append(credentialsNum).append('\'');
        sb.append(", email='").append(email).append('\'');
        sb.append(", emailVisible=").append(emailVisible);
        sb.append(", lang=").append(lang);
        sb.append(", description='").append(description).append('\'');
        sb.append(", createdAt=").append(createdAt);
        sb.append('}');
        return sb.toString();
    }

    public static class AccountUserBuilder {
        //Required Parameters
        private String screenName;
        private Gender gender;
        private Integer province;
        private Integer city;

        //Optional Parameters
        private String realName;
        private VisibleScope realNameVisible;
        /**
         * In yyyy-MM-dd format
         */
        private String birthday;
        private BirthdayVisibleScope birthdayVisible;
        private String qq;
        private VisibleScope qqVisible;
        private String msn;
        private VisibleScope msnVisible;
        private String url;
        private VisibleScope urlVisible;
        private CredentialType credentialsType;
        private String credentialsNum;
        private String email;
        private VisibleScope emailVisible;
        private Lang lang;
        private String description;
        private Date createdAt;


        //Required Parameters
        public AccountUserBuilder screenName(String screenName) {
            this.screenName = screenName;
            return this;
        }

        public AccountUserBuilder gender(Gender gender) {
            this.gender = gender;
            return this;
        }

        public AccountUserBuilder province(Integer province) {
            this.province = province;
            return this;
        }

        public AccountUserBuilder city(Integer city) {
            this.city = city;
            return this;
        }

        //Optional Parameters
        public AccountUserBuilder realName(String realName) {
            this.realName = realName;
            return this;
        }

        public AccountUserBuilder realNameVisible(VisibleScope realNameVisible) {
            this.realNameVisible = realNameVisible;
            return this;
        }

        /**
         * In yyyy-MM-dd format
         */
        public AccountUserBuilder birthday(String birthday) {
            this.birthday = birthday;
            return this;
        }

        public AccountUserBuilder birthday(Date birthday) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            this.birthday = sdf.format(birthday);
            return this;
        }

        public AccountUserBuilder birthdayVisible(BirthdayVisibleScope birthdayVisible) {
            this.birthdayVisible = birthdayVisible;
            return this;
        }

        public AccountUserBuilder qq(String qq) {
            this.qq = qq;
            return this;
        }

        public AccountUserBuilder qqVisible(VisibleScope qqVisible) {
            this.qqVisible = qqVisible;
            return this;
        }

        public AccountUserBuilder msn(String msn) {
            this.msn = msn;
            return this;
        }

        public AccountUserBuilder msnVisible(VisibleScope msnVisible) {
            this.msnVisible = msnVisible;
            return this;
        }

        public AccountUserBuilder url(String url) {
            this.url = url;
            return this;
        }

        public AccountUserBuilder urlVisible(VisibleScope urlVisible) {
            this.urlVisible = urlVisible;
            return this;
        }

        public AccountUserBuilder credentialsType(CredentialType credentialsType) {
            this.credentialsType = credentialsType;
            return this;
        }

        public AccountUserBuilder credentialsNum(String credentialsNum) {
            this.credentialsNum = credentialsNum;
            return this;
        }

        public AccountUserBuilder email(String email) {
            this.email = email;
            return this;
        }

        public AccountUserBuilder emailVisible(VisibleScope emailVisible) {
            this.emailVisible = emailVisible;
            return this;
        }

        public AccountUserBuilder lang(Lang lang) {
            this.lang = lang;
            return this;
        }

        public AccountUserBuilder description(String description) {
            this.description = description;
            return this;
        }

        public AccountUserBuilder createdAt(Date createdAt) {
            this.createdAt = createdAt;
            return this;
        }


        public static AccountUserBuilder newBuilder() {
            return new AccountUserBuilder();
        }

        private boolean isEmpty(String value) {
            return null == value || value.trim().length() == 0;
        }

        public AccountUser build() {
            if (isEmpty(screenName) || province < 1 || city < 1 || null == gender) {
                throw new IllegalArgumentException("Required parameters are missing.");
            }
            AccountUser user = new AccountUser();
            user.setScreenName(screenName);
            user.setProvince(province);
            user.setCity(city);
            user.setGender(gender);
            user.setRealName(realName);
            user.setRealNameVisible(realNameVisible);
            user.setQq(qq);
            user.setQqVisible(qqVisible);
            user.setMsn(msn);
            user.setMsnVisible(msnVisible);
            user.setLang(lang);
            user.setEmail(email);
            user.setEmailVisible(emailVisible);
            user.setBirthday(birthday);
            user.setBirthdayVisible(birthdayVisible);
            user.setDescription(description);
            user.setCredentialsType(credentialsType);
            user.setCredentialsNum(credentialsNum);
            user.setUrl(url);
            user.setUrlVisible(urlVisible);
            user.setCreatedAt(createdAt);
            return user;
        }

        @Override
        public String toString() {
            final StringBuilder sb = new StringBuilder();
            sb.append("AccountUserBuilder");
            sb.append("{screenName='").append(screenName).append('\'');
            sb.append(", gender=").append(gender);
            sb.append(", province='").append(province).append('\'');
            sb.append(", city='").append(city).append('\'');
            sb.append(", realName='").append(realName).append('\'');
            sb.append(", realNameVisible=").append(realNameVisible);
            sb.append(", birthday='").append(birthday).append('\'');
            sb.append(", birthdayVisible=").append(birthdayVisible);
            sb.append(", qq='").append(qq).append('\'');
            sb.append(", qqVisible=").append(qqVisible);
            sb.append(", msn='").append(msn).append('\'');
            sb.append(", msnVisible=").append(msnVisible);
            sb.append(", url='").append(url).append('\'');
            sb.append(", urlVisible=").append(urlVisible);
            sb.append(", credentialsType=").append(credentialsType);
            sb.append(", credentialsNum='").append(credentialsNum).append('\'');
            sb.append(", email='").append(email).append('\'');
            sb.append(", emailVisible=").append(emailVisible);
            sb.append(", lang=").append(lang);
            sb.append(", description='").append(description).append('\'');
            sb.append(", createdAt='").append(createdAt).append('\'');
            sb.append('}');
            return sb.toString();
        }
    }
}
