package exchange.useritem.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import exchange.useritem.domain.entity.ItemInfo;
import exchange.useritem.mapper.ItemInfoMapper;
import exchange.useritem.service.IItemInfoService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 物品信息表 服务实现类
 * </p>
 *
 * @author hb
 * @since 2024-02-29
 */
@Service
public class ItemInfoServiceImpl extends ServiceImpl<ItemInfoMapper, ItemInfo> implements IItemInfoService {

}
