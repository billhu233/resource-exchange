package exchange.common.utils;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 工具类：日期差
 */
public class DateDurationUtil {

    public enum DateUnitEnum {
        YEAR,
        MONTH,
        DAY,
        HOUR,
        MINUTE,
        SECOND,
        MILLISECOND,// 毫秒：1秒 = 1000毫秒
        MICROSECOND,// 微妙：1毫秒 = 1000微妙
        NANOSECOND,// 纳秒：1微妙 = 1000纳秒
//        PICOSECOND,// 皮秒：1纳秒 = 1000皮秒【Duration.between最小单位是纳秒，不支持皮秒】
    }

    /**
     * 时间差
     *
     * @param from     时间从
     * @param to       时间到
     * @param dateUnit 时间单位
     */
    private static long duration(LocalDateTime from, LocalDateTime to, DateUnitEnum dateUnit) {
        long valR = 0L;
        if (from == null || to == null || dateUnit == null) {
            return valR;
        }
        Duration duration = Duration.between(from, to);
        switch (dateUnit) {
            case NANOSECOND:
                valR = duration.toNanos();
                break;
            case MICROSECOND:
                valR = duration.toNanos() / 1000;
                break;
            case MILLISECOND:
                valR = duration.toMillis();
                break;
            case SECOND:
                valR = duration.toSeconds();
                break;
            case MINUTE:
                valR = duration.toMinutes();
                break;
            case HOUR:
                valR = duration.toHours();
                break;
            case DAY:
                valR = duration.toDays();
                break;
            case MONTH:// 由于各月的天数并不一致，所以直接使用年月计算月份差
                valR = (to.getYear() - from.getYear()) * 12L + (to.getMonthValue() - from.getMonthValue());
                break;
            case YEAR:// 由于各年的天数并不一致，所以直接使用年计算年份差
                valR = to.getYear() - from.getYear();
                break;
            default:
                break;
        }
        return valR;
    }

    //------------------------------------------------------------------------------------------

    /**
     * 时间差：纳秒（=1秒/1000/1000/1000）
     */
    public static long durationNanosecond(LocalDateTime from, LocalDateTime to) {
        return duration(from, to, DateUnitEnum.NANOSECOND);
    }

    /**
     * 时间差：微妙（=1秒/1000/1000）
     */
    public static long durationMicrosecond(LocalDateTime from, LocalDateTime to) {
        return duration(from, to, DateUnitEnum.MICROSECOND);
    }

    /**
     * 时间差：毫秒（=1秒/1000）
     */
    public static long durationMillisecond(LocalDateTime from, LocalDateTime to) {
        return duration(from, to, DateUnitEnum.MILLISECOND);
    }

    /**
     * 时间差：秒
     */
    public static long durationSecond(LocalDateTime from, LocalDateTime to) {
        return duration(from, to, DateUnitEnum.SECOND);
    }

    /**
     * 时间差：分
     */
    public static long durationMinute(LocalDateTime from, LocalDateTime to) {
        return duration(from, to, DateUnitEnum.MINUTE);
    }

    /**
     * 时间差：时
     */
    public static long durationHour(LocalDateTime from, LocalDateTime to) {
        return duration(from, to, DateUnitEnum.HOUR);
    }

    /**
     * 时间差：天
     */
    public static long durationDay(LocalDateTime from, LocalDateTime to) {
        return duration(from, to, DateUnitEnum.DAY);
    }

    /**
     * 时间差：月
     */
    public static long durationMonth(LocalDateTime from, LocalDateTime to) {
        return duration(from, to, DateUnitEnum.MONTH);
    }

    /**
     * 时间差：年
     */
    public static long durationYear(LocalDateTime from, LocalDateTime to) {
        return duration(from, to, DateUnitEnum.YEAR);
    }

    //------------------------------------------------------------------------------------------

    /**
     * 时间差：纳秒（=1秒/1000/1000/1000）
     */
    public static long durationNanosecond(LocalDate from, LocalDate to) {
        if (from == null || to == null) {
            return 0L;
        }
        return duration(from.atStartOfDay(), to.atStartOfDay(), DateUnitEnum.NANOSECOND);
    }

    /**
     * 时间差：微妙（=1秒/1000/1000）
     */
    public static long durationMicrosecond(LocalDate from, LocalDate to) {
        if (from == null || to == null) {
            return 0L;
        }
        return duration(from.atStartOfDay(), to.atStartOfDay(), DateUnitEnum.MICROSECOND);
    }

    /**
     * 时间差：毫秒（=1秒/1000）
     */
    public static long durationMillisecond(LocalDate from, LocalDate to) {
        if (from == null || to == null) {
            return 0L;
        }
        return duration(from.atStartOfDay(), to.atStartOfDay(), DateUnitEnum.MILLISECOND);
    }

    /**
     * 时间差：秒
     */
    public static long durationSecond(LocalDate from, LocalDate to) {
        if (from == null || to == null) {
            return 0L;
        }
        return duration(from.atStartOfDay(), to.atStartOfDay(), DateUnitEnum.SECOND);
    }

    /**
     * 时间差：分
     */
    public static long durationMinute(LocalDate from, LocalDate to) {
        if (from == null || to == null) {
            return 0L;
        }
        return duration(from.atStartOfDay(), to.atStartOfDay(), DateUnitEnum.MINUTE);
    }

    /**
     * 时间差：时
     */
    public static long durationHour(LocalDate from, LocalDate to) {
        if (from == null || to == null) {
            return 0L;
        }
        return duration(from.atStartOfDay(), to.atStartOfDay(), DateUnitEnum.HOUR);
    }

    /**
     * 时间差：天
     */
    public static long durationDay(LocalDate from, LocalDate to) {
        if (from == null || to == null) {
            return 0L;
        }
        return duration(from.atStartOfDay(), to.atStartOfDay(), DateUnitEnum.DAY);
    }

    /**
     * 时间差：月
     */
    public static long durationMonth(LocalDate from, LocalDate to) {
        if (from == null || to == null) {
            return 0L;
        }
        return duration(from.atStartOfDay(), to.atStartOfDay(), DateUnitEnum.MONTH);
    }

    /**
     * 时间差：年
     */
    public static long durationYear(LocalDate from, LocalDate to) {
        if (from == null || to == null) {
            return 0L;
        }
        return duration(from.atStartOfDay(), to.atStartOfDay(), DateUnitEnum.YEAR);
    }

}
