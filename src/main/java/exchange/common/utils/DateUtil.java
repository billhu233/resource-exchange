package exchange.common.utils;


import org.apache.commons.lang3.StringUtils;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 工具类：日期格式转换
 * <p>
 * 表示日期的类，有以下几种常用的：（前5种表示当前时区的时间、6/7种表示0时区的时间[即格林威治时间]）
 * 1、java.util.Date：jdk第一版日期类
 * 2、java.time.LocalDateTime：jdk第三版（最新版）日期类
 * 3、java.time.LocalDate：jdk第三版（最新版）日期类
 * 4、java.lang.String
 * 5、java.sql.Timestamp：继承java.util.Date
 * <p>
 * 6、java.lang.Long：时间戳
 * 7、java.time.Instant：jdk第三版（最新版）日期类，0时区的日期时间
 * <p>
 * 还有以下几种不常用的：（不常用的暂不提供转换）
 * 8、java.util.Calendar：jdk第二版日期类，现在已经不怎么使用，可以使用LocalDateTime替代
 * 9、java.sql.Date：继承java.util.Date
 * <p>
 * 注：java.sql.Date/java.sql.Timestamp，主要用于写一些与数据库连接时的日期处理，如：Mybatis
 * 注：java.sql.Date只存储日期数据不存储时间数据
 * 注：java.sql.Timestamp存储日期数据和时间数据
 */
public class DateUtil {

    public static final String YYYY = "yyyy";
    public static final String YYYY_MM = "yyyy-MM";
    public static final String YYYY_MM_DD = "yyyy-MM-dd";
    public static final String YYYY_MM_DD_HH = "yyyy-MM-dd HH";
    public static final String YYYY_MM_DD_HH_MM = "yyyy-MM-dd HH:mm";
    public static final String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
    public static final String YYYY_MM_DD_HH_MM_SS_SSS = "yyyy-MM-dd HH:mm:ss.SSS";
    public static final String YYYY_MM_DD_T_HH_MM_SS_Z = "yyyy-MM-dd'T'HH:mm:ss'Z'";
    public static final String YYYYMMDDHHMMSS = "yyyyMMddHHmmss";

    private static final ZoneId zoneId = ZoneId.systemDefault();

    private static final Map<String, SimpleDateFormat> mapFormat = new ConcurrentHashMap<>();
    private static final Object lockObject = new Object();

