package exchange.common.exception.utils;

import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.StrUtil;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import org.apache.commons.lang3.StringUtils;
import org.springframework.lang.Nullable;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 字符串工具类
 *
 * @author sunql
 * @since 2023-10-21
 */
public class StrUtils {
    public static final String SEPARATOR = ",";
    public static final String SINGLE_QUOTE = "'";
    private static final String EMPTY = "";
    private static final String TRUE = "true";
    private static final String FALSE = "false";

    public static String maxLength(CharSequence str, int maxLength) {
        return StrUtil.maxLength(str, maxLength - 3); // -3 的原因，是该方法会补充 ... 恰好
    }

    /**
     * 给定字符串是否以任何一个字符串开始
     * 给定字符串和数组为空都返回 false
     *
     * @param str      给定字符串
     * @param prefixes 需要检测的开始字符串
     * @since 3.0.6
     */
    public static boolean startWithAny(String str, Collection<String> prefixes) {
        if (StrUtil.isEmpty(str) || ArrayUtil.isEmpty(prefixes)) {
            return false;
        }

        for (CharSequence suffix : prefixes) {
            if (StrUtil.startWith(str, suffix, false)) {
                return true;
            }
        }
        return false;
    }

    public static List<Long> splitToLong(String value, CharSequence separator) {
        long[] longs = StrUtil.splitToLong(value, separator);
        return Arrays.stream(longs).boxed().collect(Collectors.toList());
    }

    public static List<Integer> splitToInteger(String value, CharSequence separator) {
        int[] integers = StrUtil.splitToInt(value, separator);
        return Arrays.stream(integers).boxed().collect(Collectors.toList());
    }

    public static String format(@Nullable String message, @Nullable Object... arguments) {
        if (message == null) {
            return "";
        } else if (arguments != null && arguments.length != 0) {
            StringBuilder sb = new StringBuilder((int) ((double) message.length() * 1.5D));
            int cursor = 0;
            int index = 0;

            int start;
            int end;
            for (int argsLength = arguments.length; (start = message.indexOf(123, cursor)) != -1 && (end = message.indexOf(125, start)) != -1 && index < argsLength; ++index) {
                sb.append(message, cursor, start);
                sb.append(arguments[index]);
                cursor = end + 1;
            }

            sb.append(message.substring(cursor));
            return sb.toString();
        } else {
            return message;
        }
    }

    /**
     * 当参数id为字符串时
     */
    public static String formatChar(@Nullable String message, @Nullable Object... arguments) {
        // message 为 null 返回空字符串
        if (Objects.isNull(message)) {
            return StrUtil.EMPTY;
        }
        // 参数为 null 或者为空
        if (arguments == null || arguments.length == 0) {
            return message;
        }
        StringBuilder sb = new StringBuilder((int) (message.length() * 1.5));
        int cursor = 0;
        int index = 0;
        int argsLength = arguments.length;
        for (int start, end; (start = message.indexOf('{', cursor)) != -1 && (end = message.indexOf('}', start)) != -1 && index < argsLength; ) {
            sb.append(message, cursor, start);
            String argument = SINGLE_QUOTE + arguments[index] + SINGLE_QUOTE;
            sb.append(argument);
            cursor = end + 1;
            index++;
        }
        sb.append(message.substring(cursor));
        return sb.toString();
    }

    /**
     * 对象转为字符串
     */
    public static String toStr(Object obj) {
        if (obj == null) {
            return EMPTY;
        } else if (obj instanceof String) {
            return obj.toString();
        } else if (obj instanceof Integer) {
            return obj.toString();
        } else if (obj instanceof Long) {
            return obj.toString();
        } else if (obj instanceof Boolean) {
            return ((Boolean) obj) ? TRUE : FALSE;
        } else if (obj instanceof BigDecimal) {
            return obj.toString();
        } else if (obj instanceof Date) {
            return DateConvertUtil.date2String((Date) obj, DateConvertUtil.YYYY_MM_DD_HH_MM_SS);
        } else if (obj instanceof LocalDateTime) {
            return DateConvertUtil.localDateTime2String((LocalDateTime) obj, DateConvertUtil.YYYY_MM_DD_HH_MM_SS);
        } else if (obj instanceof LocalDate) {
            return DateConvertUtil.localDate2String((LocalDate) obj, DateConvertUtil.YYYY_MM_DD);
        } else if (obj instanceof Timestamp) {
            return DateConvertUtil.timestamp2String((Timestamp) obj, DateConvertUtil.YYYY_MM_DD_HH_MM_SS);
        } else if (obj instanceof Instant) {
            return DateConvertUtil.instant2String((Instant) obj, DateConvertUtil.YYYY_MM_DD_HH_MM_SS);
        } else {
            return obj.toString();
        }
    }

