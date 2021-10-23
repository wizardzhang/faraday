package com.xyz.wizard.faraday.common.constant;

/**
 * 描述
 *
 * @author wizard
 * @version 1.0
 * @date 2021/09/30 15:51:41
 */
public interface AuthConstants {

    /**
     * 认证请求头key
     */
    String AUTHORIZATION_KEY = "Authorization";

    /**
     * JWT令牌前缀
     */
    String AUTHORIZATION_PREFIX = "bearer ";


    /**
     * Basic认证前缀
     */
    String BASIC_PREFIX = "Basic ";

    /**
     * JWT载体key
     */
    String JWT_PAYLOAD_KEY = "payload";

    /**
     * JWT ID 唯一标识
     */
    String JWT_JTI = "jti";

    /**
     * JWT ID 唯一标识
     */
    String JWT_EXP = "exp";

    /**
     * 黑名单token前缀
     */
    String TOKEN_BLACKLIST_PREFIX = "auth:token:blacklist:";

    String USER_ID_KEY = "userId";

    String USER_NAME_KEY = "username";

    String CLIENT_ID_KEY = "client_id";

    /**
     * JWT存储权限前缀
     */
    String AUTHORITY_PREFIX = "ROLE_";

    /**
     * JWT存储权限属性
     */
    String JWT_AUTHORITIES_KEY = "authorities";

    String GRANT_TYPE_KEY = "grant_type";

    String REFRESH_TOKEN = "refresh_token";

    String APP_API_PATTERN = "/*/app-api/**";

    String LOGOUT_PATH = "/youlai-auth/oauth/logout";

    /**
     * 新增菜单路径,新增不存在的路由会导致系统无法访问，线上禁止新增菜单的操作
     */
    String SAVE_MENU_PATH = "/youlai-admin/api/v1/menus";
}
