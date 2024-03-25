package exchange.useritem.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import exchange.useritem.domain.entity.UserItems;
import exchange.useritem.mapper.UserItemsMapper;
import exchange.useritem.service.IUserItemsService;
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
