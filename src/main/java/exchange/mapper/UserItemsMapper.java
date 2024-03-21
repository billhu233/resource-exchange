package exchange.mapper;

import exchange.domain.entity.UserItems;
import exchange.mybatis.core.mapper.BaseMapperX;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 用户物品表 Mapper 接口
 * </p>
 *
 * @author hb
 * @since 2024-02-29
 */
@Mapper
public interface UserItemsMapper extends BaseMapperX<UserItems> {

}
