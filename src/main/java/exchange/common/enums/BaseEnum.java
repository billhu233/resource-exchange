package exchange.common.enums;

import java.util.Objects;

/**
 * <p>
 *     枚举基类
 * </p>
 */
public interface BaseEnum {

    /**
     * 获取枚举类的值
     */
   Integer getValue();

    /**
     * 获取枚举类的说明
     *
     * @return String
     */
   String getInfo();

    /**
     * 比较参数是否与枚举类的value相同
     *
     * @param value 枚举值
     * @return boolean
     */
    default boolean equalsValue(Integer value) {
        return Objects.equals(getValue(), value);
    }

    /**
     * 比较枚举类是否相同
     *
     * @param baseEnum 枚举
     * @return boolean
     */
    default boolean equals(BaseEnum baseEnum) {
        return Objects.equals(getValue(), baseEnum.getValue()) && Objects.equals(getInfo(), baseEnum.getInfo());
    }
}
