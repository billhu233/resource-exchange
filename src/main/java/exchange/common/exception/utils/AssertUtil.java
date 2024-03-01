package exchange.common.exception.utils;

import cn.hutool.core.collection.CollectionUtil;
import exchange.common.exception.IResultCode;
import org.apache.commons.lang3.StringUtils;

import java.util.Collection;
import java.util.Objects;

import static exchange.common.exception.utils.BusinessExceptionUtil.exception;
import static exchange.common.exception.utils.BusinessExceptionUtil.invalidParamException;


/**
 * <p>
 *      断言工具类
 * </p>
 *
 * @author sunql
 * @since 2023-10-21
 */
public class AssertUtil {

    /**
     * 判断对象是否为空
     */
    public static void notNull(Object object, IResultCode resultCode) {
        if (Objects.isNull(object)) {
            throw exception(resultCode);
        }
    }

    public static void notNull(Object object, String errorMsgTemplate, Object... params) {
        if (Objects.isNull(object)) {
            throw invalidParamException(errorMsgTemplate, params);
        }
    }

    /**
     * 判断对象是否为空
     *
     * @param params 异常信息含有占位符时，需要替换的变量
     */
    public static void notNull(Object object, IResultCode resultCode, Object... params) {
        if (Objects.isNull(object)) {
            throw exception(resultCode, params);
        }
    }

    /**
     * 判断字符串是否为空
     */
    public static void notBlank(CharSequence str, IResultCode resultCode, Object... params) {
        if (StringUtils.isBlank(str)) {
            throw exception(resultCode, params);
        }
    }

    /**
     * 判断集合是否为空
     */
    public static void notEmpty(Collection<?> coll, IResultCode resultCode, Object... params) {
        if (CollectionUtil.isEmpty(coll)) {
            throw exception(resultCode, params);
        }
    }


    /**
     * 判断条件是否为真
     */
    public static void isTrue(boolean condition, IResultCode resultCode) {
        if (condition) {
            throw exception(resultCode);
        }
    }

    /**
     * 判断条件是否为真
     */
    public static void isTrue(boolean condition, String errorMsgTemplate, Object... params) {
        if (condition) {
            throw invalidParamException(errorMsgTemplate, params);
        }
    }

    /**
     * 判断条件是否为真
     */
    public static void isTrue(boolean condition, IResultCode resultCode, Object... params) {
        if (condition) {
            throw exception(resultCode, params);
        }
    }
    /**
     * 判断条件是否为假
     */
    public static void isFalse(boolean condition, IResultCode resultCode, Object... params) {
        if (!condition) {
            throw exception(resultCode, params);
        }
    }

}
