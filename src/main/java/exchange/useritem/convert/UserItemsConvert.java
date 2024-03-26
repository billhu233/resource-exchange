package exchange.useritem.convert;

import exchange.useritem.domain.entity.ItemInfo;
import exchange.useritem.domain.entity.ItemLabel;
import exchange.useritem.domain.entity.UserItems;
import exchange.userpg.domain.entity.UserInfo;
import exchange.userpg.domain.vo.req.UserRegisterRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.math.BigDecimal;
import java.util.List;

/*
 * @description
 * @author hb
 * @since 2024/3/21 17:22
 */
@Mapper
public interface UserItemsConvert {
    UserItemsConvert INSTANCE = Mappers.getMapper(UserItemsConvert.class);

    ItemInfo convert(String itemName, String itemType, Long itemSize, String itemMd5, BigDecimal itemPoint);

    UserItems convert(Long itemId, Long userId, String description);

    ItemLabel convert(Long itemId, String labelName);
}
