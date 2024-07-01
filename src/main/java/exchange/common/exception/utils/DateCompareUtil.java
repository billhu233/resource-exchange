package exchange.common.exception.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 工具类：日期比较
 * <p>
 * LocalDate和另一个日期比较，不管是使用compareTo，还是isAfter、isBefore、isEqual，如果另一个日期为null，都会报空指针。LocalDateTime同理
 * 使用equals可以正常比较
 * 为了业务代码可以方便的比较日期大小【而不用特意关注比较的两个日期是否为空】编写了该工具类
 */
public class DateCompareUtil {

    //region LocalDateTime

    /**
     * a等于b
     */
    public static boolean isEqual(LocalDateTime a, LocalDateTime b) {
        if (a == null && b == null) {
            // 2个都为null
            return true;
        } else if (a == null || b == null) {
            // 其中1个为null
            return false;
        } else {
            // 2个都有值
            return a.isEqual(b);
        }
    }

    /**
     * a>b
     */
    public static boolean isAfter(LocalDateTime a, LocalDateTime b) {
        if (a == null || b == null) {
            // 其中1个为null 或 2个都为null
            return false;
        } else {
            // 2个都有值
            return a.isAfter(b);
        }
    }

    /**
     * a>=b
     */
    public static boolean isAfterOrEqual(LocalDateTime a, LocalDateTime b) {
        return isAfter(a, b) || isEqual(a, b);
    }

    /**
     * a<b
     */
    public static boolean isBefore(LocalDateTime a, LocalDateTime b) {
        if (a == null || b == null) {
            // 其中1个为null 或 2个都为null
            return false;
        } else {
            // 2个都有值
            return a.isBefore(b);
        }
    }

    /**
     * a<=b
     */
    public static boolean isBeforeOrEqual(LocalDateTime a, LocalDateTime b) {
        return isBefore(a, b) || isEqual(a, b);
    }
    //endregion

    //region LocalDate

    /**
     * a等于b
     */
    public static boolean isEqual(LocalDate a, LocalDate b) {
        if (a == null && b == null) {
            // 2个都为null
            return true;
        } else if (a == null || b == null) {
            // 其中1个为null
            return false;
        } else {
            // 2个都有值
            return a.isEqual(b);
        }
    }

    /**
     * a>b
     */
    public static boolean isAfter(LocalDate a, LocalDate b) {
        if (a == null || b == null) {
            // 其中1个为null 或 2个都为null
            return false;
        } else {
            // 2个都有值
            return a.isAfter(b);
        }
    }

    /**
     * a>=b
     */
    public static boolean isAfterOrEqual(LocalDate a, LocalDate b) {
        return isAfter(a, b) || isEqual(a, b);
    }

    /**
     * a<b
     */
    public static boolean isBefore(LocalDate a, LocalDate b) {
        if (a == null || b == null) {
            // 其中1个为null 或 2个都为null
            return false;
        } else {
            // 2个都有值
            return a.isBefore(b);
        }
    }

    /**
     * a<=b
     */
    public static boolean isBeforeOrEqual(LocalDate a, LocalDate b) {
        return isBefore(a, b) || isEqual(a, b);
    }
    //endregion

}
