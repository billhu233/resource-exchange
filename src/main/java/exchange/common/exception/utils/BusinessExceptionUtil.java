package exchange.common.exception.utils;

import exchange.common.exception.BusinessException;
import exchange.common.exception.IResultCode;
import exchange.common.exception.ResultCode;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * <p>
 *     {@link BusinessException} 工具类
 *      目的在于，格式化异常信息提示。
 *      考虑到 String.format 在参数不正确时会报错，因此使用 {} 作为占位符，
 *      并使用 {@link #doFormat(int, String, Object...)} 方法来格式化
 * </p>
 *
 * @author sunql
 * @since 2023-10-21
 */
@Slf4j
public class BusinessExceptionUtil {

    /**
     * 错误码提示模板
     */
    private static final ConcurrentMap<Integer, String> MESSAGES = new ConcurrentHashMap<>();

    public static void putAll(Map<Integer, String> messages) {
        BusinessExceptionUtil.MESSAGES.putAll(messages);
    }

    public static void put(Integer code, String message) {
        BusinessExceptionUtil.MESSAGES.put(code, message);
    }

    public static void delete(Integer code, String message) {
        BusinessExceptionUtil.MESSAGES.remove(code, message);
    }

    // ========== 和 ServiceException 的集成 ==========

    public static BusinessException exception(IResultCode errorCode) {
        String messagePattern = MESSAGES.getOrDefault(errorCode.getCode(), errorCode.getMessage());
        return exception0(errorCode.getCode(), messagePattern);
    }

    public static BusinessException exception(IResultCode errorCode, Object... params) {
        String messagePattern = MESSAGES.getOrDefault(errorCode.getCode(), errorCode.getMessage());
        return exception0(errorCode.getCode(), messagePattern, params);
    }

    /**
     * 创建指定编号的 ServiceException 的异常
     *
     * @param code 编号
     * @return 异常
     */
    public static BusinessException exception(Integer code) {
        return exception0(code, MESSAGES.get(code));
    }

    /**
     * 创建指定编号的 ServiceException 的异常
     *
     * @param code 编号
     * @param params 消息提示的占位符对应的参数
     * @return 异常
     */
    public static BusinessException exception(Integer code, Object... params) {
        return exception0(code, MESSAGES.get(code), params);
    }

    public static BusinessException exception0(Integer code, String messagePattern, Object... params) {
        String message = doFormat(code, messagePattern, params);
        return new BusinessException(code, message);
    }

    public static BusinessException invalidParamException(String messagePattern, Object... params) {
        return exception0(ResultCode.BAD_REQUEST.getCode(), messagePattern, params);
    }

    // ========== 格式化方法 ==========

    /**
     * 将错误编号对应的消息使用 params 进行格式化。
     *
     * @param code           错误编号
     * @param messagePattern 消息模版
     * @param params         参数
     * @return 格式化后的提示
     */
//    @VisibleForTesting
    public static String doFormat(int code, String messagePattern, Object... params) {
        StringBuilder buff = new StringBuilder(messagePattern.length() + 50);
        int i = 0, j, l;
        for (l = 0; l < params.length; l++) {
            j = messagePattern.indexOf("{}", i);
            if (j == -1) {
                log.error("[doFormat][参数过多：错误码({})|错误内容({})|参数({})", code, messagePattern, params);
                if (i == 0) {
                    return messagePattern;
                } else {
                    buff.append(messagePattern.substring(i));
                    return buff.toString();
                }
            } else {
                buff.append(messagePattern, i, j);
                buff.append(params[l]);
                i = j + 2;
            }
        }
        if (messagePattern.indexOf("{}", i) != -1) {
            log.error("[doFormat][参数过少：错误码({})|错误内容({})|参数({})", code, messagePattern, params);
        }
        buff.append(messagePattern.substring(i));
        return buff.toString();
    }
}
