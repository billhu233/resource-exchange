package exchange.useritem.domain.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import exchange.framework.mybatis.dataobject.BaseDO;
import lombok.Data;

import java.math.BigDecimal;

/**
 * <p>
 * 物品信息表
 * </p>
 *
 * @author hb
 * @since 2024-02-29
 */
@Data
@TableName("ItemInfo")
public class ItemInfo extends BaseDO {

    private static final long serialVersionUID = 1L;

    /**
     * 物品名称
     */
    @TableField("item_name")
    private String itemName;

    /**
     * 物品类型
     */
    @TableField("item_type")
    private String itemType;

    /**
     * 物品大小
     */
    @TableField("item_size")
    private Long itemSize;

    /**
     * 物品地址
     */
    @TableField("item_path")
    private String itemPath;

    /**
     * 物品md5
     */
    @TableField("item_md5")
    private String itemMd5;

    /**
     * 物品点数
     */
    @TableField("item_point")
    private BigDecimal itemPoint;
}
