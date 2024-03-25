package exchange.useritem.service.impl;

import exchange.useritem.domain.entity.ItemLabel;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import exchange.useritem.mapper.ItemLabelMapper;
import exchange.useritem.service.IItemLabelService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 物品标签 服务实现类
 * </p>
 *
 * @author hb
 * @since 2024-03-25
 */
@Service
public class ItemLabelServiceImpl extends ServiceImpl<ItemLabelMapper, ItemLabel> implements IItemLabelService {

}
