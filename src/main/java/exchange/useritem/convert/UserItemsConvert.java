package exchange.useritem.convert;

import exchange.useritem.domain.entity.ItemInfo;
import exchange.useritem.domain.entity.ItemLabel;
import exchange.useritem.domain.entity.UserItems;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.math.BigDecimal;

/*
 * @description
 * @author hb
 * @since 2024/3/21 17:22
 */
@Mapper
public interface UserItemsConvert {
    UserItemsConvert INSTANCE = Mappers.getMapper(UserItemsConvert.class);

    ItemInfo convert(String itemName, String itemType, Long itemSize, String itemMd5, BigDecimal itemPoint, String itemPath);

    UserItems convert(Long itemId, Long userId, String description, int state);

    ItemLabel convert(Long itemId, String labelName);
}
