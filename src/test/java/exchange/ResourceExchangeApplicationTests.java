package exchange;

import exchange.common.enums.SendMailTypeEnum;
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
        mailService.sendMimeMail(SendMailTypeEnum.REGISTER, "153640709@qq.com", "川A·9962kM");
    }

}
