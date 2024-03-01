package exchange.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import exchange.mybatis.dataobject.BaseDO;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 交换点申请表
 * </p>
 *
 * @author hb
 * @since 2024-02-29
 */
@Getter
@Setter
@TableName("ExchangePointApply")
public class ExchangePointApply extends BaseDO {

    private static final long serialVersionUID = 1L;

    /**
     * 用户id
     */
    @TableField("user_id")
    private Integer userId;

    /**
     * 申请交换点
     */
    @TableField("point")
    private Integer point;

    /**
     * 申请状态
     */
    @TableField("apply_state")
    private Integer applyState;
}
