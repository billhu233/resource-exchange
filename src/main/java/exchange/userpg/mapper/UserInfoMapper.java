package exchange.userpg.mapper;

import exchange.userpg.domain.entity.UserInfo;
import exchange.mybatis.core.mapper.BaseMapperX;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 用户信息 Mapper 接口
 * </p>
 *
 * @author hb
 * @since 2024-02-29
 */
@Mapper
public interface UserInfoMapper extends BaseMapperX<UserInfo> {

}
