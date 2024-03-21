package exchange.domain.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import exchange.mybatis.dataobject.BaseDO;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 用户物品表
 * </p>
 *
 * @author hb
 * @since 2024-02-29
 */
@Getter
@Setter
@TableName("UserItems")
public class UserItems extends BaseDO {

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
     * 点赞数
     */
    @TableField("like_number")
    private Integer likeNumber;

    /**
     * 点踩数
     */
    @TableField("weak_number")
    private Integer weakNumber;

    /**
     * 下载数
     */
    @TableField("download_number")
    private Integer downloadNumber;

    /**
     * 物品描述
     */
    @TableField("description")
    private String description;
}
