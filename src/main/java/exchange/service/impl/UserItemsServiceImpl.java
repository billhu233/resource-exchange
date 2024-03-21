package exchange.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import exchange.domain.entity.UserItems;
import exchange.mapper.UserItemsMapper;
import exchange.service.IUserItemsService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户物品表 服务实现类
 * </p>
 *
 * @author hb
 * @since 2024-02-29
 */
@Service
public class UserItemsServiceImpl extends ServiceImpl<UserItemsMapper, UserItems> implements IUserItemsService {

}
