package exchange.common.constants;

/**
 * 系统 公用枚举
 *
 * @author sunql
 * @since 2023-10-25
 */
public interface SystemConstants {

    /**
     * 超级管理员 - 默认（勿改）
     */
    Long SUPER_ADMIN_ROLE = 1724274346168213501L;

    /**
     * 系统默认用户
     */
    Long DEFAULT_SYSTEM_USER = 1719656916609232896L;

    String DEFAULT_SYSTEM_USER_NAME = "系统默认用户";

    /**
     * 树形结构 根节点id
     */
    Long ROOT_MENU_ID = 0L;

    String SELECT_LAST_SQL = "limit 1";

    /**
     * 层级编码长度
     */
    String DEFAULT_LONG_NO = "000";

    /**
     * 默认租户id
     */
    String DEFAULT_TENANT = "000000";

    /**
     * loginId构造拼接字符串(勿改为其他链接符)
     */
    String LOGIN_ID_JOIN_CODE = "_";

    /**
     * 施工打卡示例图默认relateId
     */
    Long WORKER_PUNCH_CLOCK_EXAMPLE_DEFAULT_ID = 0L;

    /**
     * 请求上下文的用户id
     */
    String REQUEST_LOGIN_USER_ID = "login_user_id";

    /**
     * http协议
     */
    String HTTP_SCHEMA = "http://";

    /**
     * https协议
     */
    String HTTPS_SCHEMA = "https://";

    /**
     * 不分页查询调用分页接口的pageSize设置
     */
    Integer PAGE_SIZE_MAX = 100000000;
}