    /**
     * 对调用的每种格式化日期串，创建一个格式化类。避免创建过多的格式化类
     */
    private static SimpleDateFormat getFormatFromMap(String pattern) {
        if (mapFormat.containsKey(pattern)) {
            return mapFormat.get(pattern);
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        mapFormat.put(pattern, simpleDateFormat);
        return simpleDateFormat;
    }

    //region Date转其他

    /**
     * Date转LocalDateTime
     */
    public static LocalDateTime date2LocalDateTime(Date value) {
        return Objects.isNull(value) ? null : Instant.ofEpochMilli(value.getTime()).atZone(zoneId).toLocalDateTime();
    }

    /**
     * Date转LocalDate
     */
    public static LocalDate date2LocalDate(Date value) {
        return Objects.isNull(value) ? null : Instant.ofEpochMilli(value.getTime()).atZone(zoneId).toLocalDate();
    }

    /**
     * Date转String
     */
    public static String date2String(Date value, String pattern) {
        if (StringUtils.isBlank(pattern)) {
            throw new RuntimeException("请输入pattern，如：yyyy-MM-dd HH:mm:ss");
        }
        if (Objects.isNull(value)) {
            return null;
        }
        SimpleDateFormat simpleDateFormat = getFormatFromMap(pattern);
        // 同步锁，处理：SimpleDateFormat线程不安全的问题
        synchronized (lockObject) {
            return simpleDateFormat.format(value);
        }
    }

    /**
     * Date转Timestamp
     */
    public static Timestamp date2Timestamp(Date value) {
        return Objects.isNull(value) ? null : new Timestamp(value.getTime());
    }

    /**
     * Date转Long
     */
    public static Long date2Long(Date value) {
        return Objects.isNull(value) ? null : value.getTime();
    }

    /**
     * Date转Instant
     */
    public static Instant date2Instant(Date value) {
        return Objects.isNull(value) ? null : Instant.ofEpochMilli(value.getTime());
    }
    //endregion

    //region LocalDateTime转其他

    /**
     * LocalDateTime转Date
     */
    public static Date localDateTime2Date(LocalDateTime value) {
        return Objects.isNull(value) ? null : Date.from(value.atZone(zoneId).toInstant());
    }

    /**
     * LocalDateTime转LocalDate
     */
    public static LocalDate localDateTime2LocalDate(LocalDateTime value) {
        return Objects.isNull(value) ? null : value.toLocalDate();
    }

    /**
     * LocalDateTime转String
     */
    public static String localDateTime2String(LocalDateTime value, String pattern) {
        if (StringUtils.isBlank(pattern)) {
            throw new RuntimeException("请输入pattern，如：yyyy-MM-dd HH:mm:ss");
        }
        return Objects.isNull(value) ? null : value.format(DateTimeFormatter.ofPattern(pattern));
    }

    /**
     * LocalDateTime转Timestamp
     */
    public static Timestamp localDateTime2Timestamp(LocalDateTime value) {
        return Objects.isNull(value) ? null : Timestamp.valueOf(value);
    }

    /**
     * LocalDateTime转Long
     */
    public static Long localDateTime2Long(LocalDateTime value) {
        return Objects.isNull(value) ? null : value.atZone(zoneId).toInstant().toEpochMilli();
    }

    /**
     * LocalDateTime转Instant
     */
    public static Instant localDateTime2Instant(LocalDateTime value) {
        return Objects.isNull(value) ? null : value.atZone(zoneId).toInstant();
    }
    //endregion

    //region LocalDate转其他

    /**
     * LocalDate转Date
     */
    public static Date localDate2Date(LocalDate value) {
        return Objects.isNull(value) ? null : Date.from(value.atStartOfDay().atZone(zoneId).toInstant());
    }

    /**
     * LocalDate转LocalDateTime
     */
    public static LocalDateTime localDate2LocalDateTime(LocalDate value) {
        return Objects.isNull(value) ? null : value.atStartOfDay();
    }

    /**
     * LocalDate转String
     */
    public static String localDate2String(LocalDate value, String pattern) {
        if (StringUtils.isBlank(pattern)) {
            throw new RuntimeException("请输入pattern，如：yyyy-MM-dd HH:mm:ss");
        }
        return Objects.isNull(value) ? null : value.atStartOfDay().format(DateTimeFormatter.ofPattern(pattern));
    }

    /**
     * LocalDate转Timestamp
     */
    public static Timestamp localDate2Timestamp(LocalDate value) {
        return Objects.isNull(value) ? null : Timestamp.valueOf(value.atStartOfDay());
    }

    /**
     * LocalDate转Long
     */
    public static Long localDate2Long(LocalDate value) {
        return Objects.isNull(value) ? null : value.atStartOfDay().atZone(zoneId).toInstant().toEpochMilli();
    }

    /**
     * LocalDate转Instant
     */
    public static Instant localDate2Instant(LocalDate value) {
        return Objects.isNull(value) ? null : value.atStartOfDay().atZone(zoneId).toInstant();
    }
    //endregion

    //region String转其他

    /**
     * String转Date
     */
    public static Date string2Date(String value, String pattern) {
        if (StringUtils.isBlank(value)) {
            return null;
        }
        if (StringUtils.isBlank(pattern)) {
            throw new RuntimeException("请输入pattern，如：yyyy-MM-dd HH:mm:ss");
        }
        SimpleDateFormat simpleDateFormat = getFormatFromMap(pattern);
        Date date = null;
        try {
            // 同步锁，处理：SimpleDateFormat线程不安全的问题
            synchronized (lockObject) {
                date = simpleDateFormat.parse(value);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    /**
     * String转LocalDateTime
     */
    public static LocalDateTime string2LocalDateTime(String value, String pattern) {
        if (StringUtils.isBlank(pattern)) {
            throw new RuntimeException("请输入pattern，如：yyyy-MM-dd HH:mm:ss");
        }
        // 下面的转换方式，格式要大于等于yyyy-MM-dd HH才能生效，小于yyyy-MM-dd HH则不能生效（如：yyyy-MM-dd）
        //LocalDateTime.parse(value, DateTimeFormatter.ofPattern(pattern))
        return date2LocalDateTime(string2Date(value, pattern));
    }

    /**
     * String转LocalDate
     */
    public static LocalDate string2LocalDate(String value, String pattern) {
        if (StringUtils.isBlank(pattern)) {
            throw new RuntimeException("请输入pattern，如：yyyy-MM-dd HH:mm:ss");
        }
        // 下面的转换方式，格式要大于等于yyyy-MM-dd才能生效，小于yyyy-MM-dd则不能生效（如：yyyy-MM）
        //LocalDate.parse(value, DateTimeFormatter.ofPattern(pattern));
        return date2LocalDate(string2Date(value, pattern));
    }

    /**
     * String转Timestamp
     */
    public static Timestamp string2Timestamp(String value, String pattern) {
        if (StringUtils.isBlank(pattern)) {
            throw new RuntimeException("请输入pattern，如：yyyy-MM-dd HH:mm:ss");
        }
        return date2Timestamp(string2Date(value, pattern));
    }

    /**
     * String转Long
     */
    public static Long string2Long(String value, String pattern) {
        if (StringUtils.isBlank(pattern)) {
            throw new RuntimeException("请输入pattern，如：yyyy-MM-dd HH:mm:ss");
        }
        return date2Long(string2Date(value, pattern));
    }

    /**
     * String转Instant
     */
    public static Instant string2Instant(String value, String pattern) {
        if (StringUtils.isBlank(pattern)) {
            throw new RuntimeException("请输入pattern，如：yyyy-MM-dd HH:mm:ss");
        }
        return date2Instant(string2Date(value, pattern));
    }
    //endregion

}
