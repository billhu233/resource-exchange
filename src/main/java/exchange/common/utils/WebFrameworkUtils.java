package exchange.common.utils;

import cn.hutool.core.util.StrUtil;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Objects;

import static exchange.common.constants.SystemConstants.*;


/**
 * 专属于 web 包的工具类
 *
 * @author sunql
 * @since 2023-10-21
 */
public class WebFrameworkUtils {

    public static void setLoginUserId(ServletRequest request, Long userId) {
        request.setAttribute(REQUEST_LOGIN_USER_ID, userId);
    }

    public static Long getLoginUserId() {
        HttpServletRequest request = getRequest();
        return getLoginUserId(request);
    }

    /**
     * 获得当前用户的编号，从请求中
     * 注意：该方法仅限于 framework 框架使用！！！
     *
     * @param request 请求
     * @return 用户编号
     */
    public static Long getLoginUserId(HttpServletRequest request) {
        if (request == null) {
            return null;
        }
        Long userId = (Long) request.getAttribute(REQUEST_LOGIN_USER_ID);
        if (Objects.isNull(userId)) {
            String userIdStr = request.getHeader(REQUEST_LOGIN_USER_ID);
            userId = StrUtil.isBlank(userIdStr) ? DEFAULT_SYSTEM_USER : Long.valueOf(userIdStr.split(LOGIN_ID_JOIN_CODE)[0]);
        }
        return userId;
    }

    public static HttpServletRequest getRequest() {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        if (!(requestAttributes instanceof ServletRequestAttributes servletRequestAttributes)) {
            return null;
        }
        return servletRequestAttributes.getRequest();
    }
}
