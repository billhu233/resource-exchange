package exchange.useritem.domain.vo.req;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/*
 * @description
 * @author hb
 * @since 2024/3/6 10:58
 */
@Data
@AllArgsConstructor
public class UserItemRequest {

    @NotNull(message = "物品交换点数不能为空")
    private BigDecimal itemPoint;

    private String description;

    private List<String> itemLabel;
}
