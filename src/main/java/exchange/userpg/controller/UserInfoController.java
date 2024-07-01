package exchange.userpg.controller;

import cn.dev33.satoken.annotation.SaIgnore;
import exchange.common.enums.SendMailTypeEnum;
import exchange.common.result.R;
import exchange.userpg.domain.vo.req.UserLoginRequest;
import exchange.userpg.domain.vo.req.UserRegisterRequest;
import exchange.userpg.domain.vo.req.VerifyCodeRequest;
import exchange.userpg.domain.vo.resp.LoginResponse;
import exchange.userpg.manager.UserInfoManage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

    @Autowired
    private UserInfoManage userInfoManage;

    /**
     * 获取验证码
     */
    @SaIgnore
    @PostMapping("/getVerifyCode")
    public R<String> getVerifyCode(@RequestBody VerifyCodeRequest request) {
        return R.success(userInfoManage.sendMailAndReturnCode(SendMailTypeEnum.valueOf(request.getType()), request.getEmail()));
    }

    /**
     * 用户注册
     */
    @PostMapping("/register")
    public R<Boolean> userRegistration(@RequestBody UserRegisterRequest request) {
        return R.success(userInfoManage.userRegistration(request));
    }

    /**
     * 用户登录
     */
    @PostMapping("/login")
    public R<LoginResponse> userLogin(@RequestBody UserLoginRequest request) {
        return R.success(userInfoManage.userLogin(request));
    }
}
