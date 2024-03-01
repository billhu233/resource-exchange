package exchange.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import exchange.mybatis.dataobject.BaseDO;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 交换日志
 * </p>
 *
 * @author hb
 * @since 2024-02-29
 */
@Getter
@Setter
@TableName("ExchangeLog")
public class ExchangeLog extends BaseDO {

    private static final long serialVersionUID = 1L;

    /**
     * 来源用户id
     */
    @TableField("from_user_id")
    private Integer fromUserId;

    /**
     * 交易用户id
     */
    @TableField("to_user_id")
    private Integer toUserId;

    /**
     * 物品id
     */
    @TableField("item_id")
    private Integer itemId;

    /**
     * 交换折扣
     */
    @TableField("exchange_discount")
    private Integer exchangeDiscount;

    /**
     * 交换点
     */
    @TableField("exchange_point")
    private Integer exchangePoint;

    /**
     * 交换时间
     */
    @TableField("exchange_time")
    private LocalDateTime exchangeTime;

    /**
     * 交换备注
     */
    @TableField("description")
    private String description;
}
