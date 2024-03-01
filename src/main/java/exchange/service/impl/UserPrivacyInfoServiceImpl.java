package exchange.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import exchange.entity.UserPrivacyInfo;
import exchange.mapper.UserPrivacyInfoMapper;
import exchange.service.IUserPrivacyInfoService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户私密信息 服务实现类
 * </p>
 *
 * @author hb
 * @since 2024-02-29
 */
@Service
public class UserPrivacyInfoServiceImpl extends ServiceImpl<UserPrivacyInfoMapper, UserPrivacyInfo> implements IUserPrivacyInfoService {

}
