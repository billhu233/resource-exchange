package exchange.userpg.manager;

import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import exchange.common.enums.SendMailTypeEnum;
import exchange.common.exception.ResultCode;
import exchange.common.exception.utils.AssertUtil;
import exchange.satoken.LoginHelper;
import exchange.common.utils.PwdUtil;
import exchange.common.utils.WebFrameworkUtils;
import exchange.userpg.convert.UserInfoConvert;
import exchange.userpg.domain.entity.UserInfo;
import exchange.userpg.domain.vo.req.UserLoginRequest;
import exchange.userpg.domain.vo.req.UserRegisterRequest;
import exchange.userpg.domain.vo.resp.LoginResponse;
import exchange.userpg.mapper.UserInfoMapper;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Random;

/*
 * @description
 * @author hb
 * @since 2024/2/29 11:59
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class UserInfoManage {

    @Autowired
    private MailService mailService;

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Autowired
    private HttpServletRequest httpServletRequest;

    @Value("verifyCode")
    private String verifyCode = "verifyCode";

    /**
     * 登录
     */
    public LoginResponse userLogin(UserLoginRequest request) {
        Object codeObj = httpServletRequest.getSession().getAttribute(verifyCode);
        AssertUtil.notNull(codeObj, ResultCode.VERIFY_CODE_NOT_EXISTS);
        AssertUtil.isFalse(codeObj.toString().equals(request.getVerifyCode()), ResultCode.VERIFY_CODE_ERROR);

        UserInfo userInfo = userInfoMapper.selectOne(new LambdaQueryWrapper<UserInfo>().eq(UserInfo::getEmail, request.getEmail()));
        AssertUtil.notNull(userInfo, ResultCode.USER_NOT_EXISTS);

        boolean match = Objects.equals(PwdUtil.getPWDStrFromOne2Db(request.getPassword()), userInfo.getPassword());
        AssertUtil.isFalse(match, ResultCode.PASSWORD_ERROR);

        LoginHelper.login(userInfo, 1000L);

        WebFrameworkUtils.setLoginUserId(httpServletRequest, userInfo.getUpdaterId());
        return LoginResponse.builder()
                .accessToken(StpUtil.getTokenValue())
                .userId(userInfo.getId()).userName(userInfo.getUserName())
                .build();
    }

    /**
     * 注册
     */
    public Boolean userRegistration(UserRegisterRequest request) {
        AssertUtil.isTrue(userInfoMapper.selectCount(new LambdaQueryWrapper<UserInfo>().eq(UserInfo::getEmail, request.getEmail())) > 0,
                ResultCode.EMAIL_EXISTS);

        Object codeObj = httpServletRequest.getSession().getAttribute(verifyCode);
        AssertUtil.notNull(codeObj, ResultCode.VERIFY_CODE_NOT_EXISTS);
        AssertUtil.isFalse(codeObj.toString().equals(request.getVerifyCode()), ResultCode.VERIFY_CODE_ERROR);

        return userInfoMapper.insert(UserInfoConvert.INSTANCE.convert(request)) > 0;
    }

    /**
     * 发送验证码
     */
    public String sendMailAndReturnCode(SendMailTypeEnum typeEnum, String sendToMail) {
        AssertUtil.isTrue(userInfoMapper.selectCount(new LambdaQueryWrapper<UserInfo>().eq(UserInfo::getEmail, sendToMail)) <= 0,
                ResultCode.USER_NOT_EXISTS);
        String code = generateRandomCode();
        httpServletRequest.getSession().setAttribute(verifyCode, code);
        mailService.sendMimeMail(typeEnum, sendToMail, code);
        return code;
    }

    /**
     * 生成随机验证码
     */
    private String generateRandomCode() {
        //1，用随机生成数方法，生成验证码
        Random yzm = new Random();                          //定义一个随机生成数技术，用来生成随机数

        String yzm2 = "";                                   //定义一个空的Atring变量用来接收生成的验证码

        for (int i = 0; i < 6; i++) {                       //循环5次每次生成一位，6位验证码

            int a = yzm.nextInt(3);                             //验证码包括数字、大小写字母组成
            switch (a) {                                          //a:    2       1       0
                case 0:                                         //      数字   小写字母   大写字母
                    char s = (char) (yzm.nextInt(26) + 65);
                    yzm2 = yzm2 + s;
                    break;
                case 1:
                    char s1 = (char) (yzm.nextInt(26) + 97);
                    yzm2 = yzm2 + s1;
                    break;
                case 2:
                    int s2 = yzm.nextInt(10);
                    yzm2 = yzm2 + s2;
                    break;
            }
        }

        return yzm2;
    }
}
