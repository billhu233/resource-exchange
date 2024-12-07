package exchange.userpg.domain.vo.req;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/*
 * @description
 * @author hb
 * @since 2024/3/6 10:58
 */
@Data
public class UserRegisterRequest {

    @NotBlank(message = "发送邮件不能为空")
    @Email(message = "邮箱格式不正确")
    private String email;

    @NotBlank(message = "密码不能为空")
    private String password;

    @NotBlank(message = "二次密码不能为空")
    private String secondPassword;

    @NotBlank(message = "验证码不能为空")
    private String verifyCode;

    private String userName;

    private String phoneNumber;
}
