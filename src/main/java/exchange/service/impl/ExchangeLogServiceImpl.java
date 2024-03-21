package exchange.service.impl;

import exchange.domain.entity.ExchangeLog;
import exchange.mapper.ExchangeLogMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import exchange.service.IExchangeLogService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 交换日志 服务实现类
 * </p>
 *
 * @author hb
 * @since 2024-02-29
 */
@Service
public class ExchangeLogServiceImpl extends ServiceImpl<ExchangeLogMapper, ExchangeLog> implements IExchangeLogService {

}
