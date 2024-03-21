package exchange.userpg.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import exchange.userpg.domain.entity.UserInfo;
import exchange.userpg.mapper.UserInfoMapper;
import exchange.userpg.service.IUserInfoService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户信息 服务实现类
 * </p>
 *
 * @author hb
 * @since 2024-02-29
 */
@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements IUserInfoService {

}
