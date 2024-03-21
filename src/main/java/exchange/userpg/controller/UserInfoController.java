package exchange.userpg.controller;

import exchange.common.enums.SendMailTypeEnum;
import exchange.manager.userinfomanage.UserInfoManage;
import exchange.userpg.domain.vo.req.VerifyCodeRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 用户信息 前端控制器
 * </p>
 *
 * @author hb
 * @since 2024-02-29
 */
@RestController
@RequestMapping("/admin")
public class UserInfoController {

    private UserInfoManage userInfoManage;

    /**
     * 获取验证码
     */
    @PostMapping("/getVerifyCode")
    public String getVerifyCode(VerifyCodeRequest request) {
        return userInfoManage.sendMailAndReturnCode(SendMailTypeEnum.valueOf(request.getType()), request.getEmail());
    }

//    public Boolean
}
