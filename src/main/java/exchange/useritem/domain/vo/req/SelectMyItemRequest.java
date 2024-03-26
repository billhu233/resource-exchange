package exchange.useritem.domain.vo.req;

import exchange.common.page.PageParam;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/*
 * @description
 * @author hb
 * @since 2024/3/6 10:58
 */
@Data
@AllArgsConstructor
public class SelectMyItemRequest extends PageParam {
    
    private String itemName;

    private List<Integer> states;
}
