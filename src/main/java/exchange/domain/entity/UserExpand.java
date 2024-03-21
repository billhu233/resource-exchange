package exchange.domain.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import exchange.mybatis.dataobject.BaseDO;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 用户扩展信息
 * </p>
 *
 * @author hb
 * @since 2024-02-29
 */
@Getter
@Setter
@TableName("UserExpand")
public class UserExpand extends BaseDO {

    private static final long serialVersionUID = 1L;

    /**
     * 用户id;与User表id一致
     */
    @TableField("user_id")
    private Integer userId;

    /**
     * 头像
     */
    @TableField("head_path")
    private String headPath;

    /**
     * 用户地址
     */
    @TableField("address")
    private String address;

    /**
     * 大学
     */
    @TableField("university")
    private String university;

    /**
     * 专业
     */
    @TableField("speciality")
    private String speciality;

    /**
     * 性别
     */
    @TableField("sex")
    private Integer sex;

    /**
     * 生日
     */
    @TableField("birthday")
    private LocalDateTime birthday;

    /**
     * 颜值等级
     */
    @TableField("beauty_level")
    private String beautyLevel;
}
