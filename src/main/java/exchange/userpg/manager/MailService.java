package exchange.userpg.manager;

import exchange.common.enums.SendMailTypeEnum;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Date;

/*
 * @description
 * @author hb
 * @since 2024/2/29 11:59
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class MailService {

    @Autowired
    private JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String form;

    public Boolean sendMimeMail(SendMailTypeEnum typeEnum, String mail, String verifyCode) {
        SimpleMailMessage smm = new SimpleMailMessage();
        smm.setSubject(typeEnum.getValue().equals(SendMailTypeEnum.LOGIN.getValue()) ? "登录验证码" : "注册验证码");
        smm.setFrom(form);
        smm.setSentDate(new Date());
        smm.setTo(mail);
        smm.setText("您本次的验证码是：" + verifyCode);
        mailSender.send(smm);
        return true;
    }
}
