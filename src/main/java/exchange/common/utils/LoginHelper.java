package exchange.common.utils;

import cn.dev33.satoken.context.SaHolder;
import cn.dev33.satoken.context.model.SaStorage;
import cn.dev33.satoken.session.SaSession;
import cn.dev33.satoken.stp.SaLoginModel;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.util.ObjectUtil;
import exchange.common.exception.utils.AssertUtil;
import exchange.userpg.domain.entity.UserInfo;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Objects;

import static exchange.common.constants.SystemConstants.REQUEST_LOGIN_USER_ID;

/**
 * 登录鉴权助手
 * <p>
 * user_type 为 用户类型 同一个用户表 可以有多种用户类型 例如 pc,app
 * device 为 设备类型 同一个用户类型 可以有 多种设备类型 例如 web,ios
 * 可以组成 用户类型与设备类型多对多的 权限灵活控制
 * <p>
 * 多用户体系 针对 多种用户类型 但权限控制不一致
 * 可以组成 多用户类型表与多设备类型 分别控制权限
 * </p>
 *
 * @author sunql
 * @since 2023-10-27
 */
@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class LoginHelper {
    public static final String LOGIN_USER_KEY = "loginUser";
    public static final String USER_KEY = "userId";

    /**
     * 登录系统 基于 设备类型
     * 针对相同用户体系不同设备
     *
     * @param loginUser 登录用户信息
     */
    public static void login(UserInfo loginUser, Long pcLoginTimeout) {
        SaStorage storage = SaHolder.getStorage();
        storage.set(LOGIN_USER_KEY, loginUser);
        storage.set(USER_KEY, loginUser.getId());
        SaLoginModel model = new SaLoginModel();

        StpUtil.login(loginUser.getId(), model.setExtra(USER_KEY, loginUser.getId()));
        StpUtil.getTokenSession().set(LOGIN_USER_KEY, loginUser);
//        RedisUtils.setCacheObject(LOGIN_USER_PREFIX + loginId, loginUser, Duration.ofSeconds(model.getActiveTimeout() * 2));
    }

    /**
     * 获取登录用户
     */
    public static UserInfo getSaTokenUser() {
        UserInfo loginUser = (UserInfo) SaHolder.getStorage().get(LOGIN_USER_KEY);
        if (loginUser != null) {
            return loginUser;
        }
        SaSession session = StpUtil.getTokenSession();
        AssertUtil.notNull(session, "未获取到当前登陆用户信息");
        loginUser = (UserInfo) session.get(LOGIN_USER_KEY);
        SaHolder.getStorage().set(LOGIN_USER_KEY, loginUser);
        return loginUser;
    }

    /**
     * 用户登出，按账户id
     */
    public static void logout(Long userId) {
        if (Objects.isNull(userId)) {
            return;
        }
        StpUtil.logout(userId);
    }

    /**
     * 获取用户(多级缓存)
     */
    public static UserInfo getLoginUser() {
        HttpServletRequest request = ServletUtils.getRequest();
        if (Objects.nonNull(request)) {
            String loginId = request.getHeader(REQUEST_LOGIN_USER_ID);
            return getLoginUser(loginId);
        }

        log.error("未获取到前登录用户信息");
//        throw new Exception("未获取到前登录用户信息");
        return null;
    }

    /**
     * 获取用户基于token
     */
    public static UserInfo getLoginUser(String loginId) {
        String tokenVal = StpUtil.getTokenValueByLoginId(loginId);
        SaSession session = StpUtil.getTokenSessionByToken(tokenVal);
        if (ObjectUtil.isNull(session)) {
            return null;
        }
        UserInfo loginUser = (UserInfo) session.get(LOGIN_USER_KEY);
//        RedisUtils.setCacheObject(LOGIN_USER_PREFIX + loginId, loginUser, Duration.ofSeconds(session.getTimeout() * 2));
        return loginUser;
    }

    /**
     * 获取用户id
     */
    public static Long getUserId() {
        Long userId;
        try {
            userId = getLoginUser().getId();
        } catch (Exception e) {
            return null;
        }
        return userId;
    }

    /**
     * 获取用户账户
     */
    public static String getUsername() {
        return getLoginUser().getUserName();
    }

}
