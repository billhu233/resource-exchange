package exchange.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * <p>
 *     全局错误码枚举
 *     0-999 系统异常编码保留
 * </p>
 *
 * @author sunql
 * @since 2023-10-21
 */
@Getter
@AllArgsConstructor
public enum ResultCode implements IResultCode {

	// ========== 客户端错误段 ==========
	SUCCESS(0, "操作成功"),
	BAD_REQUEST(400, "请求参数不正确"),
	UN_AUTHORIZED(401, "认证失败，无法访问系统资源"),
	FORBIDDEN(403, "没有访问权限，请联系管理员授权"),
	NOT_FOUND(404, "请求未找到"),
	METHOD_NOT_ALLOWED(405, "请求方法不正确"),
	LOCKED(423, "请求失败，请稍后重试"),
	TOO_MANY_REQUESTS(429, "请求过于频繁，请稍后重试"),

	// ========== 服务端错误段 ==========
	INTERNAL_SERVER_ERROR(500, "网络错误，请联系管理员"),
	UNKNOWN(999, "未知错误"),

	// ========== 自定义错误段 ==========
	REPEATED_REQUESTS(900, "重复请求，请稍后重试"),
	INVALID_ENUM_VALUE(901, "非法枚举值：{}，{}！"), // 重复请求
	PERMISSION_ERROR(902, "权限参数类型不合法"),
	NON_UPDATE_PERMISSION_ID(903, "主键参数为不能空"),
	UPDATE_PERMISSION_ERROR(904, "更新操作权限拦截执行报错"),
	NON_UPDATE_TABLE(905, "表信息获取失败"),
	NON_UPDATE_PERMISSION(906, "当前用户无进行此项操作的权限"),
	JOB_START_ERROR(907, "动态启动job任务执行失败！"),
	JOB_ADD_ERROR(908, "动态添加job任务调用失败！"),
	JOB_STOP_ERROR(909, "停止job任务接口调用失败！"),
	JOB_LOGIN_ERROR(910, "请求xxl-job接口没有携带token信息！"),
	JOB_TRIGGER_ERROR(911, "动态触发job任务调用失败！"),
	;

	/**
	 * code编码
	 */
	final Integer code;
	/**
	 * 中文信息描述
	 */
	final String message;

}

