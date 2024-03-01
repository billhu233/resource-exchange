package exchange.manager.mail;

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

    public Boolean sendMimeMail(String mail) {
        SimpleMailMessage smm = new SimpleMailMessage();
        smm.setSubject("测试邮件");
        smm.setFrom(form);
        smm.setSentDate(new Date());
        smm.setTo(mail);
        smm.setText("简单的邮件正文");
        mailSender.send(smm);
        return true;
    }
}
