package exchange.useritem.domain.vo.req;

import exchange.common.page.PageParam;
import lombok.AllArgsConstructor;
import lombok.Data;

/*
 * @description
 * @author hb
 * @since 2024/3/6 10:58
 */
@Data
public class SelectUserItemRequest extends PageParam {

    private String itemName;
}
