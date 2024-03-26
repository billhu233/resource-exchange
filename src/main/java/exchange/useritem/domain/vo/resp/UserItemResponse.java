package exchange.useritem.domain.vo.resp;

import exchange.common.domain.vo.resp.CommonRespVO;
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
public class UserItemResponse extends CommonRespVO {

    private String itemName;

    private String itemType;

    private Long itemSize;

//    private String itemPath;

//    private String itemMd5;

    private BigDecimal itemPoint;

    private Integer likeNumber;

    private Integer weakNumber;

    private Integer downloadNumber;

    private String description;

    private List<String> itemLabel;
}
