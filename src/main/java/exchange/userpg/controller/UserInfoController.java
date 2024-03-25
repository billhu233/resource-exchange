package exchange.userpg.controller;

import exchange.common.enums.SendMailTypeEnum;
import exchange.common.result.R;
import exchange.userpg.domain.vo.req.UserLoginRequest;
import exchange.userpg.domain.vo.resp.LoginResponse;
import exchange.userpg.manager.UserInfoManage;
import exchange.userpg.domain.vo.req.UserRegisterRequest;
import exchange.userpg.domain.vo.req.VerifyCodeRequest;
import org.springframework.web.bind.annotation.*;

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
