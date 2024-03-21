package exchange.userpg.domain.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import exchange.mybatis.dataobject.BaseDO;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 用户信息
 * </p>
 *
 * @author hb
 * @since 2024-02-29
 */
@Getter
@Setter
@TableName("UserInfo")
public class UserInfo extends BaseDO {

    private static final long serialVersionUID = 1L;

    /**
     * 是否系统管理员
     */
    @TableField("is_administrator")
    private String isAdministrator;

    /**
     * 用户名称
     */
    @TableField("user_name")
    private String userName;

    /**
     * 电话号码
     */
    @TableField("phone_number")
    private String phoneNumber;

    /**
     * Email
     */
    @TableField("email")
    private String email;

    /**
     * 密码
     */
    @TableField("password")
    private String password;
}
