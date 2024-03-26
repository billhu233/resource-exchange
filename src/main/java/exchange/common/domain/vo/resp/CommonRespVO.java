package exchange.common.domain.vo.resp;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @since 2023-11-03
 */
@Schema(description = "返回视图：列表或详情")
@Data
public class CommonRespVO {
    @Schema(description = "主键id")
    private Long id;

    @Schema(description = "创建时间")
    private LocalDateTime createTime;

    @Schema(description = "更新时间")
    private LocalDateTime updateTime;

    @Schema(description = "创建人ID")
    private Long creatorId;

    @Schema(description = "创建人名称")
    private String creator;

    @Schema(description = "更新人ID")
    private Long updaterId;

    @Schema(description = "更新人名称")
    private String updater;

}
