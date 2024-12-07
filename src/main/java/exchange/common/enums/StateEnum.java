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
public enum StateEnum implements BaseEnum, IntArrayValuable {

    WAIT_ENABLE(0, "待启用"),
    ENABLED(1, "已启用"),
    DISABLED(1, "已禁用"),
    ;

    private final Integer value;
    private final String info;

    public static final int[] ARRAYS = Arrays.stream(values()).mapToInt(StateEnum::getValue).toArray();

    @Override
    public int[] array() {
        return ARRAYS;
    }

    public static StateEnum valueOf(Integer value) {
        return ArrayUtil.firstMatch(x -> x.getValue().equals(value), StateEnum.values());
    }
}
