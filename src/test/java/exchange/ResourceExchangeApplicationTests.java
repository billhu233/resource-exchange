package exchange;

import exchange.common.enums.SendMailTypeEnum;
import exchange.userpg.manager.MailService;
import exchange.userpg.manager.UserInfoManage;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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
