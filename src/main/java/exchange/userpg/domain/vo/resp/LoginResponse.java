package exchange.userpg.domain.vo.resp;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

/*
 * @description
 * @author hb
 * @since 2024/3/6 10:58
 */
@Data
@Builder
public class LoginResponse {

    @Schema(description = "访问token")
    public String accessToken;

    @Schema(description = "账户id")
    public Long userId;

    @Schema(description = "姓名")
    public String userName;

    @Schema(description = "邮箱")
    public String email;
}
