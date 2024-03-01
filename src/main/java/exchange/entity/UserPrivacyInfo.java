package exchange.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import exchange.mybatis.dataobject.BaseDO;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 用户私密信息
 * </p>
 *
 * @author hb
 * @since 2024-02-29
 */
@Getter
@Setter
@TableName("UserPrivacyInfo")
public class UserPrivacyInfo extends BaseDO {

    private static final long serialVersionUID = 1L;

    /**
     * 用户Id
     */
    @TableField("user_id")
    private Integer userId;

    /**
     * vip级别
     */
    @TableField("vip_level")
    private Integer vipLevel;

    /**
     * 交换点
     */
    @TableField("exchange_point")
    private Integer exchangePoint;

    /**
     * 关注人数
     */
    @TableField("follow_number")
    private Integer followNumber;

    /**
     * 被关注人数
     */
    @TableField("other_follow_number")
    private Integer otherFollowNumber;
}
