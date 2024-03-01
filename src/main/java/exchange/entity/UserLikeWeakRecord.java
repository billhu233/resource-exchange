package exchange.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import exchange.mybatis.dataobject.BaseDO;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 用户赞踩记录表
 * </p>
 *
 * @author hb
 * @since 2024-02-29
 */
@Getter
@Setter
@TableName("UserLikeWeakRecord")
public class UserLikeWeakRecord extends BaseDO {

    private static final long serialVersionUID = 1L;

    /**
     * 用户id
     */
    @TableField("user_id")
    private Integer userId;

    /**
     * 物品id
     */
    @TableField("item_id")
    private Integer itemId;

    /**
     * 赞踩类型
     */
    @TableField("like_or_weak_type")
    private Integer likeOrWeakType;
}
