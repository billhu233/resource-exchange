package exchange.mapper;

import exchange.domain.entity.ExchangeLog;
import exchange.mybatis.core.mapper.BaseMapperX;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 交换日志 Mapper 接口
 * </p>
 *
 * @author hb
 * @since 2024-02-29
 */
@Mapper
public interface ExchangeLogMapper extends BaseMapperX<ExchangeLog> {

}
