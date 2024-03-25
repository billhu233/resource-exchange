package exchange.useritem.domain.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import exchange.framework.mybatis.dataobject.BaseDO;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 物品标签
 * </p>
 *
 * @author hb
 * @since 2024-03-25
 */
@Getter
@Setter
@TableName("ItemLabel")
public class ItemLabel extends BaseDO {

    private static final long serialVersionUID = 1L;

    /**
     * 物品id
     */
    @TableField("item_id")
    private Long itemId;

    /**
     * 标签
     */
    @TableField("label_name")
    private String labelName;
}
