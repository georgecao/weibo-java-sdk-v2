package weibo4j;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import weibo4j.model.*;
import weibo4j.util.ParamUtils;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertNotNull;

/**
 * Say something?
 * <pre>
 * User: zhangzhi.cao
 * Date: 12-7-2
 * Time: 下午3:36
 * </pre>
 */
public class AccountTest {
    private static final Logger LOG = LoggerFactory.getLogger(AccountTest.class);
    private static final boolean debug = LOG.isDebugEnabled();

    Weibo weibo = WeiboService.getOne();

    @Test
    public void testCreateAccount() throws Exception {

        AccountUser accountUser = AccountUser.AccountUserBuilder.newBuilder()
                .city(10)
                .screenName("George")
                .realName("George")
                .realNameVisible(VisibleScope.SELF)
                .province(100)
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

        List<HttpParameter> list = ParamUtils.get(accountUser);
        for (HttpParameter pp : list) {
            LOG.info("{}={},", pp.getName(), pp.getValue());
        }
        LOG.info("{}", list.size());

        Account account = new Account("2.00tBx13B0WKLFi98a3201060TbLJLB");
        Long userId = account.createAccount(accountUser);
        LOG.info("User id is: {}", userId);
    }

    @Test
    public void testGetAccountEducation() throws Exception {
        List<Education> value = weibo.getAccountService().getAccountEducation("2099907877");
        assertNotNull(value);
        LOG.info("{}", value);
    }

    @Test
    public void testGetAccountCareer() throws Exception {
        List<Career> value = weibo.getAccountService().getAccountCareer("1246205697");
        assertNotNull(value);
        LOG.info("{}", value);
    }
}