    /**
     * 字符串是否为数字（含：负号、整数、小数）
     */
    public static boolean isNumeric(String str) {
        if (StringUtils.isBlank(str)) {
            return false;
        }
        return str.matches("^([-]?[0-9]+[.]?[0-9]*)$");
    }

    /**
     * 字符串是否为整数（含：正整数、负整数）
     */
    public static boolean isInteger(String str) {
        if (StringUtils.isBlank(str)) {
            return false;
        }
        return str.matches("^([-]?[0-9]+)$");
    }

    /**
     * 字符串是否为正整数
     */
    public static boolean isPositiveInteger(String str) {
        if (StringUtils.isBlank(str)) {
            return false;
        }
        return str.matches("^([0-9]+)$");
    }

    /**
     * 首字母大写
     */
    public static String upperFirstCase(String str) {
        if (StringUtils.isBlank(str)) {
            return str;
        }
        char char0 = str.charAt(0);
        if ('a' <= char0 && char0 <= 'z') {
            String str0 = str.substring(0, 1);
            return str.replaceFirst(str0, str0.toUpperCase());
        }
        return str;
    }

    /**
     * 首字母小写
     */
    public static String lowerFirstCase(String str) {
        if (StringUtils.isBlank(str)) {
            return str;
        }
        char char0 = str.charAt(0);
        if ('A' <= char0 && char0 <= 'Z') {
            String str0 = str.substring(0, 1);
            return str.replaceFirst(str0, str0.toLowerCase());
        }
        return str;
    }

    /**
     * 生成指定位数随机数字串
     */
    public static String getGiveLenRandomNum(int len) {
        StringBuilder str = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < len; i++) {
            str.append(random.nextInt(9));
        }
        return str.toString();
    }

    /**
     * 获取所有父级编码（不返回本身）
     */
    public static LinkedHashSet<String> getParentLevelCode(String levelCode, int step) {
        LinkedHashSet<String> setR = Sets.newLinkedHashSet();
        if (StringUtils.isBlank(levelCode)) {
            return setR;
        }
        String code = levelCode.trim();
        while (StringUtils.isNotBlank(code) && code.length() >= step) {
            code = code.substring(0, code.length() - step);
            if (code.length() >= step) {
                setR.add(code);
            }
        }
        return setR;
    }

    /**
     * 截取子串，返回子串集合
     * <p>
     * 如：输入：123{444}567{888}9，则返回：[444, 888]
     */
    public static List<String> getSubItems(String strIn, String left, String right, boolean returnDistinct) {
        List<String> listR = Lists.newArrayList();
        if (StringUtils.isBlank(strIn) || StringUtils.isBlank(left) || StringUtils.isBlank(right)
                || !strIn.contains(left) || !strIn.contains(right)) {
            return listR;
        }
        String str = strIn;
        while (StringUtils.isNotBlank(str) && str.contains(left) && str.contains(right)) {
            try {
                String cut = str.substring(str.indexOf(left) + 1, str.indexOf(right));
                if (StringUtils.isNotBlank(cut)) {
                    listR.add(cut);
                }
                str = str.substring(str.indexOf(right) + 1);
            } catch (Exception e) {
                throw new RuntimeException("获取子项出现错误！");
            }
        }
        if (returnDistinct) {
            listR = listR.stream().distinct().collect(Collectors.toList());
        }
        return listR;
    }

    /**
     * 统计某个字符在字符串中出现的次数
     */
    public static int getCharCount(String str, String c) {
        if (StrUtil.isBlank(str)) {
            return 0;
        }
        char[] charArray = c.toCharArray();
        return getCharCount(str, charArray[0]);
    }

    /**
     * 统计某个字符在字符串中出现的次数
     */
    public static int getCharCount(String str, char c) {
        int count = 0;
        char[] charArray = str.toCharArray();
        for (char param : charArray) {
            if (param == c) {
                count++;
            }
        }
        return count;
    }
}
