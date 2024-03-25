package exchange.useritem.domain.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import exchange.framework.mybatis.dataobject.BaseDO;
import lombok.Data;

/**
 * <p>
 * 用户物品表
 * </p>
 *
 * @author hb
 * @since 2024-02-29
 */
@Data
@TableName("UserItems")
public class UserItems extends BaseDO {

    private static final long serialVersionUID = 1L;

    /**
     * 用户id
     */
    @TableField("user_id")
    private Long userId;

    /**
     * 物品id
     */
    @TableField("item_id")
    private Long itemId;

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
