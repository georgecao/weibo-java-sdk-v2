package weibo4j.util;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import weibo4j.model.*;

import java.util.Date;
import java.util.List;

/**
 * Say something?
 * <pre>
 * User: zhangzhi.cao
 * Date: 12-7-2
 * Time: 下午3:17
 * </pre>
 */
public class ParamUtilsTest {
    private static final Logger LOG = LoggerFactory.getLogger(ParamUtilsTest.class);
    private static final boolean debug = LOG.isDebugEnabled();

    @Test
    public void testGet() throws Exception {
        AccountUser user = AccountUser.AccountUserBuilder.newBuilder()
                .city(10)
                .screenName("George")
                .realName("George")
                .realNameVisible(VisibleScope.SELF)
                .province(10)
                .birthday(new Date())
                .birthdayVisible(BirthdayVisibleScope.CONSTELLATION)
                .credentialsType(CredentialType.HK_MO_TW_ID)
                .credentialsNum("323232323")
                .email("email@qq.com")
                .emailVisible(VisibleScope.ALL)
                .url("http://abc.com")
                .urlVisible(VisibleScope.FOLLOWING)
                .qq("1111111111")
                .qqVisible(VisibleScope.SELF)
                .msn("aaa@bbb.com")
                .msnVisible(VisibleScope.FOLLOWING)
                .lang(Lang.ZH_CN)
                .gender(Gender.MALE)
                .description("desc")
                .build();

        List<HttpParameter> params = ParamUtils.get(user);
        LOG.info("{}", params);
    }
}
