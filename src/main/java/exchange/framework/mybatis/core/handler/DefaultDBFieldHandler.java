package exchange.framework.mybatis.core.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import exchange.common.utils.WebFrameworkUtils;
import exchange.framework.mybatis.dataobject.BaseDO;
import org.apache.ibatis.reflection.MetaObject;

import java.time.LocalDateTime;
import java.util.Objects;

import static exchange.common.constants.SystemConstants.DEFAULT_SYSTEM_USER;


/**
 * 通用参数填充实现类
 *
 * 如果没有显式的对通用参数进行赋值，这里会对通用参数进行填充、赋值
 *
 * @author sunql
 * @since 2023-10-21
 */
public class DefaultDBFieldHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        if (Objects.isNull(metaObject)) {
            return;
        }

        if (metaObject.getOriginalObject() instanceof BaseDO baseDO) {
            LocalDateTime current = LocalDateTime.now();
            // 创建时间为空，则以当前时间为插入时间
            if (Objects.isNull(baseDO.getCreateTime())) {
                baseDO.setCreateTime(current);
            }
            // 更新时间为空，则以当前时间为更新时间
            if (Objects.isNull(baseDO.getUpdateTime())) {
                baseDO.setUpdateTime(current);
            }

            Long userId = WebFrameworkUtils.getLoginUserId();

            // 当前登录用户不为空，创建人为空，则当前登录用户为创建人，否则为系统默认用户
            if (Objects.isNull(baseDO.getCreatorId())) {
                baseDO.setCreatorId(Objects.nonNull(userId) ? userId : DEFAULT_SYSTEM_USER);
            }

            // 当前登录用户不为空，更新人为空，则当前登录用户为更新人，否则为系统默认用户
            if (Objects.isNull(baseDO.getUpdaterId())) {
                baseDO.setUpdaterId(Objects.nonNull(userId) ? userId : DEFAULT_SYSTEM_USER);
            }
        }
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        // 更新时间为空，则以当前时间为更新时间
        Object modifyTime = getFieldValByName("updateTime", metaObject);
        setFieldValByName("updateTime", LocalDateTime.now(), metaObject);

        // 当前登录用户不为空，更新人为空，则当前登录用户为更新人
        Long userId = WebFrameworkUtils.getLoginUserId();
        if (Objects.isNull(userId)) {
            userId = DEFAULT_SYSTEM_USER;
        }
        setFieldValByName("updaterId", userId, metaObject);
    }
}
