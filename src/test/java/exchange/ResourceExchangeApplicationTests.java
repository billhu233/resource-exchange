package exchange;

import exchange.manager.mail.MailService;
import exchange.manager.userinfomanage.UserInfoManage;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.UnsupportedEncodingException;
import java.util.List;

@SpringBootTest
class ResourceExchangeApplicationTests {

    @Autowired
    private MailService mailService;

    @Autowired
    private UserInfoManage userInfoManage;

    @Test
    void contextLoads() {
    }

    @Test
    public void sendMailTest() {
        mailService.sendMimeMail("153640709@qq.com");
    }

    @Test
    public void getVerifyCode() throws UnsupportedEncodingException {
        List<String> codes = Lists.newArrayList();
        for (int i = 0; i < 1000; i++)
        {
            codes.add(userInfoManage.getVerifyCode());
        }
        String h = userInfoManage.getVerifyCode();
    }
}
