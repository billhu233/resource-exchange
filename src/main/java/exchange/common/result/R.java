package exchange.common.result;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnore;
import exchange.common.exception.BusinessException;
import exchange.common.exception.IResultCode;
import exchange.common.exception.ResultCode;
import exchange.common.exception.utils.AssertUtil;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.util.Objects;

/**
 * <p>
 *     通用返回
 * </p>
 *
 * @author sunql
 * @since 2023-10-21
 */
@Data
@Schema(description = "返回信息")
public class R<T> implements Serializable {
    private static final long serialVersionUID = -7042037296682439211L;

    /**
     * 错误码
     *
     * @see ResultCode#getCode()
     */
    @Schema(description = "状态码")
    private Integer code;

    /**
     * 返回数据
     */
    @Schema(description = "承载数据")
    private T data;

    /**
     * 错误提示，用户可阅读
     *
     * @see ResultCode#getMessage
     */
    @JsonAlias(value = {"message", "msg"})
    @Schema(description = "返回消息")
    private String message;


    /**
     * 将传入的 result 对象，转换成另外一个泛型结果的对象
     *
     * 因为 A 方法返回的 CommonResult 对象，不满足调用其的 B 方法的返回，所以需要进行转换。
     *
     * @param result 传入的 result 对象
     * @param <T> 返回的泛型
     * @return 新的 CommonResult 对象
     */
    public static <T> R<T> error(R<?> result) {
        return error(result.getCode(), result.getMessage());
    }

    public static <T> R<T> error(Integer code, String message) {
        AssertUtil.isTrue(ResultCode.SUCCESS.getCode().equals(code), "code 必须是错误的！");
        R<T> result = new R<>();
        result.code = code;
        result.message = message;
        return result;
    }

    public static <T> R<T> error(IResultCode errorCode) {
        return error(errorCode.getCode(), errorCode.getMessage());
    }

    public static <T> R<T> success(T data) {
        R<T> result = new R<>();
        result.code = ResultCode.SUCCESS.getCode();
        result.data = data;
        result.message = "";
        return result;
    }

    public static <T> R<T> success() {
        R<T> result = new R<>();
        result.code = ResultCode.SUCCESS.getCode();
        result.message = "操作成功";
        return result;
    }

    public static boolean isSuccess(Integer code) {
        return Objects.equals(code, ResultCode.SUCCESS.getCode());
    }

    @JsonIgnore // 避免 jackson 序列化
    public boolean isSuccess() {
        return isSuccess(code);
    }

    @JsonIgnore // 避免 jackson 序列化
    public boolean isError() {
        return !isSuccess();
    }

    // ========= 和 Exception 异常体系集成 =========

    /**
     * 判断是否有异常。如果有，则抛出 {@link BusinessException} 异常
     */
    public void checkError() throws BusinessException {
        if (isSuccess()) {
            return;
        }
        // 业务异常
        throw new BusinessException(code, message);
    }

    /**
     * 判断是否有异常。如果有，则抛出 {@link BusinessException} 异常
     * 如果没有，则返回 {@link #data} 数据
     */
    @JsonIgnore // 避免 jackson 序列化
    public T getCheckedData() {
        checkError();
        return data;
    }

    public static <T> R<T> error(BusinessException exception) {
        return error(exception.getCode(), exception.getMessage());
    }

}
