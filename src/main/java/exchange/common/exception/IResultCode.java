package exchange.common.exception;

import java.io.Serializable;

/**
 * <p>
 * 错误码对象
 *    	全局错误码，占用 [0, 999], 参见 {@link ResultCode}
 * </p>
 *
 * @author sunql
 * @since 2023-10-21
 */
public interface IResultCode extends Serializable {

	/**
	 * 获取消息
	 *
	 * @return 消息内容
	 */
	String getMessage();

	/**
	 * 获取状态码
	 *
	 * @return 状态码
	 */
	Integer getCode();

}

