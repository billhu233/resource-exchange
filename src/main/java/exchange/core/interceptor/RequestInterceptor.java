package exchange.core.interceptor;

import com.google.common.collect.Lists;
import exchange.common.utils.DateDurationUtil;
import exchange.common.utils.DateUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 *
 **/
@Slf4j
@Component
@ConditionalOnProperty(value = "closed.config.jwt", havingValue = "false", matchIfMissing = true)
public class RequestInterceptor implements HandlerInterceptor {

    private Map<String, Integer> methodTimeMap = new HashMap<>() {
        {
            put("getVerifyCode", 5);
        }
    };

    public List<String> clearMethodNames = Lists.newArrayList("login", "register");

    @Value("${spring.custom.ipAddress}")
    private String ipAddressKey;

    @Value("${spring.custom.outTime}")
    private String outTimeKey = "outTime";

    /**
     * 在请求处理之前进行调用（Controller方法调用之前）
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String methodName = request.getMethod();
        if (!methodTimeMap.containsKey(methodName)) {
            return true;
        }

        Integer methodOutTime = methodTimeMap.get(methodName);

        String ipAddress = request.getRemoteAddr();
        HttpSession session = request.getSession();

        Object sessionIpAddress = session.getAttribute(ipAddressKey);
        if (Objects.isNull(sessionIpAddress)) {
            session.setAttribute(ipAddressKey, ipAddress);
            session.setAttribute(outTimeKey, DateUtil.localDateTime2String(LocalDateTime.now().plusMonths(methodOutTime), DateUtil.YYYY_MM_DD_HH_MM_SS));
            return true;
        }

        LocalDateTime outTime = LocalDateTime.parse(session.getAttribute(outTimeKey).toString());
        Long diffSecond = DateDurationUtil.durationSecond(outTime, LocalDateTime.now());
        //距离过期时间小于30秒，就可以重新请求
        if (diffSecond < 30) {
            session.setAttribute(outTimeKey, DateUtil.localDateTime2String(LocalDateTime.now().plusMonths(methodOutTime), DateUtil.YYYY_MM_DD_HH_MM_SS));
            return true;
        }

        throw new Exception(String.format("{0}后再请求验证码", 30 - diffSecond));
    }

    /**
     * 请求处理之后进行调用，但是在视图被渲染之前（Controller方法调用之后）
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {
    }

    /**
     * 在整个请求结束之后被调用，也就是在DispatcherServlet 渲染了对应的视图之后执行（主要是用于进行资源清理工作）
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        String methodName = request.getMethod();
        if (clearMethodNames.contains(methodName)) {
            HttpSession session = request.getSession();
            session.removeAttribute(ipAddressKey);
            session.removeAttribute(outTimeKey);
        }
    }
}
