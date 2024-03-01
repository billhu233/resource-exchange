package exchange.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import exchange.mybatis.dataobject.BaseDO;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 退点申请表
 * </p>
 *
 * @author hb
 * @since 2024-02-29
 */
@Getter
@Setter
@TableName("ReturnPointApply")
public class ReturnPointApply extends BaseDO {

    private static final long serialVersionUID = 1L;

    /**
     * 退点用户id
     */
    @TableField("user_id")
    private Integer userId;

    /**
     * 退点点数
     */
    @TableField("return_point")
    private Integer returnPoint;

    /**
     * 申请时间
     */
    @TableField("apply_time")
    private LocalDateTime applyTime;

    /**
     * 申请状态
     */
    @TableField("apply_state")
    private Integer applyState;

    /**
     * 审核人
     */
    @TableField("check_user_id")
    private Integer checkUserId;

    /**
     * 完成时间
     */
    @TableField("finish_time")
    private LocalDateTime finishTime;

    /**
     * 审核描述
     */
    @TableField("check_remark")
    private String checkRemark;
}
