package exchange.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import exchange.domain.entity.UserExpand;
import exchange.mapper.UserExpandMapper;
import exchange.service.IUserExpandService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户扩展信息 服务实现类
 * </p>
 *
 * @author hb
 * @since 2024-02-29
 */
@Service
public class UserExpandServiceImpl extends ServiceImpl<UserExpandMapper, UserExpand> implements IUserExpandService {

}
