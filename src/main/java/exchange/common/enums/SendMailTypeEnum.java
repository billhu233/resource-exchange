package exchange.common.enums;

import cn.hutool.core.util.ArrayUtil;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

/*
 * @description
 * @author hb
 * @since 2024/3/6 10:41
 */
@Getter
@AllArgsConstructor
public enum SendMailTypeEnum implements BaseEnum, IntArrayValuable {

    LOGIN(0, "登录"),
    REGISTER(1, "注册"),
    ;

    private final Integer value;
    private final String info;

    public static final int[] ARRAYS = Arrays.stream(values()).mapToInt(SendMailTypeEnum::getValue).toArray();

    @Override
    public int[] array() {
        return ARRAYS;
    }

    public static SendMailTypeEnum valueOf(Integer value) {
        return ArrayUtil.firstMatch(x -> x.getValue().equals(value), SendMailTypeEnum.values());
    }
}
