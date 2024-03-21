package exchange.userpg.convert;

import exchange.userpg.domain.entity.UserInfo;
import exchange.userpg.domain.vo.req.UserRegisterRequest;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/*
 * @description
 * @author hb
 * @since 2024/3/21 17:22
 */
@Mapper
public interface UserInfoConvert {
    UserInfoConvert INSTANCE = Mappers.getMapper(UserInfoConvert.class);

    UserInfo convert(UserRegisterRequest bean);
}
