package exchange.userpg.domain.vo.req;

import exchange.common.enums.SendMailTypeEnum;
import exchange.common.enums.validation.InEnum;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;

/*
 * @description
 * @author hb
 * @since 2024/3/6 10:58
 */
@Data
@AllArgsConstructor
public class VerifyCodeRequest {

    @NotBlank(message = "发送邮件不能为空")
    @Email(message = "邮箱格式不正确")
    private String email;

    @InEnum(SendMailTypeEnum.class)
    private Integer type;
}
